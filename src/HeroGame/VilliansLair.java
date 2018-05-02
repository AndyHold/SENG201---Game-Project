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
	
	
	public void listOptions() {
		System.out.println("Current Options Are:");
		System.out.println("1) Move to another location");
		System.out.println("2) Enter Villians Lair");
	}
	
	
	public int runLocation() {
		boolean finishedInLocation = false;
		while(!finishedInLocation) {
			listOptions();
			int n = this.getSelector().intSelector(1, 2, "Please select an option", "Invalid option, try again");
			finishedInLocation = runOption(n);
		}
		return moveLocations();
	}

	
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
