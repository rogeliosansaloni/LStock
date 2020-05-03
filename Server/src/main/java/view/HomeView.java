package view;

import utils.StockColors;

import javax.swing.*;

public class HomeView extends JPanel {
    private JButton jbUsers;
    private JButton jbBots;
    private StockColors color;

    public HomeView() {
        color = new StockColors();
        this.setBackground(color.getWHITE());

    }
}
