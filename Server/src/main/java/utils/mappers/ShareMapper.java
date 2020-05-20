package utils.mappers;

import model.entities.*;

import java.util.ArrayList;

public interface ShareMapper {
    Purchase[] shareTradeToPurchase(ShareTrade shareTrade);

    User shareTradeToUser (ShareTrade shareTrade);

    Company shareTradeToCompany (ShareTrade shareTrade);

    ShareTrade userCompanyToShareTrade (User user, Company company);

    ArrayList<ShareSell> converToSharesSell(ShareSellList shareSellList);

    ShareSellList convertToShareSellList(ArrayList<ShareSell> shareSells);
}
