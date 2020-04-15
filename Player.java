package application;

import javafx.scene.shape.Circle;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class Player.
 */
public class Player extends Circle {
	
	/** The name. */
	private String name;
    
    /** The colour. */
    private Color colour = Color.BLUE;  //default color of the player 	private double x;
	
	/** The x. */
	private double x;
    
    /** The y. */
    private double y;
    
    /** The velocity x. */
    private double velocity_x = 0; //making the value of velocity x equal to 0
    
    /** The velocity y. */
    private double velocity_y = 0; //making the value of velocity y equal to 0
    
    /** The score. */
    private int score = 0; //initializing the score to 0
	
	/** The paddle center. */
	private Circle paddle_center = new Circle();
	
	/** The border glow. */
	private DropShadow border_glow = new DropShadow();
	
	/** The size. */
	final double SIZE = 28; // The radius of the puck.
	
	/** The mass. */
	final double MASS = 20;
	
	/** The max velocity. */
	final double MAX_VELOCITY = 1000;
	
	/** The acceleration. */
	final double ACCELERATION = 200;
    
    /** The score to win. */
    public final int SCORE_TO_WIN = 7; //required score to win the game is 7
    
    /** The friction. */
    final double FRICTION = 1; //initializing the friction to 1
	
	/**
	 * Instantiates a new player.
	 *
	 * @param name the name
	 */
	// Constructor used to create a player.
	public Player(String name) {
        setName(name); //will set the name of the player from name received from getter method
        setRadius(SIZE); //will set the size of the paddle to be 28 as initialized above
        paddle_center.setFill(Color.BLACK); //the center of the paddle will be colored black to give the paddle a dense and mutlicolor look
        paddle_center.setRadius(SIZE - 8); //the center black circle will be the paddle's original (size - 8) in size   
	}
	
	/**
	 * Instantiates a new player.
	 *
	 * @param player the player
	 */
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
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	// Getter for player name.
	public String getName() {
		return new Player(this).name;
	}
	
	/**
	 * Gets the velocity X.
	 *
	 * @return the velocity X
	 */
	// Getter for player's x velocity.
	public double getVelocityX() {
		return new Player(this).velocity_x;
	}
	
	/**
	 * Gets the velocity Y.
	 *
	 * @return the velocity Y
	 */
	// Getter for player's y velocity.
	public double getVelocityY() {
		return new Player(this).velocity_y;
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	// Getter for player's x position.
	public double getX() {
		return new Player(this).x;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	// Getter for player's y position.
	public double getY() {
		return new Player(this).y;
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	// Getter for the player's score.
	public int getScore() {
		return new Player(this).score;
	}
	
	/**
	 * Gets the center circle.
	 *
	 * @return the center circle
	 */
	public Circle getCenterCircle() {
		return paddle_center;
	}
	
	/**
	 * Gets the colour.
	 *
	 * @return the colour
	 */
	public Color getColour() {
		return colour;
	}
	
	/**
	 * Gets the border glow.
	 *
	 * @return the border glow
	 */
	// Getter for the player's border glow.
	public DropShadow getBorderGlow() {
		return new Player(this).border_glow;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	// Setter for player's name.
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the colour.
	 *
	 * @param colour the new colour
	 */
	// Setter for player's colour.
	public void setColour(Color colour) {
		this.colour = colour.deriveColor(1.0,  0.8,  1.0,  5.0);
		setFill(colour);
		setBorderGlow();
		setEffect(border_glow);
		paddle_center.setEffect(border_glow);
	}
	
	/**
	 * Sets the border glow.
	 */
	// Setting the player's border glow.
	public void setBorderGlow() {
		border_glow.setColor(colour);
		border_glow.setOffsetX(0f);
		border_glow.setOffsetY(0f);
		border_glow.setWidth(50);
		border_glow.setHeight(50);
		border_glow.setRadius(SIZE + 15);
	}
	
	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	// Setter for player's x position.
	public void setX(double x) {
		this.x = x;
		setCenterX(x);
		paddle_center.setCenterX(x);
	}
	
	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	// Setter for player's y position.
	public void setY(double y) {
		this.y = y;
		setCenterY(y);
		paddle_center.setCenterY(y);
	}
	
	/**
	 * Sets the velocity X.
	 *
	 * @param v the new velocity X
	 */
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
	
	/**
	 * Sets the velocity Y.
	 *
	 * @param v the new velocity Y
	 */
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
	
	/**
	 * Goal.
	 */
	// Updates the player's score, prints a message, and pauses the game for five seconds if the player scores.
	public void goal() {
		score += 1;
		
	}
}
