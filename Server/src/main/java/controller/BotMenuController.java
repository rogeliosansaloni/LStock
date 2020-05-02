package controller;

import view.BotMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotMenuController implements ActionListener {
    private BotMenuView view;

    public BotMenuController(BotMenuView view) {
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    public void validatePercentage(float percentage) {
        // TODO: Implement validatePercentage
    }
}
