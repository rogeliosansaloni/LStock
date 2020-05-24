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

    public ShareDao(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public void updatePurchasedShare (Purchase purchase) {
        //First we need to know if that purchase already exists in the Purchase value of the database
        ResultSet retrievedCheck = dbConnector.selectQuery("CALL checkIfPurchaseExists(" + purchase.getUserId() + ", " + purchase.getCompanyId() + ", " + purchase.getShareId() + ");");
        try {
            if(retrievedCheck.next() == false){
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
     * It willl create a share in the database
     *
     * @param share the share to create
     */
    public void createShare(Share share, Company company) {
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM Share WHERE share_id = " + share.getIdShare() + " AND company_id =" + company.getCompanyId() + ";");
        //falta para resolver
        try {
            while (verify.next()) {
                if (false) {
                    System.out.println("Added shares");
                    dbConnector.insertQuery("INSERT INTO Share (share_id, company_id, price) " +
                            "VALUES ('" + share.getIdShare() + "','" + company.getCompanyId() + "','" + share.getPrice() + "');");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error creating shares");
        }
    }


    public int getNumShares(User user, Company company){
        int numShares = 0;
        ResultSet retrievedShares = dbConnector.selectQuery("CALL getNumUserShares(" + user.getUserId() + ", " + company.getCompanyId() + ");");
        try {
            while(retrievedShares.next()){
                numShares = retrievedShares.getInt("numUserShares");
            }
        } catch (SQLException e) {
            System.out.println(GETTING_SHARES_ERROR);
        }
        return numShares;
    }

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
     * Converts retrieved Share information into a Share object
     * @param resultSet information retrieved from the database
     * @return a Share object that contains all information on the specific share
     * @throws SQLException
     */
    private Share toShare(ResultSet resultSet) throws SQLException {
        Share share = new Share();
        share.setIdShare(resultSet.getInt("share_id"));
        share.setPrice(resultSet.getFloat("price"));
        share.getCompany().setCompanyId(resultSet.getInt("company_id"));
        return share;
    }

    private ShareSell toShareSell(ResultSet resultSet) throws SQLException {
        ShareSell shareSell = new ShareSell();
        shareSell.setShareId(resultSet.getInt("shareId"));
        shareSell.setShareValue(resultSet.getFloat("shareValue"));
        shareSell.setShareQuantity(resultSet.getInt("shareQuantity"));
        return shareSell;
    }

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
