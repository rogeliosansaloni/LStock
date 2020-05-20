package view;

import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BotMenuView extends JPanel {
    private static final String CARD_CREATE = "Create Bot";
    private static final String CARD_EDIT = "Edit Bot";
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
    }

    public void initUI() {
        this.setBackground(color.getWHITE());
        JPanel jpCenter = new JPanel();
        jpCenter.setBackground(color.getWHITE());

        jpButtons = new JPanel(new GridLayout(4, 1, 50, 50));
        jpButtons.setBackground(Color.WHITE);

        jbCreate = createMenuButton("Create");
        jbShowList = createMenuButton("Show list");
        jbRemove = createMenuButton("Remove");
        jbActivity = createMenuButton("Enable/Disable");

        jpButtons.add(jbCreate);
        jpButtons.add(jbRemove);
        jpButtons.add(jbShowList);
        jpButtons.add(jbActivity);

        jpCenter.add(jpButtons, BorderLayout.CENTER);
        this.add(jpCenter);
    }

    /**
     * Registers the bot menu controller to all the buttons
     * @param controller bot menu controller
     */
    public void registerControllers(ActionListener controller) {
        setUpButton(jbCreate, CARD_CREATE, controller);
        setUpButton(jbRemove, CARD_REMOVE, controller);
        setUpButton(jbShowList, CARD_LIST, controller);
        setUpButton(jbActivity, CARD_EDIT, controller);
    }

    /**
     * Sets up the button by adding the action listener and setting the action
     * command
     * @param button button that we want to set up
     * @param actionCommand the action command to be set
     * @param controller the controller that implements ActionListener
     */
    private void setUpButton(JButton button, String actionCommand, ActionListener controller) {
        button.addActionListener(controller);
        button.setActionCommand(actionCommand);
    }

    /**
     * Creates menu option button
     * @param buttonLabel label of the button
     * @return the created JButton
     */
    private JButton createMenuButton(String buttonLabel) {
        Font fuenteBotones = new Font("Segoe UI Semibold", Font.PLAIN, 20);
        int anchuraBoton = 200;
        int alturaBoton = 40;

        JButton button = new JButton(buttonLabel);
        button.setFont(fuenteBotones);
        button.setForeground(Color.BLACK);
        button.setBorder(null);
        button.setBackground(color.getYELLOW());
        button.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        return button;
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
