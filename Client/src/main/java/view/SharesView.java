package view;

import model.entities.ShareChange;
import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The Profile View
 */

public class SharesView extends JPanel {
    private JScrollPane jpScroll;
    private JPanel jpTable;
    private JPanel jpAlert;
    private JButton[] jbSellShares;
    protected StockColors color;

    public SharesView() {
        color = new StockColors();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
        this.setBackground(color.getBLACK());

        jpAlert = new JPanel();
        jpAlert.setBackground(color.getDarkGreyHeader());
        JLabel label = new JLabel("You don't have any shares yet.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(color.getDarkGreyHeader());
        label.setForeground(color.getWHITE());
        label.setOpaque(true);
        Font font = new Font("Roboto", Font.PLAIN, 40);
        label.setFont(font);
        jpAlert.add(label);

        //Let's create the table
        jpTable = new JPanel();
        jpTable.setBackground(color.getBLACK());
        jpTable.setLayout(new GridLayout(0, 5, 5, 20));

        jpScroll = new JScrollPane(jpTable);
        jpScroll.setBorder(null);
        jpScroll.setBackground(color.getBLACK());
        this.add(jpAlert, BorderLayout.NORTH);
        this.add(jpScroll, BorderLayout.CENTER);
    }

    /**
     * Adds actionListener to load the sell all shares buttons
     * @param actionListener ActionListener
     */
    public void registerController(ActionListener actionListener, ArrayList<ShareChange> shares) {
        // Add an actionListener for each share
        for (int i = 0; i < shares.size(); i++) {
            jbSellShares[i].addActionListener(actionListener);
            jbSellShares[i].setActionCommand(Integer.toString(shares.get(i).getShareId()));
        }
    }

    /**
     * Updates the list of shares the user has
     * @param shares arraylist with the information of the shares
     */
    public void updateSharesView (ArrayList<ShareChange> shares){
        jpTable.removeAll();
        if(shares.size() == 0){
            jpAlert.setVisible(true);
        } else{
            jpAlert.setVisible(false);
            jpTable.setPreferredSize(new Dimension(700, 100*shares.size()));
            Font fontTitle = new Font("Roboto", Font.BOLD, 20);
            createLabel("COMPANY", fontTitle, color.getWHITE());
            createLabel("CURRENT VALUE", fontTitle, color.getWHITE());
            createLabel("MY ACTIONS", fontTitle, color.getWHITE());
            createLabel("PROFIT & LOSS", fontTitle, color.getWHITE());
            jpTable.add(new JLabel());
            jbSellShares = new JButton[shares.size()];
            Font fontInfo = new Font("Roboto", Font.PLAIN, 24);
            for (int i = 0; i < shares.size(); i++) {
                createLabel(shares.get(i).getCompanyName(), fontInfo, color.getWHITE());
                createLabel(shares.get(i).getShareCurrentValue() + "€", fontInfo, color.getWHITE());
                createLabel(shares.get(i).getSharesQuantity() + "", fontInfo, color.getWHITE());
                if(shares.get(i).getProfitLoss() < 0){
                    createLabel(shares.get(i).getProfitLoss() + "", fontInfo, color.getRedTable());
                } else if(shares.get(i).getProfitLoss() > 0){
                    createLabel(shares.get(i).getProfitLoss() + "", fontInfo, color.getGreenTable());
                } else{
                    createLabel(shares.get(i).getProfitLoss() + "", fontInfo, color.getWHITE());
                }
                createSellButton(i);
            }
            jpScroll.revalidate();
            jpScroll.repaint();
        }
    }
    /**
     * Creates a label for the table of shares
     * @param text the text to put in the label
     * @param font the type of the font
     * @param colorLabel the color of the label
     */
    public void createLabel(String text, Font font, Color colorLabel){
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(colorLabel);
        label.setForeground(color.getBLACK());
        label.setOpaque(true);
        label.setFont(font);
        jpTable.add(label);
    }

    /**
     * Creates a button to sell all the shares
     * @param i the index of the array of buttons
     */
    public void createSellButton (int i){
        jbSellShares[i] = new JButton("SELL ALL SHARES");
        jbSellShares[i].setHorizontalAlignment(SwingConstants.CENTER);
        jbSellShares[i].setBackground(color.getWHITE());
        jbSellShares[i].setForeground(color.getBLACK());
        jbSellShares[i].setOpaque(true);
        Font font = new Font("Roboto", Font.PLAIN, 16);
        jbSellShares[i].setFont(font);
        jpTable.add(jbSellShares[i]);
    }

}
