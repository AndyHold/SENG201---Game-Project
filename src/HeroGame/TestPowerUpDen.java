package HeroGame;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPowerUpDen {
	
	Villain testVillain = Villain.CALLCENTREOPERATOR;
	Team testTeam = new Team("TestTeam");
	City testCity = new City("Springfield", testVillain, testTeam);
	PowerUpDen testPowerUpDen = new PowerUpDen(testCity, testTeam);

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
	void testGetBadPowerUpMessage() {
		//Check that a String is returned
		String testString = new String();
		assertEquals(testString.getClass(), testPowerUpDen.getBadPowerUpMessage().getClass());
	}

	@Test
	void testGetInteriorImage() {
		//Check that an image is returned
		ImageIcon testIcon = new ImageIcon();
		assertEquals(testPowerUpDen.getInteriorImage().getClass(), testIcon.getClass());
	}

	@Test
	void testCheckPowerUps() {
		//Check that a String is returned
		String testString = new String();
		assertEquals(testString.getClass(), testPowerUpDen.checkPowerUps().getClass());
		//Check length before and after adding a Power Up
		assertTrue(testPowerUpDen.checkPowerUps().length() == 52);
		testTeam.addPowerUp(PowerUpType.CHEESE_ROLL);
		assertTrue(testPowerUpDen.checkPowerUps().length() > 52);
	}

}
