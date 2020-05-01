package controller;

import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    private final MainView view;
    private CompanyDetailController companyDetailController;

    public MainController(MainView view) {
        this.view = view;
        this.companyDetailController = new CompanyDetailController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "profile":
                //TODO: Profile
                break;
            case "shares":
                //TODO: Shares
                break;
            case "load":
                //TODO: Load Balance
                break;
            case "logout":
                //TODO: Log out
                break;

        }
    }

    public CompanyDetailController getCompanyDetailController() {
        return companyDetailController;
    }
}
