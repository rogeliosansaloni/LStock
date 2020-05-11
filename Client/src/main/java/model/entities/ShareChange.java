package model.entities;

public class ShareChange {

    private String name;
    private float actionValue;
    private float myActions;
    private float profitLoss;

    public ShareChange() {

    }

    public ShareChange(String name, float actionValue, float myActions, float profitLoss) {
        this.name = name;
        this.actionValue = actionValue ;
        this.myActions = myActions;
        this.profitLoss = profitLoss;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getActionValue() {
        return actionValue;
    }

    public void setActionValue(float actionValue) {
        this.actionValue = actionValue;
    }

    public float getMyActions() {
        return myActions;
    }

    public void setMyActions(float myActions) {
        this.myActions = myActions;
    }

    public float getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(float profitLoss) {
        this.profitLoss = profitLoss;
    }

}
