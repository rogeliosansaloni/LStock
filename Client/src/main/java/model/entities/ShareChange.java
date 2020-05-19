package model.entities;

public class ShareChange {

    private int shareId;
    private String companyName;
    private float shareValue;
    private float myActions;
    private float profitLoss;

    public ShareChange() {

    }

    public ShareChange(int shareId, String companyName, float shareValue, float myActions, float profitLoss) {
        this.shareId = shareId;
        this.companyName = companyName;
        this.shareValue = shareValue;
        this.myActions = myActions;
        this.profitLoss = profitLoss;
    }

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public float getShareValue() {
        return shareValue;
    }

    public void setShareValue(float shareValue) {
        this.shareValue = shareValue;
    }

    public float getMyActions() {
        return myActions;
    }

    public void setMyActions(float myActions) {
        this.myActions = myActions;
    }

    public float getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(float profitLoss) {
        this.profitLoss = profitLoss;
    }

}
