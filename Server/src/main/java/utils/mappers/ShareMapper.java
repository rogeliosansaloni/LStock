package utils.mappers;

import model.entities.*;

import java.util.ArrayList;

/**
 *  Share Mapper method interface
 */
public interface ShareMapper {
    /**
     * Converts retrieved information into a Purchase array
     * @param shareTrade ShareTrade data
     * @return a Purchase array containing the information retrieved.
     */
    Purchase[] shareTradeToPurchase(ShareTrade shareTrade);

    /**
     * Converts retrieved information into a User
     * @param shareTrade ShareTrade data
     * @return a User object containing the information retrieved.
     */
    User shareTradeToUser (ShareTrade shareTrade);

    /**
     * Converts retrieved information into a ShareTrade
     * @param user User data
     * @return a ShareTrade object containing the information retrieved.
     */
    ShareTrade userToShareTrade (User user);

    /**
     * Converts retrieved information into a Company
     * @param shareTrade ShareTrade data
     * @return a Company object containing the information retrieved.
     */
    Company shareTradeToCompany (ShareTrade shareTrade);

    /**
     * Converts retrieved information into a ShareTrade
     * @param company Company data
     * @return a ShareTrade object containing the information retrieved.
     */
    ShareTrade companyToShareTrade (Company company);

    /**
     * Converts retrieved information into a ShareTrade
     * @param user User data
     * @param company Company data
     * @return a ShareTrade object containing the information retrieved.
     */
    ShareTrade userCompanyToShareTrade (User user, Company company);

    /**
     * Converts retrieved information into a ShareSell Arraylist
     * @param shareSellList ShareSellList data
     * @return a ShareSell Arraylist containing the information retrieved.
     */
    ArrayList<ArrayList<ShareSell>> converToSharesSell(ArrayList<ShareSellList> shareSellList);

    /**
     * Converts retrieved information into a ShareSellList
     * @param shareSells ShareSell list
     * @return a ShareSellList object containing the information retrieved.
     */
    ArrayList<ShareSellList> convertToShareSellList(ArrayList<ArrayList<ShareSell>> shareSells);

    /**
     * Converts retrieved information into a ShareChange Arraylist
     * @param shareChangeList ShareChangeList data
     * @return a ShareChange Arraylist containing the information retrieved.
     */
    ArrayList<ShareChange> convertToSharesChange(ShareChangeList shareChangeList);

    /**
     * Converts retrieved information into a ShareChangeList
     * @param sharesChange list of ShareChange
     * @return a ShareChangeList object containing the information retrieved.
     */
    ShareChangeList convertToShareChangeList(ArrayList<ShareChange> sharesChange);
}
