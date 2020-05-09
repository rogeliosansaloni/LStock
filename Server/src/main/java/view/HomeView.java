package view;

import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeView extends JPanel {
    private static final String CARD_USERS = "List of Users";
    private static final String CARD_BOTS = "Manage Bots";
    private JButton jbUsers;
    private JButton jbBots;
    private JPanel jpButtons;
    private StockColors color;

    public HomeView() {
        color = new StockColors();
        this.setBackground(color.getWHITE());

        jpButtons = new JPanel(new GridLayout(1,2, 30 ,0));
        jpButtons.setBackground(Color.WHITE);
        int anchuraBoton = 200;
        int alturaBoton = 40;

        Font fuenteBotones = new Font("Segoe UI Semibold", Font.PLAIN, 20);
        jbUsers = new JButton("Users");
        jbUsers.setFont(fuenteBotones);
        jbUsers.setForeground(Color.BLACK);
        jbUsers.setBorder(null);
        jbUsers.setBackground(color.getYELLOW());
        jbUsers.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jbBots = new JButton("Manage Bots");
        jbBots.setFont(fuenteBotones);
        jbBots.setForeground(Color.BLACK);
        jbBots.setBorder(null);
        jbBots.setBackground(color.getYELLOW());
        jbBots.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jpButtons.add(jbUsers);
        jpButtons.add(jbBots);

        this.add(jpButtons);
    }

    /**
     * Adds actionListener to buttons
     * @param actionListener ActionListener
     */
    public void registerController(ActionListener actionListener) {
        jbUsers.addActionListener(actionListener);
        jbUsers.setActionCommand(CARD_USERS);
        jbBots.addActionListener(actionListener);
        jbBots.setActionCommand(CARD_BOTS);
    }
}
