package controller;

import view.LoginView;
import view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    private final MainView view;
    private final LoginView loginView;

    public MainController(MainView view, LoginView loginView) {
        this.view = view;
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "profile":
                //TODO: Profile
                break;
            case "shares":
                //TODO: Shares
                break;
            case "load":
                //TODO: Load Balance
                break;
            case "logout":

                if(logOut() == 0){
                    loginView.setVisible(true);
                    view.setVisible(false);
                }
                break;

        }

    }
    public int logOut(){
        int verify = JOptionPane.showConfirmDialog(null, "Do you want to logout really?","Log Out", JOptionPane.YES_NO_OPTION);
        return verify;
    }
}
