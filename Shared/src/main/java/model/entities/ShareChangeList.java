package model.entities;

public class ShareChangeList extends TunnelObject {

    private int userId;
    private int[] shareId;
    private String[] companyName;
    private float[] shareValue;
    private int[] sharesQuantity;
    private float[] profitLoss;

    public ShareChangeList () {
    }

    public ShareChangeList(int numShares) {
        this.shareId = new int[numShares];
        this.companyName = new String[numShares];
        this.shareValue = new float[numShares];
        this.sharesQuantity = new int[numShares];
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

    public void setShareValue(int i, float shareValue) {
        this.shareValue[i] = shareValue;
    }

    public int[] getSharesQuantity() {
        return sharesQuantity;
    }

    public void setSharesQuantity(int i, int sharesQuantity) {
        this.sharesQuantity[i] = sharesQuantity;
    }

    public float[] getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(int i, float profitLoss) {
        this.profitLoss[i] = profitLoss;
    }

}
