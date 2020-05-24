package controller;

import model.managers.BotManager;
import view.BotsListView;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the bots list
 */
public class BotsListController implements ActionListener {
    private static final String CARD_BOTS = "Manage Bots";
    private MainView mainView;
    private BotsListView view;
    private BotManager model;

    /**
     * Creates and initializes the controller and views
     * @param mainView MainView to be able to return to Bots Menu view
     * @param view BotsList view
     * @param model BotManager
     */
    public BotsListController (MainView mainView, BotsListView view, BotManager model) {
        this.mainView = mainView;
        this.view = view;
        this.model = model;
        initView();
    }

    /**
     * Initializes view data
     */
    public void initView() {
        view.showBotsInTable(model.getAllBots());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainView.updateView(CARD_BOTS);
    }
}
