package view;

import controller.BotCreateFocusController;
import model.entities.Company;
import utils.StockColors;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * View for bot creation
 */
public class BotsCreateView extends JPanel {
    private static final String PATH_ARROW_ICON = "/Images/dropdown.png";
    private static final String FONT = "Segoe UI";
    private static final String FONT_BUTTON = "Segoe UI Semibold";
    private static final String TITLE = "Create Bot";
    private static final String BUY_PERCENTAGE_LABEL = "Buy Percentage";
    private static final String ACTIVATE_TIME_LABEL = "Activation Time";
    private static final String CREATE = "CREATE";
    private static final String CANCEL = "CANCEL";
    private static final int WIDTH = 200;
    private static final int HEIGHT = 20;
    private JPanel jpButtons;
    private JComboBox<String> jcbCompany;
    private JTextField[] jtField;
    private JButton jbCreate;
    private JButton jbCancel;
    private StockColors color;

    /**
     * Creates the view for bot creation
     */
    public BotsCreateView() {
        color = new StockColors();
        Font font = new Font(FONT, Font.ITALIC, 20);

        this.setLayout(new GridLayout(6,1,0,15));
        this.setBackground(color.getWHITE());

        JLabel jlTitle = new JLabel(TITLE);
        jlTitle.setPreferredSize(new Dimension(200, 1));
        jlTitle.setHorizontalAlignment(JLabel.CENTER);
        jlTitle.setFont(font);
        this.add(jlTitle);

        jtField = new JTextField[2];

        //Company combobox
        jcbCompany = new JComboBox<>();
        jcbCompany.setUI(new BasicComboBoxUI());
        jcbCompany.setBorder(null);
        jcbCompany.setFont(font);
        jcbCompany.setForeground(Color.GRAY);
        jcbCompany.setBackground(color.getTEXTFIELD());
        this.add(jcbCompany);

        //Buy Percentage
        jtField[0] = new JTextField(BUY_PERCENTAGE_LABEL);
        jtField[0].setBorder(null);
        jtField[0].setFont(font);
        jtField[0].setForeground(Color.GRAY);
        jtField[0].setBackground(color.getTEXTFIELD());
        this.add(jtField[0]);

        //Activation Time
        jtField[1] = new JTextField(ACTIVATE_TIME_LABEL);
        jtField[1].setBorder(null);
        jtField[1].setFont(font);
        jtField[1].setForeground(Color.GRAY);
        jtField[1].setBackground(color.getTEXTFIELD());
        this.add(jtField[1]);

        jpButtons = new JPanel(new GridLayout(1, 2, 30, 0));
        jpButtons.setBackground(color.getWHITE());

        Font buttonFont = new Font(FONT_BUTTON, Font.PLAIN, 20);
        jbCreate = new JButton(CREATE);
        jbCreate.setFont(buttonFont);
        jbCreate.setForeground(color.getBLACK());
        jbCreate.setBorder(null);
        jbCreate.setBackground(color.getGREEN());
        jbCreate.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        jpButtons.add(jbCreate);

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
     * Registers controller for each field of the form
     *
     * @param controller the action listener
     */
    public void registerController(ActionListener controller) {
        this.jbCreate.addActionListener(controller);
        this.jbCreate.setActionCommand(CREATE);
        this.jbCancel.addActionListener(controller);
        this.jbCancel.setActionCommand(CANCEL);
    }

    /**
     * Registers FocusListener for each JTextField of the view
     */
    public void registerFocusController() {
        this.jtField[0].addFocusListener(new BotCreateFocusController(this, 0, BUY_PERCENTAGE_LABEL));
        this.jtField[1].addFocusListener(new BotCreateFocusController(this, 1, ACTIVATE_TIME_LABEL));
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
     * Gets the indicated bot name
     *
     * @return the bot name
     */
    public String getBotName() {
        return jtField[0].getText();
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
     * Gets the indicated probability of being bought/sold
     *
     * @return the probability in percentage
     */
    public String getPercentage() {
        return jtField[0].getText();
    }

    /**
     * Gets the indicated activation time
     *
     * @return the activation time
     */
    public String getActivation() {
        return jtField[1].getText();
    }

    /**
     * Shows error message
     *
     * @param message the error message
     */
    public void showErrorMessage (String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Gets all JTextFields
     * @return array of all JTextFields of the view
     */
    public JTextField[] getJtField() {
        return jtField;
    }

    /**
     * Gets a specific JTextFrield
     * @param i id of the JTextField
     * @return
     */
    public JTextField getJTextField(int i) {
        return jtField[i];
    }

    /**
     * Sets the texts of a specific JTextField
     * @param i
     * @param text
     */
    public void setJTextField(int i, String text){
        jtField[i].setText(text);
    }
}
