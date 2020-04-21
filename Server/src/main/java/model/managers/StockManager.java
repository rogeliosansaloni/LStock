package model.managers;

import database.CompanyDao;
import database.UserDao;
import model.entities.AuthenticationInfo;
import model.entities.Company;
import model.entities.User;
import network.DBConnector;
import utils.UserMapperImpl;

import java.util.ArrayList;

public class StockManager {
    private DBConnector connector;
    private Company company;
    private UserDao userDao;
    private CompanyDao companyDao;

    public StockManager() {
        connector = new DBConnector();
        userDao = new UserDao(connector);
        connector.connect();
    }

    public StockManager(Company company, UserDao userDao, CompanyDao companyDao) {
        this.company = company;
        this.userDao = userDao;
        this.companyDao = companyDao;
    }

    /**
     * Function that registers a user if the conditions are met
     * @param user the user
     * @return Authentification with the information we need to send for the client
     */
    public AuthenticationInfo registerUser(User user) {
        UserMapperImpl mapper = new UserMapperImpl();
        User usr = new User(user.getNickname(), user.getEmail(), user.getPassword());
        String response = userDao.createUser(usr);
        AuthenticationInfo info = mapper.userToAuthenticationInfo(usr);
        if (!response.equals("Register Success")) {
            info.setValidated(false);
        }
        else {
            info.setValidated(true);
        }
        info.setAction("register");
        info.setResponseType(response);
        return info;
    }

    public AuthenticationInfo validateUser(User user) {
        return null;
    }

    public ArrayList<Company> listAllCompanies() {
        return null;
    }
}

