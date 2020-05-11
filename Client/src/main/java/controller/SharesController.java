package controller;

import model.entities.*;
import network.NetworkManager;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class SharesController implements ActionListener {
    private MainView view;
    private StockManager model;

    public SharesController(MainView view, StockManager model) {
        this.view = view;
        this.model = model;
    }

     @Override
        public void actionPerformed (ActionEvent e){
            TunnelObject companyShares = new CurrentShares(model.getUser().getUserId(), Integer.parseInt(e.getActionCommand()));
            try {
                NetworkManager.getInstance().sendCompanyShares(companyShares);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        public void updateCompanyList (ArrayList < ShareChange > shares) {
            this.view.updateCompanyList(shares);
        }
}
