package Connetor;


import networ.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;
import java.util.LinkedList;


public class UserDao {

    private ConectorDB connectorDB;

    /**
     * CREATE Company
     * @param Company company
     */
    public void createUser (Company company) {
        boolean companyExist = false;
        ResultSet verify = connectorDB.selectQuery("SELECT * FROM User WHERE nickname LIKE '%"+company.getCompanyName()+"%'");

        try {
            while (verify.next()) {
                if (verify.next().equals("name")) {
                    System.out.println("This company already exist.");
                    companyExist = true;
                }
            }
            if(!companyExist){
                connectorDB.insertQuery("INSERT INTO Company (name) VALUES ('"+company.getCompanyName()+"')");
            }
        }catch (SQLException e) {
            System.out.println("Error creating"+company.getCompanyName());
        }

    }

    /**
     * DELETE COMPANY
     * @param Company company
     */
    public boolean deleteUser (Company company) {
        ResultSet verify = connectorDB.selectQuery("SELECT * FROM User WHERE nickname LIKE '%"+company.getCompanyName()+"%'");
        try {
            while (verify.next()) {
                if (verify.next().equals("name")) {
                    connectorDB.deleteQuery("DELETE FROM Company WHERE name ='"+company.getCompanyName()+"')");
                    System.out.println("Company Deleted");
                    return true;
                }
            }

        }catch (SQLException e) {
            System.out.println("Error deleting "+company.getCompanyName()")";
        }
        return false;
    }

    /**
     * GET ALL Companies
     * @param
     */
    public LinkedList<String> getAllCompanies () {
        ResultSet getCompany = connectorDB.selectQuery("SELECT * FROM Company");
        LinkedList<String> companies = null;
        try {
            companies = new LinkedList<String>();
            while (getCompany.next()){
                companies.add((String)getUsers.getObject("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all companies");
        }
        return users;

    }

    /**
     * GET COMPANY INFO
     * @param Company Company
     */
    public Company getCompanyInfo(Company Company){
        User userData = new User;
        ResultSet verify = connectorDB.selectQuery("SELECT * FROM Company WHERE (name LIKE '"+ user.getNickname()+"' OR correo LIKE '"+user.getEmail()+"')");

        try {
            while (verify.next()){
                if (verify.next().equals("name")) {
                    userData.setNickname((String) verify.getObject("name"));
                }
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Error");
        }
        return null;
    }


}
