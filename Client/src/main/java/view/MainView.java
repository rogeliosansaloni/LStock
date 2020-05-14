package view;

import model.entities.CompanyChange;
import utils.StockColors;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.entities.Company;

public class MainView extends JFrame {

    //logo de stock
    private static final String PATH_LOGO = "/Images/stock.png";
    private static final String PATH_USER_PHOTO = "/Images/logoUser.png";
    private static final String PATH_ARROW_ICON = "/Images/dropdown-grey.png";
    private static final String TITLE = "StockLS - C2";
    private static final String CARD_COMPANY = "Companies";
    private static final String CARD_COMPANYDETAILS = "Company Details";
    private static final String CARD_PROFILE = "My Profile";
    private static final String CARD_SHARES = "Shares";
    private static final String CARD_BALANCE = "Load Balance";
    private static final int PANEL_WIDTH = 1080;
    private static final int PANEL_HEIGHT = 768;
    private JLabel labelLogo;
    private JLabel labelStock;
    private JLabel labelUserPhoto;
    private JLabel labelViewName;
    private JLabel labelBalance;
    private String userName = "Peter Fox";
    private String userBalance = "00.00";
    private JPanel jpHeader;
    private JPanel jpLogo;
    private JPanel jpOptions;
    private JPanel jpMenu;
    private JPanel jpCenter;
    private JMenuBar menuBar;
    private JMenu menuOptions;
    private StockColors color;
    private CompanyDetailView jpCompanyDetailsView;
    private JMenuItem optionProfile, optionShares, optionBalance, optionCompany, optionLogout;
    private BalanceView jpBalanceView;
    private CompanyView jpCompanyView;

    public MainView() {
        color = new StockColors();
        this.setTitle(TITLE);
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
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
        jpCompanyView = new CompanyView();
        jpBalanceView = new BalanceView();
        jpCompanyDetailsView = new CompanyDetailView();
        //TODO: Add the rest of views
        addToCardLayout();
    }

    /**
     * Add diferent views to layout
     */
    private void addToCardLayout() {
        jpCenter.add(jpCompanyView, CARD_COMPANY);
        jpCenter.add(jpCompanyDetailsView, CARD_COMPANYDETAILS);
        jpCenter.add(jpBalanceView, CARD_BALANCE);
        //TODO: Add the rest of views
    }

    /**
     * Initializes UI
     */
    public void initUI() {
        JPanel jpMain = new JPanel();
        jpMain.setLayout(new BorderLayout());
        jpMain.setBackground(color.getBLACK());

        //We create the JPanel for the header
        jpHeader = new JPanel(new BorderLayout());
        jpHeader.setBackground(color.getDarkGreyHeader());
        jpLogo = new JPanel(new BorderLayout());

        // Create logo
        ImageIcon imageIcon = new ImageIcon(MainView.class.getResource(
                PATH_LOGO));
        Image scaleImage = imageIcon.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
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
        Image scaleImageUser = imageIconUser.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
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
        jpOptions.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));

        jpHeader.add(jpOptions, BorderLayout.EAST);

        jpHeader.setBorder(BorderFactory.createEmptyBorder(30, 50, 15, 50));
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
    public void createMenuBar() {

        Font fontName = new Font("Segoe UI", Font.BOLD, 30);
        jpMenu = new JPanel(new BorderLayout());
        jpMenu.setBackground(color.getDarkGreyHeader());

        ImageIcon imageArrow = new ImageIcon(MainView.class.getResource(
                PATH_ARROW_ICON));
        Image scaleImageArrow = imageArrow.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
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
        initializeOptions();
        updateOptionsCompany();
        jpMenu.add(menuBar, BorderLayout.CENTER);
        jpOptions.add(jpMenu, BorderLayout.CENTER);
    }

    public void initializeOptions(){
        //Option Companies
        optionCompany = new JMenuItem("Companies list");
        //Option Profile
        optionProfile = new JMenuItem("My Profile");
        //Option Shares
        optionShares = new JMenuItem("Shares");
        //Option Load Balance
        optionBalance = new JMenuItem("Load Balance");
        //Option Logout
        optionLogout = new JMenuItem("Log out");
    }

    public void addOptionBar (JMenuItem option){
        Font fontOptions = new Font("Segoe UI", Font.PLAIN, 28);
        Border bordeOptions = BorderFactory.createLineBorder(color.getDarkGreyText(), 1);
        option.setHorizontalAlignment(SwingConstants.CENTER);
        option.setBackground(color.getDarkGreyHeader());
        option.setBorder(bordeOptions);
        option.setForeground(color.getDarkGreyText());
        option.setFont(fontOptions);
        menuOptions.add(option);
    }

    public void updateOptionsCompany() {
        menuOptions.removeAll();
        addOptionBar(optionProfile);
        addOptionBar(optionShares);
        addOptionBar(optionBalance);
        addOptionBar(optionLogout);
    }

    public void updateOptionsBalance() {
        menuOptions.removeAll();
        addOptionBar(optionProfile);
        addOptionBar(optionCompany);
        addOptionBar(optionShares);
        addOptionBar(optionLogout);
    }

    public void updateOptionsShares() {
        menuOptions.removeAll();
        addOptionBar(optionProfile);
        addOptionBar(optionCompany);
        addOptionBar(optionBalance);
        addOptionBar(optionLogout);
    }

    public void updateOptionsProfile() {
        menuOptions.removeAll();
        addOptionBar(optionCompany);
        addOptionBar(optionShares);
        addOptionBar(optionBalance);
        addOptionBar(optionLogout);
    }

    /**
     * Register controllers to Menu Bar
     *
     * @param actionListener ActionLister
     */
    public void registerMainController(ActionListener actionListener) {
        optionProfile.addActionListener(actionListener);
        optionProfile.setActionCommand("profile");
        optionShares.addActionListener(actionListener);
        optionShares.setActionCommand("shares");
        optionBalance.addActionListener(actionListener);
        optionBalance.setActionCommand("load");
        optionCompany.addActionListener(actionListener);
        optionCompany.setActionCommand("company");
        optionLogout.addActionListener(actionListener);
        optionLogout.setActionCommand("logout");

    }

    public int confirmLogOutWindow() {
        int verify = JOptionPane.showConfirmDialog(null, "Do you really want to logout?", "Log Out", JOptionPane.YES_NO_OPTION);
        return verify;
    }

    public void registerCompanyDetailViewController(ActionListener controller) {
        jpCompanyDetailsView.registerController(controller);
    }

    public void registerBalanceController(ActionListener controller) {
        jpBalanceView.registerController(controller);
    }

    public void registerCompanyController(ActionListener controller) {
        jpCompanyView.registerController(controller);
    }

    /**
     * Shows desired view
     *
     * @param card
     */
    public void updateView(String card) {
        CardLayout cardLayout = (CardLayout) jpCenter.getLayout();
        switch (card) {
            case CARD_COMPANY:
                labelViewName.setText(CARD_COMPANY);
                cardLayout.show(jpCenter, CARD_COMPANY);
                updateOptionsCompany();
                break;
            case CARD_PROFILE:
                labelViewName.setText(CARD_PROFILE);
                //cardLayout.show(jpCenter, CARD_PROFILE);
                updateOptionsProfile();
                break;
            case CARD_SHARES:
                labelViewName.setText(CARD_SHARES);
                //cardLayout.show(jpCenter, CARD_SHARES);
                updateOptionsShares();
                break;
            case CARD_BALANCE:
                labelViewName.setText(CARD_BALANCE);
                cardLayout.show(jpCenter, CARD_BALANCE);
                updateOptionsBalance();
                break;
            case CARD_COMPANYDETAILS:
                labelViewName.setText(CARD_COMPANYDETAILS);
                cardLayout.show(jpCenter, CARD_COMPANYDETAILS);
                break;
        }
    }

    public void updateCompanyList(ArrayList<CompanyChange> companies){
        jpCompanyView.showCompanies(companies);
    }

    /**
     * Gets amount selected from the Load Balance view
     *
     * @return amount selected
     */
    public String getBalanceAmount() {
        return jpBalanceView.getAmount();
    }

    /**
     * Update the total balance of the user in the header
     * @param nickname Users nickname
     * @param totalBalance Current balance of the user
     */
    public void initHeaderInformation (String nickname, float totalBalance) {
        String strDouble = String.format("%.2f", totalBalance);
        menuOptions.setText(nickname);
        labelBalance.setText("Balance: " + strDouble + " $");
    }

    public void initFirstView (ArrayList<CompanyChange> companies) {
        updateCompanyList(companies);
    }

    /**
     * Updates total balance of the user in the header
     * @param totalBalance Current balance of the user
     */
    public void updateTotalBalance (float totalBalance) {
        String strDouble = String.format("%.2f", totalBalance);
        labelBalance.setText("Balance: " + strDouble + " $");
    }

    /**
     * Shows a window to confirm action
     * @param message the message
     * @return true if confirmed
     */
    public int confirmAction (String message) {
        return jpCompanyDetailsView.confirmAction(message);

    }

    /**
     * Shows error message when user doesn't have enough money
     *
     * @param message the error message
     */
    public void showNoEnoughBalanceErrorMessage (String message) {
        jpCompanyDetailsView.showErrorMessage(message);
    }

    /**
     * Update the user and the company new values in the view
     *
     * @param totalBalance new balance of the user
     * @param value new value of the company
     */
    public void updateCompanyUserValueAndBalance (float totalBalance, float value) {
        String strBalance = String.format("%.2f", totalBalance);
        String strValue = String.format("%.2f", value);
        jpBalanceView.updateCurrentBalance(strBalance);
        jpCompanyDetailsView.updateValue(strValue);
    }

    /**
     * Gets the Balance View
     *
     * @return Balance View
     */
    public BalanceView getBalanceView () { return jpBalanceView; }

    /**
     * Gets the company view
     *
     * @return Company View
     */
    public CompanyView getCompanyView() { return jpCompanyView; }

    /**
     * Gets the Company Details View
     *
     * @return Company Details View
     */
    public CompanyDetailView getCompanyDetailsView() { return jpCompanyDetailsView; }

}
