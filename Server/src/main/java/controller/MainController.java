package controller;

import model.managers.BotManager;
import view.HomeView;
import view.MainView;
import view.SharesListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main controller for the Client
 */
public class MainController implements ActionListener {
    private static final String CARD_HOME = "Home";
    private static final String CARD_USERS = "List of Users";
    private static final String CARD_BOTS = "Manage Bots";
    private final MainView view;
    private final SharesListView sharesListView;
    private HomeView homeView;
    private BotManager botModel;
    private HomeController homeController;
    private SharesListController sharesController;
    //TODO: Add the rest con controllers
    private BotMenuController botMenuController;
    private BotsCreateController botsCreateController;
    private BotsListController botsListController;
    private BotsRemoveController botsRemoveController;
    private BotsEditController botsEditController;

    /**
     * Creates and initializes the controller
     * @param view Main client view
     */
    public MainController(MainView view, BotManager botModel) {
        this.view = view;
        this.botModel = botModel;
        this.homeView = new HomeView();
        this.sharesListView = new SharesListView();
        this.homeController = new HomeController(view);
        this.botMenuController = new BotMenuController(this, view);
        this.botsCreateController = new BotsCreateController(view.getBotsCreateView(), view, botModel);
        this.botsRemoveController = new BotsRemoveController(view.getBotsRemoveView(), view, botModel);
        this.botsListController = new BotsListController(view, view.getBotsListView(), botModel);
        this.botsEditController = new BotsEditController(view.getBotsEditView(), view, botModel);
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

    /**
     * Gets the BotMenuController
     * @return BotMenuController
     */
    public BotMenuController getBotMenuController() { return botMenuController; }

    /**
     * Gets the BotsCreateController
     * @return BotsCreateCntroller
     */
    public BotsCreateController getBotsCreateController() { return botsCreateController; }

    /**
     * Gets the BotsRemoveController
     * @return BotsRemoveController
     */
    public BotsRemoveController getBotsRemoveController() { return botsRemoveController; }

    /**
     * Gets the BotsListController
     * @return BotsListController
     */
    public BotsListController getBotsListController() { return  botsListController; }


    /**
     * Gets the BotsEditController
     * @return BotsEditController
     */
    public BotsEditController getBotsEditController() {
        return botsEditController;
    }

}
