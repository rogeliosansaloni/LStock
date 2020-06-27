package database;


import model.entities.Bot;
import model.entities.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Represents the DAO for the Bot table
 */
public class BotDao {
    private DBConnector dbConnector;

    public BotDao(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Creates a bot associated to a company
     *
     * @param bot     bot to be created
     * @return id of the newly created bot. It will return -1 in case there is an error.
     */
    public int createBot(Bot bot) {
        final String insertQuery = "INSERT INTO Bots (company_id, active_time, probability, activity_status) " +
                "VALUES (%d, %f, %f, TRUE);";
        final String selectQuery = "SELECT * FROM Bots WHERE company_id = %d ORDER BY created_at DESC LIMIT 1;";
        final String successMessage = "New bot with id %d has been created";
        final String errorMessage = "Error in creating bot for %s";

        // Create the new bot for the specified company
        dbConnector.insertQuery(String.format(insertQuery, bot.getCompany().getCompanyId(), bot.getActiveTime(),
                bot.getProbability()));

        // Get the latest bot created for the specified company
        ResultSet verify = dbConnector.selectQuery(String.format(selectQuery, bot.getCompany().getCompanyId()));
        try {
            while (verify.next()) {
                int botId = verify.getInt("bot_id");
                System.out.println(String.format(successMessage, botId));
                return botId;
            }
        } catch (SQLException e) {
            System.out.println(String.format(errorMessage, bot.getCompany().getCompanyId()));
        }
        return -1;
    }

    /**
     * Retrieves bot information by its id
     *
     * @param botId id of the bot
     * @return a Bot object that contains all the information of the bot
     */
    public Bot getBotById(int botId) {
        final String selectQuery = "SELECT * FROM Bots WHERE bot_id = %d;";
        final String errorMessage = "Error in getting bot information for bot with %d";

        ResultSet retrievedBot = dbConnector.selectQuery(String.format(selectQuery, botId));
        try {
            while (retrievedBot.next()) {
                return toBot(retrievedBot);
            }
        } catch (SQLException e) {
            System.out.println(String.format(errorMessage, botId));
        }
        return null;
    }

    /**
     * Gets all existing bots
     * @return a list of all existing bots
     */
    public ArrayList<Bot> getAllBots() {
        final String errorMessage = "Error getting all bots";
        ResultSet retrievedBots = dbConnector.selectQuery("SELECT c.name, bot_id, active_time, probability, activity_status" +
                " FROM Bots as b, Company as c WHERE b.company_id = c.company_id;");
        ArrayList<Bot> bots = null;
        try {
            bots = new ArrayList<Bot>();
            while (retrievedBots.next()) {
                bots.add(toBot(retrievedBots));
            }
        } catch (SQLException e) {
            System.out.println(errorMessage);
        }
        return bots;
    }

    /**
     * Gets all bots for a specific company
     * @param companyId company id
     * @return a list of all bots associated to a company
     */
    public ArrayList<Bot> getAllBotsByCompany(int companyId) {
        final String selectQuery = "SELECT * FROM Bots WHERE company_id = %d;";
        ResultSet retrievedBots = dbConnector.selectQuery(String.format(selectQuery, companyId));
        ArrayList<Bot> bots = null;
        try {
            bots = new ArrayList<Bot>();
            while (retrievedBots.next()) {
                bots.add(toBot(retrievedBots));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all bots");
        }
        return bots;
    }

    /**
     * Converts retrieved information into a Bot
     *
     * @param resultSet result set from database
     * @return a Bot object containing the information retrieved from the database
     * @throws SQLException
     */
    private Bot toBot(ResultSet resultSet) throws SQLException {
        Bot bot = new Bot();
        try {
            bot.setBotId(resultSet.getInt("bot_id"));
            bot.setActiveTime(resultSet.getFloat("active_time"));
            bot.setProbability(resultSet.getFloat("probability"));
            bot.setStatus(resultSet.getInt("activity_status"));
            bot.setCompany(new Company(resultSet.getString("name")));
        } catch (SQLException e) {
            // If column 'name' does not exist, parse the company_id
            bot.setCompany(new Company(resultSet.getInt("company_id")));
        }
        return bot;
    }

    /**
     * Erases a bot
     *
     * @param botId id of bot to erase
     */
    public boolean deleteBot(int botId) {
        final String selectQuery = "SELECT * FROM Bots WHERE bot_id = %d;";
        final String deleteQuery = "DELETE FROM Bots WHERE bot_id = %d;";
        final String successMessage = "Bot %d deleted";
        final String errorMessage = "Error deleting the bot with id %d";

        // Consult the database for the bot to be deleted
        ResultSet verify = dbConnector.selectQuery(String.format(selectQuery, botId));
        try {
            while (verify.next()) {
                // If the bot exists, delete it
                if (verify.getInt("bot_id") == botId) {
                    dbConnector.deleteQuery(String.format(deleteQuery, botId));
                    System.out.println(String.format(successMessage, botId));
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(String.format(errorMessage, botId));
        }
        return false;
    }

    /**
     * Updates the activity of a bot
     * @param botId id of the bot to be configured
     * @param activate indicates if the bot should be enabled or disabled
     * @return true if the bot activity has been changed. If not, false.
     */
    public int updateBot(int botId, String activate) {
        final String updateQuery = "UPDATE Bots SET activity_status = %s WHERE " +
                "bot_id = %d;";
        final String selectQuery = "SELECT * FROM Bots WHERE bot_id = %d;";
        final String errorMessage = "Error updating bot activity with id %d";
        final String successMessage = "Bot with id %d has been %s";


        Bot bot = getBotById(botId);

        // Determine if we should enable or disable the bot
        int newActivity;
        if (activate.equals("ENABLE")) {
            newActivity = 1;
        } else if (activate.equals("DISABLE")) {
            newActivity = 0;
        } else { // No change
            newActivity = bot.getStatus();
        }

        // Consult the database to get information on the bot to be updated
        ResultSet result = dbConnector.selectQuery(String.format(selectQuery, botId));
        try {
            while (result.next()) {
                // If the bot exists, update the information
                if (result.getInt("bot_id") == botId) {
                    dbConnector.updateQuery(String.format(updateQuery, newActivity, botId));
                    if(newActivity == 1) {
                        System.out.println(String.format(successMessage, botId, "enabled."));
                    } else {
                        System.out.println(String.format(successMessage, botId, "disabled."));
                    }
                    return newActivity;
                }
            }
        } catch (SQLException e) {
            System.out.println(String.format(errorMessage, botId));
        }
        return newActivity;
    }
}