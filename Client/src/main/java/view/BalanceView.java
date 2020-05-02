package view;

import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BalanceView extends JPanel {
    private JLabel jlCurrent;
    private JButton jbLoad;
    private JComboBox<String> jcbAmount;
    protected StockColors color;

    public BalanceView () {
        color = new StockColors();
        //TODO: BalanceView
        this.setBackground(color.getBLACK());
        String amount[] = {"10.00$", "20.00$", "30.00$", "40.00$"};
        this.setLayout(new GridLayout(3,1));
        jlCurrent = new JLabel("You have: 00.00$");
        JPanel jpAmount = new JPanel(new GridLayout(1, 2));
        JLabel jLabel = new JLabel("Amount to Load: ");
        jcbAmount = new JComboBox<>(amount);
        jpAmount.add(jLabel);
        jpAmount.add(jcbAmount);
        jbLoad = new JButton("Load");

        this.add(jlCurrent);
        this.add(jpAmount);
        this.add(jbLoad);
    }

    /**
     * Adds actionListener to load button
     * @param actionListener ActionListener
     */
    public void registerController(ActionListener actionListener) {
        jbLoad.addActionListener(actionListener);
        jbLoad.setActionCommand("load");
    }

    /**
     * Gets the amount selected
     * @return amount selected
     */
    public String getAmount() {
        return jcbAmount.getSelectedItem().toString();
    }
}
