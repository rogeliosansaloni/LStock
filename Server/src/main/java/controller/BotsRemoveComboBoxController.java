package controller;

import model.entities.Bot;
import model.managers.BotManager;
import view.BotsRemoveView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Controller for the comboboxes of the Bot Remove view
 */
public class BotsRemoveComboBoxController implements ItemListener {
    private BotsRemoveView view;
    private BotManager model;

    /**
     * Creates and initializes the combobox controller
     * @param view Bot Remove view
     * @param model BotManager
     */
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
        }
    }
}
