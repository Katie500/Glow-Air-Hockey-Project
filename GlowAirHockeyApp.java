
import java.util.Scanner;

public class GlowAirHockeyApp {
	private Table table;
	
	final static int FPS = 20;
	
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
//		table.applyFriction(); // Friction is turned off for this demo since only the puck is moving, and we need to keep moving until it finds its way into a goal.
	}
	
	// This function creates a text-based rendering of the game.
	// There are no player controls in this demo due to the limitations of a text-based animation game.
	// However, it effectively shows how the puck (and paddles, though for this demo they are stationary) will move and how it will bounce after a collision.
	public void renderGame() {
		char[][] board = new char[32][24];
		
		// Separating each rendering of the board.
		for (int i = 0; i < 2; i++) {
			System.out.println("");
		}
		
		// Filling the board with empty spaces.
		for (int y = 0; y < 32; y++) {
			for (int x = 0; x < 24; x++) {
				board[y][x] = '-';
			}
		}
		
		// Creating goal posts. They will be represented by the character '#'.
		int goal_x1 = (int) ((table.CENTER_X - (table.GOAL_SIZE / 2)) / 25);
		int goal_x2 = (int) ((table.CENTER_X + (table.GOAL_SIZE / 2)) / 25);
		board[0][goal_x1] = '#';
		board[0][goal_x2] = '#';
		board[31][goal_x1] = '#';
		board[31][goal_x2] = '#';
		
		
		// Assigning puck location. It will be represented by the character '0'.
		int puck_x = (int) (table.getPuck().getX() / 25);
		int puck_y = (int) (table.getPuck().getY() / 25);
		board[puck_y][puck_x] = '0';
		
		// Assigning player one's location. It will be represented by the character '1'.
		int player_one_x = (int) (table.getPlayerOne().getX() / 25);
		int player_one_y = (int) (table.getPlayerOne().getY() / 25);
		board[player_one_y][player_one_x] = '1';
				
		// Assigning player two's location. It will be represented by the character '2'.
		int player_two_x = (int) (table.getPlayerTwo().getX() / 25);
		int player_two_y = (int) (table.getPlayerTwo().getY() / 25);
		board[player_two_y][player_two_x] = '2';
		
		// Printing the table.
		for (int y = 31; y >= 0; y--) {
			System.out.println("");
			for (int x = 0; x < 24; x++) {
				System.out.print(" " + board[y][x] + " ");
			}
		}
		
		System.out.println("");
	}
}
