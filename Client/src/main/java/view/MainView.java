package view;

import model.entities.CompanyChange;
import model.entities.*;
import utils.StockColors;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Main view
 */
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
    private JLabel labelCurrentPrice;
    private JLabel labelBalance;
    private String userName = "Peter Fox";
    private String userBalance = "00.00";
    private JPanel jpHeader;
    private JPanel jpCenterHeader;
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
    private ProfileView jpProfileView;

    /**
     * Creates and initializes the main view
     */
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
        jpProfileView = new ProfileView();
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
        jpCenter.add(jpProfileView, CARD_PROFILE);
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

        Font fontLogo = new Font("Roboto", Font.PLAIN, 30);
        labelStock = new JLabel("StockLS", SwingConstants.CENTER);
        labelStock.setFont(fontLogo);
        labelStock.setForeground(color.getDarkGreyText());
        jpLogo.add(labelStock, BorderLayout.SOUTH);
        jpHeader.add(jpLogo, BorderLayout.WEST);

        jpCenterHeader = new JPanel(new BorderLayout());
        jpCenterHeader.setBackground(color.getDarkGreyHeader());

        labelViewName = new JLabel("COMPANIES", SwingConstants.CENTER);
        Font fontNameView = new Font("Roboto", Font.PLAIN, 50);
        labelViewName.setFont(fontNameView);
        labelViewName.setForeground(color.getWHITE());
        labelViewName.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        jpCenterHeader.add(labelViewName, BorderLayout.CENTER);

        labelCurrentPrice = new JLabel("", SwingConstants.CENTER);
        Font fontActualPrice = new Font("Roboto", Font.PLAIN, 30);
        labelCurrentPrice.setFont(fontActualPrice);
        labelCurrentPrice.setForeground(color.getWHITE());
        labelCurrentPrice.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        jpCenterHeader.add(labelCurrentPrice, BorderLayout.SOUTH);
        jpHeader.add(jpCenterHeader, BorderLayout.CENTER);

        jpOptions = new JPanel(new BorderLayout());
        jpOptions.setBackground(color.getDarkGreyHeader());

        ImageIcon imageIconUser = new ImageIcon(MainView.class.getResource(
                PATH_USER_PHOTO));
        Image scaleImageUser = imageIconUser.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        imageIconUser = new ImageIcon(scaleImageUser);
        labelUserPhoto = new JLabel(imageIconUser);
        jpOptions.add(labelUserPhoto, BorderLayout.NORTH);

        createMenuBar();

        Font fontBalance = new Font("Roboto", Font.PLAIN, 25);

        //Here we put the user's balance
        labelBalance = new JLabel("Balance: " + userBalance + " $");
        labelBalance.setFont(fontBalance);
        labelBalance.setForeground(color.getDarkGreyText());
        jpOptions.add(labelBalance, BorderLayout.SOUTH);
        jpOptions.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));

        jpHeader.add(jpOptions, BorderLayout.EAST);

        jpHeader.setBorder(BorderFactory.createEmptyBorder(30, 50, 15, 10));
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
        Font fontName = new Font("Roboto", Font.BOLD, 30);
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

    public void initializeOptions() {
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

    public void addOptionBar(JMenuItem option) {
        Font fontOptions = new Font("Roboto", Font.PLAIN, 28);
        Border bordeOptions = BorderFactory.createLineBorder(color.getDarkGreyText(), 1);
        option.setHorizontalAlignment(SwingConstants.CENTER);
        option.setBackground(color.getDarkGreyHeader());
        option.setBorder(bordeOptions);
        option.setForeground(color.getDarkGreyText());
        option.setFont(fontOptions);
        menuOptions.add(option);
    }

    private void updateOptionsCompany() {
        menuOptions.removeAll();
        addOptionBar(optionProfile);
        addOptionBar(optionShares);
        addOptionBar(optionBalance);
        addOptionBar(optionLogout);
    }

    private void updateOptionsBalance() {
        menuOptions.removeAll();
        addOptionBar(optionProfile);
        addOptionBar(optionCompany);
        addOptionBar(optionShares);
        addOptionBar(optionLogout);
    }

    private void updateOptionsShares() {
        menuOptions.removeAll();
        addOptionBar(optionProfile);
        addOptionBar(optionCompany);
        addOptionBar(optionBalance);
        addOptionBar(optionLogout);
    }

    private void updateOptionsProfile() {
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
        return JOptionPane.showConfirmDialog(null, "Do you really want to logout?", "Log Out", JOptionPane.YES_NO_OPTION);
    }

    public void registerCompanyDetailViewController(ActionListener controller, FocusListener focusController) {
        jpCompanyDetailsView.registerController(controller);
        jpCompanyDetailsView.registerFocusController(focusController);
    }

    public void registerBalanceController(ActionListener controller) {
        jpBalanceView.registerController(controller);
    }

    public void registerCompanyController(ActionListener controller, ArrayList<CompanyChange> companies) {
        jpCompanyView.registerController(controller, companies);
    }

    /**
     * Shows desired view
     *
     * @param card
     */
    public void updateView(String card) {
        CardLayout cardLayout = (CardLayout) jpCenter.getLayout();
        if (!card.equals(CARD_COMPANYDETAILS)) {
            labelCurrentPrice.setText("");
        }
        switch (card) {
            case CARD_COMPANY:
                labelViewName.setText(CARD_COMPANY);
                cardLayout.show(jpCenter, CARD_COMPANY);
                updateOptionsCompany();
                break;
            case CARD_PROFILE:
                labelViewName.setText(CARD_PROFILE);
                cardLayout.show(jpCenter, CARD_PROFILE);
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
                cardLayout.show(jpCenter, CARD_COMPANYDETAILS);
                break;
        }
    }

    /**
     * Sets the value of the labelCurrentPrice depending on the value it receives
     */

    public void setTitleCompanyDetail(float value, String companyName) {
        String text = "CURRENT PRICE: " + value + " €";
        labelCurrentPrice.setText(text);
        labelViewName.setText(companyName);
    }

    public String getNumSharesBuy() {
        String text = jpCompanyDetailsView.getSharesBuy();
        return text;
    }

    public String[] getNumSharesSell() {
        String[] text = jpCompanyDetailsView.getSharesSell();
        return text;
    }

    public void showErrorCompanyDetail(int error) {
        jpCompanyDetailsView.showErrorTextfield(error);
    }

    public void updateCompanyList(ArrayList<CompanyChange> companies) {
        jpCompanyView.showCompanies(companies);
    }

    public void updateCompanyDetailView(ArrayList<ShareSell> sharesSell, ArrayList<CompanyDetail> companyDetails, float maxValue) {

        jpCompanyDetailsView.updateCompanyDetailView(companyDetails, maxValue);
        jpCompanyDetailsView.updateSharesToSell(sharesSell);
    }

    /**
     * Updates profile view
     *
     * @param user the user
     */
    public void updateProfileView(User user) {
        jpProfileView.updateProfileView(user);
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
     *
     * @param nickname     Users nickname
     * @param totalBalance Current balance of the user
     */
    public void initHeaderInformation(String nickname, float totalBalance) {
        String strDouble = String.format("%.2f", totalBalance);
        menuOptions.setText(nickname);
        labelBalance.setText("Balance: " + strDouble + " $");
    }

    /**
     * Updates total balance of the user in the header
     *
     * @param totalBalance Current balance of the user
     */
    public void updateTotalBalance(float totalBalance) {
        String strDouble = String.format("%.2f", totalBalance);
        labelBalance.setText("Balance: " + strDouble + " $");
    }

    /**
     * Shows a window to confirm action
     *
     * @param message the message
     * @return true if confirmed
     */
    public int confirmAction(String message) {
        return jpCompanyDetailsView.confirmAction(message);
    }

    /**
     * Shows error message when user doesn't have enough money
     *
     * @param message the error message
     */
    public void showNoEnoughBalanceErrorMessage(String message) {
        jpCompanyDetailsView.showErrorMessage(message);
    }

    /**
     * Update the user and the company new values in the view
     *
     * @param totalBalance new balance of the user
     */
    public void updateViewsAfterPurchase(float totalBalance) {
        String strBalance = String.format("%.2f", totalBalance);
        updateTotalBalance(totalBalance);
        jpBalanceView.updateCurrentBalance(strBalance);
    }

    /**
     * Gets the Balance View
     *
     * @return Balance View
     */
    public BalanceView getBalanceView() {
        return jpBalanceView;
    }

    /**
     * Gets the company view
     *
     * @return Company View
     */
    public CompanyView getCompanyView() {
        return jpCompanyView;
    }

    /**
     * Gets the Company Details View
     *
     * @return Company Details View
     */
    public CompanyDetailView getCompanyDetailsView() {
        return jpCompanyDetailsView;
    }

}
