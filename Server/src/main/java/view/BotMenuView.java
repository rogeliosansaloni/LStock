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
    private JPanel jpButtons;
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
    }

    public void initUI() {
        this.setBackground(color.getWHITE());
        JPanel jpCenter = new JPanel();
        jpCenter.setBackground(color.getWHITE());

        jpButtons = new JPanel(new GridLayout(2, 2, 30, 0));
        jpButtons.setBackground(Color.WHITE);
        int anchuraBoton = 200;
        int alturaBoton = 40;

        Font fuenteBotones = new Font("Segoe UI Semibold", Font.PLAIN, 20);
        jbCreate = new JButton("Create");
        jbCreate.setFont(fuenteBotones);
        jbCreate.setForeground(Color.BLACK);
        jbCreate.setBorder(null);
        jbCreate.setBackground(color.getYELLOW());
        jbCreate.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jbShowList = new JButton("Show list");
        jbShowList.setFont(fuenteBotones);
        jbShowList.setForeground(Color.BLACK);
        jbShowList.setBorder(null);
        jbShowList.setBackground(color.getYELLOW());
        jbShowList.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jbRemove = new JButton("Remove");
        jbRemove.setFont(fuenteBotones);
        jbRemove.setForeground(Color.BLACK);
        jbRemove.setBorder(null);
        jbRemove.setBackground(color.getYELLOW());
        jbRemove.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jbActivity = new JButton("Enable/Disable");
        jbActivity.setFont(fuenteBotones);
        jbActivity.setForeground(Color.BLACK);
        jbActivity.setBorder(null);
        jbActivity.setBackground(color.getYELLOW());
        jbActivity.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jpButtons.add(jbCreate);
        jpButtons.add(jbRemove);
        jpButtons.add(jbShowList);
        jpButtons.add(jbActivity);

        jpCenter.add(jpButtons, BorderLayout.CENTER);
        this.add(jpCenter);
    }

    public void registerControllers(ActionListener controller) {
        jbCreate.addActionListener(controller);
        jbCreate.setActionCommand(CARD_CREATE);
        jbRemove.addActionListener(controller);
        jbRemove.setActionCommand(CARD_REMOVE);
        jbShowList.addActionListener(controller);
        jbShowList.setActionCommand(CARD_LIST);
        jbActivity.addActionListener(controller);
        jbActivity.setActionCommand(CARD_EDIT);
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
