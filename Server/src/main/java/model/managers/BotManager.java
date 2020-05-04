package model.managers;

import database.BotDao;
import model.entities.Bot;
import model.entities.Company;
import network.DedicatedServer;

public class BotManager {
    private DedicatedServer dedicatedServer;
    private BotDao botDao;

    public BotManager(BotDao botDao) {
        this.botDao = botDao;
    }

    /**
     * Creates a bot
     *
     * @param bot     bot to be created
     * @param company company to which the bot is associated to
     * @return
     */
    public int createBot(Bot bot, Company company) {
        return botDao.createBot(bot, company);
    }

    /**
     * Updates the bot information
     *
     * @param botId id of the bot to be configured
     */
    public void configureBot(int botId) {
    }

    /**
     * Deletes a bot
     *
     * @param botId id of the bot to be deleted
     */
    public void deleteBot(int botId) {
        botDao.deleteBot(botId);
    }
}
