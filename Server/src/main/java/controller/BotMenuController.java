package controller;

import model.entities.Bot;
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
                Bot bot = new Bot();
                // TODO: Get bot information from view
                model.createBot(bot);
                break;
            case "editBot":
                int botToBeEdited = view.getBotsEditView().getBotId();
                // TODO: Get bot information from view
                model.configureBot(botToBeEdited, "ENABLE");
                break;
            case "deleteBot":
                int botToBeDeleted = view.getBotsRemoveView().getBotId();
                // TODO: Get bot information from view
                model.deleteBot(botToBeDeleted);
                break;
            case "listBots":
                view.getBotsListView().showBotsInTable(model.getAllBots());
                break;
        }
    }

    public void validatePercentage(float percentage) {
        // TODO: Implement validatePercentage
    }
}
