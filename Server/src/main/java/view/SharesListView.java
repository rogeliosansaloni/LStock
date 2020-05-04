package view;

import utils.StockColors;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;


public class SharesListView extends JPanel{
    private static final String[] columnNames = { "Users", "Email", "Stock Value", "Total Balance"};
    private String[][] userList = {{ "", "", "", ""}};
    private static final int anchuraPanel = 1080;
    private static final int alturaPanel = 768;
    protected StockColors color;
    private JTable jtSharesList;
    private JButton jbReturn;
    private JPanel jpNorth;
    private JLabel labelLogo;
    private JLabel labelStock;
    private JPanel jpCenter;
    private JPanel jpSouth;
    JScrollPane scrollPane;
    private ListSelectionModel selectionModel;
    public int selectedRow;

    public SharesListView () {
        color = new StockColors();
        initUI();
        getRowListener();
    }

    private void initUI() {
        //Main Panel
        this.setLayout(new BorderLayout());
        this.setBackground(color.getWHITE());

        //Center panel with data table
        jpCenter = new JPanel(new BorderLayout());
        jpCenter.setBackground(color.getWHITE());
        String[][] userRow = {
                { "User1", "user1@gmail.com", "1234", "12345"},
                { "User2", "user2@gmail.com", "1234", "12345"},
                { "User3", "user3@gmail.com", "1234", "12345"},
                { "User4", "user4@gmail.com", "1234", "12345"},
                { "User5", "user5@gmail.com", "1234", "12345"},
                { "User6", "user6@gmail.com", "1234", "12345"},
                { "User7", "user7@gmail.com", "1234", "12345"},
                { "User8", "user8@gmail.com", "1234", "12345"},
                { "User9", "user9@gmail.com", "1234", "12345"},
                { "User10", "user10@gmail.com", "1234", "12345"}
        };

        jtSharesList = new JTable(userRow,columnNames);
        jtSharesList.setRowHeight(40);
        jtSharesList.setBackground(Color.WHITE);
        selectionModel = jtSharesList.getSelectionModel();
        scrollPane = new JScrollPane(jtSharesList);
        scrollPane.setBackground(color.getWHITE());
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        JTableHeader tableHeader = jtSharesList.getTableHeader();
        tableHeader.setBackground(color.getWHITE());
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jpCenter.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        jpCenter.add(scrollPane);
        this.add(jpCenter, BorderLayout.CENTER);

        //Return button
        jpSouth = new JPanel();
        jpSouth.setLayout(new BoxLayout(jpSouth, BoxLayout.Y_AXIS));
        jbReturn = new JButton();
        jbReturn.setText("RETURN");
        jbReturn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jbReturn.setBackground(color.getYELLOW());
        jbReturn.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbReturn.setBorder(BorderFactory.createMatteBorder(5,40,5,40, color.getYELLOW()));
        jbReturn.setContentAreaFilled(false);
        jbReturn.setOpaque(true);
        jpSouth.add(jbReturn);
        jpSouth.setBackground(Color.WHITE);
        jpSouth.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        this.add(jpSouth, BorderLayout.SOUTH);

    }

    public void registerController (ActionListener actionListener) {
        jbReturn.addActionListener(actionListener);
        jbReturn.setActionCommand("return");
        jtSharesList.addMouseListener((MouseListener) actionListener);
    }

    /**
     *  Sets the last selected table row
     */
    private void getRowListener() {
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                selectedRow = -1;
                if (! selectionModel.isSelectionEmpty()){
                    selectedRow = selectionModel.getMinSelectionIndex();
                }
            }
        });
    }

    /**
     * Returns the selected row index
     *
     * @return value of selected row index
     */
    public int getSelectedRow() {
        return selectedRow;
    }

    /**
     * Returns the selected user data
     *
     * @return user data selected
     */
//    public User getSelectedUser(){
//        User user = new User();
//        user.setNickname(jtSharesList.getModel().getValueAt(selectedRow, 0).toString());
//        user.setEmail(jtSharesList.getModel().getValueAt(selectedRow, 1).toString());
//        user.setStockValue(Float.parseFloat(jtSharesList.getModel().getValueAt(selectedRow, 2).toString()));
//        user.setTotalBalance(Float.parseFloat(jtSharesList.getModel().getValueAt(selectedRow, 3).toString()));
//        return user;
//    }

    /**
     * Sets the new data for the table and updates it
     *
     * @param userList String list with data
     */
    public void setUserList(String[][] userList) {
        this.userList = userList;
        fillData();
    }

    /**
     *  Fills table with userList and columnNames
     */
    public void fillData() {
        this.jtSharesList.setModel(new DefaultTableModel());
        DefaultTableModel tablemodel = new DefaultTableModel();
        for (String col : columnNames) {
            tablemodel.addColumn(col);
        }
        for (String[] row : userList) {
            tablemodel.addRow(row);
        }
        this.jtSharesList.setModel(tablemodel);
    }
}
