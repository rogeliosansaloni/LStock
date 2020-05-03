package controller;

import model.managers.BotManager;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotMenuController implements ActionListener {
    private BotMenuView view;
    private BotManager model;

    public BotMenuController(BotMenuView view, BotManager model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String mode = e.getActionCommand();
        switch (mode) {
            case "createBot":
                // TODO: Handle bot creation
                break;
            case "editBot":
                // TODO: Handle bot update
                break;
            case "deleteBot":
                // TODO: Handke bot deletion
                break;
            case "listBots":
                // TODO: Handle bot list
                break;
        }
    }

    public void validatePercentage(float percentage) {
        // TODO: Implement validatePercentage
    }
}
