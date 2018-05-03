package HeroGame;

/**
 * VillainsLair Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class VilliansLair extends Location {
	
	/**
	 * Constructor for VillainsLair Class.
	 * @param cityName Name of the City containing this Villains Lair
	 * @param currentVillian Current Villain of this city.
	 */
	VilliansLair(City thisCity, Villain currentVillian, Team heroTeam) {
		super(currentVillian.getLairName(thisCity.getName()), heroTeam, thisCity.getName(), LocationType.VILLIANSLAIR);
	}
	
	
	/**
	 * Method to list available options for player to choose from
	 */
	public void listOptions() {
		System.out.println("Current Options Are:");
		System.out.println("1) Move to another location");
		System.out.println("2) Enter Villians Lair");
	}
	
	
	/**
	 * Run loop for VillainsLair class
	 * @return int, Number corresponding to the direction to move to next.
	 */
	public int runLocation() {
		boolean finishedInLocation = false;
		while(!finishedInLocation) {
			listOptions();
			int n = this.getSelector().intSelector(1, 2, "Please select an option", "Invalid option, try again");
			finishedInLocation = runOption(n);
		}
		return moveLocations();
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
			Battle currentBattle = new Battle();
			//currentBattle.runBattle();
			return false;		
		}
		return false;
	}
	
	
	@Override
	public String toString() {
		return super.getName() + this.getName();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
