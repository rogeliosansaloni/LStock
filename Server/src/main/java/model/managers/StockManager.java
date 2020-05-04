package model.managers;

import database.CompanyDao;
import database.UserDao;
import model.entities.AuthenticationInfo;
import model.entities.Company;
import model.entities.User;
import model.entities.UserProfileInfo;
import database.DBConnector;
import utils.UserMapperImpl;

import java.util.ArrayList;

public class StockManager {
    private DBConnector connector;
    private ArrayList<Company> companies;
    private UserDao userDao;
    private CompanyDao companyDao;
    private UserMapperImpl mapper;

    public StockManager() {
        connector = new DBConnector();
        userDao = new UserDao(connector);
        mapper = new UserMapperImpl();
        connector.connect();
    }

    public StockManager(UserDao userDao, CompanyDao companyDao) {
        this.userDao = userDao;
        this.companyDao = companyDao;
        this.companies = new ArrayList<Company>();
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

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public void updateCompanyValue(int companyId, String action) {
        for (Company c : companies) {
            if (c.getCompanyId() == companyId) {
                c.recalculateValue(action);
            }
        }
    }
}

