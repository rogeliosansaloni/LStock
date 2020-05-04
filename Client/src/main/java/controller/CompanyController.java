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
        switch (e.getActionCommand()) {
            case "Company 1":
                System.out.println("Company 1");
                break;
            case "Company 2":
                System.out.println("Company 2");
                break;
            case "Company 3":
                System.out.println("Company 3");
                break;
            case "Company 4":
                System.out.println("Company 4");
                break;
        }
    }
}
