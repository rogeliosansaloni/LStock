package view;

import controller.*;
import model.entities.Company;
import utils.StockColors;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainView extends JFrame {

    private static final String PATH_LOGO = "/Images/stock.png";
    private static final String PATH_ARROW_ICON = "/Images/dropdown.png";
    private static final String TITLE = "StockLS - C2";
    private static final String CARD_HOME = "Home";
    private static final String CARD_USERS = "List of Users";
    private static final String CARD_BOTS = "Manage Bots";
    private static final String CARD_BOTS_CREATE = "Create Bot";
    private static final String CARD_BOTS_EDIT = "Edit Bot";
    private static final String CARD_BOTS_REMOVE = "Remove Bot";
    private static final String CARD_BOTS_LIST = "Bots";
    private static final int PANEL_WIDTH = 780;
    private static final int PANEL_HEIGHT = 740;

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
    private HomeView jpHomeView;
    private SharesListView jpSharesView;
    private BotMenuView jpMenuBots;
    private BotsCreateView jpBotsCreateView;
    private BotsRemoveView jpBotsRemoveView;
    private BotsListView jpBotsListView;

    public MainView() {
        color = new StockColors();
        this.setTitle(TITLE);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        initUI();
        initAllViews();
    }

    /**
     * Initializes all views
     */
    public void initAllViews() {
        jpMenuBots = new BotMenuView();
        jpBotsCreateView = new BotsCreateView();
        jpBotsRemoveView = new BotsRemoveView();
        jpBotsListView = new BotsListView();
        jpHomeView = new HomeView();
        jpSharesView = new SharesListView();
        //TODO: Add the rest of views
        addToCardLayout();
    }

    /**
     * Add diferent views to layout
     */
    private void addToCardLayout() {
        jpCenter.add(jpHomeView, CARD_HOME);
        jpCenter.add(jpMenuBots, CARD_BOTS);
        jpCenter.add(jpBotsCreateView, CARD_BOTS_CREATE);
        jpCenter.add(jpBotsRemoveView, CARD_BOTS_REMOVE);
        jpCenter.add(jpBotsListView, CARD_BOTS_LIST);
        jpCenter.add(jpSharesView, CARD_USERS);
        //TODO: Add the rest of views
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

    /**
     * Register controllers to Menu Bar
     * @param actionListener ActionLister
     */
    public void registerController (ActionListener actionListener) {
        option1.addActionListener(actionListener);
        option1.setActionCommand(CARD_HOME);
        option2.addActionListener(actionListener);
        option2.setActionCommand(CARD_USERS);
        option3.addActionListener(actionListener);
        option3.setActionCommand(CARD_BOTS);
    }

    /**
     * Registers controller for the HomeView
     *
     * @param controller Home controller
     */
    public void registerHomeController(HomeController controller) {
        jpHomeView.registerController(controller);
    }

    /**
     * Registers controller for the BotMenuView
     *
     * @param controller Bot menu controller
     */
    public void registerBotMenuController(BotMenuController controller) {
        jpMenuBots.registerControllers(controller);
    }

    /**
     * Registers controller for the Bot create view
     *
     * @param controller BotsCreate controller
     */
    public void registerBotCreateController(BotsCreateController controller) {
        jpBotsCreateView.registerController(controller);
        jpBotsCreateView.registerFocusController();
    }
    public void registerBotEditController() {}
    public void registerBotRemoveController() {}

    /**
     * Registers controller for Bots List controller
     * @param controller BotsListController
     */
    public void registerBotListController(BotsListController controller) {
        jpBotsListView.registerController(controller);
    }

    /**
     * Gets the Bots List view
     * @return Bots list view
     */
    public BotsListView getBotsListView () { return jpBotsListView; }

    /**
     * Shows all of the companies name in the Bots Create view combobox
     * @param companies receives a list of companies
     */
    public void showCompanies (ArrayList<Company> companies) {
        jpBotsCreateView.showCompanies(companies);
    }

    /**
     * Gets the selected company name
     *
     * @return the company name
     */
    public String getSelectedCompany() { return jpBotsCreateView.getCompanyName(); }

    /**
     * Gets the indicated bot name
     *
     * @return the bot name
     */
    public String getBotName() {
        return jpBotsCreateView.getBotName();
    }

    /**
     * Gets the probability of being bought/sold
     *
     * @return the probability in percentage
     */
    public String getBotPercentage() {
        return jpBotsCreateView.getPercentage();
    }

    /**
     * Gets the activation time indicated in the Bots Create view
     *
     * @return the activation time
     */
    public String getBotActivation() {
        return jpBotsCreateView.getActivation();
    }

    /**
     * Shows error message in the Bots Create view
     *
     * @param message the error message
     */
    public void showErrorMessage (String message) {
        jpBotsCreateView.showErrorMessage(message);
    }


    /**
     * Shows desired view
     * @param card
     */
    public void updateView(String card) {
        CardLayout cardLayout = (CardLayout) jpCenter.getLayout();
        switch (card) {
            case CARD_HOME:
                cardLayout.show(jpCenter, CARD_HOME);
                break;
            case CARD_USERS:
                cardLayout.show(jpCenter, CARD_USERS);
                break;
            case CARD_BOTS:
                cardLayout.show(jpCenter, CARD_BOTS);
                break;
            case CARD_BOTS_CREATE:
                cardLayout.show(jpCenter, CARD_BOTS_CREATE);
                break;
            case CARD_BOTS_EDIT:
                break;
            case CARD_BOTS_REMOVE:
                cardLayout.show(jpCenter, CARD_BOTS_REMOVE);
                break;
            case CARD_BOTS_LIST:
                cardLayout.show(jpCenter, CARD_BOTS_LIST);
                break;
        }
    }
}

