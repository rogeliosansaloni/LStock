package model.entities;

public class Top10 {
    private int company_id;
    private String name;
    private float price;
    private  int share_quantity;

    public Top10(int company_id, String name, float price, int share_quantity) {
        this.company_id = company_id;
        this.name = name;
        this.price = price;
        this.share_quantity = share_quantity;
    }

    public Top10(String name, float price){
        this.name = name;
        this.price = price;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getShare_quantity() {
        return share_quantity;
    }

    public void setShare_quantity(int share_quantity) {
        this.share_quantity = share_quantity;
    }
}
