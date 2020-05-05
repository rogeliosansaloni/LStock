package utils;

import model.entities.Company;
import model.entities.ShareTrade;
import model.entities.User;
import utils.mappers.ShareMapper;

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
}
