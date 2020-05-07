package controller;

import model.entities.Bot;
import model.entities.Company;
import model.managers.BotManager;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotMenuController implements ActionListener {
    private BotMenuView view;
    private BotManager model;

    public BotMenuController(BotMenuView view, BotManager model) {
        this.view = view;
        this.view.getBotsCreateView().showCompanies(model.getCompanies());
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String mode = e.getActionCommand();
        switch (mode) {
            case "createNewBot":
                Bot bot = new Bot();
                Company company = new Company(view.getBotsCreateView().getCompanyName());
                bot.setCompany(company);
                bot.setCompany(company);
                bot.setActiveTime(view.getBotsCreateView().getActivation());
                bot.setProbability(view.getBotsCreateView().getPercentage());
                model.createBot(bot);
                break;
            case "editBot":
                int botToBeEdited = view.getBotsEditView().getBotId();
                // TODO: Get bot information from view
                model.configureBot(botToBeEdited);
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
