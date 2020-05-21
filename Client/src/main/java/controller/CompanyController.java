package controller;

import model.entities.CompanyChange;
import model.entities.StockManager;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.entities.Company;
import java.util.ArrayList;


public class CompanyController implements ActionListener {

    private MainView view;
    private StockManager model;

    public CompanyController(MainView view, StockManager model) {
        this.view = view;
        this.model = model;
    }
    /**
     * The controller of the CompanyView. Depending on the company that has been selected,
     * it will show the corresponding CompanyDetaiñView.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int companyId = Integer.parseInt(e.getActionCommand());


    }
    /**
     * Proc that shows the companies on the view's table
     *
     * @param companies that contains the list of companies
     */
    public void updateCompanyList (ArrayList<CompanyChange> companies){
        this.view.updateCompanyList(companies);
    }
}
