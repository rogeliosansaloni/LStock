package view;

import model.entities.Bot;
import model.entities.Company;
import utils.StockColors;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BotsEditView extends JPanel {
    private static final String FONT = "Segoe UI";
    private static final String FONT_BUTTON = "Segoe UI Semibold";
    private static final String TITLE = "Edit Bot";
    private static final String ENABLE = "ENABLE";
    private static final String DISABLE = "DISABLE";
    private static final String CANCEL = "CANCEL";
    private static final int WIDTH = 200;
    private static final int HEIGHT = 20;
    private StockColors color;
    private JComboBox<String> jcbBot;
    private JComboBox<String> jcbCompany;
    private JPanel jpButtons;
    private JButton jbActivate;
    private JButton jbDeactivate;
    private JButton jbCancel;

    public BotsEditView() {
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

        //Buttons
        jpButtons = new JPanel(new GridLayout(1, 2, 30, 0));
        jpButtons.setBackground(color.getWHITE());

        Font buttonFont = new Font(FONT_BUTTON, Font.PLAIN, 20);
        jbActivate = new JButton(ENABLE);
        jbActivate.setFont(buttonFont);
        jbActivate.setForeground(color.getBLACK());
        jbActivate.setBorder(null);
        jbActivate.setBackground(color.getGREEN());
        jbActivate.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        jpButtons.add(jbActivate);

        jbDeactivate = new JButton(DISABLE);
        jbDeactivate.setFont(buttonFont);
        jbDeactivate.setForeground(color.getBLACK());
        jbDeactivate.setBorder(null);
        jbDeactivate.setBackground(color.getRED());
        jbDeactivate.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        jpButtons.add(jbDeactivate);

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

    public int getBotId() {
        String name = jcbBot.getSelectedItem().toString();
        return Integer.parseInt(name.replaceAll("[^0-9]", ""));
    }

    public String getCompany() {
        return jcbCompany.getSelectedItem().toString();
    }

    public void registerController(ActionListener controller) {
        this.jbActivate.addActionListener(controller);
        this.jbActivate.setActionCommand("activateBot");
        this.jbDeactivate.addActionListener(controller);
        this.jbDeactivate.setActionCommand("deactivateBot");
    }
}
