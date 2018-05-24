package HeroGame;

/**
 * JUnit Tests for PowerUp Class and related enum - Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestPowerUp {
	private PowerUp p1 = new PowerUp(PowerUpType.CHEESE_ROLL);
	private PowerUp p2 = new PowerUp(PowerUpType.PINEAPPLE_LUMPS);
	private PowerUp p3 = new PowerUp(PowerUpType.PAVLOVA);
	private PowerUp p4 = new PowerUp(PowerUpType.CHEESE_ROLL);
	
	
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
		assertEquals("Gives a hero the lightning quick speed to see the opponent's" + 
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
		assertEquals("a perfectly cooked cheese roll - a little piece of Southland",
				p1.getLongDescription());
	}


	@Test
	public void testGetResponse() {
		assertEquals("Tears the packet open and begins wolfing them down. \"Nom Nom Nom!\"",
				p2.getResponse());
	}


	@Test
	public void testToString() {
		assertEquals("Pavlova", p3.toString());
	}
	
	@Test
	public void testEquals() {
		assertTrue(p1.equals(p1));
		assertFalse(p1.equals(p2));
		HealingItem h1 = new HealingItem(HealingItemType.DOUBLE_BROWN);
		assertFalse(p1.equals(h1));
		assertTrue(p1.equals(p4));
	}

}
