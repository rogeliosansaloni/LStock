package model.entities;


public class ShareSell {

    private int userId;
    private int companyId;
    private int shareId;
    private String companyName;
    private float shareValue;
    private int shareQuantity;

    public ShareSell() {
    }


    public ShareSell(int userId, int companyId, int shareId, String companyName, float shareValue, int shareQuantity) {
        this.userId = userId;
        this.companyId = companyId;
        this.shareId = shareId;
        this.companyName = companyName;
        this.shareValue = shareValue;
        this.shareQuantity = shareQuantity;
    }


    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
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

    public int getShareQuantity() {
        return shareQuantity;
    }

    public void setShareQuantity(int shareQuantity) {
        this.shareQuantity = shareQuantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }
}



