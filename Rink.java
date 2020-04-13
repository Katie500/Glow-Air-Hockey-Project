package gui;

import application.Controller;
import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Rink extends GameScreen{

    private static Circle center_circle = new Circle();
    private static Circle center_circle_cover = new Circle();
    private static Circle center = new Circle();
    private static Rectangle left_border = new Rectangle();
    private static Rectangle right_border = new Rectangle();
    private static Rectangle top_border = new Rectangle();
    private static Rectangle bottom_border = new Rectangle();
    private static Rectangle center_line = new Rectangle();
    private static Rectangle player_one_goal = new Rectangle();
    private static Rectangle player_two_goal = new Rectangle();
    
    private static Pane layout = new Pane();
    
    public static Label p1_score = new Label();
    public static Label p2_score = new Label();
    private Label p1 = new Label();
    private Label p2 = new Label();
    
    private  Label paused_label = new Label();
    private  Label game_over_label = new Label();
    private  Label winner = new Label();
    
    private static  VBox text_display = new VBox();
    private static  Button play_again = new Button();
    
    //public static Pane layout = new Pane();
    
    
    public Rink() {
        setStyle("-fx-background-color: BLACK;");
        setPrefSize(WIDTH, HEIGHT);
    }
    
    public void runRink() {
        setScreen();
        getChildren().addAll(center_line, left_border, right_border, top_border, bottom_border, player_one_goal, player_two_goal, center_circle, center_circle_cover, center);
    }
    
    @Override
    public void setScreen() {
        
        center_circle.setRadius(80);
        center_circle.setFill(Color.RED);
        center_circle.setCenterX(GameScreen.CENTER_X);
        center_circle.setCenterY(GameScreen.CENTER_Y);
        center_circle.setEffect(red_border_glow);
        
        
        center_circle_cover.setRadius(75);
        center_circle_cover.setFill(Color.BLACK);
        center_circle_cover.setCenterX(GameScreen.CENTER_X);
        center_circle_cover.setCenterY(GameScreen.CENTER_Y);
        
        
        center.setRadius(5);
        center.setFill(Color.RED);
        center.setCenterX(GameScreen.CENTER_X);
        center.setCenterY(GameScreen.CENTER_Y);
        center.setEffect(GameScreen.red_border_glow);
        
        
        left_border.setX(0);
        left_border.setY(0);
        left_border.setHeight(GameScreen.HEIGHT);
        left_border.setWidth(10);
        left_border.setFill(Color.DARKRED);
        left_border.setEffect(GameScreen.red_border_glow);
        
        
        right_border.setX(GameScreen.WIDTH - 10);
        right_border.setY(0);
        right_border.setHeight(800);
        right_border.setWidth(10);
        right_border.setFill(Color.DARKRED);
        right_border.setEffect(GameScreen.red_border_glow);
        
        
        top_border.setX(0);
        top_border.setY(GameScreen.HEIGHT - 10);
        top_border.setHeight(10);
        top_border.setWidth(GameScreen.WIDTH);
        top_border.setFill(Color.DARKRED);
        top_border.setEffect(GameScreen.red_border_glow);
        
        
        bottom_border.setX(0);
        bottom_border.setY(0);
        bottom_border.setHeight(10);
        bottom_border.setWidth(GameScreen.WIDTH);
        bottom_border.setFill(Color.DARKRED);
        bottom_border.setEffect(GameScreen.red_border_glow);
        
        
        center_line.setX(0);
        center_line.setY(GameScreen.CENTER_Y + -3);
        center_line.setHeight(6);
        center_line.setWidth(GameScreen.WIDTH);
        center_line.setFill(Color.RED);
        center_line.setEffect(GameScreen.red_border_glow);
        
        
        player_one_goal.setX(GameScreen.CENTER_X - GameScreen.GOAL_SIZE / 2);
        player_one_goal.setY(0);
        player_one_goal.setHeight(10);
        player_one_goal.setWidth(GameScreen.GOAL_SIZE);
        player_one_goal.setFill(Color.CHARTREUSE);
        player_one_goal.setEffect(GameScreen.green_border_glow);
        
        
        player_two_goal.setX(GameScreen.CENTER_X - GameScreen.GOAL_SIZE / 2);
        player_two_goal.setY(GameScreen.HEIGHT - 10);
        player_two_goal.setHeight(10);
        player_two_goal.setWidth(GameScreen.GOAL_SIZE);
        player_two_goal.setFill(Color.CHARTREUSE);
        player_two_goal.setEffect(GameScreen.green_border_glow);
        
        

    }  
 
    public  void createLabels() {
        int font_size = 60;
        Font f = new Font(Font.getDefault().getStyle(), font_size);
        
        p1_score.setText(Integer.toString(Main.table.getPlayerOne().getScore()));
        p1_score.setTextFill(Main.table.getPlayerOne().getColour());
        p1_score.setAlignment(Pos.CENTER);
        p1_score.setTextAlignment(TextAlignment.CENTER);
        p1_score.setEffect(Main.table.getPlayerOne().getBorderGlow());

        p2_score.setText(Integer.toString(Main.table.getPlayerTwo().getScore()));
        p2_score.setTextAlignment(TextAlignment.CENTER);
        p2_score.setAlignment(Pos.CENTER);
        p2_score.setTextFill(Main.table.getPlayerTwo().getColour());
        p2_score.setEffect(Main.table.getPlayerTwo().getBorderGlow());
        
        for (String font_name: Font.getFontNames()) {
            if (font_name.equals("Bauhaus 93")) {
                f = new Font(font_name, font_size * 1.3);
            }
        }
        
        p1_score.setFont(f);
        p2_score.setFont(f);
        
        paused_label.setText("Paused");
        paused_label.setFont(Main.getFont());
        paused_label.setAlignment(Pos.CENTER);
        paused_label.setTextAlignment(TextAlignment.CENTER);
        paused_label.setTextFill(Color.CHARTREUSE);
        paused_label.setEffect(green_border_glow);
        
        game_over_label.setText("Game Over");
        game_over_label.setFont(Main.getFont());
        game_over_label.setTextFill(Color.CHARTREUSE);
        game_over_label.setEffect(green_border_glow);
        
        winner.setFont(Main.getFont());
        winner.setTextFill(Color.CHARTREUSE);
        winner.setAlignment(Pos.CENTER);
        winner.setTextAlignment(TextAlignment.CENTER);
        winner.setEffect(green_border_glow);
        
        p1.setText(Main.table.getPlayerOne().getName() + "\nScored!");
        p1.setFont(Main.getFont());
        p1.setAlignment(Pos.CENTER);
        p1.setTextAlignment(TextAlignment.CENTER);
        p1.setTextFill(Color.CHARTREUSE);
        p1.setEffect(green_border_glow);
        p1.setPrefSize(WIDTH, HEIGHT / 2);
        
        p2.setText(Main.table.getPlayerTwo().getName() + "\nScored!");
        p2.setFont(Main.getFont());
        p2.setAlignment(Pos.CENTER);
        p2.setTextAlignment(TextAlignment.CENTER);
        p2.setTextFill(Color.CHARTREUSE);
        p2.setEffect(green_border_glow);
        p2.setPrefSize(WIDTH,  HEIGHT / 2);
        
        text_display.setPrefSize(WIDTH,  HEIGHT);
        text_display.setAlignment(Pos.CENTER);
        text_display.setLayoutX(0);
        text_display.setLayoutY(0);
        text_display.setSpacing(HEIGHT / 10);
        
        play_again.setText("New Game?");
        play_again.setTextFill(Color.CHARTREUSE);
        play_again.setFont(Main.getFont());
        play_again.setPrefSize(WIDTH - 20,  HEIGHT / 3);
        play_again.setAlignment(Pos.CENTER);
        play_again.setTextAlignment(TextAlignment.CENTER);
    
        layout.setPrefSize(Rink.WIDTH, Rink.HEIGHT);
        layout.setStyle("-fx-background-color: BLACK;");
    }

    
    public void displayScore(boolean p1_goal, boolean p2_goal) {    
        p1_score.setText(Integer.toString(Main.table.getPlayerOne().getScore()));
        p2_score.setText(Integer.toString(Main.table.getPlayerTwo().getScore()));
        
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

    public void winnerLabel() {
        if (Main.table.getPlayerOne().getScore() == Main.table.getPlayerOne().SCORE_TO_WIN) {
            p1.setText("Game Over\n" + Main.table.getPlayerOne().getName() + "\nWon!");
            winner = p1;
        }
        
        else {
            p2.setText("Game Over\n" + Main.table.getPlayerTwo().getName() + "\nWon!");
            winner = p2;
        }
        p1_score.setText(Main.table.getPlayerOne().getScore() + "");
        p2_score.setText(Main.table.getPlayerTwo().getScore() + "");
        
        text_display.setSpacing(HEIGHT / 10);
        text_display.getChildren().addAll(p1_score, winner, p2_score);
        layout.getChildren().add(text_display);
    }

    public void showPausedLabel() {
        text_display.getChildren().add(paused_label);
        if (!layout.getChildren().contains(text_display)) {
            layout.getChildren().add(text_display);
        }
    }
    public void hidePausedLabel() {
        text_display.getChildren().remove(paused_label);
        if (!layout.getChildren().contains(text_display)) {
            layout.getChildren().remove(text_display);
        }
    }
    public void reset() {
        text_display.getChildren().clear();
        layout = new Pane();
        layout.setPrefSize(600, 800);
        layout.setStyle("-fx-background-color: BLACK;");
        setScreen();
        Menu menu = new Menu();
        menu.runMenu();
        Main.createTable();
        createLabels();
        layout.getChildren().addAll(Main.table.getPuck(), Main.table.getPlayerOne(), Main.table.getPlayerTwo(), Main.table.getPlayerOne().getCenterCircle(), Main.table.getPlayerTwo().getCenterCircle());
        Scene scene = new Scene(layout);
        Controller.setControls();
        Main.game_state = Main.state.MENU;
        Main.menu_finished = false;
    }
    public static void playAgainButton() {
        play_again.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Main.new_game = true;
                Main.timeline.play();
            }
    });
        text_display.getChildren().clear();
        text_display.getChildren().add(play_again);
        
        if (!layout.getChildren().contains(text_display)) {
            layout.getChildren().add(text_display);
        }
    }

   
    
    
    
    
    
}








