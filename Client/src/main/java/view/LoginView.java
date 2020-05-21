package view;

import controller.LoginFocusController;
import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Login view
 */
public class LoginView extends JFrame {
    private static final String PATH_LOGO = "/Images/stock.png";
    private static final String TITLE = "StockLS - C2";
    private static final String ERROR_MESSAGE_1 = "All fields are required.";
    private static final String ERROR_MESSAGE_2 = "Fields can't be empty.";
    private static final String USER_LABEL = "Nickname or Email";
    private static final String PASSWORD_LABEL = "Password";
    private static final String LOGIN = "Login";
    private static final String REGISTER = "Register";
    private static final int anchuraPanel = 1080;
    private static final int alturaPanel = 740;
    private JLabel labelLogo;
    private JLabel labelStock;
    protected JPanel jpNorth;
    private JPanel jpCenter;
    private JPanel jpCampos;
    private JPanel jpBotones;
    private JTextField[] jtField;
    private JButton jbLogin;
    private JButton jbRegister;
    private StockColors color;

    /**
     * Creates and initializes the login view
     */
    public LoginView() {
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
     * Creates the UI of the login form
     */
    public void initUI() {
        JPanel jpLogin = new JPanel();
        jpLogin.setLayout(new BorderLayout());
        jpLogin.setBackground(Color.WHITE);

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
        jpNorth.setBorder(BorderFactory.createEmptyBorder(70, 0, 80, 0));
        jpLogin.add(jpNorth, BorderLayout.NORTH);

        //We create a center panel for the form and the bottons
        jpCenter = new JPanel();
        jpCenter = new JPanel(new GridLayout(4, 1, 0, 15));
        jpCenter.setBackground(Color.WHITE);

        Font fuenteCampo = new Font("Segoe UI", Font.ITALIC, 20);
        jtField = new JTextField[2];
        jtField[0] = new JTextField(USER_LABEL);
        jtField[0].setBorder(null);
        jtField[0].setFont(fuenteCampo);
        jtField[0].setForeground(Color.GRAY);
        jtField[0].setBackground(color.getTEXTFIELD());
        jpCenter.add(jtField[0]);

        jtField[1] = new JTextField(PASSWORD_LABEL);
        jtField[1].setFont(fuenteCampo);
        jtField[1].setForeground(Color.GRAY);
        jtField[1].setBorder(null);
        jtField[1].setBackground(color.getTEXTFIELD());
        jpCenter.add(jtField[1]);

        //We create a filler row to separate the buttons and the login fields
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
        jbLogin.setBackground(color.getYELLOW());
        jbLogin.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jbRegister = new JButton(REGISTER);
        jbRegister.setFont(fuenteBotones);
        jbRegister.setForeground(Color.BLACK);
        jbRegister.setBorder(null);
        jbRegister.setBackground(color.getLightGrey());
        jbRegister.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jpBotones.add(jbLogin);
        jpBotones.add(jbRegister);
        jpCenter.add(jpBotones);
        jpCenter.setBorder(BorderFactory.createEmptyBorder(0, 300, 100, 300));

        jpLogin.add(jpCenter, BorderLayout.CENTER);
        this.getContentPane().add(jpLogin);
    }

    /**
     * Proc thats adds listener to textfields and buttons.
     *
     * @param listener an ActionListener
     */
    public void registerController(ActionListener listener) {
        jbLogin.addActionListener(listener);
        jbLogin.setActionCommand(LOGIN);
        jbRegister.addActionListener(listener);
        jbRegister.setActionCommand(REGISTER);
    }

    /**
     * Registers FocusListener for each JTextField of the view
     */
    public void registerFocusController() {
        this.jtField[0].addFocusListener(new LoginFocusController(this,0, USER_LABEL));
        this.jtField[1].addFocusListener(new LoginFocusController(this, 1, PASSWORD_LABEL));
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
     * Proc that shows the corresponding error
     *
     * @param error shows the corresponding error
     */
    public void showErrorMessage(int error) {
        switch (error) {
            case 1:
                JOptionPane.showMessageDialog(null, ERROR_MESSAGE_1);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, ERROR_MESSAGE_2);
                break;
        }
    }

    /**
     * Function that returns the introduced nickname or email
     *
     * @return introduced nickname or email
     */
    public String getNicknameEmail() {
        return jtField[0].getText();
    }

    /**
     * Function that returns the introduced password
     *
     * @return introduced password
     */
    public String getPassword() {
        return jtField[1].getText();
    }

    public void showLoginFailure(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void flushCredentials(){
        jtField[0].setText(USER_LABEL);
        jtField[1].setText(PASSWORD_LABEL);

    }
}
