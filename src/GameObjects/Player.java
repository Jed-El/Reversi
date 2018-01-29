package GameObjects;

import javafx.scene.paint.Color;

public class Player {
	private Side side;
	private Color color;
	private int score;
    /**********************
	 * constructor
	 * @param side the Player Side.
	 * @param color the Player Color.
     *********************/
	public Player (Side side, Color color) {
		this.side = side;
		this.color = color;
		this.score = 2;
	}
    /**********************
	 * Setter function for score.
	 * @param score.
     *********************/
	public void setScore(int score) {
		this.score = score;
	}
    /**********************
	 * Getter function for score.
	 * @return score.
     *********************/
	public int getScore() {
		return this.score;
	}
    /**********************
	 * Getter function for side.
	 * @return side.
     *********************/
	public Side getSide() {
		return this.side;
	}
    /**********************
	 * Getter function for color.
	 * @return color.
     *********************/
	public Color getColor() {
		return this.color;
	}
}
