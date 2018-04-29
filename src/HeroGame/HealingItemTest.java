package HeroGame;

/**
 * JUnit Tests for HealingItem Class and related enum - Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HealingItemTest {
	
	HealingItem h1 = new HealingItem(HealingItemType.DOUBLE_BROWN);
	HealingItem h2 = new HealingItem(HealingItemType.LINDAUER);
	HealingItem h3 = new HealingItem(HealingItemType.LION_RED);
	
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
	public void testGetHealingItemType() {
		assertEquals(HealingItemType.DOUBLE_BROWN, h1.getHealingItemType());
	}

	@Test
	public void testGetApplyTime() {
		assertTrue(h2.getApplyTime() > 0);
	}

	@Test
	public void testGetHealthValue() {
		assertEquals(25, h3.getHealthValue());
	}

	@Test
	public void testGetCost() {//Also tests superclass setter
		assertTrue(h1.getCost() > 0);
	}



	@Test
	public void testGetLongDescription() {//Also tests superclass setter
		assertEquals(h2.getLongDescription(), 
				HealingItemType.LINDAUER.getLongDescription());
	}

	@Test
	public void testGetResponse() {//Also tests superclass setter
		assertEquals(h3.getResponse(),
				HealingItemType.LION_RED.getResponse());
	}


	@Test
	public void testToString() {
		assertEquals(HealingItemType.DOUBLE_BROWN.getDescription(), 
				h1.toString());
	}

}
