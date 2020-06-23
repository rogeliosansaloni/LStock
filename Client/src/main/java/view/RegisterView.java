package view;

import controller.LoginFocusController;
import controller.RegisterFocusController;
import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;

/**
 * Register view
 */
public class RegisterView extends JFrame {
    private static final String PATH_LOGO = "/Images/stock.png";
    private static final String TITLE = "StockLS - C2";
    private static final String NICKNAME_LABEL = "Nickname";
    private static final String EMAIL_LABEL = "Email";
    private static final String PASSWORD_LABEL = "Password";
    private static final String VERIFY_PASSWORD_LABEL = "Verify Password";
    private static final String LOGIN = "Login";
    private static final String REGISTER = "Register";
    private static final String ERROR_CASE_1 = "Fields Required";
    private static final String ERROR_CASE_2 = "Fields Empty";
    private static final String ERROR_CASE_3 = "Email Error";
    private static final String ERROR_CASE_4 = "Password Match";
    private static final String ERROR_CASE_5 = "Password Length";
    private static final String ERROR_CASE_6 = "Password Format";
    private static final String ERROR_CASE_7 = "Email Taken";
    private static final String ERROR_CASE_8 = "Nickname Taken";
    private static final String ERROR_MESSAGE_1 = "All fields are required.";
    private static final String ERROR_MESSAGE_2 = "Fields can't be empty.";
    private static final String ERROR_MESSAGE_3 = "Email is not valid.";
    private static final String ERROR_MESSAGE_4 = "Password doesn't match.";
    private static final String ERROR_MESSAGE_5 = "Password must have at least 8 characters.";
    private static final String ERROR_MESSAGE_6 = "Password must have at least one capital letter and one number";
    private static final String ERROR_REGISTER_1 = "This email has an account already.";
    private static final String ERROR_REGISTER_2 = "This username is already taken.";

    private static final int anchuraPanel = 1080;
    private static final int alturaPanel = 740;
    private JLabel labelLogo;
    private JLabel labelStock;
    protected JPanel jpNorth;
    private JPanel jpCenter;
    private JPanel jpBotones;
    private JTextField[] jtField;
    private JButton jbLogin;
    private JButton jbRegister;
    private StockColors color;

    /**
     * Creates and initializes the register view
     */
    public RegisterView() {
        color = new StockColors();
        this.setTitle(TITLE);
        this.setPreferredSize(new Dimension(anchuraPanel, alturaPanel));
        this.setSize(anchuraPanel, alturaPanel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        initUI();
    }

    /**
     * Creates the UI of the register form
     */
    public void initUI() {
        JPanel jpRegister = new JPanel();
        jpRegister.setLayout(new BorderLayout());
        jpRegister.setBackground(Color.WHITE);

        //Header
        jpNorth = new JPanel(new BorderLayout());
        jpNorth.setBackground(Color.WHITE);
        URL url = getClass().getResource(PATH_LOGO);
        ImageIcon imageIcon = new ImageIcon(url);
        Image scaleImage = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(scaleImage);
        labelLogo = new JLabel(imageIcon);

        jpNorth.add(labelLogo, BorderLayout.CENTER);
        labelStock = new JLabel("StockLS", SwingConstants.CENTER);
        Font fuenteLogo = new Font("Segoe UI", Font.PLAIN, 50);
        labelStock.setFont(fuenteLogo);
        jpNorth.add(labelStock, BorderLayout.SOUTH);
        jpNorth.setBorder(BorderFactory.createEmptyBorder(40, 0, 35, 0));
        jpRegister.add(jpNorth, BorderLayout.NORTH);

        //We create a center panel for the form and the bottons
        jpCenter = new JPanel();
        jpCenter = new JPanel(new GridLayout(6, 1, 0, 15));
        jpCenter.setBackground(Color.WHITE);

        Font fuenteCampo = new Font("Segoe UI", Font.ITALIC, 20);
        jtField = new JTextField[4];
        jtField[0] = new JTextField(NICKNAME_LABEL);
        jtField[0].setBorder(null);
        jtField[0].setFont(fuenteCampo);
        jtField[0].setForeground(Color.GRAY);
        jtField[0].setBackground(color.getTEXTFIELD());
        jpCenter.add(jtField[0]);

        //We add separation space between fields
        jtField[1] = new JTextField(EMAIL_LABEL);
        jtField[1].setBorder(null);
        jtField[1].setFont(fuenteCampo);
        jtField[1].setForeground(Color.GRAY);
        jtField[1].setBackground(color.getTEXTFIELD());
        jpCenter.add(jtField[1]);

        //We add separation space between fields
        jtField[2] = new JTextField(PASSWORD_LABEL);
        jtField[2].setBorder(null);
        jtField[2].setFont(fuenteCampo);
        jtField[2].setForeground(Color.GRAY);
        jtField[2].setBackground(color.getTEXTFIELD());
        jpCenter.add(jtField[2]);

        //We add separation space between fields
        jtField[3] = new JTextField(VERIFY_PASSWORD_LABEL);
        jtField[3].setBorder(null);
        jtField[3].setFont(fuenteCampo);
        jtField[3].setForeground(Color.GRAY);
        jtField[3].setBackground(color.getTEXTFIELD());
        jpCenter.add(jtField[3]);

        //We create a filler row to separate the buttons and the register fields
        JLabel relleno = new JLabel();
        relleno.setPreferredSize(new Dimension(200, 1));
        jpCenter.add(relleno);

        jpBotones = new JPanel(new GridLayout(1, 2, 30, 0));
        jpBotones.setBackground(Color.WHITE);
        int anchuraBoton = 200;
        int alturaBoton = 40;

        Font fuenteBotones = new Font("Segoe UI Semibold", Font.PLAIN, 20);
        jbLogin = new JButton(LOGIN);
        jbLogin.setFont(fuenteBotones);
        jbLogin.setForeground(Color.BLACK);
        jbLogin.setBorder(null);
        jbLogin.setBackground(color.getLightGrey());
        jbLogin.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jbRegister = new JButton(REGISTER);
        jbRegister.setFont(fuenteBotones);
        jbRegister.setForeground(Color.BLACK);
        jbRegister.setBorder(null);
        jbRegister.setBackground(color.getYELLOW());
        jbRegister.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jpBotones.add(jbRegister);
        jpBotones.add(jbLogin);
        jpCenter.add(jpBotones);
        jpCenter.setBorder(BorderFactory.createEmptyBorder(0, 250, 50, 250));

        jpRegister.add(jpCenter, BorderLayout.CENTER);
        this.getContentPane().add(jpRegister);
    }

    /**
     * Proc that adds an actionListener to fields and buttons.
     *
     * @param actionListener the actionListener
     */
    public void registerController(ActionListener actionListener) {
        jbLogin.addActionListener(actionListener);
        jbLogin.setActionCommand(LOGIN);
        jbRegister.addActionListener(actionListener);
        jbRegister.setActionCommand(REGISTER);
    }

    /**
     * Registers FocusListener for each JTextField of the view
     */
    public void registerFocusController() {
        this.jtField[0].addFocusListener(new RegisterFocusController(this,0, NICKNAME_LABEL));
        this.jtField[1].addFocusListener(new RegisterFocusController(this, 1, EMAIL_LABEL));
        this.jtField[2].addFocusListener(new RegisterFocusController(this, 2, PASSWORD_LABEL));
        this.jtField[3].addFocusListener(new RegisterFocusController(this, 3, VERIFY_PASSWORD_LABEL));
    }

    /**
     * Gets a specific JTextFrield
     * @param i id of the JTextField
     * @return Jtextfield
     */
    public JTextField getJTextField(int i) {
        return jtField[i];
    }

    /**
     * Sets the texts of a specific JTextField
     * @param i
     * @param text
     */
    public void setJTextField (int i, String text) {
        jtField[i].setText(text);
    }

    /**
     * Proc that shows a pop-up window with an error message
     *
     * @param error message to be shown
     */
    public void showErrorMessages(String error) {
        switch (error) {
            case ERROR_CASE_1:
                JOptionPane.showMessageDialog(null, ERROR_MESSAGE_1);
                break;
            case ERROR_CASE_2:
                JOptionPane.showMessageDialog(null, ERROR_MESSAGE_2);
                break;
            case ERROR_CASE_3:
                JOptionPane.showMessageDialog(null, ERROR_MESSAGE_3);
                break;
            case ERROR_CASE_4:
                JOptionPane.showMessageDialog(null, ERROR_MESSAGE_4);
                break;
            case ERROR_CASE_5:
                JOptionPane.showMessageDialog(null, ERROR_MESSAGE_5);
                break;
            case ERROR_CASE_6:
                JOptionPane.showMessageDialog(null, ERROR_MESSAGE_6);
                break;
            case ERROR_CASE_7:
                JOptionPane.showMessageDialog(null, ERROR_REGISTER_1);
                break;
            case ERROR_CASE_8:
                JOptionPane.showMessageDialog(null, ERROR_REGISTER_2);
                break;
        }
    }

    /**
     * Function that returns the nickname typed in the nickname field
     *
     * @return Nickname
     */
    public String getNickname() {
        return jtField[0].getText();
    }

    /**
     * Function that returns the email typed in the email field
     *
     * @return Email
     */
    public String getEmail() {
        return jtField[1].getText();
    }

    /**
     * Function that returns the password typed in the password field
     *
     * @return Password
     */
    public String getPassword() {
        return jtField[2].getText();
    }

    /**
     * Function that returns the password typed in the verify password field
     *
     * @return Repeated password
     */
    public String getPasswordVerification() {
        return jtField[3].getText();
    }

}
