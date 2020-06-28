package view;

import model.entities.Bot;
import utils.StockColors;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Bots list view
 */
public class BotsListView extends JPanel {
    private static final String[] COLUMN_NAMES = {"Company", "Bot", "Buy percentage", "Activation Time", "Status"};
    private static final String RETURN = "Return";
    private static final String STATUS_OK = "Enabled";
    private static final String STATUS_KO = "Disabled";
    private JTable jtBotsList;
    private JButton jbReturn;
    private StockColors color;

    /**
     * Creates and initializes the bots list view
     */
    public BotsListView() {
        color = new StockColors();
        initUI();
    }

    /**
     * Creates de Bots list view
     */
    private void initUI() {
        this.setLayout(new BorderLayout());
        this.setBackground(color.getWHITE());

        JPanel jpCenter = new JPanel(new BorderLayout());
        jpCenter.setBackground(color.getWHITE());

        //Initializes table with columns and 0 rows.
        DefaultTableModel model = new DefaultTableModel(COLUMN_NAMES, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jtBotsList = new JTable(model);
        jtBotsList.setRowHeight(40);
        jtBotsList.setBackground(color.getWHITE());
        JTableHeader jtbHeader = jtBotsList.getTableHeader();
        jtbHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(jtBotsList);
        jpCenter.setBorder(BorderFactory.createEmptyBorder(40, 40, 0, 40));
        jpCenter.add(scrollPane);
        this.add(jpCenter, BorderLayout.CENTER);

        //Button
        JPanel jpSouth = new JPanel();
        jpSouth.setLayout(new BoxLayout(jpSouth, BoxLayout.Y_AXIS));
        jpSouth.setBackground(color.getWHITE());

        jbReturn = new JButton(RETURN);
        jbReturn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jbReturn.setBackground(color.getYELLOW());
        jbReturn.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbReturn.setBorder(BorderFactory.createMatteBorder(5, 40, 5, 40, color.getYELLOW()));
        jbReturn.setContentAreaFilled(false);
        jbReturn.setOpaque(true);
        jpSouth.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        jpSouth.add(jbReturn);

        this.add(jpSouth, BorderLayout.SOUTH);

    }

    /**
     * Register action listener to Return button
     *
     * @param controller ActionListener
     */
    public void registerController(ActionListener controller) {
        this.jbReturn.addActionListener(controller);
        this.jbReturn.setActionCommand(RETURN);
    }

    /**
     * Initializes Bot table with the corresponding information
     *
     * @param bots List of company bots
     */
    public void showBotsInTable(ArrayList<Bot> bots) {
        String status;

        DefaultTableModel model = (DefaultTableModel) jtBotsList.getModel();
        // Remove all the previous information from the table
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        // Add new information to the table
        for (Bot b : bots) {
            if (b != null) {
                if (b.getStatus() == 1) {
                    status = STATUS_OK;
                } else {
                    status = STATUS_KO;
                }
                Object[] obj = {b.getCompany().getName(), b.getBotId(), String.format("%.2f", b.getProbability()) + "%", Math.round(b.getActiveTime()) + "s", status};
                model.addRow(obj);
            }
        }
        //Sets cells value to center
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int col = 0; col < model.getColumnCount(); col++) {
            jtBotsList.getColumnModel().getColumn(col).setCellRenderer(center);
        }
    }
}
