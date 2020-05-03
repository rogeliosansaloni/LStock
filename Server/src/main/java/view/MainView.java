package view;

import utils.StockColors;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainView extends JFrame {

    private static final String PATH_LOGO = "/Images/stock.png";
    private static final String PATH_ARROW_ICON = "/Images/dropdown.png";
    private static final String TITLE = "StockLS - C2";
    private static final String ERROR_MESSAGE_1 = "All fields are required.";
    private static final String ERROR_MESSAGE_2 = "Fields can't be empty.";
    private static final int anchuraPanel = 1080;
    private static final int alturaPanel = 740;
    private JLabel labelLogo;
    private JLabel labelStock;
    protected JPanel jpNorth;
    protected JPanel jpLogo;
    private JPanel jpCenter;
    private JPanel jpMenu;
    private JMenuBar menuBar;
    private JMenu menuOptions;
    private JMenuItem option1,option2, option3;
    private StockColors color;

    public MainView() {
        color = new StockColors();
        this.setTitle(TITLE);
        this.setPreferredSize(new Dimension(anchuraPanel, alturaPanel));
        this.setSize(anchuraPanel, alturaPanel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        initUI();
    }

    /**
     * Creates the UI of the login form
     */
    public void initUI() {
        JPanel jpMain = new JPanel();
        jpMain.setLayout(new BorderLayout());
        jpMain.setBackground(Color.WHITE);

        jpNorth = new JPanel(new BorderLayout());
        jpNorth.setBackground(Color.WHITE);

        jpLogo = new JPanel(new BorderLayout());
        jpLogo.setBackground(Color.WHITE);
        ImageIcon imageIcon = new ImageIcon(MainView.class.getResource(PATH_LOGO));
        Image scaleImage = imageIcon.getImage().getScaledInstance(120, 120,Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(scaleImage);
        labelLogo = new JLabel(imageIcon);
        jpLogo.add(labelLogo, BorderLayout.CENTER);

        Font fontMenu = new Font("Segoe UI", Font.PLAIN, 30);
        Border borderOptions = BorderFactory.createLineBorder(color.getDarkGreyText(), 1);

        jpMenu = new JPanel();
        jpMenu.setBackground(Color.WHITE);

        ImageIcon imageArrow = new ImageIcon(MainView.class.getResource(PATH_ARROW_ICON));
        Image scaleImageArrow = imageArrow.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        imageArrow = new ImageIcon(scaleImageArrow);

        menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        menuBar.setBorder(null);
        menuBar.setBorderPainted(false);

        menuOptions = new JMenu("StockLS");
        menuOptions.setIcon(imageArrow);
        menuOptions.setIconTextGap(10);
        menuOptions.setHorizontalTextPosition(SwingConstants.LEFT);
        menuOptions.setFont(fontMenu);
        menuOptions.setForeground(Color.BLACK);
        menuOptions.setBorder(null);
        menuBar.add(menuOptions);

        option1 = new JMenuItem("Home");
        option1.setHorizontalAlignment(SwingConstants.CENTER);
        option1.setBackground(Color.WHITE);
        option1.setBorder(borderOptions);
        option1.setForeground(color.getDarkGreyText());
        option1.setFont(fontMenu);
        menuOptions.add(option1);
        //Option2
        option2 = new JMenuItem("List of Users");
        option2.setHorizontalAlignment(SwingConstants.CENTER);
        option2.setBackground(Color.WHITE);
        option2.setBorder(borderOptions);
        option2.setForeground(color.getDarkGreyText());
        option2.setFont(fontMenu);
        menuOptions.add(option2);
        //Option3
        option3 = new JMenuItem("Manage Bots");
        option3.setHorizontalAlignment(SwingConstants.CENTER);
        option3.setBackground(Color.WHITE);
        option3.setBorder(borderOptions);
        option3.setForeground(color.getDarkGreyText());
        option3.setFont(fontMenu);
        menuOptions.add(option3);
        jpMenu.add(menuBar, BorderLayout.CENTER);

        jpLogo.add(jpMenu, BorderLayout.SOUTH);
        jpNorth.add(jpLogo, BorderLayout.CENTER);

        jpNorth.setBorder(BorderFactory.createEmptyBorder(30,50,15,50));
        jpMain.add(jpNorth, BorderLayout.NORTH);

        //Center Panel is a CardLayout
        jpCenter = new JPanel();
        jpCenter.setLayout(new CardLayout());

        jpCenter.setBackground(Color.WHITE);
        jpMain.add(jpCenter, BorderLayout.CENTER);
        this.getContentPane().add(jpMain);
    }
}

