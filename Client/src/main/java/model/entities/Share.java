package main.java.model.entities;

public class Share {

    private float price;

    public Share(float price){

        this.price = price;
    }


    public float calculateTotalValue (){

    }

    /**
     * @return
     * Getter
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price
     * Setter
     */
    public void setPrice(float price) {
        this.price = price;
    }
}
