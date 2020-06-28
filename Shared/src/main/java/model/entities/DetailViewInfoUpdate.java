package model.entities;

import java.util.ArrayList;

/**
 * This class has all the necessary information to show in the CompanyDetailView
 */
public class DetailViewInfoUpdate extends TunnelObject {

    private ArrayList<CompanyDetailList> companyDetailList;

    private ArrayList<ShareSellList> shareSellList;

    public DetailViewInfoUpdate(ArrayList<CompanyDetailList> companyDetailList, ArrayList<ShareSellList> shareSellList) {
        this.companyDetailList = companyDetailList;
        this.shareSellList = shareSellList;
    }

    public ArrayList<CompanyDetailList> getCompanyDetailList() {
        return companyDetailList;
    }

    public void setCompanyDetailList(ArrayList<CompanyDetailList> companyDetailList) {
        this.companyDetailList = companyDetailList;
    }

    public ArrayList<ShareSellList> getShareSellList() {
        return shareSellList;
    }

    public void setShareSellList(ArrayList<ShareSellList> shareSellList) {
        this.shareSellList = shareSellList;
    }
}
