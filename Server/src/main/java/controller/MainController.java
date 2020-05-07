package controller;

import model.managers.BotManager;
import view.BotMenuView;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    private static final String CARD_HOME = "Home";
    private static final String CARD_USERS = "List of Users";
    private static final String CARD_BOTS = "Manage Bots";
    private final MainView view;
    private BotMenuView botMenuView;
    private HomeController homeController;
    private BotMenuController botMenuController;

    public MainController(MainView view) {
        this.view = view;
        this.botMenuView = new BotMenuView();
        this.homeController = new HomeController(view);
        this.botMenuController = new BotMenuController(botMenuView, new BotManager());
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

    public HomeController getHomeController() {
        return homeController;
    }

    public BotMenuController getBotMenuController() {
        return botMenuController;
    }
}
