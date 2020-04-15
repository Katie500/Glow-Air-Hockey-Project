package application;

import javafx.scene.shape.Circle;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class Puck.
 */
public class Puck extends Circle {
	
	/** The x. */
	private double x;
	
	/** The y. */
	private double y;
    
    /** The velocity x. */
    private double velocity_x = 0; //making the value of velocity x equal to 0
    
    /** The velocity y. */
    private double velocity_y = 0; //making the value of velocity y equal to 0
	
    /** The size. */
    final double SIZE = 20; // Initializes the radius of the puck to be 20
    
    /** The mass. */
    final double MASS = 10; //Initializes the mass of the puck to be 10
	
	/** The max velocity. */
	final double MAX_VELOCITY = 1500;
	
	/** The friction. */
	final double FRICTION = 0.997;
	
	/** The colour. */
	final Color COLOUR = Color.RED; //the default color of the puck is red
	
	/**
	 * Instantiates a new puck.
	 */
	// Constructor used to create a puck.
	public Puck() {
		setRadius(SIZE);
		setFill(COLOUR.deriveColor(1.0,  0.8,  1.0,  5.0));
        DropShadow border_glow = new DropShadow(); //creates the glow around the puck 
        border_glow.setColor(COLOUR); //creates the glow around the puck 
        border_glow.setOffsetX(0f); //creates the glow around the puck 
        border_glow.setOffsetY(0f); //creates the glow around the puck 
        border_glow.setWidth(50); //creates the width of the glow 
        border_glow.setHeight(50); //creates the height of the glow 
        border_glow.setRadius(SIZE + 15); //creates the radius of the glow 
		setEffect(border_glow);
	}
	
	/**
	 * Instantiates a new puck.
	 *
	 * @param puck the puck
	 */
	// This constructor will be used by certain getter methods to eliminate privacy leaks while retaining the necessary values.
	public Puck(Puck puck) {
		this.x = puck.x;
		this.y = puck.y;
		this.velocity_x = puck.velocity_x;
		this.velocity_y = puck.velocity_y;
	}
	
	/**
	 * Gets the velocity X.
	 *
	 * @return the velocity X
	 */
	// Getter for the puck's x velocity.
	public double getVelocityX() {
		return new Puck(this).velocity_x;
	}
	
	/**
	 * Gets the velocity Y.
	 *
	 * @return the velocity Y
	 */
	// Getter for the puck's y velocity.
	public double getVelocityY() {
		return new Puck(this).velocity_y;
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	// Getter for the puck's x position.
	public double getX() {
		return new Puck(this).x;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	// Getter for the puck's y position.
	public double getY() {
		return new Puck(this).y;
	}
	
	/**
	 * Sets the velocity X.
	 *
	 * @param velocity the new velocity X
	 */
	// Setter for the puck's x velocity.
	public void setVelocityX(double velocity) {
		if (velocity_x <= 1000) {
			this.velocity_x = velocity;
		}
		
		else {
			this.velocity_x = 1000;
		}
	}
	
	/**
	 * Sets the velocity Y.
	 *
	 * @param velocity the new velocity Y
	 */
	// Setter for the puck's y velocity.
	public void setVelocityY(double velocity) {
		if (velocity_x <= 1000) {
			this.velocity_y = velocity;
		}
		
		else {
			this.velocity_y = 1000;
		}
	}
	
	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	// Setter for the puck's x position.
	public void setX(double x) {
		this.x = x;
		setCenterX(x);
	}
	
	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	// Setter for the puck's y position.
	public void setY(double y) {
		this.y = y;
		setCenterY(y);
	}
	
	// This function keeps the puck inside the confines of the table and changes the puck's velocity if it collides with a wall.
	/**
	 * Keep puck in.
	 *
	 * @param width the width
	 * @param height the height
	 */
	// It should be called each time the timer goes off.
	public void keepPuckIn(double width, double height) {
		// For when the puck collides with the left wall of the table.
		if (x > (width - SIZE)) {
			setX(width - SIZE);
			velocity_x *= -1;
		}
		
		// For when the puck collides with the right wall of the table.
		if (x < SIZE) {
			setX(SIZE);
			velocity_x *= -1;
		}
		
		// For when the puck collides with the top wall of the table.
		if (y > (height - SIZE)) {
			setY(height - SIZE);
			velocity_y *= -1;
		}
		
		// For when the puck collides with the bottom wall of the table.
		if (y < SIZE) {
			y = SIZE;
			velocity_y *= -1;
		}
	}
	
	/**
	 * Update puck position.
	 *
	 * @param delay the delay
	 */
	// Updates the position of the puck based on the delay between each frame.
	public void updatePuckPosition(int delay) {
		double delta_x = getVelocityX() * delay / 1000;
		setX(getX() + delta_x);
		
		double delta_y = getVelocityY() * delay / 1000;
		setY(getY() + delta_y);
	}
}
