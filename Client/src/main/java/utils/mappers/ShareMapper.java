package utils.mappers;

import model.entities.*;

import java.util.ArrayList;

/**
 * Mapper interface for Share
 */
public interface ShareMapper {
    User shareTradeToUser(ShareTrade shareTrade);

    ShareTrade userToShareTrade(User user);

    Company shareTradeToCompany(ShareTrade shareTrade);

    ShareTrade companyToShareTrade(Company company);

    ShareTrade userCompanyToShareTrade(User user, Company company);

    ArrayList<ShareChange> convertToSharesChange(ShareChangeList shareChangeList);

    ShareChangeList convertToShareChangeList(ArrayList<ShareChange> sharesChange);

    ArrayList<ShareSell> converToSharesSell(ShareSellList shareSellList);

    ShareSellList convertToShareSellList(ArrayList<ShareSell> shareSells);
}
