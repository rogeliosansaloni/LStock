package controller;

import model.entities.*;
import network.NetworkManager;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

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
                String textfieldText = view.getNumSharesTextfield();
                int numShares = checkInteger(textfieldText);
                if(numShares > 0){
                    sendShareTrade(numShares);
                } else {
                    view.showErrorCompanyDetail(2);
                }
            }
        }
        if (e.getActionCommand().equals("sellShare")) {
            if (view.confirmAction(CONFIRM_SELL_ACTION) == CONFIRMED) {
                //Check if the user has enough money
                String textfieldText = view.getNumSharesTextfield();
                int numShares = checkInteger(textfieldText);
                if(numShares > 0){
                    sendShareTrade(numShares);
                } else {
                    view.showErrorCompanyDetail(2);
                }
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

    public void updateCompanyDetailView(ArrayList<CompanyDetail> companyDetails) {
        view.updateCompanyDetailView(companyDetails);
    }

    public int checkInteger(String text){
        try{
            int numShares = Integer.parseInt(text);
            if(numShares < 0){
                view.showErrorCompanyDetail(1);
                return -1;
            }
            return numShares;
        }
        catch(NumberFormatException e){
            view.showErrorCompanyDetail(1);
            return -1;
        }
    }

    public void sendShareTrade(int numShares){
        float userBalance = model.checkUserBalance(numShares);
        int userId = model.getUser().getUserId();
        int companyId = model.getCompanyDetailId();
        int shareId = model.getCurrentShareId();
        float currentShareValue = model.getCurrentShareValue();
        ShareTrade shareTrade = new ShareTrade(userId, userBalance, companyId, shareId, currentShareValue, BUY_ACTION);
        try {
            NetworkManager.getInstance().sendShareTrade(shareTrade);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}