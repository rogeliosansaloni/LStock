package controller;

import model.entities.Company;
import model.entities.ShareTrade;
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
    private static final int CONFIRMED = 0;
    private static final int NOT_CONFIRMED = 1;

    private MainView view;

    public CompanyDetailController (MainView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("buyShare")) {
            if (view.confirmAction(CONFIRM_BUY_ACTION) == CONFIRMED) {
                System.out.println("Buy shares");
            }

            /* TODO: Handle buying of shares
            1. Check if the user has enough money
            2. If the user doesn't have enough money, show error
            3. If the user has enough money, send the petition to update ddbb
             */
            // Send petition to buy shares
            //Mirar si tiene dinero
           // if() {
               /* ShareTrade shareTrade = new ShareTrade(1, 0.01f, "buy");
                try {
                    NetworkManager.getInstance().sendShareTrade(shareTrade);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
           //}{
                companyDetailView.showErrorMessage("Don't have enough balance");*/
                //}
        }
        if (e.getActionCommand().equals("sellShare")) {
            // TODO: Handle selling of shares
            String message = "Do you really want to sell your shares?";
            if(view.confirmAction(message) == CONFIRMED){
                //companyDetailView.updateNumberShares();
            }
        }
    }

    public void updateShares(Company company, User user) {

    }
}
