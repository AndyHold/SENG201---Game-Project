
/**
 * HomeBase Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class HomeBase extends Location {
	
	private Team heroTeam;
	private City currentCity;
	
	
	/**
	 * Constructor for HomeBase Class
	 * @param cityName String name of the city HomeBase is located in.
	 * @param newTeam current Team being controlled by the Player.
	 */
	HomeBase(City thisCity, Team newTeam) {
		super(thisCity.getName() + " HomeBase");
		heroTeam = newTeam;
		currentCity = thisCity;
	}

	
	/**
	 * Shows the Teams Heroes and their current Status
	 */
	public void teamStatus() {
		heroTeam.teamStatus();
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
	public void useMap(int powerUpIndex) {
		if(!currentCity.isMapped()) {
			currentCity.makeMapped();
			heroTeam.removePowerUp(powerUpIndex);
			currentCity.showMap();
		}
	}
	
	
	/**
	 * If city is mapped, lists the places in the current city.
	 */
	public void useMap() {
		if(currentCity.isMapped()) {
			currentCity.showMap();
		} else {
			System.out.println("City is not mapped");
		}
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
