package controller;

import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BalanceController implements ActionListener {
    private MainView view;

    public BalanceController(MainView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("load")) {
            String amountStr = view.getBalanceAmount().replace("$", "");
            float amount = Float.parseFloat(amountStr);
            //TODO: NetWorkManager
        }

    }
}
