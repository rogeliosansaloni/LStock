package model.entities;

public class StockManager {
    private User user;

    public StockManager(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
