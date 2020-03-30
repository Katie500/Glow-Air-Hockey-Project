
package application;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	protected static Table table;
	protected static boolean up, down, left, right, w, a, s, d;
	protected static DropShadow green_border_glow = new DropShadow();
	protected static DropShadow red_border_glow = new DropShadow();
	protected static Font font;
	protected static boolean menu_finished = false;
	
	private Menu menu = new Menu();
	private Timeline timeline;
	private Pane layout = new Pane();
	private Label p1_score = new Label();
	private Label p2_score = new Label();
	private Label p1 = new Label();
	private Label p2 = new Label();
	private boolean paused = false;
	private Label paused_label = new Label();
	private Label game_over_label = new Label();
	private Label winner = new Label();
	private Scene scene;
	private Scene menu_screen;
	private VBox text_display = new VBox();
	private Button play_again = new Button();
	private boolean new_game = false;
	
	final static int DELAY = 10;
	final static int BASE_VELOCITY = 30;
	
	protected static enum state{
	    MENU,
	    GAME,
	    OVER
	}
	
	public static state game_state = state.MENU;
	
	public static void main(String [] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		createTable();
		createLabels();
		createBorderGlows();
		
		primaryStage.setTitle("Glow Air Hockey");
		layout.setPrefSize(600, 800);
		layout.setStyle("-fx-background-color: BLACK;");
		createBackground();
		
		// Timeline to call the event handler every 10ms to update the table.
		timeline = new Timeline(new KeyFrame(Duration.millis(DELAY), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (Main.game_state == state.MENU) {
					if (menu_finished) {
						table.getPlayerOne().setName(menu.getPlayerOneName());
						table.getPlayerTwo().setName(menu.getPlayerTwoName());
						table.getPlayerOne().setColour(menu.getPlayerOneColour());
						table.getPlayerTwo().setColour(menu.getPlayerTwoColour());
						createLabels();
						primaryStage.setScene(scene);
						Main.game_state = state.GAME;
					}
				}
				
				else if (Main.game_state == state.GAME) {
					boolean goal = false;
					ArrayList<Boolean> goal_data;
					
        			removeScore();
        			
        			if (paused) {
        				if (!text_display.getChildren().contains(paused_label)) {
        					text_display.getChildren().add(paused_label);
        				}
        				
        				if (!layout.getChildren().contains(text_display)) {
        					layout.getChildren().add(text_display);
        				}
        			}
        			
        			else {
        				text_display.getChildren().clear();
        				
        				if (layout.getChildren().contains(text_display)) {
        					layout.getChildren().remove(text_display);
        				}
        				
        				goal_data = updateGame();
    					for (int i = 0; i < goal_data.size(); i++) {
    						if (goal_data.get(i)) {
    							goal = true;
    						}
    					}
    					
	        			Controller.controllerOne();
	        			Controller.controllerTwo();
	        			
	        			if (table.gameOver()) {
	        				Main.game_state = state.OVER;
	        				
							if (table.getPlayerOne().getScore() == table.getPlayerOne().SCORE_TO_WIN) {
								p1.setText("Game Over\n" + table.getPlayerOne().getName() + "\nWon!");
								winner = p1;
							}
							
							else {
								p2.setText("Game Over\n" + table.getPlayerTwo().getName() + "\nWon!");
								winner = p2;
							}
							
							p1_score.setText(table.getPlayerOne().getScore() + "");
							p2_score.setText(table.getPlayerTwo().getScore() + "");
							
							text_display.setSpacing(table.HEIGHT / 10);
							text_display.getChildren().addAll(p1_score, winner, p2_score);
							layout.getChildren().add(text_display);
							
							PauseTransition pause = new PauseTransition(Duration.seconds(5));
	        				pause.setOnFinished(e -> playAgain());
	        				pause.play();
	        			}
	        			
	        			else if (goal) {
	        				displayScore(goal_data.get(0), goal_data.get(1));
	        				PauseTransition pause = new PauseTransition(Duration.seconds(2));
	        				pause.setOnFinished(e -> timeline.play());
	        				timeline.pause();
	        				pause.play();
						}
        			}
				}
				
				else {
					if (new_game) {
						reset();
						new_game = false;
						primaryStage.setScene(new Scene(menu));
					}
				}
	        }
		}));
				
		layout.getChildren().addAll(table.getPuck(), table.getPlayerOne(), table.getPlayerTwo(), table.getPlayerOne().getCenterCircle(), table.getPlayerTwo().getCenterCircle());
		scene = new Scene(layout);
		setControls();
		menu_screen = new Scene(menu);
		
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();
		
		menu.runMenu();
		
		if (game_state == state.MENU) {
			primaryStage.setScene(menu_screen);

		}
		
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public void setControls() {
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
					table.getPlayerOne().setVelocityY(table.getPlayerOne().getVelocityY() * 0.2);
				}
				break;
				
				case DOWN: {
					down = false;
					table.getPlayerOne().setVelocityY(table.getPlayerOne().getVelocityY() * 0.2);
				}
				break;
				
				case LEFT: {
					left = false;
					table.getPlayerOne().setVelocityX(table.getPlayerOne().getVelocityX() * 0.2);
				}
				break;
				
				case RIGHT: {
					right = false;
					table.getPlayerOne().setVelocityX(table.getPlayerOne().getVelocityX() * 0.2);
				}
				break;
				
				case W: {
					w = false;
					table.getPlayerTwo().setVelocityY(table.getPlayerTwo().getVelocityY() * 0.2);
				}
				break;
				
				case S: {
					s = false;
					table.getPlayerTwo().setVelocityY(table.getPlayerTwo().getVelocityY() * 0.2);
				}
				
				break;
				
				case A: {
					a = false;
					table.getPlayerTwo().setVelocityX(table.getPlayerTwo().getVelocityX() * 0.2);
				}
				break;
				
				case D: {
					d = false;
					table.getPlayerTwo().setVelocityX(table.getPlayerTwo().getVelocityX() * 0.2);
				}
				break;
				
				case P: {
					if (paused) {
						paused = false;
					}
					
					else {
						paused = true;
					}
				}
				
				default: break;
				}
			}
		});
	}
	
	public void createTable() {
		String player_one_name = "";
		String player_two_name = "";
		
		// Creating a new table.
		table = new Table(new Player(player_one_name), new Player(player_two_name), new Puck());
		
		// Setting the puck and paddles to their starting positions.
		table.resetPuck();
		table.resetPlayerOne();
		table.resetPlayerTwo();
		
		// Now we have to set the colour for each player's paddle.
		table.getPlayerOne().setColour(table.getPlayerOne().getColour());
		table.getPlayerTwo().setColour(table.getPlayerTwo().getColour());
	}
	
	// This function updates what is necessary with each tick in the timeline.
	public ArrayList<Boolean> updateGame() {
		table.applyFriction(DELAY);
		table.getPuck().updatePuckPosition(DELAY);
		table.updatePaddlePositions(DELAY);
		ArrayList<Boolean> goal_data = table.checkForGoal();
		table.paddleCollision(table.getPlayerOne());
		table.paddleCollision(table.getPlayerTwo());
		table.getPuck().keepPuckIn(table.WIDTH, table.HEIGHT);
		table.keepPaddlesIn();
		return goal_data;
	}
	
	
	public void createBorderGlows() {
		green_border_glow.setColor(Color.CHARTREUSE);
		green_border_glow.setOffsetX(0f);
		green_border_glow.setOffsetY(0f);
		green_border_glow.setWidth(50);
		green_border_glow.setHeight(50);
		
		red_border_glow.setColor(Color.RED);
		red_border_glow.setOffsetX(0f);
		red_border_glow.setOffsetY(0f);
		red_border_glow.setWidth(50);
		red_border_glow.setHeight(50);
	}
	
	public void createBackground() {
		Circle center_circle = new Circle();
		center_circle.setRadius(80);
		center_circle.setFill(Color.RED);
		center_circle.setCenterX(table.CENTER_X);
		center_circle.setCenterY(table.CENTER_Y);
		center_circle.setEffect(red_border_glow);
		
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
		center.setEffect(red_border_glow);
		
		Rectangle left_border = new Rectangle();
		left_border.setX(0);
		left_border.setY(0);
		left_border.setHeight(table.HEIGHT);
		left_border.setWidth(10);
		left_border.setFill(Color.DARKRED);
		left_border.setEffect(red_border_glow);
		
		Rectangle right_border = new Rectangle();
		right_border.setX(table.WIDTH - 10);
		right_border.setY(0);
		right_border.setHeight(800);
		right_border.setWidth(10);
		right_border.setFill(Color.DARKRED);
		right_border.setEffect(red_border_glow);
		
		Rectangle top_border = new Rectangle();
		top_border.setX(0);
		top_border.setY(table.HEIGHT - 10);
		top_border.setHeight(10);
		top_border.setWidth(table.WIDTH);
		top_border.setFill(Color.DARKRED);
		top_border.setEffect(red_border_glow);
		
		Rectangle bottom_border = new Rectangle();
		bottom_border.setX(0);
		bottom_border.setY(0);
		bottom_border.setHeight(10);
		bottom_border.setWidth(table.WIDTH);
		bottom_border.setFill(Color.DARKRED);
		bottom_border.setEffect(red_border_glow);
		
		Rectangle center_line = new Rectangle();
		center_line.setX(0);
		center_line.setY(table.CENTER_Y + -3);
		center_line.setHeight(6);
		center_line.setWidth(table.WIDTH);
		center_line.setFill(Color.RED);
		center_line.setEffect(red_border_glow);
		
		Rectangle player_one_goal = new Rectangle();
		player_one_goal.setX(table.CENTER_X - table.GOAL_SIZE / 2);
		player_one_goal.setY(0);
		player_one_goal.setHeight(10);
		player_one_goal.setWidth(table.GOAL_SIZE);
		player_one_goal.setFill(Color.CHARTREUSE);
		player_one_goal.setEffect(green_border_glow);
		
		Rectangle player_two_goal = new Rectangle();
		player_two_goal.setX(table.CENTER_X - table.GOAL_SIZE / 2);
		player_two_goal.setY(table.HEIGHT - 10);
		player_two_goal.setHeight(10);
		player_two_goal.setWidth(table.GOAL_SIZE);
		player_two_goal.setFill(Color.CHARTREUSE);
		player_two_goal.setEffect(green_border_glow);
		
		layout.getChildren().addAll(center_line, left_border, right_border, top_border, bottom_border, player_one_goal, player_two_goal, center_circle, center_circle_cover, center);
	}
	
	public void createLabels() {
		int font_size = 60;
		Font f = new Font(Font.getDefault().getStyle(), font_size);
		
		p1_score.setText(Integer.toString(table.getPlayerOne().getScore()));
		p1_score.setTextFill(table.getPlayerOne().getColour());
		p1_score.setAlignment(Pos.CENTER);
		p1_score.setTextAlignment(TextAlignment.CENTER);
		p1_score.setEffect(table.getPlayerOne().getBorderGlow());

		p2_score.setText(Integer.toString(table.getPlayerTwo().getScore()));
		p2_score.setTextAlignment(TextAlignment.CENTER);
		p2_score.setAlignment(Pos.CENTER);
		p2_score.setTextFill(table.getPlayerTwo().getColour());
		p2_score.setEffect(table.getPlayerTwo().getBorderGlow());
		
		for (String font_name: Font.getFontNames()) {
			if (font_name.equals("Bauhaus 93")) {
				f = new Font(font_name, font_size * 1.3);
			}
		}
		
		p1_score.setFont(f);
		p2_score.setFont(f);
		
		paused_label.setText("Paused");
		paused_label.setFont(getFont());
		paused_label.setAlignment(Pos.CENTER);
		paused_label.setTextAlignment(TextAlignment.CENTER);
		paused_label.setTextFill(Color.CHARTREUSE);
		paused_label.setEffect(green_border_glow);
		
		game_over_label.setText("Game Over");
		game_over_label.setFont(getFont());
		game_over_label.setTextFill(Color.CHARTREUSE);
		game_over_label.setEffect(green_border_glow);
		
		winner.setFont(getFont());
		winner.setTextFill(Color.CHARTREUSE);
		winner.setAlignment(Pos.CENTER);
		winner.setTextAlignment(TextAlignment.CENTER);
		winner.setEffect(green_border_glow);
		
		p1.setText(table.getPlayerOne().getName() + "\nScored!");
		p1.setFont(getFont());
		p1.setAlignment(Pos.CENTER);
		p1.setTextAlignment(TextAlignment.CENTER);
		p1.setTextFill(Color.CHARTREUSE);
		p1.setEffect(green_border_glow);
		p1.setPrefSize(table.WIDTH, table.HEIGHT / 2);
		
		p2.setText(table.getPlayerTwo().getName() + "\nScored!");
		p2.setFont(getFont());
		p2.setAlignment(Pos.CENTER);
		p2.setTextAlignment(TextAlignment.CENTER);
		p2.setTextFill(Color.CHARTREUSE);
		p2.setEffect(green_border_glow);
		p2.setPrefSize(table.WIDTH,  table.HEIGHT / 2);
		
		text_display.setPrefSize(table.WIDTH,  table.HEIGHT);
		text_display.setAlignment(Pos.CENTER);
		text_display.setLayoutX(0);
		text_display.setLayoutY(0);
		text_display.setSpacing(table.HEIGHT / 10);
		
		play_again.setText("New Game?");
		play_again.setTextFill(Color.CHARTREUSE);
		play_again.setFont(getFont());
		play_again.setPrefSize(table.WIDTH - 20,  table.HEIGHT / 3);
		play_again.setAlignment(Pos.CENTER);
		play_again.setTextAlignment(TextAlignment.CENTER);
	}
	
	public void displayScore(boolean p1_goal, boolean p2_goal) {	
		p1_score.setText(Integer.toString(table.getPlayerOne().getScore()));
		p2_score.setText(Integer.toString(table.getPlayerTwo().getScore()));
		
		if (p1_goal) {
			text_display.getChildren().addAll(p1_score, p1, p2_score);
		}
		
		else if (p2_goal) {
			text_display.getChildren().addAll(p1_score, p2, p2_score);
		}
		
		else {
			text_display.getChildren().addAll(p1_score, p2_score);
		}
		
		layout.getChildren().add(text_display);
	}
	
	public void removeScore() {
		text_display.getChildren().clear();
		
		if (layout.getChildren().contains(text_display)) {
			layout.getChildren().remove(text_display);
		}
	}	
	
	public Font getFont() {
		int font_size = 60;
		Font font = new Font(Font.getDefault().getStyle(), font_size);
		
		for (String f: Font.getFontNames()) {
			if (f.equals("Bauhaus 93")) {
				font = new Font(f, font_size * 1.3);
				p1_score.setLayoutX(p1_score.getLayoutX() - 5);
				p2_score.setLayoutX(p2_score.getLayoutX() - 5);
			}
		}
		
		Main.font = font;
		
		return font;
	}
	
	public void playAgain() {
		timeline.pause();
		play_again.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				new_game = true;
				timeline.play();
			}
		});
		text_display.getChildren().clear();
		text_display.getChildren().add(play_again);
		
		if (!layout.getChildren().contains(text_display)) {
			layout.getChildren().add(text_display);
		}
	}
	
	public void reset() {
		text_display.getChildren().clear();
		layout = new Pane();
		layout.setPrefSize(600, 800);
		layout.setStyle("-fx-background-color: BLACK;");
		createBackground();
		menu = new Menu();
		menu.runMenu();
		createTable();
		createLabels();
		layout.getChildren().addAll(table.getPuck(), table.getPlayerOne(), table.getPlayerTwo(), table.getPlayerOne().getCenterCircle(), table.getPlayerTwo().getCenterCircle());
		scene = new Scene(layout);
		setControls();
		Main.game_state = state.MENU;
		menu_finished = false;
	}
}
