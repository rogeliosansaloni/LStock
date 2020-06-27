package utils;

import model.entities.*;
import utils.mappers.CompanyMapper;

import java.util.ArrayList;

/**
 * Implementation of CompanyMapper
 */
public class CompanyMapperImpl implements CompanyMapper {
    /**
     * Converts CompanyList DTO to a list of Companies
     * @param companyList CompanyList DTO
     * @return a list of Companies
     */
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

    /**
     * Converts a list of Companies to CompanyList DTO
     * @param companies a list of Companies
     * @return CompanyList DTO
     */
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

    /**
     * Converts a CompanyChangeList DTO to a list of CompanyChange
     * @param companyChangeList CompanyChangeList DTO
     * @return a list of CompanyChange
     */
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

    /**
     * Converts a list of companies to CompanyChangeList DTO
     * @param companiesChange a list of Companies
     * @return CompanyChangeList DTO
     */
    @Override
    public CompanyChangeList convertToCompanyChangeList(ArrayList<CompanyChange> companiesChange) {
        CompanyChangeList companyChangeList = new CompanyChangeList(companiesChange.size());
        int i = 0;
        for (CompanyChange c : companiesChange) {
            companyChangeList.setCompanyId(i, c.getCompanyId());
            companyChangeList.setCompanyName(i, c.getName());
            companyChangeList.setCompanyChange(i, c.getChange());
            companyChangeList.setCompanyChangePer(i, c.getChangePer());
            i++;
        }
        return companyChangeList;
    }

    @Override
    public ArrayList<ArrayList<CompanyDetail>> converToCompanyDetails(ArrayList<CompanyDetailList> companyDetailList) {
        ArrayList<ArrayList<CompanyDetail>> companiesDetails = new ArrayList<ArrayList<CompanyDetail>>();
        for(int i=0; i< companyDetailList.size(); i++){
            ArrayList<CompanyDetail> companies = new ArrayList<CompanyDetail>();
            int companiesLen = companyDetailList.get(i).getValueClose().length;
            int numUserShares = companyDetailList.get(i).getNumUserShares();
            int companyId = companyDetailList.get(i).getCompanyId();
            String companyName = companyDetailList.get(i).getCompanyName();
            int[] shareIdOpen = companyDetailList.get(i).getShareIdOpen();
            float[] valueOpen = companyDetailList.get(i).getValueOpen();
            int[] shareIdClose = companyDetailList.get(i).getShareIdClose();
            float[] valueClose = companyDetailList.get(i).getValueClose();
            float[] maxValue = companyDetailList.get(i).getMaxValue();
            float[] minValue = companyDetailList.get(i).getMinValue();

            int[] minutesBefore = companyDetailList.get(i).getMinutesBefore();
            for (int j = 0; j < companiesLen; j++) {
                companies.add(new CompanyDetail(numUserShares, companyId, companyName, shareIdOpen[j], valueOpen[j], shareIdClose[j], valueClose[j], maxValue[j], minValue[j], minutesBefore[j]));
            }
            companiesDetails.add(companies);
        }

        return companiesDetails;
    }

    @Override
    public ArrayList<CompanyDetailList> convertToCompanyDetailList(ArrayList<ArrayList<CompanyDetail>> companyDetails) {
        ArrayList<CompanyDetailList> companiesDetails = new ArrayList<CompanyDetailList>();
        for(int n=0; n<companyDetails.size(); n++){
            CompanyDetailList companyDetailList = new CompanyDetailList(companyDetails.size());
            companyDetailList.setNumUserShares(companyDetails.get(n).get(0).getNumUserShares());
            companyDetailList.setCompanyId(companyDetails.get(n).get(0).getCompanyId());
            companyDetailList.setCompanyName(companyDetails.get(n).get(0).getCompanyName());
            int i = 0;
            for (CompanyDetail c : companyDetails.get(n)) {
                companyDetailList.setShareIdOpen(i, c.getShareIdOpen());
                companyDetailList.setValueOpen(i, c.getValueOpen());
                companyDetailList.setShareIdClose(i, c.getShareIdClose());
                companyDetailList.setValueClose(i, c.getValueClose());
                companyDetailList.setMaxValue(i, c.getMaxValue());
                companyDetailList.setMinValue(i, c.getMinValue());
                companyDetailList.setMinutesBefore(i, c.getMinutesBefore());
                i++;
            }
            companiesDetails.add(companyDetailList);
        }
        return companiesDetails;
    }
}
