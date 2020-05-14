package controller;

import model.entities.Bot;
import model.entities.Company;
import model.managers.BotManager;
import view.BotsCreateView;
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
    private static final String NAME = "Name";
    private static final String ACTIVATION_TIME = "Activation Time";
    private static final String PROBABILITY = "Buy Percentage";
    private static final String BOT_NAME_ERROR = "You have to indicate a bot name.";
    private static final String BOT_PROB_ERROR = "You have to indicate a probability value (0-100%).";
    private static final String BOT_PROB_VALUE_ERROR = "Probability value is wrong (Only 0-100%).";
    private static final String BOT_ACT_ERROR = "You have to indicate an activation time.";
    private static final String BOT_ACT_VALUE_ERROR = "Activation time value is wrong.";
    private BotsCreateView view;
    private MainView mainView;
    private BotManager model;

    /**
     * Creates and initializes the controller
     * @param view Bot creation view
     */
    public BotsCreateController (BotsCreateView view, MainView mainView, BotManager model) {
        this.model = model;
        this.view = view;
        this.mainView = mainView;
        initView();
    }

    /**
     * Initializes view data
     */
    public void initView() {
        view.showCompanies(model.getCompanies());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case CREATE:
                String companyName = view.getCompanyName();
                String botName = view.getBotName();
                String percentage = view.getPercentage();
                String activation = view.getActivation();
                if (validFields(botName, percentage, activation)) {
                    Bot bot = new Bot();
                    int id = model.getCompanyId(companyName);
                    bot.setCompany(new Company(id, companyName));
                    bot.setActiveTime(Float.parseFloat(activation));
                    bot.setProbability(Float.parseFloat(percentage));
                    model.createBot(bot);
                }
                break;
            case CANCEL:
                mainView.updateView(CARD_BOTS);
                break;
        }
    }

    /**
     * Checks if the fields are all correct.
     *
     * @param botName the bot name
     * @param percentage the probability of being bought/sold
     * @param activation the activation time in seconds
     * @return true if they're all valid
     */
    private boolean validFields (String botName, String percentage, String activation) {
        if (botName.equals(NAME)) {
            view.showErrorMessage(BOT_NAME_ERROR);
            return false;
        }
        else {
            if (percentage.equals(PROBABILITY)) {
                view.showErrorMessage(BOT_PROB_ERROR);
                return false;
            }
            else {
                if (Float.parseFloat(percentage) < 0 || Float.parseFloat(percentage) > 100) {
                    view.showErrorMessage(BOT_PROB_VALUE_ERROR);
                    return false;
                }
                else {
                    if (activation.equals(ACTIVATION_TIME)) {
                        view.showErrorMessage(BOT_ACT_ERROR);
                        return false;
                    }
                    else {
                        if (Float.parseFloat(activation) <= 0) {
                            view.showErrorMessage(BOT_ACT_VALUE_ERROR);
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
