package database;


import java.util.ArrayList;

import network.DBConnector;
import model.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private DBConnector dbConnector;
    private static final String REGISTER_MESSAGE_1 = "Register Success";
    private static final String REGISTER_MESSAGE_2 = "Email Taken";
    private static final String REGISTER_MESSAGE_3 = "Nickname Taken";


    public UserDao(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Creates user if nickname or email aren't taken yet.
     * If not, it creates an account for this user.
     *
     * @param user the class that will be registering
     */
    public String createUser(User user) {
        String message = REGISTER_MESSAGE_1;
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM User WHERE nickname LIKE '%" + user.getNickname() + "%' OR email LIKE '%" + user.getEmail() + "%';");

        try {
            while (verify.next()) {
                if (verifyEmail(user.getEmail(), verify.getObject("email").toString(), user)) {
                    message = REGISTER_MESSAGE_2;
                } else {
                    if (verifyNickname(user.getNickname(), verify.getObject("nickname").toString(), user)) {
                        message = REGISTER_MESSAGE_3;
                    }
                }
            }
            if (message.equals(REGISTER_MESSAGE_1)) {
                dbConnector.insertQuery("INSERT INTO User (nickname,email,password) VALUES ('" + user.getNickname() + "','" + user.getEmail() + "','" + user.getPassword() + "')");
            }
        } catch (SQLException e) {
            System.out.println("Error Creating User");
        }
        return message;
    }

    /**
     * It will validate the user data
     * @param user the class that will be validating
     */
    public boolean validateUser(User user) {
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM User WHERE nickname LIKE '%"+ user.getNickname() + "%' OR email LIKE '%" + user.getEmail() + "%';");

        try {
            while (verify.next()) {
                if (verifyEmail(user.getEmail(), verify.getObject("email").toString(), user) && verifyNickname(user.getNickname(), verify.getObject("nickname").toString(), user)) {
                        return false;
                }
            }
        }catch (SQLException e) {
            System.out.println("Error Validating User");
        }
        return true;
    }


    /**
     * It will get all the users registered in LStock
     *
     * @return ArrayList<String> all users registered
     */
    public ArrayList<String> getAllUsers() {
        ResultSet getUsers = dbConnector.selectQuery("SELECT * FROM User;");
        ArrayList<String> users = null;
        try {
            users = new ArrayList<String>();
            while (getUsers.next()) {
                users.add((getUsers.getObject("nickname")).toString());
            }
        } catch (SQLException e) {
            System.out.println("Error getting all users");
        }
        return users;

    }

    /**
     * It will update the information of one user
     *
     * @param user User information
     */
    public void updateUser(User user) {
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM User WHERE (nickname LIKE '" + user.getNickname() + "' OR email LIKE '" + user.getEmail() + "');");

        try {
            while (verify.next()) {
                if (verifyEmail(user.getEmail(), verify.getObject("email").toString(), user) || verifyNickname(user.getNickname(), verify.getObject("nickname").toString(), user)) {
                    dbConnector.insertQuery("UPDATE User SET (description) VALUES ('" + user.getDescription() + "');");

                }
            }
        } catch (SQLException e) {
            System.out.println("Error updatting information");
        }
    }

    /**
     * It will get all the information of one user
     *
     * @param user User information
     * @return user  User infromation
     */
    public User getUserInfo(User user) {
        User userData = new User();
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM User WHERE (nickname LIKE '" + user.getNickname() + "' OR email LIKE '" + user.getEmail() + "');");

        try {
            while (verify.next()) {
                if (verifyEmail(user.getEmail(), verify.getObject("email").toString(), user) || verifyNickname(user.getNickname(), verify.getObject("nickname").toString(), user)) {
                    userData.setEmail(verify.getObject("email").toString());
                    userData.setNickname(verify.getObject("nickname").toString());
                    userData.setDescription(verify.getObject("decription").toString());
                    userData.setTotalBalance((float) verify.getObject("totalBalance"));
                }
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Error to getting user information");
        }
        return null;
    }

    /**
     * It permits to verify that the email hasn't arealdy exist.
     *
     * @param email   Email to verify
     * @param dbEmail Email from database to compare
     * @return boolean email verified
     */
    public boolean verifyEmail(String email, String dbEmail, User user) {
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM User WHERE (nickname LIKE '" + user.getNickname() + "' OR email LIKE '" + user.getEmail() + "');");

        try {
            while (verify.next()) {
                if (dbEmail.equals(email)) {
                    System.out.println("This email has an account already.");
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting email");
        }
        return false;
    }

    /**
     * It permits to verify that the nickname hasn't arealdy exist.
     *
     * @param nickname   Nickname to verify
     * @param dbNickname Nickname from database to compare
     * @return boolean nickname verified
     */
    public boolean verifyNickname(String nickname, String dbNickname, User user) {
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM User WHERE (nickname LIKE '" + user.getNickname() + "' OR email LIKE '" + user.getEmail() + "');");

        try {
            while (verify.next()) {
                if (dbNickname.equals(nickname)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting nickname");
        }
        return false;
    }

}
