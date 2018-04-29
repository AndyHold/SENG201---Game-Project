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

public class HeroTest {

	
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
	public void testSetNameGetName() {
		Hero testHero = new Hero();
		assertNull(testHero.getName());
		testHero.setName("Jim");
		assertEquals("Jim", testHero.getName());
		
	}

	@Test
	//Test constructor and notionally immutable attributes
	public void testConstructorGetTypeDescriptionAbilityStrength() {
		//Empty constructor builds empty Hero with strength 100
		Hero testHero1 = new Hero();
		assertNull(testHero1.getType());
		assertNull(testHero1.toString());
		assertNull(testHero1.getAbility());
		assertEquals(0, testHero1.getStrength());
		assertEquals(100, testHero1.getHealth());
		
		//Parameterised Constructor fills out type and hence 
		//description (toString), ability, strength
		Hero testHero2 = new Hero("Jim", HeroType.ALL_BLACK);
		assertEquals(HeroType.ALL_BLACK, testHero2.getType());
		assertEquals("An All Black", testHero2.toString());
		assertEquals("have a virtually unprecedented success in games. Wins 50% more games", testHero2.getAbility());
		assertEquals(100, testHero2.getStrength());
		assertEquals(100, testHero2.getHealth());
		
		
		//Check that creating second Hero of same type creates new instance
		//not pointer to first instance. Seems obvious, is but important.
		Hero testHero3 = new Hero("Cletus", HeroType.ALL_BLACK);
		assertEquals("Jim", testHero2.getName()); //testHero2 is unmodified
	}

	@Test
	public void testChangeStrength() {
		Hero testHero = new Hero();
		assertEquals(0, testHero.getStrength());
		//Change positive
		assertEquals(100, testHero.changeStrength(100));
		//Change negative
		assertEquals(75, testHero.changeStrength(-25));
		//Change by 0
		assertEquals(75, testHero.changeStrength(0));
		//Change to 0
		assertEquals(0, testHero.changeStrength(-75));
		//Change to < 0
		assertEquals(-10, testHero.changeStrength(-10));
		
	}

	@Test
	public void testChangeHealth() {
		Hero testHero = new Hero();
		assertEquals(100, testHero.getHealth());
		//Change positive
		assertEquals(125, testHero.changeHealth(25));
		//Change negative
		assertEquals(75, testHero.changeHealth(-50));
		//Change by 0
		assertEquals(75, testHero.changeHealth(0));
		//Change to 0
		assertEquals(0, testHero.changeHealth(-75));
		//Change to < 0
		assertEquals(-10, testHero.changeHealth(-10));
	}


	@Test
	public void testEatGetClearPowerUp() {
		Hero testHero = new Hero();
		
		//Check defaults to "No power up eaten"
		assertEquals("No power up eaten", testHero.getPowerUp());
		
		//Eat one - check getter
		PowerUp p1 = new PowerUp(PowerUpType.PINEAPPLE_LUMPS);
		testHero.eatPowerUp(p1);
		assertEquals("Pineapple Lumps", testHero.getPowerUp());
		
		//Eat a second. Should overwrite first - check getter
		PowerUp p2 = new PowerUp(PowerUpType.PAVLOVA);
		testHero.eatPowerUp(p2);
		assertEquals("Pavlova", testHero.getPowerUp());
		
		//Clear powerup. should now be null
		testHero.clearPowerUp();
		assertEquals("No power up eaten", testHero.getPowerUp());
		//Clear again
		testHero.clearPowerUp();
		assertEquals("No power up eaten", testHero.getPowerUp());
		
	}


	@Test
	public void testPrintHeroList() {
		//Difficult to test, as outputs to console
		Hero testHero = new Hero();
		assertEquals(6, testHero.printHeroList());
		
	}

}
