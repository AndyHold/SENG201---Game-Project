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

public class TestHero {

	
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
		assertNull(testHero1.getAbility());
		assertEquals(0, testHero1.getStrength());
		assertEquals(100, testHero1.getHealth());
		
		//Parameterised Constructor fills out type and hence 
		//ability, strength (and description - unused in final game
		Hero testHero2 = new Hero("Jim", HeroType.ALL_BLACK);
		assertEquals(HeroType.ALL_BLACK, testHero2.getType());
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
		assertNull(testHero.getPowerUp());
		
		//Eat one - check getter
		PowerUp p1 = new PowerUp(PowerUpType.PINEAPPLE_LUMPS);
		testHero.eatPowerUp(p1);
		assertEquals("Pineapple Lumps", testHero.getPowerUp().toString());
		
		//Eat a second. Should overwrite first - check getter
		PowerUp p2 = new PowerUp(PowerUpType.PAVLOVA);
		testHero.eatPowerUp(p2);
		assertEquals("Pavlova", testHero.getPowerUp().toString());
		
		//Clear powerup. should now be null
		testHero.clearPowerUp();
		assertNull(testHero.getPowerUp());
		//Clear again
		testHero.clearPowerUp();
		assertNull(testHero.getPowerUp());
		
	}
	
	@Test
	public void testDrinkCheckHealingItem() {
		Hero h1 = new Hero("Jim", HeroType.ALL_BLACK);
		HealingItem hI1 = new HealingItem(HealingItemType.DOUBLE_BROWN);
		HealingItem hI2 = new HealingItem(HealingItemType.LINDAUER);
		
		//No healing item exists
		assertEquals(0, (int)h1.checkHealingItemTime(100));
		
		//Drink a healing item
		assertEquals((h1.getName() + " " + hI1.getResponse()), (h1.drinkHealingItem(hI1, 60)));
		assertEquals(30, (int)h1.checkHealingItemTime(60));
		
		//Drink a second, should override first
		h1.drinkHealingItem(hI2, 70);
		assertEquals(30, (int)h1.checkHealingItemTime(70));
		
		//Healing item of value 100 - check at - 25%, 25%, 50%, 75%, 100%, 125% of apply time
		hI2.changeHealthValue(50);
		h1.drinkHealingItem(hI2, 60);//Apply time = 30 secs
		h1.checkHealingItemTime(52.5);
		assertEquals(100, h1.getHealth());
		int expectedValue = 100;
		for (double d = 60; d < 90; d += 7.5) {
			h1.checkHealingItemTime(d);
			assertEquals(expectedValue, h1.getHealth());
			expectedValue += 25;
		}
		h1.checkHealingItemTime(107.5);
		assertEquals(200, h1.getHealth());
		
		//Healing item of value 25 - check at -25%, 50%, 100%, 125% of apply time
		h1.drinkHealingItem(hI1, 60.0);
		h1.checkHealingItemTime(52.5);
		assertEquals(200, h1.getHealth());
		h1.checkHealingItemTime(75);
		assertEquals(200, h1.getHealth());
		h1.checkHealingItemTime(90);
		assertEquals(225, h1.getHealth());
		h1.checkHealingItemTime(105);
		assertEquals(225, h1.getHealth());
	}


	@Test
	public void testPrintHeroList() {
		//Difficult to test, as outputs to console
		Hero testHero = new Hero();
		assertEquals(6, testHero.printHeroList());
		
	}

}
