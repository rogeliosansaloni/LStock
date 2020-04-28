package model.entities;

/**
 * Represents a share
 */
public class Share {
    private int idShare;
    private float price;

    /**
     * It will create a share
     *
     * @param price share value
     */

    public Share(float price) {
        this.price = price;
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

    /**
     * Setters
     */
    public void setIdShare(int idShare) {
        this.idShare = idShare;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

