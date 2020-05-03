package model.entities;

/**
 * Represents a share bought by a User from a specific Company
 */
public class Share {
    private float price;

    public Share(float price) {
        this.price = price;
    }

    public float calculateTotalValue() {
        float value = 0;
        return value;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
