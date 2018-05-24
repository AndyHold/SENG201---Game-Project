package HeroGame;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestHomeBase {
	
	Villain testVillain = Villain.CALLCENTREOPERATOR;
	Team testTeam = new Team("TestTeam");
	City testCity = new City("Springfield", testVillain, testTeam);
	HomeBase testHomeBase = new HomeBase(testCity, testTeam);

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
	public void testGetInteriorImage() {
		//Test that an image is returned
		ImageIcon icon = new ImageIcon();
		assertEquals(testHomeBase.getInteriorImage().getClass(), icon.getClass());
	}


	@Test
	public void testGetIcon() {
		//Test that an image is returned
		ImageIcon icon = new ImageIcon();
		assertEquals(testHomeBase.getIcon().getClass(), icon.getClass());
	}

}
