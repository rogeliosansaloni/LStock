package model.entities;

public class StockManager {
    private User user;
    private Company company;

    public StockManager(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
