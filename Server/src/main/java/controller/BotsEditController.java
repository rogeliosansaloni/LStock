package controller;

import model.entities.Bot;
import model.entities.Company;
import model.managers.BotManager;
import view.BotsEditView;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controller for Bot removal
 */
public class BotsEditController implements ActionListener {
    private static final String ENABLE = "ENABLE";
    private static final String DISABLE = "DISABLE";
    private static final String CANCEL = "CANCEL";
    private static final String MESSAGE = "The bot you selected was ";
    private static final String CARD_BOTS = "Manage Bots";
    private MainView mainView;
    private BotsEditView view;
    private BotManager model;

    /**
     * Creates and initializes the controller
     * @param view MainView to be able to return to Bots Menu view
     * @param mainView the main view for Client
     * @param model BotManager
     */
    public BotsEditController(BotsEditView view, MainView mainView, BotManager model) {
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
        ArrayList<Bot> bots = model.getAllBotsByCompany(getSelectedCompanyId());
        view.showBots(getInitBots());
        view.showStatusButton(bots.get(0));
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

    /**
     * Gets the company id of the selected company name from the combobox
     * @return id of the company
     */
    private int getSelectedCompanyId() {
        String companyName = mainView.getBotsRemoveView().getCompanyName();
        return model.getCompanyId(companyName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case CANCEL:
                mainView.updateView(CARD_BOTS);
                break;
            default:
                int botId = view.getBotId();
                int status = model.getBot(botId).getStatus();
                String action = ENABLE;
                if (status == 1) { action = DISABLE; }
                model.configureBot(botId,action);
                model.updateCompanyBots();
                view.showMessages(MESSAGE + action.toLowerCase() + "d");
                view.showStatusButton(model.getBot(botId));
                break;
        }
    }
}
