package view;

import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SharesView extends JPanel {
    private JPanel jpCenter;
    private JPanel jpTable;
    protected StockColors color;

    public SharesView() {
        color = new StockColors();
        this.setBackground(color.getBLACK());
        this.setLayout(new BorderLayout());
        jpTable = new JPanel();
        jpTable.setLayout(new GridLayout(5, 5, 20, 20));
        createWhiteLabel("COMPANY", Font.BOLD);
        createWhiteLabel("ACTION VALUE", Font.BOLD);
        createWhiteLabel("MY ACTIONS", Font.BOLD);
        createWhiteLabel("PROFIT & LOSS", Font.BOLD);

        for(int i=0; i<5; i++){
            createWhiteLabel("COMPANY" + (i+1), Font.PLAIN);
            createWhiteLabel( i*30 + "€", Font.PLAIN);
            createWhiteLabel(i*30 + "€", Font.PLAIN);
            createWhiteLabel("0.5€", Font.PLAIN);
            createWhiteLabelSmall("Sell all shares", Font.PLAIN);
        }

        jpTable.setBackground(color.getBLACK());
        this.add(jpTable, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        this.setBackground(color.getBLACK());
    }

    /**
     * Adds actionListener to load button
     * @param actionListener ActionListener
     */
    public void registerController(ActionListener actionListener) {

    }

    public void updateSharesView (){

    }

    public void createWhiteLabel(String text, int type){
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(color.getWHITE());
        label.setForeground(color.getBLACK());
        label.setOpaque(true);
        Font font = new Font("Roboto", type, 25);
        label.setFont(font);
        jpTable.add(label);

    }

    public void createWhiteLabelSmall (String text, int type){
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(color.getWHITE());
        label.setForeground(color.getBLACK());
        label.setOpaque(true);
        Font font = new Font("Roboto", type, 20);
        label.setFont(font);
        jpTable.add(label);
    }

    /**
     * Gets the amount selected
     * @return amount selected
     */

}
