package controller;

import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    private static final String CARD_HOME = "Home";
    private static final String CARD_USERS = "List of Users";
    private static final String CARD_BOTS = "Manage Bots";
    private static final String CARD_BOTS_EDIT = "Edit Bot";
    private static final String CARD_BOTS_REMOVE = "Remove Bot";
    private static final String CARD_BOTS_LIST = "Bots";
    private final MainView view;
    private HomeController homeController;
    private BotMenuController botMenuController;
    private BotsCreateController botsCreateController;
    //TODO: Add the rest con controllers

    public MainController(MainView view) {
        this.view = view;
        this.homeController = new HomeController(view);
        this.botMenuController = new BotMenuController(view);
        this.botsCreateController = new BotsCreateController(view);

        //TODO: Initialize controllers
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case CARD_HOME:
                view.updateView(CARD_HOME);
                break;
            case CARD_USERS:
                view.updateView(CARD_USERS);
                break;
            case CARD_BOTS:
                view.updateView(CARD_BOTS);
                break;
        }
    }

    /**
     * Gets home controller
     *
     * @return HomeController
     */
    public HomeController getHomeController() {
        return homeController;
    }

    public BotMenuController getBotMenuController() { return botMenuController; }

    public BotsCreateController getBotsCreateController() { return botsCreateController; }

}
