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
    private int id;
    private String labelName;

    public BotCreateFocusController(BotsCreateView view, int id, String labelName) {
        this.view = view;
        this.id = id;
        this.labelName = labelName;
    }

    @Override
    public void focusGained(FocusEvent e) {
        view.setJTextField(id,"");
    }

    @Override
    public void focusLost(FocusEvent e) {
        view.setJTextField(id, labelName);
    }
}
