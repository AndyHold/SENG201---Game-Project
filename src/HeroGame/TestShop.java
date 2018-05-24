package HeroGame;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestShop {
	
	Villain testVillain = Villain.CALLCENTREOPERATOR;
	Team testTeam = new Team("TestTeam");
	City testCity = new City("Springfield", testVillain, testTeam);
	Shop testShop = new Shop(testCity, testTeam);

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
	public void testGetMoneyError() {
		//Check that a String is returned
		String testString = new String();
		assertEquals(testString.getClass(), testShop.getMoneyError().getClass());
	}

	@Test
	public void testGetInteriorImage() {
		//Check that an image is returned
		ImageIcon testIcon = new ImageIcon();
		assertEquals(testShop.getInteriorImage().getClass(), testIcon.getClass());
	}

	@Test
	public void testGetVendorImage() {
		//Check that an image is returned
		ImageIcon testIcon = new ImageIcon();
		assertEquals(testShop.getVendorImage().getClass(), testIcon.getClass());
	}

}
