package controller;

import model.entities.AuthenticationInfo;
import model.entities.StockManager;
import model.entities.TunnelObject;
import model.entities.UserProfileInfo;
import network.NetworkManager;
import view.BalanceView;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BalanceController implements ActionListener {
    private BalanceView view;
    private StockManager model;

    public BalanceController(BalanceView view, StockManager model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("load")) {
            String amountStr = view.getAmount().replace("$", "");
            float newAmount = Float.parseFloat(amountStr);
            int userId = model.getUser().getUserId();
            TunnelObject info = new UserProfileInfo(userId, newAmount, "balance");
            try {
                NetworkManager.getInstance().sendUserProfileInfo(info);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}