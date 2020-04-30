package controller;

import model.entities.AuthenticationInfo;
import model.entities.TunnelObject;
import network.NetworkManager;
import view.LoginView;
import view.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginController implements ActionListener {
    private static final int ERROR_1 = 1;
    private static final int ERROR_2 = 2;
    private LoginView loginView;
    private RegisterView registerView;

    public LoginController(LoginView loginView, RegisterView registerView) {
        this.loginView = loginView;
        this.registerView = registerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("login")) {
            String user = loginView.getNicknameEmail();
            String password = loginView.getPassword();
            if (validCredentials(user, password)) {
                AuthenticationInfo login = new AuthenticationInfo(user, user, password, "login");
                try {
                    NetworkManager.getInstance().sendAuthentificationInformation(login);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (e.getActionCommand().equals("register")) {
            loginView.setVisible(false);
            registerView.setVisible(true);
        }

    }

    /**
     * Function that validates the form values
     *
     * @param user     a nickname or an email
     * @param password the password
     * @return true if all the fields are filled up correctly
     */
    private boolean validCredentials(String user, String password) {
        if (user.equals("Nickname or Email") && password.equals("Password")) {
            loginView.showErrorMessage(ERROR_1);
            return false;
        }
        if (user.equals("Nickname or Email") || password.equals("Password")) {
            loginView.showErrorMessage(ERROR_2);
            return false;
        }
        return true;
    }

    /**
     * Closes the login view
     */
    public void closeLoginView() { loginView.setVisible(false);}

    /**
     * Proc that sends the error message to the view
     *
     * @param message that contains what error it is dealing with
     */
    public void sendErrorMessage(String message) {
        loginView.showLoginFailure(message);
    }
}
