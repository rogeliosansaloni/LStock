package view;

import model.entities.CompanyDetail;
import model.entities.ShareSell;
import utils.StockColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class CompanyDetailView extends JPanel {
    private static final String ERROR_MESSAGE_1 = "The value introduced must be a number bigger than 0.";
    private static final String ERROR_MESSAGE_2 = "You don't have enough balance to buy %s shares.";
    private static final String ERROR_MESSAGE_3 = "All quantities introduced must be a number bigger than 0.";
    private static final String ERROR_MESSAGE_4 = "Introduce at least 1 valid number.";
    private static final String ERROR_MESSAGE_5 = "Sale cancelled. You are trying to sell more sells than you actually have.";
    private static final String LABEL_SHARES_BUY = "Number of shares you want to buy";
    private JPanel jpRightCenter;
    private JPanel jpLeft;
    private JPanel jpShareList;
    private JButton jbBuy;
    private JButton jbSell;
    private JButton jbBack;
    private JTextField jtBuyShares;
    private JTextField[] jtSellShares;
    private JLabel jlMyShares;
    private StockColors color;

    public CompanyDetailView() {
        //General window setup
        color = new StockColors();
        this.setLayout(new BorderLayout());
        this.setBackground(color.getBLACK());

        //Panel South
        JPanel jpRight = new JPanel();
        jpRight.setLayout(new BorderLayout());
        jpRight.setBackground(color.getBLACK());
        jpRight.setAlignmentX(SwingConstants.CENTER);

        //Panel South
        JPanel jpRightTop = new JPanel();
        jpRightTop.setLayout(new BorderLayout());
        jpRightTop.setBackground(color.getBLACK());
        jpRightTop.setAlignmentX(SwingConstants.CENTER);

        //Info shares of the client in the company
        Font fontMyShares = new Font("Roboto", Font.PLAIN, 18);
        jlMyShares = new JLabel("My Company Shares: 0");
        jlMyShares.setForeground(color.getWHITE());
        jlMyShares.setFont(fontMyShares);
        jlMyShares.setHorizontalAlignment(SwingConstants.CENTER);

        //A panel for the buy option
        JPanel jpBuy = new JPanel();
        jpBuy.setLayout(new FlowLayout());
        jpBuy.setBackground(color.getBLACK());
        jpBuy.setPreferredSize(new Dimension(400, 60));

        //Textfield number of shares to sell or buy
        Font fontTextfield = new Font("Roboto", Font.PLAIN, 18);
        jtBuyShares = new JTextField(LABEL_SHARES_BUY);
        jtBuyShares.setFont(fontTextfield);
        jtBuyShares.setForeground(Color.GRAY);
        jtBuyShares.setPreferredSize(new Dimension(290, 60));
        jtBuyShares.setBorder(null);
        jtBuyShares.setBackground(color.getTEXTFIELD());
        jtBuyShares.setHorizontalAlignment(SwingConstants.CENTER);
        jpBuy.add(jtBuyShares);

        Font buyFont = new Font("Roboto", Font.BOLD, 22);
        //Buy button
        jbBuy = new JButton("BUY");
        jbBuy.setActionCommand("buyShare");
        jbBuy.setBackground(color.getWHITE());
        jbBuy.setPreferredSize(new Dimension(90, 60));
        jbBuy.setFont(buyFont);
        jpBuy.add(jbBuy);

        jpRightTop.add(jlMyShares, BorderLayout.NORTH);
        jpRightTop.add(jpBuy, BorderLayout.CENTER);
        jpRightTop.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        jpRight.add(jpRightTop, BorderLayout.NORTH);

        //Panel South
        jpRightCenter = new JPanel();
        jpRightCenter.setLayout(new BorderLayout());
        jpRightCenter.setBackground(color.getBLACK());
        jpRightCenter.setAlignmentX(SwingConstants.CENTER);
        jpRightCenter.setPreferredSize(new Dimension(400, 300));
        jpRight.add(jpRightCenter, BorderLayout.CENTER);

        Font sellFont = new Font("Roboto", Font.BOLD, 25);
        //Sell button
        jbSell = new JButton("SELL SHARES");
        jbSell.setActionCommand("sellShare");
        jbSell.setBackground(color.getWHITE());
        jbSell.setPreferredSize(new Dimension(400, 50));
        jbSell.setFont(sellFont);

        //Left JPanel
        jpLeft = new JPanel(new BorderLayout());
        jpLeft.setBackground(color.getWHITE());
        jpLeft.setPreferredSize(new Dimension(600, this.getHeight()));

        jbBack = new JButton("BACK");
        jbBack.setActionCommand("back");
        jbBack.setBackground(color.getWHITE());
        jbBack.setPreferredSize(new Dimension(400, 50));
        jbBack.setFont(sellFont);
        jpRight.add(jbBack, BorderLayout.SOUTH);

        //Adding in the main panel
        this.add(jpRight, BorderLayout.EAST);
        this.add(jpLeft, BorderLayout.WEST);
        this.setBorder(new EmptyBorder(20, 30, 20, 30));
    }

    public void updateNumberShares(int numShares) {
        jlMyShares.setText("Your quantity of shares of this company is: " + numShares);
    }

    public void updateCompanyDetailView(ArrayList<CompanyDetail> companyDetails, float maxTotalValue) {
        updateNumberShares(companyDetails.get(0).getNumUserShares());
        int widthGraph = jpLeft.getWidth() - 20;
        int heightGraph = jpLeft.getHeight() - 70;
        JPanel jpGraph = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int y_axis_start = 60;
                int x_axis_start = 90;
                //Draw the y axis of the graph
                g2.drawLine(x_axis_start, y_axis_start, x_axis_start, heightGraph);
                //Draw the x axis of the graph
                g2.drawLine(x_axis_start, heightGraph, widthGraph, heightGraph);

                int y_increase = 34;
                int x_increase = 45;
                float value_increase = maxTotalValue / 10;
                int y_axis_lines = heightGraph;
                int x_axis_lines = x_axis_start;
                float value_y_axis = value_increase;
                Font fontAxis = new Font("Roboto", Font.PLAIN, 12);
                g2.setFont(fontAxis);
                //Draw the axis values
                for (int i = 0; i < 10; i++) {
                    y_axis_lines -= y_increase;
                    x_axis_lines += x_increase;

                    //Draw lines on the y axis
                    g2.drawLine(x_axis_start - 5, y_axis_lines, widthGraph, y_axis_lines);
                    //Draw values on the y axis
                    g2.drawString(Float.toString((int) value_y_axis), x_axis_start - 35, y_axis_lines + 4);
                    //Draw lines on the x axis
                    g2.drawLine(x_axis_lines, heightGraph - 5, x_axis_lines, heightGraph + 5);
                    //Draw the minutes on the x axis
                    g2.drawString(Integer.toString(10 - i), x_axis_lines - 3, heightGraph + 20);
                    value_y_axis += value_increase;

            }
            int limitYValues = heightGraph - y_axis_lines;
            int posYCandleSup;
            int posYCandleInf;
            int posYMaxValue;
            int posYMinValue;
            for(int i=0; i<10; i++) {
                //Draw the candles
                float valueOpen = companyDetails.get(i).getValueOpen();
                float valueClose = companyDetails.get(i).getValueClose();
                float maxValue = companyDetails.get(i).getMaxValue();
                float minValue = companyDetails.get(i).getMinValue();
                if(valueOpen != -1){
                    posYMaxValue = heightGraph - (int)((float)(limitYValues)/maxTotalValue*maxValue);
                    posYMinValue = heightGraph - (int)((float)(limitYValues)/maxTotalValue*minValue);
                    if(valueClose > valueOpen){
                        g2.setColor(color.getGREEN());
                        posYCandleSup = heightGraph - (int)((float)(limitYValues)/maxTotalValue*valueClose);
                        posYCandleInf = heightGraph - (int)((float)(limitYValues)/maxTotalValue*valueOpen);
                    } else{
                        g2.setColor(color.getRED());
                        posYCandleSup = heightGraph - (int)((float)(limitYValues)/maxTotalValue*valueOpen);
                        posYCandleInf = heightGraph - (int)((float)(limitYValues)/maxTotalValue*valueClose);
                    }
                    int widthCandle = 20;
                    g2.fillRect(x_axis_lines - widthCandle/2, posYCandleSup, widthCandle, posYCandleInf - posYCandleSup);
                    g2.setColor(color.getBLACK());
                    g2.setStroke(new BasicStroke(2));
                    g2.drawRect(x_axis_lines - widthCandle/2, posYCandleSup, widthCandle, posYCandleInf - posYCandleSup);
                    g2.drawLine(x_axis_lines, posYMaxValue, x_axis_lines, posYCandleSup);
                    g2.drawLine(x_axis_lines, posYCandleInf, x_axis_lines, posYMinValue);
                }
                x_axis_lines -= x_increase;
            }
            Font fontTitle = new Font("Roboto", Font.BOLD, 25);
            g2.setFont(fontTitle);
            g2.drawString("Shares Candlestick Chart", 150, y_axis_start - 20);
            Font fontNameAxis = new Font("Roboto", Font.PLAIN, 16);
            g2.setFont(fontNameAxis);
            g2.drawString("Minutes before last update", widthGraph/2 - 50, heightGraph + 50);
            g2.translate(x_axis_start - 55, heightGraph/2 + 65);
            g2.rotate(-Math.PI / 2);
            g2.drawString("Share value", 0, 0);
            }
        };
        jpGraph.setBackground(color.getWHITE());
        jpLeft.add(jpGraph);
    }

    public void updateSharesToSell(ArrayList<ShareSell> sharesSell) {
        jpRightCenter.removeAll();
        if (sharesSell.size() > 0) {
            jpShareList = new JPanel();
            jpShareList.removeAll();
            jpShareList.setLayout(new GridLayout(0, 3, 10, 10));
            jpShareList.setBackground(color.getBLACK());

            Font fontTitle = new Font("Roboto", Font.BOLD, 12);
            createLabel("STARTING PRICE", fontTitle, 0);
            createLabel("YOUR QUANTITY", fontTitle, 0);
            jpShareList.add(new JLabel());
            jtSellShares = new JTextField[sharesSell.size()];
            Font fontData = new Font("Roboto", Font.PLAIN, 20);
            for (int i = 0; i < sharesSell.size(); i++) {
                createLabel(sharesSell.get(i).getShareValue() + "€", fontData, i);
                createLabel(sharesSell.get(i).getShareQuantity() + "", fontData, i);
                jtSellShares[i] = new JTextField("");
                jtSellShares[i].setForeground(Color.GRAY);
                jtSellShares[i].setBackground(color.getTEXTFIELD());
                jtSellShares[i].setFont(fontData);
                jtSellShares[i].setHorizontalAlignment(SwingConstants.CENTER);
                jpShareList.add(jtSellShares[i]);
            }
            JScrollPane jpScrollShares = new JScrollPane(jpShareList);
            jpScrollShares.setBackground(color.getBLACK());
            jpScrollShares.setPreferredSize(new Dimension(400, 300));
            jpRightCenter.add(jpScrollShares, BorderLayout.CENTER);
            jpRightCenter.add(jbSell, BorderLayout.SOUTH);
        }
    }

    private void createLabel(String text, Font font, int i) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(color.getWHITE());
        label.setForeground(color.getBLACK());
        label.setOpaque(true);
        label.setFont(font);
        jpShareList.add(label);
    }

    public String getSharesBuy() {
        return jtBuyShares.getText();
    }

    public void setJtBuyShares(String text) {
        jtBuyShares.setText(text);
    }

    public String[] getSharesSell() {
        String[] textFields = new String[jtSellShares.length];
        for (int i = 0; i < jtSellShares.length; i++) {
            textFields[i] = jtSellShares[i].getText();
        }
        return textFields;
    }

    public void showErrorTextfield(int error) {
        switch (error) {
            case 1:
                JOptionPane.showMessageDialog(null, ERROR_MESSAGE_1);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, String.format(ERROR_MESSAGE_2, getSharesBuy()));
                break;
            case 3:
                JOptionPane.showMessageDialog(null, ERROR_MESSAGE_3);
                break;
            case 4:
                JOptionPane.showMessageDialog(null, ERROR_MESSAGE_4);
                break;
            case 5:
                JOptionPane.showMessageDialog(null, ERROR_MESSAGE_5);
                break;
        }
    }

    public int confirmAction(String message) {
        return JOptionPane.showConfirmDialog(null, message, "Confirm", JOptionPane.YES_NO_OPTION);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void registerController(ActionListener controller) {
        jbBuy.addActionListener(controller);
        jbSell.addActionListener(controller);
        jbBack.addActionListener(controller);
    }

    public void registerFocusController(FocusListener controller) {
        this.jtBuyShares.addFocusListener(controller);
    }
}
