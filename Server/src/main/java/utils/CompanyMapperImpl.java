package utils;

import model.entities.Company;
import model.entities.CompanyList;
import utils.mappers.CompanyMapper;

import java.util.ArrayList;

public class CompanyMapperImpl implements CompanyMapper {
    @Override
    public ArrayList<Company> convertToCompanies(CompanyList companyList) {
        ArrayList<Company> companies = new ArrayList<Company>();
        int companiesLen = companyList.getCompanyId().length;
        int[] ids = companyList.getCompanyId();
        String[] names = companyList.getCompanyName();
        float[] values = companyList.getCompanyValue();
        int[] shares = companyList.getCompanyShares();
        for(int i = 0; i < companiesLen; i++) {
            companies.add(new Company(ids[i], names[i], values[i], shares[i]));
        }
        return companies;
    }

    @Override
    public CompanyList convertToCompanyList(ArrayList<Company> companies) {
        return null;
    }
}
