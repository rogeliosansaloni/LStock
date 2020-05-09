package controller;

import model.entities.Bot;
import model.entities.Company;
import model.managers.BotManager;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotMenuController implements ActionListener {
    private static final String CARD_CREATE = "Create Bot";
    private static final String CARD_EDIT = "Configure Bot";
    private static final String CARD_REMOVE = "Remove Bot";
    private static final String CARD_LIST = "Bots";
    private MainView view;
    private BotManager model;

    public BotMenuController(MainView view, BotManager model) {
        this.view = view;
        //this.view.getBotsCreateView().showCompanies(model.getCompanies());
        this.model = model;
    }

    public BotMenuController(MainView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String mode = e.getActionCommand();
        switch (mode) {
            case CARD_CREATE:
                view.updateView(CARD_CREATE);
                break;
            case CARD_EDIT:
                view.updateView(CARD_EDIT);
                //int botToBeEdited = view.getBotsEditView().getBotId();
                // TODO: Get bot information from view
                //model.configureBot(botToBeEdited);
                break;
            case CARD_REMOVE:
                view.updateView(CARD_REMOVE);
                //int botToBeDeleted = view.getBotsRemoveView().getBotId();
                // TODO: Get bot information from view
                //model.deleteBot(botToBeDeleted);
                break;
            case CARD_LIST:
                view.updateView(CARD_LIST);
                //view.getBotsListView().showBotsInTable(model.getAllBots());
                break;
        }
    }
}
