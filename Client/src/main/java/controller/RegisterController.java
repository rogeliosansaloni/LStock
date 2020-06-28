package controller;

import model.entities.AuthenticationInfo;
import network.NetworkManager;
import view.LoginView;
import view.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Controller of the JTextfields and buttons for the registration functionality
 */
public class RegisterController implements ActionListener {
    private static final String LOGIN = "Login";
    private static final String REGISTER = "Register";
    private static final String NICKNAME_LABEL = "Nickname";
    private static final String EMAIL_LABEL = "Email";
    private static final String PASSWORD_LABEL = "Password";
    private static final String VERIFY_PASSWORD_LABEL = "Verify Password";
    private static final String NO_ERROR = "No Error";
    private static final String ERROR_1 = "Fields Required";
    private static final String ERROR_2 = "Fields Empty";
    private static final String ERROR_3 = "Email Error";
    private static final String ERROR_4 = "Password Match";
    private static final String ERROR_5 = "Password Length";
    private static final String ERROR_6 = "Password Format";
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
    private static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[0-9]).*$";
    private RegisterView registerView;
    private LoginView loginView;

    /**
     * Initializes the register controller and views
     *
     * @param registerView Register view
     * @param loginView    Login view
     */
    public RegisterController(RegisterView registerView, LoginView loginView) {
        this.registerView = registerView;
        this.loginView = loginView;
    }

    /**
     * Proc that captures any event in the register view
     *
     * @param e the actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(REGISTER)) {
            String nickname = registerView.getNickname();
            String email = registerView.getEmail();
            String pass1 = registerView.getPassword();
            String pass2 = registerView.getPasswordVerification();
            if (validCredentials(nickname, email, pass1, pass2)) {
                AuthenticationInfo register = new AuthenticationInfo(nickname, email, pass1, "register");
                try {
                    NetworkManager.getInstance().sendAuthentificationInformation(register);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (e.getActionCommand().equals(LOGIN)) {
            registerView.setVisible(false);
            loginView.setVisible(true);
        }
    }

    /**
     * Function that checks each field of the register form.
     *
     * @param nickname the nickname
     * @param email    the email
     * @param pass1    the password
     * @param pass2    repeated password
     * @return true if the fields are all valid
     */
    private boolean validCredentials(String nickname, String email, String pass1, String pass2) {
        //If all fields are empty
        if (nickname.equals(NICKNAME_LABEL) && email.equals(EMAIL_LABEL) && pass1.equals(PASSWORD_LABEL) && pass2.equals(VERIFY_PASSWORD_LABEL)) {
            registerView.showErrorMessages(ERROR_1);
            return false;
        }
        //If some field is empty, show the corresponding message
        if (nickname.equals(NICKNAME_LABEL) || email.equals(EMAIL_LABEL) || pass1.equals(PASSWORD_LABEL) || pass2.equals(VERIFY_PASSWORD_LABEL)) {
            registerView.showErrorMessages(ERROR_2);
            return false;
        }
        //If email field isn't empty check if it's valid
        if (!email.equals(EMAIL_LABEL)) {
            Pattern emailPattern = Pattern.compile(REGEX_EMAIL);
            if (!emailPattern.matcher(email).matches()) {
                registerView.showErrorMessages(ERROR_3);
                return false;
            }
        }
        //If password fields aren't empty, check if they're the same
        if (!pass1.equals(PASSWORD_LABEL) && !pass2.equals(VERIFY_PASSWORD_LABEL)) {
            if (!pass1.equals(pass2)) {
                registerView.showErrorMessages(ERROR_4);
                return false;
            }
            String error = validatePassword(pass1);
            if (!error.equals(NO_ERROR)) {
                registerView.showErrorMessages(error);
                return false;
            }
        }
        return true;
    }

    /**
     * Validates a password. It must be at least 8 characters with a capital letter and a digit.
     *
     * @param password the password
     * @return result message
     */
    private String validatePassword(String password) {
        if (password.length() < 8) {
            return ERROR_5;
        }
        Pattern pattern = Pattern.compile(REGEX_PASSWORD);
        if (!pattern.matcher(password).matches()) {
            return ERROR_6;
        }
        return NO_ERROR;
    }

    /**
     * Closes the register view
     */
    public void closeRegisterView() {
        registerView.setVisible(false);
    }

    /**
     * Sends the error message to the view
     *
     * @param message message that contains what error it is dealing with
     */
    public void sendErrorMessage(String message) {
        registerView.showErrorMessages(message);
    }
}
