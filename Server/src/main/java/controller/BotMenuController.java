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
                int botId = view.getBotsEditView().getBotId();
                // TODO: Get bot information from view
                model.configureBot(botId);
                break;
            case "deleteBot":
                // TODO: Handle bot deletion
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
