package HeroGame;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestPowerUpDen {
	
	private Villain testVillain = Villain.CALLCENTREOPERATOR;
	private Team testTeam = new Team("TestTeam");
	private City testCity = new City("Springfield", testVillain, testTeam);
	private PowerUpDen testPowerUpDen = new PowerUpDen(testCity, testTeam);


	@Test
	public void testGetBadPowerUpMessage() {
		//Check that a String is returned
		String testString = new String();
		assertEquals(testString.getClass(), testPowerUpDen.getBadPowerUpMessage().getClass());
	}

	@Test
	public void testGetInteriorImage() {
		//Check that an image is returned
		ImageIcon testIcon = new ImageIcon();
		assertEquals(testPowerUpDen.getInteriorImage().getClass(), testIcon.getClass());
	}

	@Test
	public void testCheckPowerUps() {
		//Check that a String is returned
		String testString = new String();
		assertEquals(testString.getClass(), testPowerUpDen.checkPowerUps().getClass());
		//Check length before and after adding a Power Up
		assertEquals(59, testPowerUpDen.checkPowerUps().length());
		testTeam.addPowerUp(PowerUpType.CHEESE_ROLL);
		assertTrue(testPowerUpDen.checkPowerUps().length() >= 59);
	}

}
