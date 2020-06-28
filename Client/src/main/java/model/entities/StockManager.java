package model.entities;

import java.util.ArrayList;

/**
 * Manager for the main information of stocks from the server
 */
public class StockManager {
    private User user;
    private ArrayList<CompanyDetail> companyDetails;
    private ArrayList<ArrayList<CompanyDetail>> companyDetailsList;
    private ArrayList<CompanyChange> companiesChange;
    private ArrayList<ShareSell> sharesSell;
    private ArrayList<ArrayList<ShareSell>> sharesSellList;
    private ArrayList<ShareChange> sharesChange;
    private int currentCompanyId;

    /**
     * Creates the manager
     * @param user user
     */
    public StockManager(User user) {
        this.user = user;
        companyDetailsList = new ArrayList<ArrayList<CompanyDetail>>();
        sharesSellList = new ArrayList<ArrayList<ShareSell>>();
        this.currentCompanyId = 1;
    }

    /**
     * Gets the ids of all shares that the user has in the CompanyDetailView
     */
    public int[] getSharesSellSharesId() {
        int[] sharesId = new int[sharesSell.size()];
        for (int i = 0; i < sharesSell.size(); i++) {
            sharesId[i] = sharesSell.get(i).getShareId();
        }
        return sharesId;
    }

    /**
     * Gets the values of all shares that the user has in the CompanyDetailView
     */
    public float[] getSharesSellSharesValue() {
        float[] sharesValue = new float[sharesSell.size()];
        for (int i = 0; i < sharesSell.size(); i++) {
            sharesValue[i] = sharesSell.get(i).getShareValue();
        }
        return sharesValue;
    }


    /**
     * Gets the maximum value of all the prices that are in the Candlestick Chart of CompanyDetailView.
     */
    public float getMaxDetailShareValue() {
        float maxValue = companyDetails.get(0).getMaxValue();
        for (int i = 1; i < companyDetails.size(); i++) {
            if (companyDetails.get(i).getMaxValue() > maxValue) {
                maxValue = companyDetails.get(i).getMaxValue();
            }
        }
        return maxValue;
    }

    /**
     * Gets the information of the shares that are in the Candlestick Chart of CompanyDetailView.
     */

    public ShareChange getShareChangeInfo(int shareId) {
        for (int i = 0; i < sharesChange.size(); i++) {
            if (sharesChange.get(i).getShareId() == shareId) {
                return sharesChange.get(i);
            }
        }
        return null;
    }

    /**
     * Updates the information of the user.
     */

    public void updateUserInfo(User user) {
        this.user.setNickname(user.getNickname());
        this.user.setEmail(user.getEmail());
        this.user.setDescription(user.getDescription());
    }

    /**
     * Checks if the user has enough value to buy the shares in the CompanyDetailView.
     */
    public float checkUserBalance(int quantityShares) {
        float totalPurchase = quantityShares * getCurrentShareValue();
        float userBalance = user.getTotalBalance() - totalPurchase;
        return userBalance;
    }

    /**
     * Checks if the has enough shares of each type to sell them
     */
    public float checkNumUserShares(int[] quantityShares) {
        float benefitSale = 0;
        for (int i = 0; i < quantityShares.length; i++) {
            if (this.sharesSell.get(i).getShareQuantity() < quantityShares[i]) {
                return -1;
            }
            benefitSale += quantityShares[i] * getCurrentShareValue();
        }
        return user.getTotalBalance() + benefitSale;
    }

    /**
     * Getters
     **/

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

    public ArrayList<ShareChange> getSharesChange() {
        return sharesChange;
    }

    public float getCurrentShareValue() {
        return companyDetails.get(0).getValueClose();
    }

    public User getUser() {
        return user;
    }

    public ArrayList<CompanyDetail> getCompanyDetails() {
        return companyDetails;
    }

    public ArrayList<ShareSell> getSharesSell() {
        return sharesSell;
    }

    public ArrayList<ArrayList<ShareSell>> getSharesSellList() {
        return sharesSellList;
    }

    public int getCurrentCompanyId() {
        return currentCompanyId;
    }

    public ArrayList<ArrayList<CompanyDetail>> getCompanyDetailsList() {
        return companyDetailsList;
    }

    /**
     * Setters
     **/

    public void setCompanyDetails(ArrayList<CompanyDetail> companyDetails) {
        this.companyDetails = companyDetails;
    }

    public void setSharesSell(ArrayList<ShareSell> sharesSell) {
        this.sharesSell = sharesSell;
    }

    public void setCompaniesChange(ArrayList<CompanyChange> companiesChange) {
        this.companiesChange = companiesChange;
    }

    public void setSharesChange(ArrayList<ShareChange> sharesChange) {
        this.sharesChange = sharesChange;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int setCurrentCompanyId(int id) {
        return currentCompanyId;
    }

    public void setCompanyDetailsList(ArrayList<ArrayList<CompanyDetail>> companyDetailsList) {
        this.companyDetailsList = companyDetailsList;
        this.companyDetails = this.companyDetailsList.get(this.currentCompanyId-1);
    }

    public void setSharesSellList(ArrayList<ArrayList<ShareSell>> sharesSellList) {
        this.sharesSellList = sharesSellList;
        this.sharesSell =  this.sharesSellList.get(this.currentCompanyId-1);
    }
}
