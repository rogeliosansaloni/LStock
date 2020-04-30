package view;

import model.entities.User;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CompanyDetailView extends JPanel {
    private static final String TITLE = "Company Shares";
    private JButton jbBuy;
    private JButton jbSell;
    private JLabel jlActualPrice;
    private JLabel jlMyShares;
    private JFreeChart jfcCandleStick;

    public CompanyDetailView() {
        jbSell = new JButton("SELL SHARES");
        jbSell.setActionCommand("sellShare");
        jbBuy = new JButton("BUY SHARES");
        jbBuy.setActionCommand("buyShare");
    }

    public void updateNumberShares(User user) {
        jlMyShares.setText(Integer.toString(user.getShares().size()));
    }

    public void showErrorMessages(String error) {

    }

    public void registerController(ActionListener controller) {
        this.jbBuy.addActionListener(controller);
        this.jbSell.addActionListener(controller);
    }
}
