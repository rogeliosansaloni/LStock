package model;

public class User {
    private int userId;
    private String nickname;
    private String email;
    private String password;
    private float stockValue;
    private String description;
    private float totalBalance;
    private DedicateServer dedicatedServer;
    private Company company;


    public User(int userId, String nickname, String email, String password, float stockValue, String description, float totalBalance) {
        this.userId = userId;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.stockValue = stockValue;
        this.description = description;
        this.totalBalance = totalBalance;
    }


    public void lisTopCompanies(){}
    public void purchaseShared(){}
    public void updateBalance(){}
    public void loadBalance(){}
}

