package gui;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import application.Controller;
import application.Main;
import application.Main.state;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;

public class Menu extends GameScreen{
	private static Button play = new Button();
	private static Button controls = new Button();
	private static Button display_box = new Button();
	private static Button input = new Button();
	private static Label info = new Label();
	private static Label play_requirements = new Label();
	private static VBox input_box = new VBox();
	private static HBox p1_input_box = new HBox();
	private static HBox p2_input_box = new HBox();
	private static TextField p1_name = new TextField();
	private static TextField p1_colour = new TextField();
	private static TextField p2_name = new TextField();
	private static TextField p2_colour = new TextField();
	private static String player_one_name = "";
	private static String player_two_name = "";
	private static Color player_one_colour;
	private static Color player_two_colour;
//	protected DropShadow green_border_glow = new DropShadow();
//	protected DropShadow red_border_glow = new DropShadow();
	protected static Font font = new Font("bauhaus 93", 45);
	

	
	public Menu() {
		setPrefSize(WIDTH, HEIGHT);
		setStyle("-fx-background-color: BLACK;");
	}
	
	public void runMenu() {
		setScreen();
		setButtons();
		getChildren().addAll(play, controls, display_box, input, input_box);
	}
	
	public static void setScreen() {
		int padding = 15;
		double button_width = WIDTH - 2 * padding;
		double button_height = (HEIGHT - 5 * padding) / 4;
		
		play.setPrefSize(button_width,  button_height);
		play.setLayoutX(padding);
		play.setLayoutY(padding);
		play.setText("Play");
		play.setAlignment(Pos.CENTER);
		play.setTextFill(Color.CHARTREUSE);
		play.setFont(font);
		play.setEffect(green_border_glow);
		
		double controls_y = HEIGHT - button_height - padding;
		
		controls.setText("Controls");
		controls.setFont(font);
		controls.setAlignment(Pos.CENTER);
		controls.setTextFill(Color.CHARTREUSE);
		controls.setEffect(green_border_glow);
		controls.setPrefSize(button_width, button_height);
		controls.setLayoutX(padding);
		controls.setLayoutY(controls_y);
		
		double display_box_y = HEIGHT - 2 * button_height - 2 * padding;
		
		display_box.setPrefSize(button_width,  button_height);
		display_box.setLayoutX(padding);
		display_box.setLayoutY(display_box_y);
		
		info.setFont(new Font(font.getName(), 15));
		info.setAlignment(Pos.CENTER);
		info.setTextAlignment(TextAlignment.CENTER);
		info.setTextFill(Color.BLACK);
		info.setLayoutX(padding);
		info.setLayoutY(display_box_y);
		info.setPrefSize(button_width, button_height);
		info.setText("Use the arrow keys for player one (top of the screen).\nUse WASD for player two (bottom of the screen).\nThe game is over when one player reaches seven points.\nPress P to pause and unpause the game."); 
		
		play_requirements.setFont(new Font(font.getName(), 15));
		play_requirements.setAlignment(Pos.CENTER);
		play_requirements.setTextAlignment(TextAlignment.CENTER);
		play_requirements.setTextFill(Color.BLACK);
		play_requirements.setLayoutX(padding);
		play_requirements.setLayoutY(display_box_y);
		play_requirements.setPrefSize(button_width,  button_height);
		play_requirements.setText("Usernames must be between 1 and 10 characters in length (inclusive).\nPlayer colours must be valid.\nValid entries will be highlighted in green.\nInvalid entries will be highighted in red.");
		
		double input_box_y = button_height + 2 * padding;
		Label player_one_name = new Label("Player One Name: ");
		Label player_one_colour = new Label("Player One Colour: ");
		Label player_two_name = new Label("Player Two Name: ");
		Label player_two_colour = new Label("Player Two Colour: ");
		
		input.setPrefSize(button_width, button_height);
		input.setLayoutX(padding);
		input.setLayoutY(input_box_y);
		
		p1_input_box.setPrefSize(button_width / 2,  button_height / 2);
		p1_input_box.setSpacing(10);
		p1_input_box.getChildren().addAll(player_one_name, p1_name, player_one_colour, p1_colour);
		p1_input_box.setAlignment(Pos.CENTER);
		
		p2_input_box.setPrefSize(button_width / 2, button_height / 2);
		p2_input_box.setSpacing(10);
		p2_input_box.getChildren().addAll(player_two_name, p2_name, player_two_colour, p2_colour);
		p2_input_box.setAlignment(Pos.CENTER);
		
		input_box.setPrefSize(button_width,  button_height);
		input_box.setLayoutX(padding);
		input_box.setLayoutY(input_box_y);
		input_box.getChildren().addAll(p1_input_box, p2_input_box);
		input_box.setAlignment(Pos.CENTER);
	}
	
	public void setButtons() {
		controls.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				displayControls();
			}
		});
		
		play.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				play();
			}
		});
	}
	
	public void displayControls() {
		if (getChildren().contains(info)) {
			controls.setEffect(green_border_glow);
			controls.setTextFill(Color.CHARTREUSE);
			getChildren().remove(info);
		}
		
		else {
			controls.setEffect(red_border_glow);
			controls.setTextFill(Color.RED);
			getChildren().add(info);
		}
	
		play.setTextFill(Color.CHARTREUSE);
		play.setEffect(green_border_glow);
		if (getChildren().contains(play_requirements)) {
			getChildren().remove(play_requirements);
		}
	}
	
	public void play() {
		if (inputIsValid()) {
			Main.menu_finished = true;                                                            
		}
		
		else {
			if (getChildren().contains(info)) {
				displayControls();
			}
			
			if(!getChildren().contains(play_requirements)) {
				getChildren().add(play_requirements);
			}
			play.setTextFill(Color.RED);
			play.setEffect(red_border_glow);
			controls.setTextFill(Color.CHARTREUSE);
			controls.setEffect(green_border_glow);
		}
	}
	
	public boolean inputIsValid() {
		boolean valid = true;
		player_one_name = p1_name.getText();
		player_two_name = p2_name.getText();
		String c1 = p1_colour.getText();
		String c2 = p2_colour.getText();
		
		if (player_one_name.length() < 1 | player_one_name.length() > 10 | player_one_name == null) {
			valid = false;
			p1_name.setEffect(red_border_glow);
		}
		
		else {
			p1_name.setEffect(green_border_glow);
		}
		
		if (player_two_name.length() < 1 | player_two_name.length() > 10 | player_two_name == null) {
			valid = false;
			p2_name.setEffect(red_border_glow);
		}
		
		else {
			p2_name.setEffect(green_border_glow);
		}
		
		try {
			player_one_colour = Color.valueOf(c1);
			p1_colour.setEffect(green_border_glow);
		}
		
		catch (Exception e) {
			p1_colour.setEffect(red_border_glow);
			valid = false;
		}
		
		try {
			player_two_colour = Color.valueOf(c2);
			p2_colour.setEffect(green_border_glow);
		}
		
		catch (Exception e) {
			p2_colour.setEffect(red_border_glow);
			valid = false;
		}
		
		return valid;
	}
	
	public String getPlayerOneName() {
		return player_one_name;
	}
	
	public String getPlayerTwoName() {
		return player_two_name;
	}
	
	public Color getPlayerOneColour() {
		return player_one_colour;
	}
	
	public Color getPlayerTwoColour() {
		return player_two_colour;
	}

	

}
