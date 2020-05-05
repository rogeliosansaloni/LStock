package controller;

import model.entities.StockManager;
import view.LoginView;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public MainController(MainView view, StockManager model, LoginView loginView) {
        this.view = view;
        this.balanceController = new BalanceController(view, model);
        this.companyController = new CompanyController(view);
        this.balanceController = new BalanceController(view, model);
        this.companyDetailController = new CompanyDetailController();
        this.loginView = loginView;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "company":
                view.updateView(CARD_COMPANY);
                break;
            case "profile":
                view.updateView(CARD_PROFILE);
                //TODO: Profile
                break;
            case "shares":
                view.updateView(CARD_SHARES);
                //TODO: Shares
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

    public CompanyDetailController getCompanyDetailController() {
        return companyDetailController;
    }

    public BalanceController getBalanceController(StockManager model) {
        this.model = model;
        return balanceController;
    }

    public CompanyController getCompanyController() {
        return companyController;
    }

}
