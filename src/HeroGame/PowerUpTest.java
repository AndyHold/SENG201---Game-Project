package HeroGame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PowerUpTest {
	private PowerUp p1 = new PowerUp(PowerUpType.CHEESE_ROLL);
	private PowerUp p2 = new PowerUp(PowerUpType.PINEAPPLE_LUMPS);
	private PowerUp p3 = new PowerUp(PowerUpType.PAVLOVA);
	
	
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
	public void testGetEffect() {
		assertEquals("gives a hero the lightning quick speed to see the opponent's" + 
				" move in rock, paper, scissors and play the appropriate move", 
				p3.getEffect());
	}

	@Test
	public void testGetType() {
		assertEquals(PowerUpType.CHEESE_ROLL, p1.getType());
	}
	
	@Test
	public void testGetCost() {
		assertTrue(p2.getCost() > 0);
	}
	
	
	@Test
	public void testGetLongDescription() {
		assertEquals("A perfectly cooked cheese roll - a little piece of Southland",
				p1.getLongDescription());
	}


	@Test
	public void testGetResponse() {
		assertEquals("tears the packet open and begins wolfing them down. \"Nom Nom Nom!\"",
				p2.getResponse());
	}


	@Test
	public void testToString() {
		assertEquals("Pavlova", p3.toString());
	}

}
