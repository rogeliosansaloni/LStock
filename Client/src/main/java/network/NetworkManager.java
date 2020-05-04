package main.java.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import controller.LoginController;
import controller.MainController;
import controller.RegisterController;
import model.entities.AuthenticationInfo;
import model.entities.CompanyInfo;
import model.entities.ShareTrade;
import model.entities.TunnelObject;
import utils.JSONReader;
import view.LoginView;
import view.MainView;
import view.RegisterView;

public class NetworkManager extends Thread {
    private Socket serverSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private boolean running;
    private static NetworkManager instance = null;
    private NetworkConfiguration configuration;
    private MainController mainController;
    private LoginController loginController;
    private RegisterController registerController;
    private MainView mainView;
    private RegisterView registerView;
    private LoginView loginView;

    /**
     * Represents a Singleton
     *
     * @return single and shared global instance
     */
    public synchronized static NetworkManager getInstance() {
        try {
            if (instance == null) {
                instance = new NetworkManager();
            }
            return instance;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Constructor that initializes all the elements for server connection
     *
     * @throws IOException
     */
    private NetworkManager() throws IOException {
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
        // Initialize views
        initLoginRegisterView();
        initMainView();

        // Start main client thread
        running = true;
        start();
    }

    /**
     * Initializes the main view and its controller
     */
    private void initMainView() {
        this.mainView = new MainView();
        this.mainController = new MainController(mainView);
        this.mainView.registerMainController(mainController);
        this.mainView.registerBalanceController(this.mainController.getBalanceController());
        this.mainView.registerCompanyDetailViewController(this.mainController.getCompanyDetailController());
        this.mainView.setVisible(false);
    }

    /**
     * Initializes the login and register views, and their controllers
     */
    private void initLoginRegisterView() {
        this.loginView = new LoginView();
        this.registerView = new RegisterView();
        this.loginController = new LoginController(loginView, registerView);
        this.registerController = new RegisterController(registerView, loginView);
        loginView.registerController(loginController);
        registerView.registerController(registerController);

        // We only show the login view as the first screen
        loginView.setVisible(true);
    }

    /**
     * Stops the connection to the server and interrupts the client thread
     */
    public void stopServerConnection() {
        running = false;
        interrupt();
    }

    /**
     * Send a generic object through the sockets.
     *
     * @param object the object to be sent to the server
     * @throws IOException
     */
    public void sendTunnelObject(TunnelObject object) throws IOException {
        oos.writeObject(object);
    }

    /**
     * Sends information for login or registering a user
     *
     * @param object object that contains user information for login or registering in the system
     * @throws IOException
     */
    public void sendAuthentificationInformation(AuthenticationInfo object) throws IOException {
        oos.writeObject(object);
    }

    /**
     * Sends information of the company
     *
     * @param object object that contains user information for company details menu
     * @throws IOException
     */
    public void sendCompanyDetails(CompanyInfo object) throws IOException {
        oos.writeObject(object);
    }

    public void sendShareTrade(ShareTrade object) throws IOException {
        oos.writeObject(object);
    }

    /**
     * Runs the main client thread and receives objects coming from the server
     */
    @Override
    public void run() {
        try {
            while (running) {
                System.out.println("Waiting for object to be received...");
                TunnelObject received = (TunnelObject) ois.readObject();

                if (received instanceof AuthenticationInfo) {
                    AuthenticationInfo info = ((AuthenticationInfo) received);
                    if (info.getAction().equals("register")) {
                        if (info.isValidated()) {
                            registerController.closeRegisterView();
                            loginView.setVisible(true);
                        } else {
                            registerController.sendErrorMessage(info.getResponseType());
                        }
                    }
                    if (info.getAction().equals("login")) {
                        if (info.isValidated()) {
                            loginController.closeLoginView();
                            mainView.setVisible(true);
                        } else {
                            loginController.sendErrorMessage(info.getResponseType());
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
