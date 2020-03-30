
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu extends JPanel  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static int width = 600;
    protected static int height = 800;
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Button boxes
        g.setColor(Color.GRAY);
        g.fillRect(width/2-200, height/5, 400, 60);
        g.fillRect(width/2-50, height/5*2, 100, 60);
        g.fillRect(width/2-50, height/5*3, 100, 60);
        g.fillRect(width/2-50, height/5*4, 100, 60);
        
        // Borders
        g.setColor(Color.RED);
        g.fillRect(0, 0, 2, 800);
        g.fillRect(width-18, 0, 2, 800);
        g.fillRect(0, 0, 600, 2);
        g.fillRect(0, height-41, 600, 2);
        
        // Labels
        g.setColor(Color.RED);
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
        frame.setResizable(false);
        frame.addMouseListener(new MouseClick());
    }

    
}
