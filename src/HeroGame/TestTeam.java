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
		//Check non-existent index
		assertNull(testTeam.getHero(2));
		//Check existing index
		testTeam.addMember(h1);
		assertEquals(h1, testTeam.getHero(0));
	}

	@Test
	public void testAddRemoveApplyPowerUp() {
		assertEquals(0, testTeam.getPowerUpsSize());
		testTeam.addMember(h1);
		testTeam.addPowerUp(PowerUpType.PAVLOVA);
		//Remove power up at non-existent index
		assertEquals(1, testTeam.removePowerUp(1));
		//Remove power up at negative index
		assertEquals(1, testTeam.removePowerUp(-1));
		//Remove power up from list when amount is 0
		assertEquals(0, testTeam.removePowerUp(0));
		//Check get powerUp Type - none in list
		assertNull(testTeam.getPowerUpType(0));
		//Check get power up type negative index
		assertNull(testTeam.getPowerUpType(-1));
		//Check list of 1
		testTeam.addPowerUp(PowerUpType.CHEESE_ROLL);
		assertEquals(PowerUpType.CHEESE_ROLL, testTeam.getPowerUpType(0));
		//Check list of two
		testTeam.addPowerUp(PowerUpType.PINEAPPLE_LUMPS);
		assertEquals(PowerUpType.PINEAPPLE_LUMPS, testTeam.getPowerUpType(1));
		//Check get size
		assertEquals(2, testTeam.getPowerUpsSize());
		//Check that adding an additional HealingItem of same type increments amount
		testTeam.addPowerUp(PowerUpType.PINEAPPLE_LUMPS);
		assertEquals(2, testTeam.getPowerUps().get(1).getAmount());
		//Check that removing a HealingItem > 0 increments amount down
		testTeam.removePowerUp(1);
		assertEquals(1, testTeam.getPowerUps().get(1).getAmount());
	}

	@Test
	public void testAddRemoveApplyHealingItem() {
		testTeam.addMember(h1);
		testTeam.addHealingItem(HealingItemType.DOUBLE_BROWN);
		assertEquals(25, testTeam.getHealingItems().get(0).getHealthValue());
		//Check that power up added with Nurse present doubles health value
		testTeam.removeHealingItem(0);
		testTeam.addMember(h4);
		testTeam.addHealingItem(HealingItemType.DOUBLE_BROWN);
		assertEquals(50, testTeam.getHealingItems().get(0).getHealthValue());
		testTeam.addHealingItem(HealingItemType.LINDAUER);
		//Check that adding an additional HealingItem of same type increments amount
		testTeam.addHealingItem(HealingItemType.DOUBLE_BROWN);
		assertEquals(2, testTeam.getHealingItems().get(0).getAmount());
		//Check get size
		assertEquals(2, testTeam.getHealingItemsSize());
		//check get type - negative index
		assertNull(testTeam.getHealingItemType(-1));
		//check get type - non existent index
		assertNull(testTeam.getHealingItemType(3));
		//check get type - existing index
		assertEquals(HealingItemType.DOUBLE_BROWN, testTeam.getHealingItemType(0));
		//Remove healing item at negative index
		assertEquals(2, testTeam.removeHealingItem(-1));
		//Remove healing item at non-existent index
		assertEquals(2, testTeam.removeHealingItem(2));
		//Check that incrementing to 0 removes from list
		assertEquals(1, testTeam.removeHealingItem(1));
		//Check that removing a HealingItem > 0 increments amount down
		testTeam.removeHealingItem(0);
		assertEquals(1, testTeam.getHealingItems().get(0).getAmount());
		

		
	}




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
		assertEquals(15, (int)testTeam.getMoney());
		//Change money to negative - should stay at 0
		testTeam.changeMoney(-16);
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
	
	@Test
	public void testListHealingItemsListPowerUps() {
		//Empty lists still return a string
		assertNotNull(testTeam.listHealingItems());
		assertNotNull(testTeam.listPowerUps());
		//Lists with items return a String
		testTeam.addHealingItem(HealingItemType.DOUBLE_BROWN);
		testTeam.addPowerUp(PowerUpType.CHEESE_ROLL);
		assertNotNull(testTeam.listHealingItems());
		assertNotNull(testTeam.listPowerUps());
		
		
	}
	
	


}
