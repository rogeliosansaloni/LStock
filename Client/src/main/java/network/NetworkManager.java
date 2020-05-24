package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import controller.CompanyDetailFocusController;
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

/**
 * Manages the connection with the Server
 */
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
    private UserMapperImpl mapper;
    private CompanyMapperImpl companyMapper;
    private ShareMapperImpl shareMapper;
    private StockManager model;
    private ClientThread clientThread;

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

        ClientThread.getInstance().startServerConnection();

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
        init();
        // Initialize views
        initLoginRegisterView();
        // Start main client thread
        running = true;
        start();
    }

    /**
     * Initialize the mappers
     */
    private void init() {
        this.mapper = new UserMapperImpl();
        this.companyMapper = new CompanyMapperImpl();
        this.shareMapper = new ShareMapperImpl();
    }

    /**
     * Initializes the main view and its controller
     */
    private void initMainView(ArrayList<CompanyChange> companyChange) {
        model.setCompaniesChange(companyChange);
        mainView = new MainView();
        mainController = new MainController(mainView, model, loginView);
        mainController.updateCompanyList();
        mainView.registerMainController(mainController);
        mainView.initHeaderInformation(model.getUser().getNickname(), model.getUser().getTotalBalance());
        mainView.setVisible(true);
    }

    /**
     * Sets the company changes in the model
     * @param companyChange list of company changes
     */
    private void initCompanies(ArrayList<CompanyChange> companyChange) {
        model.setCompaniesChange(companyChange);
    }

    /**
     * Reinitialize the main view with the new login information.
     * @param companyChange the list of company changes
     */
    private void reinitMainView(ArrayList<CompanyChange> companyChange) {

        // Refresh the header information with new user information
        mainView.initHeaderInformation(model.getUser().getNickname(), model.getUser().getTotalBalance());

        // Refresh the balance view
        mainController.getBalanceController().refreshBalanceView();
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
        loginView.registerFocusController();
        registerView.registerController(registerController);
        registerView.registerFocusController();
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
    public void sendUserShares(TunnelObject object) throws IOException {
        oos.writeObject(object);
    }

    /**
     * Sends information of the buy/sell of shares
     * @param object object that contains information for buy and sell of shares
     * @throws IOException
     */
    public void sendShareTrade(ShareTrade object) throws IOException {
        oos.writeObject(object);
    }

    /**
     * Sends user profile information
     * @param object object that contains the user profile information
     * @throws IOException
     */
    public void sendUserProfileInfo(TunnelObject object) throws IOException {
        oos.writeObject(object);
    }

    /**
     * Sends share change information
     * @param object object that contains the the id of the user from who we want to obtain the shares
     * @throws IOException
     */
    public void sendShareChange(TunnelObject object) throws IOException {
        oos.writeObject(object);
    }

    /**
     * Runs the main client thread and receives objects coming from the server
     */
    @Override
    public void run() {
        System.out.println("Network Manager run");
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
                            User user = mapper.authenticationInfoToUser((AuthenticationInfo) received);
                            model = new StockManager(user);
                            loginController.closeLoginView();
                            NetworkManager.getInstance().sendTunnelObject(new CompanyChangeList());
                        } else {
                            loginController.sendErrorMessage(info.getResponseType());
                        }
                    }
                }

                if (received instanceof UserProfileInfo) {
                    UserProfileInfo info = (UserProfileInfo) received;
                    if (info.getAction().equals("balance")) {
                        mainController.updateTotalBalance(info.getTotalBalance());
                    } else if (info.getAction().equals("profileView")) {
                        User user = mapper.userProfileInfoToUser(info);
                        model.updateUserInfo(user);
                        mainController.updateModel(model);
                        mainController.updateProfileView();
                    }
                }

                if (received instanceof ShareTrade) {
                    ShareTrade info = ((ShareTrade) received);
                    mainController.updateTotalBalance(info.getTotalBalance());
                    if(info.getView().equals("CompanyDetail")){
                        mainController.getCompanyController().sendUserShares(info.getCompanyId());
                    } else if(info.getView().equals("Shares")){
                        mainController.sendSharesChange();
                    }
                }

                if (received instanceof CompanyChangeList) {
                    CompanyChangeList companies = (CompanyChangeList) received;
                    ArrayList<CompanyChange> companiesChange = companyMapper.convertToCompaniesChange(companies);
                    if (mainView == null) {
                        initMainView(companiesChange);
                    }else{
                        reinitMainView(companiesChange);
                    }
                    model.setCompaniesChange(companiesChange);
                    mainController.updateModel(model);
                    mainController.updateCompanyList();
                    mainView.setVisible(true);
                }

                if (received instanceof DetailViewInfo) {
                    CompanyDetailList companyDetails = ((DetailViewInfo) received).getCompanyDetailList();
                    ShareSellList sharesSells = ((DetailViewInfo) received).getShareSellList();
                    model.setCompanyDetails(companyMapper.converToCompanyDetails(companyDetails));
                    model.setSharesSell(shareMapper.converToSharesSell(sharesSells));
                    mainController.updateModel(model);
                    mainController.updateCompanyDetails();
                }

                if (received instanceof ShareChangeList) {
                    ShareChangeList shares = (ShareChangeList) received;
                    ArrayList<ShareChange> sharesChange = shareMapper.convertToSharesChange(shares);
                    model.setSharesChange(sharesChange);
                    mainController.updateModel(model);
                    mainController.updateShareView();
                }


                if (received instanceof ThreadChange){
                    ((ThreadChange) received).asdf();
                    mainController.sendCompaniesChange();
                    mainController.sendUserProfileInfo();
                    mainController.sendSharesChange();
                    if (model.getCompanyDetails()!=null) {
                        mainController.getCompanyController().sendUserShares(model.getCompanyDetails().get(0).getCompanyId());
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
