package model.entities;

public class CompanyDetailList extends TunnelObject {

    private int[] companyId;
    private String[] companyName;
    private float[] valueOpen;
    private float[] valueClose;
    private int[] minutesBefore;

    public CompanyDetailList(int numCompanies) {
        this.companyId = new int[numCompanies];
        this.companyName = new String[numCompanies];
        this.valueOpen = new float[numCompanies];
        this.valueClose = new float[numCompanies];
        this.minutesBefore = new int[numCompanies];
    }

    public CompanyDetailList() {
    }

    public int[] getCompanyId() {
        return companyId;
    }

    public String[] getCompanyName() {
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


    public void setCompanyId(int i, int companyId) {
        this.companyId[i] = companyId;
    }

    public void setCompanyName(int i, String companyName) {
        this.companyName[i] = companyName;
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
