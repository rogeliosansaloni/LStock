package controller;

import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the Bot menu
 */
public class BotMenuController implements ActionListener {
    private static final String CARD_CREATE = "Create Bot";
    private static final String CARD_EDIT = "Configure Bot";
    private static final String CARD_REMOVE = "Remove Bot";
    private static final String CARD_LIST = "Bots";
    private MainView view;
    private MainController mainController;
    /**
     * Creates and initializes the controller
     * @param view main view for Client
     * @param mainController main controller for Client views
     */
    public BotMenuController(MainController mainController, MainView view) {
        this.mainController = mainController;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String mode = e.getActionCommand();
        switch (mode) {
            case CARD_CREATE:
                mainController.getBotsCreateController().initView();
                view.updateView(CARD_CREATE);
                break;
            case CARD_EDIT:
                //mainController.getBotsEditController().initView();
                view.updateView(CARD_EDIT);
                break;
            case CARD_REMOVE:
                mainController.getBotsRemoveController().initView();
                view.updateView(CARD_REMOVE);
                break;
            case CARD_LIST:
                mainController.getBotsListController().initView();
                view.updateView(CARD_LIST);
                break;
        }
    }
}
