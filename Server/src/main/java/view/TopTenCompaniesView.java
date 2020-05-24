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
    private static final String TITLE = "Top 10 Companies";
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

        JPanel bar = new JPanel(new FlowLayout());
        bar.setBackground(color.getWHITE());
        barchart = new BarChartView();
        barchart.setBorder(BorderFactory.createEmptyBorder(180,500,180,500));
        barchart.setBackground(color.getWHITE());

        jpTitle.add(jlTitle);
        jpGeneral.add(jpTitle);
        bar.add(barchart);
        jpGeneral.add(bar);

        this.add(jpGeneral);
    }

    /**
     * Updates de Top 10 Company list and repaints it
     *
     * @param topTenCompanies updated Top 10 Company List
     */
    public void showTopTen(ArrayList<Top10> topTenCompanies) {
        barchart.setTopTen(topTenCompanies);
        this.repaint();
    }
}
