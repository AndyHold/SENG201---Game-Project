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
	
	
	
	Shop(City thisCity, Team newTeam) {
		super(thisCity.getPlaceName(thisCity.getName() + " Shop"), newTeam, thisCity.getName());
	}
	
	
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
