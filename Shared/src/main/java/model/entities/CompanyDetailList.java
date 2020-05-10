package model.entities;

public class CompanyDetailList extends TunnelObject {

    private int numShares;
    private int companyId;
    private String companyName;
    private float[] valueOpen;
    private float[] valueClose;
    private int[] minutesBefore;

    public CompanyDetailList(int numCompanies) {

        this.valueOpen = new float[numCompanies];
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

    public float[] getValueOpen() {
        return valueOpen;
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

    public void setValueOpen(int i, float valueOpen) {
        this.valueOpen[i] = valueOpen;
    }

    public void setValueClose(int i, float valueClose) {
        this.valueClose[i] = valueClose;
    }

    public void setMinutesBefore(int i, int minutesBefore) {
        this.minutesBefore[i] = minutesBefore;
    }
}
