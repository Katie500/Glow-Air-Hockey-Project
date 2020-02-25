
public class Paddle {
	private String name;
	private String colour;

	private double x;
	private double y;
	private double velocity_x = 0;
	private double velocity_y = 0;
	
	final double PADDLE_SIZE = 25; // The radius of the puck.
	final double MASS = 10;
	final double MAX_SPEED = 50;
	final double FRICTION_X = 0.95;
	final double FRICTION_Y = 0.95;
	final double ACCELERATION = 1;
	
	public Paddle(String name, String colour) {
		this.name = name;
		this.colour = colour;
	}
	
	public Paddle(double x, double y, double velocity_x, double velocity_y) {
		this.x = x;
		this.y = y;
		this.velocity_x = velocity_x;
		this.velocity_y = velocity_y;
	}
	
	public String getName() {
		return new Paddle(name, colour).name;
	}
	
	public String getColour() {
		return new Paddle(name, colour).colour;
	}

	public double getVelocityX() {
		return new Paddle(x, y, velocity_x, velocity_y).velocity_x;
	}
	
	public double getVelocityY() {
		return new Paddle(x, y, velocity_x, velocity_y).velocity_y;
	}
	
	public double getX() {
		return new Paddle(x, y, velocity_x, velocity_y).x;
	}
	
	public double getY() {
		return new Paddle(x, y, velocity_x, velocity_y).y;
	}
		
	public void setName(String name) {
		this.name = name;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setVelocityX(double velocity_x) {
		this.velocity_x = velocity_x;
	}
	
	public void setVelocityY(double velocity_y) {
		this.velocity_y = velocity_y;
	}
}
