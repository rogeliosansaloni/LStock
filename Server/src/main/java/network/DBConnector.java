package network;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {


    private String dbUsername;
    private String dbPassword;
    private String dbName;
    private int dbPort;
    private String db;
    private String url;
    private static Connection conn = null;
    private static Statement s;


    public DBConnector(String url,String dbUsername, String dbPassword, String db, int dbPort){
        this.url =url;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        this.db = db;
        this.dbPort = dbPort;
        this.url += ":"+dbPort+"/";
        this.url += db;
        this.url += "?verifyServerCertificate=false&useSSL=true";
    }


    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Connection");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:"+dbPort+"/"+ db + url + dbUsername + dbPassword+"");
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
