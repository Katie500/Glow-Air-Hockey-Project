import java.util.ArrayList;
import java.lang.Math;



// An instance of this class can occur using a paddle or a wall, and the puck. The collision calculations will depend on which constructor was used.
public class Collision {
	private Puck puck;
	private Paddle paddle;
	private Wall wall;
	private char collision_type;
	
	final double PUCK_SIZE = 50; // Radius of the puck.
	final double PADDLE_SIZE = 50; // Radius of the paddle.
	
	
	// Constructor for a puck-wall collision.
	public Collision(Puck puck, Wall wall) {
		this.puck = puck;
		this.wall = wall;
		collision_type = 'w';
	}
	
	
	// Constructor for a puck-paddle collision.
	public Collision(Puck puck, Paddle paddle) {
		this.puck = puck;
		this.paddle = paddle;
		collision_type = 'p';
	}
	
	
	public double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
	}
	
	
	// This function checks if a collision has occurred.
	public boolean isCollision() {
		if (collision_type == 'w') {
			return puckWallCollision();
		}
		
		return puckPaddleCollision();
	}
	
	
	// This function checks whether a puck-paddle collision has occurred.
	public boolean puckPaddleCollision() {
		double puck_x = puck.getLocation().get(0);
		double puck_y = puck.getLocation().get(1);
		double paddle_x = paddle.getLocation().get(0);
		double paddle_y = paddle.getLocation().get(1);
		
		double distance_between_circles = distance(paddle_x, paddle_y, puck_x, puck_y);
		
		return distance_between_circles <= PUCK_SIZE +  PADDLE_SIZE;
	}
	
	
	// This function checks whether a puck-wall collision has occurred between the puck and the specific instance of 'Wall' given in the constructor.
	public boolean puckWallCollision() {
		final double left_wall = 0.0;
		final double right_wall = 600.0;
		final double lower_wall = 0.0;
		final double upper_wall = 800.0;
		double puck_x = puck.getLocation().get(0);
		double puck_y = puck.getLocation().get(1);
		
		// This is executed the left wall is being checked for a collision.
		if (wall.getType() == 'l') {
			if (puck_x - PUCK_SIZE <= left_wall) {
					return true;
			}
			else {
				return false;
			}
		}
		
		// This is executed the right wall is being checked for a collision.
		else if (wall.getType() == 'r') {
			if (puck_x + PUCK_SIZE >= right_wall) {
					return true;
			}
			else {
				return false;
			}
		}
		
		// This is executed the top wall is being checked for a collision.
		else if (wall.getType() == 't') {
			if (puck_y + PUCK_SIZE >= upper_wall) {
					return true;
			}
			else {
				return false;
			}
		}
		
		// This is executed the bottom wall is being checked for a collision.
		else {
			if (puck_y - PUCK_SIZE <= lower_wall) {
					return true;
			}
			else {
				return false;
			}
		}
	}	
	
	
	
	
	
	public double puckBounceAngle() {
		// The vector AB will be a line between point A: (x1, y1), the location of the paddle (the center of the circle), and point B: (x2, y2), the location of the point of impact.
		// Consider the tangent line CD, which touches the paddle with radius r at point Z.
		// The equation for the paddle (circle) is (x2 - x1)^2 + (y2-y1)^2 = r^2
		ArrayList<Double> paddle_location = paddle.getLocation();
		return 5;
	}	
	
	
	
	
	
	
	// If a collision has occurred, this function can be called to update the puck's trajectory and speed of travel.
	public void update() {		
		// This will be executed for a puck-paddle collision.
		if (collision_type == 'p') {
			double puck_x = puck.getLocation().get(0);
			double puck_y = puck.getLocation().get(1);
			double paddle_x = paddle.getLocation().get(0);
			double paddle_y = paddle.getLocation().get(1);
			
			double distance_between_circles = distance(paddle_x, paddle_y, puck_x, puck_y);
			double distanceToMove = PUCK_SIZE + PADDLE_SIZE - distance_between_circles;
			
			// If there is a collision and if the obstacles are overlapping, the puck will be moved to the outer point of impact.
			if (distanceToMove != 0) {
				double angle = Math.atan2(puck_y - paddle_y, puck_x - paddle_x); // This is the angle of the line between the circles.
				double puck_x_adjustment = Math.cos(angle) * distance_between_circles;
				double puck_y_adjustment = Math.sin(angle) * distance_between_circles;
				puck.setLocation(puck_x + puck_x_adjustment, puck_y + puck_y_adjustment);
			}
		}
		
		// This will be executed for a puck-wall collision.
		else if (collision_type == 'w') {
			// If there is a collision and if the obstacles are overlapping, the puck will be moved to the outer point of impact.
		}
	}
	
	
	
	
	

	
}
