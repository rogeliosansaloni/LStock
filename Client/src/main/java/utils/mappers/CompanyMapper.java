package utils.mappers;

import model.entities.*;

import java.util.ArrayList;

/**
 * Mapper interface for Company
 */
public interface CompanyMapper {
    /**
     * Converts retrieved information into a Company ArrayList
     * @param companyList CompanyList data
     * @return a Company ArrayList containing the information retrieved.
     */
    ArrayList<Company> convertToCompanies(CompanyList companyList);

    /**
     * Converts retrieved information into a CompanyList
     * @param companies ArrayList of Company
     * @return a Company ArrayList containing the information retrieved.
     */
    CompanyList convertToCompanyList(ArrayList<Company> companies);

    /**
     * Converts retrieved information into a CompanyChange ArrayList
     * @param companyChangeList CompanyChangeList data
     * @return a CompanyChange ArrayList containing the information retrieved.
     */
    ArrayList<CompanyChange> convertToCompaniesChange(CompanyChangeList companyChangeList);

    /**
     * Converts retrieved information into a CompanyChangeList
     * @param companiesChange CompanyChange ArrayList
     * @return a CompanyChangeList object containing the information retrieved.
     */
    CompanyChangeList convertToCompanyChangeList(ArrayList<CompanyChange> companiesChange);

    ArrayList<CompanyDetail> converToCompanyDetails(CompanyDetailList companyDetailList);

    CompanyDetailList convertToCompanyDetailList(ArrayList<CompanyDetail> companyDetails);

    /**
     * Converts retrieved information into a CompanyDetail ArrayList
     * @param companyDetailList CompanyDetailList data
     * @return a CompanyDetail ArrayList containing the information retrieved.
     */
    ArrayList<ArrayList<CompanyDetail>> converToCompanyDetailsUpdate(ArrayList<CompanyDetailList> companyDetailList);

    /**
     * Converts retrieved information into a CompanyDetailList
     * @param companyDetails CompanyDetail ArrayList
     * @return a CompanyDetailList object containing the information retrieved.
     */
    ArrayList<CompanyDetailList> convertToCompanyDetailListUpdate(ArrayList<ArrayList<CompanyDetail>> companyDetails);
}
