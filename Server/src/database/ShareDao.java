package Connetor;


import networ.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


public class ShareDao {

    private DBConector dbConector;
    public ShareDao(DBConector dbConector){
        this.dbConector = dbConector;
    }

    /**
     * It willl create a share in the database
     * @param share the share to create
     */
    public void createShare (Share share) {
        ResultSet verify = connectorDB.selectQuery("SELECT * FROM Share WHERE share_id = "+ share.getShareId();

        try {
            while (verify.next()) {
                if (verify.next().equals("share_id")) {
                    System.out.println("Added shares");
                    connectorDB.insertQuery("INSERT INTO Share (share_id) VALUES ('"+ share.getShareId() +"')");
                }
            }

        }catch (SQLException e) {
            System.out.println("Error creating shares");
        }

    }

    /**
     * It will permit to errase shares
     * @param share Share to erase
     */
    public boolean deleteShare (Share share) {
        ResultSet verify = connectorDB.selectQuery("SELECT * FROM Share WHERE share_id = "+ share.getShareId();

        try {
            while (verify.next()) {
                if (verify.next().equals("share_id")) {
                    connectorDB.deleteQuery("DELETE FROM Share WHERE share_id ='"+share.getShareId()+"')");
                    System.out.println("Share Deleted");
                    return true;
                }
            }

        }catch (SQLException e) {
            System.out.println("Error deleting shares";
        }
        return false;
    }

    /**
     * It will all get all the shares in the LStock
     */
    public ArrayList<String> getAllShares () {
        ResultSet getShares = connectorDB.selectQuery("SELECT * FROM Shares");
        ArrayList<String> shares = null;
        try {
            shares = new ArrayList<String>();
            while (getShares.next()){
                //No tengo muy claro que se tiene que coger
                shares.add(getShares.getObject("share_id").toString());
            }
        } catch (SQLException e) {
            System.out.println("Error getting all shares");
        }
        return shares;

    }


}
