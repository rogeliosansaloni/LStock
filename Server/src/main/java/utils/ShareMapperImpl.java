package utils;

import model.entities.*;
import utils.mappers.ShareMapper;

import java.util.ArrayList;

public class ShareMapperImpl implements ShareMapper {

    @Override
    public Purchase[] shareTradeToPurchase(ShareTrade shareTrade) {
        int numPurchases = shareTrade.getShareId().length;
        Purchase[] purchases = new Purchase[numPurchases];
        for(int i=0; i<numPurchases; i++){
            purchases[i] = new Purchase();
            purchases[i].setUserId(shareTrade.getUserId());
            purchases[i].setCompanyId(shareTrade.getCompanyId());
            purchases[i].setShareId(shareTrade.getShareId()[i]);
            purchases[i].setShareQuantity(shareTrade.getNumShares()[i]);
        }
        return purchases;
    }

    @Override
    public User shareTradeToUser(ShareTrade shareTrade) {
        User user = new User();
        user.setUserId(shareTrade.getUserId());
        user.setTotalBalance(shareTrade.getTotalBalance());
        return user;
    }

    @Override
    public ShareTrade userToShareTrade (User user) {
        ShareTrade shareTrade = new ShareTrade();
        shareTrade.setUserId(user.getUserId());
        shareTrade.setTotalBalance(user.getTotalBalance());
        return  shareTrade;
    }

    @Override
    public Company shareTradeToCompany (ShareTrade shareTrade) {
        Company company = new Company();
        company.setCompanyId(shareTrade.getCompanyId());
        company.setValue(shareTrade.getSharePrice()[0]);
        return company;
    }

    @Override
    public ShareTrade companyToShareTrade (Company company) {
        ShareTrade shareTrade = new ShareTrade();
        shareTrade.setCompanyId(company.getCompanyId());
        shareTrade.setSharePrice(company.getValue());
        shareTrade.setShareId(company.getShareId());
        return shareTrade;
    }

    @Override
    public ShareTrade userCompanyToShareTrade (User user, Company company) {
        ShareTrade shareTrade = new ShareTrade();
        shareTrade.setUserId(user.getUserId());
        shareTrade.setTotalBalance(user.getTotalBalance());
        shareTrade.setCompanyId(company.getCompanyId());
        return shareTrade;
    }

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
    public ArrayList<ShareChange> convertToSharesChange(ShareChangeList shareChangeList) {
        ArrayList<ShareChange> shares = new ArrayList<ShareChange>();
        int sharesLen = shareChangeList.getCompanyName().length;
        int[] sharesId = shareChangeList.getShareId();
        String[] companyNames = shareChangeList.getCompanyName();
        float[] actionValue = shareChangeList.getShareValue();
        int[] sharesQuantity = shareChangeList.getSharesQuantity();
        float[] profitLoss = shareChangeList.getProfitLoss();
        for (int i = 0; i < sharesLen; i++) {
            shares.add(new ShareChange(sharesId[i], companyNames[i], actionValue[i], sharesQuantity[i], profitLoss[i]));
        }
        return shares;
    }

    @Override
    public ShareChangeList convertToShareChangeList(ArrayList<ShareChange> sharesChange) {
        ShareChangeList shareChangeList = new ShareChangeList(sharesChange.size());
        int i = 0;
        for (ShareChange s : sharesChange) {
            shareChangeList.setShareId(i, s.getShareId());
            shareChangeList.setCompanyName(i, s.getCompanyName());
            shareChangeList.setShareValue(i, s.getShareValue());
            shareChangeList.setSharesQuantity(i, s.getSharesQuantity());
            shareChangeList.setProfitLoss(i, s.getProfitLoss());
            i++;
        }
        return shareChangeList;
    }
}
