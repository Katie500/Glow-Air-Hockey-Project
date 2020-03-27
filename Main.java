
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	private Table table;
	private boolean up, down, left, right, w, a, s, d = false;
	Timeline timeline;
	
	final static int DELAY = 10;
	final static int BASE_VELOCITY = 30;
	
	public static void main(String [] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		createTable();
		
		primaryStage.setTitle("Glow Air Hockey");
		
		Pane layout = new Pane();
		layout.setPrefSize(600, 800);
		layout.getChildren(); // Add things using this.
		layout.setStyle("-fx-background-color: BLACK;");
		createBackground(layout);
		
		// Timeline to call the event handler every 10ms to update the table.
		timeline = new Timeline(new KeyFrame(Duration.millis(DELAY), new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
	        	updateGame();
	        	controllerOne();
	        	controllerTwo();
	        	
	        	if (table.gameOver()) {
					timeline.stop();
				}
	        }
		}));
		
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();
		
		layout.getChildren().addAll(table.getPuck(), table.getPlayerOne(), table.getPlayerTwo(), table.getPlayerOne().getCenterCircle(), table.getPlayerTwo().getCenterCircle());
		Scene scene = new Scene(layout);
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {				
				switch (e.getCode()) {
					case UP: up = true; break;
					case DOWN: down = true; break;
					case LEFT: left = true; break;
					case RIGHT: right = true; break;
					case W: w = true; break;
					case S: s = true; break;
					case A: a = true; break;
					case D: d = true; break;
					default: break;
				}
			}
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
					case UP: {
						up = false;
						table.getPlayerOne().setVelocityY(table.getPlayerOne().getVelocityY() * 0.3);
					}
					break;
					
					case DOWN: {
						down = false;
						table.getPlayerOne().setVelocityY(table.getPlayerOne().getVelocityY() * 0.3);
					}
					break;
					
					case LEFT: {
						left = false;
						table.getPlayerOne().setVelocityX(table.getPlayerOne().getVelocityX() * 0.3);
					}
					break;
					
					case RIGHT: {
						right = false;
						table.getPlayerOne().setVelocityX(table.getPlayerOne().getVelocityX() * 0.3);
					}
					break;
					
					case W: {
						w = false;
						table.getPlayerTwo().setVelocityY(table.getPlayerTwo().getVelocityY() * 0.3);
					}
					break;
					
					case S: {
						s = false;
						table.getPlayerTwo().setVelocityY(table.getPlayerTwo().getVelocityY() * 0.3);
					}
					break;
					
					case A: {
						a = false;
						table.getPlayerTwo().setVelocityX(table.getPlayerTwo().getVelocityX() * 0.3);
					}
					break;
					
					case D: {
						d = false;
						table.getPlayerTwo().setVelocityX(table.getPlayerTwo().getVelocityX() * 0.3);
					}
					break;
					
					default: break;
				}
			}
		});
		
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public void createTable() {
		Scanner input = new Scanner(System.in);
		
		// The following takes input for each player's username, which is then used to create a new game.
		System.out.print("Enter a username for player one: ");
		String player_one_name = input.nextLine();
		
		System.out.println("");
		System.out.print("Enter a username for player two: ");
		String player_two_name = input.nextLine();
		
		// Creating a new table.
		table = new Table(new Player(player_one_name), new Player(player_two_name), new Puck());
		
		// Setting the puck and paddles to their starting positions.
		table.resetPuck();
		table.resetPlayerOne();
		table.resetPlayerTwo();
		
		// Now we have to set the colour for each player's paddle.
		playerOneColour(input);
		playerTwoColour(input);
		
		input.close();
	}
	
	// This takes a string input from the user and tries to use it to set player one's colour.
	// An invalid colour will cause an exception, which will be caught, causing the function to be called again asking for a new colour.
	public void playerOneColour(Scanner scanner) {
		System.out.println("");
		System.out.print("Enter a colour for player one's paddle: ");
		String colour = scanner.nextLine();
		
		try {
			table.getPlayerOne().setColour(Color.valueOf(colour));
		}
		
		catch (Exception e) {
			System.out.println("That is an invalid colour.");
			playerOneColour(scanner);
		}
	}
	
	// This takes a string input from the user and tries to use it to set player two's colour.
	// An invalid colour will cause an exception, which will be caught, causing the function to be called again asking for a new colour.
	public void playerTwoColour(Scanner scanner) {
		System.out.println("");
		System.out.print("Enter a colour for player two's paddle: ");
		String colour = scanner.nextLine();
		
		try {
			table.getPlayerTwo().setColour(Color.valueOf(colour));
		}
		
		catch (Exception e) {
			System.out.println("That is an invalid colour.");
			playerTwoColour(scanner);
		}
	}
	
	// This function updates what is necessary with each tick in the timeline.
	public void updateGame() {
		table.applyFriction(DELAY);
		table.getPuck().updatePuckPosition(DELAY);
		table.updatePaddlePositions(DELAY);
		table.checkForGoal();
		table.paddleCollision(table.getPlayerOne());
		table.paddleCollision(table.getPlayerTwo());
		table.getPuck().keepPuckIn(table.WIDTH, table.HEIGHT);
		table.keepPaddlesIn();
	}
	
	public void createBackground(Pane layout) {
		DropShadow border_glow = new DropShadow();
		border_glow.setColor(Color.RED);
		border_glow.setOffsetX(0f);
		border_glow.setOffsetY(0f);
		border_glow.setWidth(50);
		border_glow.setHeight(50);
		
		DropShadow goal_border_glow = new DropShadow();
		goal_border_glow.setColor(Color.CHARTREUSE);
		goal_border_glow.setOffsetX(0f);
		goal_border_glow.setOffsetY(0f);
		goal_border_glow.setWidth(50);
		goal_border_glow.setHeight(50);
		
		Circle center_circle = new Circle();
		center_circle.setRadius(80);
		center_circle.setFill(Color.RED);
		center_circle.setCenterX(table.CENTER_X);
		center_circle.setCenterY(table.CENTER_Y);
		center_circle.setEffect(border_glow);
		
		Circle center_circle_cover = new Circle();
		center_circle_cover.setRadius(75);
		center_circle_cover.setFill(Color.BLACK);
		center_circle_cover.setCenterX(table.CENTER_X);
		center_circle_cover.setCenterY(table.CENTER_Y);
		
		Circle center = new Circle();
		center.setRadius(5);
		center.setFill(Color.RED);
		center.setCenterX(table.CENTER_X);
		center.setCenterY(table.CENTER_Y);
		center.setEffect(border_glow);
		
		Rectangle left_border = new Rectangle();
		left_border.setX(0);
		left_border.setY(0);
		left_border.setHeight(table.HEIGHT);
		left_border.setWidth(10);
		left_border.setFill(Color.DARKRED);
		left_border.setEffect(border_glow);
		
		Rectangle right_border = new Rectangle();
		right_border.setX(table.WIDTH - 10);
		right_border.setY(0);
		right_border.setHeight(800);
		right_border.setWidth(10);
		right_border.setFill(Color.DARKRED);
		right_border.setEffect(border_glow);
		
		Rectangle top_border = new Rectangle();
		top_border.setX(0);
		top_border.setY(table.HEIGHT - 10);
		top_border.setHeight(10);
		top_border.setWidth(table.WIDTH);
		top_border.setFill(Color.DARKRED);
		top_border.setEffect(border_glow);
		
		Rectangle bottom_border = new Rectangle();
		bottom_border.setX(0);
		bottom_border.setY(0);
		bottom_border.setHeight(10);
		bottom_border.setWidth(table.WIDTH);
		bottom_border.setFill(Color.DARKRED);
		bottom_border.setEffect(border_glow);
		
		Rectangle center_line = new Rectangle();
		center_line.setX(0);
		center_line.setY(table.CENTER_Y + -3);
		center_line.setHeight(6);
		center_line.setWidth(table.WIDTH);
		center_line.setFill(Color.RED);
		center_line.setEffect(border_glow);
		
		Rectangle player_one_goal = new Rectangle();
		player_one_goal.setX(table.CENTER_X - table.GOAL_SIZE / 2);
		player_one_goal.setY(0);
		player_one_goal.setHeight(10);
		player_one_goal.setWidth(table.GOAL_SIZE);
		player_one_goal.setFill(Color.CHARTREUSE);
		player_one_goal.setEffect(goal_border_glow);
		
		Rectangle player_two_goal = new Rectangle();
		player_two_goal.setX(table.CENTER_X - table.GOAL_SIZE / 2);
		player_two_goal.setY(table.HEIGHT - 10);
		player_two_goal.setHeight(10);
		player_two_goal.setWidth(table.GOAL_SIZE);
		player_two_goal.setFill(Color.CHARTREUSE);
		player_two_goal.setEffect(goal_border_glow);
		
		layout.getChildren().addAll(center_line, left_border, right_border, top_border, bottom_border, player_one_goal, player_two_goal, center_circle, center_circle_cover, center);
	}
	
	public void controllerOne() {
		double vx = table.getPlayerOne().getVelocityX();
		double vy = table.getPlayerOne().getVelocityY();
		double acceleration = table.getPlayerOne().ACCELERATION;
		
		if (up) {
			vy -= acceleration;
		}
		
		if (down) {
			vy += acceleration;
		}
		
		if (left) {
			vx -= acceleration;
		}
		
		if (right) {
			vx += acceleration;
		}
		
		table.getPlayerOne().setVelocityX(vx);
		table.getPlayerOne().setVelocityY(vy);
	}
	
	public void controllerTwo() {
		double vx = table.getPlayerTwo().getVelocityX();
		double vy = table.getPlayerTwo().getVelocityY();
		double acceleration = table.getPlayerTwo().ACCELERATION;
		
		if (w) {
			vy -= acceleration;
		}
		
		if (s) {
			vy += acceleration;
		}
		
		if (a) {
			vx -= acceleration;
		}
		
		if (d) {
			vx += acceleration;
		}
		
		table.getPlayerTwo().setVelocityX(vx);
		table.getPlayerTwo().setVelocityY(vy);
	}
}
