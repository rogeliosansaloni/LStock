package model.entities;

public class CompanyDetailList extends TunnelObject {

    private int numShares;
    private int companyId;
    private String companyName;
    private int[] shareIdOpen;
    private float[] valueOpen;
    private int[] shareIdClose;
    private float[] valueClose;
    private int[] minutesBefore;

    public CompanyDetailList(int numCompanies) {

        this.shareIdOpen = new int[numCompanies];
        this.valueOpen = new float[numCompanies];
        this.shareIdClose = new int[numCompanies];
        this.valueClose = new float[numCompanies];
        this.minutesBefore = new int[numCompanies];
    }

    public CompanyDetailList() {
    }

    public int getNumShares() {
        return numShares;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int[] getShareIdOpen() {
        return shareIdOpen;
    }

    public float[] getValueOpen() {
        return valueOpen;
    }

    public int[] getShareIdClose() {
        return shareIdClose;
    }

    public float[] getValueClose() {
        return valueClose;
    }

    public int[] getMinutesBefore() {
        return minutesBefore;
    }

    public void setNumShares(int numShares) {
        this.numShares = numShares;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setShareIdOpen(int i, int shareIdOpen) {
        this.shareIdOpen[i] = shareIdOpen;
    }

    public void setValueOpen(int i, float valueOpen) {
        this.valueOpen[i] = valueOpen;
    }

    public void setShareIdClose(int i, int shareIdClose) {
        this.shareIdClose[i] = shareIdClose;
    }

    public void setValueClose(int i, float valueClose) {
        this.valueClose[i] = valueClose;
    }

    public void setMinutesBefore(int i, int minutesBefore) {
        this.minutesBefore[i] = minutesBefore;
    }
}
