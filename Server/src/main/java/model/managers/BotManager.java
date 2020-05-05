package model.managers;

import database.BotDao;
import database.CompanyDao;
import model.entities.Bot;
import model.entities.Company;
import network.DedicatedServer;

import java.util.ArrayList;

public class BotManager {
    private DedicatedServer dedicatedServer;
    private BotDao botDao;
    private CompanyDao companyDao;

    public BotManager(BotDao botDao, CompanyDao companyDao) {
        this.botDao = botDao;
        this.companyDao = companyDao;
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
     */
    public void configureBot(int botId) {
        Bot bot = botDao.getBotById(botId);
        botDao.updateBot(bot);
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
