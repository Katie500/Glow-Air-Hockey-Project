import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseClick extends MainMenu implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        System.out.println(mx + " " + my);

        
        //playbutton
        if (mx >= width/2-50 && mx <= width/2+50 && my >= height/5*2 && my <= height/5*2+60) {
                Main.game_state = Main.state.GAME;
        }
        
        //infobutton
        if (mx >= width/2-50 && mx <= width/2+50 && my >= height/5*3 && my <= height/5*3+60) {
            
        }
        
        //quitbutton
        if (mx >= width/2-50 && mx <= width/2+50 && my >= height/5*4 && my <= height/5*4+60) {
                System.exit(0);
        }        
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        System.out.println("Entered");
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        //quitbutton
        if (mx >= width/2-50 && mx <= width/2+50 && my >= height/5*4 && my <= height/5*4+60) {
                System.exit(0);        
        }
    }

}
