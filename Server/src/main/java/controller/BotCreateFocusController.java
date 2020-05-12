package controller;

import view.BotsCreateView;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class BotCreateFocusController implements FocusListener {
    private static final String NAME_LABEL = "Name";
    private static final String BUY_PERCENTAGE_LABEL = "Buy Percentatge";
    private static final String ACTIVATE_TIME_LABEL = "Activate time";
    private BotsCreateView view;

    public BotCreateFocusController(BotsCreateView view) {
        this.view = view;
    }

    @Override
    public void focusGained(FocusEvent e) {
        JTextField[] jTextFields = view.getJtField();
        if (jTextFields[0].getText().equals(NAME_LABEL)) {
            jTextFields[0].setText("");
        }
        if (jTextFields[1].getText().equals(BUY_PERCENTAGE_LABEL)) {
            jTextFields[1].setText("");
        }
        if (jTextFields[2].getText().equals(ACTIVATE_TIME_LABEL)) {
            jTextFields[2].setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField[] jTextFields = view.getJtField();
        if (jTextFields[0].getText().equals("")) {
            jTextFields[0].setText(NAME_LABEL);
        }
        if (jTextFields[1].getText().equals("")) {
            jTextFields[1].setText(BUY_PERCENTAGE_LABEL);
        }
        if (jTextFields[2].getText().equals("")) {
            jTextFields[2].setText(ACTIVATE_TIME_LABEL);
        }
    }
}
