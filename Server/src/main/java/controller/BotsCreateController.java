package controller;

import model.entities.Bot;
import model.entities.Company;
import model.managers.BotManager;
import view.BotsCreateView;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the Bot creation screen
 */
public class BotsCreateController implements ActionListener {
    private static final String CREATE = "CREATE";
    private static final String CANCEL = "CANCEL";
    private static final String CARD_BOTS = "Manage Bots";
    private static final String ACTIVATION_TIME = "Activation Time";
    private static final String PROBABILITY = "Buy Percentage";
    private static final String BOT_NUMBER_PERCENTAGE_ERROR = "You have to indicate a valid percentage (for example: 45.6).";
    private static final String BOT_NUMBER_ACTIVATION_ERROR = "You have to indicate a valid activation value (for example: 20.6).";
    private static final String BOT_PROB_ERROR = "You have to indicate a probability value (0-100%).";
    private static final String BOT_PROB_VALUE_ERROR = "Probability value is wrong (Only 0-100%).";
    private static final String BOT_ACT_ERROR = "You have to indicate an activation time.";
    private static final String BOT_ACT_VALUE_ERROR = "Activation time value is wrong.";
    private static final String SUCCESS_MESSAGE = "New bot has been created successfully";
    private static final String ERROR_MESSAGE = "There was a problem with creating a new bot.";
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
        view.initTextFields();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case CREATE:
                String companyName = view.getCompanyName();
                String percentage = view.getPercentage();
                String activation = view.getActivation();
                if (validFields(percentage, activation)) {
                    Bot bot = new Bot();
                    int id = model.getCompanyId(companyName);
                    bot.setCompany(new Company(id, companyName));
                    bot.setActiveTime(Float.parseFloat(activation));
                    bot.setProbability(Float.parseFloat(percentage));
                    if (model.createBot(bot) >= 0) {
                        initView();
                        model.addBotToCompany(bot);
                        view.showErrorMessage(SUCCESS_MESSAGE);
                    } else {
                        view.showErrorMessage(ERROR_MESSAGE);
                    }
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
     * @param percentage the probability of being bought/sold
     * @param activation the activation time in seconds
     * @return true if they're all valid
     */
    private boolean validFields (String percentage, String activation) {
        float percentageFloat;
        float activationFloat;

        if (percentage.equals(PROBABILITY)) {
            view.showErrorMessage(BOT_PROB_ERROR);
            return false;
        }
        else {
            if (activation.equals(ACTIVATION_TIME)) {
                view.showErrorMessage(BOT_ACT_ERROR);
                return false;
            }
        }

        try {
            percentageFloat = Float.parseFloat(percentage);
        } catch (NumberFormatException e) {
            view.showErrorMessage(BOT_NUMBER_PERCENTAGE_ERROR);
            return false;
        }
        try {
            activationFloat = Float.parseFloat(activation);
        } catch (NumberFormatException e) {
            view.showErrorMessage(BOT_NUMBER_ACTIVATION_ERROR);
            return false;
        }

         if (percentageFloat < 0 || percentageFloat > 100) {
             view.showErrorMessage(BOT_PROB_VALUE_ERROR);
             return false;
         }
         else {
             if (activationFloat <= 0) {
                 view.showErrorMessage(BOT_ACT_VALUE_ERROR);
                 return false;
             }
         }
        return true;
    }
}
