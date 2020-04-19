package model.managers;

import database.CompanyDao;
import database.UserDao;
import model.entities.AuthenticationInfo;
import model.entities.Company;
import model.entities.User;
import network.DBConnector;

import java.util.ArrayList;

public class StockManager {
    private Company company;
    private UserDao userDao;
    private CompanyDao companyDao;

    public  StockManager () {

    }
    public StockManager(Company company, UserDao userDao, CompanyDao companyDao) {
        this.company = company;
        this.userDao = userDao;
        this.companyDao = companyDao;
    }

    public AuthenticationInfo registerUser(User user){
        User usr = new User(user.getNickname(), user.getEmail(), user.getPassword());
        userDao.createUser(usr);
        return null;
    }
    public AuthenticationInfo validateUser(User user){
        return null;
    }
    public ArrayList<Company> listAllCompanies(){
        return  null;
    }
}

