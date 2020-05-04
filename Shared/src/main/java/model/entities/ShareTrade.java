package model.entities;

/**
 * Represents the DTO that contains the action to do with company shares
 */
public class ShareTrade extends TunnelObject {
    private int companyId;
    private int shareIf;
    private float sharePrice;
    private String actionToDo;

    /**
     * @param companyId id of the company associated with the share
     * @param sharePrice price of the share
     * @param actionToDo action to do with the share: buy or sell
     */
    public ShareTrade(int companyId, float sharePrice, String actionToDo) {
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

    public int getShareIf() {
        return shareIf;
    }

    public void setShareIf(int shareIf) {
        this.shareIf = shareIf;
    }
}
