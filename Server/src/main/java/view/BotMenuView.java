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

    // Buttons
    private JButton jbCreate;
    private JButton jbRemove;
    private JButton jbShowList;
    private JButton jbEnable;
    private JButton jbDisable;

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
        JPanel jpMain = new JPanel();
        jpMain.setLayout(new BorderLayout());
        jpMain.setBackground(color.getWHITE());

        // Main header
        jpHeader = new JPanel(new BorderLayout());
        jpHeader.setBackground(color.getDarkGreyHeader());
    }

    public void registerControllers(ActionListener controller) {
        jbCreate.addActionListener(controller);
        jbRemove.addActionListener(controller);
        jbShowList.addActionListener(controller);
        jbEnable.addActionListener(controller);
        jbDisable.addActionListener(controller);
    }
}
