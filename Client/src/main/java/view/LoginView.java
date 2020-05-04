package view;

import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;

public class LoginView extends JFrame {
    private static final String PATH_LOGO = "/Images/stock.png";
    private static final String TITLE = "StockLS - C2";
    private static final String ERROR_MESSAGE_1 = "All fields are required.";
    private static final String ERROR_MESSAGE_2 = "Fields can't be empty.";
    private static final int anchuraPanel = 1080;
    private static final int alturaPanel = 740;
    private JLabel labelLogo;
    private JLabel labelStock;
    protected JPanel jpNorth;
    private JPanel jpCenter;
    private JPanel jpCampos;
    private JPanel jpBotones;
    private JTextField[] campos;
    private JButton jbLogin;
    private JButton jbRegister;
    private StockColors color;

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
        campos = new JTextField[2];
        campos[0] = new JTextField("Nickname or Email");
        campos[0].setBorder(null);
        campos[0].setFont(fuenteCampo);
        campos[0].setForeground(Color.GRAY);
        campos[0].setBackground(color.getTEXTFIELD());
        campos[0].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campos[0].getText().equals("Nickname or Email")) {
                    campos[0].setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campos[0].getText().equals("")) {
                    campos[0].setText("Nickname or Email");
                }
            }
        });
        //We add separation space between fields
        jpCenter.add(campos[0]);
        campos[1] = new JTextField("Password");
        campos[1].setFont(fuenteCampo);
        campos[1].setForeground(Color.GRAY);
        campos[1].setBorder(null);
        campos[1].setBackground(color.getTEXTFIELD());
        campos[1].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campos[1].getText().equals("Password")) {
                    campos[1].setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campos[1].getText().equals("")) {
                    campos[1].setText("Password");
                }
            }
        });
        jpCenter.add(campos[1]);

        //We create a filler row to separate the buttons and the login fields
        JLabel relleno = new JLabel();
        relleno.setPreferredSize(new Dimension(200, 1));
        jpCenter.add(relleno);

        jpBotones = new JPanel(new GridLayout(1, 2, 30, 0));
        jpBotones.setBackground(Color.WHITE);
        int anchuraBoton = 200;
        int alturaBoton = 40;

        Font fuenteBotones = new Font("Segoe UI Semibold", Font.PLAIN, 20);
        jbLogin = new JButton("Login");
        jbLogin.setFont(fuenteBotones);
        jbLogin.setForeground(Color.BLACK);
        jbLogin.setBorder(null);
        jbLogin.setBackground(color.getYELLOW());
        jbLogin.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jbRegister = new JButton("Register");
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
        campos[0].addActionListener(listener);
        campos[0].setActionCommand("user");
        campos[1].addActionListener(listener);
        campos[1].setActionCommand("password");
        jbLogin.addActionListener(listener);
        jbLogin.setActionCommand("login");
        jbRegister.addActionListener(listener);
        jbRegister.setActionCommand("register");
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
        return campos[0].getText();
    }

    /**
     * Function that returns the introduced password
     *
     * @return introduced password
     */
    public String getPassword() {
        return campos[1].getText();
    }

    public void showLoginFailure(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void flushCredentials(){
        campos[0].setText("Nickname or Email");
        campos[1].setText("Password");

    }

}
