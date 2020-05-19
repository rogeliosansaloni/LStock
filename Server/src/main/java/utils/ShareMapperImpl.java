package utils;

import model.entities.*;
import utils.mappers.ShareMapper;

import java.util.ArrayList;

public class ShareMapperImpl implements ShareMapper {
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
        company.setValue(shareTrade.getSharePrice());
        company.setShareId(shareTrade.getShareId());
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
        shareTrade.setSharePrice(company.getValue());
        shareTrade.setShareId(company.getShareId());
        return shareTrade;
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
