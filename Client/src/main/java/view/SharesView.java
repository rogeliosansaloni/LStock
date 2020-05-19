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
    private JButton[] jbSellShares;
    protected StockColors color;

    public SharesView() {
        color = new StockColors();
        this.setBackground(color.getBLACK());
        this.setLayout(new BorderLayout());
        jpTable = new JPanel();
        jpTable.setBackground(color.getBLACK());
        jpScroll = new JScrollPane(jpTable);
        this.add(jpScroll, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
        this.setBackground(color.getBLACK());
    }

    /**
     * Adds actionListener to load button
     * @param actionListener ActionListener
     */
    public void registerController(ActionListener actionListener) {

    }

    public void updateSharesView (ArrayList<ShareChange> shares){
        jpTable.removeAll();
        jpTable.setLayout(new GridLayout(0, 5, 20, 20));
        createLabel("COMPANY", Font.BOLD, color.getWHITE());
        createLabel("ACTION VALUE", Font.BOLD, color.getWHITE());
        createLabel("MY ACTIONS", Font.BOLD, color.getWHITE());
        createLabel("PROFIT & LOSS", Font.BOLD, color.getWHITE());
        jpTable.add(new JLabel());
        jbSellShares = new JButton[shares.size()];
        for (int i = 0; i < shares.size(); i++) {
            createLabel(shares.get(i).getCompanyName(), Font.PLAIN, color.getWHITE());
            createLabel(shares.get(i).getShareValue() + "€", Font.PLAIN, color.getWHITE());
            createLabel(shares.get(i).getMyActions() + "", Font.PLAIN, color.getWHITE());
            if(shares.get(i).getProfitLoss() < 0){
                createLabel(shares.get(i).getMyActions() + "", Font.PLAIN, color.getRedTable());
            } else if(shares.get(i).getProfitLoss() > 0){
                createLabel(shares.get(i).getMyActions() + "", Font.PLAIN, color.getGreenTable());
            } else{
                createLabel(shares.get(i).getMyActions() + "", Font.PLAIN, color.getWHITE());
            }
            createSellButton(shares.get(i).getProfitLoss() + "", i);
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

    public void createSellButton (String text, int i){
        jbSellShares[i] = new JButton(text);
        jbSellShares[i].setHorizontalAlignment(SwingConstants.CENTER);
        jbSellShares[i].setBackground(color.getWHITE());
        jbSellShares[i].setForeground(color.getBLACK());
        jbSellShares[i].setOpaque(true);
        Font font = new Font("Roboto", Font.PLAIN, 20);
        jbSellShares[i].setFont(font);
        jpTable.add(jbSellShares[i]);
    }

    /**
     * Gets the amount selected
     * @return amount selected
     */

}
