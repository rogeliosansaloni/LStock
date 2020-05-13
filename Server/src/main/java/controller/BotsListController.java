package controller;

import model.managers.BotManager;
import view.BotsListView;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotsListController implements ActionListener {
    private static final String RETURN = "Return";
    private static final String CARD_BOTS = "Manage Bots";
    private MainView mainView;
    private BotsListView view;
    private BotManager model;

    public BotsListController (MainView mainView, BotsListView view, BotManager model) {
        this.mainView = mainView;
        this.view = view;
        this.model = model;
        this.view.showBotsInTable(model.getAllBots());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        mainView.updateView(CARD_BOTS);
    }
}
