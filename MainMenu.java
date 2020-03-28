import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javafx.scene.effect.DropShadow;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.scene.shape.Rectangle;

public class MainMenu extends JPanel implements MouseListener{
    protected static int width = 600;
    protected static int height = 800;
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //button boxes
        g.setColor(Color.gray);
        g.fillRect(width/2-200, height/5, 400, 60);
        g.fillRect(width/2-50, height/5*2, 100, 60);
        g.fillRect(width/2-50, height/5*3, 100, 60);
        g.fillRect(width/2-50, height/5*4, 100, 60);
        
        //borders
        g.setColor(Color.red);
        g.fillRect(0, 0, 2, 800);
        g.fillRect(width-18, 0, 2, 800);
        g.fillRect(0, 0, 600, 2);
        g.fillRect(0, height-41, 600, 2);
        
        
        
        
        //labels
        g.setColor(Color.red);
        g.setFont(new Font("Bauhaus 93", Font.PLAIN, 45));
        g.drawString("Glow Air Hockey", width/2-160, height/5 + 45);
        g.drawString("Play", width/2 - 45, height/5*2+42);
        g.drawString("Info", width/2 - 40, height/5*3+45);
        g.drawString("Quit", width/2 - 45, height/5*4+45);

    }
    
    public static void main (String[] args) {
        MainMenu menu = new MainMenu();
        
        JFrame frame = new JFrame();
        frame.setTitle("Glow Air Hockey");
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(menu);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        
        //playbutton
        if (mx >= width/2-50 && mx <= width/2+50) {
            if(my >= height/5*2 && my <= height/5*2+60) {
                Main.state = Main.STATE.GAME;
            }
        }
        
        //infobutton
        if (mx >= width/2-50 && mx <= width/2+50) {
            if(my >= height/5*3 && my <= height/5*3+60) {
                //display instructions and names class etc;
            }
        }
        
        //quitbutton
        if (mx >= width/2-50 && mx <= width/2+50) {
            if(my >= height/5*4 && my <= height/5*4+60) {
                System.exit(0);
            }
        }        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    
        // TODO Auto-generated method stub
        
    }
}
