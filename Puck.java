
public class Puck {
	private String colour;
	private double x;
	private double y;
	private double velocity_x = 100;
	private double velocity_y = 100;
	
	final double PUCK_SIZE = 20; // The radius of the puck.
	final double MASS = 10;
	final double MAX_SPEED = 50;
	final double FRICTION_X = 0.97;
	final double FRICTION_Y = 0.97;
	final double ACCELERATION = 1;
	
	public Puck(String colour) {
		this.colour = colour;
	}
	
	// This constructor will be used by the getX(), getY(), getVelocityX(), and getVelocityY() methods to eliminate privacy leaks.
	public Puck(double x, double y, double velocity_x, double velocity_y) {
		this.x = x;
		this.y = y;
		this.velocity_x = velocity_x;
		this.velocity_y = velocity_y;
	}
	
	public String getColour() {
		return new Puck(colour).colour;
	}
	
	public double getVelocityX() {
		return new Puck(x, y, velocity_x, velocity_y).velocity_x;
	}
	
	public double getVelocityY() {
		return new Puck(x, y, velocity_x, velocity_y).velocity_y;
	}
	
	public double getX() {
		return new Puck(x, y, velocity_x, velocity_y).x;
	}
	
	public double getY() {
		return new Puck(x, y, velocity_x, velocity_y).y;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public void setVelocityX(double velocity) {
		this.velocity_x = velocity;
	}
	
	public void setVelocityY(double velocity) {
		this.velocity_y = velocity;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}
