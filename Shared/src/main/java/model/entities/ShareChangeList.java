package model.entities;

public class ShareChangeList extends TunnelObject {

    private int userId;
    private int[] companyId;
    private int[] shareId;
    private String[] companyName;
    private float[] shareOriginalValue;
    private float[] shareCurrentValue;
    private int[] sharesQuantity;
    private float[] profitLoss;

    public ShareChangeList () {
    }

    public ShareChangeList(int numShares) {
        this.companyId = new int[numShares];
        this.shareId = new int[numShares];
        this.companyName = new String[numShares];
        this.shareOriginalValue = new float[numShares];
        this.shareCurrentValue = new float[numShares];
        this.sharesQuantity = new int[numShares];
        this.profitLoss = new float[numShares];
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int[] getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int i, int companyId) {
        this.companyId[i] = companyId;
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

    public float[] getShareOriginalValue() {
        return shareOriginalValue;
    }

    public void setShareOriginalValue(int i, float shareValue) {
        this.shareOriginalValue[i] = shareValue;
    }

    public float[] getShareCurrentValue() {
        return shareCurrentValue;
    }

    public void setShareCurrentValue(int i, float shareValue) {
        this.shareCurrentValue[i] = shareValue;
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
