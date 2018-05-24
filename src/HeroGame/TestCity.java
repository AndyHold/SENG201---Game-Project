package HeroGame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCity {
	
	Villain testVillain = Villain.BADRUGBYREFEREE;
	Team testTeam = new Team("TestTeam");
	City testCity = new City("Springfield", testVillain, testTeam);
	
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
	public void testGetLocation() {
		//Test that all locations are filled
		Location l1 = testCity.getEastLocation();
		Location l2 = testCity.getWestLocation();
		Location l3 = testCity.getNorthLocation();
		Location l4 = testCity.getSouthLocation();
		Location l5 = testCity.getCenterLocation();
		assertFalse(l1.getLocationType() == l2.getLocationType());
		assertFalse(l2.getLocationType() == l3.getLocationType());
		assertFalse(l3.getLocationType() == l4.getLocationType());
		assertFalse(l4.getLocationType() == l5.getLocationType());
		assertFalse(l5.getLocationType() == l1.getLocationType());
		//check that the centre location is a HomeBase
		assertEquals(LocationType.HOMEBASE, l5.getLocationType());
		//Test that generic getLocation returns a Location
		Object l6 = testCity.getLocation(Direction.CENTER);
		assertTrue(l6 instanceof Location);
	}

	@Test
	public void testGetBadDirectionMessage() {
		//Returns a String
		Object s1 = testCity.getBadDirectionMessage();
		assertTrue(s1 instanceof String);
	}

	@Test
	public void testGetTeam() {
		//Returns a Team
		Object t2 = testCity.getTeam();
		assertTrue(t2 instanceof Team);
		
	}

	@Test
	public void testGetVillain() {
		//Returns a Villain
		Object v2 = testCity.getVillain();
		assertTrue(v2 instanceof Villain);
	}

	@Test
	public void testIsMakeMapped() {
		assertFalse(testCity.isMapped());
		testCity.makeMapped();
		assertTrue(testCity.isMapped());
	}

	@Test
	public void testGetPlaceName() {
		assertEquals("Taihape Railway Station", testCity.getPlaceName("Taihape HomeBase"));
		assertEquals("Paeroa Arts Centre", testCity.getPlaceName("Paeroa HomeBase"));
	}

	@Test
	public void testGetName() {
		assertEquals("Springfield", testCity.getName());
	}

}
