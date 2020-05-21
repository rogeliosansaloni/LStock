package model.managers;

import database.CompanyDao;
import database.DBConnector;
import database.ShareDao;
import database.UserDao;
import model.entities.User;

import java.util.ArrayList;

public class UserManager {
    private DBConnector connector;
    private UserDao userDao;
    private ShareDao shareDao;
    private CompanyDao companyDao;

    public UserManager(){
        connector = new DBConnector();
        userDao = new UserDao(connector);
        shareDao = new ShareDao(connector);
        companyDao = new CompanyDao(connector);
        connector.connect();
    }

    /**
     * It will get all the users registered in LStock
     *
     * @return Registered users
     */
    public String[][] getUserList(){
        return userDao.getAllUserList();
    }

    public ArrayList<User> getUsers(){
        return userDao.getAllUsers();
    }

    /**
     * It will return all the shares from the selected user
     *
     * @return User shares list
     */
    public String[][] getUserShares(String name){
        return userDao.getUserShares(name);
    }
}
