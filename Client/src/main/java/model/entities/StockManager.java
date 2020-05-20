package model.entities;

import java.util.ArrayList;

public class StockManager {
    private User user;
    private ArrayList<CompanyDetail> companyDetails;
    private ArrayList<CompanyChange> companiesChange;
    private ArrayList<ShareSell> sharesSell;

    public StockManager(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<CompanyDetail> getCompanyDetails() {
        return companyDetails;
    }

    public ArrayList<ShareSell> getSharesSell() {
        return sharesSell;
    }

    public int[] getSharesSellSharesId() {
        int[] sharesId = new int[sharesSell.size()];
        for(int i=0; i<sharesSell.size(); i++){
            System.out.println(sharesSell.get(i).getShareId());
            sharesId[i] = sharesSell.get(i).getShareId();
        }
        return sharesId;
    }

    public float[] getSharesSellSharesValue() {
        float[] sharesValue = new float[sharesSell.size()];
        for(int i=0; i<sharesSell.size(); i++){
            sharesValue[i] = sharesSell.get(i).getShareValue();
        }
        return sharesValue;
    }

    public float getCurrentShareValue() {
        return companyDetails.get(0).getValueClose();
    }

    public float getMaxDetailShareValue() {
        float maxValue = companyDetails.get(0).getMaxValue();
        for(int i=1; i<companyDetails.size(); i++){
            if(companyDetails.get(i).getMaxValue() > maxValue){
                maxValue = companyDetails.get(i).getMaxValue();
            }
        }
        return maxValue;
    }

    public float getMinShareValue() {
        return companyDetails.get(0).getValueClose();
    }

    public int getCurrentShareId() {
        return companyDetails.get(0).getShareIdClose();
    }

    public String getCompanyDetailName() {
        return companyDetails.get(0).getCompanyName();
    }

    public int getCompanyDetailId() {
        return companyDetails.get(0).getCompanyId();
    }

    public ArrayList<CompanyChange> getCompaniesChange() {
        return companiesChange;
    }

    public void setCompanyDetails(ArrayList<CompanyDetail> companyDetails) {
        this.companyDetails = companyDetails;
    }

    public void setSharesSell(ArrayList<ShareSell> sharesSell) {
        this.sharesSell = sharesSell;
    }

    public void setCompaniesChange(ArrayList<CompanyChange> companiesChange) {
        this.companiesChange = companiesChange;
    }

    public float checkUserBalance (int quantityShares){
        float totalPurchase = quantityShares * getCurrentShareValue();
        float userBalance = user.getTotalBalance() - totalPurchase;
        return userBalance;
    }

    public void updateUserBalance (float newBalance){
        this.user.setTotalBalance(newBalance);
    }

    public float checkNumUserShares (int[] quantityShares){
        float benefitSale = 0;
        for(int i=0; i<quantityShares.length; i++){
            if(this.sharesSell.get(i).getShareQuantity() < quantityShares[i]){
                return -1;
            }
            benefitSale += quantityShares[i] * getCurrentShareValue();
        }
        return user.getTotalBalance() + benefitSale;
    }
}
