package view;


import model.entities.Top10;
import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * View for Top Ten Company chart
 */
public class TopTenCompaniesView extends JPanel {
    private static final String FONT = "Segoe UI";
    private static final String FONT_BUTTON = "Segoe UI Semibold";
    private static final String TITLE = "Top 10 Companies";
    private static final String SHARE = "SHARE";
    private JButton jbShare;
    private BarChartView barchart;

    /**
     * Constructor for the TopTenCompaniesView
     */
    public TopTenCompaniesView(){
        StockColors color = new StockColors();
        Font font = new Font(FONT, Font.ITALIC, 20);
        this.setLayout(new GridLayout(1,1,0,0));
        this.setBackground(color.getWHITE());

        JPanel jpGeneral = new JPanel();
        jpGeneral.setLayout(new BoxLayout(jpGeneral,BoxLayout.Y_AXIS));
        this.setBackground(color.getWHITE());

        JLabel jlTitle = new JLabel(TITLE);
        jlTitle.setPreferredSize(new Dimension(200, 30));
        jlTitle.setFont(font);
        JPanel jpTitle = new JPanel(new FlowLayout());
        jpTitle.setBackground(color.getWHITE());

        JPanel jpButtonAux = new JPanel( new FlowLayout());
        jpButtonAux.setBackground(color.getWHITE());
        Font buttonFont = new Font(FONT_BUTTON, Font.PLAIN, 20);
        jbShare = new JButton(SHARE);
        jbShare.setFont(buttonFont);
        jbShare.setForeground(color.getBLACK());
        jbShare.setBorder(null);
        jbShare.setBackground(color.getYELLOW());
        jbShare.setPreferredSize(new Dimension(200, 50));
        jpButtonAux.add(jbShare);

        JPanel bar = new JPanel(new FlowLayout());
        bar.setBackground(color.getWHITE());
        barchart = new BarChartView();
        barchart.setBorder(BorderFactory.createEmptyBorder(180,500,180,500));
        barchart.setBackground(color.getWHITE());

        jpTitle.add(jlTitle);
        jpGeneral.add(jpTitle);
        bar.add(barchart);
        jpGeneral.add(bar);
        jpGeneral.add(jpButtonAux);

        this.add(jpGeneral);
    }

    public void showTopTen(ArrayList<Top10> topTenCompanies) {
        barchart.setTopTen(topTenCompanies);
    }
}
