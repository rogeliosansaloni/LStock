package controller;

import view.LoginView;
import view.RegisterView;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class RegisterFocusController implements FocusListener {
    private RegisterView view;
    private int id;
    private String label;

    /**
     * Creates and initializes the controller
     * @param view register view
     * @param id id of the text field to control
     * @param label label of the text field
     */
    public RegisterFocusController(RegisterView view, int id, String label) {
        this.view = view;
        this.id = id;
        this.label = label;
    }

    /**
     * Controls the text field when clicked
     * @param e focus event
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (view.getJTextField(id).getText().equals(label)) {
            view.setJTextField(id,"");
        }
    }

    /**
     * Controls the text field when unclicked
     * @param e focus event
     */
    @Override
    public void focusLost(FocusEvent e) {
        if (view.getJTextField(id).getText().equals("")) {
            view.setJTextField(id, label);
        }
    }
}
