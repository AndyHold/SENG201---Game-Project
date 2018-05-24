package HeroGame;

import javax.swing.ImageIcon;


/**
 * Location SuperClass for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class Location {
	
	private ImageIcon icon;
	private String placeName;
	protected Team heroTeam;
	protected String cityName;
	private Direction locationOnMap;
	private LocationType locationType;
	
	
	/**
	 * Constructor method for Location class Text Version
	 * @param locationName String, Name of the location
	 * @param newTeam Team, Team being controller by the user
	 * @param newCityName String, Name of the city containing the location
	 * @param newType LocationType, Enum of the type of location
	 */
	public Location(String locationName, Team newTeam, String newCityName, LocationType newType, ImageIcon newIcon) {
		this.placeName = locationName;
		this.heroTeam = newTeam;
		this.cityName = newCityName;
		this.locationType = newType;
		this.icon = newIcon;
	}
	
	
	/**
	 * Method to get icon
	 * @return ImageIcon, icon associated with this location
	 */
	public ImageIcon getIcon() {
		return icon;
	}
	
	
	/**
	 * Method to get the type of location
	 * @return LocationType, Enum of the type of location this is 
	 */
	public LocationType getLocationType() {
		return this.locationType;
	}
	
	
	/**
	 * Method to set the direction this location is set to within the city.
	 * @param currentDirection Direction, Direction this location is set to within the city.
	 */
	public void setDirection(Direction currentDirection) {
		this.locationOnMap = currentDirection;
	}

	
	/**
	 * Method to get the direction allocated to this location
	 * @return Direction, Direction Enum associated with this location
	 */
	public Direction getDirection() {
		return this.locationOnMap;
	}
	
	
	/**
	 * Method to get the name of the location
	 * @return String, Name of the location
	 */
	public String getName() {
		return this.placeName;
	}
	
}
