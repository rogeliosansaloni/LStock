package controller;

import model.managers.BotManager;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotsRemoveController implements ActionListener {
    private static final String REMOVE = "CREATE";
    private static final String CANCEL = "CANCEL";
    private static final String CARD_BOTS = "Manage Bots";
    private MainView view;
    private BotManager model;

    public BotsRemoveController(MainView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case REMOVE:
                break;
            case CANCEL:
                view.updateView(CARD_BOTS);
                break;
        }
    }
}
