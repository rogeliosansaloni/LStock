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
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("login")) {
            String user = view.getNicknameEmail();
            String password = view.getPassword();
            if (validCredentials(user, password)) {
                TunnelObject login = new AuthenticationInfo(user, user, password, "login");
                try {
                    NetworkManager.getInstance().sendAuthentificationInformation(login);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (e.getActionCommand().equals("register")) {
            view.setVisible(false);
            RegisterView registerView = new RegisterView();
            RegisterController controller = new RegisterController(registerView);
            registerView.registerController(controller);
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
    public boolean validCredentials(String user, String password) {
        if (user.equals("Nickname or Email") && password.equals("Password")) {
            view.showErrorMessage(ERROR_1);
            return false;
        }
        if (user.equals("Nickname or Email") || password.equals("Password")) {
            view.showErrorMessage(ERROR_2);
            return false;
        }
        return true;
    }

    /**
     * Closes the login view
     */
    public void closeLoginView() { view.setVisible(false);}
}
