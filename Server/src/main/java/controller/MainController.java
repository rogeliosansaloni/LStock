package controller;

import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    private static final String CARD_HOME = "Home";
    private static final String CARD_USERS = "List of Users";
    private static final String CARD_BOTS = "Manage Bots";
    private final MainView view;
    private HomeController homeController;
    //TODO: Add the rest con controllers

    public MainController(MainView view) {
        this.view = view;
        this.homeController = new HomeController(view);
        //TODO: Initialize controllers
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case CARD_HOME:
                view.updateView(CARD_HOME);
                break;
            case CARD_USERS:
                break;
            case CARD_BOTS:
                break;
        }
    }

    public HomeController getHomeController() {
        return homeController;
    }
}
