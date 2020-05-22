package view;

import model.entities.Share;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

public class BarChartView extends Panel{
    private ArrayList<Share> shares;
    public static final int TOP_BUFFER = 30; // where additional text is drawn
    public static final int AXIS_OFFSET = 20;
    public static final String COMPANIES = "Companies";
    public static final String VALUE = "Value";
    private int chartwidth, chartheight, chartX, chartY;
    private String xLabel, yLabel;
    //private ArrayList<Integer> list;
    //private Map<Integer, Integer> counts = new HashMap<>();

    public BarChartView (ArrayList<Share> companyArrayList){
        this.shares = companyArrayList;
    }


    private void setupCounts() {
        counts.clear();

        for (int i : shares) {
            if (counts.containsKey(i)) {
                counts.put(i, counts.get(i) + 1);
            } else {
                counts.put(i, 1);
            }
        }
    }

    public void paintComponent(Graphics g) {
        setupCounts();
        computeSize();

        Graphics2D g2 = (Graphics2D) g;
        drawBars(g2);
        drawAxes(g2);
        drawText(g2);
    }

    private void computeSize() {

        int width = this.getWidth();
        int height = this.getHeight();

        // chart area size
        chartwidth = width - 2*AXIS_OFFSET;
        chartheight = height - 2*AXIS_OFFSET - TOP_BUFFER;

        // Chart origin coords
        chartX = AXIS_OFFSET;
        chartY = height - AXIS_OFFSET;

    }

    public void drawBars(Graphics2D g2) {

        Color original = g2.getColor();

        double numBars = counts.keySet().size();
        double max = 0.;

        for (Integer wrapper : counts.values()) {
            if (max < wrapper)
                max = wrapper;
        }
        System.out.println("max "+max);
        int barWidth = (int) (chartwidth/numBars);

        int value, height, xLeft, yTopLeft;
        int counter = 0;
        for (Integer bar : counts.keySet()) {
            value = counts.get(bar);

            double height2 = (value/max)*chartheight;
            height = (int) height2;

            xLeft = AXIS_OFFSET + counter * barWidth;
            yTopLeft = chartY - height;
            Rectangle rec = new Rectangle(xLeft, yTopLeft, barWidth, height);

            g2.setColor(getRandomColor());
            //g2.draw(rec);
            g2.fill(rec);

            counter++;
        }

        g2.setColor(original);
    }

    private void drawAxes(Graphics2D g2) {

        xLabel = COMPANIES;
        yLabel = VALUE;

        int rightX = chartX + chartwidth;
        int topY = chartY - chartheight;

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

        int size = counts.keySet().size();

        g2.drawString("Number of classes: " + size, AXIS_OFFSET +10, 15) ;

        g2.drawString("Number of counts: " + shares.size(), AXIS_OFFSET +10, 30) ;
    }

    private Color getRandomColor() {
        // see https://stackoverflow.com/questions/4246351/creating-random-colour-in-java
        Random rand = new Random();

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        return new Color(r, g, b);
    }
}
