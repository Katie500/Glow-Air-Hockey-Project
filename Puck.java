import java.util.ArrayList;

public class Puck {
	private String colour;
	private ArrayList<Double> location = new ArrayList<Double>();
	
	public Puck(String colour) {
		this.colour = colour;
	}
	
	public String getColour() {
		return new Puck(colour).colour;
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
