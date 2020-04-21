package view;

import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;

public class RegisterView extends JFrame{

    private static final String PATH_LOGO = "/stock.png";
    private static final String TITLE = "StockLS - C2";
    private static final String ERROR_MESSAGE_1 = "All fields are required.";
    private static final String ERROR_MESSAGE_2 = "Fields can't be empty.";
    private static final String ERROR_MESSAGE_3 = "Email is not valid.";
    private static final String ERROR_MESSAGE_4 = "Password doesn't match.";
    private static final String ERROR_MESSAGE_5 = "Password must have at least 8 characters.";
    private static final String ERROR_MESSAGE_6 = "Password must have at least one capital letter and one number";

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

    public RegisterView () {
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
    public void initUI () {
        JPanel jpRegister = new JPanel();
        jpRegister.setLayout(new BorderLayout());
        jpRegister.setBackground(Color.WHITE);

        //Header
        jpNorth = new JPanel(new BorderLayout());
        jpNorth.setBackground(Color.WHITE);
        URL url = getClass().getResource(PATH_LOGO);
        ImageIcon imageIcon = new ImageIcon(url);
        Image scaleImage = imageIcon.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(scaleImage);
        labelLogo = new JLabel(imageIcon);

        //TODO: Add label under the logo
        jpNorth.add(labelLogo, BorderLayout.CENTER);
        labelStock = new JLabel("StockLS", SwingConstants.CENTER);
        Font fuenteLogo = new Font("Segoe UI", Font.PLAIN, 50);
        labelStock.setFont(fuenteLogo);
        jpNorth.add(labelStock, BorderLayout.SOUTH);
        jpNorth.setBorder(BorderFactory.createEmptyBorder(40,0,35,0));
        jpRegister.add(jpNorth, BorderLayout.NORTH);

        //We create a center panel for the form and the bottons
        jpCenter = new JPanel();
        jpCenter = new JPanel(new GridLayout(6,1, 0 ,15));
        jpCenter.setBackground(Color.WHITE);

        Font fuenteCampo = new Font("Segoe UI", Font.ITALIC, 20);
        campos = new JTextField[4];
        campos[0] = new JTextField("Nickname");
        campos[0].setBorder(null);
        campos[0].setFont(fuenteCampo);
        campos[0].setForeground(Color.GRAY);
        campos[0].setBackground(color.getTEXTFIELD());
        campos[0].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campos[0].getText().equals("Nickname")) {
                    campos[0].setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campos[0].getText().equals("")) {
                    campos[0].setText("Nickname");
                }
            }
        });
        jpCenter.add(campos[0]);
        //We add separation space between fields
        campos[1] = new JTextField("Email");
        campos[1].setBorder(null);
        campos[1].setFont(fuenteCampo);
        campos[1].setForeground(Color.GRAY);
        campos[1].setBackground(color.getTEXTFIELD());
        campos[1].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campos[1].getText().equals("Email")) {
                    campos[1].setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (campos[1].getText().equals("")) {
                    campos[1].setText("Email");
                }
            }
        });
        jpCenter.add(campos[1]);
        //We add separation space between fields
        campos[2] = new JTextField("Password");
        campos[2].setBorder(null);
        campos[2].setFont(fuenteCampo);
        campos[2].setForeground(Color.GRAY);
        campos[2].setBackground(color.getTEXTFIELD());
        campos[2].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campos[2].getText().equals("Password")) {
                    campos[2].setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (campos[2].getText().equals("")) {
                    campos[2].setText("Password");
                }
            }
        });
        jpCenter.add(campos[2]);
        //We add separation space between fields
        campos[3] = new JTextField("Verify Password");
        campos[3].setBorder(null);
        campos[3].setFont(fuenteCampo);
        campos[3].setForeground(Color.GRAY);
        campos[3].setBackground(color.getTEXTFIELD());
        campos[3].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campos[3].getText().equals("Verify Password")) {
                    campos[3].setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (campos[3].getText().equals("")) {
                    campos[3].setText("Verify Password");
                }
            }
        });
        jpCenter.add(campos[3]);

        //We create a filler row to separate the buttons and the register fields
        JLabel relleno = new JLabel();
        relleno.setPreferredSize(new Dimension(200, 1));
        jpCenter.add(relleno);

        jpBotones = new JPanel(new GridLayout(1,2, 30 ,0));
        jpBotones.setBackground(Color.WHITE);
        int anchuraBoton = 200;
        int alturaBoton = 40;

        Font fuenteBotones = new Font("Segoe UI Semibold", Font.PLAIN, 20);
        jbLogin = new JButton("Login");
        jbLogin.setFont(fuenteBotones);
        jbLogin.setForeground(Color.BLACK);
        jbLogin.setBorder(null);
        jbLogin.setBackground(color.getLightGrey());
        jbLogin.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jbRegister = new JButton("Register");
        jbRegister.setFont(fuenteBotones);
        jbRegister.setForeground(Color.BLACK);
        jbRegister.setBorder(null);
        jbRegister.setBackground(color.getYELLOW());
        jbRegister.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jpBotones.add(jbRegister);
        jpBotones.add(jbLogin);
        jpCenter.add(jpBotones);
        jpCenter.setBorder(BorderFactory.createEmptyBorder(0,250,50,250));

        jpRegister.add(jpCenter, BorderLayout.CENTER);
        this.getContentPane().add(jpRegister);
    }

    /**
     * Proc that adds an actionListener to fields and buttons.
     * @param actionListener the actionListener
     */
    public void registerController (ActionListener actionListener) {
        campos[0].addActionListener(actionListener);
        campos[0].setActionCommand("nickname");
        campos[1].addActionListener(actionListener);
        campos[1].setActionCommand("email");
        campos[2].addActionListener(actionListener);
        campos[2].setActionCommand("pass1");
        campos[3].addActionListener(actionListener);
        campos[3].setActionCommand("pass2");
        jbLogin.addActionListener(actionListener);
        jbLogin.setActionCommand("login");
        jbRegister.addActionListener(actionListener);
        jbRegister.setActionCommand("register");
    }

    /**
     * Proc that shows a pop-up window with an error message
     * @param error message to be shown
     */
    public void showErrorMessages (int error) {
        switch (error) {
            case 1:
                JOptionPane.showMessageDialog(null,ERROR_MESSAGE_1);
                break;
            case 2:
                JOptionPane.showMessageDialog(null,ERROR_MESSAGE_2);
                break;
            case 3:
                JOptionPane.showMessageDialog(null,ERROR_MESSAGE_3);
                break;
            case 4:
                JOptionPane.showMessageDialog(null,ERROR_MESSAGE_4);
                break;
            case 5:
                JOptionPane.showMessageDialog(null,ERROR_MESSAGE_5);
                break;
            case 6:
                JOptionPane.showMessageDialog(null,ERROR_MESSAGE_6);
                break;
        }
    }

    /**
     * Function that returns the nickname typed in the nickname field
     * @return Nickname
     */
    public String getNickname () { return campos[0].getText();}

    /**
     * Function that returns the email typed in the email field
     * @return Email
     */
    public String getEmail () { return campos[1].getText();}

    /**
     * Function that returns the password typed in the password field
     * @return Password
     */
    public String getPassword () { return campos[2].getText();}

    /**
     * Function that returns the password typed in the verify password field
     * @return Repeated password
     */
    public String getPasswordVerification () { return campos[3].getText();}

}
