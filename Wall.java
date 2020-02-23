
public class Wall {
	private String name; // To identify which wall is being referred to in the case of a collision.
	private char type; // Can be 'l' for left wall, 'r' for right wall, 'u' for top wall, or 'l' for bottom wall.
	private double location; // An x or y value (depends on wall type).
	
	public Wall(String name, char type, double location) {
		this.name = name;
		if (type == 'l' | type == 'r' | type == 't' | type == 'b') {
			this.type = type;
		}
		this.location = location;
	}
	
	public String getName() {
		return new Wall(name, type, location).name;
	}
	
	public char getType() {
		return new Wall(name, type, location).type;
	}
	
	public double getLocation() {
		return new Wall(name, type, location).location;
	}
}

