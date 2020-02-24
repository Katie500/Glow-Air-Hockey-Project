
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
}
