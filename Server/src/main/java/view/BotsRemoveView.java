package view;

import model.entities.Bot;
import model.entities.Company;
import utils.StockColors;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class BotsRemoveView extends JPanel {
    private static final String FONT = "Segoe UI";
    private static final String FONT_BUTTON = "Segoe UI Semibold";
    private static final String TITLE = "Remove Bot";
    private static final String REMOVE = "REMOVE";
    private static final String CANCEL = "CANCEL";
    private static final int WIDTH = 200;
    private static final int HEIGHT = 20;
    private StockColors color;
    private JComboBox<String> jcbBot;
    private JComboBox<String> jcbCompany;
    private JPanel jpButtons;
    private JLabel jlStatus;
    private JButton jbRemove;
    private JButton jbCancel;

    public BotsRemoveView() {
        color = new StockColors();
        Font font = new Font(FONT, Font.ITALIC, 20);

        this.setLayout(new GridLayout(6,1,0,15));
        this.setBackground(color.getWHITE());

        JLabel jlTitle = new JLabel(TITLE);
        jlTitle.setPreferredSize(new Dimension(200, 1));
        jlTitle.setHorizontalAlignment(JLabel.CENTER);
        jlTitle.setFont(font);
        this.add(jlTitle);

        //Company combobox
        jcbCompany = new JComboBox<>();
        jcbCompany.setUI(new BasicComboBoxUI());
        jcbCompany.setBorder(null);
        jcbCompany.setFont(font);
        jcbCompany.setForeground(Color.GRAY);
        jcbCompany.setBackground(color.getTEXTFIELD());
        jcbCompany.setActionCommand("bot-company");
        this.add(jcbCompany);

        //Bot combobox
        jcbBot = new JComboBox<>();
        jcbBot.setUI(new BasicComboBoxUI());
        jcbBot.setBorder(null);
        jcbBot.setFont(font);
        jcbBot.setForeground(Color.GRAY);
        jcbBot.setBackground(color.getTEXTFIELD());
        jcbBot.setActionCommand("bot-id");
        this.add(jcbBot);

        //Status
        jlStatus = new JLabel();
        jlStatus.setPreferredSize(new Dimension(200, 1));
        jlStatus.setHorizontalAlignment(JLabel.CENTER);
        jlStatus.setFont(font);
        this.add(jlStatus);

        //Buttons
        jpButtons = new JPanel(new GridLayout(1, 2, 30, 0));
        jpButtons.setBackground(color.getWHITE());

        Font buttonFont = new Font(FONT_BUTTON, Font.PLAIN, 20);
        jbRemove = new JButton(REMOVE);
        jbRemove.setFont(buttonFont);
        jbRemove.setForeground(color.getBLACK());
        jbRemove.setBorder(null);
        jbRemove.setBackground(color.getGREEN());
        jbRemove.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        jpButtons.add(jbRemove);

        jbCancel = new JButton(CANCEL);
        jbCancel.setFont(buttonFont);
        jbCancel.setForeground(color.getBLACK());
        jbCancel.setBorder(null);
        jbCancel.setBackground(color.getRED());
        jbCancel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        jpButtons.add(jbCancel);

        this.add(jpButtons);
        this.setBorder(BorderFactory.createEmptyBorder(0, 150, 50, 150));
    }

    /**
     * Gets the selected company name
     *
     * @return the company name
     */
    public String getCompanyName() {
        return String.valueOf(jcbCompany.getSelectedItem());
    }

    /**
     * Shows the companies in the combobox;
     *
     * @param companies list of the companies
     */
    public void showCompanies(ArrayList<Company> companies) {
        Font font = new Font(FONT, Font.ITALIC, 20);
        int numCompanies = companies.size();
        for (int i = 0; i < numCompanies; i++) {
            Company company = (Company) companies.get(i);
            jcbCompany.addItem(company.getName());
        }
    }

    /**
     * Shows all bots for a specific company in the combo box
     * @param bots list of bots
     */
    public void showBots(ArrayList<Bot> bots) {
        // Clear bot list from combo box
        jcbBot.removeAllItems();

        // Add retrieved bots to the list
        Font font = new Font(FONT, Font.ITALIC, 20);
        int numBots = bots.size();
        for (int i = 0; i < numBots; i++) {
            Bot bot = (Bot) bots.get(i);
            jcbBot.addItem(String.format("Bot %d", bot.getBotId()));
        }
    }

    /**
     * Registers the controller for the buttons
     * @param controller controller for the buttons
     */
    public void registerController(ActionListener controller) {
        this.jbRemove.addActionListener(controller);
        this.jbRemove.setActionCommand(REMOVE);
        this.jbCancel.addActionListener(controller);
        this.jbCancel.setActionCommand(CANCEL);
    }

    /**
     * Registers the controller for the combo boxes
     * @param itemListener item listener for the combo boxes
     */
    public void registerComboBoxController(ItemListener itemListener) {
        this.jcbCompany.addItemListener(itemListener);
        this.jcbBot.addItemListener(itemListener);
    }

    /**
     * Gets id of bot to be removed
     * @return id of the bot
     */
    public int getBotId() {
        String name = jcbBot.getSelectedItem().toString();
        return Integer.parseInt(name.replaceAll("[^0-9]", ""));
    }

    /**
     * Shows message when a bot activity status change
     * @param message the message
     */
    public void showMessages(String message) {
        JOptionPane.showMessageDialog(null,message);

    }
}
