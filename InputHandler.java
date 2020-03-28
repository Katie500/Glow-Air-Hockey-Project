import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import javax.*;



public class InputHandler extends Main{
    public static void controllerOne() {
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
    
    public static void controllerTwo() {
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
