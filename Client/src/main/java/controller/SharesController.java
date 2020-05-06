package controller;

import model.entities.Company;
import model.entities.User;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SharesController implements ActionListener {
    private MainView view;
    private Company company;

    public SharesController(MainView view, Company company){
        this.view = view;
        this.company = company;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object jbPush = e.getSource();
        final String message = "Do you really want to sell all your shares for %s?";
        String.format(message, company.getName());
        if (jbPush == jbSell1) {
            //companyDetailView.updateNumberShares();

        }else if(jbPush == jbSell2){
            //companyDetailView.updateNumberShares();

        }else if(jbPush == jbSell3){
            //companyDetailView.updateNumberShares();

        }else{
            //companyDetailView.updateNumberShares();

        }

    }

    public void updateShares (Company company, User user){

    }
}
