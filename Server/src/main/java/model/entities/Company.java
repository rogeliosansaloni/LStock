package model.entities;

import java.util.ArrayList;

/**
 * Represents a company
 */
public class Company {
    private int companyId;
    private String name;
    private float value;
    private int numShares;
    private User user;
    private ArrayList<Bot> bots;
    private ArrayList<Share> shares;

    /**
     * It will create a company
     *
     * @param companyId identification
     * @param name      company name
     * @param value     current company price
     * @param numShares number of shares available to the user
     * @param user      user who has shares in that company
     */

    public Company(int companyId, String name, float value, int numShares, User user, ArrayList<Bot> bot) {
        this.companyId = companyId;
        this.name = name;
        this.value = value;
        this.numShares = numShares;
        this.user = user;
        this.bots = bot;
    }

    public Company(String name, float value, int numShares) {
        this.name = name;
        this.value = value;
        this.numShares = numShares;
        this.bots = new ArrayList<Bot>();
    }

    public Company(String name, float value) {
        this.name = name;
        this.value = value;
        this.numShares = 0;
        this.bots = new ArrayList<Bot>();
        this.shares = new ArrayList<Share>();
    }

    public Company(int companyId, String name, float value, int numShares) {
        this.companyId = companyId;
        this.name = name;
        this.value = value;
        this.numShares = numShares;
        this.bots = new ArrayList<Bot>();
    }

    public Company() {
        this.bots = new ArrayList<Bot>();
    }

    public Company(int companyId) {
        this.companyId = companyId;
    }

    public void listBots() {
    }

    public void addBot(Bot bot) {
        this.bots.add(bot);
    }

    public void recalculateValue(String action) {
        if (action.equals("buy")) {
            this.value += (this.value * 0.01f);
        } else {
            if (action.equals("sell")) {
                this.value -= (this.value * 0.01f);
            }
        }
    }

    /**
     * Getters
     **/
    public int getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }

    public int getNumShares() {
        return numShares;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Bot> getBots() {
        return bots;
    }

    public ArrayList<Share> getShares() {
        return shares;
    }

    /**
     * Setters
     **/
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setNumShares(int numShares) {
        this.numShares = numShares;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBots(ArrayList<Bot> bots) {
        this.bots = bots;
    }

    public void setShares(ArrayList<Share> shares) {
        this.shares = shares;
    }
}
