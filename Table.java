import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class Player extends Circle {
	private String name;
	private String colour;
	private double x;
	private double y;
	private double velocity_x = 1000;
	private double velocity_y = 0;
	private int score = 0;

	final double SIZE = 25; // The radius of the puck.
	final double MASS = 10;
	final double MAX_VELOCITY = 1000;
	final double ACCELERATION = 2;
	final int SCORE_TO_WIN = 7;
	final double FRICTION = 1;

	// Constructor used to create a player.
	public Player(String name, String colour) {
		setName(name);
		setColour(colour);
		setRadius(SIZE);
		setFill(Color.valueOf(colour));
	}

	// Constructor used exclusively by some getter methods to eliminate privacy leaks and retain the values needed.
	public Player(Player player) {
		this.name = player.name;
		this.colour = player.colour;
		this.x = player.x;
		this.y = player.y;
		this.velocity_x = player.velocity_x;
		this.velocity_y = player.velocity_y;
		this.score = player.score;
	}

	// Getter for player name.
	public String getName() {
		return new Player(this).name;
	}

	// Getter for player colour.
	public String getColour() {
		return new Player(this).colour;
	}

	// Getter for player's x velocity.
	public double getVelocityX() {
		return new Player(this).velocity_x;
	}

	// Getter for player's y velocity.
	public double getVelocityY() {
		return new Player(this).velocity_y;
	}

	// Getter for player's x position.
	public double getX() {
		return new Player(this).x;
	}

	// Getter for player's y position.
	public double getY() {
		return new Player(this).y;
	}

	// Getter for the player's score.
	public int getScore() {
		return new Player(this).score;
	}

	// Setter for player's name.
	public void setName(String name) {
		this.name = name;
	}

	// Setter for player's colour.
	public void setColour(String colour) {
		this.colour = colour;
	}

	// Setter for player's x position.
	public void setX(double x) {
		this.x = x;
		setCenterX(x);
	}

	// Setter for player's y position.
	public void setY(double y) {
		this.y = y;
		setCenterY(y);
	}

	// Setter for player's x velocity.
	public void setVelocityX(double velocity) {
		if (velocity_x <= 1000) {
			this.velocity_x = velocity;
		}

		else {
			this.velocity_x = 1000;
		}
	}

	// Setter for player's y velocity.
	public void setVelocityY(double velocity_y) {
		if (velocity_x <= 1000) {
			this.velocity_y = velocity_y;
		}

		else {
			this.velocity_y = 1000;
		}
	}

	// Updates the player's score, prints a message, and pauses the game for five seconds if the player scores.
	public void goal() {
		score += 1;
		System.out.println(name + " has scored!");
	}
}
