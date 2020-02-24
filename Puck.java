
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;


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
	
	public double distance(double delta_x, double delta_y) {
		return Math.sqrt((delta_x)*(delta_x) + (delta_y)*(delta_y));
	}
	
	public ArrayList<Double> rotate(double x, double y, double sin, double cos, boolean direction) {
		ArrayList<Double> values = new ArrayList<Double>(2);
		
		if (direction) {
			double new_x = (x * cos) + (y * sin);
			double new_y = (y * cos) - (x * sin);
			values.set(0, new_x);
			values.set(1, new_y);
		}
		
		else {
			double new_x = (x * cos) - (y * sin);
			double new_y = (y * cos) + (x * sin);
			values.set(0,  new_x);
			values.set(1,  new_y);
		}
		
		return values;
	}
	
	// This function checks if the puck has collided with a specified paddle.
	// This should be checked with each paddle each time the timer goes off.
	public void paddleCollision(Paddle paddle) {
		double delta_x = this.x - paddle.getX();
		double delta_y = this.y - paddle.getY();
		double distance = distance(delta_x, delta_y);
		double sum_of_radii = PUCK_SIZE + paddle.PADDLE_SIZE;
		
		// If the distance between the pucks is less than the sum of their radii, then a collision has occurred.
		if (distance < sum_of_radii) {
			double angle = Math.atan2(delta_y,  delta_x);
			double sin = Math.sin(angle);
			double cos = Math.cos(angle);
			
			// Rotate paddle's position.
			ArrayList<Double> rotated_paddle_position = new ArrayList<Double>(Arrays.asList(0.0, 0.0));
			
			// Rotate puck's position.
			ArrayList<Double> rotated_puck_position = rotate(delta_x, delta_y, sin, cos, true);
			
			// Rotate paddle's velocity.
			ArrayList<Double> rotated_paddle_velocity = rotate(paddle.getVelocityX(), paddle.getVelocityY(), sin, cos, true);
			
			// Rotate Puck's velocity.
			ArrayList<Double> rotated_puck_velocity = rotate(this.velocity_x, this.velocity_y, sin, cos, true);
			
			// In each of the above array lists, the first entry is the x value and the second is the y value. We can use those to adjust the velocities.
			double net_velocity_x =  rotated_paddle_velocity.get(0) - rotated_puck_velocity.get(0);
			double new_paddle_velocity_x = (rotated_paddle_velocity.get(0) * (paddle.MASS - this.MASS) + 2 * this.MASS + rotated_puck_velocity.get(0)) / (paddle.MASS + this.MASS);
			rotated_puck_velocity.set(0, net_velocity_x + new_paddle_velocity_x);
			
			// Adjusting positions in case there is any overlap.
			double absolute_v = Math.abs(rotated_paddle_velocity.get(0)) + Math.abs(rotated_puck_velocity.get(0));
			double overlap = paddle.PADDLE_SIZE + PUCK_SIZE - Math.abs(rotated_paddle_position.get(0) - rotated_puck_position.get(0));
			
			rotated_paddle_position.set(0, rotated_paddle_position.get(0) + (rotated_paddle_velocity.get(0) / absolute_v * overlap));
			rotated_puck_position.set(0, rotated_puck_position.get(0) + (rotated_puck_velocity.get(0) / absolute_v * overlap));
			
			// Rotate the positions and velocities back.
			ArrayList<Double> reversed_paddle_position = rotate(rotated_paddle_position.get(0), rotated_paddle_position.get(1), sin, cos, false);
			ArrayList<Double> reversed_puck_position = rotate(rotated_puck_position.get(0), rotated_puck_position.get(1), sin, cos, false);
			ArrayList<Double> reversed_paddle_velocity = rotate(rotated_paddle_velocity.get(0), rotated_paddle_velocity.get(1), sin, cos, false);
			ArrayList<Double> reversed_puck_velocity = rotate(rotated_puck_velocity.get(0), rotated_puck_velocity.get(1), sin, cos, false);
			
			// Adjusting the screen positions.
			this.x = paddle.getX() + reversed_puck_position.get(0);
			this.y = paddle.getY() + reversed_puck_position.get(1);
			paddle.setX(paddle.getX() + reversed_paddle_position.get(0));
			paddle.setY(paddle.getY() + reversed_paddle_position.get(1));
			
			// Adjusting the screen velocities.
			this.velocity_x = reversed_puck_velocity.get(0);
			this.velocity_y = reversed_puck_velocity.get(1);
			paddle.setVelocityX(reversed_paddle_velocity.get(0));
			paddle.setVelocityY(reversed_paddle_velocity.get(1));
		}
	}
}
