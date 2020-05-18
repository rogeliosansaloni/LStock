package controller;

import view.HomeView;
import view.MainView;
import view.SharesListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    private static final String CARD_HOME = "Home";
    private static final String CARD_USERS = "List of Users";
    private static final String CARD_BOTS = "Manage Bots";
    private final MainView view;
    private final SharesListView sharesListView;
    private HomeView homeView;
    private HomeController homeController;
    private SharesListController sharesController;
    //TODO: Add the rest con controllers

    public MainController(MainView view) {
        this.view = view;
        this.homeView = new HomeView();
        this.sharesListView = new SharesListView();
        this.homeController = new HomeController(view);
        this.sharesController = new SharesListController(this.sharesListView);
        this.sharesListView.registerController(this.sharesController);
        this.view.addToCardLayout(homeView,sharesListView);
        //TODO: Initialize controllers
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case CARD_HOME:
                view.updateView(CARD_HOME);
                break;
            case CARD_USERS:
                this.sharesController.loadUsers();
                view.updateView(CARD_USERS);
                break;
            case CARD_BOTS:
                break;
        }
    }

    public HomeController getHomeController() {
        return homeController;
    }
}
