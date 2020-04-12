package model;

import java.util.ArrayList;

public class Company {
    private int companyId;
    private String name;
    private float value;
    private int shares;
    private User user;
    private ArrayList<Bot> bot;

    public Company(int companyId, String name, float value, int shares) {
        this.companyId = companyId;
        this.name = name;
        this.value = value;
        this.shares = shares;
    }

    public void  listBots(){}
}
