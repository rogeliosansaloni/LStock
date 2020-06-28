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

    public CompanyList() {

    }

    public int[] getCompanyId() {
        return companyId;
    }

    public String[] getCompanyName() {
        return companyName;
    }

    public float[] getCompanyValue() {
        return companyValue;
    }

    public int[] getCompanyShares() {
        return companyShares;
    }

    public void setCompanyId(int i, int companyId) {
        this.companyId[i] = companyId;
    }

    public void setCompanyName(int i, String companyName) {
        this.companyName[i] = companyName;
    }

    public void setCompanyValue(int i, float companyValue) {
        this.companyValue[i] = companyValue;
    }

    public void setCompanyShares(int i, int companyShares) {
        this.companyShares[i] = companyShares;
    }
}
