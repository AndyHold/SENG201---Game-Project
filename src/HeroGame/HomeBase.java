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
		interiors.put("Springield", new ImageIcon(HomeBase.class.getResource("/HeroGame/Images/springfieldHomeBase.jpg")));
		interiors.put("Te Puke", new ImageIcon(HomeBase.class.getResource("/HeroGame/Images/tePukeHomeBase.jpg")));
		interiors.put("Gore", new ImageIcon(HomeBase.class.getResource("/HeroGame/Images/goreHomeBase.jpg")));
		interiors.put("Ohakune", new ImageIcon(HomeBase.class.getResource("/HeroGame/Images/ohakuneHomeBase.jpg")));
		interiors.put("Paeroa", new ImageIcon(HomeBase.class.getResource("/HeroGame/Images/paeroaHomeBase.jpg")));
		interiors.put("Taihape", new ImageIcon(HomeBase.class.getResource("/HeroGame/Images/taiHapeHomeBase.jpg")));
	}


	/**
	 * Shows the Team's heroes and their current status. Command line version only.
	 */
	@Deprecated
	public void teamStatus() {
		super.heroTeam.listHeroes();
		int n = this.getSelector().intSelector(0, heroTeam.getTeamSize(), "Which Hero would you like to see?", "Invalid Hero choice please try again");
		System.out.println(this.heroTeam.getHero(n).heroStatus());
	}
	
	
	/**
	 * Shows a list of items currently held by the Team. Command line veriosn only.
	 */
	@Deprecated
	public void teamInventory() {
		System.out.println("Current PowerUps in Inventory:");
		this.heroTeam.showPowerUps();
		System.out.println("Current Healing Items in Inventory:");
		this.heroTeam.showHealingItems();
	}
	
	
	/**
	 * If the Team has enough maps:
	 * Consumes a map and lists the places in the current City.
	 * If the City is mapped, lists the places in the current City.
	 * Command line version only
	 */
	@Deprecated
	public void useMap() {
		if(!this.currentCity.isMapped() && (this.heroTeam.getMaps() > 0)) {
			this.currentCity.makeMapped();
			this.heroTeam.changeMaps(-1);;
			this.currentCity.showMap();
		} else {
			if(this.currentCity.isMapped()) {
				this.currentCity.showMap();
			}
		}
		System.out.println("Mapping failed, you have insufficient maps.");
	}
	
	
	/**
	 * Method to list available options for player to choose from. Command line version of the game only
	 */
	@Deprecated
	public void listOptions() {
		System.out.println("Current Options Are:");
		System.out.println("1) Move to another location");
		System.out.println("2) Use a Map");
		System.out.println("3) View status and attributes of a hero");
	}
	
	
	/**
	 * Run loop for HomeBase class in the command line version of the game
	 * @return int, number corresponding to the direction user wishes to move next
	 */
	@Deprecated
	public int runLocation() {
		boolean finishedInLocation = false;
		while(!finishedInLocation) {
			this.listOptions();
			int n = this.getSelector().intSelector(1, 3, "Please select an option", "Invalid option, try again");
			finishedInLocation = runOption(n);
		}
		return this.moveLocations();
	}

	
	/**
	 * Method to choose which method to run based on input from the user. Command line version only
	 * @param n int, used in the switch statement to find which method to run.
	 * @return boolean, true if finished in location and want to move, false if need menu again.
	 */
	@Deprecated
	public boolean runOption(int n) {
		
		switch(n) {
		
		case 1:
			return true;
			
		case 2:
			this.useMap();
			return false;
			
		case 3:
			this.teamStatus();
			return false;
			
		
		}
		return false;
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
