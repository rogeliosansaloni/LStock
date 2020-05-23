package controller;

import model.entities.*;
import network.NetworkManager;
import view.CompanyView;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class CompanyController implements ActionListener {

    private CompanyView view;
    private StockManager model;

    public CompanyController(CompanyView view, StockManager model) {
        this.view = view;
        this.model = model;
    }

    /**
     * The controller of the CompanyView. Depending on the company that has been selected,
     * it will show the corresponding CompanyDetailView.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int companyId = Integer.parseInt(e.getActionCommand());
        sendUserShares(companyId);
    }

    /**
     * Proc that shows the companies on the view's table
     */
    public void updateCompanyList() {
        this.view.showCompanies(model.getCompaniesChange());
        view.registerController(this, model.getCompaniesChange());
    }

    /**
     * Updates the model
     *
     * @param model new model updated
     */
    public void updateModel(StockManager model) {
        this.model = model;
    }

    /**
     * Sends a UserShares class to change into the CompanyDetailView
     */
    public void sendUserShares(int companyId) {
        TunnelObject userShares = new UserShares(model.getUser().getUserId(), companyId);
        try {
            NetworkManager.getInstance().sendUserShares(userShares);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
