
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

public class Table {
	final double WIDTH = 600;
	final double HEIGHT = 800;
	final double CENTER_X = WIDTH / 2;
	final double CENTER_Y = HEIGHT / 2;
	final double GOAL_SIZE = 75;
	final double PLAYER_ONE_DEFAULT_Y = HEIGHT / 4;
	final double PLAYER_TWO_DEFAULT_Y = HEIGHT - (HEIGHT / 4);
	
	private Scoreboard scoreboard = new Scoreboard(0, 0);
	private Puck puck;
	private Paddle player_one;
	private Paddle player_two;
	
	// Player one will play on the lower half of the table and player two will be on the top half of the table.
	public Table(Puck puck, Paddle player_one, Paddle player_two) {
		this.puck = puck;
		this.player_one = player_one;
		this.player_two = player_two;
	}
	
	public Puck getPuck() {
		return new Table(puck, player_one, player_two).puck;
	}
	
	public Paddle getPlayerOne() {
		return new Table(puck, player_one, player_two).player_one;
	}
	
	public Paddle getPlayerTwo() {
		return new Table(puck, player_one, player_two).player_two;
	}

	public void setPuck(Puck puck) {
		this.puck = puck;
	}
	
	public void setPlayerOne(Paddle paddle) {
		player_one = paddle;
	}
	
	public void setPlayerTwo(Paddle paddle) {
		player_two = paddle;
	}
	
	// This function keeps the puck inside the confines of the table and changes the puck's velocity if it collides with a wall.
	// It should be called each time the timer goes off.
	public void keepPuckIn() {
		// For when the puck collides with the left wall of the table.
		if (puck.getX() > (WIDTH - puck.PUCK_SIZE)) {
			puck.setX(WIDTH - puck.PUCK_SIZE);
			puck.setVelocityX(-puck.getVelocityX());
		}
		
		// For when the puck collides with the right wall of the table.
		if (puck.getX() < puck.PUCK_SIZE) {
			puck.setX(puck.PUCK_SIZE);
			puck.setVelocityX(-puck.getVelocityX());
		}
		
		// For when the puck collides with the top wall of the table.
		if (puck.getY() > (HEIGHT - puck.PUCK_SIZE)) {
			puck.setY(HEIGHT - puck.PUCK_SIZE);
			puck.setVelocityY(-puck.getVelocityY());
		}
		
		// For when the puck collides with the bottom wall of the table.
		if (puck.getY() < puck.PUCK_SIZE) {
			puck.setY(puck.PUCK_SIZE);
			puck.setVelocityY(-puck.getVelocityY());
		}
	}
	
	// This function keeps the paddles inside the confines of the table and changes each paddle's velocity if one collides with a wall.
	// It should be called each time the timer goes off.
	// Because there are two paddles it was easier to put this function in 'Table' instead of 'Paddle'.
	public void keepPaddlesIn() {
		// CHECKING PLAYER ONE
		
		// For when player one's paddle collides with the right wall of the table.
		if (player_one.getX() > (WIDTH - player_one.PADDLE_SIZE)) {
			player_one.setX(WIDTH - player_one.PADDLE_SIZE);
			player_one.setVelocityX(-player_one.getVelocityX());
		}
		
		// For when player one's paddle collides with the left wall of the table.
		if (player_one.getX() < player_one.PADDLE_SIZE) {
			player_one.setX(player_one.PADDLE_SIZE);
			player_one.setVelocityX(-player_one.getVelocityX());
		}
		
		// For when player one's paddle collides with the bottom wall of the table.
		if (player_one.getY() < player_one.PADDLE_SIZE) {
			player_one.setY(player_one.PADDLE_SIZE);
			player_one.setVelocityY(-player_one.getVelocityY());
		}
		
		// For when player one's paddle attempts to go over the center line.
		if (player_one.getY() > (CENTER_Y - player_one.PADDLE_SIZE)) {
			player_one.setY(CENTER_Y - player_one.PADDLE_SIZE);
			player_one.setVelocityY(-player_one.getVelocityY());
		}
		
		// CHECKING PLAYER TWO
		
		// For when player two's paddle collides with the right wall of the table.
		if (player_two.getX() > (WIDTH - player_two.PADDLE_SIZE)) {
			player_two.setX(WIDTH - player_two.PADDLE_SIZE);
			player_two.setVelocityX(-player_two.getVelocityX());
		}
		
		// For when player two's paddle collides with the left wall of the table.
		if (player_two.getX() < player_two.PADDLE_SIZE) {
			player_two.setX(player_two.PADDLE_SIZE);
			player_two.setVelocityX(-player_two.getVelocityX());
		}
		
		// For when player two's paddle collides with the top wall of the table.
		if (player_two.getY() > HEIGHT - player_two.PADDLE_SIZE) {
			player_two.setY(HEIGHT - player_two.PADDLE_SIZE);
			player_two.setVelocityY(-player_two.getVelocityY());
		}
		
		// For when player two's paddle attempts to go over the center line.
		if (player_two.getY() < (CENTER_Y + player_two.PADDLE_SIZE)) {
			player_two.setY(CENTER_Y + player_two.PADDLE_SIZE);
			player_two.setVelocityY(-player_two.getVelocityY());
		}
	}
	
	// This function checks if either player has scored. If one has then the scoreboard is changed and the puck is reset to the center of the board.
	public void checkForGoal() {
		// Checking to see if player two has scored.
		if ((puck.getX() > (CENTER_X - (GOAL_SIZE / 2) + puck.PUCK_SIZE) && (puck.getX() < (CENTER_X + (GOAL_SIZE / 2) - puck.PUCK_SIZE) )) && (puck.getY() <= puck.PUCK_SIZE)) {
			scoreboard.playerTwoGoal();
			puck.setX(CENTER_X);
			puck.setY(CENTER_Y);
			
			// Resetting both players' paddles to their default positions.
			player_one.setX(CENTER_X);
			player_one.setY(PLAYER_ONE_DEFAULT_Y);
			player_two.setX(CENTER_X);
			player_two.setY(PLAYER_TWO_DEFAULT_Y);
		}
		
		// Checking to see if player one has scored.
		if ((puck.getX() > (CENTER_X - (GOAL_SIZE / 2) + puck.PUCK_SIZE) && (puck.getX() < (CENTER_X + (GOAL_SIZE / 2) - puck.PUCK_SIZE) )) && (puck.getY() >= HEIGHT - puck.PUCK_SIZE)) {
			scoreboard.playerOneGoal();
			puck.setX(CENTER_X);
			puck.setY(CENTER_Y);
			
			// Resetting both players' paddles to their default positions.
			player_one.setX(CENTER_X);
			player_one.setY(PLAYER_ONE_DEFAULT_Y);
			player_two.setX(CENTER_X);
			player_two.setY(PLAYER_TWO_DEFAULT_Y);
		}
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
		double delta_x = puck.getX() - paddle.getX();
		double delta_y = puck.getY() - paddle.getY();
		double distance = distance(delta_x, delta_y);
		double sum_of_radii = puck.PUCK_SIZE + paddle.PADDLE_SIZE;
		
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
			ArrayList<Double> rotated_puck_velocity = rotate(puck.getVelocityX(), puck.getVelocityY(), sin, cos, true);
			
			// In each of the above array lists, the first entry is the x value and the second is the y value. We can use those to adjust the velocities.
			double net_velocity_x =  rotated_paddle_velocity.get(0) - rotated_puck_velocity.get(0);
			double new_paddle_velocity_x = (rotated_paddle_velocity.get(0) * (paddle.MASS - puck.MASS) + 2 * puck.MASS + rotated_puck_velocity.get(0)) / (paddle.MASS + puck.MASS);
			rotated_puck_velocity.set(0, net_velocity_x + new_paddle_velocity_x);
			
			// Adjusting positions in case there is any overlap.
			double absolute_v = Math.abs(rotated_paddle_velocity.get(0)) + Math.abs(rotated_puck_velocity.get(0));
			double overlap = paddle.PADDLE_SIZE + puck.PUCK_SIZE - Math.abs(rotated_paddle_position.get(0) - rotated_puck_position.get(0));
			
			rotated_paddle_position.set(0, rotated_paddle_position.get(0) + (rotated_paddle_velocity.get(0) / absolute_v * overlap));
			rotated_puck_position.set(0, rotated_puck_position.get(0) + (rotated_puck_velocity.get(0) / absolute_v * overlap));
			
			// Rotate the positions and velocities back.
			ArrayList<Double> reversed_paddle_position = rotate(rotated_paddle_position.get(0), rotated_paddle_position.get(1), sin, cos, false);
			ArrayList<Double> reversed_puck_position = rotate(rotated_puck_position.get(0), rotated_puck_position.get(1), sin, cos, false);
			ArrayList<Double> reversed_paddle_velocity = rotate(rotated_paddle_velocity.get(0), rotated_paddle_velocity.get(1), sin, cos, false);
			ArrayList<Double> reversed_puck_velocity = rotate(rotated_puck_velocity.get(0), rotated_puck_velocity.get(1), sin, cos, false);
			
			// Adjusting the screen positions.
			puck.setX(paddle.getX() + reversed_puck_position.get(0));
			puck.setY(paddle.getY() + reversed_puck_position.get(1));
			paddle.setX(paddle.getX() + reversed_paddle_position.get(0));
			paddle.setY(paddle.getY() + reversed_paddle_position.get(1));
			
			// Adjusting the screen velocities.
			puck.setVelocityX(reversed_puck_velocity.get(0));
			puck.setVelocityY(reversed_puck_velocity.get(1));
			paddle.setVelocityX(reversed_paddle_velocity.get(0));
			paddle.setVelocityY(reversed_paddle_velocity.get(1));
		}
	}	
}
