package model.entities;

/**
 * Represents a share bought by a User from a specific Company
 */
public class Share {

    private float price;
    private User user;
    private Company company;
    private int shareId;

    public Share (){ }
    /**
     * @param price
     * @param user
     * @param company
     * @param shareId
     */
    public Share(float price, User user, Company company, int shareId){
        this.price = price;
        this.user = user;
        this.company = company;
        this.shareId = shareId;
    }

    /**
     * @return
     */
    public float calculateTotalValue (){
        float value = 0;
        return value;
    }

    /**
     * Getters
     **/

    public float getPrice() {
        return price;
    }

    public int getShareId() {
        return shareId;
    }

    public User getUser() {
        return user;
    }

    public Company getCompany() {
        return company;
    }

    /**
     * Setters
     **/

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
