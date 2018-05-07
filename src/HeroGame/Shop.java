package HeroGame;
import java.util.ArrayList;


/**
 * Shop Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class Shop extends Location {
	
	
	private ArrayList<HealingItem> healingItems = new ArrayList<HealingItem>();
	private ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	
	
	/**
	 * Constructor method for Shop Class
	 * @param thisCity City, City containing this shop
	 * @param newTeam Team, Team being controlled by the user
	 */
	Shop(City thisCity, Team newTeam) {
		super(thisCity.getPlaceName(thisCity.getName() + " Shop"), newTeam, thisCity.getName(), LocationType.SHOP);
	}
	
	
	/**
	 * Method to list available options for player to choose from
	 */
	public void listOptions() {
		System.out.println("Welcome to " + this.getName() + ":");
		System.out.println("1) Move to another location");
		System.out.println("2) Talk to waitress");
	}
	
	
	/**
	 * Run loop for Shop class
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
			this.runInkeeperLoop();
			return false;
		}
		return false;
	}
	
	
	/**
	 * run loop for the Innkeeper
	 */
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
	 * Innkeeper specific method to list available options for player to choose from
	 */
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
	 * Innkeeper specific method to choose which method to run based on input from the user.
	 * @param n int, used in the switch statement to find which method to run.
	 * @return boolean, true if finished with Innkeeper and want to return to the previous menu, false if need Innkeeper menu again.
	 */
	public boolean runInnKeeperOption(int n) {
		
		switch(n) {
		
		case 1:
			//this.purchaseMap();
			return false;
			
		case 2:
			//this.purchasePowerUp();
			return false;
		
		case 3:
			//this.purchaseHealingItem();
			return false;
			
		case 4:
			//this.showPrices();
			return false;
			
		case 5:
			//this.showInventory();
			return false;
			
		case 6:
			//this.showItem();
			return false;
			
		case 7:
			return true;
		}
		return false;
	}
	
	
	/**
	 * Method to display shop inventory
	 */
	public void shopInventory() {
		System.out.println("Inventory for " + this.getName());
		System.out.println("Healing Items:");
		for(int x = 0; x == this.healingItems.size() ; x++) {
			System.out.println(x + ") " + healingItems.get(x));
		}
		System.out.println("Power Ups:");
		for(int x = 0; x == this.powerUps.size() ; x++) {
			System.out.println(x + ") " + powerUps.get(x));
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
