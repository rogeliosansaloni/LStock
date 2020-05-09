package controller;

import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotsCreateController implements ActionListener {
    private static final String CREATE = "Create";
    private static final String CANCEL = "Cancel";
    private static final String CARD_BOTS = "Manage Bots";
    private MainView view;

    public BotsCreateController (MainView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case CREATE:
                break;
            case CANCEL:
                view.updateView(CARD_BOTS);
                break;
        }

    }
}
