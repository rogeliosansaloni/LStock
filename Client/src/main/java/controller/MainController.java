package controller;

import model.entities.StockManager;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    private static final String CARD_COMPANY = "Companies";
    private static final String CARD_PROFILE = "My Profile";
    private static final String CARD_SHARES = "Shares";
    private static final String CARD_BALANCE = "Load Balance";
    private final MainView view;
    private StockManager model;
    private BalanceController balanceController;

    public MainController(MainView view, StockManager model) {
        this.view = view;
        this.model = model;
        this.balanceController = new BalanceController(view, model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
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
                //TODO: Log out
                break;

        }
    }

    public BalanceController getBalanceController() {
        this.balanceController.setModel(model);
        return balanceController;
    }

    public void setModel (StockManager model) {
        this.model = model;
    }
}
