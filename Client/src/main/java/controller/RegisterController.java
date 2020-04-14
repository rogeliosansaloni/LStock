package controller;

import view.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class RegisterController implements ActionListener {
    private static final int NO_ERROR = 0;
    private static final int ERROR_1 = 1;
    private static final int ERROR_2 = 2;
    private static final int ERROR_3 = 3;
    private static final int ERROR_4 = 4;
    private static final int ERROR_5 = 5;
    private static final int ERROR_6 = 6;
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
    private static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[0-9]).*$";


    private RegisterView view;

    public RegisterController(RegisterView view) {
        this.view = view;
    }

    /**
     * Proc that capturees any event in the register view
     * @param e the actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("register")) {
            String nickname =  view.getNickname();
            String email = view.getEmail();
            String pass1 = view.getPassword();
            String pass2 = view.getPasswordVerification();
            if (validCredentials(nickname, email, pass1, pass2)) {
                //TODO: Validate with server
            }
        }
        if (e.getActionCommand().equals("login")) {
            view.setVisible(false);
            //TODO: Opens the LoginView
        }
    }

    /**
     * Function that checks each field of the register form.
     * @param nickname the nickname
     * @param email the email
     * @param pass1 the password
     * @param pass2 repeated password
     * @return true if the fields are all valid
     */
    public boolean validCredentials (String nickname, String email, String pass1, String pass2) {
        //If all fields are empty
        if (nickname.equals("Nickname") && email.equals("Email") && pass1.equals("Password") && pass2.equals("Verify Password")) {
            view.showErrorMessages(ERROR_1);
            return false;
        }
        //If some field is empty, show the corresponding message
        if (nickname.equals("Nickname") || email.equals("Email") || pass1.equals("Password") || pass2.equals("Verify Password")) {
            view.showErrorMessages(ERROR_2);
            return false;
        }
        //If email field isn't empty check if it's valid
        if (!email.equals("Email")) {
            Pattern emailPattern = Pattern.compile(REGEX_EMAIL);
            if (!emailPattern.matcher(email).matches()) {
                view.showErrorMessages(ERROR_3);
                return false;
            }
        }
        //If password fields aren't empty, check if they're the same
        if (!pass1.equals("Password") && !pass2.equals("Verify Password")) {
            if (!pass1.equals(pass2)) {
                view.showErrorMessages(ERROR_4);
                return false;
            }
            int error = validatePassword(pass1);
            if (error != NO_ERROR) {
                view.showErrorMessages(error);
                return false;
            }
        }
        return true;
    }

    /**
     * Function that validates a password. It must be at least 8 characters with a capital letter and a digit.
     * The pattern is made from a regex.
     * @param password the password
     * @return a String with the error message
     */
    public int validatePassword(String password) {
        if (password.length() < 8) {
            return ERROR_5;
        }
        Pattern pattern = Pattern.compile(REGEX_PASSWORD);
        if (!pattern.matcher(password).matches()) {
            return ERROR_6;
        }
        return NO_ERROR;
    }
}
