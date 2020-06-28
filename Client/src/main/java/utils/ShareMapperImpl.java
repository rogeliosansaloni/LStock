package utils;

import model.entities.*;
import utils.mappers.ShareMapper;

import java.util.ArrayList;

/**
 * Implementation of ShareMapper
 */
public class ShareMapperImpl implements ShareMapper {

    /**
     * Converts SharesSellList DTO to a list of ShareSell
     * @param shareSellList SharesSellList DTO
     * @return a list of SharesSell
     */
    @Override
    public ArrayList<ArrayList<ShareSell>> converToSharesSellUpdate(ArrayList<ShareSellList> shareSellList) {
        ArrayList<ArrayList<ShareSell>> sharesSells = new ArrayList<ArrayList<ShareSell>>();
        for(int n=0; n< shareSellList.size(); n++){
            ArrayList<ShareSell> shares = new ArrayList<ShareSell>();
            int sharesLen = shareSellList.get(n).getShareQuantity().length;
            int[] shareId = shareSellList.get(n).getShareId();
            float[] sharesValue = shareSellList.get(n).getShareValue();
            int[] sharesQuantity = shareSellList.get(n).getShareQuantity();
            for (int i = 0; i < sharesLen; i++) {
                shares.add(new ShareSell(shareId[i], sharesValue[i], sharesQuantity[i]));
            }
            sharesSells.add(shares);
        }
        return sharesSells;
    }

    /**
     * Converts SharesSellList DTO to a list of ShareSell
     * @param shareSellList SharesSellList DTO
     * @return a list of SharesSell
     */
    @Override
    public ArrayList<ShareSell> converToSharesSell(ShareSellList shareSellList) {
        ArrayList<ShareSell> sharesSell = new ArrayList<ShareSell>();
        int sharesLen = shareSellList.getShareQuantity().length;
        int[] shareId = shareSellList.getShareId();
        float[] sharesValue = shareSellList.getShareValue();
        int[] sharesQuantity = shareSellList.getShareQuantity();
        for (int i = 0; i < sharesLen; i++) {
            sharesSell.add(new ShareSell(shareId[i], sharesValue[i], sharesQuantity[i]));
        }
        return sharesSell;
    }


    @Override
    public ShareSellList convertToShareSellList(ArrayList<ShareSell> shareSells) {
        ShareSellList shareSellList = new ShareSellList(shareSells.size());
        int i = 0;
        for (ShareSell s : shareSells) {
            shareSellList.setShareId(i, s.getShareId());
            shareSellList.setShareValue(i, s.getShareValue());
            shareSellList.setShareQuantity(i, s.getShareQuantity());
            i++;
        }
        return shareSellList;
    }


    @Override
    public ArrayList<ShareSellList> convertToShareSellListUpdate(ArrayList<ArrayList<ShareSell>> shareSells) {
        ArrayList<ShareSellList> sharesSells = new ArrayList<ShareSellList>();
        for(int n=0; n<shareSells.size(); n++){
            ShareSellList shareSellList = new ShareSellList(shareSells.size());
            int i = 0;
            for (ShareSell s : shareSells.get(n)) {
                shareSellList.setShareId(i, s.getShareId());
                shareSellList.setShareValue(i, s.getShareValue());
                shareSellList.setShareQuantity(i, s.getShareQuantity());
                i++;
            }
            sharesSells.add(shareSellList);
        }
        return sharesSells;
    }

    /**
     * Converts ShareTrade DTO to User POJO
     * @param shareTrade ShareTrade DTO
     * @return User POJO
     */
    @Override
    public User shareTradeToUser(ShareTrade shareTrade) {
        User user = new User();
        user.setUserId(shareTrade.getUserId());
        user.setTotalBalance(shareTrade.getTotalBalance());
        return user;
    }

    /**
     * Converts User POJO to ShareTrade DTO
     * @param user User POJO
     * @return ShareTrade DTO
     */
    @Override
    public ShareTrade userToShareTrade(User user) {
        ShareTrade shareTrade = new ShareTrade();
        shareTrade.setUserId(user.getUserId());
        shareTrade.setTotalBalance(user.getTotalBalance());
        return shareTrade;
    }

    /**
     * Converts ShareTrade DTO to Company POJO
     * @param shareTrade ShareTrade DTO
     * @return Company POJO
     */
    @Override
    public Company shareTradeToCompany(ShareTrade shareTrade) {
        Company company = new Company();
        company.setCompanyId(shareTrade.getCompanyId());
        return company;
    }

    /**
     * Converts Company POJO to ShareTrade DTO
     * @param company Company POJO
     * @return ShareTrade DTO
     */
    @Override
    public ShareTrade companyToShareTrade(Company company) {
        ShareTrade shareTrade = new ShareTrade();
        shareTrade.setCompanyId(company.getCompanyId());
        return shareTrade;
    }

    /**
     * Creates ShareTrade DTO with User and Company details
     * @param user user
     * @param company company
     * @return ShareTrade DTO
     */
    @Override
    public ShareTrade userCompanyToShareTrade(User user, Company company) {
        ShareTrade shareTrade = new ShareTrade();
        shareTrade.setUserId(user.getUserId());
        shareTrade.setTotalBalance(user.getTotalBalance());
        shareTrade.setCompanyId(company.getCompanyId());
        return shareTrade;
    }

    /**
     * Converts ShareChangeList DTO to a list of ShareChange
     * @param shareChangeList ShareChangeList DTO
     * @return a list of ShareChange
     */
    @Override
    public ArrayList<ShareChange> convertToSharesChange(ShareChangeList shareChangeList) {
        ArrayList<ShareChange> shares = new ArrayList<ShareChange>();
        int sharesLen = shareChangeList.getCompanyName().length;
        int userId = shareChangeList.getUserId();
        int[] companyId = shareChangeList.getCompanyId();
        int[] sharesId = shareChangeList.getShareId();
        String[] companyNames = shareChangeList.getCompanyName();
        float[] shareOriginalValue = shareChangeList.getShareOriginalValue();
        float[] shareCurrentValue = shareChangeList.getShareCurrentValue();
        int[] sharesQuantity = shareChangeList.getSharesQuantity();
        float[] profitLoss = shareChangeList.getProfitLoss();
        for (int i = 0; i < sharesLen; i++) {
            shares.add(new ShareChange(userId, companyId[i], sharesId[i], companyNames[i], shareOriginalValue[i], shareCurrentValue[i], sharesQuantity[i], profitLoss[i]));
        }
        return shares;
    }

    /**
     * Converts a list of ShareChange to ShareChangeList DTO
     * @param sharesChange a list of ShareChange
     * @return ShareChangeList DTO
     */
    @Override
    public ShareChangeList convertToShareChangeList(ArrayList<ShareChange> sharesChange) {
        ShareChangeList shareChangeList = new ShareChangeList(sharesChange.size());
        shareChangeList.setUserId(sharesChange.get(0).getUserId());
        int i = 0;
        for (ShareChange s : sharesChange) {
            shareChangeList.setCompanyId(i, s.getCompanyId());
            shareChangeList.setShareId(i, s.getShareId());
            shareChangeList.setCompanyName(i, s.getCompanyName());
            shareChangeList.setShareOriginalValue(i, s.getShareOriginalValue());
            shareChangeList.setShareCurrentValue(i, s.getShareCurrentValue());
            shareChangeList.setSharesQuantity(i, s.getSharesQuantity());
            shareChangeList.setProfitLoss(i, s.getProfitLoss());
            i++;
        }
        return shareChangeList;
    }
}
