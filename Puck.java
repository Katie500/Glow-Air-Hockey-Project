package application;

import javafx.scene.shape.Circle;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class Puck extends Circle {
	private double x;
	private double y;
    private double velocity_x = 0; //making the value of velocity x equal to 0
    private double velocity_y = 0; //making the value of velocity y equal to 0
	
    final double SIZE = 20; // Initializes the radius of the puck to be 20
    final double MASS = 10; //Initializes the mass of the puck to be 10
	final double MAX_VELOCITY = 1500;
	final double FRICTION = 0.997;
	final Color COLOUR = Color.RED; //the default color of the puck is red
	
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
	
	// This constructor will be used by certain getter methods to eliminate privacy leaks while retaining the necessary values.
	public Puck(Puck puck) {
		this.x = puck.x;
		this.y = puck.y;
		this.velocity_x = puck.velocity_x;
		this.velocity_y = puck.velocity_y;
	}
	
	// Getter for the puck's x velocity.
	public double getVelocityX() {
		return new Puck(this).velocity_x;
	}
	
	// Getter for the puck's y velocity.
	public double getVelocityY() {
		return new Puck(this).velocity_y;
	}
	
	// Getter for the puck's x position.
	public double getX() {
		return new Puck(this).x;
	}
	
	// Getter for the puck's y position.
	public double getY() {
		return new Puck(this).y;
	}
	
	// Setter for the puck's x velocity.
	public void setVelocityX(double velocity) {
		if (velocity_x <= 1000) {
			this.velocity_x = velocity;
		}
		
		else {
			this.velocity_x = 1000;
		}
	}
	
	// Setter for the puck's y velocity.
	public void setVelocityY(double velocity) {
		if (velocity_x <= 1000) {
			this.velocity_y = velocity;
		}
		
		else {
			this.velocity_y = 1000;
		}
	}
	
	// Setter for the puck's x position.
	public void setX(double x) {
		this.x = x;
		setCenterX(x);
	}
	
	// Setter for the puck's y position.
	public void setY(double y) {
		this.y = y;
		setCenterY(y);
	}
	
	// This function keeps the puck inside the confines of the table and changes the puck's velocity if it collides with a wall.
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
	
	// Updates the position of the puck based on the delay between each frame.
	public void updatePuckPosition(int delay) {
		double delta_x = getVelocityX() * delay / 1000;
		setX(getX() + delta_x);
		
		double delta_y = getVelocityY() * delay / 1000;
		setY(getY() + delta_y);
	}
}
