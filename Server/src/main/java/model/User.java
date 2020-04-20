package model;

import model.entities.TunnelObject;

import java.util.ArrayList;
import network.DedicatedServer;

public class User extends TunnelObject {
    protected int userId;
    protected String nickname;
    protected String email;
    protected String password;
    protected float stockValue;
    protected String description;
    protected float totalBalance;
    protected DedicatedServer dedicatedServer;
    protected ArrayList<Company> company;


    public User(int userId, String nickname, String email, String password, float stockValue, String description, float totalBalance, ArrayList<Company> company) {
        this.userId = userId;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.stockValue = stockValue;
        this.description = description;
        this.totalBalance = totalBalance;
        this.company = company;
    }

    public User (String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public User() {

    }


    public void lisTopCompanies(){}
    public void purchaseShared(){}
    public void updateBalance(){}
    public void loadBalance(){}


    /**
     * Getters
     * **/
    public int getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public float getStockValue() {
        return stockValue;
    }

    public String getDescription() {
        return description;
    }

    public float getTotalBalance() {
        return totalBalance;
    }

    public ArrayList<Company> getCompany() {
        return company;
    }

    /**
     * Setters
     * **/
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStockValue(float stockValue) {
        this.stockValue = stockValue;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTotalBalance(float totalBalance) {
        this.totalBalance = totalBalance;
    }

    public void setCompany(ArrayList<Company> company) {
        this.company = company;
    }
}

