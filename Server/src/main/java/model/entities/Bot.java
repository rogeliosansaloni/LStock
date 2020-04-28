package model.entities;

/**
 * Represents a Bot
 */
public class Bot extends Thread {
    private int botId;
    private float activeTime;
    private float probability;
    private Company company;

    /**
     * It will create a bot
     *
     * @param activeTime the time the bot have to be active
     * @param probability Defines the probability of being bought or sold.
     * @param company   company the bot works for
     */

    public Bot(float activeTime, float probability, Company company) {
        this.activeTime = activeTime;
        this.probability = probability;
        this.company = company;
    }

    public boolean checkBuySell() {
        return false;
    }

    public void makeTransaction() {
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
}
