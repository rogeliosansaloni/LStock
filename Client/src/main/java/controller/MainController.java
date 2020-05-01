package controller;

import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    private static final String CARD_COMPANY = "Companies";
    private static final String CARD_PROFILE = "My Profile";
    private static final String CARD_SHARES = "Shares";
    private static final String CARD_BALANCE = "Load Balance";
    private final MainView view;

    public MainController(MainView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "profile":
                view.updateView(CARD_PROFILE);
                //TODO: Profile
                break;
            case "shares":
                view.updateView(CARD_SHARES);
                //TODO: Shares
                break;
            case "load":
                view.updateView(CARD_BALANCE);
                //TODO: Load Balance
                break;
            case "logout":
                //TODO: Log out
                break;

        }
    }
}
