package model.entities;

/**
 * The information of the
 */
public class ShareChange {

    private int userId;
    private int companyId;
    private int shareId;
    private String companyName;
    private float shareOriginalValue;
    private float shareCurrentValue;
    private int sharesQuantity;
    private float profitLoss;

    public ShareChange() {

    }
    /**
     * It will create a companyChange
     *
     * @param userId user id
     * @param companyId company id
     * @param shareId      share id
     * @param companyName     company name
     * @param shareOriginalValue     the price of the share when the user bought it
     * @param shareCurrentValue the current price of the share
     * @param sharesQuantity     the quantity of shares the user has
     * @param profitLoss     (the price of the share when the user bought it - its current price) * sharesQuantity
     */
    public ShareChange(int userId, int companyId, int shareId, String companyName, float shareOriginalValue, float shareCurrentValue, int sharesQuantity, float profitLoss) {
        this.userId = userId;
        this.companyId = companyId;
        this.shareId = shareId;
        this.companyName = companyName;
        this.shareOriginalValue = shareOriginalValue;
        this.shareCurrentValue = shareCurrentValue;
        this.sharesQuantity = sharesQuantity;
        this.profitLoss = profitLoss;
    }

    /**
     * It will create a companyChange
     *
     * @param companyId company id
     * @param companyName     company name
     * @param shareCurrentValue the current price of the share
     * @param sharesQuantity     the quantity of shares the user has
     */
    public ShareChange(int companyId, String companyName, float shareCurrentValue, int sharesQuantity){
        this.companyId = companyId;
        this.companyName = companyName;
        this.shareCurrentValue = shareCurrentValue;
        this.sharesQuantity = sharesQuantity;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
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

    public float getShareOriginalValue() {
        return shareOriginalValue;
    }

    public void setShareOriginalValue(float shareOriginalValue) {
        this.shareOriginalValue = shareOriginalValue;
    }

    public float getShareCurrentValue() {
        return shareCurrentValue;
    }

    public void setShareCurrentValue(float shareCurrentValue) {
        this.shareCurrentValue = shareCurrentValue;
    }

    public int getSharesQuantity() {
        return sharesQuantity;
    }

    public void setSharesQuantity(int sharesQuantity) {
        this.sharesQuantity = sharesQuantity;
    }

    public float getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(float profitLoss) {
        this.profitLoss = profitLoss;
    }

}