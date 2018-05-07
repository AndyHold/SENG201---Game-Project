package HeroGame;


/**
 * Place Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class Location {
	
	private String placeName;
	protected Team heroTeam;
	protected String cityName;
	private Direction locationOnMap;
	private Selector optionSelector = new Selector();
	private LocationType locationType;
	
	
	/**
	 * Constructor method for Location class
	 * @param locationName String, Name of the location
	 * @param newTeam Team, Team being controller by the user
	 * @param newCityName String, Name of the city containing the location
	 * @param newType LocationType, Enum of the type of location
	 */
	Location(String locationName, Team newTeam, String newCityName, LocationType newType) {
		this.placeName = locationName;
		this.heroTeam = newTeam;
		this.cityName = newCityName;
		this.locationType = newType;
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
	 * Method to get the selector for use in getting player input
	 * @return Selector, The selector
	 */
	public Selector getSelector() {
		return this.optionSelector;
	}
	
	
	/**
	 * Method to prompt user for a direction to move in
	 * @return int, Number corresponding to the direction to travel in
	 */
	public int moveLocations() {
		boolean locationFound = false;
		int n = 0;
		while(!locationFound) {
			System.out.println("Current options:");
			System.out.println("1) North");
			System.out.println("2) East");
			System.out.println("3) South");
			System.out.println("4) West");
			n = this.optionSelector.intSelector(1, 4, "Please enter a direction: ", "Invalid direction, please try again: ");
			locationFound = this.moveCheck(n);
			if(!locationFound) {
				System.out.println("Can't go that way bro, Jake the Muss is in the way");
			}
		}
		if(this.locationType == LocationType.HOMEBASE) {
			return n;
		} else {
			return 0;
		}
		
	}
	

	/**
	 * Method to check if a move is legitimate
	 * @param n int, Number corresponding to the direction to move in
	 * @return boolean, true if the direction is good.
	 */
	private boolean moveCheck(int n) {
		switch(n) {
		
		case 1:
			if((this.getDirection() == Direction.CENTER) || (this.getDirection() == Direction.SOUTH)) {
				return true;
			}
			break;
		
		case 2:
			if((this.getDirection() == Direction.CENTER) || (this.getDirection() == Direction.WEST)) {
				return true;
			}
			break;
			
		case 3:
			if((this.getDirection() == Direction.CENTER) || (this.getDirection() == Direction.NORTH)) {
				return true;
			}
			break;
		
		case 4:
			if((this.getDirection() == Direction.CENTER) || (this.getDirection() == Direction.EAST)) {
				return true;
			}
			break;
		}
		return false;
		
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
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
