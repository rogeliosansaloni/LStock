package controller;

import model.entities.Bot;
import model.managers.BotManager;
import view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the Bot creation screen
 */
public class BotsCreateController implements ActionListener {
    private static final String CREATE = "CREATE";
    private static final String CANCEL = "CANCEL";
    private static final String CARD_BOTS = "Manage Bots";
    private MainView view;
    private BotManager model;

    /**
     * Creates and initializes the controller
     * @param view Bot creation view
     */
    public BotsCreateController (MainView view, BotManager model) {
        this.model = model;
        this.view = view;
        this.view.showCompanies(model.getCompanies());
        //this.view.getBotsCreateView().showCompanies(model.getCompanies());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case CREATE:
                String botName = view.getBotName();
                float percentage = view.getBotPercentage();
                float activation = view.getBotActivation();
                if (percentageValid(percentage)) {
                    Bot bot = new Bot();
                    //bot.setCompany();
                    bot.setActiveTime(activation);
                    bot.setProbability(percentage);
                    model.createBot(bot);
                }
                break;
            case CANCEL:
                view.updateView(CARD_BOTS);
                break;
        }
    }

    /**
     * Validates the probability of being bought/sold
     *
     * @param percentage the percentage
     * @return true if valid
     */
    private boolean percentageValid (float percentage) {
        if (percentage >= 0 && percentage <= 100) {
            return true;
        }
        return false;
    }
}
