package controller;

import model.entities.Bot;
import model.managers.BotManager;
import view.MainView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class BotsRemoveComboBoxController implements ItemListener {
    private MainView view;
    private BotManager model;

    public BotsRemoveComboBoxController(MainView view, BotManager model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        if (comboBox.getActionCommand().equals("bot-company")) {
            String companyName = view.getBotsRemoveView().getCompanyName();
            int companyId = model.getCompanyId(companyName);
            ArrayList<Bot> bots = model.getAllBotsByCompany(companyId);
            view.getBotsRemoveView().showBots(bots);
        } else {
            if (comboBox.getActionCommand().equals("bot-id")) {

            }
        }
    }
}
