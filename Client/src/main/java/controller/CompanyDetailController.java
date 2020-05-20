package controller;

import model.entities.*;
import network.NetworkManager;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CompanyDetailController implements ActionListener {
    private static final String CONFIRM_BUY_ACTION = "Do you want to buy this share?";
    private static final String CONFIRM_SELL_ACTION = "Do you want to sell your share?";
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
            String textfieldText = view.getNumSharesBuy();
            //Check if the text introduced is an integer
            int numShares = checkInteger(textfieldText);
            if(numShares > 0){
                //Check if the user has enough money
                float userBalance = model.checkUserBalance(numShares);
                if(userBalance > 0){
                    //If user confirms to buy the share
                    if (view.confirmAction(CONFIRM_BUY_ACTION) == CONFIRMED) {
                        sendShareTradeBuy(numShares, userBalance, BUY_ACTION);
                    }
                } else{
                    view.showErrorCompanyDetail(2);
                }
            } else{
                view.showErrorCompanyDetail(1);
            }

        }
        if (e.getActionCommand().equals("sellShare")) {
            String[] textShareSells = view.getNumSharesSell();
            //Check if the text introduced is an integer
            int[] numShares = checkAllFields(textShareSells);
            if(numShares != null){
                float userBalance = model.checkNumUserShares(numShares);
                if(userBalance > 0){
                    if (view.confirmAction(CONFIRM_SELL_ACTION) == CONFIRMED) {
                        sendShareTradeSell(numShares, userBalance, SELL_ACTION);
                    }
                } else{
                    view.showErrorCompanyDetail(4);
                }
            } else {
                view.showErrorCompanyDetail(3);
            }

        }
    }

    public int checkInteger(String text){
        if(text.equals("")){
            return -2;
        }else{
            try{
                int numShares = Integer.parseInt(text);
                if(numShares <= 0){
                    return -1;
                }
                return numShares;
            }
            catch(NumberFormatException e){
                return -1;
            }
        }
    }

    public int[] checkAllFields(String[] valuesString){
        int value;
        int[] valuesInt = new int[valuesString.length];
        for(int i=0; i<valuesString.length; i++){
            value = checkInteger(valuesString[i]);
            if(value == -1){
                return null;
            } else {
                valuesInt[i] = value;
            }
        }
        return valuesInt;
    }

    public void sendShareTradeBuy(int numShares, float userBalance, String action){
        int userId = model.getUser().getUserId();
        int companyId = model.getCompanyDetailId();
        int shareId = model.getCurrentShareId();
        float currentShareValue = model.getCurrentShareValue();
        ShareTrade shareTrade = new ShareTrade(userId, userBalance, companyId, shareId, currentShareValue, numShares,  action);
        try {
            NetworkManager.getInstance().sendShareTrade(shareTrade);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void sendShareTradeSell(int[] numShares, float userBalance, String action){
        int userId = model.getUser().getUserId();
        int companyId = model.getCompanyDetailId();
        int[] shareId = model.getSharesSellSharesId();
        float[] shareValue = model.getSharesSellSharesValue();
        ShareTrade shareTrade = new ShareTrade(userId, userBalance, companyId, shareId, shareValue, numShares,  action);
        try {
            NetworkManager.getInstance().sendShareTrade(shareTrade);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}