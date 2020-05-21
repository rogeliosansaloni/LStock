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
        jpScroll.setBackground(color.getBLACK());
        this.add(jpAlert, BorderLayout.NORTH);
        this.add(jpScroll, BorderLayout.CENTER);
    }

    /**
     * Adds actionListener to load button
     * @param actionListener ActionListener
     */
    public void registerController(ActionListener actionListener, ArrayList<ShareChange> shares) {
        // Add an actionListener for each share
        for (int i = 0; i < shares.size(); i++) {
            jbSellShares[i].addActionListener(actionListener);
            jbSellShares[i].setActionCommand(Float.toString(shares.get(i).getShareId()));
        }
    }

    public void updateSharesView (ArrayList<ShareChange> shares){
        if(shares.size() == 0){
            jpAlert.setVisible(true);
        } else{
            jpAlert.setVisible(false);
            jpAlert.removeAll();
            jpTable.setPreferredSize(new Dimension(700, 100*shares.size()));
            createLabel("COMPANY", Font.BOLD, color.getWHITE());
            createLabel("ACTION VALUE", Font.BOLD, color.getWHITE());
            createLabel("MY ACTIONS", Font.BOLD, color.getWHITE());
            createLabel("PROFIT & LOSS", Font.BOLD, color.getWHITE());
            jpTable.add(new JLabel());
            jbSellShares = new JButton[shares.size()];
            for (int i = 0; i < shares.size(); i++) {
                createLabel(shares.get(i).getCompanyName(), Font.PLAIN, color.getWHITE());
                createLabel(shares.get(i).getShareValue() + "€", Font.PLAIN, color.getWHITE());
                createLabel(shares.get(i).getSharesQuantity() + "", Font.PLAIN, color.getWHITE());
                if(shares.get(i).getProfitLoss() < 0){
                    createLabel(shares.get(i).getProfitLoss() + "", Font.PLAIN, color.getRedTable());
                } else if(shares.get(i).getProfitLoss() > 0){
                    createLabel(shares.get(i).getProfitLoss() + "", Font.PLAIN, color.getGreenTable());
                } else{
                    createLabel(shares.get(i).getProfitLoss() + "", Font.PLAIN, color.getWHITE());
                }
                createSellButton(i);
            }
        }

    }

    public void createLabel(String text, int type, Color colorLabel){
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(colorLabel);
        label.setForeground(color.getBLACK());
        label.setOpaque(true);
        Font font = new Font("Roboto", type, 25);
        label.setFont(font);
        jpTable.add(label);
    }

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
