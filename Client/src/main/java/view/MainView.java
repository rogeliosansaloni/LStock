package view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel{

    //logo de stock
    private static final String LINK_LOGO = "resources/stock.png";
    private JLabel logo_stock;

    public MainView () {
        this.setSize(1024, 768);
        this.setLayout(new BorderLayout());
        initUI();
    }

    private void initUI () {
        ImageIcon imagen_logo = new ImageIcon(getClass().getResource(LINK_LOGO));
        Image imagen = imagen_logo.getImage();
        int anchura_imagen = 400;
        int altura_foto = 400;
        Image imagen_temp = imagen.getScaledInstance(anchura_imagen, altura_foto, Image.SCALE_SMOOTH);
        imagen_logo = new ImageIcon(imagen_temp);
        logo_stock = new JLabel ("", imagen_logo, JLabel.CENTER);
        this.add(logo_stock, BorderLayout.NORTH);
    }
}
