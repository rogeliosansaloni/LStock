package view;

import model.entities.Company;
import model.entities.User;
import org.jfree.chart.JFreeChart;

import javax.swing.*;

public class CompanyDetailView {
    private static final String CANDLESTICK_TITLE = "Company Shares";
    private JButton jbBuy;
    private JButton jbSell;
    private JLabel jlActualPrice;
    private JLabel jlMyShares;
    private JFreeChart jfcCandleStick;

    public CompanyDetailView() {

    }

    public void updateHeader() {

    }

    public void updateShares(Company company, User user) {

    }

    public void showErrorMessages(String error) {

    }
}
