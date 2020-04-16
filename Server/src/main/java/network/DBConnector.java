package network;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {

    private static final String BASE_URL = "jdbc:mysql://%s:%d/%s?verifyServerCertificate=false&useSSL=true";
    private String dbUsername;
    private String dbPassword;
    private String dbName;
    private int dbPort;
    private String db;
    private String url;
    private static Connection conn = null;
    private static Statement s;


    public DBConnector(String url, String dbUsername, String dbPassword, String db, int dbPort){
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        this.dbPort = dbPort;
        this.url = url;
        this.url = String.format(BASE_URL, url, dbPort, db);
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, dbUsername, dbPassword);
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