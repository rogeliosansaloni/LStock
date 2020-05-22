package view;

import model.entities.Company;
import model.entities.Share;
import model.entities.User;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

public class BarChartView extends Panel{
    private ArrayList<Share> shares;
    public static final int TOP_BUFFER = 30; // For the title
    public static final int AXIS_OFFSET = 20;
    public static final String COMPANIES = "Companies";
    public static final String VALUE = "Value";
    private int chartwidth, chartheight, chartX, chartY;
    private String xLabel, yLabel;

    public BarChartView (ArrayList<Share> shares)
    {
       // this.shares = shares;
        this.shares = new ArrayList<Share>();
        User user = new User (1, "mary","email","aloha",20,"cute",50,null);
        Company com1= new Company(1, "comp1", 20,20, user, null);
        this.shares.add(new Share(user,com1,10));
        User user2 = new User (1, "lol","lol1","aloha",20,"cute",50,null);
        Company com2= new Company(1, "comp1", 10,30, user, null);
        this.shares.add(new Share(user2,com2,10));

    }


    public void paintComponent(Graphics g) {
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

        //numero de companias que tendra del user
        //double numBars = counts.keySet().size();
        int numBars = 4;
        double max = 0.;
        int barWidth = (int) (chartwidth/numBars);

        int value, height, xLeft, yTopLeft;
        int counter = 0;
        for (Share share : shares) {
            value = (int) share.getPrice();

            double height2 = (value/max)*chartheight;
            height = (int) height2;

            xLeft = AXIS_OFFSET + counter * barWidth;
            yTopLeft = chartY - height;
            Rectangle rec = new Rectangle(xLeft, yTopLeft, barWidth, height);

            g2.setColor(getRandomColor());
            g2.fill(rec);

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

        int size = shares.size();
        g2.drawString("Number of classes: " + size, AXIS_OFFSET +10, 15) ;
        g2.drawString("Number of counts: " + shares.size(), AXIS_OFFSET +10, 30) ;
    }

    private Color getRandomColor() {
        Random rand = new Random();

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        return new Color(r, g, b);
    }
}
