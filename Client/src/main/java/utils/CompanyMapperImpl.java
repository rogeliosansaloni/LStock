package utils;

import model.entities.*;
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
        for (int i = 0; i < companiesLen; i++) {
            companies.add(new Company(ids[i], names[i], values[i], shares[i]));
        }
        return companies;
    }

    @Override
    public CompanyList convertToCompanyList(ArrayList<Company> companies) {
        CompanyList companyList = new CompanyList(companies.size());
        int i = 0;
        for (Company c : companies) {
            companyList.setCompanyId(i, c.getCompanyId());
            companyList.setCompanyName(i, c.getName());
            companyList.setCompanyValue(i, c.getValue());
            companyList.setCompanyShares(i, c.getShares());
            i++;
        }
        return companyList;
    }

    @Override
    public ArrayList<CompanyChange> convertToCompaniesChange(CompanyChangeList companyChangeList) {
        ArrayList<CompanyChange> companies = new ArrayList<CompanyChange>();
        int companiesLen = companyChangeList.getCompanyName().length;
        int[] companiesId = companyChangeList.getCompanyId();
        String[] names = companyChangeList.getCompanyName();
        float[] currentShares = companyChangeList.getCompanyCurrentShare();
        float[] sharesChange = companyChangeList.getCompanyChange();
        float[] changePer = companyChangeList.getCompanyChangePer();
        for (int i = 0; i < companiesLen; i++) {
            companies.add(new CompanyChange(companiesId[i], names[i], currentShares[i], sharesChange[i], changePer[i]));
        }
        return companies;
    }

    @Override
    public CompanyChangeList convertToCompanyChangeList(ArrayList<CompanyChange> companiesChange) {
        CompanyChangeList companyChangeList = new CompanyChangeList(companiesChange.size());
        int i = 0;
        for (CompanyChange c : companiesChange) {
            companyChangeList.setCompanyId(i, c.getCompanyId());
            companyChangeList.setCompanyName(i, c.getName());
            companyChangeList.setCompanyCurrentShare(i, c.getCurrentShare());
            companyChangeList.setCompanyChange(i, c.getChange());
            companyChangeList.setCompanyChangePer(i, c.getChangePer());
            i++;
        }
        return companyChangeList;
    }

    @Override
    public ArrayList<CompanyDetail> converToCompanyDetails(CompanyDetailList companyDetailList) {
        ArrayList<CompanyDetail> companies = new ArrayList<CompanyDetail>();
        int companiesLen = companyDetailList.getCompanyName().length;
        int[] companiesId = companyDetailList.getCompanyId();
        String[] names = companyDetailList.getCompanyName();
        float[] valueOpen = companyDetailList.getValueOpen();
        float[] valueClose = companyDetailList.getValueClose();
        int[] minutesBefore = companyDetailList.getMinutesBefore();
        for (int i = 0; i < companiesLen; i++) {
            companies.add(new CompanyDetail(companiesId[i], names[i], valueOpen[i], valueClose[i], minutesBefore[i]));
        }
        return companies;
    }

    @Override
    public CompanyDetailList convertToCompanyDetailList(ArrayList<CompanyDetail> companyDetails) {
        CompanyDetailList companyDetailList = new CompanyDetailList(companyDetails.size());
        int i = 0;
        for (CompanyDetail c : companyDetails) {
            companyDetailList.setCompanyId(i, c.getCompanyId());
            companyDetailList.setCompanyName(i, c.getCompanyName());
            companyDetailList.setValueOpen(i, c.getValueOpen());
            companyDetailList.setValueClose(i, c.getValueClose());
            companyDetailList.setMinutesBefore(i, c.getMinutesBefore());
            i++;
        }
        return companyDetailList;
    }
}
