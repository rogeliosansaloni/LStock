package utils.mappers;

import model.entities.*;

import java.util.ArrayList;

/**
 * Mapper interface for Company
 */
public interface CompanyMapper {
    ArrayList<Company> convertToCompanies(CompanyList companyList);

    CompanyList convertToCompanyList(ArrayList<Company> companies);

    ArrayList<CompanyChange> convertToCompaniesChange(CompanyChangeList companyChangeList);

    CompanyChangeList convertToCompanyChangeList(ArrayList<CompanyChange> companiesChange);

    ArrayList<CompanyDetail> converToCompanyDetails(CompanyDetailList companyDetailList);

    CompanyDetailList convertToCompanyDetailList(ArrayList<CompanyDetail> companyDetails);
}
