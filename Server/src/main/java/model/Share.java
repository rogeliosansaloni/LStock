package model;

public class Share {
    private int idShare;
    private float price;

    public Share(float price) {
        this.price = price;
    }

    public float calculteTotalValue(){
        return 1;
    }

    /**
     * Getters
     * */
    public int getIdShare() {
        return idShare;
    }

    public float getPrice() {
        return price;
    }
}

