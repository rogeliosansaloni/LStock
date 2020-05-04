package controller;

import model.entities.Company;
import model.entities.ShareTrade;
import model.entities.User;
import network.NetworkManager;
import view.CompanyDetailView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CompanyDetailController implements ActionListener {
    private CompanyDetailView companyDetailView;
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("buyShare")) {
            /* TODO: Handle buying of shares
            1. Check if the user has enough money
            2. If the user doesn't have enough money, show error
            3. If the user has enough money, send the petition to update ddbb
             */
            // Send petition to buy shares
            //Mirar si tiene dinero
           // if() {
                ShareTrade shareTrade = new ShareTrade(1, 0.01f, "buy");
                try {
                    NetworkManager.getInstance().sendShareTrade(shareTrade);
                } catch (IOException e) {
                    e.printStackTrace();
                }
           //}{
                companyDetailView.showErrorMessage("Don't have enough balance");
                //}
        }
        if (evt.getActionCommand().equals("sellShare")) {
            // TODO: Handle selling of shares
            String message = "Do you really want to sell your shares?";
            if(companyDetailView.confirmAction(message) == 1){
                //companyDetailView.updateNumberShares();
            }
        }
    }

    public void updateShares(Company company, User user) {

    }
}
