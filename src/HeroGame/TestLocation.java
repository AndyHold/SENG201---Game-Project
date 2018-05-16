package HeroGame;

/**
 * JUnit Tests for Hero Class - Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.swing.ImageIcon;

public class TestLocation {
	
	Team t1 = new Team("TestTeam");
	ImageIcon i1 = new ImageIcon();
	
	Location l1 = new Location("Bob's Burgers", t1, "New York", LocationType.POWERUPDEN, i1); 
 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetLocationType() {
		assertEquals(LocationType.POWERUPDEN, l1.getLocationType());
	}

	@Test
	public void testSetGetDirection() {
		//Check initially null;
		assertNull(l1.getDirection());
		//Check set/get
		l1.setDirection(Direction.NORTH);
		assertEquals(Direction.NORTH, l1.getDirection());
		//Check can reset
		l1.setDirection(Direction.WEST);
		assertEquals(Direction.WEST, l1.getDirection());
	}


	@Test
	public void testGetName() {
		assertEquals("Bob's Burgers", l1.getName());
	}

}
