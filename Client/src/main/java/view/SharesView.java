package view;

import model.entities.Company;
import model.entities.CompanyChange;
import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SharesView extends JPanel {
    private JScrollPane jpScroll;
    private JPanel jpTable;
    private JButton[][] jlCompanies;
    private JButton[][] jlSell;
    private String[] sellNames = {"sellshares_1", "sellshares_2", "sellshares_3", "sellshares_4"};
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
     *
     * @param actionListener ActionListener
     */
    public void registerController(ActionListener actionListener) {
        //We add an actionListener for each company
        for (int i = 0; i < jlCompanies.length; i++) {
            for (int j = 0; j < 4; j++) {
                jlCompanies[i][j].addActionListener(actionListener);
            }
        }
        //We add an actionListener for each sell shares button
        for (int i = 0; i < jlSell.length; i++) {
            for (int j = 0; j < 4; j++) {
                jlSell[i][j].addActionListener(actionListener);
                jlSell[i][j].setActionCommand(sellNames[i]);
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

    private void createDataLabel(String text, Color c, int i, int j) {
        jlCompanies[i][j] = new JButton(text);
        jlCompanies[i][j].setHorizontalAlignment(SwingConstants.CENTER);
        jlCompanies[i][j].setBackground(c);
        if (c == color.getWHITE()) {
            jlCompanies[i][j].setForeground(color.getBLACK());
        } else {
            jlCompanies[i][j].setForeground(color.getWHITE());
        }
        jlCompanies[i][j].setBorderPainted(false);
        Font font = new Font("Roboto", Font.PLAIN, 25);
        jlCompanies[i][j].setFont(font);
        jpTable.add(jlCompanies[i][j]);
    }

    public void createDataLabelSmall(String text, Color c, int i, int j) {
        jlSell[i][j] = new JButton(text);
        jlSell[i][j].setBackground(color.getWHITE());
        jlSell[i][j].setPreferredSize(new Dimension(170, 40));
        Font font = new Font("Roboto", Font.PLAIN, 20);
        jlSell[i][j].setFont(font);
        jpTable.add(jlSell[i][j]);

    }

    public void showCompanies(ArrayList<CompanyChange> companies){
        // Create a row for each company available
        jpTable.removeAll();
        jpTable.setLayout(new GridLayout(0, 4, 20, 20));
        createColumnLabel("COMPANY");
        createColumnLabel("PRICE 1");
        createColumnLabel("CHANGE (5 min)");
        createColumnLabel("% CHANGE (5 min)");
        jlCompanies = new JButton[companies.size()][4];
        jlSell = new JButton[companies.size()][4];
        System.out.println(companies);
        for (int i = 0; i < companies.size(); i++) {
            createDataLabel(companies.get(i).getName(), color.getWHITE(), i, 0);
            createDataLabel(companies.get(i).getCurrentShare() + "€", color.getGreenTable(), i, 1);
            if(companies.get(i).getChange() < 0){
                createDataLabel( companies.get(i).getChange() + "€", color.getRedTable(), i, 2);
                createDataLabel(companies.get(i).getChangePer() + "%", color.getRedTable(), i, 3);
            } else if(companies.get(i).getChange() > 0){
                createDataLabel( companies.get(i).getChange() + "€", color.getGreenTable(), i, 2);
                createDataLabel(companies.get(i).getChangePer() + "%", color.getGreenTable(), i, 3);
            } else{
                createDataLabel( companies.get(i).getChange() + "€", color.getWHITE(), i, 2);
                createDataLabel(companies.get(i).getChangePer() + "%", color.getWHITE(), i, 3);
            }
            createDataLabelSmall("Sell All Shares", color.getWHITE(), i, 4);
        }
    }

    public void showSellActionConfirmation(Company company){
        final String message = "Do you really want to sell all your shares for %s?";
        String.format(message, company.getName());
    }

}

