package model.managers;

import database.CompanyDao;
import database.ShareDao;
import database.UserDao;
import model.entities.*;
import database.DBConnector;
import utils.ShareMapperImpl;
import utils.UserMapperImpl;

import java.util.ArrayList;

/**
 * Represents the manager that control all transactions and stock operations
 */
public class StockManager {
    private ArrayList<Company> companies;
    private ArrayList<CompanyChange> companiesChange;
    private ArrayList<ShareChange> sharesChange;
    private ArrayList<ArrayList<CompanyDetail>> companyDetails;
    private UserDao userDao;
    private CompanyDao companyDao;
    private ShareDao shareDao;
    private UserMapperImpl mapper;
    private ShareMapperImpl shareMapper;

    /**
     * Constructor for StockManager
     */
    public StockManager() {
        DBConnector connector = new DBConnector();
        userDao = new UserDao(connector);
        shareDao = new ShareDao(connector);
        companyDao = new CompanyDao(connector);
        mapper = new UserMapperImpl();
        shareMapper = new ShareMapperImpl();
        connector.connect();
    }

    /**
     * Registers a user if the conditions are met
     *
     * @param user the user
     * @return Authentification with the information we need to send for the client
     */
    public AuthenticationInfo registerUser(User user) {
        String response = userDao.createUser(user);
        AuthenticationInfo info = mapper.userToAuthenticationInfo(user);
        if (!response.equals("Register Success")) {
            info.setValidated(false);
        } else {
            info.setValidated(true);
        }
        info.setAction("register");
        info.setResponseType(response);
        return info;
    }

    /**
     * Validates the user
     *
     * @param user The user
     * @return AuthenticationInfo with the validated users information
     */
    public AuthenticationInfo validateUser(User user) {
        String response = userDao.validateUser(user);
        AuthenticationInfo info = mapper.userToAuthenticationInfo(user);
        if (response.equals("Login Success")) {
            info.setValidated(true);
        } else {
            info.setValidated(false);
        }
        info.setAction("login");
        info.setResponseType(response);
        return info;
    }

    /**
     * Updates the user new balanace
     *
     * @param user The user
     * @return UserProfileInfo with the updated information of the user
     */
    public UserProfileInfo updateUserBalance(User user) {
        userDao.updateUserBalanceLoad(user);
        UserProfileInfo info = mapper.userToUserProfileInfo(user);
        info.setAction("balance");
        return info;
    }

    /**
     * Updates the users description, for now.
     *
     * @param user The user
     * @return UserProfileInfo with the the update information of the user
     */
    public UserProfileInfo updateUserInformation(User user) {
        userDao.updateUserInformation(user);
        UserProfileInfo info = mapper.userToUserProfileInfo(user);
        info.setAction("information");
        return info;
    }

    /**
     * Gets the profile info of the user.
     *
     * @param user The user
     * @return UserProfileInfo with the the update information of the user
     */
    public UserProfileInfo getUserProfileInfo(User user) {
        userDao.getUserProfileInfo(user);
        UserProfileInfo info = mapper.userToUserProfileInfo(user);
        info.setAction("profileView");
        return info;
    }

    /**
     * Gets all the info of the user.
     *
     * @param user_id the user_id
     * @return User the information of the user
     */
    public User getAllUserInfo(int user_id) {
        User user = userDao.getAllUserInfo(user_id);
        return user;
    }

    /**
     * Creates a new share between company and user.
     *
     * @param user    the user
     * @param company the company
     * @return ShareTrade with the new values of users total balance and company value
     */
    public ShareTrade updatePurchaseBuy(User user, Company company, Purchase[] purchases, String action, String view) {
        //Updates the user balance
        userDao.updateUserBalance(user);
        //If the action is Sell, we want to decrease the number of shares.
        if (action.equals("BUY")) {
            //Updates the purchased share
            shareDao.updatePurchasedShare(purchases[0]);
        } else {
            for (int i = 0; i < purchases.length; i++) {
                purchases[i].setShareQuantity(-purchases[i].getShareQuantity());
                //Updates the purchased share
                shareDao.updatePurchasedShare(purchases[i]);
            }
        }
        //Recalculates the new value of the company
        company = updateCompanyValue(company, action);

        ShareTrade info = shareMapper.userCompanyToShareTrade(user, company);
        info.setView(view);
        return info;
    }

    /**
     * Recalculates the new value of the company
     *
     * @param company company
     * @param action the action
     */
    public Company updateCompanyValue(Company company, String action) {
        float currentValue = companyDao.getCompanyCurrenValue(company.getCompanyId());
        company.setValue(currentValue);
        company.recalculateValue(action);
        //Updates the company new value
        companyDao.updateCompanyNewValue(company);
        return company;
    }

    /**
     * Buys a company share and updates the purchased share
     *
     * @param purchase Purchased share
     */
    public void buyShare(Purchase purchase) {
        Company company = new Company(purchase.getCompanyId());
        updateCompanyValue(company, "BUY");
    }

    /**
     * Sells a company share/shares and updates it
     *
     * @param purchase Purchased share
     */
    public void sellShare(Purchase purchase) {
        Company company = new Company(purchase.getCompanyId());
        updateCompanyValue(company, "SELL");
    }

    /**
     * Gets the current share identifier
     *
     * @param id Share identifier
     * @return the share identifier
     */
    public int getShareId(int id) {
        return shareDao.getCurrentShareId(id);
    }

    /**
     * Returns an Arraylist with all the companies
     *
     * @return ArrayList<Company> with all the companies
     */
    public ArrayList<Company> getCompanies() {
        companies = companyDao.getAllCompanies();
        return companies;
    }

    /**
     * Gets all the companies in the LStock, with their name, current share price and
     * the difference between the current price and the one that had 5 minutes ago
     *
     * @return ArrayList<CompanyChange> a list of the information mentioned before
     */
    public ArrayList<CompanyChange> getCompaniesChange() {
        companiesChange = companyDao.getCompaniesChange();
        return companiesChange;
    }

    /**
     * Returns an Arraylist with the Top 10 Companies in share value
     *
     * @return ArrayList<Top10> with Top 10 Companies
     */
    public ArrayList<Top10> getTopTenlist() {
        return companyDao.getTopTen();
    }

    /**
     * Gets the list of the share changes within a given a time
     *
     * @param userId User identifier
     * @return a list of shares changes
     */
    public ArrayList<ShareChange> getSharesChange(int userId) {
        sharesChange = shareDao.getSharesChange(userId);
        return sharesChange;
    }

    /**
     * Gets all the company details from a user
     *
     * @param userId    User identifier
     * @return a list of a company detail
     */
    public ArrayList<ArrayList<CompanyDetail>> getCompanyDetails(int userId) {
        companyDetails = companyDao.getCompanyDetails(userId);
        return companyDetails;
    }

    /**
     * Gets all the sold shares from a user
     *
     * @param userId    User identifier
     * @return a list of sold shares
     */
    public ArrayList<ArrayList<ShareSell>> getSharesSell(int userId) {
        return shareDao.getSharesSell(userId);
    }
}

