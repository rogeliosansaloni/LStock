package Connetor;


import networ.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;
import java.util.LinkedList;


public class UserDao {

    private ConectorDB connectorDB;

    /**
     * CREATE USER (when a client want to register)
     * @param User user
     */
    public void createUser (User user) {
        ResultSet verify = connectorDB.selectQuery("SELECT * FROM User WHERE nickname LIKE '%"+user.getNickname()+"%' OR email LIKE '%"+user.getEmail()+"%'");

        try {
            while (verify.next()) {
                if (verifyEmail(user.getEmail(), verify.getObject("email"))) {
                    System.out.println("This email has an account already.");
                }
                else {
                    if (verifyNickname(user.getNickname(), verify.getObject("nickname"))) {
                        System.out.println("This username is already taken.");
                    }
                }
            }
            if(!userExist){
                connectorDB.insertQuery("INSERT INTO Usuario (nickname,email,password,description,total_balance) VALUES ('"+user.getnickname()+"','"+user.getEmail()+"','"+user.getdescription()+"','"+user.getTotalBalance()"')");
            }
        }catch (SQLException e) {
            System.out.println("Problema al obtener los datos del Select de Usuario");
        }

    }

    /**
     * DELETE USER
     * @param User user
     */
    public boolean deleteUser (User user) {
        ResultSet verify = connectorDB.selectQuery("SELECT * FROM User WHERE (nickname LIKE '"+ user.getNickname()+"' OR correo LIKE '"+user.getEmail()+"')");

        try {
            while (verify.next()) {
                if (verifyEmail(user.getEmail(), verify.getObject("email"))) {
                    connectorDB.deleteQuery("DELETE FROM User WHERE email='"+user.getEmail()+"')");
                    System.out.println("User Deleted");
                    return true;
                }
                else {
                    if (verifyNickname(user.getNickname(), verify.getObject("nickname"))) {
                        connectorDB.deleteQuery("DELETE FROM User WHERE nickname ='"+user.getNickName()+"')");
                        System.out.println("User Deleted");
                         return true;
                    }
                }
            }

        }catch (SQLException e) {
            System.out.println("Problema al obtener los datos del Select de Usuario");
        }
        return false;
    }

    /**
     * GET ALL USERS
     * @param
     */
    public LinkedList<String> deleteUser () {
        ResultSet getUsers = connectorDB.selectQuery("SELECT * FROM User");
        LinkedList<String> users = null;
        try {
            users = new LinkedList<String>();
            while (getUsers.next()){
                users.add((String)getUsers.getObject("nickname"));
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }
        return users;

    }

    /**
     * GET ALL USERS
     * @param User user
     */
    public User getUserInfo(User user){
        User userData = new User;
        ResultSet verify = connectorDB.selectQuery("SELECT * FROM User WHERE (nickname LIKE '"+ user.getNickname()+"' OR correo LIKE '"+user.getEmail()+"')");

        try {
            while (verify.next()){
                if (verifyEmail(user.getEmail(), verify.getObject("email")) || verifyNickname(user.getNickname(), verify.getObject("nickname"))) {
                    userData.setNickname((String) verify.getObject("email"));
                    userData.setNickname((String) verify.getObject("nickname"));
                    userData.setNickname((String) verify.getObject("decription"));
                    userData.setNickname((String) verify.getObject("totalBalance"));
                }
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Error");
        }
        return null;
    }

    /**
     * VERIFY EMAIL
     * @param String email
     */
    public void verifyEmail (String email, String db_email) {

        try {
            while (verify.next()) {
                if (db_email.equals(email)) {
                    System.out.println("This email has an account already.");
                    return true;
                }
            }
        }catch (SQLException e) {
            System.out.println("Error");
        }

    }

    /**
     * VERIFY NICKNAME
     * @param String nickname
     */
    public void verifyNickname (String nickname, String db_nickname) {
        try {
            while (verify.next()) {
                if (db_nickname.equals(nickname)) {
                    return true;
                }
            }
        }catch (SQLException e) {
            System.out.println("Error");
        }

    }

}
