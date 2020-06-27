package controller;

import view.CompanyDetailView;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Controller for the JTextFields of the Company Detail View
 */
public class CompanyDetailFocusController implements FocusListener {
    private static final String LABEL_SHARES_BUY = "Number of shares you want to buy";
    private CompanyDetailView view;

    /**
     * Createsand initializes the controller
     *
     * @param view Company Detail View
     */
    public CompanyDetailFocusController(CompanyDetailView view) {
        this.view = view;
    }

    /**
     * Controls the text field when clicked
     *
     * @param e focus event
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (view.getSharesBuy().equals(LABEL_SHARES_BUY))
            view.setJtBuyShares("");
    }

    /**
     * Controls the text field when unclicked
     *
     * @param e focus event
     */
    @Override
    public void focusLost(FocusEvent e) {
        if (view.getSharesBuy().equals(""))
            view.setJtBuyShares(LABEL_SHARES_BUY);
    }
}
