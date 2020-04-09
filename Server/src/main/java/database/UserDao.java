package database;


import network.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserDao {

    private DBConnector dbConnector;

    public  UserDao (DBConnector dbConnector){
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
     * It will delete a user when its no longer available
     * @param user User to delete
     */
    public boolean deleteUser (User user) {
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM User WHERE (nickname LIKE '" + user.getNickname() + "' OR correo LIKE '" + user.getEmail() + "');");

        try {
            while (verify.next()) {
                if (verifyEmail(user.getEmail(), verify.getObject("email"))) {
                    dbConnector.deleteQuery("DELETE FROM User WHERE email LIKE'" + user.getEmail() + "');");
                    System.out.println("User %s deleted.", user.getEmail());
                    return true;
                }
                else {
                    if (verifyNickname(user.getNickname(), verify.getObject("nickname"))) {
                        dbConnector.deleteQuery("DELETE FROM User WHERE nickname LIKE'" + user.getNickName() + "');");
                        System.out.println((String.format("User %s deleted.", user.getNickname())));
                         return true;
                    }
                }
            }

        }catch (SQLException e) {
            System.out.println("Error deleting user");
        }
        return false;
    }

    /**
     * It will get all the users registered in LStock
     */
    public Arraylist<String> getAllUsers () {
        ResultSet getUsers = connectorDB.selectQuery("SELECT * FROM User;");
        Arraylist<String> users = null;
        try {
            users = new Arraylist<String>();
            while (getUsers.next()){
                users.add((getUsers.getObject("nickname")).toString());
            }
        } catch (SQLException e) {
            System.out.println("Error getting all users");
        }
        return users;

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
