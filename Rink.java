package gui;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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
    
    
    
    public static Pane layout = new Pane();
    
    
    public void runRink() {
        layout.setPrefSize(WIDTH, HEIGHT);
        layout.setStyle("-fx-background-color: BLACK;");
        setScreen();
        getChildren().addAll(center_line, left_border, right_border, top_border, bottom_border, player_one_goal, player_two_goal, center_circle, center_circle_cover, center);
        
    }
    
    
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
        
        //layout.getChildren().addAll(center_line, left_border, right_border, top_border, bottom_border, player_one_goal, player_two_goal, center_circle, center_circle_cover, center);

    }  
 
}
