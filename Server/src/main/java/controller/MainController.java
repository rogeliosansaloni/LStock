package controller;

import model.managers.BotManager;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Main Controller for the Server
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
    private BotMenuView jpMenuBots;
    private BotsCreateView jpBotsCreateView;
    private BotsRemoveView jpBotsRemoveView;
    private BotsListView jpBotsListView;
    private BotsEditView jpBotsEditView;
    private BotManager botModel;
    private HomeController homeController;
    private SharesListController sharesController;
    private TopTenController topTenController;
    private BotMenuController botMenuController;
    private BotsCreateController botsCreateController;
    private BotsRemoveController botsRemoveController;
    private BotsListController botsListController;
    private BotsEditController botsEditController;

    /**
     * Creates and initializes MainController
     * with the main views and controllers for Server
     *
     * @param view     MainView
     * @param botModel
     */
    public MainController(MainView view, BotManager botModel) {
        this.view = view;
        this.homeView = new HomeView();
        this.homeController = new HomeController(view);
        this.sharesListView = new SharesListView();
        this.homeController = new HomeController(view);
        this.sharesController = new SharesListController(this.sharesListView);
        this.sharesListView.registerController(this.sharesController);
        this.topTenView = new TopTenCompaniesView();
        this.jpMenuBots = new BotMenuView();
        this.jpBotsCreateView = new BotsCreateView();
        this.jpBotsRemoveView = new BotsRemoveView();
        this.jpBotsListView = new BotsListView();
        this.jpBotsEditView = new BotsEditView();
        this.topTenController = new TopTenController(topTenView);
        this.topTenView.showTopTen(this.topTenController.getTopTenCompanies());
        this.view.addToCardLayout(this.homeView, this.sharesListView, this.topTenView, this.jpMenuBots, this.jpBotsCreateView, this.jpBotsRemoveView, this.jpBotsListView, this.jpBotsEditView);
        this.botMenuController = new BotMenuController(this, view);
        this.botsCreateController = new BotsCreateController(jpBotsCreateView, view, botModel);
        this.botsRemoveController = new BotsRemoveController(jpBotsRemoveView, view, botModel);
        this.botsListController = new BotsListController(view, jpBotsListView, botModel);
        this.botsEditController = new BotsEditController(jpBotsEditView, view, botModel);
        this.view.addToCardLayout(homeView, sharesListView, topTenView, jpMenuBots, jpBotsCreateView, jpBotsRemoveView, jpBotsListView, jpBotsEditView);
        updateTopTen();
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
                view.updateView(CARD_BOTS);
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
    public void updateTopTen() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                topTenController.updateTopTenView();
            }
        };
        timer.schedule(task, 0, 1000);
    }

    /**
     * Gets the BotMenuController
     *
     * @return BotMenuController
     */
    public BotMenuController getBotMenuController() {
        return botMenuController;
    }

    /**
     * Gets the BotsCreateController
     *
     * @return BotsCreateCntroller
     */
    public BotsCreateController getBotsCreateController() {
        return botsCreateController;
    }

    /**
     * Gets the BotsRemoveController
     *
     * @return BotsRemoveController
     */
    public BotsRemoveController getBotsRemoveController() {
        return botsRemoveController;
    }

    /**
     * Gets the BotsListController
     *
     * @return BotsListController
     */
    public BotsListController getBotsListController() {
        return botsListController;
    }


    /**
     * Gets the BotsEditController
     *
     * @return BotsEditController
     */
    public BotsEditController getBotsEditController() {
        return botsEditController;
    }

}
