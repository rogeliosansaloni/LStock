package view;

import model.entities.Bot;
import utils.StockColors;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BotsListView extends JPanel {
    private static final String[] columnNames = {"Company", "Bot", "Buy percentage", "Activation Time", "Status"};
    private static final String RETURN = "Return";
    private JPanel jpCenter;
    private JPanel jpSouth;
    private JTable jtBotsList;
    private JButton jbReturn;
    private StockColors color;

    public BotsListView() {
        color = new StockColors();
        initUI();
        // TODO: Implement list view
    }

    private void initUI() {
        this.setLayout(new BorderLayout());
        this.setBackground(color.getWHITE());

        jpCenter = new JPanel(new BorderLayout());
        jpCenter.setBackground(color.getWHITE());

        jtBotsList = new JTable();
        jtBotsList.setRowHeight(40);
        jtBotsList.setBackground(color.getWHITE());

        JScrollPane scrollPane = new JScrollPane(jtBotsList);
        jpCenter.setBorder(BorderFactory.createEmptyBorder(40,40,0,40));
        jpCenter.add(scrollPane);
        this.add(jpCenter, BorderLayout.CENTER);

        //Button
        jpSouth = new JPanel();
        jpSouth.setLayout(new BoxLayout(jpSouth, BoxLayout.Y_AXIS));
        jpSouth.setBackground(color.getWHITE());

        jbReturn = new JButton(RETURN);
        jbReturn.setFont(new Font("Segoe UI", Font.BOLD,18));
        jbReturn.setBackground(color.getYELLOW());
        jbReturn.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbReturn.setBorder(BorderFactory.createMatteBorder(5,40,5,40, color.getYELLOW()));
        jbReturn.setContentAreaFilled(false);
        jbReturn.setOpaque(true);
        jpSouth.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        jpSouth.add(jbReturn);

        this.add(jpSouth, BorderLayout.SOUTH);

    }

    public void registerController(ActionListener controller) {
        this.jbReturn.addActionListener(controller);
        this.jbReturn.setActionCommand("cancelBotList");
    }

    public void showBotsInTable(ArrayList<Bot> bots) {
        // TODO: Show bots in table
    }
}
