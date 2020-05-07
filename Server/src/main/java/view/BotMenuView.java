package view;

import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BotMenuView extends JPanel {
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
        initViews();
        initUI();
    }

    private void initViews() {
        this.botsCreateView = new BotsCreateView();
        this.botsEditView = new BotsEditView();
        this.botsRemoveView = new BotsRemoveView();
        this.botsListView = new BotsListView();
    }

    public void initUI() {
        jpMain = new JPanel();
        jpMain.setLayout(new CardLayout());
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
     * Shows desired bot view
     *
     * @param card la vista que se quiere mostrar
     */
    public void updateView(String card) {
        CardLayout cardLayout = (CardLayout) jpMain.getLayout();
        switch (card) {
            case CARD_CREATE:
                cardLayout.show(jpMain, CARD_CREATE);
                break;
            case CARD_EDIT:
                cardLayout.show(jpMain, CARD_EDIT);
                break;
            case CARD_LIST:
                cardLayout.show(jpMain, CARD_LIST);
                break;
            case CARD_REMOVE:
                cardLayout.show(jpMain, CARD_REMOVE);
                break;
        }
    }

    public void registerController(ActionListener controller) {
        jbCreate.addActionListener(controller);
        jbRemove.addActionListener(controller);
        jbShowList.addActionListener(controller);
        jbActivity.addActionListener(controller);
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
