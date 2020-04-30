package controller;

import model.entities.Company;
import model.entities.ShareTrade;
import model.entities.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanyDetailController implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("buyShare")) {
            // TODO: Handle buying of shares
            // Send petition to buy shares
            ShareTrade shareTrade = new ShareTrade();
        }
        if (e.getActionCommand().equals("sellShare")) {
            // TODO: Handle selling of shares
        }
    }

    public void updateShares(Company company, User user) {

    }
}
