package HeroGame;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestHospital {
	
	Villain testVillain = Villain.CALLCENTREOPERATOR;
	Team testTeam = new Team("TestTeam");
	City testCity = new City("Springfield", testVillain, testTeam);
	Hospital testHospital = new Hospital(testCity, testTeam);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetBadHealingItemMessage() {
		//Check that a String is returned
		String testString = new String();
		assertEquals(testString.getClass(), testHospital.getBadHealingItemMessage().getClass());
	}

	@Test
	void testGetInteriorImage() {
		//Check that an image is returned
		ImageIcon testIcon = new ImageIcon();
		assertEquals(testHospital.getInteriorImage().getClass(), testIcon.getClass());
	}

	@Test
	void testCheckHealingTimes() {
		//Check that a String is returned
		String testString = new String();
		assertEquals(testString.getClass(), testHospital.checkHealingTimes().getClass());
		assertTrue(testHospital.checkHealingTimes().length() == 65);
		testTeam.addHealingItem(HealingItemType.DOUBLE_BROWN);
		assertTrue(testHospital.checkHealingTimes().length() > 65);
	}

}
