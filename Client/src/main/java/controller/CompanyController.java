package controller;

import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanyController implements ActionListener {

    private MainView view;

    public CompanyController(MainView view) {
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        view.updateView("Company Details");
    }
}
