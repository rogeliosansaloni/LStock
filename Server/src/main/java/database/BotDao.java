package database;


import network.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;
import java.util.ArrayList;


public class BotDao {

    private DBConnector dbConnector;

    public BotDao (DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }


    /**
     * It willl create a bot in the database
     * @param bot the bot to be created
     */
    public void createBot (Bot bot, Company company) {
        boolean botExist = false;
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM Bot WHERE bot_id = " + bot.getBotId() + " AND company_id =" + company.getCompanyId() + ";";

        try {
            while (verify.next()) {
                if (verify.next().equals("bot_id") &&verify.next().equals("company_id")) {
                    System.out.println("This bot already exists.");
                    botExist = true;
                }
            }
            if(!botExist){
                dbConnector.insertQuery("INSERT INTO Bot (bot_id, company_id) " +
                        "VALUES ('" + bot.getBotId() + "','" + company.getCompanyId() + "','" + bot.getActiveTime() + "','" + bot.getProbability() + "')");
            }
        }catch (SQLException e) {
            System.out.println(String.format("Error creating bot for %s", company.getName()));
        }

    }

    /**
     * It will permit to erase a bot
     * @param bot Bot to erase
     */
    public boolean deleteBot(Bot bot) {
        ResultSet verify = dbConnector.selectQuery("SELECT * FROM Share WHERE bot_id = " + bot.getBotId() +" AND company_id =" + company.getCompanyId() + ";";
        try {
            while (verify.next()) {
                if (verify.next().equals("bot_id") &&verify.next().equals("company_id")) {
                    dbConnector.deleteQuery("DELETE FROM Bot WHERE bot_id = " + bot.getBotId() + " AND company_id =" + company.getCompanyId() + ";");
                    System.out.println("Bot Deleted");
                    return true;
                }
            }

        }catch (SQLException e) {
            System.out.println("Error deleting bot");
        }
        return false;
    }

    /**
     * It will get all the bots in the LStock
     */
        public ArrayList<String> getAllBots () {
        ResultSet getBots = dbConnector.selectQuery("SELECT * FROM Bots;");
        ArrayList<String> bots = null;
        try {
            bots = new ArrayList<String>();
            while (getBots.next()){
                bots.add(getBots.getObject("name").toString());
            }
        } catch (SQLException e) {
            System.out.println("Error getting all bots");
        }
        return bots;

    }



}
