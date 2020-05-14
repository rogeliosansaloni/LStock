package view;

import model.entities.CompanyDetail;
import utils.StockColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class CompanyDetailView extends JPanel {
    private static final String ERROR_MESSAGE_1 = "The value introduced must be a positive number.";
    private static final String ERROR_MESSAGE_2 = "You don't hace enough balance to buy ";
    private JPanel jpRight;
    private JPanel jpLeft;
    private JPanel jpButtons;
    private JButton jbBuy;
    private JButton jbSell;
    private JTextField jtNumShares;
    private JLabel jlMyShares;
    protected StockColors color;

    public CompanyDetailView() {
        //General window setup
        color = new StockColors();
        this.setLayout(new BorderLayout());
        this.setBackground(color.getBLACK());

        //Panel South
        jpRight = new JPanel();
        jpRight.setLayout(new BorderLayout());
        jpRight.setBackground(color.getBLACK());
        jpRight.setAlignmentX(SwingConstants.CENTER);
        jpRight.setPreferredSize(new Dimension(400, this.getHeight()));

        //Info shares of the client in the company
        Font fontMyShares = new Font("Roboto", Font.PLAIN, 22);
        jlMyShares = new JLabel("My Company Shares: 0");
        jlMyShares.setForeground(color.getWHITE());
        jlMyShares.setFont(fontMyShares);
        jlMyShares.setHorizontalAlignment(SwingConstants.CENTER);
        jlMyShares.setBorder(BorderFactory.createEmptyBorder(120, 0, 50, 0));

        //Textfield number of shares to sell or buy
        Font fontTextfield = new Font("Roboto", Font.PLAIN, 20);
        jtNumShares = new JTextField("Number of shares to sell/buy");
        jtNumShares.setFont(fontTextfield);
        jtNumShares.setForeground(Color.GRAY);
        jtNumShares.setBorder(null);
        jtNumShares.setBackground(color.getTEXTFIELD());
        jtNumShares.setHorizontalAlignment(SwingConstants.CENTER);
        jtNumShares.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jtNumShares.getText().equals("Number of shares to sell/buy")) {
                    jtNumShares.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jtNumShares.getText().equals("")) {
                    jtNumShares.setText("Number of shares to sell/buy");
                }
            }
        });
        //A panel for the buttons
        jpButtons = new JPanel();
        jpButtons.setLayout(new GridLayout(1, 2, 20, 0));
        jpButtons.setBorder(BorderFactory.createEmptyBorder(40, 0, 120, 0));
        jpButtons.setBackground(color.getBLACK());
        Font fontButtons = new Font("Roboto", Font.BOLD, 25);
        //Sell button
        jbSell = new JButton("SELL");
        jbSell.setActionCommand("sellShare");
        jbSell.setBackground(color.getWHITE());
        jbSell.setPreferredSize(new Dimension(200,40));
        jbSell.setFont(fontButtons);
        jpButtons.add(jbSell);

        //Buy button
        jbBuy = new JButton("BUY");
        jbBuy.setActionCommand("buyShare");
        jbBuy.setBackground(color.getWHITE());
        jbBuy.setPreferredSize(new Dimension(200,40));
        jbBuy.setFont(fontButtons);
        jpButtons.add(jbBuy);

        //Adding in the right panel
        jpRight.add(jlMyShares, BorderLayout.NORTH);
        jpRight.add(jtNumShares, BorderLayout.CENTER);
        jpRight.add(jpButtons, BorderLayout.SOUTH);

        //Left JPanel
        jpLeft = new JPanel(new BorderLayout());
        jpLeft.setBackground(color.getWHITE());
        jpLeft.setPreferredSize(new Dimension(600, this.getHeight()));

        //Adding in the main panel
        this.add(jpRight, BorderLayout.EAST);
        this.add(jpLeft, BorderLayout.WEST);
        this.setBorder(new EmptyBorder(20, 30, 20, 30));
    }

    public void updateNumberShares(int numShares) {
        jlMyShares.setText("Your quantity of shares of this company is: " + numShares);
    }

    public void updateCompanyDetailView(ArrayList<CompanyDetail> companyDetails, float maxTotalValue){
        updateNumberShares(companyDetails.get(0).getNumShares());
        int widthGraph = jpLeft.getWidth() - 60;
        int heightGraph = jpLeft.getHeight() -60;
        JPanel jpGraph = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                int y_axis_start = 20;
                int x_axis_start = 40;
                //Draw the y axis of the graph
                g.drawLine(x_axis_start, y_axis_start, x_axis_start, heightGraph);
                //Draw the x axis of the graph
                g.drawLine(x_axis_start, heightGraph, widthGraph, heightGraph);

                int widthXAxis = (widthGraph - x_axis_start);
                int y_increase = 37;
                int x_increase = 47;
                System.out.println("Max value: " + maxTotalValue);
                System.out.println("heightGraph: " + heightGraph);
                float value_increase = maxTotalValue/10;
                int y_axis_lines = heightGraph;
                int x_axis_lines = x_axis_start;
                float value_y_axis = value_increase;
                //Draw the axis values
                for(int i=0; i<10; i++) {
                    y_axis_lines -= y_increase;
                    x_axis_lines += x_increase;

                    //Draw lines on the y axis
                    g.drawLine(x_axis_start - 5, y_axis_lines, widthGraph, y_axis_lines);
                    //Draw values on the y axis
                    g.drawString(Float.toString((int)value_y_axis), x_axis_start - 35, y_axis_lines + 4);
                    //Draw lines on the x axis
                    g.drawLine(x_axis_lines, heightGraph - 5, x_axis_lines, heightGraph + 5);
                    //Draw the minutes on the x axis
                    g.drawString(Integer.toString(10 - i), x_axis_lines - 3, heightGraph + 20);
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
                    if(valueOpen != valueClose){
                        posYMaxValue = heightGraph - (int)((float)(limitYValues)/maxTotalValue*maxValue);
                        posYMinValue = heightGraph - (int)((float)(limitYValues)/maxTotalValue*minValue);
                        if(valueClose > valueOpen){
                            g.setColor(color.getGREEN());
                            posYCandleSup = heightGraph - (int)((float)(limitYValues)/maxTotalValue*valueClose);
                            posYCandleInf = heightGraph - (int)((float)(limitYValues)/maxTotalValue*valueOpen);
                        } else{
                            g.setColor(color.getRED());
                            posYCandleSup = heightGraph - (int)((float)(limitYValues)/maxTotalValue*valueOpen);
                            posYCandleInf = heightGraph - (int)((float)(limitYValues)/maxTotalValue*valueClose);
                        }
                        int widthCandle = 20;
                        g.fillRect(x_axis_lines - widthCandle/2, posYCandleSup, widthCandle, posYCandleInf - posYCandleSup);
                        g.setColor(color.getBLACK());
                        Graphics2D g2 = (Graphics2D) g;
                        g2.setStroke(new BasicStroke(2));
                        g.drawRect(x_axis_lines - widthCandle/2, posYCandleSup, widthCandle, posYCandleInf - posYCandleSup);
                        g.drawLine(x_axis_lines, posYMaxValue, x_axis_lines, posYCandleSup);
                        g.drawLine(x_axis_lines, posYCandleInf, x_axis_lines, posYMinValue);
                    }
                    x_axis_lines -= x_increase;
                }
            }
        };
        jpGraph.setBackground(color.getWHITE());
        jpLeft.add(jpGraph);
    }

    public String getNumShares (){
        String numShares = jtNumShares.getText();
        return numShares;
    }

    public void showErrorTextfield(int error){
        switch (error) {
            case 1:
                JOptionPane.showMessageDialog(null, ERROR_MESSAGE_1);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, ERROR_MESSAGE_2 + getNumShares() + " shares.");
                break;
        }

    }

    public int confirmAction(String message) {
        int verify = JOptionPane.showConfirmDialog(null, message, "Confirm", JOptionPane.YES_NO_OPTION);
        return verify;
    }

    public void showErrorMessage(String message){
         JOptionPane.showMessageDialog(null, message);
    }

    public void registerController(ActionListener controller) {
        this.jbBuy.addActionListener(controller);
        this.jbSell.addActionListener(controller);
    }
}
