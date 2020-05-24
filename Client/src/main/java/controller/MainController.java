package controller;

import model.entities.ShareChangeList;
import model.entities.CompanyChangeList;
import model.entities.StockManager;
import model.entities.TunnelObject;
import model.entities.TunnelObject;
import model.entities.UserProfileInfo;
import network.NetworkManager;
import view.LoginView;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Main controller of the client different views
 */
public class MainController implements ActionListener {
    private static final String CARD_COMPANY = "Companies";
    private static final String CARD_PROFILE = "My Profile";
    private static final String CARD_SHARES = "Shares";
    private static final String CARD_BALANCE = "Load Balance";
    private static final String CARD_COMPANYDETAILS = "Company Details";
    private final MainView view;
    private final LoginView loginView;
    private StockManager model;
    private CompanyDetailController companyDetailController;
    private BalanceController balanceController;
    private CompanyController companyController;
    private SharesController sharesController;

    /**
     * Creates and initializes the controller and views
     * @param view Main view
     * @param model StockManager
     * @param loginView Login view
     */
    public MainController(MainView view, StockManager model, LoginView loginView) {
        this.view = view;
        this.loginView = loginView;
        this.model = model;
        this.balanceController = new BalanceController(view.getBalanceView(), model);
        this.balanceController.registerController();
        this.companyController = new CompanyController(view.getCompanyView(), model, view);
        this.companyDetailController = new CompanyDetailController(view, view.getCompanyDetailsView(), model);
        this.companyDetailController.registerController();
        this.sharesController = new SharesController(view.getSharesView(), model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "company":
                sendCompaniesChange();
                view.updateView(CARD_COMPANY);
                break;
            case "profile":
                sendUserProfileInfo();
                view.updateView(CARD_PROFILE);
                break;
            case "shares":
                sendSharesChange();
                view.updateView(CARD_SHARES);
                break;
            case "load":
                view.updateView(CARD_BALANCE);
                break;
            case "logout":
                if(view.confirmLogOutWindow() == 0){
                    loginView.setVisible(true);
                    view.setVisible(false);
                    view.updateView(CARD_COMPANY);
                }
                break;
        }
    }

    /**
     * Returns the balance controller of the BalanceView
     *
     * @return the balance controller
     */
    public BalanceController getBalanceController() {
        return balanceController;
    }

    /**
     * Returns the company controller of the CompanyView
     *
     * @return company controller
     */
    public CompanyController getCompanyController() {
        return companyController;
    }

    /**
     * Updates the model
     *
     * @param model new model updated
     */
    public void updateModel (StockManager model) {
        this.model = model;
        companyController.updateModel(this.model);
        companyDetailController.updateModel(this.model);
        sharesController.updateModel(this.model);
    }

    public void updateTotalBalance (float totalBalance) {
        model.getUser().setTotalBalance(totalBalance);
        view.updateTotalBalance(totalBalance);
    }

    /**
     * Updates the company list with the database information
     */
    public void updateCompanyList () {
        companyController.updateCompanyList();
    }

    /**
     * Updates the company list with the database information
     */
    public void startAtCompanyList () {
        companyController.updateCompanyList();
        view.updateView(CARD_COMPANY);
    }

    /**
     * Updates the CompanyDetailView depending on the values received from the database
     */
    public void updateCompanyDetails () {
        companyDetailController.updateCompanyDetailView();
        view.setTitleCompanyDetail(model.getCurrentShareValue(), model.getCompanyDetailName());
    }

    /**
     * Updates the profilw view with the database information
     */
    public void updateProfileView () {
        view.updateProfileView(model.getUser());
    }

    /**
     * Updates the shares table in the SharesView
     */
    public void updateShareView () {
        sharesController.updateSharesView();
    }

    /**
     * Sends a SharesChangeList class when the user presses "Shares" option in the menu bar
     */
    public void sendSharesChange(){
        TunnelObject info = new ShareChangeList();
        ((ShareChangeList) info).setUserId(model.getUser().getUserId());
        try {
            NetworkManager.getInstance().sendShareChange(info);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Sends a CompaniesChangeList class when the user presses "Companies list" option in the menu bar
     */
    public void sendCompaniesChange(){
        try {
            NetworkManager.getInstance().sendTunnelObject(new CompanyChangeList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Sends a ProfileInfo class when the user presses "My Profile" option in the menu bar
     */
    public void sendUserProfileInfo(){
        int userId = model.getUser().getUserId();
        TunnelObject info = new UserProfileInfo(userId, "profileView");
        try {
            NetworkManager.getInstance().sendUserProfileInfo(info);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
