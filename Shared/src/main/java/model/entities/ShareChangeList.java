package model.entities;

public class ShareChangeList extends TunnelObject {

    private int userId;
    private int[] shareId;
    private String[] companyName;
    private float[] shareValue;
    private float[] myActions;
    private float[] profitLoss;

    public ShareChangeList () {
    }

    public ShareChangeList(int numShares) {
        this.shareId = new int[numShares];
        this.companyName = new String[numShares];
        this.shareValue = new float[numShares];
        this.myActions = new float[numShares];
        this.profitLoss = new float[numShares];
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int[] getShareId() {
        return shareId;
    }

    public void setShareId(int i, int shareId) {
        this.shareId[i] = shareId;
    }

    public String[] getCompanyName() {
        return companyName;
    }

    public void setCompanyName(int i, String companyName) {
        this.companyName[i] = companyName;
    }

    public float[] getShareValue() {
        return shareValue;
    }

    public void setShareValueValue(int i, float shareValue) {
        this.shareValue[i] = shareValue;
    }

    public float[] getMyActions() {
        return myActions;
    }

    public void setMyActions(int i, float myActions) {
        this.myActions[i] = myActions;
    }

    public float[] getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(int i, float profitLoss) {
        this.profitLoss[i] = profitLoss;
    }

}
