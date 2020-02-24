
public class Table {
	final double WIDTH = 600;
	final double HEIGHT = 800;
	final double CENTER_X = WIDTH / 2;
	final double CENTER_Y = HEIGHT / 2;
	
	private Paddle player_one;
	private Paddle player_two;
	
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
}
