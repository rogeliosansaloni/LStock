package model.entities;

public class ShareChange {

    private int shareId;
    private String companyName;
    private float actionValue;
    private float myActions;
    private float profitLoss;

    public ShareChange() {

    }

    public ShareChange(int shareId, String companyName, float actionValue, float myActions, float profitLoss) {
        this.shareId = shareId;
        this.companyName = companyName;
        this.actionValue = actionValue ;
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

    public float getActionValue() {
        return actionValue;
    }

    public void setActionValue(float actionValue) {
        this.actionValue = actionValue;
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
