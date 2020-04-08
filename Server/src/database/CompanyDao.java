package Connetor;


import networ.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;
import java.util.LinkedList;


public class CompanyDao {

    private DBConector dbConector;
    public  CompanyDao (DBConector dbConector){
        this.dbConector = dbConector;
    }

    /**
     * It willl create a company in the database
     * @param company the company to create
     */
    public void createCompany (Company company) {
        boolean companyExist = false;
        ResultSet verify = connectorDB.selectQuery("SELECT * FROM Company WHERE nickname LIKE '%"+company.getCompanyName()+"%';");

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
     * It will permit to errase a company
     * @param company Company to erase
     */
    public boolean deleteCompany (Company company) {
        ResultSet verify = connectorDB.selectQuery("SELECT * FROM Company WHERE nickname LIKE '%"+company.getCompanyName()+"%';");
        try {
            while (verify.next()) {
                if (verify.next().equals("name")) {
                    connectorDB.deleteQuery("DELETE FROM Company WHERE name LIKE '"+company.getCompanyName()+"');");
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
     * It will all get all the companies in the LStock
     */
        public ArrayList<String> getAllCompanies () {
        ResultSet getCompany = connectorDB.selectQuery("SELECT * FROM Company;");
        ArrayList<String> companies = null;
        try {
            companies = new ArrayList<String>();
            while (getCompany.next()){
                companies.add(getCompanies.getObject("name").toString());
            }
        } catch (SQLException e) {
            System.out.println("Error getting all companies");
        }
        return companies;

    }

    /**
     * It will get the information of one company
     * @param company Company where will get the information from.
     */
    public Company getCompanyInfo(Company Company){
        ResultSet verify = connectorDB.selectQuery("SELECT * FROM Company WHERE nickname LIKE '%"+company.getCompanyName()+"%';");
        Company companyData = new Company;

        try {
            while (verify.next()){
                if (verify.next().equals("name")) {
                    companyData.setName(verify.getObject("name").toString());
                }
            }
            return companyData;
        } catch (SQLException e) {
            System.out.println("Error getting information from the company");
        }
        return null;
    }


}
