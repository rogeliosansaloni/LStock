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

    public SharesController(MainView view) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object jbPush = e.getSource();
        final String message = "Do you really want to sell all your shares for %s?";
        String.format(message, company.getName());
        switch (e.getActionCommand()) {
            case "Sell All Shares 1":
                System.out.println("Company 1");
                break;
            case "Sell All Shares 2":
                System.out.println("Company 2");
                break;
            case "Sell All Shares 3":
                System.out.println("Company 3");
                break;
            case "Sell All Shares 4":
                System.out.println("Company 4");
                break;
        }

    }

    public void updateShares (Company company, User user){

    }
}
