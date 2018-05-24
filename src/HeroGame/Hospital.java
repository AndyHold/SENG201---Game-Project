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
	
	
	@Override
	public String toString() {
		return this.getName();
	}

}
