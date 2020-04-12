package model;

import database.BotDao;

public class BotManager {
    //private DedicateServer dedicatedServer;
    private BotDao botDao;

    public BotManager(BotDao botDao) {
        this.botDao = botDao;
    }
    public void createBot(Bot bot){}
    public void configureBot(String company, int id){}
}
