package application;

import javafx.scene.shape.Circle;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class Player extends Circle {
	private String name;
	private Color colour = Color.BLUE;  //default color of the player 
	private double x; 
	private double y;
	private double velocity_x = 0; //making the value of velocity x equal to 0
	private double velocity_y = 0; //making the value of velocity y equal to 0
	private int score = 0; //initializing the score to 0
	private Circle paddle_center = new Circle();
	private DropShadow border_glow = new DropShadow();
	
	final double SIZE = 28; // The radius of the puck.
	final double MASS = 20;
	final double MAX_VELOCITY = 1000;
	final double ACCELERATION = 200;
	final int SCORE_TO_WIN = 7; //required score to win the game is 7
	final double FRICTION = 1; //initializing the friction to 1
	
	// Constructor used to create a player.
	public Player(String name) {
		setName(name); //will set the name of the player from name received from getter method
		setRadius(SIZE); //will set the size of the paddle to be 28 as initialized above
		paddle_center.setFill(Color.BLACK); //the center of the paddle will be colored black to give the paddle a dense and mutlicolor look
		paddle_center.setRadius(SIZE - 8); //the center black circle will be the paddle's original (size - 8) in size   
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
		this.border_glow = player.border_glow;
	}
	
	// Getter for player name.
	public String getName() {
		return new Player(this).name;
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
	
	// Getter for the paddle's center circle
	public Circle getCenterCircle() {
		return paddle_center;
	}
	// Getter for the paddle's color
	public Color getColour() {
		return colour;
	}
	
	// Getter for the player's border glow.
	public DropShadow getBorderGlow() {
		return new Player(this).border_glow;
	}
	
	// Setter for player's name.
	public void setName(String name) {
		this.name = name;
	}
	
	// Setter for player's colour.
	public void setColour(Color colour) {
		this.colour = colour.deriveColor(1.0,  0.8,  1.0,  5.0);
		setFill(colour); //set the color
		setBorderGlow(); //add the glow effect around the paddle
		setEffect(border_glow);
		paddle_center.setEffect(border_glow);
	}
	
	// Setting the player's border glow.
	public void setBorderGlow() {
		border_glow.setColor(colour);//creates the glow around the paddle
		border_glow.setOffsetX(0f);//creates the glow around the paddle
		border_glow.setOffsetY(0f);//creates the glow around the paddle
		border_glow.setWidth(50);//creates the width of the glow 
		border_glow.setHeight(50);//creates the height of the glow 
		border_glow.setRadius(SIZE + 15); //creates the radius of the glow 
	}
	
	// Setter for player's x position.
	public void setX(double x) {
		this.x = x;
		setCenterX(x);
		paddle_center.setCenterX(x);
	}
	
	// Setter for player's y position.
	public void setY(double y) {
		this.y = y;
		setCenterY(y);
		paddle_center.setCenterY(y);
	}
	
	// Setter for player's x velocity.
	public void setVelocityX(double v) {
		if (v > MAX_VELOCITY) {
			this.velocity_x = MAX_VELOCITY;
		}
		
		else if (v < -MAX_VELOCITY) {
			this.velocity_x = -MAX_VELOCITY;
		}
		
		else {
			this.velocity_x = v;
		}
	}
	
	// Setter for player's y velocity.
	public void setVelocityY(double v) {
		if (v > MAX_VELOCITY) {
			this.velocity_y = MAX_VELOCITY;
		}
		
		else if (v < -MAX_VELOCITY) {
			this.velocity_y = -MAX_VELOCITY;
		}
		
		else {
			this.velocity_y = v;
		}
	}
	
	// Updates the player's score, prints a message, and pauses the game for five seconds if the player scores.
	public void goal() {
		score += 1;
		
	}
}
