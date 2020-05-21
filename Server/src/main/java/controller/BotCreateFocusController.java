package controller;

import view.BotsCreateView;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Controller for the focus effect of JTextField of BotsCreateView
 */
public class BotCreateFocusController implements FocusListener {
    private BotsCreateView view;
    private int id;
    private String labelName;

    /**
     * Creates and initializes the controller
     * @param view view for creating a bot
     * @param id id of the text field to control
     * @param labelName label of the text field
     */
    public BotCreateFocusController(BotsCreateView view, int id, String labelName) {
        this.view = view;
        this.id = id;
        this.labelName = labelName;
    }

    /**
     * Controls the text field when clicked
     * @param e focus event
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (view.getJTextField(id).getText().equals(labelName))
            view.setJTextField(id,"");
    }

    /**
     * Controls the text field when unclicked
     * @param e focus event
     */
    @Override
    public void focusLost(FocusEvent e) {
        if(view.getJTextField(id).getText().equals(""))
            view.setJTextField(id, labelName);
    }
}
