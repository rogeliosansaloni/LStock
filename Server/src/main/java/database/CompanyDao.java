package database;


import model.entities.Bot;
import model.entities.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Represents the DAO for the Company table
 */
public class CompanyDao {

    private DBConnector dbConnector;

    public CompanyDao(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * It willl create a company in the database
     *
     * @param company the company to create
     */
    public void createCompany(Company company) {
        boolean companyExist = false;
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM Company WHERE name LIKE '%" + company.getName() + "%';");

        try {
            while (verify.next()) {
                System.out.println("This company already exists.");
                companyExist = true;
            }
            if (!companyExist) {
                dbConnector.insertQuery("INSERT INTO Company (name) VALUES ('" + company.getName() + "')");
            }
        } catch (SQLException e) {
            System.out.println("Error creating" + company.getName() + ".");
        }
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
            System.out.println("Error getting all companies");
        }
        return companies;
    }

    /**
     * Retrieves a company by name
     * @param companyName name of the company
     * @return Company object with all the information retrieved from the database
     */
    public Company getCompanyByName(String companyName) {
        final String selectQuery = "SELECT * FROM Company WHERE name = %s;";
        final String errorMessage = "Error in getting company information for %s";

        ResultSet resultSet = dbConnector.selectQuery(String.format(selectQuery, companyName));
        try {
            while (resultSet.next()) {
                return toCompany(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(String.format(errorMessage, companyName));
        }
        return null;
    }

    /**
     * Converts retrieved information to Company object
     * @param resultSet information retrieved from the database
     * @return Company object with all the information
     * @throws SQLException
     */
    private Company toCompany(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setCompanyId(resultSet.getInt("company_id"));
        company.setName(resultSet.getString("name"));
        return company;
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
            System.out.println("Error getting information from the company");
        }
        return null;
    }


}