package model.entities;

import java.util.ArrayList;

public class StockManager {
    private User user;
    private Company company;
    private ArrayList<Company> companies;

    private ArrayList<CompanyChange> companiesChange;


    public StockManager(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public ArrayList<CompanyChange> getCompaniesChange() {
        return companiesChange;
    }

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }

    public void setCompaniesChange(ArrayList<CompanyChange> companiesChange) {
        this.companiesChange = companiesChange;
    }
}
