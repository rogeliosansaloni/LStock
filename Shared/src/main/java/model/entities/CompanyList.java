package model.entities;

public class CompanyList extends TunnelObject {
    private int[] companyId;
    private String[] companyName;
    private float[] companyValue;
    private int[] companyShares;

    public CompanyList(int numCompanies) {
        this.companyId = new int[numCompanies];
        this.companyName = new String[numCompanies];
        this.companyValue = new float[numCompanies];
        this.companyShares = new int[numCompanies];
    }

    public int[] getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int[] companyId) {
        this.companyId = companyId;
    }

    public String[] getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String[] companyName) {
        this.companyName = companyName;
    }

    public float[] getCompanyValue() {
        return companyValue;
    }

    public void setCompanyValue(float[] companyValue) {
        this.companyValue = companyValue;
    }

    public int[] getCompanyShares() {
        return companyShares;
    }

    public void setCompanyShares(int[] companyShares) {
        this.companyShares = companyShares;
    }
}
