package model.entities;

/**
 * Represents a Company in the system
 */
public class Company {
    private int companyId;
    private String name;
    private float value;
    private int shares;
    private User user;

    /**
     * It will create a company
     *
     * @param companyId identification
     * @param name      company name
     * @param value     current company price
     * @param shares    number of shares available to the user
     * @param user      user who has shares in that company
     */
    public Company(int companyId, String name, float value, int shares, User user) {
        this.companyId = companyId;
        this.name = name;
        this.value = value;
        this.shares = shares;
        this.user = user;
    }

    /**
     * It will create a company
     *
     * @param companyId identification
     * @param name      company name
     * @param value     current company price
     * @param shares    number of shares available to the user
     */
    public Company(int companyId, String name, float value, int shares) {
        this.companyId = companyId;
        this.name = name;
        this.value = value;
        this.shares = shares;
    }

    /**
     * It will create an empty company
     */
    public Company() {

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
}
