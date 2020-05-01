package model.entities;

/**
 * Represents the DTO that contains the action to do with company shares
 */
public class ShareTrade extends TunnelObject {
    private float sharePrice;
    private String actionToDo;

    /**
     * @param sharePrice price of the share
     * @param actionToDo action to do with the share: buy or sell
     */
    public ShareTrade(float sharePrice, String actionToDo){
        this.sharePrice=sharePrice;
        this.actionToDo=actionToDo;

    }


    // Getters and setters
    public String getActionToDo() {
        return actionToDo;
    }

    public void setActionToDo(String actionToDo) {
        this.actionToDo = actionToDo;
    }

    public float getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(float sharePrice) {
        this.sharePrice = sharePrice;
    }
}
