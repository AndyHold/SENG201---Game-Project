package HeroGame;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestVillainsLair {
	
	Villain testVillain = Villain.CALLCENTREOPERATOR;
	Team testTeam = new Team("TestTeam");
	City testCity = new City("Springfield", testVillain, testTeam);
	VillainsLair testVillainsLair = new VillainsLair(testCity, testVillain, testTeam);

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
	void testGetVillainImage() {
		//Check that an image is returned
		ImageIcon testIcon = new ImageIcon();
		assertEquals(testVillainsLair.getVillainImage().getClass(), testIcon.getClass());
	}

}
