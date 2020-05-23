package view;

import model.entities.Top10;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

/**
 * Creates the bar chart for the Top Ten Companies
 */
public class BarChartView extends JPanel {
    private ArrayList<Top10> topTen;
    public static final int AXIS_OFFSET = 30;
    public static final String COMPANIES = "COMPANIES";
    public static final String VALUE = "VALUE";
    private int chartwidth, chartheight, chartX, chartY;
    private String xLabel, yLabel;

    /**
     * Contructor for the BarChart
     *
     */
    public BarChartView () {}

    /**
     * Draws the components
     *
     * @param g Graphics2D component
     */
    public void paintComponent(Graphics g) {
        computeSize();
        Graphics2D g2 = (Graphics2D) g;
        drawBars(g2);
        drawAxes(g2);
    }

    /**
     * Fixes the measurements for the chart
     */
    private void computeSize() {
        int height = 350;

        // chart area size
        chartwidth = 1000;
        chartheight = height;

        // Chart origin coords
        chartX = AXIS_OFFSET;
        chartY = height;
    }

    /**
     * Creates the bars in the chart
     *
     * @param g2 Graphics2D component
     */
    public void drawBars(Graphics2D g2) {

        int numBars = topTen.size();
        float max = 1;
        //Find the maximum share value
        for (Top10 top : topTen){
            if (top.getPrice() > max){
                max = top.getPrice();
            }
        }

        int barWidth = (chartwidth/numBars);
        int value, height, xLeft, yTopLeft;
        int counter = 0;

        //Create a bar for each Company in the list
        for (Top10 barTopten : topTen) {
            value = (int) barTopten.getPrice();
            double height2 = (value/max)*chartheight-30;
            height = (int) height2;

            //Generate Bar
            xLeft = AXIS_OFFSET + counter * barWidth + barWidth/4;
            yTopLeft = chartY - height;
            Rectangle rec = new Rectangle(xLeft, yTopLeft, barWidth/2, height);
            g2.setColor(new Color(255, 102, 0));
            g2.fill(rec);

            //Add text tag for each Company
            g2.drawString(barTopten.getName(), xLeft - 2,yTopLeft - 15);
            g2.drawString(Integer.toString(value) + "€", xLeft + 10,yTopLeft-2);
            counter++;
        }
    }

    /**
     * Creates the axes for the chart
     *
     * @param g2 Graphics2D component
     */
    private void drawAxes(Graphics2D g2) {
        xLabel = COMPANIES;
        yLabel = VALUE;

        int rightX = chartX + chartwidth;
        int topY = chartY - chartheight;

        g2.drawLine(chartX, chartY, rightX, chartY);
        g2.drawLine(chartX, chartY, chartX, topY);
        g2.drawString(xLabel, chartX + chartwidth/2 - 60, chartY + AXIS_OFFSET/2 + 3) ;

        // draw vertical string
        Font original = g2.getFont();

        Font font = new Font(   null, original.getStyle(), original.getSize());
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(-90), 0, 0);
        Font rotatedFont = font.deriveFont(affineTransform);
        g2.setFont(rotatedFont);
        g2.drawString(yLabel,AXIS_OFFSET/2+3, chartY - chartheight/2);
        g2.setFont(original);

    }

    public void setTopTen(ArrayList<Top10> topTen) {
        this.topTen = topTen;
    }
}
