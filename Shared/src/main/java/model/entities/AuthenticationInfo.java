package model.entities;

/**
 * Represents the DTO that contains the user infomation needed for authentication
 */
public class AuthenticationInfo extends TunnelObject {
    private int id;
    private String nickname;
    private String email;
    private String password;
    private float total_balance;
    private String action;
    private String responseType;
    private boolean validated;

    /**
     * Default constructor
     */
    public AuthenticationInfo() {
    }

    /**
     * Constructor for DTO
     * @param nickname user nickname
     * @param email user email
     * @param password user password
     * @param action action to be done: register or login
     */
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

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotal_balance() {
        return total_balance;
    }

    public void setTotal_balance(float total_balance) {
        this.total_balance = total_balance;
    }
}
