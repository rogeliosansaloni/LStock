package controller;

import model.entities.AuthenticationInfo;
import network.NetworkManager;
import view.LoginView;
import view.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Controller of JTextfields and buttons for login functionality
 */
public class LoginController implements ActionListener {
    private static final int ERROR_1 = 1;
    private static final int ERROR_2 = 2;
    private static final String LOGIN = "Login";
    private static final String REGISTER = "Register";
    private static final String USER_LABEL = "Nickname or Email";
    private static final String PASSWORD_LABEL = "Password";
    private LoginView loginView;
    private RegisterView registerView;

    /**
     * Initializes login controller and views
     * @param loginView Login view
     * @param registerView Register view
     */
    public LoginController(LoginView loginView, RegisterView registerView) {
        this.loginView = loginView;
        this.registerView = registerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(LOGIN)){
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
        if (e.getActionCommand().equals(REGISTER)) {
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
        if (user.equals(USER_LABEL) && password.equals(PASSWORD_LABEL)) {
            loginView.showErrorMessage(ERROR_1);
            return false;
        }
        if (user.equals(USER_LABEL) || password.equals(PASSWORD_LABEL)) {
            loginView.showErrorMessage(ERROR_2);
            return false;
        }
        return true;
    }

    /**
     * Closes the login view
     */
    public void closeLoginView() {
        loginView.setVisible(false);
        loginView.flushCredentials();
    }

    /**
     * Proc that sends the error message to the view
     *
     * @param message that contains what error it is dealing with
     */
    public void sendErrorMessage(String message) {
        loginView.showLoginFailure(message);
    }
}
