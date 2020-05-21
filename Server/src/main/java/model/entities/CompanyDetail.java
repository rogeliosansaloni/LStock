package model.entities;

public class CompanyDetail {
    private int companyId;
    private String name;
    private float value;
    private int shares;


    /**
     * It will create a Company Detail Class
     *
     * @param companyId Company ID
     * @param name Company Name
     * @param value Share Value
     * @param shares Number of shares
     */
    public CompanyDetail(int companyId, String name, float value, int shares) {
        this.companyId = companyId;
        this.name = name;
        this.value = value;
        this.shares = shares;
    }

    /**
     * Getters
     */
    public int getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }

    public int getShares() {
        return shares;
    }

    /**
     * Setters
     */
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }
}
