package database;

import model.entities.Company;
import model.entities.Share;
import model.entities.ShareChange;
import model.entities.User;

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

    public void insertPurchasedShare (User user, Company company) {
        dbConnector.insertQuery("INSERT INTO Purchase (user_id, share_id, company_id, share_quantity) " + "VALUES (" + user.getUserId() + ", "
                                + company.getShareId() + ", " + company.getCompanyId() + ", " + company.getValue() + ");");
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

    private ShareChange toShareChange(ResultSet resultSet) throws SQLException {
        ShareChange shareChange = new ShareChange();
        shareChange.setShareId(resultSet.getInt("shareId"));
        shareChange.setCompanyName(resultSet.getString("companyName"));
        shareChange.setShareValue(resultSet.getFloat("shareValue"));
        shareChange.setMyActions(resultSet.getInt("myActions"));
        shareChange.setProfitLoss(resultSet.getFloat("profitLoss"));

        return shareChange;
    }

}
