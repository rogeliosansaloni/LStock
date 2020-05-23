package view;

import model.entities.Top10;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

public class BarChartView extends JPanel {
    private ArrayList<Top10> topTen;
    public static final int TOP_BUFFER = 30; // For the title
    public static final int AXIS_OFFSET = 50;
    public static final String COMPANIES = "Top 10 Companies";
    public static final String VALUE = "Value";
    private int chartwidth, chartheight, chartX, chartY;
    private String xLabel, yLabel;

    public BarChartView (ArrayList<Top10> topTen) {
       this.topTen = topTen;
    }

    public void paintComponent(Graphics g) {
        computeSize();
        Graphics2D g2 = (Graphics2D) g;
        drawBars(g2);
        drawAxes(g2);
//        drawText(g2);
    }

    private void computeSize() {
        int width = this.getWidth();
        int height = this.getHeight();

        System.out.println(height);

        // chart area size
        chartwidth = width - 2*AXIS_OFFSET;
//        chartheight = height - 2*AXIS_OFFSET - TOP_BUFFER;
        chartheight = height;
//        System.out.println(chartheight + " (chartheight) = "+height+" (height) - "+2*AXIS_OFFSET+" (2*AXIS_OFFSET) - "+TOP_BUFFER+" (TOP_BUFFER)");

        // Chart origin coords
        chartX = AXIS_OFFSET;
        chartY = height - AXIS_OFFSET;

    }

    public void drawBars(Graphics2D g2) {

        Color original = g2.getColor();

        //numero de companias que tendra del user
        //double numBars = counts.keySet().size();
        int numBars = topTen.size();
        float max = 1;
        for (Top10 top : topTen){
            System.out.println("BarChartView TOP TEN---- Company: "+top.getName()+" ("+top.getPrice()+"€/share)");
            if (top.getPrice() > max){
                max = top.getPrice();
            }
        }

        int barWidth = (int) (chartwidth/numBars);
        int value, height, xLeft, yTopLeft;
        int counter = 0;

        for (Top10 barTopten : topTen) {
            value = (int) barTopten.getPrice();
            double height2 = (value/max)*chartheight;
            height = (int) height2;

            xLeft = AXIS_OFFSET + counter * barWidth + barWidth/4;
            yTopLeft = chartY - height;
            System.out.println(barWidth);
            Rectangle rec = new Rectangle(xLeft, yTopLeft, barWidth/2, height);

            g2.setColor(getRandomColor());
            g2.fill(rec);
            counter++;
        }
        g2.setColor(original);
    }

    private void drawAxes(Graphics2D g2) {
        xLabel = COMPANIES;
        yLabel = VALUE;

        int rightX = chartX + chartwidth;
        System.out.println(rightX + " (rightX) = "+chartX+" (chartX) + "+chartwidth+" (chartwidth)");
        int topY = chartY - chartheight;
        System.out.println(topY + " (topY) = "+chartY+" (chartY) - "+chartheight+" (chartheight)");

        g2.drawLine(chartX, chartY, rightX, chartY);
        g2.drawLine(chartX, chartY, chartX, topY);
        g2.drawString(xLabel, chartX + chartwidth/2, chartY + AXIS_OFFSET/2 +3) ;

        // draw vertical string
        Font original = g2.getFont();

        Font font = new Font(null, original.getStyle(), original.getSize());
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(-90), 0, 0);
        Font rotatedFont = font.deriveFont(affineTransform);
        g2.setFont(rotatedFont);
        g2.drawString(yLabel,AXIS_OFFSET/2+3, chartY - chartheight/2);
        g2.setFont(original);


    }

    private void drawText(Graphics2D g2) {

        int size = topTen.size();
        g2.drawString("Number of classes: " + size, AXIS_OFFSET +10, 15);
        g2.drawString("Number of counts: " + topTen.size(), AXIS_OFFSET +10, 30);
    }

    private Color getRandomColor() {
        Random rand = new Random();

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        return new Color(r, g, b);
    }
}
