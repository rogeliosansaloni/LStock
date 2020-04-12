package model;

public class Bot extends Thread{
    private float activeTime;
    private float probability;
    private Company company;

    public Bot(Runnable target, float activeTime, float probability, Company company) {
        super(target);
        this.activeTime = activeTime;
        this.probability = probability;
        this.company = company;
    }

    public boolean checkBuySell(){
        return false;
    }
    public void makeTransaction(){}
}
