package view;


import model.entities.Top10;
import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TopTenCompaniesView extends JPanel {
    private static final String FONT = "Segoe UI";
    private static final String FONT_BUTTON = "Segoe UI Semibold";
    private static final String TITLE = "Top 10 Companies";
    private static final String SHARE = "SHARE";
    private static final String USER = "User: ";
    private static final int WIDTH = 200;
    private static final int HEIGHT = 20;
    private JLabel jlUser;
    private JButton jbShare;
    private ArrayList<Top10> topTen;
    private BarChartView barchart;

    public TopTenCompaniesView(){
        StockColors color = new StockColors();
        Font font = new Font(FONT, Font.ITALIC, 20);

        this.setLayout(new GridLayout(3,1,0,0));
        this.setBackground(color.getWHITE());

        JLabel jlTitle = new JLabel(TITLE);
        jlTitle.setPreferredSize(new Dimension(200, 1));
        jlTitle.setHorizontalAlignment(JLabel.CENTER);
        jlTitle.setFont(font);

        JPanel jpBarchart = new JPanel(new FlowLayout());


        JPanel jpButtom = new JPanel(new GridLayout(2,1,0,0));
        jpButtom.setBackground(color.getWHITE());

        JPanel jpButtonAux = new JPanel( new FlowLayout());
        jpButtonAux.setBackground(color.getWHITE());
        Font buttonFont = new Font(FONT_BUTTON, Font.PLAIN, 20);
        jbShare = new JButton(SHARE);
        jbShare.setFont(buttonFont);
        jbShare.setForeground(color.getBLACK());
        jbShare.setBorder(null);
        jbShare.setBackground(color.getYELLOW());
        jbShare.setPreferredSize(new Dimension(200, 50));

        barchart = new BarChartView(topTen);
        this.add(barchart);



        jlUser = new JLabel(USER+ "Peter");
        jlUser.setHorizontalAlignment(JLabel.CENTER);
        jlUser.setPreferredSize(new Dimension(500, 500));
        jlUser.setFont(font);

        jpButtom.add(jlUser);
        jpButtonAux.add(jbShare);
        jpButtom.add(jpButtonAux);

        this.add(jlTitle);
        this.add(jpButtom);

    }


}
