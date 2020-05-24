package view;

import controller.*;
import model.entities.Bot;
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
    private static final String CARD_TOPTEN = "Top 10 Companies";
    private static final int PANEL_WIDTH = 1080;
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
    private JMenuItem option1,option2, option3, option4;
    private StockColors color;
    private HomeView jpHomeView;
    private SharesListView jpSharesView;
    private TopTenCompaniesView jpTopTenCompaniesView;
    private BotMenuView jpMenuBots;
    private BotsCreateView jpBotsCreateView;
    private BotsRemoveView jpBotsRemoveView;
    private BotsListView jpBotsListView;
    private BotsEditView jpBotsEditView;

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
        jpBotsEditView = new BotsEditView();
        jpHomeView = new HomeView();
        jpSharesView = new SharesListView();
        addToCardLayout();
    }

    /**
     * Add diferent views to layout
     */
    public void addToCardLayout(HomeView homeView, SharesListView sharesView, TopTenCompaniesView topTenCompaniesView) {
        jpHomeView = homeView;
        jpSharesView = sharesView;
        jpTopTenCompaniesView = topTenCompaniesView;
        jpCenter.add(jpHomeView, CARD_HOME);
        jpCenter.add(jpMenuBots, CARD_BOTS);
        jpCenter.add(jpBotsCreateView, CARD_BOTS_CREATE);
        jpCenter.add(jpBotsRemoveView, CARD_BOTS_REMOVE);
        jpCenter.add(jpBotsListView, CARD_BOTS_LIST);
        jpCenter.add(jpBotsEditView, CARD_BOTS_EDIT);
        jpCenter.add(jpSharesView, CARD_USERS);
        jpCenter.add(jpTopTenCompaniesView, CARD_TOPTEN);

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

        //Option1
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
        //Option4
        option4 = new JMenuItem("TOP 10 Companies");
        option4.setHorizontalAlignment(SwingConstants.CENTER);
        option4.setBackground(Color.WHITE);
        option4.setBorder(borderOptions);
        option4.setForeground(color.getDarkGreyText());
        option4.setFont(fontMenu);
        menuOptions.add(option4);
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
        option4.addActionListener(actionListener);
        option4.setActionCommand(CARD_TOPTEN);
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

    /**
     * Registers controller for the Bot remove view
     * @param controller BotsRemove controller
     */
    public void registerBotRemoveController(BotsRemoveController controller,
                                            BotsRemoveComboBoxController comboBoxController) {
        jpBotsRemoveView.registerController(controller);
        jpBotsRemoveView.registerComboBoxController(comboBoxController);
    }

    public void registerBotEditController(BotsEditController controller, BotsEditComboBoxController comboBoxController) {
        jpBotsEditView.registerController(controller);
        jpBotsEditView.registerComboBoxController(comboBoxController);
    }

    /**
     * Registers controller for Bots List controller
     * @param controller BotsListController
     */
    public void registerBotListController(BotsListController controller) {
        jpBotsListView.registerController(controller);
    }

    /**
     * Gets the Bots List view
     * @return view for Bot list
     */
    public BotsListView getBotsListView () { return jpBotsListView; }

    /**
     * Gets Bots Remove view
     * @return view for Bot removal
     */
    public BotsRemoveView getBotsRemoveView() { return  jpBotsRemoveView; }

    /**
     * Gets Bots Create view
     * @return view for Bot creation
     */
    public BotsCreateView getBotsCreateView() {
        return jpBotsCreateView;
    }

    /**
     * G3ets Bots Edit view
     * @return view for Bot edition
     */
    public BotsEditView getBotsEditView() {
        return jpBotsEditView;
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
            case CARD_TOPTEN:
                cardLayout.show(jpCenter, CARD_TOPTEN);
            case CARD_BOTS_CREATE:
                cardLayout.show(jpCenter, CARD_BOTS_CREATE);
                break;
            case CARD_BOTS_REMOVE:
                cardLayout.show(jpCenter, CARD_BOTS_REMOVE);
                break;
            case CARD_BOTS_LIST:
                cardLayout.show(jpCenter, CARD_BOTS_LIST);
                break;
            case CARD_BOTS_EDIT:
                cardLayout.show(jpCenter, CARD_BOTS_EDIT);
                break;
        }
    }
}

