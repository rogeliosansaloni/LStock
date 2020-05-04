package main.java.model.entities;

import model.entities.User;

/**
 * Represents a share bought by a User from a specific Company
 */
public class Share {

    private float price;
    private User user;
    private Company company;
    private int shareId;

    public Share (){

    }
    public Share(float price, User user, Company company, int shareId){
        this.price = price;
        this.user = user;
        this.company = company;
        this.shareId = shareId;
    }

    public float calculateTotalValue (){
        float value = 0;
        return value;
    }

    public float getPrice() {
        return price;
    }

    public int getShareId() {
        return shareId;
    }

    public Company getCompany() {
        return company;
    }

    public User getUser() {
        return user;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
