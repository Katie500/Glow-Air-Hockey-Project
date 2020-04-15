package gui;

import javafx.application.Application;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class GameScreen.
 */
public abstract class GameScreen extends Pane {
    
    /** The Constant WIDTH. */
    //declaring values that will remain unchanged in both gui screens
    public final static int WIDTH = 600;
    
    /** The Constant HEIGHT. */
    public final static int HEIGHT = 800;
    
    /** The Constant CENTER_X. */
    final static double CENTER_X = WIDTH / 2;
    
    /** The Constant CENTER_Y. */
    final static double CENTER_Y = HEIGHT / 2;
    
    /** The Constant GOAL_SIZE. */
    public final static double GOAL_SIZE = 200;
    
    /** The green border glow. */
    protected static DropShadow green_border_glow = new DropShadow();
    
    /** The red border glow. */
    protected static DropShadow red_border_glow = new DropShadow();
    
    /**
     * Sets the screen.
     */
    //abstract method that must be used in both gui screens although differently
    public abstract void setScreen();
    
    /**
     * Creates the border glows.
     */
    //method used the same way in both gui screens
    public static void createBorderGlows() {
        getGreen_border_glow().setColor(Color.CHARTREUSE);
        getGreen_border_glow().setOffsetX(0f);
        getGreen_border_glow().setOffsetY(0f);
        getGreen_border_glow().setWidth(50);
        getGreen_border_glow().setHeight(50);
        
        red_border_glow.setColor(Color.RED);
        red_border_glow.setOffsetX(0f);
        red_border_glow.setOffsetY(0f);
        red_border_glow.setWidth(50);
        red_border_glow.setHeight(50);
    }

    
    
    /**
     * Gets the green border glow.
     *
     * @return the green border glow
     */
    //Getter and setter for green border glow to access in main. 
    public static DropShadow getGreen_border_glow() {
        return green_border_glow;
    }
    
    /**
     * Sets the green border glow.
     *
     * @param green_border_glow the new green border glow
     */
    public static void setGreen_border_glow(DropShadow green_border_glow) {
        GameScreen.green_border_glow = green_border_glow;
    }



}
