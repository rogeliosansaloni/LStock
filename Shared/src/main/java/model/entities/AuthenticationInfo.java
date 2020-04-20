package model.entities;

public class AuthenticationInfo extends TunnelObject {
    private String nickname;
    private String email;
    private String password;
    private String action;
    private boolean validated;

    public AuthenticationInfo() { }

    public AuthenticationInfo(String nickname, String email, String password, String action) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }
}
