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
	
	
	private ImageIcon interior = new ImageIcon(PowerUpDen.class.getResource("/HeroGame/Images/cafe_interior.jpg"));
	private Random rand = new Random();
	private ArrayList<String> badPowerUpMessages;
	
	
	/**
	 * Constructor Method for PowerUpDen Class
	 * @param thisCity City, city containing this power up den
	 * @param heroTeam Team, current team being played by the user
	 */
	public PowerUpDen(City thisCity, Team heroTeam) {
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
	 * Method to get the image associated with the interior of the PowerUpDen
	 * @return ImageIcon, image representing the interior of the PowerUpDen
	 */
	public ImageIcon getInteriorImage() {
		return interior;
	}
	
	
	/**
	 * Returns an HTML tagged String showing which Team members have PowerUps applied
	 * @return an HTML tagged String showing which Team members have PowerUps applied
	 */
	public String checkPowerUps() {
		String result = "<html>The following team members have power ups:<br>";
		
		for (Hero hero: heroTeam.getMemberList()) {//Iterate through team, check time remaining for each member
			PowerUp powerUp = hero.getPowerUp();
			if(powerUp != null) {
				result += MessageFormat.format("{0}: {1}<br>", hero.getName(), powerUp.toString());
			}
		}
		result += "</html>";
		return result;
	}

}
