package model.managers;

import database.BotDao;
import database.CompanyDao;
import database.DBConnector;
import model.entities.Bot;
import model.entities.Company;

import java.util.ArrayList;

/**
 * Represents the manager that controls all operations that can be
 * done to the bots
 */
public class BotManager {
    private BotDao botDao;
    private CompanyDao companyDao;

    /**
     * Creates and initializes the BotManager
     */
    public BotManager() {
        DBConnector dbConnector = new DBConnector();
        this.botDao = new BotDao(dbConnector);
        this.companyDao = new CompanyDao(dbConnector);
        dbConnector.connect();
    }

    /**
     * Creates a bot
     * @param bot bot to be created
     *
     * @return id of the bot. If the bot is not created, returns -1.
     */
    public int createBot(Bot bot) {
        Company company = companyDao.getCompanyByName(bot.getCompany().getName());
        bot.setCompany(company);
        return botDao.createBot(bot);
    }

    /**
     * Updates the bot information
     *
     * @param botId id of the bot to be configured
     * @param action indicates if we should enable or disable a bot
     */
    public void configureBot(int botId, String action) {
            botDao.updateBot(botId, action);
    }

    /**
     * Deletes a bot
     *
     * @param botId id of the bot to be deleted
     */
    public void deleteBot(int botId) {
        botDao.deleteBot(botId);
    }

    public ArrayList<Bot> getAllBots() {
        return botDao.getAllBots();
    }
}
