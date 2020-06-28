package view;

import model.entities.User;
import utils.StockColors;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * View for Shares List
 */
public class SharesListView extends JPanel{
    private static final String[] COLUMNS_USER = { "Users", "Email", "Stock Value", "Total Balance"};
    private static final String[] COLUMNS_SHARE = { "Company", "Shares", "Share Price", "Total Value"};
    private String[][] ROWS = {{ "", "", "", ""}};
    protected StockColors color;
    private JTable jtSharesList;
    private JPanel jpCenter;
    private JPanel jpSouth;
    JScrollPane scrollPane;
    private ListSelectionModel selectionModel;

    /**
     * Constructor for the SharesListView
     */
    public SharesListView () {
        color = new StockColors();
        initUI();
    }

    /**
     * Creates the SharesListView
     */
    private void initUI() {
        //Main Panel
        this.setLayout(new BorderLayout());
        this.setBackground(color.getWHITE());

        //Center panel with data table
        jpCenter = new JPanel(new BorderLayout());
        jpCenter.setBackground(color.getWHITE());
        String[][] userRow = {};
        jtSharesList = new JTable(ROWS, COLUMNS_USER);
        jtSharesList.setRowHeight(40);
        jtSharesList.setBackground(Color.WHITE);
        selectionModel = jtSharesList.getSelectionModel();
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(jtSharesList);
        scrollPane.setBackground(color.getWHITE());
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        JTableHeader tableHeader = jtSharesList.getTableHeader();
        tableHeader.setBackground(color.getWHITE());
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jpCenter.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        jpCenter.add(scrollPane);
        this.add(jpCenter, BorderLayout.CENTER);

        jpSouth = new JPanel();
        jpSouth.setLayout(new BoxLayout(jpSouth, BoxLayout.Y_AXIS));
        jpSouth.setBackground(Color.WHITE);
        jpSouth.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        this.add(jpSouth, BorderLayout.SOUTH);
    }

    /**
     *  Registers controller for this view
     *
     *  @param controller View Controller
     */
    public void registerController (ListSelectionListener controller) {
        selectionModel.addListSelectionListener(controller);
    }

    /**
     * Returns the selected user nickname
     *
     * @param selectedRow selected user row to return
     * @return user data selected
     */
    public String getSelectedUser(int selectedRow){
        User user = new User();
        user.setNickname(jtSharesList.getModel().getValueAt(selectedRow, 0).toString());
        return user.getNickname();
    }

    /**
     * Sets the new data for the table and updates it
     *
     * @param rows String list with data
     */
    public void setTableRow(String[][] rows) {
        this.ROWS = rows;
    }

    /**
     *  Empties the table
     */
    public void emptyTable(){
        this.jtSharesList.setModel(new DefaultTableModel());
        DefaultTableModel tablemodel = (DefaultTableModel) jtSharesList.getModel();
        tablemodel.setRowCount(0);
    }

    /**
     *  Fills table with the user data
     */
    public void fillUserData() {
        this.jtSharesList.setModel(new DefaultTableModel());
        DefaultTableModel model = (DefaultTableModel) jtSharesList.getModel();
        model.setRowCount(0);
        for (String col : COLUMNS_USER) {
            model.addColumn(col);
        }
        for (String[] row : ROWS) {
            model.addRow(row);
        }

        //Sets cells value to center
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int col = 0; col < model.getColumnCount(); col++) {
            jtSharesList.getColumnModel().getColumn(col).setCellRenderer(center);
        }
        this.jtSharesList.setModel(model);
    }

    /**
     *  Fills table with Shares data
     */
    public void fillShareData() {
        this.jtSharesList.setModel(new DefaultTableModel());
        DefaultTableModel model = (DefaultTableModel) jtSharesList.getModel();
        model.setRowCount(0);
        for (String col : COLUMNS_SHARE) {
            model.addColumn(col);
        }
        for (String[] row : ROWS) {
            model.addRow(row);
        }
        //Sets cells value to center
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int col = 0; col < model.getColumnCount(); col++) {
            jtSharesList.getColumnModel().getColumn(col).setCellRenderer(center);
        }
        this.jtSharesList.setModel(model);
    }

    /**
     *  Return the selectionModel for the JTable
     */
    public ListSelectionModel getSelectionModel() {
        return selectionModel;
    }

    /**
     * Shows error message
     *
     * @param message the error message
     */
    public void showErrorMessage (String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
