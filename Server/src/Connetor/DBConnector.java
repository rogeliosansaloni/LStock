package Connetor;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DBConnector {

    private String db_name;
    private String db_password;
    private String db;
    private int db_port;
    private String url = "jdbc:mysql://localhost";
    private static Connection conn = null;
    private static Statement s;


    public DBConnector(String db_name, String db_password, String database, int db_port){
        this.db_name = db_name;
        this.db_password = db_password;
        this.db = database;
        this.db_port = db_port;
        this.url += ":"+db_port+"/"+db;
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Connection");
            conn = (Connection) DriverManager.getConnection(url, db_name, db_password);
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

    public void deleteQuery(String query){
        try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Delete KO" + ex.getSQLState());
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

    public void createUser(String db_name, String db_password){
        String query = "CREATE USER '" + db_name + "' IDENTIFIED BY '" + db_password + "';";
        try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Creating User KO: " + db_name + " --> " + ex.getSQLState());
            System.out.println("Query: " + query);
            System.err.println(ex);
        }
    }

    public void disconnect(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Connetion KO" + e.getSQLState());
            System.err.println(e);
        }
    }





}
