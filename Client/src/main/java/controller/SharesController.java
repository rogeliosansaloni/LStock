package controller;

import model.entities.Company;
import model.entities.User;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SharesController implements ActionListener {
    private MainView view;
    private static final int CONFIRMED = 0;

    public SharesController (MainView view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("sellAllShares")) {
            String message = "Do you really want to sell all your shares?";
          //  if () {
                //companyDetailView.updateNumberShares();
            //}
        }

    }

    public void updateShares (Company company, User user){

    }
}
