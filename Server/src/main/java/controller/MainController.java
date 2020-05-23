package controller;

import view.HomeView;
import view.MainView;
import view.SharesListView;
import view.TopTenCompaniesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    private static final String CARD_HOME = "Home";
    private static final String CARD_USERS = "List of Users";
    private static final String CARD_BOTS = "Manage Bots";
    private static final String CARD_TOPTEN = "Top 10 Companies";
    private final MainView view;
    private HomeView homeView;
    private final SharesListView sharesListView;
    private final TopTenCompaniesView topTenView;
    private HomeController homeController;
    private TopTenController topTenController;
    //TODO: Add the rest con controllers

    public MainController(MainView view) {
        this.view = view;
        this.homeView = new HomeView();
        this.homeController = new HomeController(view);
        this.sharesListView = new SharesListView();
        this.topTenController = new TopTenController();
        this.topTenView = new TopTenCompaniesView();
        this.topTenView.showTopTen(this.topTenController.getTopTenCompanies());
        this.view.addToCardLayout(this.homeView, this.sharesListView,this.topTenView);
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
                break;
            case CARD_TOPTEN:
                view.updateView(CARD_TOPTEN);
                break;
        }
    }

    public HomeController getHomeController() {
        return homeController;
    }
}
