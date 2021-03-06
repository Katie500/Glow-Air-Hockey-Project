import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.Player;
import application.Puck;
import javafx.scene.paint.Color;
// TODO: Auto-generated Javadoc

/**
 * The Class puckTest.
 */
public class puckTest {

	/**
	 * Test constructorfor colour.
	 */
	@Test
	public void testConstructorforColour() {
		Puck p = new Puck();
		equals("red");
	}
	
	/**
	 * Test set colour.
	 */
	@Test
	public void testSetColour()
	{
		Player p = new Player("yellow");
		p.setColour(Color.GREEN);
		equals(Color.GREEN );
	}

	/**
	 * Test constructor for x and Y.
	 */
	@Test
	public void testConstructorforXandY()
	{
		Puck p = new Puck();
		assertEquals(0.0, p.getX(), 0.000001);
		

	}
	
	/**
	 * Test set velocity X.
	 */
	@Test
	public void testSetVelocityX() {
		Puck p = new Puck();
		p.setVelocityX(5.5);
		assertEquals(5.5, p.getVelocityX(), 0.000001);
		
	}
	
	/**
	 * Test set velocity Y.
	 */
	@Test
	public void testSetVelocityY() {
		Puck p = new Puck();
		p.setVelocityY(10.1);
		assertEquals(10.1, p.getVelocityY(), 0.000001);
		
	}
	
	/**
	 * Test set X.
	 */
	@Test
	public void testSetX() {
		Puck p = new Puck();
		p.setX(2.0);
		assertEquals(2.0, p.getX(), 0.000001);
		
	}
	
	/**
	 * Test set Y.
	 */
	@Test
	public void testSetY() {
		Puck p = new Puck();
		p.setY(3.2);
		assertEquals(3.2, p.getY(), 0.000001);
		
	}
	
	
	
}
