package model.entities;

import java.util.ArrayList;

public class User {
    private int userId;
    private String nickname;
    private String email;
    private String password;
    private float stockValue;
    private String description;
    private float totalBalance;
    private ArrayList<Company> company;
    private ArrayList<Share> shares;

    /**
     * It will create a user
     *
     * @param userId  identification
     * @param nickname user nickname
     * @param email user email
     * @param password  user password
     * @param stockValue stock value of the user
     * @param description additional user information
     * @param totalBalance user available balance
     * @param company companies the user participates
     */

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

    /**
     * Creates a user with the specified information
     *
     * @param nickname user nickname
     * @param email user email
     * @param password  user password
     */

    public User (String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

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

    public ArrayList<Share> getShares() {
        return shares;
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

    public void setShares(ArrayList<Share> shares) {
        this.shares = shares;
    }
}
