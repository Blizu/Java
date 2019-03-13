package gra.snake;

import java.io.Serializable;

public class Gracz implements Serializable {
	private String name;
	private int score;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;

	}

	public int getScore() {

		return score;
	}
	public void setScore() {
		this.score = score;
	}
	
	public Gracz(String name, int score) {

		this.name = name;
		this.score = score;
	}
}
