package controller;

import model.managers.BotManager;
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
    private MainView view;
    private BotManager model;

    /**
     * Creates and initializes the controller
     * @param view Bot creation view
     */
    public BotsCreateController (MainView view) {
        //TODO: Add model and initialized the dropdown field
        this.view = view;
        //this.view.getBotsCreateView().showCompanies(model.getCompanies());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case CREATE:
                /*Bot bot = new Bot();
                Company company = new Company(view.getBotsCreateView().getCompanyName());
                bot.setCompany(company);
                bot.setCompany(company);
                bot.setActiveTime(view.getBotsCreateView().getActivation());
                bot.setProbability(view.getBotsCreateView().getPercentage());
                model.createBot(bot);*/
                break;
            case CANCEL:
                view.updateView(CARD_BOTS);
                break;
        }
    }
    public void validatePercentage(float percentage) {
        // TODO: Implement validatePercentage
    }
}
