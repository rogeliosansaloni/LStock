package view;

import utils.StockColors;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;


public class SharesListView extends JFrame{

    private final static String TITLE = "StockLS - C2 - Server";
    private static final String PATH_LOGO = "resources/stock.png";
    private static final String[] columnNames = { "Users", "Email", "Stock Value", "Total Balance"};
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
    private ListSelectionModel selectionModel;

    public SharesListView () {
        color = new StockColors();
        this.setTitle(TITLE);
        this.setSize(anchuraPanel, alturaPanel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        initUI();
        getRow();
    }

    private void initUI() {
        JPanel jpMain = new JPanel();
        jpMain.setLayout(new BorderLayout());
        jpMain.setBackground(Color.WHITE);
        this.getContentPane().add(jpMain);

        //Header
        jpNorth = new JPanel(new BorderLayout());
        jpNorth.setBackground(Color.WHITE);

        //TODO Add App Logo

        labelStock = new JLabel("List of Users", SwingConstants.CENTER);
        Font fuenteLogo = new Font("Segoe UI", Font.PLAIN, 50);
        labelStock.setFont(fuenteLogo);
        jpNorth.add(labelStock, BorderLayout.SOUTH);
        jpNorth.setBorder(BorderFactory.createEmptyBorder(70,0,80,0));
        this.add(jpNorth,BorderLayout.NORTH);

        //Center panel with data table
        jpCenter = new JPanel(new BorderLayout());
        jpCenter.setBackground(Color.WHITE);
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
        JScrollPane scrollPane = new JScrollPane(jtSharesList);
        scrollPane.setBackground(color.getWHITE());
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        JTableHeader tableHeader = jtSharesList.getTableHeader();
        tableHeader.setBackground(color.getWHITE());
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jpCenter.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        jpCenter.add(scrollPane);
        jpMain.add(jpCenter, BorderLayout.CENTER);

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
        jpMain.add(jpSouth, BorderLayout.SOUTH);

        this.getContentPane().add(jpMain);
    }

    public void registerController (ActionListener actionListener) {
        jbReturn.addActionListener(actionListener);
        jbReturn.setActionCommand("return");
    }

    private void getRow() {
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if (! selectionModel.isSelectionEmpty()){
                    int selecRow = selectionModel.getMinSelectionIndex();
                    System.out.println("selected row: "+selecRow);
                }
            }
        });
    }

}
