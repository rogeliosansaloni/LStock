package controller;

import view.MainView;
import view.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    private final MainView view;

    public MainController (MainView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
