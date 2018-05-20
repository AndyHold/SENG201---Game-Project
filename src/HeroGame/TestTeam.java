package HeroGame;

/**
 * JUnit Tests for Team Class - Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestTeam {
	
	public Team testTeam = new Team("Test Team");
	public Hero h1 = new Hero("Cletus", HeroType.ALL_BLACK);
	public Hero h2 = new Hero("Ethel Aardvark", HeroType.SURVEYOR);
	public Hero h3 = new Hero("Abraham Lincoln", HeroType.SURVEYOR);
	public Hero h4 = new Hero("Florence", HeroType.NURSE);

	
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
	public void testAddRemoveMemberTeamSize() {
		//Size of empty team
		assertEquals(0, testTeam.getTeamSize());
		
		//Add team member, size of team
		testTeam.addMember(h1);
		assertEquals(1, testTeam.getTeamSize());
		
		//Add team member, size of team
		testTeam.addMember(h2);
		assertEquals(2, testTeam.getTeamSize());
		
		//Remove team member
		testTeam.removeMember(1);
		assertEquals(1, testTeam.getTeamSize());
		
		//Remove team member at non-existent index
		testTeam.removeMember(1);
		assertEquals(1, testTeam.getTeamSize());
		
		//Remove team member at negative index
		testTeam.removeMember(-1);
		assertEquals(1, testTeam.getTeamSize());
		

	}



	@Test
	public void testBuildTeam() {
		//Build team size 0
		Team testTeam = new Team("Test Team");
		testTeam.buildTeam(0);
		//Build team size non 0
		//testTeam.buildTeam(2);
		//Build team size negative?
		testTeam.buildTeam(-1);
	}

	@Test
	public void testCheckNameUnique() {
		testTeam.addMember(h1);
		testTeam.addMember(h2);
		
		//Check same name
		assertFalse(testTeam.checkNameUnique("Cletus"));
		
		//Check name with same number chars
		assertTrue(testTeam.checkNameUnique("Doris Platypus"));
		
		//Check name with same starting letters
		assertTrue(testTeam.checkNameUnique("Ethel"));
		
		//Check name with spaces
		assertTrue(testTeam.checkNameUnique("EthelAardvark"));
		
		//Check same name different case
		assertFalse(testTeam.checkNameUnique("ethel aardvark"));
	}

	@Test
	public void testCheckPresent() {
		testTeam.addMember(h1);
		testTeam.addMember(h2);
		
		//Check - actually present
		assertTrue(testTeam.checkPresent(HeroType.ALL_BLACK));
		//Check - not present
		assertFalse(testTeam.checkPresent(HeroType.NURSE));
		
	}

	@Test
	public void testGetIndex() {
		//Check - hero in team
		testTeam.addMember(h1);
		testTeam.addMember(h2);
		assertEquals(1, testTeam.getIndex(h2));
		
		//Check - hero not in team
		testTeam.removeMember(0);
		assertEquals(-1, testTeam.getIndex(h1));
	}
	
	@Test
	public void testListHeroes() {
		//Check Empty Team
		String result = "Heroes in team:\n";
		assertEquals(result, testTeam.listHeroes());
		//Check Team With One Member
		testTeam.addMember(h1);
		assertEquals(result + "0. " + h1.getName() + "\n", testTeam.listHeroes());
	}
	
	@Test
	public void testGetHero() {
		//Check negative index
		assertNull(testTeam.getHero(-1));
		//Check non-existant index
		assertNull(testTeam.getHero(2));
		//Check existing index
		testTeam.addMember(h1);
		assertEquals(h1, testTeam.getHero(0));
	}

//	@Test
//	public void testAddRemoveApplyPowerUp() {
//		PowerUp p1 = new PowerUp(PowerUpType.CHEESE_ROLL);
//		PowerUp p2 = new PowerUp(PowerUpType.PINEAPPLE_LUMPS);
//		assertEquals(0, testTeam.getPowerUpsSize());
//		testTeam.addPowerUp(p1);
//		testTeam.addMember(h1);
//		//Remove power up at non-existent index
//		assertEquals(1, testTeam.removePowerUp(1));
//		//Remove power up at negative index
//		assertEquals(1, testTeam.removePowerUp(-1));
//		//Remove power up at existent index
//		assertEquals(0, testTeam.removePowerUp(0));
//		//Check get powerUp Type - none in list
//		assertNull(testTeam.getPowerUpType(0));
//		//Check get power up type negative index
//		assertNull(testTeam.getPowerUpType(-1));
//		//Check list of 1
//		testTeam.addPowerUp(p1);
//		assertEquals(PowerUpType.CHEESE_ROLL, testTeam.getPowerUpType(0));
//		//Check list of two
//		testTeam.addPowerUp(p2);
//		assertEquals(PowerUpType.PINEAPPLE_LUMPS, testTeam.getPowerUpType(1));
//		//Check get size
//		assertEquals(2, testTeam.getPowerUpsSize());
//		//Eat non-existent power up 
//		testTeam.applyPowerUp(PowerUpType.PAVLOVA, 0);
//		assertNull(h1.getPowerUp());
//		//Eat power up by non-existent team member
//		assertEquals("No hero found!\n", testTeam.applyPowerUp(PowerUpType.CHEESE_ROLL, 1));
//		//Eat power up by negative index team member
//		assertEquals("No hero found!\n", testTeam.applyPowerUp(PowerUpType.CHEESE_ROLL, -1));
//		//Eat power up
//		testTeam.applyPowerUp(PowerUpType.CHEESE_ROLL, 0);
//		assertEquals(h1.getPowerUp().toString(), "Cheese Roll");
//		//Check power up removed from list - can't eat twice
//		h1.clearPowerUp();
//		testTeam.applyPowerUp(PowerUpType.CHEESE_ROLL, 0);
//		assertNull(h1.getPowerUp());
//	}

//	@Test
//	public void testAddRemoveApplyHealingItem() {
//		HealingItem hI1 = new HealingItem(HealingItemType.DOUBLE_BROWN);
//		testTeam.addMember(h1);
//		testTeam.addHealingItem(hI1);
//		assertEquals(25, hI1.getHealthValue());
//		//Check that power up added with Nurse present doubles health value
//		testTeam.addMember(h4);
//		HealingItem hI2 = new HealingItem(HealingItemType.DOUBLE_BROWN);
//		testTeam.addHealingItem(hI2);
//		assertEquals(50, hI2.getHealthValue());
//		//Check get size
//		assertEquals(2, testTeam.getHealingItemsSize());
//		//check get type - negative index
//		assertNull(testTeam.getHealingItemType(-1));
//		//check get type - non existent index
//		assertNull(testTeam.getHealingItemType(3));
//		//check get type - existing index
//		assertEquals(HealingItemType.DOUBLE_BROWN, testTeam.getHealingItemType(0));
//		//Remove healing item at negative index
//		assertEquals(2, testTeam.removeHealingItem(-1));
//		//Remove healing item at non-existent index
//		assertEquals(2, testTeam.removeHealingItem(2));
//		//Remove healing item at existing index
//		assertEquals(1, testTeam.removeHealingItem(1));
//		//Apply non existent healing item
//		assertEquals("Healing item not found\n", testTeam.applyHealingItem(HealingItemType.LINDAUER, 0));
//		//Apply healing item to non-existent team member
//		assertEquals("No hero found!\n", testTeam.applyHealingItem(HealingItemType.DOUBLE_BROWN, 3));
//		//Apply healing item to negative team member index
//		assertEquals("No hero found!\n", testTeam.applyHealingItem(HealingItemType.DOUBLE_BROWN, -1));
//		//Apply healing item to existing team member
//		assertEquals("Healing item applied!\n", testTeam.applyHealingItem(HealingItemType.DOUBLE_BROWN, 1));
//		//Healing item removed from list
//		assertEquals(0, testTeam.getHealingItemsSize());
//		
//		
//	}




	@Test
	public void testGetChangeMaps() {
		assertEquals(0, testTeam.getMaps());
		//Change maps to negative - should stay at 0
		testTeam.changeMaps(-1);
		assertEquals(0, testTeam.getMaps());
		//Add a map
		testTeam.changeMaps(1);
		assertEquals(1, testTeam.getMaps());
		}
	
	@Test
	public void testGetChangeMoney() {
		assertEquals(10, (int)testTeam.getMoney());
		//Change money to negative - should stay at 0
		testTeam.changeMoney(-11);
		assertEquals(0, (int)testTeam.getMoney());
		//Add money
		testTeam.changeMoney(2.00);
		assertEquals(2, (int)(testTeam.getMoney()));
		//Remove money stay positive
		testTeam.changeMoney(-1.00);
		assertEquals(1, (int)testTeam.getMoney());
		}
	
	@Test
	public void testClock() throws InterruptedException{
		//Start clock - check that time is 1 after 1 second
		testTeam.startClock();
		Thread.sleep(1000);
		assertEquals(1, (int)testTeam.getTime());
	}
	
	


}
