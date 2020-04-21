package utils;

import java.awt.*;

public class StockColors {
    private static final Color WHITE = new Color(225, 225, 225);
    private static final Color BLACK = new Color(0, 0, 0);
    private static final Color TEXTFIELD = new Color(248, 243, 243);
    private static final Color YELLOW = new Color(239, 198, 118);
    private static final Color GREEN = new Color(92, 221, 71);
    private static final Color RED = new Color(237, 76, 30);
    private static final Color BLUE = new Color(53, 132, 176);

    //Shades of Grey
    private static final Color DARK_GREY_HEADER = new Color(40, 38, 38);
    private static final Color DARK_GREY_TEXT = new Color(128, 126, 126);
    // Used for buttons and text
    private static final Color LIGHT_GREY = new Color(196, 196, 196);
    private static final Color LIGHT_LIGHT_GREY = new Color(229, 229, 229);

    public Color getWHITE() {
        return WHITE;
    }

    public Color getBLACK() {
        return BLACK;
    }

    public Color getTEXTFIELD() {
        return TEXTFIELD;
    }

    public Color getYELLOW() {
        return YELLOW;
    }

    public Color getGREEN() {
        return GREEN;
    }

    public Color getRED() {
        return RED;
    }

    public Color getBLUE() {
        return BLUE;
    }

    public Color getDarkGreyHeader() {
        return DARK_GREY_HEADER;
    }

    public Color getDarkGreyText() {
        return DARK_GREY_TEXT;
    }

    public Color getLightGrey() {
        return LIGHT_GREY;
    }

    public Color getLightLightGrey() {
        return LIGHT_LIGHT_GREY;
    }
}
