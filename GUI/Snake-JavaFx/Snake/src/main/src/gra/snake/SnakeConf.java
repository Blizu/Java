package gra.snake;

import javafx.scene.shape.Rectangle;

public class SnakeConf {

    private static final int rozmiarKratki = 20;
    private static int WIDTH= 0 ;
    private static int HEIGHT=0;

    static Rectangle food;


    private static double ogonX;
    private static double ogonY;


    public static void setOgon(double x, double y) {
        ogonX = x;
        ogonY = y;
    }

    public static double getOgonX() {
        return ogonX;
    }

    public static double getOgonY() {

        return ogonY;
    }

    public static Rectangle getFood() {
        return food;
    }

    public static int getRozmiarKratki() {
        return rozmiarKratki;
    }

    public static void setHeight (int height){
        HEIGHT = height * rozmiarKratki;
    }
    public static void setWidth(int width){
        WIDTH = width * rozmiarKratki;
    }
    public static int getHeight() {
        return HEIGHT;
    }
    public static int getWidth() { return WIDTH; }
}
