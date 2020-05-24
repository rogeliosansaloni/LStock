package network;

import controller.LoginController;
import controller.MainController;
import controller.RegisterController;
import model.entities.*;
import utils.CompanyMapperImpl;
import utils.JSONReader;
import utils.ShareMapperImpl;
import utils.UserMapperImpl;
import view.LoginView;
import view.MainView;
import view.RegisterView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread extends Thread{

    private NetworkConfiguration configuration;
    private Socket serverSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private boolean running;
    private static ClientThread instance = null;
    private MainController mainController;
    private LoginController loginController;
    private RegisterController registerController;
    private UserMapperImpl mapper;
    private CompanyMapperImpl companyMapper;
    private ShareMapperImpl shareMapper;
    private StockManager model;

    /**
     * Represents a Singleton
     *
     * @return single and shared global instance
     */
    public synchronized static ClientThread getInstance() {
        try {
            if (instance == null) {
                instance = new ClientThread();
            }
            return instance;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ClientThread() throws IOException {
        // Get Network configuration from JSON
        JSONReader jsonReader = new JSONReader();
        configuration = jsonReader.getClientConfiguration();

        // Set up the connection to the server
        this.serverSocket = new Socket(configuration.getIp(), configuration.getPort()); // pass ip and port from NetworkConfiguration
        oos = new ObjectOutputStream(this.serverSocket.getOutputStream());
        oos.flush();
        ois = new ObjectInputStream(this.serverSocket.getInputStream());
    }

    /**
     * Starts the connection to the server. Initializes the main views and its controllers for the Client.
     */
    public void startServerConnection() {
        running = true;
        start();
    }

    /**
     * Runs the main client thread and receives objects coming from the server
     */
    @Override
    public void run() {
        try {
            while (running) {
                TunnelObject received = (TunnelObject) ois.readObject();

                if (received instanceof DetailViewInfo) {
                    CompanyDetailList companyDetails = ((DetailViewInfo) received).getCompanyDetailList();
                    ShareSellList sharesSells = ((DetailViewInfo) received).getShareSellList();
                    model.setCompanyDetails(companyMapper.converToCompanyDetails(companyDetails));
                    model.setSharesSell(shareMapper.converToSharesSell(sharesSells));
                    mainController.updateModel(model);
                    mainController.updateCompanyDetails();
                }

                if (received instanceof CompanyChangeList) {
                    CompanyChangeList companies = (CompanyChangeList) received;
                    ArrayList<CompanyChange> companiesChange = companyMapper.convertToCompaniesChange(companies);
                    model.setCompaniesChange(companiesChange);
                    mainController.updateModel(model);
                }

                if (received instanceof ShareChangeList) {
                    ShareChangeList shares = (ShareChangeList) received;
                    ArrayList<ShareChange> sharesChange = shareMapper.convertToSharesChange(shares);
                    model.setSharesChange(sharesChange);
                    mainController.updateModel(model);
                    mainController.updateShareView();
                }




            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
