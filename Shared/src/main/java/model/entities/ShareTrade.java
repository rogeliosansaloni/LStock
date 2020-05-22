package model.entities;

/**
 * Represents the DTO that contains the action to do with company shares
 */
public class ShareTrade extends TunnelObject {
    private int userId;
    private float totalBalance;
    private int companyId;
    private int[] shareId;
    private float[] sharePrice;
    private int[] numShares;
    private String actionToDo;

    public ShareTrade () { }

    /**
     * @param userId  id of the user
     * @param totalBalance user's balance
     * @param companyId  id of the company associated with the share
     * @param shareId  id of the share
     * @param sharePrice price of the share
     * @param numShares number of shares that the users wants to buy/sell
     * @param actionToDo action to do with the share: buy or sell
     */
    public ShareTrade(int userId, float totalBalance, int companyId, int[] shareId, float[] sharePrice, int[] numShares, String actionToDo) {
        this.userId = userId;
        this.totalBalance = totalBalance;
        this.companyId = companyId;
        this.shareId = shareId;
        this.sharePrice = sharePrice;
        this.numShares = numShares;
        this.actionToDo = actionToDo;
    }

    public ShareTrade(int userId, float totalBalance, int companyId, int shareId, float sharePrice, int numShares, String actionToDo) {
        this.userId = userId;
        this.totalBalance = totalBalance;
        this.companyId = companyId;
        this.shareId = new int[1];
        this.sharePrice = new float[1];
        this.numShares = new int[1];
        this.shareId[0] = shareId;
        this.sharePrice[0] = sharePrice;
        this.numShares[0] = numShares;
        this.actionToDo = actionToDo;
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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int[] getShareId() {
        return shareId;
    }

    public void setShareId(int[] shareId) {
        this.shareId = shareId;
    }

    public float[] getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(float[] sharePrice) {
        this.sharePrice = sharePrice;
    }

    public int[] getNumShares() {
        return numShares;
    }

    public void setNumShares(int[] numShares) {
        this.numShares = numShares;
    }

    public String getActionToDo() {
        return actionToDo;
    }

    public void setActionToDo(String actionToDo) {
        this.actionToDo = actionToDo;
    }

}
