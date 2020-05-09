package controller;

import model.entities.Company;
import model.entities.CompanyChange;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        String whichButton;
        whichButton = e.getActionCommand();
        String[] substrings = whichButton.split("_", 2);
        int sharesPosition = Integer.parseInt(substrings[1]);

    }

    public void updateCompanyList (ArrayList<CompanyChange> companies){
        this.view.updateCompanyList(companies);
    }
}
