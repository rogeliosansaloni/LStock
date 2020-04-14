package controller;

import view.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController implements ActionListener {
    private RegisterView view;

    public RegisterController(RegisterView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("register")) {
            String nickname =  view.getNickname();
            String email = view.getEmail();
            String pass1 = view.getPassword();
            String pass2 = view.getPasswordVerification();
        }
        if (e.getActionCommand().equals("login")) {
            view.setVisible(false);
        }
    }
}
