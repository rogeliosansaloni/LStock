package view;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends MainView{

    private JPanel jpCenter;
    private JPanel jpCampos;
    private JPanel jpBotones;
    private JTextField[] campos;
    private JButton jbLogin;
    private JButton jbRegister;


    public RegisterView () {
        super();
    }

    public void initUI () {
        super.initUI();
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
        campos = new JTextField[2];
        //Color colorTextField = new Color(242, 239, 236);
        campos[0] = new JTextField("Nickname or Email");
        campos[0].setPreferredSize(new Dimension(anchuraCampo,alturaCampo));
        campos[0].setBorder(null);
        campos[0].setBackground(color.getTEXTFIELD());
        jpCampos.add(campos[0]);
        //Ponemos un espacio de separacion entre los campos
        jpCampos.add(Box.createRigidArea(new Dimension(anchuraCampo, 20)));
        campos[1] = new JTextField("Password");
        campos[1].setPreferredSize(new Dimension(anchuraCampo,alturaCampo));
        campos[1].setBorder(null);
        campos[1].setBackground(color.getTEXTFIELD());
        jpCampos.add(campos[1]);
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
        jpCard.add(jpCenter);
    }

    public static void main( String[] args )
    {
        JFrame frame = new JFrame();
        RegisterView registerView = new RegisterView();
        frame.getContentPane().add(registerView);
        frame.setSize(1024, 768);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
