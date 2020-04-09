package network;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DBConnector {

    private String Username;
    private String dbPassword;
    private String db;
    private int dbPort;
    private String url = "jdbc:mysql://localhost";
    private static Connection conn = null;
    private static Statement s;


    public DBConnector(String dbUsername, String dbPassword, String database, int dbPort){
        this.Username = dbUsername;
        this.dbPassword = dbPassword;
        this.db = database;
        this.dbPort = dbPort;
        this.url += ":"+dbPort+"/"+db;
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Connection");
            conn = (Connection) DriverManager.getConnection(url, Username, dbPassword);
            if (conn != null) {
                System.out.println("Connecting database " + url + " ... OK");
            }
        }
        catch(SQLException ex) {
            System.out.println("Connecting database" + url + " ... KO");
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public void insertQuery(String query){
        try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Insertion KO " + ex.getSQLState());
            System.out.println("Query: " + query);
            System.err.println(ex);
        }
    }

    public void updateQuery(String query){
        try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Modification KO " + ex.getSQLState());
            System.out.println("Query: " + query);
            System.err.println(ex);
        }
    }


    public ResultSet selectQuery(String query){
        ResultSet rs = null;
        try {
            s =(Statement) conn.createStatement();
            rs = s.executeQuery (query);

        } catch (SQLException ex) {
            System.out.println("Data Recovery KO" + ex.getSQLState());
            System.out.println("Query: " + query);
            System.err.println(ex);
        }
        return rs;
    }


    public void disconnect(){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Connection KO" + ex.getSQLState());
            System.err.println(ex);
        }
    }





}
