package view;

import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SharesView extends JPanel {
    private JPanel jpCenter;
    private JPanel jpTable;
    private JButton[][] jlCompanies;
    private String[] companiesNames = {"Company 1", "Company 2", "Company 3", "Company 4"};;
    private JButton[][] jlShares;
    private String[] sharesNames = {"sell shares 1", "sell shares 2", "sell shares 3", "sell shares 4"};;
    protected StockColors color;
    JButton jbSell1;
    JButton jbSell2;
    JButton jbSell3;
    JButton jbSell4;

    public SharesView() {
        color = new StockColors();
        this.setBackground(color.getBLACK());
        this.setLayout(new BorderLayout());
        jpTable = new JPanel();
        jpTable.setLayout(new GridLayout(5, 5, 20, 20));
        Font font = new Font("Segoe UI Semibold", Font.PLAIN, 20);
        createWhiteLabel("COMPANY", Font.BOLD);
        createWhiteLabel("ACTION VALUE", Font.BOLD);
        createWhiteLabel("MY ACTIONS", Font.BOLD);
        createWhiteLabel("PROFIT & LOSS", Font.BOLD);

        for(int i=0; i<5; i++){
            createWhiteLabel("COMPANY" + (i+1), Font.PLAIN);
            createWhiteLabel( i*30 + "€", Font.PLAIN);
            createWhiteLabel(i*30 + "€", Font.PLAIN);
            createWhiteLabel("0.5€", Font.PLAIN);

            //Sell button
            switch (i) {
                case 1:
                    jbSell1 = new JButton("Sell all shares");
                    jbSell1.setActionCommand("sellAllShares1");
                    jbSell1.setBackground(color.getWHITE());
                    jbSell1.setPreferredSize(new Dimension(170, 40));
                    jbSell1.setFont(font);
                    break;
                case 2:
                    jbSell2 = new JButton("Sell all shares");
                    jbSell2.setActionCommand("sellAllShares2");
                    jbSell2.setBackground(color.getWHITE());
                    jbSell2.setPreferredSize(new Dimension(170, 40));
                    jbSell2.setFont(font);
                    break;
                case 3:
                    jbSell3 = new JButton("Sell all shares");
                    jbSell3.setActionCommand("sellAllShares3");
                    jbSell3.setBackground(color.getWHITE());
                    jbSell3.setPreferredSize(new Dimension(170, 40));
                    jbSell3.setFont(font);
                    break;
                case 4:
                    jbSell4 = new JButton("Sell all shares");
                    jbSell4.setActionCommand("sellAllShares4");
                    jbSell4.setBackground(color.getWHITE());
                    jbSell4.setPreferredSize(new Dimension(170, 40));
                    jbSell4.setFont(font);
                    break;

            }


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
        this.jbSell1.addActionListener(actionListener);
        this.jbSell2.addActionListener(actionListener);
        this.jbSell3.addActionListener(actionListener);
        this.jbSell4.addActionListener(actionListener);
    }

    public void createWhiteLabel(String text, int type){
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(color.getWHITE());
        label.setForeground(color.getBLACK());
        label.setOpaque(true);
        Font font = new Font("Roboto", Font.BOLD, 25);
        label.setFont(font);
        jpTable.add(label);

    }

    /**
     * Gets the amount selected
     * @return amount selected
     */

}