package controller;

import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the Home screen
 */
public class HomeController implements ActionListener {
    private static final String CARD_USERS = "List of Users";
    private static final String CARD_BOTS = "Manage Bots";
    private MainView view;

    /**
     * Creates and initializes the controller
     * @param view Server main view
     */
    public HomeController (MainView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case CARD_USERS:
                view.updateView(CARD_USERS);
                break;
            case CARD_BOTS:
                view.updateView(CARD_BOTS);
                break;
        }

    }
}
