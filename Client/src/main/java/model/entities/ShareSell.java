package model.entities;


public class ShareSell {

    private int shareId;
    private float shareValue;
    private int shareQuantity;

    public ShareSell() {
    }

    public ShareSell(int shareId, float shareValue, int shareQuantity) {
        this.shareId = shareId;
        this.shareValue = shareValue;
        this.shareQuantity = shareQuantity;
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

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }
}

