
import java.util.ArrayList;
import java.util.Arrays;

public class Table {
	private double width = 600;
	private double height = 800;
	
	final double CENTER_X = width / 2;
	final double CENTER_Y = height / 2;
	
	private ArrayList<Paddle> paddles = new ArrayList<Paddle>(2);
	
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
	
	public Paddle getPlayerOne() {
		ArrayList<Paddle> temp = new ArrayList<Paddle>(Arrays.asList(paddles.get(0), paddles.get(1)));
		return temp.get(0);
	}
	
	public Paddle getPlayerTwo() {
		ArrayList<Paddle> temp = new ArrayList<Paddle>(Arrays.asList(paddles.get(0), paddles.get(1)));
		return temp.get(1);
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setPlayerOne(Paddle paddle) {
		paddles.set(0,  paddle);
	}
	
	public void setPlayerTwo(Paddle paddle) {
		paddles.set(1,  paddle);
	}
}
