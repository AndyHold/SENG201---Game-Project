package HeroGame;

/**
 * JUnit Tests for Villain Class - Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestVillain {
	Villain v1 = Villain.BADRUGBYREFEREE;
	Villain v2 = Villain.POLITICIAN;
	//Team t1 = new Team("TestTeam");
	//City c1 = new City("Paeroa", v1, t1);
	
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
	public void testGetChangeStrength() {
		//Check default
		assertEquals(100, v1.getStrength());
		//Change Positive
		v1.changeStrength(200);
		assertEquals(200, v1.getStrength());
		//Change Negative
		v1.changeStrength(10);
		assertEquals(10, v1.getStrength());

	}

	@Test
	public void testGetLairName() {
		assertEquals("Paeroa Old Boys Football Ground", v1.getLairName("Paeroa"));
		assertEquals("Springfield Giant Donut Statue", v2.getLairName("Springfield"));
	}

	@Test
	public void testGetName() {
		assertEquals("Barry the Bad Referee", v1.getName());
		assertEquals("Peter the Politician", v2.getName());
	}

	@Test
	public void testGetTaunt() {
		//Taunt is random, so sufficient to check it always returns *Something*. It's
		//bound to be insulting anyway
		assertNotNull(v1.getTaunt());
	}

	@Test
	public void testTakeDamageGetHealthIsAlive() {
		//Check default
		assertEquals(100, v1.getHealth());
		//Check villain is alive
		assertTrue(v1.isAlive());
		//take some damage
		v1.takeDamage(50);
		assertEquals(50, v1.getHealth());
		//Reduce to <0 -should bottom at 0
		v1.takeDamage(60);
		assertEquals(0, v1.getHealth());
		//Villain should now be dead
		assertFalse(v1.isAlive());
	}


}
