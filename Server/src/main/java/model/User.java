package model;

import model.entities.TunnelObject;

import java.util.ArrayList;

public class User extends TunnelObject {
    protected int userId;
    protected String nickname;
    protected String email;
    protected String password;
    protected float stockValue;
    protected String description;
    protected float totalBalance;
    //protected DedicateServer dedicatedServer;
    protected ArrayList<Company> company;


    public User(int userId, String nickname, String email, String password, float stockValue, String description, float totalBalance) {
        this.userId = userId;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.stockValue = stockValue;
        this.description = description;
        this.totalBalance = totalBalance;
    }



    public void lisTopCompanies(){}
    public void purchaseShared(){}
    public void updateBalance(){}
    public void loadBalance(){}
}

