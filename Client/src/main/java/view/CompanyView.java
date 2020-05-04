package view;

import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CompanyView extends JPanel {
    private JPanel jpCenter;
    private JPanel jpTable;
    protected StockColors color;

    public CompanyView() {
        color = new StockColors();
        this.setBackground(color.getBLACK());
        this.setLayout(new BorderLayout());
        jpTable = new JPanel();
        jpTable.setLayout(new GridLayout(5, 4, 20, 20));
        createWhiteLabel("COMPANY", Font.BOLD);
        createWhiteLabel("PRICE 1", Font.BOLD);
        createWhiteLabel("CHANGE (5 min)", Font.BOLD);
        createWhiteLabel("% CHANGE (5 min)", Font.BOLD);

        for(int i=0; i<4; i++){
            createWhiteLabel("COMPANY" + (i+1), Font.PLAIN);
            createWhiteLabel( i*30 + "€", Font.PLAIN);
            createWhiteLabel(i*30 + "€", Font.PLAIN);
            createWhiteLabel("0.5€", Font.PLAIN);
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
        //We add an actionListener for each company
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

    /**
     * Gets the amount selected
     * @return amount selected
     */

}
