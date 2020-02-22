import java.util.ArrayList;
import java.util.Arrays;

public class Puck {
	private String colour;
	private double velocity = 0;
	private double direction = 0; // This is in radians, between 0 and 2pi.
	private ArrayList<Double> location = new ArrayList<Double>(Arrays.asList(300.0, 400.0)); // Default location for the puck is the center of the screen.
	
	public Puck(String colour) {
		this.colour = colour;
	}
	
	public String getColour() {
		return new Puck(colour).colour;
	}
	
	public double getVelocity() {
		return new Puck(colour).velocity;
	}
	
	public double getDirection() {
		return new Puck(colour).direction;
	}
	
	public ArrayList<Double> getLocation() {
		ArrayList<Double> location_copy = new ArrayList<Double>();
		for (int i = 0; i < location.size(); i++) {
			location.add(location.get(i));
		}
		return location_copy;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	
	public void setDirection(double direction) {
		if (direction >= 0 && direction <= 2*Math.PI) {
			this.direction = direction;
		}
	}
	
	public void setLocation(double x, double y) {
		if (location.size() == 0) {
			location.add(x);
			location.add(y);
		}
		
		else {
			location.set(0, x);
			location.set(1,  y);
		}
	}
}
