package view;

import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.entities.Company;

public class CompanyView extends JPanel {
    private JScrollPane jpScroll;
    private JPanel jpTable;
    private JButton[][] jlCompanies;
    protected StockColors color;

    public CompanyView() {
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
        // Add an actionListener for each company
        for (int i = 0; i < jlCompanies.length; i++) {
            for (int j = 0; j < 4; j++) {
                jlCompanies[i][j].addActionListener(actionListener);
            }
        }
    }

    public void createColumnLabel(String text) {
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

    public void showCompanies(ArrayList<Company> companies){
        // Create a row for each company available
        jpTable.removeAll();
        jpTable.setLayout(new GridLayout(0, 4, 20, 20));
        createColumnLabel("COMPANY");
        createColumnLabel("PRICE 1");
        createColumnLabel("CHANGE (5 min)");
        createColumnLabel("% CHANGE (5 min)");
        jlCompanies = new JButton[companies.size()][4];
        System.out.println(companies);
        for (int i = 0; i < companies.size(); i++) {
            createDataLabel(companies.get(i).getName(), color.getWHITE(), i, 0);
            createDataLabel(companies.get(i).getValue() + "€", color.getGreenTable(), i, 1);
            createDataLabel( companies.get(i).getValue() + "€", color.getRedTable(), i, 2);
            float changePer = (companies.get(i).getValue()/ companies.get(i).getValue()) * 100;
            createDataLabel(changePer + "%", color.getGreenTable(), i, 3);
        }
    }

    /**
     * Gets the amount selected
     * @return amount selected
     */

}
