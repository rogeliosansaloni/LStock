package model.entities;

/**
 * Represents a share
 */
public class Share {
    private User user;
    private Company company;
    private int idShare;
    private float price;

    public Share(User user, Company company, float price) {
        this.user = user;
        this.company = company;
        this.price = price;
    }

    public Share() {

    }

    public float calculteTotalValue() {
        return 1;
    }

    /**
     * Getters
     */
    public int getIdShare() {
        return idShare;
    }

    public float getPrice() {
        return price;
    }

    public User getUser() {
        return user;
    }

    public Company getCompany() {
        return company;
    }

    /**
     * Setters
     */
    public void setIdShare(int idShare) {
        this.idShare = idShare;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

