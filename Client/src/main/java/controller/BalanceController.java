package controller;

import model.entities.AuthenticationInfo;
import model.entities.StockManager;
import model.entities.TunnelObject;
import model.entities.UserProfileInfo;
import network.NetworkManager;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BalanceController implements ActionListener {
    private MainView view;
    private StockManager model;

    public BalanceController(MainView view, StockManager model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("load")) {
            String amountStr = view.getBalanceAmount().replace("$", "");
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

    /**
     * Updates the new total balance of the user
     *
     * @param totalBalance new total balance
     */
    public void updateTotalBalance(float totalBalance) {
        view.updateTotalBalance(totalBalance);
    }
}