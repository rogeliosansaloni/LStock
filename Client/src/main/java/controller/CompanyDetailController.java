package controller;

import model.entities.Company;
import model.entities.ShareTrade;
import model.entities.StockManager;
import model.entities.User;
import network.NetworkManager;
import view.CompanyDetailView;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CompanyDetailController implements ActionListener {
    private static final String CONFIRM_BUY_ACTION = "Do you want to buy this share?";
    private static final String CONFIRM_SELL_ACTION = "Do you want to sell your share?";
    private static final String BALANCE_ERROR = "You don't have enough money to buy this share.";
    private static final String BUY_ACTION = "BUY";
    private static final String SELL_ACTION = "SELL";
    private static final int CONFIRMED = 0;
    private static final int NOT_CONFIRMED = 1;

    private MainView view;
    private StockManager model;

    public CompanyDetailController(MainView view, StockManager model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("buyShare")) {
            //If user confirms to buy the share
            if (view.confirmAction(CONFIRM_BUY_ACTION) == CONFIRMED) {
                //Check if the user has enough money
                float shareValue = 200;
                int companyId = 3; //stockers
                int shareId = 1;
                if (model.getUser().getTotalBalance() >= shareValue) {
                    ShareTrade shareTrade = new ShareTrade(model.getUser().getUserId(), model.getUser().getTotalBalance(), companyId, shareId, shareValue, BUY_ACTION);
                    try {
                        NetworkManager.getInstance().sendShareTrade(shareTrade);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    view.showNoEnoughBalanceErrorMessage(BALANCE_ERROR);
                }
            }
        }
        if (e.getActionCommand().equals("sellShare")) {
            // TODO: Handle selling of shares
            String message = "Do you really want to sell your shares?";
            if (view.confirmAction(message) == CONFIRMED) {
                //companyDetailView.updateNumberShares();
            }
        }
    }

    /**
     * Updates company and users value in the view
     *
     * @param totalBalance the new balance of the user
     * @param value the new value of the company
     */
    public void updateCompanyUserValueAndBalance(float totalBalance, float value) {
        view.updateCompanyUserValueAndBalance(totalBalance, value);
    }
}