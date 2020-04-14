package view;

import utils.StockColors;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainView extends JPanel{

    //logo de stock
    private static final String PATH_LOGO = "/stock.png";
    private static final int anchuraPanel = 1080;
    private static final int alturaPanel = 768;
    private JLabel labelLogo;
    protected JPanel northPanel;
    protected JPanel jpCard;
    protected StockColors color;

    public MainView () {
        color = new StockColors();
        this.setPreferredSize(new Dimension(anchuraPanel, alturaPanel));
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        initUI();
    }

    public void initUI () {
        //Creamos el panel superior
        northPanel = new JPanel();
        northPanel.setBackground(Color.WHITE);
        //Creamos la imagen del logo
        URL url = getClass().getResource(PATH_LOGO);
        ImageIcon imageIcon = new ImageIcon(url);
        Image scaleImage = imageIcon.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(scaleImage);
        labelLogo = new JLabel(imageIcon);
        //Añadimos la imagen y ponemos margen al panel para que quede más estético
        northPanel.add(labelLogo, BorderLayout.CENTER);
        northPanel.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
        //Añadimos el panel superior al panel global
        this.add(northPanel, BorderLayout.NORTH);
        jpCard = new JPanel(new CardLayout());
        this.add(jpCard, BorderLayout.CENTER);


    }

}
