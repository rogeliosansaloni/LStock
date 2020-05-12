package utils.mappers;

import model.entities.Company;
import model.entities.CompanyChange;
import model.entities.CompanyList;
import model.entities.CompanyChangeList;

import java.util.ArrayList;

public interface CompanyMapper {
    ArrayList<Company> convertToCompanies(CompanyList companyList);
    CompanyList convertToCompanyList(ArrayList<Company> companies);
    ArrayList<CompanyChange> convertToCompaniesChange(CompanyChangeList companyChangeList);
    CompanyChangeList convertToCompanyChangeList(ArrayList<CompanyChange> companiesChange);
}
