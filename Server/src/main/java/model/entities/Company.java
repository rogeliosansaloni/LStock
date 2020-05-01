package model.entities;

import java.util.ArrayList;

/**
 * Represents a company
 */
public class Company {
    private int companyId;
    private String name;
    private float value;
    private int shares;
    private User user;
    private ArrayList<Bot> bots;

    /**
     * It will create a company
     *
     * @param companyId  identification
     * @param name company name
     * @param value current company price
     * @param shares  number of shares available to the user
     * @param user user who has shares in that company
     */

    public Company(int companyId, String name, float value, int shares, User user, ArrayList<Bot> bot) {
        this.companyId = companyId;
        this.name = name;
        this.value = value;
        this.shares = shares;
        this.user = user;
        this.bots = bot;
    }

    public Company(String name, float value, int shares) {
        this.name = name;
        this.value = value;
        this.shares = shares;
        this.bots = new ArrayList<Bot>();
    }

    public Company() {
        this.bots = new ArrayList<Bot>();
    }

    public void listBots() {
    }

    public void addBot(Bot bot) {
        this.bots.add(bot);
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

    public int getShares() {
        return shares;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Bot> getBots() {
        return bots;
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

    public void setShares(int shares) {
        this.shares = shares;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBots(ArrayList<Bot> bots) {
        this.bots = bots;
    }
}
