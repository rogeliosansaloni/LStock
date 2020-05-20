package controller;

import model.entities.ShareChangeList;
import model.entities.StockManager;
import model.entities.TunnelObject;
import network.NetworkManager;
import view.LoginView;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainController implements ActionListener {
    private static final String CARD_COMPANY = "Companies";
    private static final String CARD_PROFILE = "My Profile";
    private static final String CARD_SHARES = "Shares";
    private static final String CARD_BALANCE = "Load Balance";
    private final MainView view;
    private final LoginView loginView;
    private StockManager model;
    private CompanyDetailController companyDetailController;
    private BalanceController balanceController;
    private CompanyController companyController;
    private SharesController sharesController;

    public MainController(MainView view, StockManager model, LoginView loginView) {
        this.view = view;
        this.loginView = loginView;
        this.model = model;
        this.balanceController = new BalanceController(view.getBalanceView(), model);
        this.companyController = new CompanyController(view, model);
        this.companyDetailController = new CompanyDetailController(view, model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "company":
                updateCompanyList();
                view.updateView(CARD_COMPANY);
                break;
            case "profile":
                view.updateView(CARD_PROFILE);
                //TODO: Profile
                break;
            case "shares":
                view.updateView(CARD_SHARES);
                sendSharesChange();
                break;
            case "load":
                view.updateView(CARD_BALANCE);
                //TODO: Load Balance
                break;
            case "logout":
                if(view.confirmLogOutWindow() == 0){
                    loginView.setVisible(true);
                    view.setVisible(false);
                }
                break;
        }
    }

    /**
     * Returns the company detail controller of the CompanyDetailsView
     *
     * @return the company detail controller
     */
    public CompanyDetailController getCompanyDetailController() {
        return companyDetailController;
    }

    /**
     * Returns the balance controller of the BalanceView
     *
     * @return balance controller
     */
    public BalanceController getBalanceController() {
        return balanceController;
    }

    public CompanyController getCompanyController() {
        return companyController;
    }

    public SharesController getSharesController() {
        return sharesController;
    }

    /**
     * Updates the new total balance of the user
     *
     * @param totalBalance new total balance
     */
    public void updateTotalBalance (float totalBalance) {
        model.getUser().setTotalBalance(totalBalance);
        view.updateTotalBalance(totalBalance);
    }

    public void updateCompanyList () {
        companyController.updateCompanyList(model.getCompaniesChange());
    }

    /**
     * Updates company and users value in the view
     *
     * @param totalBalance the new balance of the user
     * @param value the new value of the company
     */
    public void updateCompanyUserValueAndBalance (float totalBalance, float value) {
        //TODO: Update company in the model
        companyDetailController.updateCompanyUserValueAndBalance(totalBalance, value);
    }

    /**
     * Updates the shares table in the SharesView
     */
    public void updateShareView () {
        view.getSharesView().updateSharesView(model.getSharesChange());
        view.registerSharesController(sharesController, model.getSharesChange());
    }

    public void sendSharesChange(){
        TunnelObject info = new ShareChangeList();
        ((ShareChangeList) info).setUserId(model.getUser().getUserId());
        try {
            NetworkManager.getInstance().sendShareChange(info);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
