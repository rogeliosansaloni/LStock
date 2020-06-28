package model.managers;

import database.DBConnector;
import database.UserDao;

/**
 * Represents the manager that controls the user operations
 */
public class UserManager {
    private UserDao userDao;

    /**
     * Constructor for the user manager
     */
    public UserManager() {
        DBConnector connector = new DBConnector();
        userDao = new UserDao(connector);
        connector.connect();
    }

    /**
     * It will get all the users registered in LStock
     *
     * @return Registered users
     */
    public String[][] getUserList() {
        return userDao.toUserList();
    }

    /**
     * It will return all the shares from the selected user
     *
     * @return User shares list
     */
    public String[][] getUserShares(String name) {
        return userDao.getUserShares(name);
    }
}
