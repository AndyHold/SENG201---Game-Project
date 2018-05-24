package HeroGame;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;


/**
 * Shop Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class Shop extends Location {
	
	
	private ArrayList<HealingItemType> healingItems = new ArrayList<HealingItemType>();
	private ArrayList<PowerUpType> powerUps = new ArrayList<PowerUpType>();
	private ImageIcon interior = new ImageIcon(Shop.class.getResource("/HeroGame/Images/dairy_interior.jpg"));
	private ImageIcon vendor = new ImageIcon(Shop.class.getResource("/HeroGame/Images/vendor.jpg"));
	private ArrayList<String> moneyErrors;
	private Random rand = new Random();
	
	
	/**
	 * Constructor method for Shop Class
	 * @param thisCity City, City containing this shop
	 * @param newTeam Team, Team being controlled by the user
	 */
	Shop(City thisCity, Team newTeam) {
		super(thisCity.getPlaceName(thisCity.getName() + " Shop"), newTeam, thisCity.getName(), LocationType.SHOP, new ImageIcon(Shop.class.getResource("/HeroGame/Images/Shop.png")));
		this.fillMoneyErrors();
	}
	
	
	/**
	 * Method to create and fill the ArrayList lowMoneyErrors for use in the said error box
	 */
	private void fillMoneyErrors() {
		moneyErrors = new ArrayList<String>();
		moneyErrors.add("You have insufficient funds, best do some busking");
		moneyErrors.add("The bank has not approved your loan, leaving you in a sticky situation...");
		moneyErrors.add("You cannot pay with willpower alone");
		moneyErrors.add("Gary the Vendor looks you up and down in disgust as you cannot produce enough coins");
	}
	
	/**
	 * Return one of a random selection of error messages if money is insufficient for a transaction
	 * @return a String the randomly chosen error message
	 */
	public String getMoneyError() {
		int n = this.rand.nextInt(this.moneyErrors.size() - 1);
		return moneyErrors.get(n);
	}


	/**
	 * Method to get the image associated with the interior of the shop
	 * @return ImageIcon, Image associated with the interior of the shop
	 */
	public ImageIcon getInteriorImage() {
		return this.interior;
	}
	
	
	/**
	 * Method to get the image associated with the waitress
	 * @return ImageIcon, Image associated with the waitress
	 */
	public ImageIcon getVendorImage() {
		return this.vendor;
	}
	
	
	/**
	 * Method to fill ArrayList healingItems
	 */
	@Deprecated
	private void fillHealingItems() {
		this.healingItems.add(HealingItemType.DOUBLE_BROWN);
		this.healingItems.add(HealingItemType.LINDAUER);
		this.healingItems.add(HealingItemType.LION_RED);
	}
	
	

	
}
