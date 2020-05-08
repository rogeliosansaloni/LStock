package view;

import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BotMenuView extends JPanel {
    private static final String CARD_MAIN = "Bot Menu";
    private static final String CARD_CREATE = "Create Bot";
    private static final String CARD_EDIT = "Configure Bot";
    private static final String CARD_REMOVE = "Remove Bot";
    private static final String CARD_LIST = "Bots";
    private StockColors color;

    // Main views for each menu option
    private BotsCreateView botsCreateView;
    private BotsEditView botsEditView;
    private BotsRemoveView botsRemoveView;
    private BotsListView botsListView;

    // Main panels
    private JPanel jpHeader;
    private JPanel jpMain;

    // Buttons
    private JButton jbCreate;
    private JButton jbRemove;
    private JButton jbShowList;
    private JButton jbActivity;

    public BotMenuView() {
        color = new StockColors();
        initUI();
        initViews();
    }

    private void initViews() {
        this.botsCreateView = new BotsCreateView();
        this.botsEditView = new BotsEditView();
        this.botsRemoveView = new BotsRemoveView();
        this.botsListView = new BotsListView();
        addToCardLayout();
    }

    public void initUI() {
        this.setLayout(new CardLayout());
        jpMain = new JPanel();
        jpMain.setBackground(color.getWHITE());

        // Main header
        jpHeader = new JPanel(new BorderLayout());
        jpHeader.setBackground(color.getDarkGreyHeader());

        // Menu
        JPanel jpCenter = new JPanel(new GridLayout(2, 2));
        jbCreate = new JButton("Create");
        jbShowList = new JButton("Show list");
        jbRemove = new JButton("Remove");
        jbActivity = new JButton("Enable/Disable");

        jpCenter.add(jbCreate);
        jpCenter.add(jbRemove);
        jpCenter.add(jbShowList);
        jpCenter.add(jbActivity);

        jpMain.add(jpCenter);
    }

    /**
     * Add diferent views to layout
     */
    private void addToCardLayout() {
        //this.add(jpMain, CARD_MAIN);
        this.add(botsCreateView, CARD_CREATE);
        this.add(botsEditView, CARD_EDIT);
        this.add(botsListView, CARD_LIST);
        this.add(botsRemoveView, CARD_REMOVE);
    }

    /**
     * Shows desired bot view
     *
     * @param card la vista que se quiere mostrar
     */
    public void updateView(String card) {
        CardLayout cardLayout = (CardLayout) this.getLayout();
        switch (card) {
            case CARD_CREATE:
                cardLayout.show(this, CARD_CREATE);
                break;
            case CARD_EDIT:
                cardLayout.show(this, CARD_EDIT);
                break;
            case CARD_LIST:
                cardLayout.show(this, CARD_LIST);
                break;
            case CARD_REMOVE:
                cardLayout.show(this, CARD_REMOVE);
                break;
        }
    }

    public void registerController(ActionListener controller) {
        jbCreate.addActionListener(controller);
        jbCreate.setActionCommand("CREATE_VIEW");
        jbRemove.addActionListener(controller);
        jbRemove.setActionCommand("REMOVE_VIEW");
        jbShowList.addActionListener(controller);
        jbShowList.setActionCommand("LIST_VIEW");
        jbActivity.addActionListener(controller);
        jbActivity.setActionCommand("ACTIVITY_VIEW");
    }

    public BotsCreateView getBotsCreateView() {
        return botsCreateView;
    }

    public void setBotsCreateView(BotsCreateView botsCreateView) {
        this.botsCreateView = botsCreateView;
    }

    public BotsEditView getBotsEditView() {
        return botsEditView;
    }

    public void setBotsEditView(BotsEditView botsEditView) {
        this.botsEditView = botsEditView;
    }

    public BotsRemoveView getBotsRemoveView() {
        return botsRemoveView;
    }

    public void setBotsRemoveView(BotsRemoveView botsRemoveView) {
        this.botsRemoveView = botsRemoveView;
    }

    public BotsListView getBotsListView() {
        return botsListView;
    }

    public void setBotsListView(BotsListView botsListView) {
        this.botsListView = botsListView;
    }
}
