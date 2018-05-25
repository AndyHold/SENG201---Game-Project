package HeroGame;
import java.text.MessageFormat;
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
	private boolean mapped = false;
	private HashMap<String, String> placeNames = new HashMap<String, String>();
	private Random rand = new Random();
	private ArrayList<Location> availableLocations = new ArrayList<Location>();
	private ArrayList<String> badDirectionMessages;
	
	
	/**
	 * Constructor method for City class
	 * @param newName String, Name of the city
	 * @param newVillain Villain, Villain in the city
	 * @param newTeam Team, Team being controlled by the player.
	 */
	public City(String newName, Villain newVillain, Team newTeam) {
		this.cityName = newName;
		this.cityVillain = newVillain;
		this.ruler = cityVillain.getName();
		this.currentTeam = newTeam;
		this.setPlaceNames();
		this.fillAvailableLocations();
		this.centerArea = new HomeBase(this, this.currentTeam);
		this.setDirections();
		this.setBadDirectionMessages();
	}
	
	
	/**
	 * Method to get the location in a given direction
	 * @param direction Direction, direction of required location
	 * @return Location, location in given direction for this city
	 */
	public Location getLocation(Direction direction) {
		switch(direction) { // returns the location in the given direction
		case NORTH:
			return northArea;
		case SOUTH:
			return southArea;
		case WEST:
			return westArea;
		case EAST:
			return eastArea;
		case CENTER:
			return centerArea;
		}
		return null;
	}
	
	
	/**
	 * Method to create and fill the ArrayList of badDirectionMessages, for use when the user tries to go the wrong way.
	 */
	private void setBadDirectionMessages() {
		badDirectionMessages = new ArrayList<String>();// create new arraylist
		badDirectionMessages.add("Can't go that way cuz, Jake the Muss is down there"); // fill arraylist with error messages
		badDirectionMessages.add("That area has been hit by an earthquake, better turn around...");
		badDirectionMessages.add("You have reached the ocean. Well Done! But you can't go any further");
		badDirectionMessages.add("Jim's Mowers are currently grooming that area, please keep clear");
		badDirectionMessages.add("A sleeping politician is blocking the path...");
		badDirectionMessages.add("You forgot to bring your 4WD, best be safe and turn around...");
	}
	
	
	/**
	 * Method to  a random error message for occasions when the user tries to go the wrong way
	 * @return String, Bad direction message
	 */
	public String getBadDirectionMessage() {
		int n = rand.nextInt(this.badDirectionMessages.size()); // create random int
		return badDirectionMessages.get(n); // return indexed message from the arraylist
	}
	
	
	/**
	 * Method to return Team currently being controlled by the user
	 * @return Team, User's team.
	 */
	public Team getTeam() {
		return this.currentTeam;
	}
	
	
	/**
	 * Method to get the location in the north area of the current City
	 * @return Location, north location of the current City
	 */
	public Location getNorthLocation() {
		return this.northArea;
	}
	
	
	/**
	 * Method to get the location in the south area of the current City
	 * @return Location, south location of the current City
	 */
	public Location getSouthLocation() {
		return this.southArea;
	}
	
	
	/**
	 * Method to get the location in the west area of the current City
	 * @return Location, west location of the current City
	 */
	public Location getWestLocation() {
		return this.westArea;
	}
	
	
	/**
	 * Method to get the location in the east area of the current City
	 * @return Location, east location of the current City
	 */
	public Location getEastLocation() {
		return this.eastArea;
	}
	
	
	/**
	 * Method to get the location in the center area of the current City
	 * @return Location, centre location of the current City
	 */
	public Location getCenterLocation() {
		return this.centerArea;
	}
	
	
	/**
	 * Method to get the Villain controlling the current City
	 * @return Villain, current City's Villain
	 */
	public Villain getVillain() {
		return this.cityVillain;
	}
	
	
	/**
	 * Method to get whether or not the City is mapped yet.
	 * @return boolean, true if City is mapped already.
	 */
	public boolean isMapped() {
		return this.mapped;
	}
	
	
	/**
	 * Method to change the City to mapped
	 */
	public void makeMapped() {
		this.mapped = true;
	}
	
	
	/**
	 * Method to fill the HashMap with the location names for each City/Location type.
	 */
	private void setPlaceNames() { // fills the hashmap with "cityname locationtype" as the key and the location name as the value 
		this.placeNames.put("Springfield HomeBase", "Springfield Town Centre");
		this.placeNames.put("Springfield Hospital", "The Bealey Hotel");
		this.placeNames.put("Springfield PowerUpDen", "The Yelloshack Cafe");
		this.placeNames.put("Springfield Shop", "Springfield Shop");
		
		this.placeNames.put("Te Puke HomeBase", "Pyes Pa, Te Puke");
		this.placeNames.put("Te Puke Hospital", "Te Puke Tavern");
		this.placeNames.put("Te Puke PowerUpDen", "The Daily Cafe");
		this.placeNames.put("Te Puke Shop", "Te Puke New World");
		
		this.placeNames.put("Gore HomeBase", "Gore Country Music Club");
		this.placeNames.put("Gore Hospital", "The Royal Hotel Gore");
		this.placeNames.put("Gore PowerUpDen", "The Green Room Cafe");
		this.placeNames.put("Gore Shop", "Gore Countdown");
		
		this.placeNames.put("Ohakune HomeBase", "Ohakune Carrot Adventure Park");
		this.placeNames.put("Ohakune Hospital", "King's Pub Ohakune");
		this.placeNames.put("Ohakune PowerUpDen", "Stutz Cafe");
		this.placeNames.put("Ohakune Shop", "Ohakune Take Note Bookshop");
		
		this.placeNames.put("Paeroa HomeBase", "Paeroa Arts Centre");
		this.placeNames.put("Paeroa Hospital", "Waikino Hotel Paeroa");
		this.placeNames.put("Paeroa PowerUpDen", "Cosy Kitchen Cafe");
		this.placeNames.put("Paeroa Shop", "Paeroa Op Shop");
		
		this.placeNames.put("Taihape HomeBase", "Taihape Railway Station");
		this.placeNames.put("Taihape Hospital", "Gretna Hotel Taihape");
		this.placeNames.put("Taihape PowerUpDen", "Le Cafe Telephonique");
		this.placeNames.put("Taihape Shop", "Taihape Four Square");
	}
	
	
	/**
	 * Looks up the placeName dictionary and returns the place name.
	 * @param key String, City name and Place type representation.
	 * @return String, PlaceName for location.
	 */
	public String getPlaceName(String key) {
		return this.placeNames.get(key);
	}
	
	
	/**
	 * Method to get the City's name
	 * @return String, Name of the City
	 */
	public String getName() {
		return this.cityName;
	}
	
	
	/**
	 * Method to set each direction on the map (N, S, E, W) to a random Location.
	 */
	public void setDirections() { // gets a random location and puts it in each direction
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
	 * Method to get a random Location
	 * @return Location Returns one of the four Location types specific to this City.
	 */
	private Location getLocation() {
		int n = this.rand.nextInt(availableLocations.size()); // create random int
		Location location = availableLocations.get(n); // returns indexed location from arraylist after removing it from the arraylist
		this.availableLocations.remove(n);
		return location;
	}
	
	
	/**
	 * Method to fill the ArrayList availableLocations with the 4 types of Location (excluding HomeBase)
	 */
	private void fillAvailableLocations() {
		availableLocations.add(new VillainsLair(this, cityVillain, this.currentTeam)); // fills arraylist with a location of each type accept home base 
		availableLocations.add(new Hospital(this, this.currentTeam));
		availableLocations.add(new PowerUpDen(this, this.currentTeam));
		availableLocations.add(new Shop(this, this.currentTeam));
	}
	

	
	@Override
	public String toString() {
		return MessageFormat.format("Welcome to {0} currently ruled by {1}", cityName, ruler);
	}


}
