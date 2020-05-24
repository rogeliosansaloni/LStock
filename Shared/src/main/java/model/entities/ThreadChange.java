package model.entities;

public class ThreadChange extends TunnelObject{
    private CompanyChangeList companyChangeList;
    private DetailViewInfo detailViewInfo;
    private ShareChangeList shareChangeList;

    public ThreadChange() {
        System.out.println("thread change invoked");
    }

    public void asdf(){
        System.out.println("asdf ThreadChange");
    }

    public ThreadChange(CompanyChangeList companyChangeList, DetailViewInfo detailViewInfo, ShareChangeList shareChangeList) {
        this.companyChangeList = companyChangeList;
        this.detailViewInfo = detailViewInfo;
        this.shareChangeList = shareChangeList;
    }

    public ShareChangeList getShareChangeList() {
        return shareChangeList;
    }

    public void setShareChangeList(ShareChangeList shareChangeList) {
        this.shareChangeList = shareChangeList;
    }

    public DetailViewInfo getDetailViewInfo() {
        return detailViewInfo;
    }

    public void setDetailViewInfo(DetailViewInfo detailViewInfo) {
        this.detailViewInfo = detailViewInfo;
    }

    public CompanyChangeList getCompanyChangeList() {
        return companyChangeList;
    }

    public void setCompanyChangeList(CompanyChangeList companyChangeList) {
        this.companyChangeList = companyChangeList;
    }
}
