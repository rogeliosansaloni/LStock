package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CompanyView extends JPanel {
    private JPanel jpCenter;
    private JPanel jpTable;
    private JButton[][] jlCompanies;
    private String[] companiesNames = {"Company 1", "Company 2", "Company 3", "Company 4"};
    protected StockColors color;

    public CompanyView() {
        color = new StockColors();
        this.setBackground(color.getBLACK());
        this.setLayout(new BorderLayout());
        jpTable = new JPanel();
        jpTable.setLayout(new GridLayout(5, 4, 20, 20));
        createColumnLabel("COMPANY");
        createColumnLabel("PRICE 1");
        createColumnLabel("CHANGE (5 min)");
        createColumnLabel("% CHANGE (5 min)");

        jlCompanies = new JButton[companiesNames.length][4];
        // Create a row for each company available
        for (int i = 0; i < companiesNames.length; i++) {
            createDataLabel("COMPANY " + (i + 1), color.getWHITE(), i, 0);
            createDataLabel((i + 1) * 100 + "€", color.getGreenTable(), i, 1);
            createDataLabel((i + 1) * 30 + "€", color.getRedTable(), i, 2);
            createDataLabel("0.5€", color.getGreenTable(), i, 3);
        }

        jpTable.setBackground(color.getBLACK());
        this.add(jpTable, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
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
                jlCompanies[i][j].setActionCommand(companiesNames[i]);
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

    /**
     * Gets the amount selected
     * @return amount selected
     */

}
