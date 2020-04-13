package application;

import java.util.ArrayList;

import gui.GameScreen;
import gui.Menu;
import gui.Rink;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
	public static Table table;
	protected static boolean up, down, left, right, w, a, s, d;
	protected static DropShadow green_border_glow = new DropShadow();
	protected static DropShadow red_border_glow = new DropShadow();
	protected static Font font;
	public static boolean menu_finished = false;         // Necessary to access from gui package
	
	private Menu menu = new Menu();
	private Rink rink = new Rink();
	public static Timeline timeline;
//	private Pane layout = new Pane();
//	private Label p1_score = new Label();
//	private Label p2_score = new Label();
//	private Label p1 = new Label();
//	private Label p2 = new Label();
	protected static boolean paused = false;
//	private Label paused_label = new Label();
//	private Label game_over_label = new Label();
//	private Label winner = new Label();
	protected static Scene scene;
	private Scene menu_screen;
	public static Scene rink_screen;
//	private VBox text_display = new VBox();
//	private Button play_again = new Button();
	public static boolean new_game = false;
	
	final static int DELAY = 10;
	final static int BASE_VELOCITY = 30;
	
	public static enum state{
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
		rink.createLabels();
		GameScreen.createBorderGlows();
		
		primaryStage.setTitle("Glow Air Hockey");
//		layout.setPrefSize(Rink.WIDTH, Rink.HEIGHT);
//		layout.setStyle("-fx-background-color: BLACK;");
		rink.runRink();
		rink_screen = new Scene(rink);
				
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
						rink.createLabels();
						primaryStage.setScene(rink_screen);
						Controller.setControls();
						Main.game_state = state.GAME;
					}
				}
				
				else if (Main.game_state == state.GAME) {
					boolean goal = false;
					ArrayList<Boolean> goal_data;
					
        			rink.removeScore();
        			
        			if (paused) {
        				rink.showPausedLabel();
        			}
        			
        			else {
        				rink.hidePausedLabel();
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
								rink.winnerLabel();
							}
							
							else {
								rink.winnerLabel();
							}
							
//							p1_score.setText(table.getPlayerOne().getScore() + "");
//							p2_score.setText(table.getPlayerTwo().getScore() + "");
//							
//							text_display.setSpacing(table.HEIGHT / 10);
//							text_display.getChildren().addAll(p1_score, winner, p2_score);
//							layout.getChildren().add(text_display);
							
							PauseTransition pause = new PauseTransition(Duration.seconds(5));
	        				pause.setOnFinished(e -> Rink.playAgainButton());
	        				pause.play();
	        			}
	        			
	        			else if (goal) {
	        				rink.displayScore(goal_data.get(0), goal_data.get(1));
	        				PauseTransition pause = new PauseTransition(Duration.seconds(2));
	        				pause.setOnFinished(e -> timeline.play());
	        				timeline.pause();
	        				pause.play();
						}
        			}
				}
				
//				else if (new_game)  {
//						reset();
//						new_game = false;
//						primaryStage.setScene(new Scene(menu));
//					}
//				}
	        
		}));
				
		
		
		rink.getChildren().addAll(table.getPuck(), table.getPlayerOne(), table.getPlayerTwo(), table.getPlayerOne().getCenterCircle(), table.getPlayerTwo().getCenterCircle());
		//scene = new Scene(layout);
		//Controller.setControls();
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
	
	
	public static void createTable() {
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
	
	
	public ArrayList<Boolean> updateGame() {                    // This function updates what is necessary with each tick in the timeline.
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
	

	
//	public void createLabels() {
//		int font_size = 60;
//		Font f = new Font(Font.getDefault().getStyle(), font_size);
//		
//		p1_score.setText(Integer.toString(table.getPlayerOne().getScore()));
//		p1_score.setTextFill(table.getPlayerOne().getColour());
//		p1_score.setAlignment(Pos.CENTER);
//		p1_score.setTextAlignment(TextAlignment.CENTER);
//		p1_score.setEffect(table.getPlayerOne().getBorderGlow());
//
//		p2_score.setText(Integer.toString(table.getPlayerTwo().getScore()));
//		p2_score.setTextAlignment(TextAlignment.CENTER);
//		p2_score.setAlignment(Pos.CENTER);
//		p2_score.setTextFill(table.getPlayerTwo().getColour());
//		p2_score.setEffect(table.getPlayerTwo().getBorderGlow());
//		
//		for (String font_name: Font.getFontNames()) {
//			if (font_name.equals("Bauhaus 93")) {
//				f = new Font(font_name, font_size * 1.3);
//			}
//		}
//		
//		p1_score.setFont(f);
//		p2_score.setFont(f);
//		
//		paused_label.setText("Paused");
//		paused_label.setFont(getFont());
//		paused_label.setAlignment(Pos.CENTER);
//		paused_label.setTextAlignment(TextAlignment.CENTER);
//		paused_label.setTextFill(Color.CHARTREUSE);
//		paused_label.setEffect(green_border_glow);
//		
//		game_over_label.setText("Game Over");
//		game_over_label.setFont(getFont());
//		game_over_label.setTextFill(Color.CHARTREUSE);
//		game_over_label.setEffect(green_border_glow);
//		
//		winner.setFont(getFont());
//		winner.setTextFill(Color.CHARTREUSE);
//		winner.setAlignment(Pos.CENTER);
//		winner.setTextAlignment(TextAlignment.CENTER);
//		winner.setEffect(green_border_glow);
//		
//		p1.setText(table.getPlayerOne().getName() + "\nScored!");
//		p1.setFont(getFont());
//		p1.setAlignment(Pos.CENTER);
//		p1.setTextAlignment(TextAlignment.CENTER);
//		p1.setTextFill(Color.CHARTREUSE);
//		p1.setEffect(green_border_glow);
//		p1.setPrefSize(table.WIDTH, table.HEIGHT / 2);
//		
//		p2.setText(table.getPlayerTwo().getName() + "\nScored!");
//		p2.setFont(getFont());
//		p2.setAlignment(Pos.CENTER);
//		p2.setTextAlignment(TextAlignment.CENTER);
//		p2.setTextFill(Color.CHARTREUSE);
//		p2.setEffect(green_border_glow);
//		p2.setPrefSize(table.WIDTH,  table.HEIGHT / 2);
//		
//		text_display.setPrefSize(table.WIDTH,  table.HEIGHT);
//		text_display.setAlignment(Pos.CENTER);
//		text_display.setLayoutX(0);
//		text_display.setLayoutY(0);
//		text_display.setSpacing(table.HEIGHT / 10);
//		
//		play_again.setText("New Game?");
//		play_again.setTextFill(Color.CHARTREUSE);
//		play_again.setFont(getFont());
//		play_again.setPrefSize(table.WIDTH - 20,  table.HEIGHT / 3);
//		play_again.setAlignment(Pos.CENTER);
//		play_again.setTextAlignment(TextAlignment.CENTER);
//	}
//	
//	public void displayScore(boolean p1_goal, boolean p2_goal) {	
//		p1_score.setText(Integer.toString(table.getPlayerOne().getScore()));
//		p2_score.setText(Integer.toString(table.getPlayerTwo().getScore()));
//		
//		if (p1_goal) {
//			text_display.getChildren().addAll(p1_score, p1, p2_score);
//		}
//		
//		else if (p2_goal) {
//			text_display.getChildren().addAll(p1_score, p2, p2_score);
//		}
//		
//		else {
//			text_display.getChildren().addAll(p1_score, p2_score);
//		}
//		
//		layout.getChildren().add(text_display);
//	}
//	
//	public void removeScore() {
//		text_display.getChildren().clear();
//		
//		if (layout.getChildren().contains(text_display)) {
//			layout.getChildren().remove(text_display);
//		}
//	}	
	
	public static Font getFont() {
		int font_size = 60;
		Font font = new Font(Font.getDefault().getStyle(), font_size);
		
		for (String f: Font.getFontNames()) {
			if (f.equals("Bauhaus 93")) {
				font = new Font(f, font_size * 1.3);
				Rink.p1_score.setLayoutX(Rink.p1_score.getLayoutX() - 5);
				Rink.p2_score.setLayoutX(Rink.p2_score.getLayoutX() - 5);
			}
		}
		
		Main.font = font;
		
		return font;
	}
	
//	public void playAgain() {
//		timeline.pause();
//		play_again.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent e) {
//				new_game = true;
//				timeline.play();
//			}
//		});
//		text_display.getChildren().clear();
//		text_display.getChildren().add(play_again);
//		
//		if (!layout.getChildren().contains(text_display)) {
//			layout.getChildren().add(text_display);
//		}
//	}
	
//	public void reset() {
//		text_display.getChildren().clear();
//		layout = new Pane();
//		layout.setPrefSize(600, 800);
//		layout.setStyle("-fx-background-color: BLACK;");
//		rink.runRink();
//		menu = new Menu();
//		menu.runMenu();
//		createTable();
//		createLabels();
//		layout.getChildren().addAll(table.getPuck(), table.getPlayerOne(), table.getPlayerTwo(), table.getPlayerOne().getCenterCircle(), table.getPlayerTwo().getCenterCircle());
//		scene = new Scene(layout);
//		Controller.setControls();
//		Main.game_state = state.MENU;
//		menu_finished = false;
//	}
}
