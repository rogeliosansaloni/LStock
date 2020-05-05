package database;


import model.entities.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Represents the DAO for the Company table
 */
public class CompanyDao {

    private DBConnector dbConnector;
    private static final String SHARE_INFORMATION_ERROR = "Error getting information from the company share";
    private static final String INFORMATION_ERROR = "Error getting information from the company";
    private static final String GETTING_COMPANIES_ERROR = "Error getting all companies";

    public CompanyDao(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * It will get all the companies in the LStock
     *
     * @return ArrayList<String> all companies name
     */
    public ArrayList<String> getAllCompanies() {
        ResultSet getCompany = dbConnector.selectQuery("SELECT * FROM Company;");
        ArrayList<String> companies = null;
        try {
            companies = new ArrayList<String>();
            while (getCompany.next()) {
                companies.add(getCompany.getObject("name").toString());
            }
        } catch (SQLException e) {
            System.out.println(GETTING_COMPANIES_ERROR);
        }
        return companies;
    }

    /**
     * It will get the information of one company
     *
     * @param company Company where will get the information from.
     * @return companyData Company Information
     */
    public Company getCompanyInfo(Company company) {
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM Company WHERE name LIKE '%" + company.getName() + "%';");
        Company companyData = new Company();

        try {
            while (verify.next()) {
                if (company.getName().equals(verify.getObject("name").toString())) {
                    companyData.setName(verify.getObject("name").toString());
                }
            }
            return companyData;
        } catch (SQLException e) {
            System.out.println(INFORMATION_ERROR);
        }
        return null;
    }

    /**
     * Inserts company recalculated value
     *
     * @param company the company
     */
    public void insertCompanyNewShare (Company company) {
        dbConnector.insertQuery("INSERT INTO Share (company_id, price) VALUES (" + company.getCompanyId() + ", " + company.getValue() + ");");
        ResultSet result = dbConnector.selectQuery("SELECT * FROM Share WHERE company_id = " + company.getCompanyId() + " AND price = " + company.getValue() + ";");
        try {
            while (result.next()) {
                if (company.getCompanyId() == result.getInt("company_id") && company.getValue() == result.getFloat("price")) {
                    company.setShareId(result.getInt("share_id"));
                }
            }
        } catch (SQLException e) {
            System.out.println(SHARE_INFORMATION_ERROR);
        }
    }


}