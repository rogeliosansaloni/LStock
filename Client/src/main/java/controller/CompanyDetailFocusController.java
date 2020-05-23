package controller;

import view.CompanyDetailView;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CompanyDetailFocusController implements FocusListener {
    private static final String LABEL_SHARES_BUY = "Number of shares you want to buy";
    private CompanyDetailView view;

    public CompanyDetailFocusController(CompanyDetailView view) {
        this.view = view;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(view.getSharesBuy().equals(LABEL_SHARES_BUY))
            view.setJtBuyShares("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(view.getSharesBuy().equals(""))
            view.setJtBuyShares(LABEL_SHARES_BUY);
    }
}
