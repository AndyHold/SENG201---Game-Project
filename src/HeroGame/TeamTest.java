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

public class TeamTest {
	
	public Team testTeam = new Team("Test Team");
	public Hero h1 = new Hero("Cletus", HeroType.ALL_BLACK);
	public Hero h2 = new Hero("Ethel Aardvark", HeroType.SURVEYOR);
	public Hero h3 = new Hero("Abraham Lincoln", HeroType.SURVEYOR);

	
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

/* BORKED!!
	@Test
	public void testAddRemoveApplyPowerUp() {
		PowerUp p1 = new PowerUp(PowerUpType.CHEESE_ROLL);
		testTeam.addPowerUp(p1);
		testTeam.addMember(h1);
		//Remove power up at non-existent index
		assertEquals(1, testTeam.removePowerUp(1));
		//Eat power up at non existent index
		testTeam.applyPowerUp(1, 0);
		assertNull(h1.getPowerUp());
		//Eat power up by non-existent team member
		testTeam.applyPowerUp(-1, 1);
		//Eat power up
		testTeam.applyPowerUp(0, 0);
		assertEquals(h1.getPowerUp().toString(), "Cheese Roll");
		//Check power up removed from list - can't eat twice
		h1.clearPowerUp();
		testTeam.applyPowerUp(0, 0);
		assertNull(h1.getPowerUp());
	}
	*/



	@Test
	public void testAddRemoveApplyHealingItem() {
		HealingItem hl1 = new HealingItem(HealingItemType.DOUBLE_BROWN);
		testTeam.addMember(h1);
		testTeam.addHealingItem(hl1);
		//Remove healing item at non-existent index
		assertEquals(1, testTeam.removeHealingItem(1));
		//Apply healing item at non-existent index
		fail("Not yet implemented");
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


}
