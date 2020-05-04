package view;

import utils.StockColors;

import javax.swing.*;
import java.awt.*;

public class SharesView extends JPanel {
    private JTable tableShares;
    private JPanel jpCenter;
    private JPanel jpTable;
    protected StockColors color;
    public SharesView() {
        color = new StockColors();
        jpCenter = new JPanel();
        jpTable = new JPanel();
        jpTable.setLayout(new GridLayout(5, 4, 50, 30));
        JLabel labelCompany = new JLabel(("COMPANY"));
        labelCompany.setHorizontalAlignment(SwingConstants.CENTER);
        jpTable.add(labelCompany);
        JLabel labelPrice = new JLabel(("ACTION VALUE"));
        labelPrice.setHorizontalAlignment(SwingConstants.CENTER);
        jpTable.add(labelPrice);
        JLabel labelChange = new JLabel("MY ACTIONS");
        labelChange.setHorizontalAlignment(SwingConstants.CENTER);
        jpTable.add(labelChange);
        JLabel labelChange2 = new JLabel("PROFITS & LOSS");
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
