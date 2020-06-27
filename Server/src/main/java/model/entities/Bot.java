package model.entities;

import model.managers.StockManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a Bot
 */
public class Bot extends Thread {
    private static final String TRANSACTION_MESSAGE = "Bot %d has made a %s transaction.";
    private StockManager model;
    private int botId;
    private float activeTime;
    private float probability;
    private int status;
    private Company company;
    private final ArrayList<Purchase> shares = new ArrayList<>();

    /**
     * Bot constructor
     *
     * @param activeTime  the time the bot have to be active
     * @param probability Defines the probability of being bought or sold.
     * @param company     company the bot works for
     */

    public Bot(float activeTime, float probability, Company company) {
        this.activeTime = activeTime;
        this.probability = probability;
        this.company = company;
    }

    /**
     * Default bot constructor
     */
    public Bot() {

    }

    /**
     * Getters
     */
    public float getActiveTime() {
        return activeTime;
    }

    public float getProbability() {
        return probability;
    }

    public Company getCompany() {
        return company;
    }

    public int getBotId() {
        return botId;
    }

    public int getStatus() {
        return status;
    }

    /**
     * Setters
     */

    public void setBotId(int botId) {
        this.botId = botId;
    }

    public void setActiveTime(float activeTime) {
        this.activeTime = activeTime;
    }

    public void setProbability(float probability) {
        this.probability = probability;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public StockManager getModel() {
        return model;
    }

    public void setModel(StockManager model) {
        this.model = model;
    }

    @Override
    public void run() {
        try {
            transact();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the bot thread
     */
    public void stopBot() {
        interrupt();
    }

    /**
     * Bot activity control
     *
     * @throws InterruptedException
     */
    private void transact() throws InterruptedException {
        synchronized (shares) {
            while (status != 1) {
                System.out.println("The bot " + botId + " is inactive.");
                shares.wait();
            }
            System.out.println("The bot " + botId + " is active.");

            // Bot works every after X seconds
            Thread.sleep((long) (activeTime * 1000));

            Purchase purchase = new Purchase(1, company.getCompanyId(), model.getShareId(company.getCompanyId()), 1);

            // Checks if the bot should buy or sell according to probability
            double randomProb = new Random().nextDouble();
            if (randomProb * 100 >= probability) {
                System.out.println(String.format(TRANSACTION_MESSAGE, botId, "buy"));
                model.buyShare(purchase);
                shares.add(purchase);
            } else {
                System.out.println(String.format(TRANSACTION_MESSAGE, botId, "sell"));
                model.sellShare(purchase);
                shares.remove(purchase);
            }
            shares.notifyAll();
        }
    }
}
