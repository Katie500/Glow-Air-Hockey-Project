
public class Puck {
	private String colour;
	private double x;
	private double y;
	private double velocity_x = 0;
	private double velocity_y = 0;
	
	final double PUCK_SIZE = 20; // The radius of the puck.
	final double MASS = 10;
	final double MAX_SPEED = 50;
	final double FRICTION_X = 0.95;
	final double FRICTION_Y = 0.95;
	final double ACCELERATION = 1;
	
	public Puck(String colour) {
		this.colour = colour;
	}
	
	public String getColour() {
		return new Puck(colour).colour;
	}
	
	public double getVelocityX() {
		return new Puck(colour).velocity_x;
	}
	
	public double getVelocityY() {
		return new Puck(colour).velocity_y;
	}
	
	public double getX() {
		return new Puck(colour).x;
	}
	
	public double getY() {
		return new Puck(colour).y;
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
