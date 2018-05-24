package HeroGame;

import java.util.HashMap;

import javax.swing.ImageIcon;

/**
 * HomeBase Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class HomeBase extends Location {
	
	
	private City currentCity;
	private CityScreen currentCityScreen;
	private ImageIcon interior;
	private HashMap<String, ImageIcon> interiors = new HashMap<String, ImageIcon>();
	

	/**
	 * Constructor for HomeBase Class Command Line Version
	 * @param cityName String, Name of the city HomeBase is located in.
	 * @param newTeam Team, Team being controlled by the Player.
	 */
	public HomeBase(City thisCity, Team newTeam) {
		super(thisCity.getPlaceName(thisCity.getName() + " HomeBase"), newTeam, thisCity.getName(), LocationType.HOMEBASE, new ImageIcon(CityScreen.class.getResource("/HeroGame/Images/HomeBase.png")));
		this.currentCity = thisCity;
		super.setDirection(Direction.CENTER);
		populateImages();
		interior = interiors.get(thisCity.getName());
	}
	
	
	/**
	 * Method to get the Image associated with the interior of the Home Base
	 * @return ImageIcon, Image associated with the interior of the Home Base
	 */
	public ImageIcon getInteriorImage() {
		return this.interior;
	}
	
	
	/**
	 * Populates Images for HashMap interiors
	 */
	private void populateImages() {
		interiors.put("Springfield", new ImageIcon(HomeBase.class.getResource("/HeroGame/Images/springfieldHomeBase.jpg")));
		interiors.put("Te Puke", new ImageIcon(HomeBase.class.getResource("/HeroGame/Images/tePukeHomeBase.jpg")));
		interiors.put("Gore", new ImageIcon(HomeBase.class.getResource("/HeroGame/Images/goreHomeBase.jpg")));
		interiors.put("Ohakune", new ImageIcon(HomeBase.class.getResource("/HeroGame/Images/ohakuneHomeBase.jpg")));
		interiors.put("Paeroa", new ImageIcon(HomeBase.class.getResource("/HeroGame/Images/paeroaHomeBase.jpg")));
		interiors.put("Taihape", new ImageIcon(HomeBase.class.getResource("/HeroGame/Images/taiHapeHomeBase.jpg")));
	}


	
	@Override
	public String toString() {
		if(currentCity.isMapped()) {
			return this.getName() + "\n" + "Current Options:" + "\n" + "See Team Status" + "\n" + "See Team Inventory" + "\n" + "Show Map";
		} else {
			return this.getName() + "\n" + "Current Options:" + "\n" + "See Team Status" + "\n" + "See Team Inventory" + "\n" + "Use a Map";
		}
		
	}
	
}
