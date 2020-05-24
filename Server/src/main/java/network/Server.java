package network;

import controller.BotsEditComboBoxController;
import controller.BotsRemoveComboBoxController;
import controller.MainController;
import model.managers.BotManager;
import utils.JSONReader;
import view.MainView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

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

    public Server() throws IOException {
        initServerConfiguration();
        this.isOn = false;
        this.sSocket = new ServerSocket(port);
        this.clients = new LinkedList<DedicatedServer>();
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
    }

    /**
     * Stops connection
     */
    public void stopServer() {
        // Stop main server thread
        isOn = false;
        stopListening();
        this.interrupt();
    }

    /**
     * Initializes main view
     */
    public void initMainView () {
        botModel = new BotManager();
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
                DedicatedServer client = new DedicatedServer(clientSocket);
                clients.add(client);
                for (DedicatedServer c : clients) {
                    c.setClients(clients);
                }
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
}
