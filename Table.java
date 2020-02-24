
public class Table {
	final double WIDTH = 600;
	final double HEIGHT = 800;
	final double CENTER_X = WIDTH / 2;
	final double CENTER_Y = HEIGHT / 2;
	
	private Paddle player_one;
	private Paddle player_two;
	
	// Player one will play on the lower half of the table and player two will be on the top half of the table.
	public Table(Paddle player_one, Paddle player_two) {
		this.player_one = player_one;
		this.player_two = player_two;
	}
	
	public Paddle getPlayerOne() {
		return new Table(player_one, player_two).player_one;
	}
	
	public Paddle getPlayerTwo() {
		return new Table(player_one, player_two).player_two;
	}

	public void setPlayerOne(Paddle paddle) {
		player_one = paddle;
	}
	
	public void setPlayerTwo(Paddle paddle) {
		player_two = paddle;
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
}
