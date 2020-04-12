package model;

public class Company {
    private int companyId;
    private String name;
    private float value;
    private int shares;
    private User user;
    private Bot bot;

    public Company(int companyId, String name, float value, int shares) {
        this.companyId = companyId;
        this.name = name;
        this.value = value;
        this.shares = shares;
    }

    public void  listBots(){}
}
