package Connetor;


import networ.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


public class PurchaseDao {

    private DBConector dbConector;
    private Company company;
    private User user;
    public PurchaseDao (DBConector dbConector, Company company, User user){
        this.dbConector = dbConector;
        this.company = company;
        this.user = user;
    }
    /**
     * It willl create a purchase in the database
     * @param purchase the purchase to create
     */
    public void createPurchase (Purchase purchase, Company company, User user) {
        ResultSet verify = connectorDB.selectQuery("SELECT * FROM Share WHERE share_id = "+ share.getShareId()+" AND company_id ="+company.getCompanyId()
                +"AND user_id = "+ User.getUserId()+";");

        try {
            while (verify.next()) {
                if (verify.next().equals("share_id") && verify.next().equals("company_id") && verify.next().equals("user_id")) {
                    System.out.println("Added Purchase");
                    connectorDB.insertQuery("INSERT INTO Share (share_id) VALUES ('"+ share.getShareId() +"','"+ company.getCompanyId()+"','"+ User.getUserId() +"')");
                }
            }

        }catch (SQLException e) {
            System.out.println("Error creating purchase");
        }

    }

    /**
     * It will permit to errase purchase
     * @param purchase Purchase to erase
     */
    public boolean deletePurchase (Purchase purchase, Company company, User user) {
        ResultSet verify = connectorDB.selectQuery("SELECT * FROM Share WHERE share_id = "+ share.getShareId()+" AND company_id ="+company.getCompanyId()
                +"AND user_id = "+ User.getUserId()+";");
        try {
            while (verify.next()) {
                if (verify.next().equals("share_id") && verify.next().equals("company_id") && verify.next().equals("user_id")) {
                    connectorDB.deleteQuery("DELETE FROM Share WHERE share_id = "+ share.getShareId()+" AND company_id ="+company.getCompanyId()
                            +"AND user_id = "+ User.getUserId()+";");
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
     * It will all get all the purchases in the LStock
     */
    //TENGO DUDA LO QUE SE TIENE QUE DEVOLVER AQUI
    public ArrayList<String> getAllPurchases () {
        ResultSet getPurchases = connectorDB.selectQuery("SELECT * FROM Purchase");
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
