package view;

import model.entities.Bot;
import utils.StockColors;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BotsListView extends JPanel {
    private static final String[] columnNames = {"Company", "Bot", "Buy percentage", "Activation Time", "Status"};
    private static final String RETURN = "Return";
    private static final String STATUS_OK = "Enabled";
    private static final String STATUS_KO = "Disabled";
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

        DefaultTableModel model = new DefaultTableModel(columnNames,0) {
          public boolean isCellEditable (int row, int column) {
              return false;
          }
        };
        jtBotsList = new JTable(model);
        jtBotsList.setRowHeight(40);
        jtBotsList.setBackground(color.getWHITE());
        JTableHeader jtbHeader = jtBotsList.getTableHeader();
        jtbHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));

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
        this.jbReturn.setActionCommand(RETURN);
    }

    public void showBotsInTable(ArrayList<Bot> bots) {
        String status;
        DefaultTableModel model = (DefaultTableModel) jtBotsList.getModel();
        for (Bot b : bots) {
            if (b != null) {
                if (b.getStatus() == 1) { status = STATUS_OK; }
                else { status = STATUS_KO; }
                Object[] obj = {b.getCompany().getName(), b.getBotId(), Math.round(b.getProbability()) + "%", Math.round(b.getActiveTime()) + "s", status};
                model.addRow(obj);
            }
        }
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);

        for (int col = 0; col < model.getColumnCount(); col++) {
            jtBotsList.getColumnModel().getColumn(col).setCellRenderer(center);
        }
    }
}
