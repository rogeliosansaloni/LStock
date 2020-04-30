package model.entities;

public class UserProfileInfo extends TunnelObject {
    private String nickname;
    private String email;
    private String description;
    private float totalBalance;

    public UserProfileInfo() {}

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
}
