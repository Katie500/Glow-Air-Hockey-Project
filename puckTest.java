import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class puckTest {

	@Test
	public void testConstructorforColor() {
		Puck p = new Puck("yellow");
		assertEquals("yellow", p.getColour());
	}
	
	@Test
	public void testConstructorforXandY()
	{
		Puck p = new Puck(2.0, 3.2, 5.5, 10.1);
		assertEquals(2.0, p.getX(), 0.000001);
		assertEquals(3.2, p.getY(), 0.000001);
		assertEquals(5.5, p.getVelocityX(), 0.000001);
		assertEquals(10.1, p.getVelocityY(), 0.000001);

	}
	
	@Test
	public void testSetColour()
	{
		Puck p = new Puck("yellow");
		p.setColour("green");
		assertEquals("green", p.getColour());
	}

	@Test
	public void testSetVelocityX() {
		Puck p = new Puck(2.0, 3.2, 5.5, 10.1);
		p.setVelocityX(5.5);
		assertEquals(5.5, p.getVelocityX(), 0.000001);
		
	}
	@Test
	public void testSetVelocityY() {
		Puck p = new Puck(2.0, 3.2, 5.5, 10.1);
		p.setVelocityY(10.1);
		assertEquals(10.1, p.getVelocityY(), 0.000001);
		
	}
	
	@Test
	public void testSetX() {
		Puck p = new Puck(2.0, 3.2, 5.5, 10.1);
		p.setX(2.0);
		assertEquals(2.0, p.getX(), 0.000001);
		
	}
	@Test
	public void testSetY() {
		Puck p = new Puck(2.0, 3.2, 5.5, 10.1);
		p.setY(3.2);
		assertEquals(3.2, p.getY(), 0.000001);
		
	}
	
	
	
}
