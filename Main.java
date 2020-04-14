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






//setting the main class as an extension of the Application class
public class Main extends Application {
	protected static Table table;
	protected static boolean up, down, left, right, w, a, s, d;
//	protected static DropShadow green_border_glow = new DropShadow();
//	protected static DropShadow red_border_glow = new DropShadow();
	protected static Font font;
	public static boolean menu_finished = false;         // Necessary to access from gui package
	
	private Menu menu = new Menu();
	private Rink rink = new Rink();
	private Timeline timeline;
	private Pane layout = new Pane();
	private Label p1_score = new Label();
	private Label p2_score = new Label();
	private Label p1 = new Label();
	private Label p2 = new Label();
	protected static boolean paused = false;
	private Label paused_label = new Label();
	private Label game_over_label = new Label();
	private Label winner = new Label();
	protected static Scene scene;
	private Scene menu_screen;
	public static Scene rink_screen;
	private VBox text_display = new VBox();
	private Button play_again = new Button();
	private boolean new_game = false;
	
	final static int DELAY = 10;
	final static int BASE_VELOCITY = 30;
	
	public static enum state{
	    MENU,
	    GAME,
	    OVER
	}
	//setting the first game state to menu
	public static state game_state = state.MENU;
	
	public static void main(String [] args) {        //Pretty sure this is not necessary 
		launch(args);
	}
	
	//setting up the start menu
	@Override
	public void start(Stage primaryStage) {
		createTable();
		createLabels();
		GameScreen.createBorderGlows();
		
		//setting the title, size, and background for the primary stage
		primaryStage.setTitle("Glow Air Hockey");
		layout.setPrefSize(Rink.WIDTH, Rink.HEIGHT);
		layout.setStyle("-fx-background-color: BLACK;");
//		Rink.setScreen();
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
						createLabels();
						primaryStage.setScene(rink_screen);
						Controller.setControls();
						Main.game_state = state.GAME;
					}
				}
				
				//checking the game state to see if it is the state of the game
				else if (Main.game_state == state.GAME) {
					boolean goal = false;
					ArrayList<Boolean> goal_data;
					
					//removes the score from the screen after the player has scored
        			removeScore();
        			
        			//if the player pauses the game, the label shows that it is paused
        			if (paused) {
        				if (!text_display.getChildren().contains(paused_label)) {
        					text_display.getChildren().add(paused_label);

        				}
        				
        				if (!rink.getChildren().contains(text_display)) {
        					rink.getChildren().add(text_display);

        				}
        			}
        			
        			else {
        				text_display.getChildren().clear();
        				rink.getChildren().remove(layout);
        				
        				if (layout.getChildren().contains(text_display)) {
        					layout.getChildren().remove(text_display);
        					rink.getChildren().remove(layout);
        				}
        				
        				goal_data = updateGame();
    					for (int i = 0; i < goal_data.size(); i++) {
    						if (goal_data.get(i)) {
    							goal = true;
    						}
    					}
    					
	        			Controller.controllerOne();
	        			Controller.controllerTwo();
	        			
	        			
	        			//if the game is over, change the main game state
	        			if (table.gameOver()) {
	        				Main.game_state = state.OVER;
	        				
	        				
	        				//we check if player one won and if so, we send a message to the screen
                            //that says who one and set winner equal to player one
							if (table.getPlayerOne().getScore() == table.getPlayerOne().SCORE_TO_WIN) {
								p1.setText("Game Over\n" + table.getPlayerOne().getName() + "\nWon!");
								winner = p1;
							}
							
							//if player one didn't win, we sent a message to the screen saying that player 
                            //two won and set winner equal to player two
							else {
								p2.setText("Game Over\n" + table.getPlayerTwo().getName() + "\nWon!");
								winner = p2;
							}
							
							//getting the scores for both players
							p1_score.setText(table.getPlayerOne().getScore() + "");
							p2_score.setText(table.getPlayerTwo().getScore() + "");
							
							//displaying the winner on the screen
							text_display.setSpacing(table.HEIGHT / 10);
							text_display.getChildren().addAll(p1_score, winner, p2_score);
							layout.getChildren().add(text_display);
							rink.getChildren().add(layout);
							
							//asking if user wants to play again after the game over message has been displayed for a while. 
							PauseTransition pause = new PauseTransition(Duration.seconds(5));
	        				pause.setOnFinished(e -> playAgain());
	        				pause.play();
	        			}
	        			
	        			//if new goal, show scores 
	        			else if (goal) {
	        				displayScore(goal_data.get(0), goal_data.get(1));
	        				PauseTransition pause = new PauseTransition(Duration.seconds(2));
	        				pause.setOnFinished(e -> timeline.play());
	        				timeline.pause();
	        				pause.play();
						}
        			}
				}
				
				//setting up for new game
				else {
					if (new_game) {
						reset();
						new_game = false;
						primaryStage.setScene(new Scene(menu));
					}
				}
	        }
		}));
				
		
		//getting the elements needed for the game
		rink.getChildren().addAll(table.getPuck(), table.getPlayerOne(), table.getPlayerTwo(), table.getPlayerOne().getCenterCircle(), table.getPlayerTwo().getCenterCircle());
		
		//setting menu screen
		menu_screen = new Scene(menu);
		//setting timeline
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();
		//running menu
		menu.runMenu();
		//setting primary stage if game state is menu
		if (game_state == state.MENU) {
			primaryStage.setScene(menu_screen);

		}
		//setting size for primary stage
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	//creating the table for the game
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
	
	

	//creating elements for score, game over and play again button.
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
		//setting font of scores that show up when a player scores or the game is over. 
		p1_score.setFont(f);
		p2_score.setFont(f);
		
		//setting up pause screen text
		paused_label.setText("Paused");
		paused_label.setFont(getFont());
		paused_label.setAlignment(Pos.CENTER);
		paused_label.setTextAlignment(TextAlignment.CENTER);
		paused_label.setTextFill(Color.CHARTREUSE);
		paused_label.setEffect(GameScreen.getGreen_border_glow());
		
		//setting up game over text
		game_over_label.setText("Game Over");
		game_over_label.setFont(getFont());
		game_over_label.setTextFill(Color.CHARTREUSE);
		game_over_label.setEffect(GameScreen.getGreen_border_glow());
		
		//setting up game winner message
		winner.setFont(getFont());
		winner.setTextFill(Color.CHARTREUSE);
		winner.setAlignment(Pos.CENTER);
		winner.setTextAlignment(TextAlignment.CENTER);
		winner.setEffect(GameScreen.getGreen_border_glow());
		
		//setting the player one scored font
		p1.setText(table.getPlayerOne().getName() + "\nScored!");
		p1.setFont(getFont());
		p1.setAlignment(Pos.CENTER);
		p1.setTextAlignment(TextAlignment.CENTER);
		p1.setTextFill(Color.CHARTREUSE);
		p1.setEffect(GameScreen.getGreen_border_glow());
		p1.setPrefSize(table.WIDTH, table.HEIGHT / 2);
		
		//setting the player two scored font
		p2.setText(table.getPlayerTwo().getName() + "\nScored!");
		p2.setFont(getFont());
		p2.setAlignment(Pos.CENTER);
		p2.setTextAlignment(TextAlignment.CENTER);
		p2.setTextFill(Color.CHARTREUSE);
		p2.setEffect(GameScreen.getGreen_border_glow());
		p2.setPrefSize(table.WIDTH,  table.HEIGHT / 2);
		
		//setting table display
		text_display.setPrefSize(table.WIDTH,  table.HEIGHT);
		text_display.setAlignment(Pos.CENTER);
		text_display.setLayoutX(0);
		text_display.setLayoutY(0);
		text_display.setSpacing(table.HEIGHT / 10);
		
		//setting the new game text
		play_again.setText("New Game?");
		play_again.setTextFill(Color.CHARTREUSE);
		play_again.setFont(getFont());
		play_again.setPrefSize(table.WIDTH - 20,  table.HEIGHT / 3);
		play_again.setAlignment(Pos.CENTER);
		play_again.setTextAlignment(TextAlignment.CENTER);
	}
	
	//method to display the score
	public void displayScore(boolean p1_goal, boolean p2_goal) {	
		p1_score.setText(Integer.toString(table.getPlayerOne().getScore()));
		p2_score.setText(Integer.toString(table.getPlayerTwo().getScore()));
		
		// if a certain player scores display their name and both new scores
		if (p1_goal) {
			text_display.getChildren().addAll(p1_score, p1, p2_score);
			rink.getChildren().add(text_display);
		}
		
		else if (p2_goal) {
			text_display.getChildren().addAll(p1_score, p2, p2_score);
			rink.getChildren().add(text_display);
		}
		
		else {
			text_display.getChildren().addAll(p1_score, p2_score);
			rink.getChildren().add(text_display);
		}
		
		layout.getChildren().add(text_display);
		rink.getChildren().add(text_display);
	}
	
	//clearing the score from the the display
	public void removeScore() {
		text_display.getChildren().clear();
		
		if (rink.getChildren().contains(text_display)) {
			rink.getChildren().remove(text_display);
			
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
	
	//building button displayed after game over to play again. 
	public void playAgain() {
		timeline.pause();
		play_again.setOnAction(new EventHandler<ActionEvent>() {
			//handling of play again button click 
		    @Override
			public void handle(ActionEvent e) {
				new_game = true;
				timeline.play();
				rink.getChildren().clear();
				reset();
			}
		});
		//clearing the display to play again
		text_display.getChildren().clear();
		text_display.getChildren().add(play_again);
//		rink.getChildren().add(layout);		
		if (!layout.getChildren().contains(text_display)) {
			layout.getChildren().add(text_display);
			rink.getChildren().add(layout);
		}
	}
	
	//method to reset the game and begin again from the menu
	public void reset() {
		text_display.getChildren().clear();
		layout = new Pane();
		layout.setPrefSize(600, 800);
		layout.setStyle("-fx-background-color: BLACK;");
		rink.runRink();
		menu = new Menu();
		menu.runMenu();
		createTable();
		createLabels();
		layout.getChildren().addAll(table.getPuck(), table.getPlayerOne(), table.getPlayerTwo(), table.getPlayerOne().getCenterCircle(), table.getPlayerTwo().getCenterCircle());
		rink.getChildren().add(layout);
		scene = new Scene(layout);
		Controller.setControls();
		Main.game_state = state.MENU;
		menu_finished = false;
	}
}
