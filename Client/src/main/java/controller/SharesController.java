package controller;

import model.entities.*;
import network.NetworkManager;
import view.SharesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class SharesController implements ActionListener {
    private static final String SELL_ACTION = "SELL";
    private static final String VIEW = "Shares";
    private SharesView view;
    private StockManager model;

    public SharesController(SharesView view, StockManager model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int shareId = Integer.parseInt(e.getActionCommand());
        ShareChange shareChange = model.getShareChangeInfo(shareId);

        // Calculates new user balance
        float userBalance = shareChange.getSharesQuantity() * shareChange.getShareCurrentValue() +
                model.getUser().getTotalBalance();
        ShareTrade shareTrade = new ShareTrade(shareChange.getUserId(), userBalance, shareChange.getCompanyId(),
                shareId, shareChange.getShareCurrentValue(), shareChange.getSharesQuantity(), SELL_ACTION, VIEW);
        try {
            NetworkManager.getInstance().sendShareTrade(shareTrade);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Updates the SharesView
     */
    public void updateSharesView() {
        view.updateSharesView(model.getSharesChange());
        view.registerController(this, model.getSharesChange());
    }

    /**
     * Updates the model
     *
     * @param model new model updated
     */
    public void updateModel(StockManager model) {
        this.model = model;
    }

}
