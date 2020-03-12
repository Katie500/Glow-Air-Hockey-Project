
import java.util.Scanner;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group ;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;

public class GlowAirHockeyApp extends Application {
	private Table table;
	
	final static int DELAY = 10;
	
	public static void main(String[] args) {
		Application.launch(args);
		
	}
	
	 public void start(Stage primaryStage) throws Exception {		
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

		// Creating a new table.
		table = new Table(new Player(player_one_name, player_one_colour), new Player(player_two_name, player_two_colour), new Puck(puck_colour));
		
		// Setting the puck and paddles to their starting positions.
		table.resetPuck();
		table.resetPlayerOne();
		table.resetPlayerTwo();
		Group g = new Group();
		Pane root = new Pane();
		root.setPrefSize(table.WIDTH, table.HEIGHT);
		root.setStyle("-fx-background-color: BLACK;");
		
		// Timeline to call the event handler every 10ms to update the table.
		Timeline timeline = new Timeline(
	        new KeyFrame(Duration.millis(DELAY),
	        		
	               new EventHandler <ActionEvent>() {
	        	
	        			@Override
	        			public void handle(ActionEvent event) {
	        				updateGame();
	        			}
				   }
	        )
		) ;
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();
		
		Circle center_circle = new Circle();
		center_circle.setRadius(80);
		center_circle.setFill(Color.RED);
		center_circle.setCenterX(table.CENTER_X);
		center_circle.setCenterY(table.CENTER_Y);
		
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
		
		Rectangle left_border = new Rectangle();
		left_border.setX(0);
		left_border.setY(0);
		left_border.setHeight(table.HEIGHT);
		left_border.setWidth(10);
		left_border.setFill(Color.DARKRED);
		
		Rectangle right_border = new Rectangle();
		right_border.setX(table.WIDTH - 10);
		right_border.setY(0);
		right_border.setHeight(800);
		right_border.setWidth(10);
		right_border.setFill(Color.DARKRED);
		
		Rectangle top_border = new Rectangle();
		top_border.setX(0);
		top_border.setY(table.HEIGHT - 10);
		top_border.setHeight(10);
		top_border.setWidth(table.WIDTH);
		top_border.setFill(Color.DARKRED);
		
		Rectangle bottom_border = new Rectangle();
		bottom_border.setX(0);
		bottom_border.setY(0);
		bottom_border.setHeight(10);
		bottom_border.setWidth(table.WIDTH);
		bottom_border.setFill(Color.DARKRED);
		
		Rectangle center_line = new Rectangle();
		center_line.setX(0);
		center_line.setY(table.CENTER_Y + -3);
		center_line.setHeight(6);
		center_line.setWidth(table.WIDTH);
		center_line.setFill(Color.RED);

		Rectangle player_one_goal = new Rectangle();
		player_one_goal.setX(table.CENTER_X - table.GOAL_SIZE / 2);
		player_one_goal.setY(0);
		player_one_goal.setHeight(10);
		player_one_goal.setWidth(table.GOAL_SIZE);
		player_one_goal.setFill(Color.CHARTREUSE);

		Rectangle player_two_goal = new Rectangle();
		player_two_goal.setX(table.CENTER_X - table.GOAL_SIZE / 2);
		player_two_goal.setY(table.HEIGHT - 10);
		player_two_goal.setHeight(10);
		player_two_goal.setWidth(table.GOAL_SIZE);
		player_two_goal.setFill(Color.CHARTREUSE);


		root.getChildren().addAll(center_line, left_border, right_border, top_border, bottom_border, player_one_goal, player_two_goal, center_circle, center_circle_cover, center);
		root.getChildren().add(table.getPuck());
		root.getChildren().add(table.getPlayerOne());
		root.getChildren().add(table.getPlayerTwo());
		
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Glow Air Hockey");
		primaryStage.show();
	   }

	// This function updates what is necessary with each tick in the timeline.
	public void updateGame() {
		table.applyFriction(DELAY);
		table.updatePuckPosition(DELAY);
		table.updatePaddlePositions(DELAY);
		table.checkForGoal();
		table.paddleCollision(table.getPlayerOne(), DELAY);
		table.paddleCollision(table.getPlayerTwo(), DELAY);
		table.keepPuckIn();
		table.keepPaddlesIn();
	}
}
