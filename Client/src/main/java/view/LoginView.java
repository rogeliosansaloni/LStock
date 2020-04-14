package view;

import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;

public class LoginView extends JFrame {
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

    public LoginView () {
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
     * Creates the UI of the login form
     */
    public void initUI () {
        JPanel jpLogin = new JPanel();
        jpLogin.setLayout(new BorderLayout());
        jpLogin.setBackground(Color.WHITE);

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
        jpNorth.setBorder(BorderFactory.createEmptyBorder(50,0,100,0));
        jpLogin.add(jpNorth, BorderLayout.NORTH);

        //We create a center panel for the form and the bottons
        jpCenter = new JPanel();
        jpCenter = new JPanel(new BorderLayout());
        jpCenter.setBackground(Color.WHITE);
        jpCampos = new JPanel();

        jpCampos.setLayout(new BoxLayout(jpCampos, BoxLayout.PAGE_AXIS));
        jpCampos.setBackground(Color.WHITE);
        jpCampos.setBorder(BorderFactory.createEmptyBorder(50,0,60,0));
        int anchuraCampo = 150;
        int alturaCampo = 20;

        campos = new JTextField[2];
        campos[0] = new JTextField("Nickname or Email");
        campos[0].setPreferredSize(new Dimension(anchuraCampo,alturaCampo));
        campos[0].setBorder(null);
        campos[0].setMargin(new Insets(0,70,0,0));
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
        jpCampos.add(campos[0]);
        //We add separation space between fields
        jpCampos.add(Box.createRigidArea(new Dimension(anchuraCampo, 20)));
        campos[1] = new JTextField("Password");
        campos[1].setPreferredSize(new Dimension(anchuraCampo,alturaCampo));
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
        jpCampos.add(campos[1]);

        jpCenter.add(jpCampos, BorderLayout.CENTER);

        jpBotones = new JPanel(new GridLayout(1,2, 30 ,0));
        jpBotones.setBackground(Color.WHITE);
        int anchuraBoton = 200;
        int alturaBoton = 40;

        jbLogin = new JButton("Login");
        jbLogin.setBorder(null);
        jbLogin.setBackground(color.getYELLOW());
        jbLogin.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jbRegister = new JButton("Register");
        jbRegister.setBorder(null);
        jbRegister.setBackground(color.getLightGrey());
        jbRegister.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));

        jpBotones.add(jbLogin);
        jpBotones.add(jbRegister);

        jpCenter.add(jpBotones, BorderLayout.SOUTH);
        jpCenter.setBorder(BorderFactory.createEmptyBorder(0,250,150,250));

        jpLogin.add(jpCenter, BorderLayout.CENTER);
        this.getContentPane().add(jpLogin);
    }
}
