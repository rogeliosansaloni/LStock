package network;

import controller.BotsEditComboBoxController;
import controller.BotsRemoveComboBoxController;
import controller.MainController;
import model.entities.*;
import model.managers.BotManager;
import model.managers.StockManager;
import utils.CompanyMapperImpl;
import utils.JSONReader;
import utils.ShareMapperImpl;
import view.MainView;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Server class
 */
public class Server extends Thread {
    private String ip;
    private int port;
    private ServerSocket sSocket;
    private boolean isOn;
    private LinkedList<DedicatedServer> clients;
    private ServerConfiguration serverConfiguration;
    private MainView mainView;
    private MainController mainController;
    private BotManager botModel;
    private StockManager stockModel;
    private TimerTask task;
    private CompanyMapperImpl companyMapper;
    private ShareMapperImpl shareMapper;

    public Server() throws IOException {
        initServerConfiguration();
        this.isOn = false;
        this.sSocket = new ServerSocket(port);
        this.clients = new LinkedList<DedicatedServer>();
        this.companyMapper = new CompanyMapperImpl();
        this.shareMapper = new ShareMapperImpl();
    }

    /**
     * Initializes servers configuration
     */
    private void initServerConfiguration() {
        JSONReader jsonReader = new JSONReader();
        this.serverConfiguration = jsonReader.getServerConfiguration();
        this.ip = serverConfiguration.getIp();
        this.port = serverConfiguration.getPort();
    }

    /**
     * Starts the connection and initializes the servers main view
     */
    public void startServer() {
        initMainView();
        // Start main server thread
        isOn = true;
        this.start();
        updateClients();
    }

    /**
     * Stops connection
     */
    public void stopServer() {
        // Stop main server thread
        isOn = false;
        stopListening();
        this.interrupt();
        stopUpdatingClients();
    }

    /**
     * Initializes main view
     */
    public void initMainView() {
        stockModel = new StockManager();
        botModel = new BotManager(stockModel);
        mainView = new MainView();
        mainController = new MainController(mainView, botModel);
        mainView.registerController(mainController);
        mainView.registerHomeController(mainController.getHomeController());
        mainView.registerBotMenuController(mainController.getBotMenuController());
        mainView.registerBotCreateController(mainController.getBotsCreateController());
        mainView.registerBotRemoveController(mainController.getBotsRemoveController(),
                new BotsRemoveComboBoxController(mainView.getBotsRemoveView(), botModel));
        mainView.registerBotListController(mainController.getBotsListController());
        mainView.registerBotEditController(mainController.getBotsEditController(),
                new BotsEditComboBoxController(mainView.getBotsEditView(), botModel));
        mainView.setVisible(true);
    }

    public void run() {
        while (isOn) {
            try {
                // Wait for petitions to accept them
                // Block the execution
                System.out.println("Waiting for clients to connect...");
                Socket clientSocket = sSocket.accept();

                // Create dedicated server to attend to the client
                DedicatedServer client = new DedicatedServer(clientSocket, stockModel);
                clients.add(client);
                System.out.println("Client has connected correctly!");

                // Start dedicated server for the client
                client.startServerConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stop listening and stop server connection for each dedicated server
     */
    public void stopListening() {
        // Stop all dedicated servers when we are not listening to petitions
        for (DedicatedServer client : clients) {
            client.stopServerConnection();
        }
    }

    /**
     * Updates clients
     *
     * @throws IOException
     */
    public void updateAllClients() throws IOException {
        for (DedicatedServer client : clients) {
            ObjectOutputStream oosClient = null;
            if (isOn) {
                oosClient = client.getOos();

                // Get company change list
                ArrayList<CompanyChange> companies = stockModel.getCompaniesChange();
                CompanyChangeList companyChangeList = companyMapper.convertToCompanyChangeList(companies);

                // Get share change list
                ArrayList<ShareChange> sharesChange = stockModel.getSharesChange(client.getLoggedUser());
                ShareChangeList sharesChangeList = shareMapper.convertToShareChangeList(sharesChange);

                // Get detail view info
                ArrayList<CompanyDetail> companyDetails = stockModel.getCompanyDetails(client.getLoggedUser(), client.getCurrentCompanyId());
                ArrayList<ShareSell> shares = stockModel.getSharesSell(client.getLoggedUser(), client.getCurrentCompanyId());
                CompanyDetailList companyDetailList = companyMapper.convertToCompanyDetailList(companyDetails);
                ShareSellList shareSellList = shareMapper.convertToShareSellList(shares);
                DetailViewInfo detailViewInfo = new DetailViewInfo(companyDetailList, shareSellList);

                // Get user info
                User user = stockModel.getAllUserInfo(client.getLoggedUser());
                UserProfileInfo userProfileInfo = new UserProfileInfo(user.getUserId(), user.getNickname(), user.getEmail(), user.getDescription(), user.getTotalBalance());
                ThreadChange change = new ThreadChange(companyChangeList, detailViewInfo, sharesChangeList, userProfileInfo);

                if (oosClient != null) {
                    oosClient.writeObject(change);
                }
            }
        }
    }

    /**
     * Triggers updateAllClients every second
     */
    private void updateClients() {
        Timer timer = new Timer();
        this.task = new TimerTask() {
            @Override
            public void run() {
                try {
                    updateAllClients();
                    System.out.println("Send a threadChange.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(this.task, 10000, 20000);
    }

    /**
     * Stops updating all clients automatically
     */
    private void stopUpdatingClients() {
        this.task.cancel();
    }
}
