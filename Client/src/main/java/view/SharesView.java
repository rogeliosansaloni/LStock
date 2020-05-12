package view;

import model.entities.ShareChange;
import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SharesView extends JPanel {
    private JScrollPane jpScroll;
    private JPanel jpTable;
    private JButton[][] jlSells;
    private JLabel[][] jlCompanies;
    protected StockColors color;

    public SharesView() {
        color = new StockColors();
        this.setBackground(color.getBLACK());
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
        this.setBackground(color.getBLACK());
    }

    /**
     * Adds actionListener to load button
     *
     * @param actionListener ActionListener
     */
    public void registerController(ActionListener actionListener, ArrayList<ShareChange> shares) {
        // Add an actionListener for each company
        for (int i = 0; i < jlSells.length; i++) {
            for (int j = 0; j < 5; j++) {
                jlSells[i][j].addActionListener(actionListener);
                jlSells[i][j].setActionCommand(Integer.toString(shares.get(i).getCompanyId()));
            }
        }
    }

    private void createColumnLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(color.getWHITE());
        label.setForeground(color.getBLACK());
        label.setOpaque(true);
        Font font = new Font("Roboto", Font.BOLD, 25);
        label.setFont(font);
        jpTable.add(label);
    }

    public void createDataLabel(String text, Color c, int i, int j) {
        jlCompanies[i][j] = new JLabel(text);
        jlCompanies[i][j].setHorizontalAlignment(SwingConstants.CENTER);
        jlCompanies[i][j].setBackground(c);
        if (c == color.getWHITE()) {
            jlCompanies[i][j].setForeground(color.getBLACK());
        } else {
            jlCompanies[i][j].setForeground(color.getWHITE());
        }
        Font font = new Font("Roboto", Font.PLAIN, 25);
        jlCompanies[i][j].setFont(font);
        jpTable.add(jlCompanies[i][j]);
    }

    public void createDataLabelSmall(String text, Color c, int i, int j) {
        jlSells[i][j] = new JButton(text);
        jlSells[i][j].setBackground(color.getWHITE());
        jlSells[i][j].setPreferredSize(new Dimension(170, 40));
        Font font = new Font("Roboto", Font.PLAIN, 20);
        jlSells[i][j].setFont(font);
        jpTable.add(jlSells[i][j]);

    }

    public void showShares(ArrayList<ShareChange> shares){
        jpTable = new JPanel();
        jpTable.setBackground(color.getBLACK());
        // Create a row for each company available
        jpTable.setLayout(new GridLayout(0, 4, 20, 20));
        createColumnLabel("COMPANY");
        createColumnLabel("ACTION VALUE");
        createColumnLabel("MY ACTIONS");
        createColumnLabel("PROFIT & LOSS");
        for (int i = 0; i < shares.size(); i++) {
            createDataLabel (shares.get(i).getName(), color.getWHITE(), i, 0);
            createDataLabel(shares.get(i).getActionValue() + "€", color.getGreenTable(), i, 1);
            if(shares.get(i).getMyActions() < 0){
                createDataLabel( shares.get(i).getMyActions() + "€", color.getRedTable(), i, 2);
                createDataLabel(shares.get(i).getProfitLoss() + "%", color.getRedTable(), i, 3);
            } else if(shares.get(i).getMyActions() > 0){
                createDataLabel( shares.get(i).getMyActions() + "€", color.getGreenTable(), i, 2);
                createDataLabel(shares.get(i).getProfitLoss() + "%", color.getGreenTable(), i, 3);
            } else{
                createDataLabel( shares.get(i).getMyActions() + "€", color.getWHITE(), i, 2);
                createDataLabel(shares.get(i).getProfitLoss() + "%", color.getWHITE(), i, 3);
            }
            createDataLabelSmall("Sell All Shares", color.getWHITE(), i, 3);
        }
        jpScroll = new JScrollPane(jpTable);
        this.add(jpScroll, BorderLayout.CENTER);
    }
    public void showSellActionConfirmation(){
        final String message = "Do you really want to sell all your shares for %s?";
       // String.format(message, company.getName());
    }


    /**
     * Gets the amount selected
     * @return amount selected
     */
}

