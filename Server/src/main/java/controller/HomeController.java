package controller;

import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the Home screen
 */
public class HomeController implements ActionListener {
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
            case "users":
                break;
            case "bots":
                break;
        }

    }
}
