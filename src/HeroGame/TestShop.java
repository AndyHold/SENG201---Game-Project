package HeroGame;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestShop {
	
	Villain testVillain = Villain.CALLCENTREOPERATOR;
	Team testTeam = new Team("TestTeam");
	City testCity = new City("Springfield", testVillain, testTeam);
	Shop testShop = new Shop(testCity, testTeam);

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
	void testGetMoneyError() {
		//Check that a String is returned
		String testString = new String();
		assertEquals(testString.getClass(), testShop.getMoneyError().getClass());
	}

	@Test
	void testGetInteriorImage() {
		//Check that an image is returned
		ImageIcon testIcon = new ImageIcon();
		assertEquals(testShop.getInteriorImage().getClass(), testIcon.getClass());
	}

	@Test
	void testGetVendorImage() {
		//Check that an image is returned
		ImageIcon testIcon = new ImageIcon();
		assertEquals(testShop.getVendorImage().getClass(), testIcon.getClass());
	}

}
