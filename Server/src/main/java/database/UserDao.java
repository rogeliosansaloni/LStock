package database;


import java.util.ArrayList;
import network.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDao {

    private DBConnector dbConnector;

    public UserDao (DBConnector dbConnector){
        this.dbConnector = dbConnector;
    }

    /**
     * It create a user when it registered
     * @param user the class that will be registering
     */
    public void createUser (User user) {
        boolean userExist = false;
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM User WHERE nickname LIKE '%"+ user.getNickname() + "%' OR email LIKE '%" + user.getEmail() + "%';");

        try {
            while (verify.next()) {
                if (verifyEmail(user.getEmail(), verify.getObject("email"))) {
                    System.out.println("This email has an account already.");
                    userExist = true;
                }
                else {
                    if (verifyNickname(user.getNickname(), verify.getObject("nickname"))) {
                        System.out.println("This username is already taken.");
                        userExist = true;
                    }
                }
            }
            if(!userExist){
                dbConnector.insertQuery("INSERT INTO User (nickname,email,password,description,total_balance) VALUES ('" + user.getnickname() + "','" + user.getEmail() + "','" + user.getdescription() + "','" + user.getTotalBalance() + "')");
            }
        }catch (SQLException e) {
            System.out.println("Error Creating User");
        }

    }


    /**
     * It will get all the users registered in LStock
     */
    public ArrayList<String> getAllUsers() {
        ResultSet getUsers = dbConnector.selectQuery("SELECT * FROM User;");
        ArrayList<String> users = null;
        try {
            users = new ArrayList<String>();
            while (getUsers.next()){
                users.add((getUsers.getObject("nickname")).toString());
            }
        } catch (SQLException e) {
            System.out.println("Error getting all users");
        }
        return users;

    }
    /**
     * It will update the information of one user
     * @param user User information
     */
    public User updateUser (User user){
        User userData = new User();
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM User WHERE (nickname LIKE '" + user.getNickname() + "' OR correo LIKE '" + user.getEmail() + "');");

        try {
            while (verify.next()){
                if (verifyEmail(user.getEmail(), verify.getObject("email")) || verifyNickname(user.getNickname(), verify.getObject("nickname"))) {
                    dbConnector.insertQuery("UPDATE User SET (description) VALUES ('" + user.getdescription() + "');");

                }
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Error updatting information");
        }
        return null;
    }

    /**
     * It will get all the information of one user
     * @param user User information
     */
    public User getUserInfo(User user){
        User userData = new User();
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM User WHERE (nickname LIKE '" + user.getNickname() + "' OR correo LIKE '" + user.getEmail() + "');");

        try {
            while (verify.next()){
                if (verifyEmail(user.getEmail(), verify.getObject("email")) || verifyNickname(user.getNickname(), verify.getObject("nickname"))) {
                    userData.setEmail(verify.getObject("email").toString());
                    userData.setNickname(verify.getObject("nickname").toString());
                    userData.setDescription(verify.getObject("decription").toString());
                    userData.setTotalBalance(verify.getObject("totalBalance").toString());
                }
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Error to getting user information");
        }
        return null;
    }

    /**
     * It permits to verify that the email hasn't arealdy exist.
     * @param  email Email to verify
     * @param  dbEmail Email from database to compare
     */
    public boolean verifyEmail (String email, String dbEmail) {

        try {
            while (verify.next()) {
                if (dbEmail.equals(email)) {
                    System.out.println("This email has an account already.");
                    return true;
                }
            }
        }catch (SQLException e) {
            System.out.println("Error getting email");
        }

    }

    /**
     * It permits to verify that the nickname hasn't arealdy exist.
     * @param  nickname Nickname to verify
     * @param  dbNickname Nickname from database to compare
     */
    public boolean verifyNickname (String nickname, String dbNickname) {
        try {
            while (verify.next()) {
                if (dbNickname.equals(nickname)) {
                    return true;
                }
            }
        }catch (SQLException e) {
            System.out.println("Error getting nickname");
        }

    }


}
