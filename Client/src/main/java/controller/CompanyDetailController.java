package controller;

import model.entities.*;
import network.NetworkManager;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Controller for the company detail view
 */
public class CompanyDetailController implements ActionListener {
    private static final String CONFIRM_BUY_ACTION = "Do you want to buy these shares?";
    private static final String CONFIRM_SELL_ACTION = "Do you want to sell these shares?";
    private static final String CARD_COMPANY = "Companies";
    private static final String BUY_ACTION = "BUY";
    private static final String SELL_ACTION = "SELL";
    private static final int CONFIRMED = 0;
    private static final int NOT_CONFIRMED = 1;
    private MainView view;
    private StockManager model;

    /**
     * Creates and initializes the controller and the Main view
     *
     * @param view  Main view
     * @param model StockManager
     */
    public CompanyDetailController(MainView view, StockManager model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String whichButton = e.getActionCommand();
        switch (whichButton) {
            case "buyShare":
                String textfieldText = view.getNumSharesBuy();
                //Check if the text introduced is an integer
                int numShares = checkInteger(textfieldText);
                if (numShares > 0) {
                    //Check if the user has enough money
                    float userBalance = model.checkUserBalance(numShares);
                    if (userBalance > 0) {
                        //If user confirms to buy the share
                        if (view.confirmAction(CONFIRM_BUY_ACTION) == CONFIRMED) {
                            sendShareTradeBuy(numShares, userBalance);
                        }
                    } else {
                        view.showErrorCompanyDetail(2);
                    }
                } else {
                    view.showErrorCompanyDetail(1);
                }
                break;
            case "sellShare":
                String[] textShareSells = view.getNumSharesSell();
                //Check if the text introduced is an integer
                int[] numSharesSell = checkAllFields(textShareSells);
                if (numSharesSell[0] == -1) {
                    view.showErrorCompanyDetail(3);
                } else if (numSharesSell[0] == -2) {
                    view.showErrorCompanyDetail(4);
                } else {
                    float userBalance = model.checkNumUserShares(numSharesSell);
                    if (userBalance > 0) {
                        if (view.confirmAction(CONFIRM_SELL_ACTION) == CONFIRMED) {
                            sendShareTradeSell(numSharesSell, userBalance);
                        }
                    } else {
                        view.showErrorCompanyDetail(5);
                    }
                }
                break;
            case "back":
                view.updateView(CARD_COMPANY);
                break;
        }
    }

    /**
     * Checks if the number introduced in the JTextfield is an integer or not
     *
     * @param text the text introduced
     */
    private int checkInteger(String text) {
        if (text.equals("")) {
            return -2;
        } else {
            try {
                int numShares = Integer.parseInt(text);
                if (numShares <= 0) {
                    return -1;
                }
                return numShares;
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

    /**
     * Checks all the values introduced in the JTextfield of the shares sell
     *
     * @param valuesString the string array of the texts introduced
     */
    private int[] checkAllFields(String[] valuesString) {
        int totalNumShares = 0;
        int value;
        int[] valuesInt = new int[valuesString.length];
        for (int i = 0; i < valuesString.length; i++) {
            value = checkInteger(valuesString[i]);
            if (value == -1) {
                valuesInt[0] = -1;
                return valuesInt;
            } else if (value == -2) {
                valuesInt[i] = 0;
            } else {
                valuesInt[i] = value;
                totalNumShares += value;
            }
        }
        if (totalNumShares == 0) {
            valuesInt[0] = -2;
            return valuesInt;
        }
        return valuesInt;
    }

    /**
     * Sends a ShareTrade if the the buy is valid
     *
     * @param numShares   the number of sells to buy
     * @param userBalance the balance of the user updated
     */
    public void sendShareTradeBuy(int numShares, float userBalance) {
        int userId = model.getUser().getUserId();
        int companyId = model.getCompanyDetailId();
        int shareId = model.getCurrentShareId();
        float currentShareValue = model.getCurrentShareValue();
        ShareTrade shareTrade = new ShareTrade(userId, userBalance, companyId, shareId, currentShareValue, numShares, BUY_ACTION);
        try {
            NetworkManager.getInstance().sendShareTrade(shareTrade);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Sends a ShareTrade if the the sell is valid
     *
     * @param numShares   the array of all the shares that the user wants to sell
     * @param userBalance the balance of the user updated
     */
    public void sendShareTradeSell(int[] numShares, float userBalance) {
        int userId = model.getUser().getUserId();
        int companyId = model.getCompanyDetailId();
        int[] shareId = model.getSharesSellSharesId();
        float[] shareValue = model.getSharesSellSharesValue();
        ShareTrade shareTrade = new ShareTrade(userId, userBalance, companyId, shareId, shareValue, numShares, SELL_ACTION);
        try {
            NetworkManager.getInstance().sendShareTrade(shareTrade);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}