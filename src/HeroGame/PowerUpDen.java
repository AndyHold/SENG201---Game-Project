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
	
	
	/**
	 * Method to list available options for player to choose from
	 */
	public void listOptions() {
		System.out.println("Welcome to " + this.getName() + ":");
		System.out.println("1) Move to another location");
		System.out.println("2) Use a power up");
		//System.out.println("3) View current power ups being used?");
	}
	
	
	/**
	 * Run loop for PowerUpDen class
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
			this.applyPowerUp();
			return false;		
		}
		return false;
	}
	
	
	/**
	 * Method to prompt user for a power up and a hero and call the team to apply it.
	 */
	public void applyPowerUp() {		
		heroTeam.showPowerUps();
		int powerUpIndex = this.getSelector().intSelector(0, heroTeam.getPowerUpsSize() - 1, "Please select a power up:", "Invalid selection, Please try again");
		PowerUpType powerUpType = heroTeam.getPowerUpType(powerUpIndex);
		heroTeam.listHeroes();
		int teamMemberIndex = this.getSelector().intSelector(0, heroTeam.getTeamSize() - 1, "Please select a Hero to apply to:", "Invalid selection, Please try again");
		this.heroTeam.removePowerUp(powerUpIndex);
		heroTeam.applyPowerUp(powerUpType, teamMemberIndex);
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
