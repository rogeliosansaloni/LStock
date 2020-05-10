package view;

import model.entities.CompanyDetail;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import utils.StockColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class CompanyDetailView extends JPanel {
    private JPanel jpRight;
    private JPanel jpGraph;
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

        //Panel Graph
        jpGraph = new JPanel(new BorderLayout());
        jpGraph.setBorder(new EmptyBorder(20, 0, 0, 30));
        //jpGraph.setBackground(color.getBLACK());

        //Adding in the main panel
        this.add(jpGraph, BorderLayout.CENTER);
        this.add(jpRight, BorderLayout.EAST);
        this.setBorder(new EmptyBorder(20, 30, 20, 30));
    }

    public void updateNumberShares(CompanyDetail companyDetail) {
        jlMyShares.setText("Your quantity of shares of " + companyDetail.getCompanyName() + ": " + companyDetail.getNumShares());
    }

    public void updateCompanyDetailView(ArrayList<CompanyDetail> companyDetails){
        updateNumberShares(companyDetails.get(0));
        /*
        final XYSeries series = new XYSeries("Data");
        series.add(1.0, 10);
        series.add(5.0, 20);
        series.add(10, 30);
        series.add(15, 40);
        final XYSeriesCollection data = new XYSeriesCollection(series);
        jfcCandleStick = ChartFactory.createXYLineChart("Stock", "Months", "Money", data, PlotOrientation.VERTICAL, true, true,false);
        jfcCandleStick.setBackgroundPaint(color.getBLACK());
        ChartPanel chart = new ChartPanel(jfcCandleStick);

        jpGraph.add(chart);
        */
        jpGraph.add(new JLabel("Hola"));
    }

    public void updateValue (String value) {

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
