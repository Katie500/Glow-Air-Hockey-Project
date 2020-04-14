package application;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Controller extends Main {
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

	public static void setControls() {
	    getRink_screen().setOnKeyPressed(new EventHandler<KeyEvent>() {
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
        
        getRink_screen().setOnKeyReleased(new EventHandler<KeyEvent>() {
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



}
