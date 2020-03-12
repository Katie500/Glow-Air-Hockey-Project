
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Puck extends Circle {
	private String colour;
	private double x;
	private double y;
	private double velocity_x = 500;
	private double velocity_y = 500;
	final double SIZE = 20; // The radius of the puck.
	final double MASS = 10;
	final double MAX_SPEED = 1000;
	final double FRICTION = 0.997;

	
	// Constructor used to create a puck.
	public Puck(String colour) {
		this.colour = colour ;
		setRadius(SIZE) ;
		setFill(Color.valueOf(colour));

	}
	public Puck(Puck puck){
		this.colour = puck.colour ;
		this.x = puck.x ;
		this.y = puck.y ;
		this.velocity_x = puck.velocity_x ;
		this.velocity_y = puck.velocity_y ;
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
	
	// Setter for the puck's colour.
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	// Setter for the puck's x velocity.
	public void setVelocityX(double velocity) {
		if(velocity_x <= MAX_SPEED)
			this.velocity_x = velocity ;
		else
			this.velocity_x = MAX_SPEED ;
	}
	
	// Setter for the puck's y velocity.
	public void setVelocityY(double velocity) {
		if(velocity_x <= MAX_SPEED)
			this.velocity_x = velocity ;
		else
			this.velocity_x = MAX_SPEED ;
	}
	
	// Setter for the puck's x position.
	public void setX(double x) {
		this.x = x;
		setCenterX(x);
		System.out.println("x: " +x) ;

	}
	
	// Setter for the puck's y position.
	public void setY(double y) {
		this.y = y;
		setCenterY(y);
		System.out.println("y :" + y);
	}
}
