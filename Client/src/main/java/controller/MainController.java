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

    }

    public CompanyDetailController getCompanyDetailController() {
        return companyDetailController;
    }
}
