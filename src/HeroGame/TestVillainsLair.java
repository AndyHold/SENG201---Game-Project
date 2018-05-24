package HeroGame;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestVillainsLair {
	
	Villain testVillain = Villain.CALLCENTREOPERATOR;
	Team testTeam = new Team("TestTeam");
	City testCity = new City("Springfield", testVillain, testTeam);
	VillainsLair testVillainsLair = new VillainsLair(testCity, testVillain, testTeam);

	@Test
	public void testGetVillainImage() {
		//Check that an image is returned
		ImageIcon testIcon = new ImageIcon();
		assertEquals(testVillainsLair.getVillainImage().getClass(), testIcon.getClass());
	}

}
