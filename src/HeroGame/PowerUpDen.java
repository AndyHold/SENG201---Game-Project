package HeroGame;

/**
 * PowerUpDen Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class PowerUpDen extends Location {
	
	
	
	PowerUpDen(City thisCity, Team heroTeam) {
		super(thisCity.getPlaceName(thisCity.getName() + " PowerUpDen"), heroTeam, thisCity.getName(), LocationType.POWERUPDEN);
	}
	
	
	public void listOptions() {
		System.out.println("Current Options Are:");
		System.out.println("1) Move to another location");
		System.out.println("2) Use a power up");
		//System.out.println("3) View current power ups being used?");
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
			this.applyPowerUp();
			return false;		
		}
		return false;
	}
	
	
	
	public void applyPowerUp() {
		heroTeam.showPowerUps();
		int powerUpIndex = this.getSelector().intSelector(0, heroTeam.getPowerUpsSize() - 1, "Please select a power up:", "Invalid selection, Please try again");
		heroTeam.showTeamMembers();
		int teamMemberIndex = this.getSelector().intSelector(0, heroTeam.getTeamSize() - 1, "Please select a Hero to apply to:", "Invalid selection, Please try again");
		heroTeam.applyPowerUp(powerUpIndex, teamMemberIndex);
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
