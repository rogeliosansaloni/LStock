package controller;

import model.entities.*;
import network.NetworkManager;
import view.MainView;
import view.SharesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SharesController implements ActionListener {
    private SharesView view;
    private StockManager model;

    public SharesController(SharesView view, StockManager model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TunnelObject currentShares = new CurrentShares(model.getUser().getUserId(), Integer.parseInt(e.getActionCommand()));
        try {
            NetworkManager.getInstance().sendCurrentShares(currentShares);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
