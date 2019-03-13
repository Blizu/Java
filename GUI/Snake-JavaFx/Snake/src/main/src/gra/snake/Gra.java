package gra.snake;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Gra {

    private static double speedG; // domyślna prędkość gry

    public static void setSpeed(double speed){
        speedG = speed;
    }
    public static double getSpeed(){
        return speedG;
    }

	public static int randXY(int value) {

		return (int) (Math.random() * (value - SnakeConf.getRozmiarKratki())) / SnakeConf.getRozmiarKratki()
				* SnakeConf.getRozmiarKratki();

	}

	public static Rectangle newFood() {
		Rectangle food = new Rectangle(SnakeConf.getRozmiarKratki(), SnakeConf.getRozmiarKratki());
		food.getStyleClass().add("food");

		food = setFood(food);

		return food;
	}

	public static Rectangle zwieksz(double tailX, double tailY) {

		Rectangle rect = new Rectangle(SnakeConf.getRozmiarKratki(), SnakeConf.getRozmiarKratki());
		rect.setFill(Color.FORESTGREEN);
		rect.setTranslateX(tailX);
		rect.setTranslateY(tailY);

		return rect;
	}

	public static boolean checkConditions(Node tail, Rectangle food) {
		boolean flag = false;

		if (tail.getTranslateX() == food.getTranslateX() && tail.getTranslateY() == food.getTranslateY())
			flag = true;

		return flag;
	}

	public static Rectangle setFood(Rectangle food) {

		food.setTranslateX((int) (Math.random() * (SnakeConf.getWidth() - SnakeConf.getRozmiarKratki()))
				/ SnakeConf.getRozmiarKratki() * SnakeConf.getRozmiarKratki());
		food.setTranslateY((int) (Math.random() * (SnakeConf.getHeight() - SnakeConf.getRozmiarKratki()))
				/ SnakeConf.getRozmiarKratki() * SnakeConf.getRozmiarKratki());

		return food;
	}
}
