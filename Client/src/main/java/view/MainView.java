package view;

import utils.StockColors;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame{

    //logo de stock
    private static final String PATH_LOGO = "/Images/stock.png";
    private static final String PATH_USER_PHOTO = "/Images/logoUser.png";
    private static final String PATH_ARROW_ICON = "/Images/dropdown-grey.png";
    private static final String TITLE = "StockLS - C2";
    private static final String CARD_COMPANY = "Companies";
    private static final String CARD_PROFILE = "My Profile";
    private static final String CARD_SHARES = "Shares";
    private static final String CARD_BALANCE = "Load Balance";
    private static final int anchuraPanel = 1080;
    private static final int alturaPanel = 768;
    private JLabel labelLogo;
    protected JLabel labelStock;
    protected JLabel labelUserPhoto;
    protected JLabel labelViewName;
    protected JLabel labelBalance;
    protected String userName = "Peter Fox";
    protected String userBalance = "00.00";
    protected JPanel jpHeader;
    protected JPanel jpLogo;
    protected JPanel jpOptions;
    protected JPanel jpMenu;
    protected JPanel jpCenter;
    private JMenuBar menuBar;
    private JMenu menuOptions;
    private JMenuItem option1,option2,option3, option4;
    protected StockColors color;
    private BalanceView jpBalanceView;

    public MainView () {
        color = new StockColors();
        this.setTitle(TITLE);
        this.setSize(anchuraPanel, alturaPanel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(color.getBLACK());
        initUI();
        initAllViews();
    }

    /**
     * Initializes all views
     */
    public void initAllViews() {
        jpBalanceView = new BalanceView();
        //TODO: Add the rest of views
        addToCardLayout();
    }

    /**
     * Add diferent views to layout
     */
    private void addToCardLayout() {
        jpCenter.add(jpBalanceView, CARD_BALANCE);
        //TODO: Add the rest of views
    }

    /**
     * Initializes UI
     */
    public void initUI () {
        JPanel jpMain = new JPanel();
        jpMain.setLayout(new BorderLayout());
        jpMain.setBackground(color.getBLACK());

        //We create the JPanel for the header
        jpHeader = new JPanel(new BorderLayout());
        jpHeader.setBackground(color.getDarkGreyHeader());
        jpLogo = new JPanel(new BorderLayout());
        //Creamos la imagen del logo
        ImageIcon imageIcon = new ImageIcon(MainView.class.getResource(
                PATH_LOGO));
        Image scaleImage = imageIcon.getImage().getScaledInstance(120, 120,Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(scaleImage);
        labelLogo = new JLabel(imageIcon);
        jpLogo.add(labelLogo, BorderLayout.CENTER);
        jpLogo.setBackground(color.getDarkGreyHeader());

        Font fontLogo = new Font("Segoe UI", Font.PLAIN, 30);
        labelStock = new JLabel("StockLS", SwingConstants.CENTER);
        labelStock.setFont(fontLogo);
        labelStock.setForeground(color.getDarkGreyText());
        jpLogo.add(labelStock, BorderLayout.SOUTH);
        jpHeader.add(jpLogo, BorderLayout.WEST);

        //Here we change the content of this label depending on the view we create
        labelViewName = new JLabel("COMPANIES", SwingConstants.CENTER);
        Font fontNameView = new Font("Segoe UI", Font.PLAIN, 50);
        labelViewName.setFont(fontNameView);
        labelViewName.setForeground(color.getWHITE());
        jpHeader.add(labelViewName, BorderLayout.CENTER);

        jpOptions = new JPanel(new BorderLayout());
        jpOptions.setBackground(color.getDarkGreyHeader());

        ImageIcon imageIconUser = new ImageIcon(MainView.class.getResource(
                PATH_USER_PHOTO));
        Image scaleImageUser = imageIconUser.getImage().getScaledInstance(100, 100,Image.SCALE_DEFAULT);
        imageIconUser = new ImageIcon(scaleImageUser);
        labelUserPhoto = new JLabel(imageIconUser);
        jpOptions.add(labelUserPhoto, BorderLayout.NORTH);

        createMenuBar();

        Font fontBalance = new Font("Segoe UI", Font.PLAIN, 25);
        //Here we put the user's balance
        labelBalance = new JLabel("Balance: " + userBalance + " $");
        labelBalance.setFont(fontBalance);
        labelBalance.setForeground(color.getDarkGreyText());
        jpOptions.add(labelBalance, BorderLayout.SOUTH);
        jpOptions.setBorder(BorderFactory.createEmptyBorder(0,20,0,10));

        jpHeader.add(jpOptions, BorderLayout.EAST);

        jpHeader.setBorder(BorderFactory.createEmptyBorder(30,50,15,50));
        jpMain.add(jpHeader, BorderLayout.NORTH);

        //Here we edit the jpCenter depending on the view the create
        jpCenter = new JPanel();
        jpCenter.setLayout(new CardLayout());

        jpCenter.setBackground(color.getBLACK());
        jpMain.add(jpCenter, BorderLayout.CENTER);
        this.getContentPane().add(jpMain);

    }

    /**
     * Creates Menu Bar
     */
    public void createMenuBar(){

        Font fontOptions = new Font("Segoe UI", Font.PLAIN, 28);
        Font fontName = new Font("Segoe UI", Font.BOLD, 30);
        Border bordeOptions = BorderFactory.createLineBorder(color.getDarkGreyText(), 1);

        jpMenu = new JPanel(new BorderLayout());
        jpMenu.setBackground(color.getDarkGreyHeader());

        ImageIcon imageArrow = new ImageIcon(MainView.class.getResource(
                PATH_ARROW_ICON));
        Image scaleImageArrow = imageArrow.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        imageArrow = new ImageIcon(scaleImageArrow);

        menuBar = new JMenuBar();
        menuBar.setBackground(color.getDarkGreyHeader());
        menuBar.setBorder(null);
        menuBar.setBorderPainted(false);
        //Here we put the name of the user
        menuOptions = new JMenu(userName);
        menuOptions.setIcon(imageArrow);
        menuOptions.setIconTextGap(10);
        menuOptions.setHorizontalTextPosition(SwingConstants.LEFT);
        menuOptions.setFont(fontName);
        menuOptions.setForeground(color.getDarkGreyText());
        menuOptions.setBorder(null);
        menuBar.add(menuOptions);
        //Option1
        option1 = new JMenuItem("My Profile");
        option1.setHorizontalAlignment(SwingConstants.CENTER);
        option1.setBackground(color.getDarkGreyHeader());
        option1.setBorder(bordeOptions);
        option1.setForeground(color.getDarkGreyText());
        option1.setFont(fontOptions);
        menuOptions.add(option1);
        //Option2
        option2 = new JMenuItem("Shares");
        option2.setHorizontalAlignment(SwingConstants.CENTER);
        option2.setBackground(color.getDarkGreyHeader());
        option2.setBorder(bordeOptions);
        option2.setForeground(color.getDarkGreyText());
        option2.setFont(fontOptions);
        menuOptions.add(option2);
        //Option3
        option3 = new JMenuItem("Load Balance");
        option3.setHorizontalAlignment(SwingConstants.CENTER);
        option3.setBackground(color.getDarkGreyHeader());
        option3.setBorder(bordeOptions);
        option3.setForeground(color.getDarkGreyText());
        option3.setFont(fontOptions);
        menuOptions.add(option3);
        //Option4
        option4 = new JMenuItem("Log out");
        option4.setHorizontalAlignment(SwingConstants.CENTER);
        option4.setBackground(color.getDarkGreyHeader());
        option4.setBorder(bordeOptions);
        option4.setForeground(color.getDarkGreyText());
        option4.setFont(fontOptions);
        menuOptions.add(option4);
        jpMenu.add(menuBar, BorderLayout.CENTER);
        jpOptions.add(jpMenu, BorderLayout.CENTER);
    }

    /**
     * Register controllers to Menu Bar
     * @param actionListener ActionLister
     */
    public void registerController (ActionListener actionListener) {
        option1.addActionListener(actionListener);
        option1.setActionCommand("profile");
        option2.addActionListener(actionListener);
        option2.setActionCommand("shares");
        option3.addActionListener(actionListener);
        option3.setActionCommand("load");
        option4.addActionListener(actionListener);
        option4.setActionCommand("logout");
    }
    public int  confirmLogOutWindow(){
        int verify = JOptionPane.showConfirmDialog(null, "Do you really want to logout?","Log Out", JOptionPane.YES_NO_OPTION);
        return verify;
    }


    public void registerBalanceController(BalanceController controller) {
        jpBalanceView.registerController(controller);
    }

    /**
     * Shows desired view
     * @param card
     */
    public void updateView(String card) {
        CardLayout cardLayout = (CardLayout) jpCenter.getLayout();
        switch (card) {
            case CARD_PROFILE:
                break;
            case CARD_SHARES:
                break;
            case CARD_BALANCE:
                labelViewName.setText(CARD_BALANCE);
                cardLayout.show(jpCenter, CARD_BALANCE);
                break;
        }
    }

    /**
     * Gets amount selected from the Load Balance view
     * @return amount selected
     */
    public String getBalanceAmount() {
        return jpBalanceView.getAmount();
    }
}
