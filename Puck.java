
public class Puck {
	private String colour;
	private double x;
	private double y;
	private double velocity_x = 500;
	private double velocity_y = 500;
	
	final double PUCK_SIZE = 20; // The radius of the puck.
	final double MASS = 10;
	final double MAX_VELOCITY = 300;
	final double FRICTION_X = 0.997;
	final double FRICTION_Y = 0.997;
	
	// Constructor used to create a puck.
	public Puck(String colour) {
		this.colour = colour;
	}
	
	// This constructor will be used by certain getter methods to eliminate privacy leaks while retaining the necessary values.
	public Puck(double x, double y, double velocity_x, double velocity_y) {
		this.x = x;
		this.y = y;
		this.velocity_x = velocity_x;
		this.velocity_y = velocity_y;
	}
	
	// Getter for the puck's colour.
	public String getColour() {
		return new Puck(colour).colour;
	}
	
	// Getter for the puck's x velocity.
	public double getVelocityX() {
		return new Puck(x, y, velocity_x, velocity_y).velocity_x;
	}
	
	// Getter for the puck's y velocity.
	public double getVelocityY() {
		return new Puck(x, y, velocity_x, velocity_y).velocity_y;
	}
	
	// Getter for the puck's x position.
	public double getX() {
		return new Puck(x, y, velocity_x, velocity_y).x;
	}
	
	// Getter for the puck's y position.
	public double getY() {
		return new Puck(x, y, velocity_x, velocity_y).y;
	}
	
	// Setter for the puck's colour.
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	// Setter for the puck's x velocity.
	public void setVelocityX(double velocity) {
		this.velocity_x = velocity;
	}
	
	// Setter for the puck's y velocity.
	public void setVelocityY(double velocity) {
		this.velocity_y = velocity;
	}
	
	// Setter for the puck's x position.
	public void setX(double x) {
		this.x = x;
	}
	
	// Setter for the puck's y position.
	public void setY(double y) {
		this.y = y;
	}
}
