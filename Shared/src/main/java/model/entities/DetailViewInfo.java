package model.entities;

/**
 * This class has all the necessary information to show in the CompanyDetailView
 */
public class DetailViewInfo extends TunnelObject {

    private CompanyDetailList companyDetailList;

    private ShareSellList shareSellList;

    public DetailViewInfo(CompanyDetailList companyDetailList, ShareSellList shareSellList) {
        this.companyDetailList = companyDetailList;
        this.shareSellList = shareSellList;
    }

    public CompanyDetailList getCompanyDetailList() {
        return companyDetailList;
    }

    public void setCompanyDetailList(CompanyDetailList companyDetailList) {
        this.companyDetailList = companyDetailList;
    }

    public ShareSellList getShareSellList() {
        return shareSellList;
    }

    public void setShareSellList(ShareSellList shareSellList) {
        this.shareSellList = shareSellList;
    }
}

