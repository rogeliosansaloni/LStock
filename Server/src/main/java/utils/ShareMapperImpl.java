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
    public Company shareTradeToCompany (ShareTrade shareTrade) {
        Company company = new Company();
        company.setCompanyId(shareTrade.getCompanyId());
        company.setValue(shareTrade.getSharePrice()[0]);
        return company;
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
        int userId = shareSellList.getUserId();
        int companyId = shareSellList.getCompanyId();
        int[] shareId = shareSellList.getShareId();
        String companyName = shareSellList.getCompanyName();
        float[] sharesValue = shareSellList.getShareValue();
        int[] sharesQuantity = shareSellList.getShareQuantity();
        for (int i = 0; i < sharesLen; i++) {
            sharesSell.add(new ShareSell(userId, companyId, shareId[i], companyName, sharesValue[i], sharesQuantity[i]));
        }
        return sharesSell;
    }

    @Override
    public ShareSellList convertToShareSellList(ArrayList<ShareSell> shareSells) {
        ShareSellList shareSellList = new ShareSellList(shareSells.size());
        int i = 0;
        shareSellList.setUserId(shareSells.get(0).getUserId());
        shareSellList.setCompanyId(shareSells.get(0).getCompanyId());
        shareSellList.setCompanyName(shareSells.get(0).getCompanyName());
        for (ShareSell s : shareSells) {
            shareSellList.setShareId(i, s.getShareId());
            shareSellList.setShareValue(i, s.getShareValue());
            shareSellList.setShareQuantity(i, s.getShareQuantity());
            i++;
        }
        return shareSellList;
    }
}
