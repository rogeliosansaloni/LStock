package model.entities;

import java.io.Serializable;

public class ShareSellList implements Serializable {

    private int[] shareId;
    private float[] shareValue;
    private int[] shareQuantity;

    public ShareSellList(int numShares) {
        this.shareId = new int[numShares];
        this.shareValue = new float[numShares];
        this.shareQuantity = new int[numShares];
    }

    public ShareSellList() {
    }


    public int[] getShareId() {
        return shareId;
    }

    public void setShareId(int i, int shareId) {
        this.shareId[i] = shareId;
    }

    public float[] getShareValue() {
        return shareValue;
    }

    public void setShareValue(int i, float shareValue) {
        this.shareValue[i] = shareValue;
    }

    public int[] getShareQuantity() {
        return shareQuantity;
    }

    public void setShareQuantity(int i, int shareQuantity) {
        this.shareQuantity[i] = shareQuantity;
    }

}
