package database;

import model.entities.Company;
import model.entities.Share;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Represents the DAO for the Share table
 */
public class ShareDao {

    private DBConnector dbConnector;

    public ShareDao(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
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


    /**
     * It will get all the shares in the LStock
     *
     * @return ArrayList<Share> all shares
     */
    public ArrayList<Share> getAllShares() {
        ResultSet retrievedShare = dbConnector.selectQuery("SELECT * FROM Shares;");
        ArrayList<Share> shares = null;
        try {
            shares = new ArrayList<Share>();
            while (retrievedShare.next()) {
                //No tengo muy claro que se tiene que coger
                Share s = toShare(retrievedShare);
                shares.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all shares");
        }
        return shares;
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
