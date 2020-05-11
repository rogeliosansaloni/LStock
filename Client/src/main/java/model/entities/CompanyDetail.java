package model.entities;


public class CompanyDetail {

    private int numShares;
    private int companyId;
    private String companyName;
    private int shareIdOpen;
    private float valueOpen;
    private int shareIdClose;
    private float valueClose;
    private int minutesBefore;

    public CompanyDetail() {
    }

    /**
     * It will create a companyChange
     *
     * @param companyId      company id
     * @param companyName      company name
     * @param shareIdOpen      shareId of the open value
     * @param valueOpen     value of the share at the beginning of that minute
     * @param shareIdClose      shareId of the close value
     * @param valueClose    value of the share at the end of that minute
     * @param minutesBefore      minutes before tha last change in the value of the share
     */
    public CompanyDetail(int numShares, int companyId, String companyName, int shareIdOpen, float valueOpen, int shareIdClose, float valueClose, int minutesBefore) {
        this.shareIdOpen = shareIdOpen;
        this.shareIdClose = shareIdClose;
        this.numShares = numShares;
        this.companyId = companyId;
        this.companyName = companyName;
        this.valueOpen = valueOpen;
        this.valueClose = valueClose;
        this.minutesBefore = minutesBefore;
    }

    public int getNumShares() {
        return numShares;
    }

    public void setNumShares(int numShares) {
        this.numShares = numShares;
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

    public int getShareIdOpen() {
        return shareIdOpen;
    }

    public void setShareIdOpen(int shareIdOpen) {
        this.shareIdOpen = shareIdOpen;
    }

    public float getValueOpen() {
        return valueOpen;
    }

    public void setValueOpen(float valueOpen) {
        this.valueOpen = valueOpen;
    }

    public int getShareIdClose() {
        return shareIdClose;
    }

    public void setShareIdClose(int shareIdClose) {
        this.shareIdClose = shareIdClose;
    }

    public float getValueClose() {
        return valueClose;
    }

    public void setValueClose(float valueClose) {
        this.valueClose = valueClose;
    }

    public int getMinutesBefore() {
        return minutesBefore;
    }

    public void setMinutesBefore(int minutesBefore) {
        this.minutesBefore = minutesBefore;
    }
}


