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
     * Creates a company in the database
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
     * Gets all the companies in the LStock
     *
     * @return ArrayList<String> all companies name
     */
    public ArrayList<Company> getAllCompanies() {
        ResultSet retrieved = dbConnector.selectQuery("SELECT t1.*, c.name\n" +
                "FROM Share t1, Company as c\n" +
                "WHERE t1.time = (SELECT MAX(t2.time)\n" +
                "                 FROM Share t2\n" +
                "                 WHERE t2.company_id = t1.company_id)\n" +
                "AND c.name = (SELECT name FROM Company c2 WHERE c2.company_id = t1.company_id);");
        ArrayList<Company> companies = null;
        try {
            companies = new ArrayList<Company>();
            while (retrieved.next()) {
                companies.add(toCompany(retrieved));
            }
        } catch (SQLException e) {
            System.out.println(GETTING_COMPANIES_ERROR);
        }
        return companies;
    }

    /**
     * Retrieves a company by name
     * @param companyName name of the company
     * @return Company object with all the information retrieved from the database
     */
    public Company getCompanyByName(String companyName) {
        final String selectQuery = "SELECT * FROM Company WHERE name = '%s';";
        final String errorMessage = "Error in getting company information for %s";

        ResultSet resultSet = dbConnector.selectQuery(String.format(selectQuery, companyName));
        try {
            while (resultSet.next()) {
                return toCompanyNames(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(String.format(errorMessage, companyName));
        }
        return null;
    }

    /**
     * Converts retrieved information into a company
     *
     * @param resultSet result set from database
     * @return a Company object containing the information retrieved from the database.
     * @throws SQLException
     */
    private Company toCompanyNames(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setCompanyId(resultSet.getInt("company_id"));
        company.setName(resultSet.getString("name"));
        return company;
    }

    /**
     * Converts retrieved information into a company
     *
     * @param resultSet result set from database
     * @return a Company object containing the information retrieved from the database.
     * @throws SQLException
     */
    private Company toCompany(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setCompanyId(resultSet.getInt("company_id"));
        company.setName(resultSet.getString("name"));
        company.setValue(resultSet.getFloat("price"));
        return company;
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