package database;

import model.entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Represents the DAO for the Share table
 */
public class ShareDao {

    private DBConnector dbConnector;
    private static final String UPDATE_PURCHASE_ERROR = "Error updating the purchase.";
    private static final String GETTING_SHARES_ERROR = "Error getting the shares.";

    /**
     * Creates and initializes the ShareDao
     * @param dbConnector Database Connector
     */
    public ShareDao(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Update the purchased Share
     * @param purchase Purchase data
     */
    public void updatePurchasedShare (Purchase purchase) {
        //First we need to know if that purchase already exists in the Purchase value of the database
        ResultSet retrievedCheck = dbConnector.selectQuery("CALL checkIfPurchaseExists(" + purchase.getUserId() + ", " + purchase.getCompanyId() + ", " + purchase.getShareId() + ");");
        try {
            if(!retrievedCheck.next()){
                dbConnector.callProcedure("CALL insertPurchase(" + purchase.getUserId() + ", "
                        + purchase.getCompanyId()+ ", " + purchase.getShareId() + ", " + purchase.getShareQuantity() + ");");
            } else{
                dbConnector.callProcedure("CALL updatePurchase(" + purchase.getUserId() + ", "
                        + purchase.getCompanyId() + ", " + purchase.getShareId() + ", " + purchase.getShareQuantity() + ");");
            }
        } catch (SQLException e) {
            System.out.println(UPDATE_PURCHASE_ERROR);
        }
    }

    /**
     * Gets the current share identifier
     * @param companyId company identifier
     */
    public int getCurrentShareId(int companyId){
        int shareId = 0;
        ResultSet retrievedShares = dbConnector.selectQuery("CALL getMostCurrentShareId(" + companyId + ");");
        try {
            while(retrievedShares.next()){
                shareId = retrievedShares.getInt("share_id");
            }
        } catch (SQLException e) {
            System.out.println(GETTING_SHARES_ERROR);
        }
        return shareId;
    }

    /**
     * Gets a list of sold shares of a user and company
     * @param userId user identifier
     * @param companyId company identifier
     * @return list of shares sold
     */
    public ArrayList<ShareSell> getSharesSell(int userId, int companyId) {
        ResultSet retrieved = dbConnector.selectQuery("CALL getSharesSell(" + userId + "," + companyId + ");");
        ArrayList<ShareSell> shares = null;
        try {
            shares = new ArrayList<ShareSell>();
            while (retrieved.next()) {
                shares.add(toShareSell(retrieved));
            }
        } catch (SQLException e) {
            System.out.println("Error getting shares sell");
        }
        return shares;
    }

    /**
     * Gets the info from the shares that will be used in the Shares View of the Client
     * @param userId the id of the user
     * @return ArrayList<ShareChange> all shares
     */
    public ArrayList<ShareChange> getSharesChange(int userId) {
        ResultSet retrievedShares = dbConnector.selectQuery("CALL getSharesChange(" + userId + ");");
        ArrayList<ShareChange> sharesChange = new ArrayList<ShareChange>();
        try {
            while (retrievedShares.next()) {
                ShareChange s = toShareChange(retrievedShares);
                sharesChange.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all shares");
        }
        return sharesChange;
    }

    /**
     * Converts retrieved information to ShareSell
     *
     * @param resultSet result set from database
     * @return a ShareSell object containing the information retrieved from the database.
     * @throws SQLException
     */
    private ShareSell toShareSell(ResultSet resultSet) throws SQLException {
        ShareSell shareSell = new ShareSell();
        shareSell.setShareId(resultSet.getInt("shareId"));
        shareSell.setShareValue(resultSet.getFloat("shareValue"));
        shareSell.setShareQuantity(resultSet.getInt("shareQuantity"));
        return shareSell;
    }

    /**
     * Converts retrieved information to ShareChange
     *
     * @param resultSet result set from database
     * @return a ShareChange object containing the information retrieved from the database.
     * @throws SQLException
     */
    private ShareChange toShareChange(ResultSet resultSet) throws SQLException {
        ShareChange shareChange = new ShareChange();
        shareChange.setUserId(resultSet.getInt("userId"));
        shareChange.setCompanyId(resultSet.getInt("companyId"));
        shareChange.setShareId(resultSet.getInt("shareId"));
        shareChange.setCompanyName(resultSet.getString("companyName"));
        shareChange.setShareOriginalValue(resultSet.getFloat("shareOriginalValue"));
        shareChange.setShareCurrentValue(resultSet.getFloat("shareCurrentValue"));
        shareChange.setSharesQuantity(resultSet.getInt("sharesQuantity"));
        shareChange.setProfitLoss(resultSet.getFloat("profitLoss"));
        return shareChange;
    }
}
