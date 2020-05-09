package controller;

import model.entities.Company;
import model.entities.User;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.SplittableRandom;

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
        SplittableRandom whichButton;
        String[] substrings = whichButton.split("_", 2);
        int sharesPosition = Integer.parseInt(substrings[1]);

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
