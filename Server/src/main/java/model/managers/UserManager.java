package model.managers;

import database.DBConnector;
import database.UserDao;
import model.entities.User;

import java.util.ArrayList;

public class UserManager {
    private DBConnector connector;
    private UserDao userDao;

    public UserManager(){
        connector = new DBConnector();
        userDao = new UserDao(connector);
        connector.connect();
    }

    public String[][] getUserList(){
        return userDao.getAllUserList();
    }

    public ArrayList<User> getUsers(){
        return userDao.getAllUsers();
    }
}
