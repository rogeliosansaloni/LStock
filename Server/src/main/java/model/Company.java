package model;

import java.util.ArrayList;

public class Company {
    private int companyId;
    private String name;
    private float value;
    private int shares;
    private User user;
    private ArrayList<Bot> bot;

    public Company(int companyId, String name, float value, int shares, User user, ArrayList<Bot> bot) {
        this.companyId = companyId;
        this.name = name;
        this.value = value;
        this.shares = shares;
        this.user = user;
        this.bot = bot;
    }

    public void  listBots(){}

    /**
     * Getters
     * **/
    public int getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }

    public int getShares() {
        return shares;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Bot> getBot() {
        return bot;
    }
}
