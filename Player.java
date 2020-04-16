
public class Player {
	private String name;
	private String colour;
	private double x;
	private double y;
	private double velocity_x = 0;
	private double velocity_y = 0;
	private int score = 0;
	
	final double PADDLE_SIZE = 25; // The radius of the puck.
	final double MASS = 10;
	final double MAX_VELOCITY = 400;
	final double ACCELERATION = 2;
	final int SCORE_TO_WIN = 7;
	
	// Constructor used to create a player.
	public Player(String name, String colour) {
		this.name = name;
		this.colour = colour;
	}
	
	// Constructor used exclusively by some getter methods to eliminate privacy leaks and retain the values needed.
	public Player(double x, double y, double velocity_x, double velocity_y, int score) {
		this.x = x;
		this.y = y;
		this.velocity_x = velocity_x;
		this.velocity_y = velocity_y;
		this.score = score;
	}
	
	// Getter for player name.
	public String getName() {
		return new Player(name, colour).name;
	}
	
	// Getter for player colour.
	public String getColour() {
		return new Player(name, colour).colour;
	}
	
	// Getter for player's x velocity.
	public double getVelocityX() {
		return new Player(x, y, velocity_x, velocity_y, score).velocity_x;
	}
	
	// Getter for player's y velocity.
	public double getVelocityY() {
		return new Player(x, y, velocity_x, velocity_y, score).velocity_y;
	}
	
	// Getter for player's x position.
	public double getX() {
		return new Player(x, y, velocity_x, velocity_y, score).x;
	}
	
	// Getter for player's y position.
	public double getY() {
		return new Player(x, y, velocity_x, velocity_y, score).y;
	}
	
	// Setter for player's name.
	public void setName(String name) {
		this.name = name;
	}
	
	// Getter for the player's score.
	public int getScore() {
		return new Player(x, y, velocity_x, velocity_y, score).score;
	}
	
	// Setter for player's colour.
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	// Setter for player's x position.
	public void setX(double x) {
		this.x = x;
	}
	
	// Setter for player's y position.
	public void setY(double y) {
		this.y = y;
	}
	
	// Setter for player's x velocity.
	public void setVelocityX(double velocity_x) {
		this.velocity_x = velocity_x;
	}
	
	// Setter for player's y velocity.
	public void setVelocityY(double velocity_y) {
		this.velocity_y = velocity_y;
	}
	
	// Updates the player's score, prints a message, and pauses the game for five seconds if the player scores.
	public void goal() {
		score += 1;
		System.out.println(name + " has scored!");
		
		try {
		Thread.sleep(5000);
		}
		
		catch (InterruptedException e) {
		}
	}
}


