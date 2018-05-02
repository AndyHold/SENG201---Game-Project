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
	
	
	Location(String locationName, Team newTeam, String newCityName, LocationType newType) {
		this.placeName = locationName;
		this.heroTeam = newTeam;
		this.cityName = newCityName;
		this.locationType = newType;
	}
	
	
	public LocationType getLocationType() {
		return this.locationType;
	}
	
	
	public void setDirection(Direction currentDirection) {
		this.locationOnMap = currentDirection;
	}

	
	public Selector getSelector() {
		return this.optionSelector;
	}
	
	
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
			locationFound = moveCheck(n);
			if(!locationFound) {
				System.out.println("Can't go that way bro, Jake the Muss is in the way");
			}
		}
		return n;
	}
	

	private boolean moveCheck(int n) {
		switch(n) {
		
		case 1:
			if((this.getDirection() == Direction.CENTER) || (this.getDirection() == Direction.SOUTH)) {
				return true;
			}
		
		case 2:
			if((this.getDirection() == Direction.CENTER) || (this.getDirection() == Direction.WEST)) {
				return true;
			}
			
		case 3:
			if((this.getDirection() == Direction.CENTER) || (this.getDirection() == Direction.NORTH)) {
				return true;
			}
		
		case 4:
			if((this.getDirection() == Direction.CENTER) || (this.getDirection() == Direction.EAST)) {
				return true;
			}
		}
		return false;
		
	}
	
	
	
	public Direction getDirection() {
		return this.locationOnMap;
	}
	
	
	public String getName() {
		return placeName;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
