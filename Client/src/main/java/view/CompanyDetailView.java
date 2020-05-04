package view;

import model.entities.User;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class CompanyDetailView extends JPanel {
    //private static final String TITLE = "My Company Shares: ";
    private JButton jbBuy;
    private JButton jbSell;
    private JLabel jlActualPrice;
    private JLabel jlMyShares;
    private JFreeChart jfcCandleStick;
    protected StockColors color;

    public CompanyDetailView() {
        //General window setup
        color = new StockColors();
        this.setLayout(new BorderLayout());
        this.setBackground(color.getBLACK());

        //Panel South
        JPanel jpSouth = new JPanel(new FlowLayout());
        jpSouth.setBackground(color.getBLACK());
        Font font = new Font("Segoe UI Semibold", Font.PLAIN, 20);

        //Info shares of the client in the company
        jlMyShares = new JLabel("My Company Shares: 0          ");
        jlMyShares.setForeground(color.getWHITE());
        jlMyShares.setFont(font);

        //Sell button
        jbSell = new JButton("SELL SHARES");
        jbSell.setActionCommand("sellShare");
        jbSell.setBackground(color.getWHITE());
        jbSell.setPreferredSize(new Dimension(200,40));
        jbSell.setFont(font);

        //Buy button
        jbBuy = new JButton("BUY SHARES");
        jbBuy.setActionCommand("buyShare");
        jbBuy.setBackground(color.getWHITE());
        jbBuy.setPreferredSize(new Dimension(200,40));
        jbBuy.setFont(font);

        //Adding in the south panel
        jpSouth.add(jlMyShares);
        jpSouth.add(jbSell);
        jpSouth.add(jbBuy);

        //Panel Center
        JPanel jpCenter = new JPanel(new FlowLayout());
        jpCenter.setBackground(color.getBLACK());

        // Chart Example (Not valid)
        final XYSeries series = new XYSeries("Data");
        series.add(1.0, 10);
        series.add(5.0, 20);
        series.add(10, 30);
        series.add(15, 40);
        final XYSeriesCollection data = new XYSeriesCollection(series);
        jfcCandleStick = ChartFactory.createXYLineChart("Stock", "Months", "Money", data, PlotOrientation.VERTICAL, true, true,false);
        jfcCandleStick.setBackgroundPaint(color.getBLACK());
        ChartPanel chart = new ChartPanel(jfcCandleStick);
        chart.setPreferredSize(new Dimension(700,480));


        jpCenter.add(chart);
        //Adding in the main pannel
        this.add(jpSouth, BorderLayout.SOUTH);
        this.add(jpCenter, BorderLayout.CENTER);
    }

    public void updateNumberShares(User user) {
        jlMyShares.setText(Integer.toString(user.getShares().size()));
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
