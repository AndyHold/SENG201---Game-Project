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
	 * @param cityName String, Name of the city HomeBase is located in.
	 * @param newTeam Team, Team being controlled by the Player.
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
				//Done
	}
	
	
	/**
	 * Shows a list of items currently held by the Team.
	 */
	public void teamInventory() {
		System.out.println("Current PowerUps in Inventory:");
		this.heroTeam.showPowerUps();
		System.out.println("Current Healing Items in Inventory:");
		this.heroTeam.showHealingItems();
	}
	
	
	/**
	 * If the team has enough maps:
	 * Consumes a map and lists the places in the current city.
	 * Or if the city is mapped, lists the places in the current city.
	 */
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
	 * Method to list available options for player to choose from
	 */
	public void listOptions() {
		System.out.println("Current Options Are:");
		System.out.println("1) Move to another location");
		System.out.println("2) Use a Map");
		System.out.println("3) View status and attributes of a hero");
	}
	
	
	/**
	 * Run loop for HomeBase class
	 * @return int, Number corresponding to the direction to move to next.
	 */
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
	 * Method to choose which method to run based on input from the user.
	 * @param n int, used in the switch statement to find which method to run.
	 * @return boolean, true if finished in location and want to move, false if need menu again.
	 */
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
