package controller;

import model.entities.StockManager;
import model.entities.TunnelObject;
import model.entities.UserProfileInfo;
import network.NetworkManager;
import view.BalanceView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Controller for the balance view
 */
public class BalanceController implements ActionListener {
    private final static String LOAD = "Load";
    private BalanceView view;
    private StockManager model;

    /**
     * Creates and initializes the controller and the Balance view
     * @param view Balance view
     * @param model StockManager
     */
    public BalanceController(BalanceView view, StockManager model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(LOAD)) {
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

    /**
     * Refreshes the balance view
     */
    public void refreshBalanceView() {
        view.refreshComboboxBalance();
    }
}