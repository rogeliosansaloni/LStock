package database;


import model.entities.Bot;
import model.entities.Company;
import network.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BotDao {

    private DBConnector dbConnector;

    public BotDao(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }


    /**
     * Creates a bot associated to a company
     * @param bot bot to be created
     * @param company company to which the bot belongs to
     * @return id of the newly created bot. It will return -1 in case there is an error.
     */
    public int createBot(Bot bot, Company company) {
        // Create the new bot for the specified company
        dbConnector.insertQuery("INSERT INTO Bot (company_id, active_time, probability) " +
                "VALUES ('" + company.getCompanyId() + "','" + bot.getActiveTime() + "','" + bot.getProbability() + "')");

        // Get the latest bot created for the specified company
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM Bot WHERE company_id =" + company.getCompanyId() + "" +
                "ORDER BY created_at DESC LIMIT 1;");
        try {
            int botId = Integer.parseInt(verify.getObject("bot_id").toString());
            System.out.println("New bot with id " + botId + "has been created.");
            return botId;
        } catch (SQLException e) {
            System.out.println(String.format("Error in creating bot for %s", company.getName()));
        }
        return -1;
    }


    /**
     * It will get all the bots in the LStock
     */
    public ArrayList<Bot> getAllBots() {
        ResultSet retrievedBots = dbConnector.selectQuery("SELECT * FROM Bots;");
        ArrayList<Bot> bots = null;
        try {
            bots = new ArrayList<Bot>();
            while (retrievedBots.next()) {
                Bot bot = new Bot();
                bot.setBotId(Integer.parseInt(retrievedBots.getObject("id").toString()));
                bot.setActiveTime(Float.parseFloat(retrievedBots.getObject("active_time").toString()));
                bot.setProbability(Float.parseFloat(retrievedBots.getObject("probability").toString()));
                bot.setCompany(new Company(Integer.parseInt(retrievedBots.getObject("company_id").toString())));
                bots.add(bot);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all bots");
        }
        return bots;

    }


}