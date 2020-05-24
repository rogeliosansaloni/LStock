package controller;

import view.HomeView;
import view.MainView;
import view.SharesListView;
import view.TopTenCompaniesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 *  Main Controller for the Server
 */
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

    /**
     * Creates and initializes MainController
     * with the main views and controllers for Server
     *
     * @param view MainView
     */
    public MainController(MainView view) {
        this.view = view;
        this.homeView = new HomeView();
        this.homeController = new HomeController(view);
        this.sharesListView = new SharesListView();
        this.topTenView = new TopTenCompaniesView();
        this.topTenController = new TopTenController(topTenView);
        this.topTenView.showTopTen(this.topTenController.getTopTenCompanies());
        this.view.addToCardLayout(this.homeView, this.sharesListView,this.topTenView);
        updateTopTen();
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

    /**
     * Returns the HomeController
     *
     * @return HomeController
     */
    public HomeController getHomeController() {
        return homeController;
    }

    /**
     * Updates de Top 10 Company list after
     * a desired time value
     */
    public void updateTopTen(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                topTenController.updateTopTenView();
            }
        };
        timer.schedule(task,0,1000);
    }
}
