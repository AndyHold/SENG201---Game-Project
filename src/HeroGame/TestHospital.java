package HeroGame;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestHospital {
	
	Villain testVillain = Villain.CALLCENTREOPERATOR;
	Team testTeam = new Team("TestTeam");
	City testCity = new City("Springfield", testVillain, testTeam);
	Hospital testHospital = new Hospital(testCity, testTeam);

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
	public void testGetBadHealingItemMessage() {
		//Check that a String is returned
		String testString = new String();
		assertEquals(testString.getClass(), testHospital.getBadHealingItemMessage().getClass());
	}

	@Test
	public void testGetInteriorImage() {
		//Check that an image is returned
		ImageIcon testIcon = new ImageIcon();
		assertEquals(testHospital.getInteriorImage().getClass(), testIcon.getClass());
	}

	@Test
	public void testCheckHealingTimes() {
		//Check that a String is returned
		String testString = new String();
		assertEquals(testString.getClass(), testHospital.checkHealingTimes().getClass());
		assertTrue(testHospital.checkHealingTimes().length() >= 60);
		testTeam.addHealingItem(HealingItemType.DOUBLE_BROWN);
		assertTrue(testHospital.checkHealingTimes().length() > 65);
	}

}
