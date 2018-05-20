package HeroGame;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * Hospital Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class Hospital extends Location {
	
	
	private ImageIcon interior = new ImageIcon(Hospital.class.getResource("/HeroGame/Images/pub_interior.jpg"));
	private ArrayList<String> badHealingItemMessages;
	private Random rand = new Random();
	
	
	/**
	 * Constructor method for Hospital class
	 * @param thisCity City, City containing the hospital.
	 * @param newTeam Team, Team being controlled by the user.
	 */
	public Hospital(City thisCity, Team newTeam) {
		super(thisCity.getPlaceName(thisCity.getName() + " Hospital"), newTeam, thisCity.getName(), LocationType.HOSPITAL, new ImageIcon(CityScreen.class.getResource("/HeroGame/Images/Hospital.png")));
	setBadHealingItemMessages();
	}
	

	/**
	 * Method to create and fill the ArrayList badHealingItems with error messages for 
	 * when no HealingItem is present
	 */
	private void setBadHealingItemMessages() {
		badHealingItemMessages = new ArrayList<String>();
		badHealingItemMessages.add("{0} breathed in the empty space where the non existant healing item should have been, nothing happened!");
		badHealingItemMessages.add("{0} swiped at nothingness for a while before realising no item was chosen");
		badHealingItemMessages.add("{0} burst into tears at the sight of nothingness you have supplied...for shame...");
	}
	
	
	/**
	 * Method to return a random badHealingItem message for the error box.
	 * @return String, message for the error box
	 */
	public String getBadHealingItemMessage() {
		int n = rand.nextInt(badHealingItemMessages.size());
		return badHealingItemMessages.get(n);
	}
	
	
	/**
	 * Method to get the image used for the interior of the Hospital
	 * @return ImageIcon, Image associated with the interior of the Hospital
	 */
	public ImageIcon getInteriorImage() {
		return interior;
	}
	
	
	/**
	 * Method to list available options for player to choose from. Command line version only.
	 */
	@Deprecated
	public void listOptions() {
		System.out.println("Welcome to " + this.getName() + ":");
		System.out.println("1) Move to another Location");
		System.out.println("2) Heal a hero");
		System.out.println("3) Show current healing timer(s)");
	}
	
	
	/**
	 * Run loop for Hospital class. Command line version only.
	 * @return int, Number corresponding to the direction to move to next.
	 */
	@Deprecated
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
	 * Method to choose which method to run based on input from the user. Command Line Version Only
	 * @param n int, used in the switch statement to find which method to run.
	 * @return boolean, true if finished in location and want to move, false if need menu again.
	 */
	@Deprecated
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
	 * Prompts user for which HealingItem to use and which Hero to apply it to and does so. Command Line Version Only.
	 */
	@Deprecated
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
	 * Check on healing times for each Team member
	 * @return a string the remaining time for each member
	 */
	public String checkHealingTimes() {
		DecimalFormat df2 = new DecimalFormat("#.##"); //Java <= Python
		String result = "<html>The following team members have healing items applying:<br>";
		
		for (int i = 0; i < heroTeam.getTeamSize(); i++) {//Iterate through team, check time remaining for each member
			double remTime = heroTeam.getHero(i).checkHealingItemTime(heroTeam.getTime());//Also applies any health benefits
			if (remTime > 0) {//If any health benefit still outstanding, adds to the string
				result += heroTeam.getHero(i).getName() + ": " + df2.format(remTime) + " seconds remaining. <br>";
			}
			
		}
		result += "</html>";
		return result;
	}

	/**
	 * Method to check if given direction is allowable. Command Line Version Only
	 * @param n int, Number corresponding to the direction given by the user.
	 * @return boolean, returns true if player can move in that direction.
	 */
	@Deprecated
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
	
	
	@Override
	public String toString() {
		return this.getName();
	}

}
