package controller;

import model.entities.Bot;
import model.managers.BotManager;
import view.BotsRemoveView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class BotsRemoveComboBoxController implements ItemListener {
    private BotsRemoveView view;
    private BotManager model;

    public BotsRemoveComboBoxController(BotsRemoveView view, BotManager model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        if (comboBox.getActionCommand().equals("bot-company")) {
            String companyName = view.getCompanyName();
            int companyId = model.getCompanyId(companyName);
            ArrayList<Bot> bots = model.getAllBotsByCompany(companyId);
            view.showBots(bots);
        } else {
            if (comboBox.getActionCommand().equals("bot-id")) {

            }
        }
    }
}
