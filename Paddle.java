import java.util.ArrayList;

public class Paddle {
	private String name;
	private String colour;
	private ArrayList<Double> location = new ArrayList<Double>();
	
	public Paddle(String name, String colour) {
		this.name = name;
		this.colour = colour;
	}
	
	public String getName() {
		return new Paddle(name, colour).name;
	}
	
	public String getColour() {
		return new Paddle(name, colour).colour;
	}
	
	public ArrayList<Double> getLocation() {
		ArrayList<Double> location_copy = new ArrayList<Double>();
		for (int i = 0; i < location.size(); i++) {
			location.add(location.get(i));
		}
		return location_copy;
	}
	
	public void setName(String name) {
		this.name = name;
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
