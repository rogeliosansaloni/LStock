package utils.mappers;

import model.entities.Company;
import model.entities.CompanyList;

import java.util.ArrayList;

public interface CompanyMapper {
    ArrayList<Company> convertToCompanies(CompanyList companyList);
    CompanyList convertToCompanyList(ArrayList<Company> companies);
}
