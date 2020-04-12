package gui;

import javafx.application.Application;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public abstract class GameScreen extends Pane {
    public final static int WIDTH = 600;
    public final static int HEIGHT = 800;
    final static double CENTER_X = WIDTH / 2;
    final static double CENTER_Y = HEIGHT / 2;
    final static double GOAL_SIZE = 150;
    
    protected static DropShadow green_border_glow = new DropShadow();
    protected static DropShadow red_border_glow = new DropShadow();
    
    public abstract void setScreen();
    
    public static void createBorderGlows() {
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
}
