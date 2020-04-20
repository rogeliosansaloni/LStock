package model;

import database.CompanyDao;
import database.UserDao;
import model.entities.AuthenticationInfo;
//import sun.net.www.protocol.http.AuthenticationInfo;

import java.util.ArrayList;

public class StockManager {
    private Company company;
    private UserDao userDao;
    private CompanyDao companyDao;

    public StockManager(){
    }

    public StockManager(Company company, UserDao userDao, CompanyDao companyDao) {
        this.company = company;
        this.userDao = userDao;
        this.companyDao = companyDao;
    }

    public AuthenticationInfo registerUser(User user){
        return null;
    }
    public AuthenticationInfo validateUser(User user){
        User usr = new User(user.getNickname(), user.getEmail(), user.getPassword());
        boolean usrValid = userDao.validateUser(usr);
        return null;
    }
    public ArrayList<Company> listAllCompanies(){
        return  null;
    }
}

