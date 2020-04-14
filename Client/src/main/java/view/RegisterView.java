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
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        initUI();
    }

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
        //Añadimos la imagen y ponemos margen al panel para que quede más estético
        jpNorth.add(labelLogo, BorderLayout.CENTER);
        jpNorth.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
        jpRegister.add(jpNorth, BorderLayout.NORTH);

        //Creamos un panel para los campos y otro para los botones
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
        //Color colorTextField = new Color(242, 239, 236);
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
        //Ponemos un espacio de separacion entre los campos
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
        //Ponemos un espacio de separacion entre los campos
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
        //Ponemos un espacio de separacion entre los campos
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
        //Boton de login
        //Color colorLogin = new Color(232, 185, 108);
        jbLogin = new JButton("Login");
        jbLogin.setBorder(null);
        jbLogin.setBackground(color.getYELLOW());
        jbLogin.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));
        jpBotones.add(jbLogin);
        //Boton de register
        //Color colorRegister = new Color(160, 160, 160);
        jbRegister = new JButton("Register");
        jbRegister.setBorder(null);
        jbRegister.setBackground(color.getLightGrey());
        jbRegister.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));
        jpBotones.add(jbRegister);
        jpCenter.add(jpBotones, BorderLayout.SOUTH);
        jpCenter.setBorder(BorderFactory.createEmptyBorder(0,250,150,250));

        jpRegister.add(jpCenter, BorderLayout.CENTER);
        this.getContentPane().add(jpRegister);
    }

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

    public String getNickname () { return campos[0].toString();}
    public String getEmail () { return campos[1].toString();}
    public String getPassword () { return campos[2].toString();}
    public String getPasswordVerification () { return campos[3].toString();}

}
