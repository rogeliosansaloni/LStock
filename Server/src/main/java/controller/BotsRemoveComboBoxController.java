package controller;

import view.BotsRemoveView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BotsRemoveComboBoxController implements ItemListener {
    private BotsRemoveView view;

    public BotsRemoveComboBoxController(BotsRemoveView view) {
        this.view = view;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        if (comboBox.getActionCommand().equals("bot-company")) {

        } else {
            if (comboBox.getActionCommand().equals("bot-id")) {

            }
        }
    }
}
