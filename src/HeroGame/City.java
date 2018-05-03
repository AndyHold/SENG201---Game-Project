package HeroGame;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;


/**
 * City Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class City {
	
	private String cityName;
	private Team currentTeam;
	private Villain cityVillain;
	private HomeBase centerArea;
	private Location northArea;
	private Location eastArea;
	private Location southArea;
	private Location westArea;
	private String ruler;
	private boolean completed = false;
	private boolean mapped = false;
	private HashMap<String, String> placeNames = new HashMap<String, String>();
	private Random rand = new Random();
	private ArrayList<Location> availableLocations = new ArrayList<Location>();
	
	
	/**
	 * Constructor method for City class
	 * @param newName String, Name of the city
	 * @param newVillain Villain, Villain in the city
	 * @param newTeam Team, Team being controlled by the player.
	 */
	City(String newName, Villain newVillain, Team newTeam) {
		this.cityName = newName;
		this.cityVillain = newVillain;
		this.ruler = cityVillain.getName();
		this.currentTeam = newTeam;
		this.setPlaceNames();
		this.fillAvailableLocations();
		this.centerArea = new HomeBase(this, this.currentTeam);
		this.setDirections();
	}
	
	
	/**
	 * prints the name of the location in each direction of the city map.
	 */
	public void showMap() {
		System.out.println("To the South is:");
		System.out.println(southArea.getName());
		System.out.println("To the East is:");
		System.out.println(eastArea.getName());
		System.out.println("To the North is:");
		System.out.println(northArea.getName());
		System.out.println("To the West is:");
		System.out.println(westArea.getName());
	}
	
	
	/**
	 * Method to get whether or not the city is mapped yet.
	 * @return boolean, true if city is mapped already.
	 */
	public boolean isMapped() {
		return this.mapped;
	}
	
	
	/**
	 * Method to change the city to mapped
	 */
	public void makeMapped() {
		this.mapped = true;
	}
	
	
	/**
	 * Method to fill the HashMap with the location names for each city/location type.
	 */
	private void setPlaceNames() {
		this.placeNames.put("Springfield HomeBase", "");
		this.placeNames.put("Springfield Hospital", "");
		this.placeNames.put("Springfield PowerUpDen", "");
		this.placeNames.put("Springfield Shop", "");
		
		this.placeNames.put("Te Puke HomeBase", "");
		this.placeNames.put("Te Puke Hospital", "");
		this.placeNames.put("Te Puke PowerUpDen", "");
		this.placeNames.put("Te Puke Shop", "");
		
		this.placeNames.put("Gore HomeBase", "");
		this.placeNames.put("Gore Hospital", "");
		this.placeNames.put("Gore PowerUpDen", "");
		this.placeNames.put("Gore Shop", "");
		
		this.placeNames.put("Ohakune HomeBase", "");
		this.placeNames.put("Ohakune Hospital", "");
		this.placeNames.put("Ohakune PowerUpDen", "");
		this.placeNames.put("Ohakune Shop", "");
		
		this.placeNames.put("Paeroa HomeBase", "");
		this.placeNames.put("Paeroa Hospital", "");
		this.placeNames.put("Paeroa PowerUpDen", "");
		this.placeNames.put("Paeroa Shop", "");
		
		this.placeNames.put("Taihape HomeBase", "");
		this.placeNames.put("Taihape Hospital", "");
		this.placeNames.put("Taihape PowerUpDen", "");
		this.placeNames.put("Taihape Shop", "");
	}
	
	
	/**
	 * consults the place name dictionary and returns the place name.
	 * @param key String, City name and Place type representation.
	 * @return String, PlaceName for location.
	 */
	public String getPlaceName(String key) {
		return this.placeNames.get(key);
	}
	
	
	/**
	 * Method to get the city's name
	 * @return String, Name of the city
	 */
	public String getName() {
		return this.cityName;
	}
	
	
	/**
	 * Method to set each direction of the map with a random location.
	 */
	public void setDirections() {
		this.eastArea = this.getLocation();
		this.eastArea.setDirection(Direction.EAST);
		this.westArea = this.getLocation();
		this.westArea.setDirection(Direction.WEST);
		this.southArea = this.getLocation();
		this.southArea.setDirection(Direction.SOUTH);
		this.northArea = this.getLocation();
		this.northArea.setDirection(Direction.NORTH);
	}
	
	
	/**
	 * Method to get a random location
	 * @return Location Returns one of the four location types specific to this city.
	 */
	private Location getLocation() {
		int n = this.rand.nextInt(availableLocations.size());
		Location location = availableLocations.get(n);
		this.availableLocations.remove(n);
		return location;
	}
	
	
	/**
	 * Method to fill the ArrayList availableLocations with the 4 types of location (excluding HomeBase)
	 */
	private void fillAvailableLocations() {
		availableLocations.add(new VilliansLair(this, cityVillain, this.currentTeam));
		availableLocations.add(new Hospital(this, this.currentTeam));
		availableLocations.add(new PowerUpDen(this, this.currentTeam));
		availableLocations.add(new Shop(this, this.currentTeam));
	}
	
	
	/**
	 * run loop for this city.
	 */
	public void runCity() {
		int n = this.centerArea.runLocation();
		boolean finishedCity = false;
		while(!finishedCity) {
			switch(n) {
			case 0:
				n = this.centerArea.runLocation();
				
			case 1:
				switch(this.northArea.getLocationType()) {
				
				case HOSPITAL:
					((Hospital) this.northArea).runLocation();
					break;
					
				case VILLIANSLAIR:
					((VilliansLair) this.northArea).runLocation();
					break;
					
				case SHOP:
					((Shop) this.northArea).runLocation();
					break;
					
				case POWERUPDEN:
					((PowerUpDen) this.northArea).runLocation();
					break;
				}
				
				
			case 2:
				switch(this.eastArea.getLocationType()) {
				
				case HOSPITAL:
					((Hospital) this.eastArea).runLocation();
					break;
					
				case VILLIANSLAIR:
					((VilliansLair) this.eastArea).runLocation();
					break;
					
				case SHOP:
					((Shop) this.eastArea).runLocation();
					break;
					
				case POWERUPDEN:
					((PowerUpDen) this.eastArea).runLocation();
					break;
				}
				
				
			case 3:
				switch(this.southArea.getLocationType()) {
				
				case HOSPITAL:
					((Hospital) this.southArea).runLocation();
					break;
					
				case VILLIANSLAIR:
					((VilliansLair) this.southArea).runLocation();
					break;
					
				case SHOP:
					((Shop) this.southArea).runLocation();
					break;
					
				case POWERUPDEN:
					((PowerUpDen) this.southArea).runLocation();
					break;
				}
				
				
			case 4:
				switch(this.westArea.getLocationType()) {
				
				case HOSPITAL:
					((Hospital) this.westArea).runLocation();
					break;
					
				case VILLIANSLAIR:
					((VilliansLair) this.westArea).runLocation();
					break;
					
				case SHOP:
					((Shop) this.westArea).runLocation();
					break;
					
				case POWERUPDEN:
					((PowerUpDen) this.westArea).runLocation();
					break;
				}
				
				
			}
		}
	}
	
	
	@Override
	public String toString() {
		return "Welcome to " + cityName + "currently ruled by " + ruler;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Team testTeam = new Team("Test Team");
//		Hero h1 = new Hero("Cletus", HeroType.ALL_BLACK);
//		Hero h2 = new Hero("Ethel Aardvark", HeroType.SURVEYOR);
//		Hero h3 = new Hero("Abraham Lincoln", HeroType.SURVEYOR);
//		testTeam.addMember(h1);
//		testTeam.addMember(h2);
//		testTeam.addMember(h3);
//		Villain peter = Villain.AUSSIECRICKETER;
//		City newCity = new City("Taihape", peter, testTeam);
//		System.out.println(newCity.getName());
	}

}
