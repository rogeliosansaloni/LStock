package view;

import utils.StockColors;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;

public class CompanyView extends JPanel {
    private JTable tableCompanies;
    private JPanel jpCenter;
    private JPanel jpTable;
    protected StockColors color;

    public CompanyView() {
        color = new StockColors();
        jpCenter = new JPanel();
        jpTable = new JPanel();
        jpTable.setLayout(new GridLayout(5, 4, 50, 30));
        JLabel labelCompany = new JLabel(("COMPANY"));
        labelCompany.setHorizontalAlignment(SwingConstants.CENTER);
        jpTable.add(labelCompany);
        JLabel labelPrice = new JLabel(("PRICE 1"));
        labelPrice.setHorizontalAlignment(SwingConstants.CENTER);
        jpTable.add(labelPrice);
        JLabel labelChange = new JLabel("CHANGE");
        labelChange.setHorizontalAlignment(SwingConstants.CENTER);
        jpTable.add(labelChange);
        JLabel labelChange2 = new JLabel("% CHANGE");
        labelChange2.setHorizontalAlignment(SwingConstants.CENTER);
        jpTable.add(labelChange2);
        for(int i=0; i<4; i++){
            JLabel labelCompanyData = new JLabel(("COMPANY " + i+1));
            labelCompany.setHorizontalAlignment(SwingConstants.CENTER);
            jpTable.add(labelCompanyData);
            JLabel labelPriceData = new JLabel((i*30 + "€"));
            labelPriceData.setHorizontalAlignment(SwingConstants.CENTER);
            jpTable.add(labelPriceData);
            JLabel labelChangeData = new JLabel(i + "€");
            labelChangeData.setHorizontalAlignment(SwingConstants.CENTER);
            jpTable.add(labelChangeData);
            JLabel labelChange2Data = new JLabel("0.5%");
            labelChange2Data.setHorizontalAlignment(SwingConstants.CENTER);
            jpTable.add(labelChange2Data);
        }
        jpTable.setBackground(color.getBLACK());
        jpCenter.add(jpTable);
        jpCenter.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));
        jpCenter.setBackground(color.getBLACK());
        this.add(jpCenter);
        this.setBackground(color.getBLACK());
    }

    /**
     * Adds actionListener to load button
     * @param actionListener ActionListener
     */
    public void registerController(ActionListener actionListener) {

    }

    public void createTableCompanies (){
        String [] columnNames = {"COMPANY", "PRICE 1", "CHANGE", "% CHANGE"};
        String [][] dataCompanies = new String[4][4];
        for(int i=0; i<4; i++){
            dataCompanies[i][0] = "COMPANY " + i;
            dataCompanies[i][1] = i*30 + " €";
            dataCompanies[i][2] = i*2 + " €";
            dataCompanies[i][3] = "0'5 %";
        }
        tableCompanies = new JTable(dataCompanies, columnNames);
        jpTable = new JPanel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tableCompanies.setDefaultRenderer(String.class, centerRenderer);
        jpTable.add(tableCompanies);
        this.add(jpTable);
    }

    /**
     * Gets the amount selected
     * @return amount selected
     */

}
