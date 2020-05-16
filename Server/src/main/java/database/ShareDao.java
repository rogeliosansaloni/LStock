package database;

import model.entities.Company;
import model.entities.Share;
import model.entities.User;
import model.entities.Purchase;


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
                ResultSet resultPurchase = dbConnector.selectQuery("CALL insertPurchase(" + purchase.getUserId() + ", "
                        + purchase.getShareId() + ", " + purchase.getCompanyId() + ", " + purchase.getShareQuantity() + ");");
                resultPurchase.next();
            } else{
                ResultSet resultPurchase = dbConnector.selectQuery("CALL updatePurchase(" + purchase.getUserId() + ", "
                        + purchase.getShareId() + ", " + purchase.getCompanyId() + ", " + purchase.getShareQuantity() + ");");
                resultPurchase.next();
            }
        } catch (SQLException e) {
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
            if(retrievedShares.next() != false){
                numShares = retrievedShares.getInt("numShares");
            }
        } catch (SQLException e) {
            System.out.println(GETTING_SHARES_ERROR);
        }
        return numShares;
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

}
