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
    private static final int anchuraPanel = 1080;
    private static final int alturaPanel = 768;
    private JLabel labelLogo;
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
        this.setSize(1024, 768);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
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
        jpNorth = new JPanel();
        jpNorth.setBackground(Color.WHITE);
        URL url = getClass().getResource(PATH_LOGO);
        ImageIcon imageIcon = new ImageIcon(url);
        Image scaleImage = imageIcon.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(scaleImage);
        labelLogo = new JLabel(imageIcon);
        //TODO: Add label under the logo
        jpNorth.add(labelLogo, BorderLayout.CENTER);
        jpNorth.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
        jpRegister.add(jpNorth, BorderLayout.NORTH);

        //We create a center panel for the form and the bottons
        jpCenter = new JPanel();
        jpCenter = new JPanel(new BorderLayout());
        jpCenter.setBackground(Color.WHITE);
        jpCampos = new JPanel();
        //El layout de los campos será BoxLayout
        jpCampos.setLayout(new BoxLayout(jpCampos, BoxLayout.PAGE_AXIS));
        jpCampos.setBackground(Color.WHITE);
        jpCampos.setBorder(BorderFactory.createEmptyBorder(50,0,60,0));
        int anchuraCampo = 150;
        int alturaCampo = 20;

        campos = new JTextField[4];
        campos[0] = new JTextField("Nickname");
        campos[0].setPreferredSize(new Dimension(anchuraCampo,alturaCampo));
        campos[0].setBorder(null);
        campos[0].setMargin(new Insets(0,70,0,0));
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
        jpCampos.add(campos[0]);
        //We add separation space between fields
        jpCampos.add(Box.createRigidArea(new Dimension(anchuraCampo, 20)));
        campos[1] = new JTextField("Email");
        campos[1].setPreferredSize(new Dimension(anchuraCampo,alturaCampo));
        campos[1].setBorder(null);
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
        jpCampos.add(campos[1]);
        //We add separation space between fields
        jpCampos.add(Box.createRigidArea(new Dimension(anchuraCampo, 20)));
        campos[2] = new JTextField("Password");
        campos[2].setPreferredSize(new Dimension(anchuraCampo,alturaCampo));
        campos[2].setBorder(null);
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
        jpCampos.add(campos[2]);
        //We add separation space between fields
        jpCampos.add(Box.createRigidArea(new Dimension(anchuraCampo, 20)));
        campos[3] = new JTextField("Verify Password");
        campos[3].setPreferredSize(new Dimension(anchuraCampo,alturaCampo));
        campos[3].setBorder(null);
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
        jpCampos.add(campos[3]);

        jpCenter.add(jpCampos, BorderLayout.CENTER);

        jpBotones = new JPanel(new GridLayout(1,2, 30 ,0));
        jpBotones.setBackground(Color.WHITE);
        int anchuraBoton = 200;
        int alturaBoton = 40;

        jbLogin = new JButton("Login");
        jbLogin.setBorder(null);
        jbLogin.setBackground(color.getLightGrey());
        jbLogin.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jbRegister = new JButton("Register");
        jbRegister.setBorder(null);
        jbRegister.setBackground(color.getYELLOW());
        jbRegister.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jpBotones.add(jbRegister);
        jpBotones.add(jbLogin);

        jpCenter.add(jpBotones, BorderLayout.SOUTH);
        jpCenter.setBorder(BorderFactory.createEmptyBorder(0,250,150,250));

        jpRegister.add(jpCenter, BorderLayout.CENTER);
        this.getContentPane().add(jpRegister);
    }

    /**
     * Proc that adds an actionListener to fields and buttons.
     * @param actionListener
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
    public void showErrorMessages (String error) {
        JOptionPane.showMessageDialog(null,error);
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
