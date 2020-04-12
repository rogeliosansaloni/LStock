package database;

import model.Company;
import model.Share;
import network.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ShareDao {

    private DBConnector dbConnector;

    public ShareDao (DBConnector dbConnector){
        this.dbConnector = dbConnector;
    }

    /**
     * It willl create a share in the database
     * @param share the share to create
     */
    public void createShare (Share share, Company company) {
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM Share WHERE share_id = " + share.getIdShare() + " AND company_id =" + company.getCompanyId() + ";";

        try {
            while (verify.next()) {
                if (verify.next().equals("share_id") && verify.next().equals("company_id")) {
                    System.out.println("Added shares");
                    dbConnector.insertQuery("INSERT INTO Share (share_id,company_id) " +
                            "VALUES ('" + share.getIdShare() + "','" + share.getCompanyId() + "','" + share.getPrice() + "','" + share.getTime() + "');");
                }
            }

        }catch (SQLException e) {
            System.out.println("Error creating shares");
        }

    }


    /**
     * It will get all the shares in the LStock
     */
    //TENGO DUDA LO QUE SE TIENE QUE DEVOLVER AQUI
    public ArrayList<Share> getAllShares () {
        ResultSet getShares = dbConnector.selectQuery("SELECT * FROM Shares;");
        ArrayList<Share> shares = null;
        try {
            shares = new ArrayList<Share>();
            while (getShares.next()){
                //No tengo muy claro que se tiene que coger
                Share s = new Share(getShares.getObject("share_id").toString());
                shares.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all shares");
        }
        return shares;

    }


}
