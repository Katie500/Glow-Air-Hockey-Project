
import java.util.Scanner;

public class GlowAirHockeyApp {
	private Table table;
	
	final static int FPS = 30;
	
	// Create a new table.
	public GlowAirHockeyApp(String player_one_name, String player_one_colour, String player_two_name, String player_two_colour, String puck_colour) {
		Player player_one = new Player(player_one_name, player_one_colour);
		Player player_two = new Player(player_two_name, player_two_colour);
		Puck puck = new Puck(puck_colour);
		table = new Table(player_one, player_two, puck);
		
		// Setting the puck and paddles to their starting positions.
		table.resetPuck();
		table.resetPlayerOne();
		table.resetPlayerTwo();
	}
	
	public static void main(String[] args) {
		// NOTE: For this text-based demo of the game, the puck move at a constant velocity and both players' paddles will remain stationary.
			// The puck will continue moving until a player is scored on. Then it will reset to the center of the table and continue moving.
			// This will continue until one of the players has won the game.
		
		Scanner scanner = new Scanner(System.in);
		
		// The following takes input from the user, which is then used to create a new game.
		System.out.print("Enter a username for player one: ");
		String player_one_name = scanner.nextLine();
		
		System.out.println("");
		System.out.print("Enter a colour for player one's paddle: ");
		String player_one_colour = scanner.nextLine();
		
		System.out.println("");
		System.out.print("Enter a username for player one: ");
		String player_two_name = scanner.nextLine();
		
		System.out.println("");
		System.out.print("Enter a colour for player two's paddle: ");
		String player_two_colour = scanner.nextLine();
		
		System.out.println("");
		System.out.print("Enter a colour for the puck: ");
		String puck_colour = scanner.nextLine();
		
		scanner.close();
		
		// Creating a new game based on the user's input.
		GlowAirHockeyApp air_hockey = new GlowAirHockeyApp(player_one_name, player_one_colour, player_two_name, player_two_colour, puck_colour);
		
		// This creates a delay between each time the game is updated and rendered so we can see a real-time representation of the movement of the puck.
		try {
			while (! air_hockey.table.gameOver()) {
				air_hockey.updateGame();
				air_hockey.renderGame();
				Thread.sleep(1000/FPS);
			}
		}
		
		catch (InterruptedException e) {
		}
		
	}
	
	// This function updates what is necessary with each tick (or frame, if you're thinking in terms of fps).
	public void updateGame() {
		table.updatePuckPosition(FPS);
		table.checkForGoal();
		table.keepPuckIn();
		table.updatePaddlePositions(FPS);
		table.keepPaddlesIn();
		table.paddleCollision(table.getPlayerOne());
		table.paddleCollision(table.getPlayerTwo());
//		table.applyFriction();
	}
	
	// This function creates a text-based rendering of the game.
	// There are no player controls in this demo due to the limitations of a text-based animation game.
	// However, it effectively shows how the puck (and paddles, though for this demo they are stationary) will move and how it will bounce after a collision.
	public void renderGame() {
		int divisor = 25;
		int height = 32;
		int width = 24;
		char[][] board = new char[height][width];
		
		// Separating each rendering of the board.
		for (int i = 0; i < 2; i++) {
			System.out.println("");
		}
		
		// Filling the board with empty spaces.
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				board[y][x] = '-';
			}
		}
		
		// Creating goal posts. They will be represented by the character '#'.
		int goal_x1 = (int) ((table.CENTER_X - (table.GOAL_SIZE / 2)) / divisor);
		int goal_x2 = (int) ((table.CENTER_X + (table.GOAL_SIZE / 2)) / divisor);
		board[0][goal_x1] = '#';
		board[0][goal_x2] = '#';
		board[31][goal_x1] = '#';
		board[31][goal_x2] = '#';
		
		Player player_one = table.getPlayerOne();
		Player player_two = table.getPlayerTwo();
		Puck puck = table.getPuck();
		
		// Assigning puck location. It will be represented by the character '0'.
		int puck_x = (int) (puck.getX() / divisor);
		int puck_y = (int) (puck.getY() / divisor);
		if (puck_x < width && puck_y < height) {
			board[puck_y][puck_x] = '0';
		}
			
		// The following code is a computer for player one.
		final int COMPUTER_ONE_SPEED = 6;
		
		if (player_one.getX() > puck.getX()) {
			player_one.setX(player_one.getX() - (COMPUTER_ONE_SPEED * divisor / 10));
		}
		
		if (player_one.getX() < puck.getX()) {
			player_one.setX(player_one.getX() + (COMPUTER_ONE_SPEED * divisor / 10));
		}
		
		if (player_one.getY() < puck.getY() - 2*divisor && puck.getY() < table.CENTER_Y) {
			player_one.setY(player_one.getY() + (COMPUTER_ONE_SPEED * divisor / 6));
		}
		
		if (puck.getY() > table.CENTER_Y || puck.getY() < player_one.getY()) {
			if (player_one.getY() > 4*divisor) {
				player_one.setY(player_one.getY() - (COMPUTER_ONE_SPEED * divisor / 6));

			}
		}
		
		// The following code is a computer for player two.
		final int COMPUTER_TWO_SPEED = 2;
		
		if (player_two.getX() > puck.getX()) {
			player_two.setX(player_two.getX() - (COMPUTER_TWO_SPEED * divisor / 10));
		}
		
		if (player_two.getX() < puck.getX()) {
			player_two.setX(player_two.getX() + (COMPUTER_TWO_SPEED * divisor / 10));
		}
		
		if (player_two.getY() > puck.getY() + 2*divisor && puck.getY() > table.CENTER_Y) {
			player_two.setY(player_two.getY() - (COMPUTER_TWO_SPEED * divisor / 6));
		}
		
		if (puck.getY() < table.CENTER_Y || puck.getY() > player_two.getY()) {
			if (player_two.getY() < table.HEIGHT - (4*divisor)) {
				player_two.setY(player_two.getY() + (COMPUTER_TWO_SPEED * divisor / 6));

			}
		}
		
		// Assigning player one's location. It will be represented by the character '1'.
		int player_one_x = (int) (table.getPlayerOne().getX() / divisor);
		int player_one_y = (int) (table.getPlayerOne().getY() / divisor);
		
		if (player_one_x >= 0 && player_one_x < width && player_one_y >= 0 && player_one_y < height) {
			board[player_one_y][player_one_x] = '1';
		}
				
		// Assigning player two's location. It will be represented by the character '2'.
		int player_two_x = (int) (table.getPlayerTwo().getX() / divisor);
		int player_two_y = (int) (table.getPlayerTwo().getY() / divisor);
		
		if (player_two_x >= 0 && player_two_x < width && player_two_y >= 0 && player_two_y < height) {
			board[player_two_y][player_two_x] = '2';
		}		
		
		// Printing the table.
		int count = 0;

		for (int y = height - 1; y >= 0; y--) {
			System.out.println("");

			for (int x = 0; x < width; x++) {
				System.out.print(" " + board[y][x] + " ");
			}
			System.out.print("     ");
			
			for (int x = 0; x < width; x++) {
				System.out.print(" " + board[count][x] + " ");
			}
			count += 1;
		}
		
		System.out.println("");
		
		// If the puck ever stops this will keep it moving.
		if (Math.abs(puck.getVelocityX()) <= 20 && Math.abs(puck.getVelocityY()) <= 20) {
			puck.setVelocityX(50);
			puck.setVelocityY(50);
		}
	}
}
