package controller;

import view.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

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
            validateCredentials(nickname, email, pass1, pass2);
        }
        if (e.getActionCommand().equals("login")) {
            view.setVisible(false);
        }
    }

    /**
     * Function that checks each field of the register form.
     * @param nickname
     * @param email
     * @param pass1
     * @param pass2
     * @return a boolean if the fields are all valid
     */
    public boolean validateCredentials (String nickname, String email, String pass1, String pass2) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
        //If all fields are empty
        if (nickname.equals("Nickname") && email.equals("Email") && pass1.equals("Password") && pass2.equals("Verify Password")) {
            view.showErrorMessages("All fields are required.");
            return false;
        }
        //If some field is empty, show the corresponding message
        if (nickname.equals("Nickname") || email.equals("Email") || pass1.equals("Password") || pass2.equals("Verify Password")) {
            view.showErrorMessages("Fields can't be empty.");
            return false;
        }
        //If email field isn't empty check if it's valid
        if (!email.equals("Email")) {
            Pattern emailPattern = Pattern.compile(emailRegex);
            if (!emailPattern.matcher(email).matches()) {
                view.showErrorMessages("Email is not valid.");
                return false;
            }
        }
        //If password fields aren't empty, check if they're the same
        if (!pass1.equals("Password") && !pass2.equals("Verify Password")) {
            if (!pass1.equals(pass2)) {
                view.showErrorMessages("Password doesn't match.");
                return false;
            }
            String error = validatePassword(pass1);
            if (!error.equals("Valid")) {
                view.showErrorMessages(error);
                return false;
            }
        }
        return true;
    }

    /**
     * Function that validates a password. It must be at least 8 characters with a capital letter and a digit.
     * The pattern is made from a regex.
     * @param password
     * @return a String with the error message
     */
    public String validatePassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*[0-9]).*$";
        if (password.length() < 8) {
            return "Password must have at least 8 characters.";
        }
        Pattern pattern = Pattern.compile(passwordRegex);
        if (!pattern.matcher(password).matches()) {
            return "Password must have at least one capital letter and one number";
        }
        return "Valid";
    }
}
