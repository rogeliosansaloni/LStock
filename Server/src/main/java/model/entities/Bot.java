package model.entities;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a Bot
 */
public class Bot extends Thread {
    private static final String TRANSACTION_MESSAGE = "Bot %d has made a %s transaction.";
    private int botId;
    private float activeTime;
    private float probability;
    private int status;
    private Company company;
    private ArrayList<Share> shares;

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
        this.shares = new ArrayList<>();
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

    @Override
    public void run() {
        try {
            transact();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void transact() throws InterruptedException {
        synchronized (shares) {
            while(status != 1) {
                shares.wait();
            }

            // Bot works every after X seconds
            Thread.sleep((long) (activeTime * 1000));
            // TODO: Add buy or sell action here
            Share share = new Share(); // TODO: Get the share that was bought or sold

            // Checks if the bot should buy or sell according to probability
            if (new Random().nextDouble() >= probability) {
                System.out.println(String.format(TRANSACTION_MESSAGE, botId, "buy"));
                shares.add(share);
            } else {
                System.out.println(String.format(TRANSACTION_MESSAGE, botId, "sell"));
                shares.remove(share);
            }

            shares.notifyAll();
        }
    }
}
