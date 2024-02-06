package main.Ressources.Java;

import javafx.scene.paint.Color;

public class ThemeManager {

    private static String currentTheme = "Classic";

    public static String getCurrentTheme() {
        return currentTheme;
    }

    public static void setCurrentTheme(String currentTheme) {
        ThemeManager.currentTheme = currentTheme;
    }

    public static Color getSquareColor(int i, int j) {
        Color color1 = Color.web("#ffffff00");
        Color color2 = Color.web("#ffffff00");

        switch (currentTheme) {
            case "Classic" -> {
                color1 = Color.web("#f0d9b5");
                color2 = Color.web("#b58863");
                break;
            }
            case "Coral" -> {
                color1 = Color.web("#b1e4b9");
                color2 = Color.web("#70a2a3");
                break;
            }
            case "Dusk" -> {
                color1 = Color.web("#cbb7ae");
                color2 = Color.web("#716677");
                break;
            }
            case "Wheat" -> {
                color1 = Color.web("#eaefce");
                color2 = Color.web("#bbbe65");
                break;
            }
            case "Marine" -> {
                color1 = Color.web("#9dacff");
                color2 = Color.web("#6f74d2");
                break;
            }
            case "Emerald" -> {
                color1 = Color.web("#adbd90");
                color2 = Color.web("#6e8f72");
                break;
            }
            case "Sandcastle" -> {
                color1 = Color.web("#e4c16f");
                color2 = Color.web("#b88b4a");
                break;
            }
            case "Black and White" -> {
                color1 = Color.web("#ffffff00");
                color2 = Color.web("#00000000");
                break;
            }
        }
        return ((i + j) % 2 == 0) ? color1 : color2;
    }
}
