package HeroGame;
import java.text.DecimalFormat;

/**
 * Hospital Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class Hospital extends Location {
	
	
	/**
	 * Constructor method for Hospital class
	 * @param thisCity City, City containing the hospital.
	 * @param newTeam Team, Team being controlled by the user.
	 */
	Hospital(City thisCity, Team newTeam) {
		super(thisCity.getPlaceName(thisCity.getName() + " Hospital"), newTeam, thisCity.getName(), LocationType.HOSPITAL);
	}
	
	
	/**
	 * Method to list available options for player to choose from
	 */
	public void listOptions() {
		System.out.println("Welcome to " + this.getName() + ":");
		System.out.println("1) Move to another Location");
		System.out.println("2) Heal a hero");
		System.out.println("3) Show current healing timer(s)");
	}
	
	
	/**
	 * Run loop for Hospital class
	 * @return int, Number corresponding to the direction to move to next.
	 */
	public int runLocation() {
		boolean finishedInLocation = false;
		while(!finishedInLocation) {
			this.listOptions();
			int n = this.getSelector().intSelector(1, 3, "Please select an option", "Invalid option, try again");
			finishedInLocation = this.runOption(n);
		}
		int n = this.moveLocations();
		return n;
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
			this.useHealingItem();
			return false;
			
		case 3:
			//this.checkHealingStatus();
			return false;
			
		
		}
		return false;
	}
	
	
	/**
	 * Prompts user for which healing item to use and which Hero to apply it to and does so.
	 */
	public void useHealingItem() {
		this.heroTeam.showHealingItems();
		int healingItemIndex = this.getSelector().intSelector(0, this.heroTeam.getHealingItemsSize(), "Please select a healing item", "Invalid healing item please try again");
		HealingItemType healingItemType = heroTeam.getHealingItemType(healingItemIndex);
		this.heroTeam.listHeroes();
		int teamMemberIndex = this.getSelector().intSelector(0, this.heroTeam.getTeamSize(), "Please select a Hero to heal", "Invalid Hero please try again");
		this.heroTeam.removeHealingItem(healingItemIndex);
		this.heroTeam.applyHealingItem(healingItemType, teamMemberIndex);
	}
	
	/**
	 * Check on healing times for each team member
	 * @return a string the remaining tiem for each member
	 */
	public String checkHealingTimes() {
		DecimalFormat df2 = new DecimalFormat("#.##"); //Java <= Python
		String result = "The following team members have healing items applying:\n";
		
		for (int i = 0; i < heroTeam.getTeamSize(); i++) {//Iterate through team, check time remaining for each member
			double remTime = heroTeam.getHero(i).checkHealingItemTime(heroTeam.getTime());//Also applies any health benefits
			if (remTime > 0) {//If any health benefit still outstanding, adds to the string
				result += heroTeam.getHero(i).getName() + ": " + df2.format(remTime) + " seconds remaining. \n";
			}
			
		}
		return result;
	}

	/**
	 * Method to check if given direction is allowable.
	 * @param n int, Number corresponding to the direction given by the player.
	 * @return boolean, returns true if player can move in that direction.
	 */
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
