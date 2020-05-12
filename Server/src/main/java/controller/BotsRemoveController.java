package controller;

import model.managers.BotManager;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotsRemoveController implements ActionListener {
    private MainView view;
    private BotManager model;

    public BotsRemoveController(MainView view, BotManager model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
