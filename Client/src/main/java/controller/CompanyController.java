package controller;

import model.entities.*;
import network.NetworkManager;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
     * it will show the corresponding CompanyDetailView.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TunnelObject companyId = new CompanyInfo(Integer.parseInt(e.getActionCommand()));
        try {
            NetworkManager.getInstance().sendCompanyDetails(companyId);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
