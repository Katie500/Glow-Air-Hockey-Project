package application;

import java.util.ArrayList;
import java.util.Arrays;

import gui.GameScreen;

import java.lang.Math;

// TODO: Auto-generated Javadoc
/**
 * The Class Table.
 */
public class Table {
	
	/** The width. */
	final double WIDTH = GameScreen.WIDTH;
	
	/** The height. */
	final double HEIGHT = GameScreen.HEIGHT;
	
	/** The center x. */
	final double CENTER_X = GameScreen.WIDTH / 2;
	
	/** The center y. */
	final double CENTER_Y = GameScreen.HEIGHT / 2;
	
	/** The goal size. */
	final double GOAL_SIZE = GameScreen.GOAL_SIZE;
	
	/** The player one default y. */
	final double PLAYER_ONE_DEFAULT_Y = HEIGHT / 4;
	
	/** The player two default y. */
	final double PLAYER_TWO_DEFAULT_Y = HEIGHT - (HEIGHT / 4);
	
	/** The score to win. */
	final double SCORE_TO_WIN = 7;
	
	/** The puck. */
	private Puck puck;
	
	/** The player one. */
	private Player player_one;
	
	/** The player two. */
	private Player player_two;
	
	/**
	 * Instantiates a new table.
	 *
	 * @param player_one the player one
	 * @param player_two the player two
	 * @param puck the puck
	 */
	// Player one will play on the lower half of the table and player two will be on the top half of the table.
	public Table(Player player_one, Player player_two, Puck puck) {
		this.puck = puck;
		this.player_one = player_one;
		this.player_two = player_two;
	}
	
	/**
	 * Gets the puck.
	 *
	 * @return the puck
	 */
	// Getter for the puck.
	public Puck getPuck() {
		return new Table(player_one, player_two, puck).puck;
	}
	
	/**
	 * Gets the player one.
	 *
	 * @return the player one
	 */
	// Getter for player one.
	public Player getPlayerOne() {
		return new Table(player_one, player_two, puck).player_one;
	}
	
	/**
	 * Gets the player two.
	 *
	 * @return the player two
	 */
	// Getter for player two.
	public Player getPlayerTwo() {
		return new Table(player_one, player_two, puck).player_two;
	}
	
	// This function keeps the paddles inside the confines of the table and changes each paddle's velocity if one collides with a wall.
	// It should be called each time the timer goes off.
	/**
	 * Keep paddles in.
	 */
	// Because there are two paddles it was easier to put this function in 'Table' instead of 'Paddle'.
	public void keepPaddlesIn() {
		// For when the player's paddle collides with the right wall of the table.
		if (player_one.getX() > (WIDTH - player_one.SIZE)) {
			player_one.setX(WIDTH - player_one.SIZE);
			player_one.setVelocityX(-player_one.getVelocityX() * 0.9);
		}
		
		// For when player one's paddle collides with the left wall of the table.
		if (player_one.getX() < player_one.SIZE) {
			player_one.setX(player_one.SIZE);
			player_one.setVelocityX(-player_one.getVelocityX() * 0.9);
		}
		
		// For when player one's paddle collides with the bottom wall of the table.
		if (player_one.getY() < player_one.SIZE) {
			player_one.setY(player_one.SIZE);
			player_one.setVelocityY(-player_one.getVelocityY() * 0.9);
		}
		
		// For when player one's paddle attempts to go over the center line.
		if (player_one.getY() > (CENTER_Y - player_one.SIZE)) {
			player_one.setY(CENTER_Y - player_one.SIZE);
			player_one.setVelocityY(-player_one.getVelocityY() * 0.9);
		}
		
		// CHECKING PLAYER TWO
		
		// For when player two's paddle collides with the right wall of the table.
		if (player_two.getX() > (WIDTH - player_two.SIZE)) {
			player_two.setX(WIDTH - player_two.SIZE);
			player_two.setVelocityX(-player_two.getVelocityX() * 0.9);
		}
		
		// For when player two's paddle collides with the left wall of the table.
		if (player_two.getX() < player_two.SIZE) {
			player_two.setX(player_two.SIZE);
			player_two.setVelocityX(-player_two.getVelocityX() * 0.9);
		}
		
		// For when player two's paddle collides with the top wall of the table.
		if (player_two.getY() > HEIGHT - player_two.SIZE) {
			player_two.setY(HEIGHT - player_two.SIZE);
			player_two.setVelocityY(-player_two.getVelocityY() * 0.9);
		}
		
		// For when player two's paddle attempts to go over the center line.
		if (player_two.getY() < (CENTER_Y + player_two.SIZE)) {
			player_two.setY(CENTER_Y + player_two.SIZE);
			player_two.setVelocityY(-player_two.getVelocityY() * 0.9);
		}
	}
	
	/**
	 * Check for goal.
	 *
	 * @return the array list
	 */
	// This function checks if either player has scored. If one has then the score of the corresponding player is changed and the puck and paddles are reset to the center of the table.
	public ArrayList<Boolean> checkForGoal() {
		boolean p1 = false;
		boolean p2 = false;
		
		// Checking to see if player two has scored.
		if ((puck.getX() > (CENTER_X - (GOAL_SIZE / 2) + puck.SIZE) && (puck.getX() < (CENTER_X + (GOAL_SIZE / 2) - puck.SIZE))) && (puck.getY() <= puck.SIZE)) {
			player_two.goal();
			
			// Resetting both players' paddles to their default positions.
			resetPuck();
			resetPlayerOne();
			resetPlayerTwo();
			
			// Resetting velocities to 0.
			resetVelocities();
			
			p2 = true;
		}
		
		// Checking to see if player one has scored.
		if ((puck.getX() > (CENTER_X - (GOAL_SIZE / 2) + puck.SIZE) && (puck.getX() < (CENTER_X + (GOAL_SIZE / 2) - puck.SIZE) )) && (puck.getY() >= HEIGHT - puck.SIZE)) {
			player_one.goal();
			
			// Resetting positions.
			resetPuck();
			resetPlayerOne();
			resetPlayerTwo();
			
			// Resetting velocities.
			resetVelocities();
			
			p1 = true;
		}
		
		return new ArrayList<Boolean>(Arrays.asList(p1, p2));
	}
	
	/**
	 * Reset velocities.
	 */
	// Sets velocities of both paddles and the puck to 0.
	public void resetVelocities() {
		puck.setVelocityX(0);
		puck.setVelocityY(0);
		player_one.setVelocityX(0);
		player_one.setVelocityY(0);
		player_two.setVelocityX(0);
		player_two.setVelocityY(0);
	}
	
	/**
	 * Reset puck.
	 */
	// Resets the puck to the center of the table.
	public void resetPuck() {
		puck.setX(CENTER_X);
		puck.setY(CENTER_Y);
	}
	
	/**
	 * Reset player one.
	 */
	// Resets player one's paddle to it's starting position.
	public void resetPlayerOne() {
		player_one.setX(CENTER_X);
		player_one.setY(PLAYER_ONE_DEFAULT_Y);
	}
	
	/**
	 * Reset player two.
	 */
	// Resets player two's paddle to it's starting position.
	public void resetPlayerTwo() {
		player_two.setX(CENTER_X);
		player_two.setY(PLAYER_TWO_DEFAULT_Y);
	}
	
	/**
	 * Apply friction.
	 *
	 * @param delay the delay
	 */
	// Applies friction to the puck so its velocity slowly degrades over time.
	public void applyFriction(int delay) {
		puck.setVelocityX(puck.getVelocityX() * puck.FRICTION);
		puck.setVelocityY(puck.getVelocityY() * puck.FRICTION);
		player_one.setVelocityX(player_one.getVelocityX() * player_one.FRICTION);
		player_one.setVelocityY(player_one.getVelocityY() * player_one.FRICTION);
		player_two.setVelocityX(player_two.getVelocityX() * player_two.FRICTION);
		player_two.setVelocityY(player_two.getVelocityY() * player_two.FRICTION);
	}
	
	/**
	 * Update paddle positions.
	 *
	 * @param delay the delay
	 */
	// Updates the position of each player's paddle with each frame.
	public void updatePaddlePositions(int delay) {
		ArrayList<Player> paddles = new ArrayList<Player>(Arrays.asList(player_one, player_two));
		for (int p = 0; p < 2; p++) {
			Player paddle = paddles.get(p);
			
			double delta_x = paddle.getVelocityX() * delay / 1000;
			paddle.setX(paddle.getX() + delta_x);
			
			double delta_y = paddle.getVelocityY() * delay / 1000;
			paddle.setY(paddle.getY() + delta_y);
		}
	}
	
	/**
	 * Game over.
	 *
	 * @return true, if successful
	 */
	// This function checks if the game is over. If a player has won the game.
	public boolean gameOver() {
		if (player_one.getScore() == SCORE_TO_WIN | player_two.getScore() == SCORE_TO_WIN) {
			return true;
		}
		return false;
	}
	
	/**
	 * Distance.
	 *
	 * @param delta_x the delta x
	 * @param delta_y the delta y
	 * @return the double
	 */
	// This function is a distance calculator used in the puck-paddle collision calculations.
	public double distance(double delta_x, double delta_y) {
		return Math.sqrt((delta_x)*(delta_x) + (delta_y)*(delta_y));
	}
	
	/**
	 * Rotate.
	 *
	 * @param x the x
	 * @param y the y
	 * @param sin the sin
	 * @param cos the cos
	 * @param reverse the reverse
	 * @return the array list
	 */
	// This function is an angle calculator used in the puck-paddle collision calculations.
	public ArrayList<Double> rotate(double x, double y, double sin, double cos, boolean reverse) {
		ArrayList<Double> values = new ArrayList<Double>(Arrays.asList(0.0, 0.0));
		
		if (reverse) {
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
	
	// This function checks if the puck has collided with a specified paddle. If it has, it adjusts the corresponding positions and velocities.
	// This should be called with both players' paddles each time the timer goes off.
	/**
	 * Paddle collision.
	 *
	 * @param player the player
	 */
	// I used the website 'https://codepen.io/allanpope/pen/OVxVKj?editors=0010' to help me with the calculations in this function (including the rotate function used here).
	public void paddleCollision(Player player) {		
		double delta_x = puck.getX() - player.getX();
		double delta_y = puck.getY() - player.getY();
		double distance = distance(delta_x, delta_y);
		double sum_of_radii = puck.SIZE + player.SIZE;
		
		// If the distance between the puck and paddle is less than the sum of their radii, then a collision has occurred.
		if (distance < sum_of_radii) {			
			double angle = Math.atan2(delta_y,  delta_x);
			double sin = Math.sin(angle);
			double cos = Math.cos(angle);
			
			// Rotate paddle's position.
			ArrayList<Double> rotated_paddle_position = new ArrayList<Double>(Arrays.asList(0.0, 0.0));
			
			// Rotate puck's position.
			ArrayList<Double> rotated_puck_position = rotate(delta_x, delta_y, sin, cos, true);
			
			// Rotate paddle's velocity.
			ArrayList<Double> rotated_paddle_velocity = rotate(player.getVelocityX(), player.getVelocityY(), sin, cos, true);
			
			// Rotate Puck's velocity.
			ArrayList<Double> rotated_puck_velocity = rotate(puck.getVelocityX(), puck.getVelocityY(), sin, cos, true);
			
			// In each of the above array lists, the first entry is the x value and the second is the y value. We can use those to adjust the velocities.
			double net_velocity_x =  rotated_paddle_velocity.get(0) - rotated_puck_velocity.get(0);
			
			rotated_paddle_velocity.set(0, (rotated_paddle_velocity.get(0) * (player.MASS - puck.MASS) + puck.MASS + rotated_puck_velocity.get(0)) / (player.MASS + puck.MASS));
			rotated_puck_velocity.set(0, net_velocity_x + rotated_paddle_velocity.get(0));
			
			// Adjusting positions in case there is any overlap.
			double absolute_v = Math.abs(rotated_paddle_velocity.get(0)) + Math.abs(rotated_puck_velocity.get(0));
			double overlap = sum_of_radii - Math.abs(rotated_paddle_position.get(0) - rotated_puck_position.get(0));
			
			rotated_paddle_position.set(0, rotated_paddle_position.get(0) + (rotated_paddle_velocity.get(0) / absolute_v * overlap));
			rotated_puck_position.set(0, rotated_puck_position.get(0) + (rotated_puck_velocity.get(0) / absolute_v * overlap));
			
			// Rotate the positions and velocities back.
			ArrayList<Double> paddle_position = rotate(rotated_paddle_position.get(0), rotated_paddle_position.get(1), sin, cos, false);
			ArrayList<Double> puck_position = rotate(rotated_puck_position.get(0), rotated_puck_position.get(1), sin, cos, false);
			ArrayList<Double> paddle_velocity = rotate(rotated_paddle_velocity.get(0), rotated_paddle_velocity.get(1), sin, cos, false);
			ArrayList<Double> puck_velocity = rotate(rotated_puck_velocity.get(0), rotated_puck_velocity.get(1), sin, cos, false);
			
			// Adjusting the screen positions.
			puck.setX(player.getX() + puck_position.get(0));
			puck.setY(player.getY() + puck_position.get(1));
			player.setX(player.getX() + paddle_position.get(0));
			player.setY(player.getY() + paddle_position.get(1));
			
			// Adjusting the screen velocities.
			puck.setVelocityX(puck_velocity.get(0));
			puck.setVelocityY(puck_velocity.get(1));
			player.setVelocityX(paddle_velocity.get(0));
			player.setVelocityY(paddle_velocity.get(1));			
		}
	}
}
