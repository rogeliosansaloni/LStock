package model.managers;

import database.CompanyDao;
import database.ShareDao;
import database.UserDao;
import model.entities.*;
import database.DBConnector;
import utils.ShareMapperImpl;
import utils.UserMapperImpl;

import java.util.ArrayList;

public class StockManager {
    private static final String BUY_ACTION = "BUY";
    private static final String SELL_ACTION = "SELL";
    private DBConnector connector;
    private ArrayList<Company> companies;
    private ArrayList<CompanyChange> companiesChange;
    private UserDao userDao;
    private CompanyDao companyDao;
    private ShareDao shareDao;
    private UserMapperImpl mapper;
    private ShareMapperImpl shareMapper;

    public StockManager() {
        connector = new DBConnector();
        userDao = new UserDao(connector);
        shareDao = new ShareDao(connector);
        companyDao = new CompanyDao(connector);
        mapper = new UserMapperImpl();
        shareMapper = new ShareMapperImpl();
        connector.connect();
    }

    public StockManager(UserDao userDao, CompanyDao companyDao) {
        this.userDao = userDao;
        this.companyDao = companyDao;
        this.companies = new ArrayList<Company>();
        this.companiesChange = new ArrayList<CompanyChange>();
    }

    /**
     * Function that registers a user if the conditions are met
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
        userDao.updateUserBalance(user);
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
     * Creates a new share between company and user.
     * @param user the user
     * @param company the company
     * @return ShareTrade with the new values of users total balance and company value
     */
    public ShareTrade createUserCompanyShare (User user, Company company) {
        //Creates the purchased share
        shareDao.insertPurchasedShare(user, company);
        //Updates de the user balance
        userDao.updateUserBalance(user, company);
        //Recalculates the new value of the company
        company.setValue(company.recalculateValue(BUY_ACTION, company.getValue()));
        //Updates the company new value
        companyDao.insertCompanyNewShare(company);
        ShareTrade info = shareMapper.userCompanyToShareTrade(user, company);
        info.setActionToDo(BUY_ACTION);
        return info;
    }
    public ArrayList<Company> getCompanies() {
        companies = companyDao.getAllCompanies();
        return companies;
    }

    public ArrayList<CompanyChange> getCompaniesChange() {
        companiesChange = companyDao.getCompaniesChange();
        return companiesChange;
    }
}

