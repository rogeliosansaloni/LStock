package controller;

import model.entities.Bot;
import model.managers.BotManager;
import view.BotsEditView;
import view.BotsRemoveView;
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
    private static final String CARD_BOTS = "Configure Bots";
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
        view.showCompanies(model.getCompanies());
        ArrayList<Bot> bots = model.getAllBotsByCompany(getSelectedCompanyId());
        view.showBots(bots);
    }

    /**
     * Gets the company id of the selected company name from the combobox
     * @return if of the company
     */
    private int getSelectedCompanyId() {
        String companyName = mainView.getBotsRemoveView().getCompanyName();
        return model.getCompanyId(companyName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case ENABLE:
                // TODO: Change bot status to ENABLED
                break;
            case DISABLE:
                // TODO: Change bot status to DISABLED
                break;
            case CANCEL:
                mainView.updateView(CARD_BOTS);
                break;
        }
    }
}
