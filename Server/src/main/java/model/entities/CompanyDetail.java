package model.entities;


public class CompanyDetail {

    private int numUserShares;
    private int companyId;
    private String companyName;
    private int shareIdOpen;
    private float valueOpen;
    private int shareIdClose;
    private float valueClose;
    private float maxValue;
    private float minValue;
    private int minutesBefore;

    /**
     * Empty constructor for CompanyDetail
     */
    public CompanyDetail() {
    }

    /**
     * Constructor of CompanyDetail
     *
     * @param numUserShares Total number of shares
     * @param companyId     Company identifier
     * @param companyName   Company name
     * @param shareIdOpen   Share identifier
     * @param valueOpen     Company starting value
     * @param shareIdClose  Company share end identifier
     * @param valueClose    Company ending value
     * @param maxValue      Company maximum value
     * @param minValue      Company minimum value
     * @param minutesBefore Minutes
     */
    public CompanyDetail(int numUserShares, int companyId, String companyName, int shareIdOpen, float valueOpen, int shareIdClose, float valueClose, float maxValue, float minValue, int minutesBefore) {
        this.shareIdOpen = shareIdOpen;
        this.shareIdClose = shareIdClose;
        this.numUserShares = numUserShares;
        this.companyId = companyId;
        this.companyName = companyName;
        this.valueOpen = valueOpen;
        this.valueClose = valueClose;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.minutesBefore = minutesBefore;
    }

    /**
     * Company detail constructor
     *
     * @param numUserShares Total number of shares
     * @param companyId     Company identifier
     * @param companyName   Company name
     * @param minutesBefore Minutes
     * @param valueClose    Closest value
     */
    public CompanyDetail(int numUserShares, int companyId, String companyName, int minutesBefore, float valueClose) {
        this.numUserShares = numUserShares;
        this.companyId = companyId;
        this.companyName = companyName;
        this.minutesBefore = minutesBefore;
        this.shareIdOpen = -1;
        this.shareIdClose = -1;
        this.valueOpen = -1;
        this.valueClose = valueClose;
        this.maxValue = -1;
        this.minValue = -1;
    }

    /**
     * Getters and setters
     */
    public int getNumUserShares() {
        return numUserShares;
    }

    public void setNumUserShares(int numUserShares) {
        this.numUserShares = numUserShares;
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

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public int getMinutesBefore() {
        return minutesBefore;
    }

    public void setMinutesBefore(int minutesBefore) {
        this.minutesBefore = minutesBefore;
    }
}




