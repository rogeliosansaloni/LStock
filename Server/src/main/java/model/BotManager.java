package model;

import database.BotDao;
import network.DedicatedServer;

public class BotManager {
    private DedicatedServer dedicatedServer;
    private BotDao botDao;

    public BotManager(BotDao botDao) {
        this.botDao = botDao;
    }
    public void createBot(Bot bot){}
    public void configureBot(String company, int id){}
}
