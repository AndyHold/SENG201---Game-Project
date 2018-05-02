package HeroGame;

/**
 * HomeBase Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class HomeBase extends Location {
	
	
	private City currentCity;
	
	
	
	
	/**
	 * Constructor for HomeBase Class
	 * @param cityName String name of the city HomeBase is located in.
	 * @param newTeam current Team being controlled by the Player.
	 */
	HomeBase(City thisCity, Team newTeam) {
		super(thisCity.getPlaceName(thisCity.getName() + " HomeBase"), newTeam, thisCity.getName(), LocationType.HOMEBASE);
		this.currentCity = thisCity;
		super.setDirection(Direction.CENTER);
	}

	
	/**
	 * Shows the Teams Heroes and their current Status
	 */
	public void teamStatus() {
		super.heroTeam.teamStatus();
		
		return "Need to fix this so you can view individual heroes"
	}
	
	
	/**
	 * Shows a list of items currently held by the Team.
	 */
	public void teamInventory() {
		System.out.println("Current PowerUps in Inventory:");
//		heroTeam.showPowerUps();
		System.out.println("Current Healing Items in Inventory:");
//		heroTeam.showHealingItems();
	}
	
	
	/**
	 * Uses a map and lists the places in the current city.
	 */
	public void useMap() {
		if(!currentCity.isMapped() && (heroTeam.getMaps() > 0)) {
			currentCity.makeMapped();
			heroTeam.changeMaps(-1);;
			currentCity.showMap();
		} else {
			if(currentCity.isMapped()) {
				currentCity.showMap();
			}
		}
		System.out.println("Mapping failed, you have insufficient maps.");
	}
	
	
	public void listOptions() {
		System.out.println("Current Options Are:");
		System.out.println("1) Move to another location");
		System.out.println("2) Use a Map");
		System.out.println("3) View status and attributes of a hero");
	}
	
	
	public int runLocation() {
		boolean finishedInLocation = false;
		while(!finishedInLocation) {
			listOptions();
			int n = this.getSelector().intSelector(1, 3, "Please select an option", "Invalid option, try again");
			finishedInLocation = runOption(n);
		}
		return moveLocations();
	}

	
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TESTED FROM CITY CLASS
	}

}
