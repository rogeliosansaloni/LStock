package model.entities;

public class ShareChangeList extends TunnelObject {
    private String[] companyName;
    private float[] companyActionValue;
    private float[] companyMyActions;
    private float[] companyProfitLoss;

    public ShareChangeList () {

    }

    public ShareChangeList(int numCompanies) {
        this.companyName = new String[numCompanies];
        this.companyActionValue = new float[numCompanies];
        this.companyMyActions = new float[numCompanies];
        this.companyProfitLoss = new float[numCompanies];
    }

    public String[] getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String[] companyName) {
        this.companyName = companyName;
    }

    public float[] getCompanyActionValue() {
        return companyActionValue;
    }

    public void setCompanyActionValue(float[] companyActionValue) {
        this.companyActionValue = companyActionValue;
    }

    public float[] getCompanyMyActions() {
        return companyMyActions;
    }

    public void setCompanyMyActions(float[] companyMyActions) {
        this.companyMyActions = companyMyActions;
    }

    public float[] getCompanyProfitLoss() {
        return companyProfitLoss;
    }

    public void setCompanyProfitLoss(float[] companyProfitLoss) {
        this.companyProfitLoss = companyProfitLoss;
    }
}
