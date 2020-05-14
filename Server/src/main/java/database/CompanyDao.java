package database;


import model.entities.Company;
import model.entities.CompanyChange;
import model.entities.CompanyDetail;

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
     * @return ArrayList<Company> with the id, name and value
     */
    public ArrayList<Company> getAllCompanies() {
        ResultSet retrieved = dbConnector.selectQuery("SELECT c.name as name, s1.price as current_share, (s1.price - s2.price) as share_change, ((s1.price - s2.price)/s1.price)*100 as change_per\n" +
                "FROM Company as c LEFT JOIN Share as s1 ON c.company_id = s1.company_id RIGHT JOIN Share as s2 ON s2.company_id = c.company_id\n" +
                "WHERE s1.time = (SELECT MAX(s3.time) \n" +
                "FROM Share as s3 \n" +
                "WHERE s3.company_id = s1.company_id)\n" +
                "AND s2.time = (SELECT MAX(s4.time) \n" +
                "FROM Share as s4 \n" +
                "WHERE s4.company_id = s2.company_id \n" +
                "AND s4.time <= ADDDATE(NOW(), INTERVAL -5 MINUTE));");
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
     * Gets all the companies in the LStock, with their name, current share price and
     * the difference between the current price and the one that had 5 minutes ago
     *
     * @return ArrayList<CompanyChange> an list of the information mentioned before
     */

    public ArrayList<CompanyChange> getCompaniesChange() {
        ResultSet retrieved = dbConnector.selectQuery("CALL getCompaniesChange();");
        ArrayList<CompanyChange> companies = null;
        try {
            companies = new ArrayList<CompanyChange>();
            while (retrieved.next()) {
                companies.add(toCompanyChange(retrieved));
            }
        } catch (SQLException e) {
            System.out.println(GETTING_COMPANIES_ERROR);
        }
        return companies;
    }

    /**
     * Gets the value of the share of an specific company the last 10 minutes
     * before the last change in the value of the share
     *
     * @return ArrayList<CompanyChange> an list of the information mentioned before
     */

    public ArrayList<CompanyDetail> getCompanyDetails(int userId, int companyId) {
        ArrayList<CompanyDetail> companies = new ArrayList<CompanyDetail>();
        int numShares = 0;

        ResultSet retrievedShares = dbConnector.selectQuery("CALL getNumShares(" + userId + ", " + companyId + ");");
        try {
            if(retrievedShares.next() != false){
                numShares = retrievedShares.getInt("numShares");
            }
        } catch (SQLException e) {
            System.out.println(GETTING_COMPANIES_ERROR);
        }

        for(int i=0; i<10; i++){
            ResultSet retrieved = dbConnector.selectQuery("CALL getCompanyDetails(" + i + ", " + companyId + ");");
            try {
                if(retrieved.next() == false){
                    ResultSet retrievedCompanyName = dbConnector.selectQuery("SELECT c.name as companyName FROM Company as c WHERE c.company_id = 5;");
                    retrievedCompanyName.next();
                    companies.add(new CompanyDetail(numShares, 5, retrievedCompanyName.getString("companyName"), -1, -1, -1, -1,-1, -1, i));
                }else{
                    retrieved.beforeFirst();
                    while (retrieved.next()) {
                        companies.add(toCompanyDetail(numShares, retrieved));
                    }
                    ResultSet retrievedMaxMin = dbConnector.selectQuery("CALL getMaxMinValues(" + i + ", " + companyId + ");");
                    while (retrievedMaxMin.next()) {
                        companies.set(i, extractMaxMinDetail(retrievedMaxMin, companies.get(i)));
                    }
                }
            } catch (SQLException e) {
                System.out.println(GETTING_COMPANIES_ERROR);
            }

        }
        return companies;
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
     * Converts retrieved information into a companyChange
     *
     * @param resultSet result set from database
     * @return a CompanyChange object containing the information retrieved from the database.
     * @throws SQLException
     */

    private CompanyChange toCompanyChange(ResultSet resultSet) throws SQLException {
        CompanyChange companyChange = new CompanyChange();
        companyChange.setCompanyId(resultSet.getInt("companyId"));
        companyChange.setName(resultSet.getString("name"));
        companyChange.setCurrentShare(resultSet.getFloat("current_share"));
        companyChange.setChange(resultSet.getFloat("share_change"));
        companyChange.setChangePer(resultSet.getFloat("change_per"));
        return companyChange;
    }

    private CompanyDetail toCompanyDetail(int  numShares, ResultSet resultSet) throws SQLException {
        CompanyDetail companyDetail = new CompanyDetail();
        companyDetail.setNumShares(numShares);
        companyDetail.setCompanyId(resultSet.getInt("companyId"));
        companyDetail.setCompanyName(resultSet.getString("name"));
        companyDetail.setShareIdOpen(resultSet.getInt("shareIdOpen"));
        companyDetail.setValueOpen(resultSet.getFloat("valueOpen"));
        companyDetail.setShareIdClose(resultSet.getInt("shareIdClose"));
        companyDetail.setValueClose(resultSet.getFloat("valueClose"));
        companyDetail.setValueOpen(resultSet.getFloat("valueOpen"));
        companyDetail.setMinutesBefore(resultSet.getInt("minutesBefore"));
        return companyDetail;
    }

    private CompanyDetail extractMaxMinDetail(ResultSet retrievedMaxMin, CompanyDetail companyDetail) throws SQLException {
        companyDetail.setMaxValue(retrievedMaxMin.getFloat("maximumValue"));
        companyDetail.setMinValue(retrievedMaxMin.getFloat("minimumValue"));
        if(companyDetail.getValueOpen() > companyDetail.getMaxValue()){
            companyDetail.setMaxValue(companyDetail.getValueOpen());
        }
        if(companyDetail.getValueOpen() < companyDetail.getMinValue()){
            companyDetail.setMinValue(companyDetail.getValueOpen());
        }
        return companyDetail;
    }

    /**
     * Gets all the information of a company
     *
     * @param companyName Company where will get the information from.
     * @return a Company object that contains all the information of a specific company
     */
    public Company getCompanyByName(String companyName) {
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM Company WHERE name LIKE '%" + companyName + "%';");
        Company companyData = new Company();

        try {
            while (verify.next()) {
                if (companyName.equals(verify.getObject("name").toString())) {
                    companyData = toCompanyNames(verify);
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
    public void updateCompanyNewValue (Company company) {
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