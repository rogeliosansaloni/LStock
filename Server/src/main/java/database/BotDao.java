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
     * It willl create a bot in the database
     *
     * @param bot the bot to be created
     */
    public void createBot(Bot bot, Company company) {
        boolean botExist = false;
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM Bot WHERE bot_id = " + bot.getBotId() + " AND company_id =" + company.getCompanyId() + ";");

        try {
            while (verify.next()) {

                System.out.println("This bot already exists.");
                botExist = true;

            }
            if (!botExist) {
                dbConnector.insertQuery("INSERT INTO Bot (bot_id, company_id) " +
                        "VALUES ('" + bot.getBotId() + "','" + company.getCompanyId() + "','" + bot.getActiveTime() + "','" + bot.getProbability() + "')");
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error creating bot for %s", company.getName()));
        }

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
                bot.setBotId(Integer.getInteger(retrievedBots.getObject("id").toString()));
                bot.setActiveTime(Float.parseFloat(retrievedBots.getObject("active_time").toString()));
                bot.setProbability(Float.parseFloat(retrievedBots.getObject("probability").toString()));
                bot.setCompany(new Company(Integer.getInteger(retrievedBots.getObject("company_id").toString())));
                bots.add(bot);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all bots");
        }
        return bots;

    }


}