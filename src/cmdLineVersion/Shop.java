package cmdLineVersion;
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
		super(thisCity.getPlaceName(thisCity.getName() + " Shop"), newTeam, thisCity.getName(), LocationType.SHOP);
		this.fillHealingItems();
		this.fillPowerUpItems();
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
	
	
	/**
	 * Method to fill ArrayList powerUpItems
	 */
	@Deprecated
	private void fillPowerUpItems() {
		this.powerUps.add(PowerUpType.CHEESE_ROLL);
		this.powerUps.add(PowerUpType.PAVLOVA);
		this.powerUps.add(PowerUpType.PINEAPPLE_LUMPS);
	}
	
	
	/**
	 * Method to list available options for player to choose from. Command Line Version Only
	 */
	@Deprecated
	public void listOptions() {
		System.out.println("Welcome to " + this.getName() + ":");
		System.out.println("1) Move to another location");
		System.out.println("2) Talk to waitress");
	}
	
	
	/**
	 * Run loop for Shop class. Command Line Version Only
	 * @return int, Number corresponding to the direction to move to next.
	 */
	@Deprecated
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
			this.runInkeeperLoop();
			return false;
		}
		return false;
	}
	
	
	/**
	 * run loop for the Innkeeper. Command Line Version Only
	 */
	@Deprecated
	public void runInkeeperLoop() {
		boolean finished = false;
		System.out.println("Hi there, welcome to " + this.getName() + "how can i help you?:");
		while(!finished) {
			listInnKeeperOptions();
			int n = this.getSelector().intSelector(1, 7, "Please select an option", "Invalid option, try again");
			finished = runInnKeeperOption(n);
			if(!finished) {
				System.out.println("Is there anything else i can do for you?:");
			}
			
		}
		
	}
	
	
	/**
	 * Innkeeper specific method to list available options for player to choose from. Command Line Version Only
	 */
	@Deprecated
	private void listInnKeeperOptions() {
		System.out.println("1) Purchase map");
		System.out.println("2) Purchase Power up");
		System.out.println("3) Purchase Healing Item");
		System.out.println("4) Show prices");
		System.out.println("5) Show team inventory");
		System.out.println("6) Show attributes of an item");
		System.out.println("7) Nothing thanks");
	}
	
	
	/**
	 * Innkeeper specific method to choose which method to run based on input from the user. Command Line Version Only 
	 * @param n int, used in the switch statement to find which method to run.
	 * @return boolean, true if finished with Innkeeper and want to return to the previous menu, false if need Innkeeper menu again.
	 */
	@Deprecated
	public boolean runInnKeeperOption(int n) {
		
		switch(n) {
		
		case 1:
			boolean purchasedMap = this.purchaseMap();
			return false;
			
		case 2:
			boolean purchasedPowerUp = this.purchasePowerUp();
			return false;
		
		case 3:
			boolean purchasedHealingItem = this.purchaseHealingItem();
			return false;
			
		case 4:
			this.showPrices();
			return false;
			
		case 5:
			this.shopInventory();
			return false;
			
		case 6:
			this.showItem();
			return false;
			
		case 7:
			return true;
		}
		return false;
	}
	
	
	/**
	 * Method to select an item and show its attributes. Command Line Version Only
	 */
	@Deprecated
	private void showItem() {
		System.out.println("1) Healing Item");
		System.out.println("2) Power Up");
		int n = this.getSelector().intSelector(1, 2, "Please Select a Type", "Invalid Selection please try again");
		
		switch(n) {
		case 1:
			System.out.println("Healing Items:");
			for(int x = 0; x <= this.healingItems.size() - 1 ; x++) {
				System.out.println(x + ") " + healingItems.get(x).getDescription());
			}
			int healerIndex = this.getSelector().intSelector(0, this.healingItems.size(), "Please Select a Healing Item", "Invalid Selection please try again");
			System.out.println(this.healingItems.get(healerIndex).getDescription() + ":");
			System.out.println("Apply Time: " + this.healingItems.get(healerIndex).getApplyTime());
			System.out.println("Health to replenish: " + this.healingItems.get(healerIndex).getHealthValue());
			System.out.println("Cost: " + this.healingItems.get(healerIndex).getCost());
			System.out.println("Description: " + this.healingItems.get(healerIndex).getLongDescription());
			break;
			
		case 2:
			System.out.println("Power Ups:");
			for(int x = 0; x<= this.powerUps.size() - 1 ; x++) {
				System.out.println(x + ") " + powerUps.get(x).getDescription());
			}
			int powerIndex = this.getSelector().intSelector(0, this.powerUps.size(), "Please Select a Healing Item", "Invalid Selection please try again");
			System.out.println(this.powerUps.get(powerIndex).getDescription() + ":");
			System.out.println("Effect: " + this.powerUps.get(powerIndex).getEffect());
			System.out.println("Cost: " + this.powerUps.get(powerIndex).getCost());
			System.out.println("Description: " + this.powerUps.get(powerIndex).getLongDescription());
			break;
		}
		
		
	}


	/**
	 * Method to list items and prices available at the shop
	 */
	@Deprecated
	private void showPrices() {
		System.out.println("Healing Items:");
		for(HealingItemType healer: this.healingItems) {
			System.out.println(healer.getCost() + " - " + healer.getDescription());
		}
		System.out.println("Power Ups:");
		for(PowerUpType power: this.powerUps) {
			System.out.println(power.getCost() + " - " + power.getDescription());
		}
		
	}


	/**
	 * Method to purchase a healing item. Command Line Version Only
	 * @return boolean, true if purchase successful
	 */
	@Deprecated
	private boolean purchaseHealingItem() {
		for(int x = 0 ; x < this.healingItems.size() ; x++) {
			System.out.print(x + ") " + this.healingItems.get(x).getCost() + " - " + this.healingItems.get(x).getDescription());
		}
		int n = this.getSelector().intSelector(0, this.healingItems.size(), "Please select a Healing Item", "Invalid Healing Item please try again");
		if(this.heroTeam.getMoney() >= this.healingItems.get(n).getCost()) {
			this.heroTeam.changeMoney(this.healingItems.get(n).getCost() * (-1));
			this.heroTeam.addHealingItem(this.healingItems.get(n));
			return true;
		}
		else {
			System.out.print("Insufficient Funds");
			return false;
		}
	}


	/**
	 * Method to purchase a Power Up item. Command Line Version Only
	 * @return boolean, true if purchase successful
	 */
	@Deprecated
	private boolean purchasePowerUp() {
		for(int x = 0 ; x < this.powerUps.size() ; x++) {
			System.out.print(x + ") " + this.powerUps.get(x).getCost() + " - " + this.powerUps.get(x).getDescription());
		}
		int n = this.getSelector().intSelector(0, this.powerUps.size(), "Please select a power up", "Invalid Power Up please try again");
		if(this.heroTeam.getMoney() >= this.powerUps.get(n).getCost()) {
			this.heroTeam.changeMoney(this.powerUps.get(n).getCost() * (-1));
			this.heroTeam.addPowerUp(this.powerUps.get(n));
			return true;
		}
		else {
			System.out.print("Insufficient Funds");
			return false;
		}
	}


	/**
	 * Method to purchase a map. Command Line Version Only
	 * @return boolean, true if purchase successful
	 */
	@Deprecated
	private boolean purchaseMap() {
		if(this.heroTeam.getMoney() >= 5.00) {
			this.heroTeam.changeMaps(1);
			this.heroTeam.changeMoney(-5.00);
			return true;
		}
		else {
			System.out.println("Not enough money");
			return false;
		}
	}


	/**
	 * Method to display shop inventory. Command Line Version Only
	 */
	@Deprecated
	public void shopInventory() {
		System.out.println("Inventory for " + this.getName());
		System.out.println("Healing Items:");
		for(int x = 0; x <= this.healingItems.size() - 1 ; x++) {
			System.out.println(x + ") " + healingItems.get(x).getDescription());
		}
		System.out.println("Power Ups:");
		for(int x = 0; x <= this.powerUps.size() - 1 ; x++) {
			System.out.println(x + ") " + powerUps.get(x).getDescription());
		}
	}
	
}
