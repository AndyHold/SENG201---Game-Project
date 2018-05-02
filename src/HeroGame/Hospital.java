package HeroGame;

/**
 * Hospital Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class Hospital extends Location {
	
	
	Hospital(City thisCity, Team newTeam) {
		super(thisCity.getPlaceName(thisCity.getName() + " Hospital"), newTeam, thisCity.getName(), LocationType.HOSPITAL);
		
	}
	
	
	public void listOptions() {
		System.out.println("Current Options Are:");
		System.out.println("1) Move to another Location");
		System.out.println("2) Heal a hero");
		System.out.println("3) Show current healing timer(s)");
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
			//this.useHealingItem();
			return false;
			
		case 3:
			//this.checkHealingStatus();
			return false;
			
		
		}
		return false;
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

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
