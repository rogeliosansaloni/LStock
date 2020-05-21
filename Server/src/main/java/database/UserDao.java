package database;


import java.util.ArrayList;

import model.entities.Share;
import model.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private DBConnector dbConnector;
    private static final String REGISTER_MESSAGE_1 = "Register Success";
    private static final String REGISTER_MESSAGE_2 = "Email Taken";
    private static final String REGISTER_MESSAGE_3 = "Nickname Taken";
    private static final String REGISTER_MESSAGE_4 = "Error Creating User";
    private static final String LOGIN_MESSAGE_1 = "Login Success";
    private static final String LOGIN_MESSAGE_2 = "Error logging in";
    private static final String LOGIN_MESSAGE_3 = "Login Error";
    private static final String PROFILE_MESSAGE_1 = "Error getting the user information";
    private static final String PROFILE_MESSAGE_2 = "Error updating the user information";
    private static final String BALANCE_MESSAGE_1 = "Error updating the user total balance";

    /**
     * Represents the DAO for the User table
     */
    public UserDao(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Creates user if nickname or email aren't taken yet.
     * If not, it creates an account for this user.
     *
     * @param user the User to be registered
     */
    public String createUser(User user) {
        String message = REGISTER_MESSAGE_1;
        ResultSet result = dbConnector.selectQuery("SELECT * FROM User WHERE nickname LIKE '%" + user.getNickname() + "%' OR email LIKE '%" + user.getEmail() + "%';");
        try {
            while (result.next()) {
                if (result.getString("email").equals(user.getEmail())) {
                    message = REGISTER_MESSAGE_2;
                }
                else {
                    if (result.getString("nickname").equals(user.getNickname())) {
                        message = REGISTER_MESSAGE_3;
                    }
                }
            }
            if (message.equals(REGISTER_MESSAGE_1)) {
                dbConnector.insertQuery("INSERT INTO User (nickname,email,password) VALUES ('" + user.getNickname() + "','" + user.getEmail() + "','" + user.getPassword() + "')");
            }
        } catch (SQLException e) {
            message = REGISTER_MESSAGE_4;
        }
        return message;
    }

    /**
     * Validates user if it exists in the database
     *
     * @param user the User to be validated
     */
    public String validateUser(User user) {
        ResultSet result = dbConnector.selectQuery("SELECT * FROM User WHERE nickname LIKE '%" + user.getNickname() + "%' OR email LIKE '%" + user.getEmail() + "%';");
        String message = LOGIN_MESSAGE_3;
        try {
            while (result.next()) {
                if (result.getString("email").equals(user.getEmail()) && user.getPassword().equals(result.getObject("password"))) {
                    user.setUserId(result.getInt("user_id"));
                    user.setNickname(result.getString("nickname"));
                    user.setTotalBalance(result.getFloat("total_balance"));
                    return LOGIN_MESSAGE_1;
                } else {
                    if (result.getString("nickname").equals(user.getNickname()) && user.getPassword().equals(result.getObject("password"))) {
                        user.setUserId(result.getInt("user_id"));
                        user.setEmail(result.getString("email"));
                        user.setTotalBalance(result.getFloat("total_balance"));
                        return LOGIN_MESSAGE_1;
                    }
                }
            }
        } catch (SQLException e) {
            message = LOGIN_MESSAGE_2;
        }
        return message;
    }


    /**
     * It will get all the users registered in LStock
     *
     * @return ArrayList<String> all users registered
     */
    public ArrayList<User> getAllUsers() {
        ResultSet getUsers = dbConnector.selectQuery("SELECT * FROM User;");
        ArrayList<User> users = null;
        try {
            users = new ArrayList<User>();
            while (getUsers.next()) {
                users.add(new User(
                        getUsers.getObject("nickname").toString(),
                        getUsers.getObject("email").toString(),
                        //TODO add stock value to this user for SharesListView
                        -1,
                        Float.parseFloat(getUsers.getObject("total_balance").toString())
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all users");
        }
        return users;
    }

    /**
     * It will get all the users registered in LStock
     *
     * @return String[][] all users registered
     */
    public String[][] getAllUserList() {
        String[][] users;
        ArrayList<User> userList = getAllUsers();
        users = new String[userList.size()][4];

        for (int i = 0; i < userList.size(); i++){
            users[i][0] = userList.get(i).getNickname();
            users[i][1] = userList.get(i).getEmail();
            users[i][2] = String.valueOf(userList.get(i).getStockValue());
            users[i][3] = String.valueOf(userList.get(i).getTotalBalance());
        }
        return users;
    }

    /**
     * It will update the information of one user
     *
     * @param user User information
     */
    public void updateUserBalance(User user) {
        ResultSet result = dbConnector.selectQuery("SELECT * FROM User WHERE user_id = " + user.getUserId() + ";");

        try {
            while (result.next()) {
                if (result.getInt("user_id") == user.getUserId()) {
                    float totalAmount = result.getFloat("total_balance") + user.getTotalBalance();
                    dbConnector.updateQuery("UPDATE User SET total_balance = '" + totalAmount + "' WHERE user_id = " + user.getUserId() + ";");
                    user.setTotalBalance(totalAmount);
                }
            }
        } catch (SQLException e) {
            System.out.println(BALANCE_MESSAGE_1);
        }
    }

    /**
     * Updates users description for now
     *
     * @param user The user
     */
    public void updateUserInformation(User user) {
        ResultSet result = dbConnector.selectQuery("SELECT * FROM User WHERE user_id = " + user.getUserId() + ";");

        try {
            while (result.next()) {
                if (result.getInt("user_id") == user.getUserId()) {
                    dbConnector.insertQuery("UPDATE User SET description = '" + user.getDescription() + "' WHERE user_id = " + user.getUserId() + ";");
                }
            }
        } catch (SQLException e) {
            System.out.println(PROFILE_MESSAGE_2);
        }
    }

    /**
     * Gets the users information
     *
     * @return user User and its information
     */
    public ArrayList<Share> getUserInfo(String name) {
        ResultSet result = dbConnector.selectQuery("SELECT user_id FROM User WHERE nickname = '"+name+"';");
        ArrayList<Share> userInfo = null;
        try {
            while (result.next()) {
                int user_id = result.getInt("user_id");
                System.out.println("User id: "+user_id);
                result = dbConnector.selectQuery(
                        "SELECT Purchase.share_quantity, Share.price, Company.name " +
                        "FROM Share " +
                        "INNER JOIN Purchase ON Share.share_id = Purchase.share_id " +
                        "INNER JOIN Company ON Company.company_id = Purchase.company_id " +
                        "INNER JOIN User ON Purchase.user_id = '"+user_id+"';");

                userInfo = new ArrayList<Share>();
                while (result.next()) {

                    System.out.println(result.getInt("share_quantity"));
                    System.out.println(result.getFloat("price"));
                    System.out.println(result.getString("name"));
//                    userInfo.add(new Share(
//                            result.getObject("share_id"),
//                            result.getObject("company_id"),
//                            Float.parseFloat(result.getObject("price"))
//                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(PROFILE_MESSAGE_1);
        }
        return null;
    }
//    /**
//     * It will get all the user shares
//     *
//     * @return String[][] all users registered
//     */
//    public String[][] getUserShares(String name) {
//        String[][] shares;
//        ArrayList<Share> userShares = getUserInfo(name);
//        shares = new String[userShares.size()][4];
//
//        for (int i = 0; i < userShares.size(); i++){
//            shares[i][0] = userShares.get(i).getNickname();
//            shares[i][1] = userShares.get(i).getEmail();
//            shares[i][2] = String.valueOf(userShares.get(i).getStockValue());
//            shares[i][3] = String.valueOf(userShares.get(i).getTotalBalance());
//        }
//        return shares;
//    }
}
