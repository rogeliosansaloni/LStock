package utils.mappers;

import model.entities.*;

import java.util.ArrayList;

public interface ShareMapper {
    Purchase[] shareTradeToPurchase(ShareTrade shareTrade);

    User shareTradeToUser (ShareTrade shareTrade);

    ShareTrade userToShareTrade (User user);

    Company shareTradeToCompany (ShareTrade shareTrade);

    ShareTrade companyToShareTrade (Company company);

    ShareTrade userCompanyToShareTrade (User user, Company company);

    ArrayList<ShareSell> converToSharesSell(ShareSellList shareSellList);

    ShareSellList convertToShareSellList(ArrayList<ShareSell> shareSells);

    ArrayList<ShareChange> convertToSharesChange(ShareChangeList shareChangeList);

    ShareChangeList convertToShareChangeList(ArrayList<ShareChange> sharesChange);
}
