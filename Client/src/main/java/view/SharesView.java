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
    private JButton[][] jlSell;
    private String[] sellNames = {"Sell All Shares 1", "Sell All Shares 2", "Sell All Shares 3", "Sell All Shares 4"};;
    protected StockColors color;

    public SharesView() {
        color = new StockColors();
        this.setBackground(color.getBLACK());
        this.setLayout(new BorderLayout());
        jpTable = new JPanel();
        jpTable.setLayout(new GridLayout(5, 5, 20, 20));

        for(int i=0; i<5; i++){
            createColumnLabel("COMPANY");
            createColumnLabel("PRICE 1");
            createColumnLabel("CHANGE (5 min)");
            createColumnLabel("% CHANGE (5 min)");
            jlCompanies = new JButton[companiesNames.length][4];
            jlSell = new JButton[sellNames.length][4];

            //We create a row for each company available.
            for(int i=0; i<companiesNames.length; i++) {
                creatDataLabel("COMPANY " + (i + 1), color.getWHITE(), i, 0);
                creatDataLabel((i + 1) * 100 + "€", color.getGreenTable(), i, 1);
                creatDataLabel((i + 1) * 30 + "€", color.getRedTable(), i, 2);
                creatDataLabel("0.5%", color.getGreenTable(), i, 3);
                createDataLabelSmall("Sell All Shares", color.getWHITE(), i, 4);

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
            for(int i=0; i<jlCompanies.length; i++){
                for(int j=0; j<4; j++){
                    jlCompanies[i][j].addActionListener(actionListener);
                    jlCompanies[i][j].setActionCommand(companiesNames[i]);
                }
            }
            //We add an actionListener for each sell shares button
            for(int i=0; i<jlSell.length; i++){
                for(int j=0; j<4; j++){
                    jlSell[i][j].addActionListener(actionListener);
                    jlSell[i][j].setActionCommand(sellNames[i]);
                }
            }
        }

    }

    public void createColumnLabel(String text){
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(color.getWHITE());
        label.setForeground(color.getBLACK());
        label.setOpaque(true);
        Font font = new Font("Roboto", Font.BOLD, 25);
        label.setFont(font);
        jpTable.add(label);
    }

    public void creatDataLabel(String text, Color c, int i, int j){
        jlCompanies[i][j] = new JButton(text);
        jlCompanies[i][j].setHorizontalAlignment(SwingConstants.CENTER);
        jlCompanies[i][j].setBackground(c);
        if(c == color.getWHITE()){
            jlCompanies[i][j].setForeground(color.getBLACK());
        } else{
            jlCompanies[i][j].setForeground(color.getWHITE());
        }
        jlCompanies[i][j].setBorderPainted(false);
        Font font = new Font("Roboto", Font.PLAIN, 25);
        jlCompanies[i][j].setFont(font);
        jpTable.add(jlCompanies[i][j]);
    }
    public void createDataLabelSmall(String text, Color c, int i, int j){
        jlSell[i][j] = new JButton(text);
        jlSell[i][j].setBackground(color.getWHITE());
        jlSell[i][j].setPreferredSize(new Dimension(170, 40));
        Font font = new Font("Roboto", Font.PLAIN, 25);
        jlSell[i][j].setFont(font);
        jpTable.add(jlSell[i][j]);

    }

    /**
     * Gets the amount selected
     * @return amount selected
     */
}

