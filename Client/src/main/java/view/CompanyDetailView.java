package view;

import model.entities.CompanyDetail;
import utils.StockColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.Border;
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
        jtNumShares.setPreferredSize(new Dimension(200, 100));
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

        //Adding in the main panel
        this.add(jpRight, BorderLayout.EAST);
        this.setBorder(new EmptyBorder(20, 30, 20, 30));
    }

    public void updateNumberShares(int numShares) {
        jlMyShares.setText("Your quantity of shares of this company is: " + numShares);
    }

    public void updateCompanyDetailView(ArrayList<CompanyDetail> companyDetails, float maxValue, float minValue){
        updateNumberShares(companyDetails.get(0).getNumShares());
        int widthGraph = this.getWidth() - 60;
        int heightGraph = this.getHeight() -60;
        JPanel jpGraph = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                int y_axis_start = 20;
                int x_axis_start = 30;
                //Draw the y axis of the graph
                g.drawLine(x_axis_start, y_axis_start, x_axis_start, heightGraph);
                //Draw the x axis of the graph
                g.drawLine(x_axis_start, heightGraph, widthGraph, heightGraph);

                int heightYAxis = (heightGraph - y_axis_start);
                int widthXAxis = (widthGraph - x_axis_start);
                int y_axis_lines = heightYAxis/11;
                int x_axis_lines = widthXAxis/11;
                float value_y_axis = maxValue/10;
                //Draw the axis values
                for(int i=0; i<10; i++){
                    //Draw lines on the y axis
                    g.drawLine(x_axis_start - 5, y_axis_lines, widthGraph, y_axis_lines);
                    //Draw values on the y axis
                    g.drawString(Float.toString(value_y_axis), x_axis_start - 35, y_axis_lines + 4);
                    //Draw lines on the x axis
                    g.drawLine(x_axis_lines, heightGraph - 5, x_axis_lines, heightGraph + 5);
                    //Draw the minutes on the x axis
                    g.drawString(Integer.toString(9 - i), x_axis_lines - 3, heightGraph + 20);

                    //Draw the candles
                    float valueOpen = companyDetails.get(9-i).getValueOpen();
                    float valueClose = companyDetails.get(9-i).getValueClose();
                    int posYCandleSup;
                    int posYCandleInf;
                    if(valueOpen != valueClose){
                        if(valueOpen > valueClose){
                            g.setColor(color.getGREEN());
                            posYCandleSup = (int)((heightYAxis/maxValue)*valueOpen + y_axis_start);
                            posYCandleInf = (int)((heightYAxis/maxValue)*valueClose + y_axis_start);
                        } else{
                            g.setColor(color.getRED());
                            posYCandleSup = (int)((heightYAxis/maxValue)*valueClose + y_axis_start);
                            posYCandleInf = (int)((heightYAxis/maxValue)*valueOpen + y_axis_start);
                        }
                        int widthCandle = 20;
                        g.fillRect(x_axis_lines - widthCandle/2, posYCandleSup, 30, posYCandleInf);
                        g.setColor(color.getBLACK());
                    }

                    y_axis_lines -= heightYAxis/11;
                    x_axis_lines += widthXAxis/11;
                    value_y_axis += maxValue/10;
                }
            }
        };
        this.add(jpGraph, BorderLayout.WEST);
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
