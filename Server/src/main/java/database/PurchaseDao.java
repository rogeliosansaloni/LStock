package database;

import network.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PurchaseDao {

    private DBConnector dbConnector;

    public  PurchaseDao (DBConnector dbConnector){
        this.dbConnector = dbConnector;
    }

    /**
     * It willl create a purchase in the database
     * @param purchase the purchase to create
     */
    public void createPurchase (Purchase purchase, Company company, User user) {
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM Purchase WHERE share_id = " + share.getShareId() + " AND company_id =" + company.getCompanyId()
                + "AND user_id = " + user.getUserId() + ";");

        try {
            while (verify.next()) {
                if (verify.next().equals("share_id") && verify.next().equals("company_id") && verify.next().equals("user_id")) {
                    System.out.println("Added Purchase");
                    dbConnector.insertQuery("INSERT INTO Purchase (share_id,company_id,user_id, share_quantity) " +
                            "VALUES ('" + share.getShareId() + "','" + company.getCompanyId() + "','" + user.getUserId()  + "','" + share.getShareQuantity() + "')");
                }
            }

        }catch (SQLException e) {
            System.out.println("Error creating purchase");
        }

    }

    /**
     * It will permit to erase purchase
     * @param purchase Purchase to erase
     */
    public boolean deletePurchase (Purchase purchase, Company company, User user) {
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM Purchase WHERE share_id = " + share.getShareId() + " AND company_id =" + company.getCompanyId()
                + "AND user_id = " + User.getUserId() + ";");
        try {
            while (verify.next()) {
                if (verify.next().equals("share_id") && verify.next().equals("company_id") && verify.next().equals("user_id")) {
                    dbConnector.deleteQuery("DELETE FROM Purchase WHERE share_id = " + share.getShareId() + " AND company_id =" + company.getCompanyId()
                            + "AND user_id = " + user.getUserId() + ";");
                    System.out.println("Purchase Deleted");
                    return true;
                }
            }

        }catch (SQLException e) {
            System.out.println("Error deleting purchase";
        }
        return false;
    }

    /**
     * It will get all the purchases in the LStock
     */
    //TENGO DUDA LO QUE SE TIENE QUE DEVOLVER AQUI
    public ArrayList<String> getAllPurchases () {
        ResultSet getPurchases = dbConnector.selectQuery("SELECT * FROM Purchase");
        ArrayList<String> purchases = null;
        try {
            purchases = new ArrayList<String>();
            while (getPurchases.next()){
                //No tengo muy claro que se tiene que coger
                purchases.add(getPurchases.getObject("price").toString());
            }
        } catch (SQLException e) {
            System.out.println("Error getting all purchases");
        }
        return purchases;

    }


}
