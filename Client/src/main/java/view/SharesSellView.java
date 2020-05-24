package view;

import model.entities.CompanyChange;
import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SharesSellView extends JFrame {
    private static final int PANEL_WIDTH = 600;
    private static final int PANEL_HEIGHT = 400;
    private static final String TITLE = "SELL SHARES";
    private JPanel jpMain;
    private JScrollPane jpScroll;
    private JPanel jpTable;
    private JTextField[] jtShares;
    private JButton jbSell;
    private JButton jbExit;
    protected StockColors color;

    public SharesSellView() {
        color = new StockColors();

        this.setTitle(TITLE);
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(color.getBLACK());
        initUI();
    }

    public void initUI() {
        jpMain = new JPanel();
        jpMain.setLayout(new BorderLayout());
        jpMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jpMain.setBackground(color.getBLACK());
        this.add(jpMain, BorderLayout.CENTER);

        JPanel jpBotones = new JPanel(new GridLayout(1, 2, 50, 0));
        jpBotones.setBackground(Color.BLACK);
        int anchuraBoton = 200;
        int alturaBoton = 40;

        Font fuenteBotones = new Font("Roboto", Font.BOLD, 30);
        jbSell = new JButton("Sell");
        jbSell.setFont(fuenteBotones);
        jbSell.setForeground(Color.BLACK);
        jbSell.setBorder(null);
        jbSell.setBackground(color.getWHITE());
        jbSell.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jbExit = new JButton("Exit");
        jbExit.setFont(fuenteBotones);
        jbExit.setForeground(Color.BLACK);
        jbExit.setBorder(null);
        jbExit.setBackground(color.getWHITE());
        jbExit.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jpBotones.add(jbSell);
        jpBotones.add(jbExit);

        this.add(jpBotones, BorderLayout.SOUTH);
    }

}
