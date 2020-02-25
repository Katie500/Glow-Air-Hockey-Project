
import java.util.Scanner;

public class GlowAirHockeyApp {
	private Table table;
	
	final int FPS = 60;
	
	// Create a new table.
	public GlowAirHockeyApp(String player_one_name, String player_one_colour, String player_two_name, String player_two_colour, String puck_colour) {
		Paddle player_one = new Paddle(player_one_name, player_one_colour);
		Paddle player_two = new Paddle(player_two_name, player_two_colour);
		Puck puck = new Puck(puck_colour);
		table = new Table(player_one, player_two, puck);
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
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
		
		GlowAirHockeyApp air_hockey = new GlowAirHockeyApp(player_one_name, player_one_colour, player_two_name, player_two_colour, puck_colour);

		int timer_count = 0;
		air_hockey.table.resetPlayerOne();
		air_hockey.table.resetPlayerTwo();
		air_hockey.table.resetPuck();
		
		while (! air_hockey.table.gameOver()) {
			air_hockey.updateGame();
			timer_count += 1;
			if (timer_count == 1000000) {
				break;
			}
		}
	}
	
	public void updateGame() {
		table.updatePuckPosition(FPS);
		table.checkForGoal();
		table.keepPuckIn();
		table.updatePaddlePositions(FPS);
		table.keepPaddlesIn();
		table.paddleCollision(table.getPlayerOne());
		table.paddleCollision(table.getPlayerTwo());
	}
}
