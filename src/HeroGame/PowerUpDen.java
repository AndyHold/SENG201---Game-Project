package HeroGame;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * PowerUpDen Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class PowerUpDen extends Location {
	
	
	private ImageIcon interior = new ImageIcon(PowerUpDen.class.getResource("/HeroGame/Images/pub_interior.jpg"));
	private Random rand = new Random();
	private ArrayList<String> badPowerUpMessages;
	
	
	/**
	 * Constructor Method for PowerUpDen Class
	 * @param thisCity City, city containing this power up den
	 * @param heroTeam Team, current team being played by the user
	 */
	PowerUpDen(City thisCity, Team heroTeam) {
		super(thisCity.getPlaceName(thisCity.getName() + " PowerUpDen"), heroTeam, thisCity.getName(), LocationType.POWERUPDEN, new ImageIcon(CityScreen.class.getResource("/HeroGame/Images/PowerUpDen.png")));
		setBadPowerUpMessages();
	}
	
	
	/**
	 * Method to create and fill the ArrayList badHealingItems with error messages for no healing item
	 */
	private void setBadPowerUpMessages() {
		badPowerUpMessages = new ArrayList<String>();
		badPowerUpMessages.add("{0} breathed in the empty space where the non existant power up should have been, nothing happened!");
		badPowerUpMessages.add("{0} swiped at nothingness for a while before realising no power up was chosen");
		badPowerUpMessages.add("{0} burst into tears at the sight of nothingness you have supplied...for shame...");
	}
	
	
	/**
	 * Method to return a random badHealingItem message for the error box.
	 * @return String, message for the Error Box
	 */
	public String getBadPowerUpMessage() {
		int n = rand.nextInt(badPowerUpMessages.size());
		return badPowerUpMessages.get(n);
	}
	
	
	/**
	 * Method to get the image associated with the interior of the power up den
	 * @return ImageIcon, image representing the interior of the power up den
	 */
	public ImageIcon getInteriorImage() {
		return interior;
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


	public String checkPowerUps() {
		String result = "<html>The following team members have power ups:<br>";
		
		for (Hero hero: heroTeam.getMemberList()) {//Iterate through team, check time remaining for each member
			PowerUp powerUp = hero.getPowerUp();
			if(powerUp != null) {
				result += MessageFormat.format("{0} currently has {1}<br>", hero.getName(), powerUp.getLongDescription());
			}
		}
		result += "</html>";
		return result;
	}

}
