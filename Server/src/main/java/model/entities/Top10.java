package model.entities;

/**
 * Top 10 companies class
 */
public class Top10 {
    private String name;
    private float price;

    /**
     * Constructor for a Top10 Company
     *
     * @param name      company name
     * @param price     current company share price
     */
    public Top10(String name, float price){
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the company name
     * @return the company name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the company name
     * @param name company name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the company price
     * @return company price
     */
    public float getPrice() {
        return price;
    }

}
