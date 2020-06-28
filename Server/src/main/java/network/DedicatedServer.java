package network;

import model.entities.*;
import model.managers.StockManager;
import utils.CompanyMapperImpl;
import utils.ShareMapperImpl;
import utils.UserMapperImpl;

import java.io.IOException;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Dedicated server class
 */
public class DedicatedServer extends Thread {
    private boolean isOn;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket sClient;
    private StockManager stockModel;
    private UserMapperImpl mapper;
    private CompanyMapperImpl companyMapper;
    private ShareMapperImpl shareMapper;
    private int loggedUser;
    private int currentCompanyId = 1;
    private TimerTask task;


    /**
     * DedicatedServer constructor
     *
     * @param sClient client socket
     */
    public DedicatedServer(Socket sClient, StockManager stockModel) {
        this.sClient = sClient;
        this.stockModel = stockModel;
        this.mapper = new UserMapperImpl();
        this.companyMapper = new CompanyMapperImpl();
        this.shareMapper = new ShareMapperImpl();
        this.loggedUser = -1;
    }

    /**
     * Stops the connection to the server
     */
    public void stopServerConnection() {
        this.isOn = false;
        this.interrupt();
        stopUpdatingClient();
    }

    /**
     * Starts the connection to the server
     */
    public void startServerConnection() {
        this.isOn = true;
        this.start();
        updateClient();
    }

    /**
     * Main dedicated server thread that sends and receives information to and from the client side. It handles
     * each object received.
     */
    public void run() {
        try {
            // Create the communication channels
            ois = new ObjectInputStream(sClient.getInputStream());
            oos = new ObjectOutputStream(sClient.getOutputStream());

            while (isOn) {
                TunnelObject tunnelObject = (TunnelObject) ois.readObject();

                if (tunnelObject instanceof AuthenticationInfo) {
                    AuthenticationInfo info = ((AuthenticationInfo) tunnelObject);
                    User user = mapper.authenticationInfoToUser((AuthenticationInfo) tunnelObject);
                    // Check if the object is for registering users
                    if (info.getAction().equals("register")) {
                        AuthenticationInfo authInfoRegister = stockModel.registerUser(user);
                        oos.writeObject(authInfoRegister);
                    } else {
                        // Check if we need user validation for login
                        if (info.getAction().equals("login")) {
                            AuthenticationInfo authInfoLogin = stockModel.validateUser(user);
                            if (authInfoLogin.isValidated()) {
                                loggedUser = authInfoLogin.getId();
                            }
                            oos.writeObject(authInfoLogin);
                        }
                    }
                }
                // Check if the object is for updating the users balance or description
                if (tunnelObject instanceof UserProfileInfo) {
                    UserProfileInfo userInfo = ((UserProfileInfo) tunnelObject);
                    User user = mapper.userProfileInfoToUser((UserProfileInfo) tunnelObject);
                    if (userInfo.getAction().equals("balance")) {
                        UserProfileInfo userProfileInfo = stockModel.updateUserBalance(user);
                        oos.writeObject(userProfileInfo);
                    } else if (userInfo.getAction().equals("information")) {
                        UserProfileInfo userProfileInfo = stockModel.updateUserInformation(user);
                        oos.writeObject(userProfileInfo);
                    } else if (userInfo.getAction().equals("profileView")) {
                        UserProfileInfo userProfileInfo = stockModel.getUserProfileInfo(user);
                        oos.writeObject(userProfileInfo);
                    }
                }

                if (tunnelObject instanceof ShareTrade) {
                    ShareTrade shareTrade = (ShareTrade) tunnelObject;
                    Purchase[] purchases = shareMapper.shareTradeToPurchase(shareTrade);
                    User user = shareMapper.shareTradeToUser(shareTrade);
                    Company company = shareMapper.shareTradeToCompany(shareTrade);
                    ShareTrade share = stockModel.updatePurchaseBuy(user, company, purchases, shareTrade.getActionToDo(), shareTrade.getView());
                    oos.writeObject(share);
                }

                if (tunnelObject instanceof CompanyList) {
                    ArrayList<Company> companies = stockModel.getCompanies();
                    CompanyList companyList = companyMapper.convertToCompanyList(companies);
                    oos.writeObject(companyList);
                }

                if (tunnelObject instanceof CompanyChangeList) {
                    ArrayList<CompanyChange> companies = stockModel.getCompaniesChange();
                    CompanyChangeList companyChangeList = companyMapper.convertToCompanyChangeList(companies);
                    oos.writeObject(companyChangeList);
                }

                if (tunnelObject instanceof UserShares) {
                    ArrayList<CompanyDetail> companies = stockModel.getCompanyDetails(((UserShares) tunnelObject).getUserId(), ((UserShares) tunnelObject).getCompanyId());
                    ArrayList<ShareSell> shares = stockModel.getSharesSell(((UserShares) tunnelObject).getUserId(), ((UserShares) tunnelObject).getCompanyId());
                    currentCompanyId = ((UserShares) tunnelObject).getCompanyId();
                    CompanyDetailList companyDetailList = companyMapper.convertToCompanyDetailList(companies);
                    ShareSellList shareSellList = shareMapper.convertToShareSellList(shares);
                    DetailViewInfo detailViewInfo = new DetailViewInfo(companyDetailList, shareSellList);
                    oos.writeObject(detailViewInfo);
                }


                if (tunnelObject instanceof ShareChangeList) {
                    ArrayList<ShareChange> sharesChange = stockModel.getSharesChange(((ShareChangeList) tunnelObject).getUserId());
                    ShareChangeList sharesChangeList = shareMapper.convertToShareChangeList(sharesChange);
                    oos.writeObject(sharesChangeList);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            try {
                if (!sClient.isClosed()) {
                    sClient.close();
                }
            } catch (IOException e2) {
                System.out.println("Error closing the communication with the server.");
            }
            stopServerConnection();
            System.out.println("Stopped client connection to the server...");
        } catch (IOException e) {
            stopServerConnection();
            System.out.println("Stopped client connection to the server...");
            e.printStackTrace();
        }
    }

    /**
     * Triggers sendThreadChange every certain period of time
     */
    private void updateClient() {
        Timer timer = new Timer();
        this.task = new TimerTask() {
            @Override
            public void run() {
                try {
                    sendThreadChange();
                    System.out.println("Send a threadChange.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(this.task, 10000, 20000);
    }

    /**
     * Updates clients
     *
     * @throws IOException
     */
    public void sendThreadChange() throws IOException {
        if (isOn) {
            // Get company change list
            ArrayList<CompanyChange> companies = stockModel.getCompaniesChange();
            CompanyChangeList companyChangeList = companyMapper.convertToCompanyChangeList(companies);

            // Get share change list
            ArrayList<ShareChange> sharesChange = stockModel.getSharesChange(this.loggedUser);
            ShareChangeList sharesChangeList = shareMapper.convertToShareChangeList(sharesChange);

            // Get detail view info
            ArrayList<CompanyDetail> companyDetails = stockModel.getCompanyDetails(this.loggedUser, this.currentCompanyId);
            ArrayList<ShareSell> shares = stockModel.getSharesSell(this.loggedUser, this.currentCompanyId);
            CompanyDetailList companyDetailList = companyMapper.convertToCompanyDetailList(companyDetails);
            ShareSellList shareSellList = shareMapper.convertToShareSellList(shares);
            DetailViewInfo detailViewInfo = new DetailViewInfo(companyDetailList, shareSellList);

            // Get user info
            User user = stockModel.getAllUserInfo(this.loggedUser);
            UserProfileInfo userProfileInfo = new UserProfileInfo(user.getUserId(), user.getNickname(), user.getEmail(), user.getDescription(), user.getTotalBalance());
            ThreadChange change = new ThreadChange(companyChangeList, detailViewInfo, sharesChangeList, userProfileInfo);

            this.oos.writeObject(change);
        }
    }

    /**
     * Stops updating the client automatically
     */
    private void stopUpdatingClient() {
        this.task.cancel();
    }

}
