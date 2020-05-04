package model.entities;

/**
 * Represents the DTO that contains the action to do with company shares
 */
public class ShareTrade extends TunnelObject {
    private int userId;
    private float totalBalance;
    private int companyId;
    private int shareId;
    private float sharePrice;
    private String actionToDo;

    public ShareTrade () { }

    /**
     * @param companyId  id of the company associated with the share
     * @param sharePrice price of the share
     * @param actionToDo action to do with the share: buy or sell
     */
    public ShareTrade(int userId, float totalBalance, int companyId, float sharePrice, String actionToDo) {
        this.userId = userId;
        this.totalBalance = totalBalance;
        this.companyId = companyId;
        this.sharePrice = sharePrice;
        this.actionToDo = actionToDo;
    }

    // Getters and setters
    public String getActionToDo() {
        return actionToDo;
    }

    public void setActionToDo(String actionToDo) {
        this.actionToDo = actionToDo;
    }

    public float getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(float sharePrice) {
        this.sharePrice = sharePrice;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(float totalBalance) {
        this.totalBalance = totalBalance;
    }
}
