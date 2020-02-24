
import java.util.ArrayList;

public class Table {
	private double width = 600;
	private double height = 800;
	
	final double CENTER_X = width / 2;
	final double CENTER_Y = height / 2;
	
	private ArrayList<Double> paddles = new ArrayList<Double>();
	
	public double getWidth() {
		return new Table().width;
	}
	
	public double getHeight() {
		return new Table().height;
	}
	
	public double getCenterX() {
		return new Table().CENTER_X;
	}
	
	public double getCenterY() {
		return new Table().CENTER_Y;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
}
