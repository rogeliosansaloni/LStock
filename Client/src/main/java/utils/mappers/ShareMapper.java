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

    /**
     * Converts retrieved information into a ShareSell Arraylist
     * @param shareSellList ShareSellList data
     * @return a ShareSell Arraylist containing the information retrieved.
     */
    ArrayList<ShareSell> converToSharesSell(ShareSellList shareSellList);

    /**
     * Converts retrieved information into a ShareSellList
     * @param shareSells ShareSell list
     * @return a ShareSellList object containing the information retrieved.
     */
    ShareSellList convertToShareSellList(ArrayList<ShareSell> shareSells);

    ArrayList<ArrayList<ShareSell>> converToSharesSellUpdate(ArrayList<ShareSellList> shareSellList);

    ArrayList<ShareSellList> convertToShareSellListUpdate(ArrayList<ArrayList<ShareSell>> shareSells);
}
