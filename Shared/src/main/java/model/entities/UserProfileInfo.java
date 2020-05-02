package model.entities;

public class UserProfileInfo extends TunnelObject {
    private int userId;
    private String nickname;
    private String email;
    private String description;
    private float totalBalance;
    private String action;

    public UserProfileInfo() {}

    public UserProfileInfo(int userId, float totalBalance, String action) {
        this.userId = userId;
        this.totalBalance = totalBalance;
        this.action = action;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(float totalBalance) {
        this.totalBalance = totalBalance;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
