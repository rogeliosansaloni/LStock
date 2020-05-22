package database;


import java.util.ArrayList;

import model.entities.CompanyDetail;
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
     * @return All registered users Arraylist
     */
    public ArrayList<User> getAllUsers() {
        ResultSet getUsers = dbConnector.selectQuery("SELECT * FROM User;");
        ArrayList<User> users = null;
        try {
            users = new ArrayList<User>();
            while (getUsers.next()) {
                float value = 0;
                if (userShare(getUsers.getObject("nickname").toString()) != null){
                    value = getUserValue((String) getUsers.getObject("nickname"));
                }
                users.add(new User(
                        getUsers.getObject("nickname").toString(),
                        getUsers.getObject("email").toString(),
                        //TODO add stock value to this user for SharesListView
                        value,
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
     * @return All registered users
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
     * @param name User nickname
     * @return userValue of all the shares owned
     */
    public float getUserValue(String name){

        ArrayList<CompanyDetail> userShares = userShare(name);
        float userValue = 0;
        for (CompanyDetail c : userShares){
            userValue += c.getShares()*c.getValue();
        }
        return userValue;
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
     * Gets the users share data from the database
     *
     * @param name Selected user name
     * @return Selected user information Arraylist
     */
    public ArrayList<CompanyDetail> userShare(String name) {
        ResultSet result = dbConnector.selectQuery("SELECT user_id FROM User WHERE nickname = '"+name+"';");
        ArrayList<CompanyDetail> userSharesList= null;
        try {
            while (result.next()) {
                int user_id = result.getInt("user_id");
                result = dbConnector.selectQuery(
                        "SELECT DISTINCT Purchase.share_quantity, Share.price, Company.name, Company.company_id FROM Share " +
                        "INNER JOIN Purchase ON Share.share_id = Purchase.share_id " +
                        "INNER JOIN Company ON Company.company_id = Purchase.company_id " +
                        "INNER JOIN User ON Purchase.user_id = '"+user_id+"';");
                userSharesList = new ArrayList<CompanyDetail>();
                while (result.next()) {
                    userSharesList.add(new CompanyDetail(
                            result.getInt("company_id"),
                            result.getString("name"),
                            result.getFloat("price"),
                            result.getInt("share_quantity")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println(PROFILE_MESSAGE_1);
        }
        return userSharesList;
    }
    /**
     * Returns all the shares of the selected user
     *
     * @param name Selected user name
     * @return Shares from selected user
     */
    public String[][] getUserShares(String name) {
        String[][] shares;
        ArrayList<CompanyDetail> userShares = userShare(name);
        shares = new String[userShares.size()][4];
        if (!userShares.isEmpty()){
            for (int i = 0; i < userShares.size(); i++){
                shares[i][0] = userShares.get(i).getName();
                shares[i][1] = String.valueOf(userShares.get(i).getShares());
                shares[i][2] = String.valueOf(userShares.get(i).getValue());
                shares[i][3] = String.valueOf(userShares.get(i).getShares()*userShares.get(i).getValue());
            }
            return shares;
        }
        return null;
    }
}
