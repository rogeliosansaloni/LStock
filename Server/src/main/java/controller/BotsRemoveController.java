package controller;

import model.entities.Bot;
import model.entities.Company;
import model.managers.BotManager;
import view.BotsRemoveView;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controller for Bot removal
 */
public class BotsRemoveController implements ActionListener {
    private static final String REMOVE = "REMOVE";
    private static final String CANCEL = "CANCEL";
    private static final String CARD_BOTS = "Manage Bots";
    private static final String SUCCESS_MESSAGE = "The bot %d has been removed successfully";
    private static final String ERROR_MESSAGE = "There was a problem with removing the bot.";
    private MainView mainView;
    private BotsRemoveView view;
    private BotManager model;

    /**
     * Creates and initializes the controller
     * @param view MainView to be able to return to Bots Menu view
     * @param mainView the main view for Client
     * @param model BotManager
     */
    public BotsRemoveController(BotsRemoveView view, MainView mainView, BotManager model) {
        this.view = view;
        this.mainView = mainView;
        this.model = model;
        initView();
    }

    /**
     * Initializes the comboboxes with the names of the companies and the bots for the
     * selected and default company
     */
    public void initView() {
        view.showCompanies(model.getCompaniesWithBots());
        view.showBots(getInitBots());
    }

    /**
     * Gets the company id of the selected company name from the combobox
     * @return id of the company
     */
    private int getSelectedCompanyId() {
        String companyName = mainView.getBotsRemoveView().getCompanyName();
        return model.getCompanyId(companyName);
    }

    /**
     * Get the company from a list of companies by id
     * @param companies list of companies
     * @param id id of the company
     * @return company
     */
    private Company getCompany(ArrayList<Company> companies, int id) {
        for(Company c : companies) {
            if (c.getCompanyId() == id) {
                return c;
            }
        }
        return null;
    }

    /**
     * Get the bots of the selected company
     * @return bots of the company
     */
    private ArrayList<Bot> getInitBots() {
        return getCompany(model.getCompaniesWithBots(), getSelectedCompanyId()).getBots();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case REMOVE:
                int botId = mainView.getBotsRemoveView().getBotId();
                if (model.deleteBot(botId)) {
                    view.showMessages(String.format(SUCCESS_MESSAGE, botId));
                    model.updateCompanyBots();
                    ArrayList<Bot> bots = model.getAllBotsByCompany(getSelectedCompanyId());
                    if (bots.isEmpty()) {
                        initView();
                    } else {
                        view.showBots(bots);
                    }
                } else {
                    view.showMessages(ERROR_MESSAGE);
                }
                break;
            case CANCEL:
                mainView.updateView(CARD_BOTS);
                break;
        }
    }
}
