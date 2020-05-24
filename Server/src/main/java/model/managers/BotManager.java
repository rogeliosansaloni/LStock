package model.managers;

import database.BotDao;
import database.CompanyDao;
import database.DBConnector;
import model.entities.Bot;
import model.entities.Company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents the manager that controls all operations that can be
 * done to the bots
 */
public class BotManager {
    private ArrayList<Company> companies;
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

        // Initialize information
        companies = companyDao.getAllCompanyNames();
        updateCompanyBots();
    }

    /**
     * Creates a bot
     *
     * @param bot bot to be created
     * @return id of the bot. If the bot is not created, returns -1.
     */
    public int createBot(Bot bot) {
        Company company = companyDao.getCompanyByName(bot.getCompany().getName());
        bot.setCompany(company);
        //bot.start();
        return botDao.createBot(bot);
    }

    /**
     * Updates the bot information
     *
     * @param botId  id of the bot to be configured
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
    public boolean deleteBot(int botId) {
        return botDao.deleteBot(botId);
    }

    /**
     * Gets all existing
     *
     * @return a list of all existing bots
     */
    public ArrayList<Bot> getAllBots() {
        return botDao.getAllBots();
    }

    /**
     * Gets all registered companies
     *
     * @return a list of all registered companies
     */
    public ArrayList<Company> getCompanies() {
        return companies;
    }

    /**
     * Updates bots of the companies
     */
    public void updateCompanyBots() {
        for (Company company : companies) {
            ArrayList<Bot> bots = getAllBotsByCompany(company.getCompanyId());
            company.setBots(bots);
        }
    }

    /**
     * Filters all the companies with bots
     * @return companies with bots
     */
    public ArrayList<Company> getCompaniesWithBots() {
        return companies.stream().filter(company -> !company.getBots().isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Gets the company id
     *
     * @param company the company
     * @return the id of the company
     */
    public int getCompanyId(String company) {
        for (Company c : companies) {
            if (c.getName().equals(company)) {
                return c.getCompanyId();
            }
        }
        return -1;
    }

    /**
     * Gets all bots associated to a specific company
     *
     * @param companyId id of the company
     * @return a list of all bots for a specific company
     */
    public ArrayList<Bot> getAllBotsByCompany(int companyId) {
        return botDao.getAllBotsByCompany(companyId);
    }

    /**
     * Gets bot information
     *
     * @param botId id of the bot
     * @return a Bot that contains all of its information
     */
    public Bot getBot(int botId) {
        return botDao.getBotById(botId);
    }
}
