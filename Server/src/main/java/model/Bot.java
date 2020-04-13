package model;

public class Bot extends Thread{
    private int botId;
    private float activeTime;
    private float probability;
    private Company company;

    public Bot(float activeTime, float probability, Company company) {
        this.activeTime = activeTime;
        this.probability = probability;
        this.company = company;
    }

    public boolean checkBuySell(){
        return false;
    }
    public void makeTransaction(){}


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
}
