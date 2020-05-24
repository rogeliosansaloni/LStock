package model.entities;

public class UserShares extends TunnelObject {
    private int userId;
    private int companyId;

    public UserShares(int userId, int companyId){
        this.userId = userId;
        this.companyId = companyId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCompanyId() {
        return companyId;
    }
}
