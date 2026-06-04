package utils;

import java.awt.*;

public class Utils {

    public static String COLOR = "DARK";

    // constantes utilisées
    public static Color COLOR_BACKGROUND_DARK = new Color(18, 22, 28);
    public static Color COLOR_SIDEBAR_BACKGROUND = new Color(25, 30, 38);
    public static Color COLOR_TEXT_LIGHT = new Color(230, 230, 230);
    public static Color COLOR_TEXT_DIM = new Color(150, 150, 150);
    public static Color COLOR_ACCENT_GREEN = new Color(50, 205, 50);
    public static Color COLOR_CARD_BACKGROUND = new Color(30, 35, 43);

    // couleurs dark
    public static Color COLOR_BACKGROUND_DARK_D = new Color(18, 22, 28);
    public static Color COLOR_SIDEBAR_BACKGROUND_D = new Color(25, 30, 38);
    public static Color COLOR_TEXT_LIGHT_D = new Color(230, 230, 230);
    public static Color COLOR_TEXT_DIM_D = new Color(150, 150, 150);
    public static Color COLOR_ACCENT_GREEN_D = new Color(50, 205, 50);
    public static Color COLOR_CARD_BACKGROUND_D = new Color(30, 35, 43);

    // couleurs claires
    public static Color COLOR_BACKGROUND_LIGHT = new Color(245, 247, 250);
    public static Color COLOR_SIDEBAR_BACKGROUND_LIGHT = new Color(255, 255, 255);
    public static Color COLOR_TEXT_DARK = new Color(30, 35, 43);
    public static Color COLOR_TEXT_DIM_LIGHT = new Color(110, 115, 125);
    public static Color COLOR_ACCENT_GREEN_LIGHT = new Color(40, 180, 40);
    public static Color COLOR_CARD_BACKGROUND_LIGHT = new Color(255, 255, 255);

    public static void changeTheme(){
        if (COLOR == "DARK") {
            COLOR = "LIGHT";
                COLOR_BACKGROUND_DARK = COLOR_BACKGROUND_LIGHT;
                COLOR_SIDEBAR_BACKGROUND = COLOR_SIDEBAR_BACKGROUND_LIGHT;
                COLOR_TEXT_DARK = COLOR_TEXT_LIGHT;
                COLOR_TEXT_DIM = COLOR_TEXT_DIM_LIGHT;
                COLOR_ACCENT_GREEN = COLOR_ACCENT_GREEN_LIGHT;
                COLOR_CARD_BACKGROUND = COLOR_CARD_BACKGROUND_LIGHT;
        }
        if (COLOR == "LIGHT"){
            COLOR = "DARK";
            COLOR_BACKGROUND_DARK = COLOR_BACKGROUND_DARK_D;
            COLOR_SIDEBAR_BACKGROUND = COLOR_SIDEBAR_BACKGROUND_D;
            COLOR_TEXT_DARK = COLOR_TEXT_LIGHT_D;
            COLOR_TEXT_DIM = COLOR_TEXT_DIM_D;
            COLOR_ACCENT_GREEN = COLOR_ACCENT_GREEN_D;
            COLOR_CARD_BACKGROUND = COLOR_CARD_BACKGROUND_D;
        }
    }

}
