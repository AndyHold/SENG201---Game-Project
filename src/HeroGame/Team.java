package HeroGame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Team Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public class Team {

	private static final int MAX_HERO_NAME_LENGTH = 12;
	private static final double STARTING_MONEY = 10;
	private String teamName;
	private ArrayList<Hero> memberList;
	private ArrayList<PowerUp> powerUps;
	private ArrayList<HealingItem> healingItems;
	private int maps;
	double money;
	
	
	/**
	 * Parameterised constructor for Team Class
	 * @param teamName a String the name of the team
	 * @param nMembers an int the number of members in the team
	 */
	public Team(String teamName){
		this.teamName = teamName;
		this.memberList = new ArrayList<Hero>();
		this.powerUps = new ArrayList<PowerUp>();
		this.healingItems = new ArrayList<HealingItem>();
		this.maps = 0;
		this.money = STARTING_MONEY;
	}
	 
	/**
	 * The current size of the team
	 * @return an int the number of members currently in the team
	 */
	public int getTeamSize() {
		return memberList.size();
	}

	//**************************Heroes*****************************************
	
	/**
	 * Adds a new hero to the team
	 * @param newHero a Hero type
	 */
	public void addMember(Hero newHero) {
		memberList.add(newHero);
	}
	
	/**
	 * Removes the hero of the specified index from the team. Returns the 
	 * number of members remaining in the team
	 * @param heroIndex an int the index of the hero to be removed 
	 * @return an int the number of heros remaining in the team
	 */
	public int removeMember(int heroIndex) {
		if (heroIndex >= 0 && heroIndex < memberList.size()) {
			memberList.remove(heroIndex);
		}
		return memberList.size();
	}
	
	
	/**
	 * Interactive method for adding members to a Team
	 * @param size an int the number of members to add to the team
	 */
	public void buildTeam(int size) {
		
		Hero dummyHero = new Hero();
		HeroType newHeroType;
		int numPossibleHeroes = (dummyHero.printHeroList() - 1);
	    Selector teamSelector = new Selector();
		boolean selected = false;
		while (memberList.size() < size){
	    	String heroName = teamSelector.stringSelector(1, MAX_HERO_NAME_LENGTH, 
	    			"Enter the hero's name (1-12 Characters)", 
	    			"Name must be 1-12 characters.");
	    	if (checkNameUnique(heroName) == false) {
	    		System.out.println("There is already a hero named " 
	    				+ heroName + " in the team");
	    	} else {
	    		int heroNum = teamSelector.intSelector(0, numPossibleHeroes, 
	    				"Choose a type of hero " + "(0-"+ numPossibleHeroes +"):", 
	    				"Choice must be between 0 & " + numPossibleHeroes);
	    		
	    		newHeroType = HeroType.values()[heroNum];
	    		
	    		addMember(new Hero(heroName, newHeroType));
	    		System.out.println(newHeroType.getDescription() +" called " 
	    				+ heroName + " has joined the team");
	    	}
		}
	}
	
	/**
	 * Checks the team to see whether a particular name has already been allocated
	 * @return a boolean false if the name has been allocated, true if it is unique
	 */
	public boolean checkNameUnique(String name) {
		boolean result = true;
		for (Hero who : memberList) {
			if (who.getName().toLowerCase().equals(name.toLowerCase())) {
				result = false;
			}
		}
		return result;
	}
	
	/**
	 * Checks whether a particular HeroType is present in a team
	 * @param heroTypeInQuestion a HeroType the HeroType to check
	 * @return a boolean true if heroTypeInQuestion is present, else false
	 */
	public boolean checkPresent(HeroType heroTypeInQuestion) {
		boolean result = false;
		for (Hero who : memberList) {
			if (who.getType() == heroTypeInQuestion){ 
				result = true;
			}
		}

		return result;
	}
	
	/**
	 * Gets the index of a particular Hero, so that they can be removed or have a 
	 * health bonus applied from outside the class
	 * @param heroInQuestion a Hero the hero whose index is required
	 * @return an int the position of the hero in question in the memberList array
	 * If member is not present, returns -1
	 */
	public int getIndex(Hero heroInQuestion) {
		if(memberList.contains(heroInQuestion)) {
			return memberList.indexOf(heroInQuestion);
		} else {
			return -1;
		}
	}
	
	/**
	 * Returns (and prints to output) a list of heroes in the team and their index
	 * @return
	 */
	public String listHeroes() {
		String result = "Heroes in team:\n";
		for (int i = 0; i < memberList.size(); i++) {
			result += (i + ". " + memberList.get(i).getName() + "\n");
		}
		System.out.print(result);
		return result;
	}
	
	/**
	 * for a valid index, returns the hero at a given index in the private memberList array
	 * @param heroIndex an int the index of the required hero
	 * @return the Hero at the given index. Returns a null pointer otherwise
	 */
	public Hero getHero(int heroIndex) {
		Hero result;
		if (heroIndex >= 0 && heroIndex < memberList.size());{
			result = memberList.get(heroIndex);
		} 
		return result;
	}
	
	
	/**
	 * Print to output a list of the current team members, their special ability 
	 * and their current strength.
	 */
	public void teamStatus() {
		System.out.println("At this point, the members of " + teamName + " are:");
		for (Hero who : memberList) {
			System.out.print(memberList.indexOf(who) + ". ");
			System.out.print(who.getName() + ", ");
			System.out.print(who.toString().toLowerCase());
			System.out.print(" with the ability to " + who.getAbility());
			System.out.print(". " + who.getName() + "'s strength is ");
			System.out.print(who.getStrength());
			System.out.print(" and their health is " + who.getHealth() + "\n");

		}
	}
	//*********************************Power Ups*******************************
	
	/**
	 * Buy a powerup. Remove the relevant amount from the 
	 * team's money. If money is insufficient for the transaction, prints a statement
	 * to this effect, and powerup is not added
	 * @param powerUpBought a PowerUp the powerUp to be bought
	 */
	//Andy to move into shop class
	//************************************************************************
	public void buyPowerUp(PowerUp powerUpBought) {
		if (this.money >= powerUpBought.getCost()) {
			this.powerUps.add(powerUpBought);//Will need to use Team .addPowerUp method
			this.money -= powerUpBought.getCost();
			System.out.println(teamName + " have bought a " + powerUpBought.toString().toLowerCase());
			System.out.println("The ballsy band of heroes have " + this.money + " remaining");
		} else {
			System.out.println("We're too broke mate! Anyone want to sell a kidney?");
		}
	}
	//************************************************************************
	
	/**
	 * Adds a power up to the team's inventory
	 * @param powerUp a PowerUp, the power up to add to the team's inventory 
	 */
	public int addPowerUp(PowerUp powerUp){
		this.powerUps.add(powerUp);
		return powerUps.size();
	}
	
	/**
	 * Removes the power up at a given index from the list
	 * @param powerUpIndex an int, the index of the power up to remove in the ArrayList of power ups 
	 */
	public int removePowerUp(int powerUpIndex){
		if (powerUpIndex >= 0 && powerUpIndex < powerUps.size()) {
			this.powerUps.remove(powerUpIndex);
		}
		return powerUps.size();
	}
	
	/**
	 * Returns the number of power ups currently held
	 * @return an int the number of power ups currently held
	 */
	public int getPowerUpsSize() {
		return this.powerUps.size();
	}
	
	/**
	 * Makes the team member at index teamMember eat the powerup at index powerUpIndex. Removes the 
	 * power up from the team's inventory 
	 * @param powerUpType a power up type, the type of power up to apply
	 * @param teamMemberIndex an int the index of the team member who is to eat the power up
	 */
	public String applyPowerUp(PowerUpType powerUpType, int teamMemberIndex) {
		String result = "Power up not found\n"; //default value
		boolean applied = false;
		int counter = 0;
		if (teamMemberIndex < 0 || teamMemberIndex >= memberList.size()) {
			result = "No hero found!";
		} else {
			while (!applied && counter < powerUps.size()) {
				if (powerUps.get(counter).getType() == powerUpType) {
					memberList.get(teamMemberIndex).eatPowerUp(powerUps.get(counter));
					result = "Power up applied!\n";
					powerUps.remove(counter);
					applied = true;
				} else {
					counter++;
				}
			}
		}
		return result;
	}
	
	/**
	 * Returns a list of the power ups in the team's inventory
	 * @return a String a list of quantities of each power up carried by the team
	 */
	public String showPowerUps () {
		
		int pavlovaCounter = Collections.frequency(powerUps, new PowerUp(PowerUpType.PAVLOVA));
		int cheeseRollCounter = Collections.frequency(powerUps, new PowerUp(PowerUpType.CHEESE_ROLL));
		int pineappleLumpCounter = Collections.frequency(powerUps, new PowerUp(PowerUpType.PINEAPPLE_LUMPS));
		int total = pavlovaCounter + cheeseRollCounter + pineappleLumpCounter;
		int counter = 0;
		String result = "Team " + teamName + " are carrying the following power ups:\n"; 
		
		if (pavlovaCounter > 0) {
			result += pavlovaCounter + " slices of pavlova.\n";
		}
		
		if (cheeseRollCounter > 0) {
			result += cheeseRollCounter + " cheese rolls.\n";
		}
		
		if (pineappleLumpCounter > 0) {
			result += pineappleLumpCounter + " packs of pineapple lumps.\n";
		}
		
		if (total != powerUps.size()) {
			result += powerUps.size() - total + " mystery power ups.\n";
			
		}
		
		return result;
	}
	
	
	
	//**********************************Healing Items***************************
	
	/**
	 * Adds a healing item to the team's inventory
	 * @param healingItem a HealingItem, the healing item to add to the team's inventory 
	 */
	public int addHealingItem(HealingItem healingItem){
		this.healingItems.add(healingItem);
		return healingItems.size();
	}
	
	/**
	 * Removes the healing at a given index from the list
	 * @param healingItemIndex an int, the index of the healing item to remove in the ArrayList of healing items 
	 */
	public int removeHealingItem(int healingItemIndex){
		if (healingItemIndex >= 0 && healingItemIndex < healingItems.size()) {
			this.healingItems.remove(healingItemIndex);
		}
		return healingItems.size();
	}
	
	/**
	 * Makes the team member at index teamMember drink the healing item at index healingItemIndex. Removes the 
	 * healing item from the team's inventory 
	 * @param healingItemIndex an int the index of the healing item to apply
	 * @param teamMemberIndex an int the index of the team member who is to drink the healing item
	 */
	public void applyHealingItem(int healingItemIndex, int teamMemberIndex){
		//memberList.get(teamMemberIndex).drinkHealingItem(healingItems.get(powerUpIndex));
		removeHealingItem(healingItemIndex);
	}
	
	/**
	 * Prints to output a list of the healing items in the team's inventory, and the index of each
	 */
	public void showHealingItems(){
		System.out.println("Team " + teamName + " are carrying the following healing items:");
		for (int i = 0; i < healingItems.size(); i++){
			System.out.print(i + ". " + healingItems.get(i) + "\n");
		}
	}	
	
	/**
	 * Returns the number of healing items in the team's inventory
	 * @return an int the number of healing items in the team's inventory
	 */
	public int getHealingItemsSize() {
		return healingItems.size();
	}
	
	//***************************Maps******************************************
	
	/**
	 * Outputs the number of maps the team is carrying
	 */
	public int getMaps(){
		System.out.println("Team " + teamName + " are carrying " + maps + " maps");
		return maps;
	}
	
	/**
	 * Setter method for the number of maps the team is carrying
	 * @param mapChange an int the amount by which the number of maps is to change
	 */
	public void changeMaps(int mapChange) {
		this.maps += mapChange;
		if (this.maps < 0) {
			this.maps =0;
		}
		getMaps();
		
	}
	//*************************************************************************
	
	/**
	 * Prints to output and returns the amount of money the team has
	 * @return a double the amount of money the team has
	 */
	public double getMoney() {
		System.out.println("The members of " + teamName + " have a whip around. They have $" + this.money );
		return this.money;
	}
	
	/**
	 * Chnages the amount of money the team has by a positive or negative amount. The value of money has a 'floor' of $0.00
	 * @param moneyChange a double the amount the teams money changes by
	 * @return a double the amount of money the team now has
	 */
	public double changeMoney(double moneyChange) {
		this.money += moneyChange;
		if (this.money <= 0) {
			this.money = 0;
		}
		return this.getMoney();
	}
	
	@Override
	public String toString() {
		String result = "Team " + teamName + " contains: \n";
		for (Hero who: memberList) {
			result += (who + "\n");
		}
		return result;
	}

	public static void main(String[] args) {
		Team t1 = new Team("Awesome");
		Hero h1 = new Hero("Jim", HeroType.ALL_BLACK);
		PowerUp p1 = new PowerUp(PowerUpType.CHEESE_ROLL);
		PowerUp p2 = new PowerUp(PowerUpType.CHEESE_ROLL);
		PowerUp p3 = new PowerUp(PowerUpType.PINEAPPLE_LUMPS);
		t1.addMember(h1);
		t1.addPowerUp(p1);
		t1.addPowerUp(p2);
		t1.addPowerUp(p3);
		System.out.print(t1.showPowerUps());
		System.out.print(t1.applyPowerUp(PowerUpType.PAVLOVA, 0));
		System.out.print(t1.applyPowerUp(PowerUpType.CHEESE_ROLL, 0));
		System.out.print(t1.showPowerUps());
		
	}

}
