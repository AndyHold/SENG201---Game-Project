package HeroGame;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import cmdLineVersion.Selector;


/**
 * Team Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public class Team {

	private static final int MAX_HERO_NAME_LENGTH = 12;
	private static final double STARTING_MONEY = 15;
	private String teamName;
	private ArrayList<Hero> memberList;
	private ArrayList<PowerUp> powerUps;
	private ArrayList<HealingItem> healingItems;
	private int maps;
	private double money;
	private long startTime;
	
	
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
	 * The healing item Array List of the team
	 * @return an ArrayList<HealingItem> of the Healing items in the Inventory
	 */
	public ArrayList<HealingItem> getHealingItems() {
		return this.healingItems;
	}
	
	/**
	 * The power up Array List of the team
	 * @return an ArrayList<PowerUp> of the Power ups in the Inventory
	 */
	public ArrayList<PowerUp> getPowerUps() {
		return this.powerUps;
	}
	
	/**
	 * The member Array List of the team
	 * @return an ArrayList<Hero> of the Heroes in the team
	 */
	public ArrayList<Hero> getMemberList() {
		return this.memberList;
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
	 * Only used in the command line version of the game
	 * @param size an int the number of members to add to the team
	 */
	@Deprecated
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
	 * Checks the Team to see whether a particular name has already been allocated
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
	 * Checks whether a particular HeroType is present in the Team
	 * @param heroTypeInQuestion a HeroType the HeroType to check
	 * @return a boolean true if the heroTypeInQuestion is present, else false
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
	 * Returns (and prints to output) a list of heroes in the team and their index. Command Line Version Only
	 * @return a String the list of heroes in the Team
	 */
	@Deprecated
	public String listHeroes() {
		String result = "Heroes in team:\n";
		for (int i = 0; i < memberList.size(); i++) {
			result += (i + ". " + memberList.get(i).getName() + "\n");
		}
		System.out.print(result);
		return result;
	}
	
	/**
	 * for a valid index, returns the Hero at a given index in the private memberList array
	 * @param heroIndex an int the index of the required Hero
	 * @return the Hero at the given index. Returns a null pointer otherwise
	 */
	public Hero getHero(int heroIndex) {
		Hero result = null;
//		if (heroIndex < 0) {
//			//do nothing
//		} else 
		if (heroIndex >= 0 && heroIndex < memberList.size()) {
			result = memberList.get(heroIndex);
		} 
		return result;
	}
	
	
	/**
	 * Print to output a list of the current Team members, their special ability 
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
	 * Adds a PowerUp type to the team's inventory. Additional instances are added using changeAmount()
	 * @param powerUp a PowerUp, the PowerUp type to add to the team's inventory. 
	 */
	public int addPowerUp(PowerUpType powerUp){
		PowerUp newPowerUp = new PowerUp(powerUp);
		if(this.powerUps.contains(newPowerUp)) {
			int n = this.powerUps.indexOf(newPowerUp);
			this.powerUps.get(n).changeAmount(1);
		} else {
			this.powerUps.add(newPowerUp);
		}
		return powerUps.size();
	}
	
	/**
	 * Removes the power up at a given index from the list
	 * @param powerUpIndex an int, the index of the power up to remove in the ArrayList of power ups 
	 */
	public int removePowerUp(int powerUpIndex){
		if (powerUpIndex >= 0 && powerUpIndex < powerUps.size()) {
			if(this.powerUps.get(powerUpIndex).getAmount() > 1) {
				this.powerUps.get(powerUpIndex).changeAmount(-1);
			} else {
				this.powerUps.remove(powerUpIndex);
			}
		}
		return powerUps.size();
	}

	/**
	 * Returns the number of power ups currently held. Fundamental change to PowerUps/HealingItems
	 * means this method is no longer relevant/correct.
	 * @return an int the number of power ups currently held
	 */
	@Deprecated
	public int getPowerUpsSize() {
		return this.powerUps.size();
	}


	/**
	 * Makes the Hero at index teamMember eat a PowerUp of the given type. Removes the 
	 * first power up of this type from the team's inventory. Fundamental change to PowerUps/HealingItems
	 * means this method is no longer relevant/correct
	 * @param powerUpType a power up type, the type of power up to apply
	 * @param teamMemberIndex an int the index of the team member who is to eat the power up
	 */
	@Deprecated
	public String applyPowerUp(PowerUpType powerUpType, int teamMemberIndex) {
		String result = "Power up not found\n"; //default value
		boolean applied = false;
		int counter = 0;
		if (teamMemberIndex < 0 || teamMemberIndex >= memberList.size()) {
			result = "No hero found!\n";
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
	 * Returns a list of the PowerUps in the team's inventory. Fundamental change to PowerUps/HealingItems
	 * means this method is no longer relevant/correct.
	 * @return a String a list of quantities of each power up carried by the team
	 */
	@Deprecated
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
	
	/**
	 * Returns the type of power up found at a given index of the power ups ArrayList
	 * @param powerUpIndex an int the index of the power up for which the type is required
	 * @return a PowerUpType the PowerUpType at the given index in the ArrayList<PowerUps>
	 */
	public PowerUpType getPowerUpType(int powerUpIndex) {
		PowerUpType result = null;
		if (powerUpIndex >= 0 && powerUpIndex < powerUps.size()) {
			result = powerUps.get(powerUpIndex).getType();
		}
		return result;
	}
	
	
	/**
	 * Method to get a list of power ups the team currently has in html format
	 * @return String, list of power ups
	 */
	public String listPowerUps() {
		String result = "<html><center>Your Power Up Items:<br>";
		for(PowerUp powerUp: this.powerUps) {
			result += MessageFormat.format("{0} x{1}<br>", powerUp.toString(), powerUp.getAmount());
		}
		result += "</center></html>";
		return result;
	}
	
	//**********************************Healing Items***************************
	
	/**
	 * Adds a healing item to the team's inventory. If nurse is present in team, healing
	 * power of item doubles
	 * @param healingItem a HealingItem, the healing item to add to the team's inventory 
	 */
	public int addHealingItem(HealingItemType healingItemType){
		HealingItem healingItem = new HealingItem(healingItemType);
		if (this.checkPresent(HeroType.NURSE)) {//Apply special power of nurse if present 
			healingItem.changeHealthValue(healingItem.getHealthValue());
		}
		if(this.healingItems.contains(healingItem)) {
			int n = this.healingItems.indexOf(healingItem);
			this.healingItems.get(n).changeAmount(1);
		} else {
			this.healingItems.add(healingItem);
		}
		return healingItems.size();
	}
	
	/**
	 * Removes the healing at a given index from the list
	 * @param healingItemIndex an int, the index of the healing item to remove in the ArrayList of healing items 
	 */
	public int removeHealingItem(int healingItemIndex){
		if (healingItemIndex >= 0 && healingItemIndex < healingItems.size()) {
			if(this.healingItems.get(healingItemIndex).getAmount() > 1) {
				this.healingItems.get(healingItemIndex).changeAmount(-1);
			} else {
				this.healingItems.remove(healingItemIndex);
			}
		}
		return healingItems.size();
	}
	
	/**
	 * Makes the team member at index teamMember drink a healing item of the given type. Removes the 
	 * first healing item of this type from the team's inventory. Fundamental change to PowerUps/HealingItems
	 * means this method is no longer relevant/correct.
	 * @param healingItemType a HealingItemType, the type of healing item to apply
	 * @param teamMemberIndex an int the index of the team member who is to drink the healing item
	 */
	@Deprecated
	public String applyHealingItem(HealingItemType healingItemType, int teamMemberIndex) {
		String result = "Healing item not found\n"; //default value
		boolean applied = false;
		int counter = 0;
		if (teamMemberIndex < 0 || teamMemberIndex >= memberList.size()) {
			result = "No hero found!\n";
		} else {
			while (!applied && counter < healingItems.size()) {
				if (healingItems.get(counter).getHealingItemType() == healingItemType) {
					memberList.get(teamMemberIndex).drinkHealingItem(healingItems.get(counter), this.getTime());
					result = "Healing item applied!\n";
					healingItems.remove(counter);
					applied = true;
				} else {
					counter++;
				}
			}
		}
		return result;
	}
	
	/**
	 * Returns the type of the healing item at the given index
	 * @param healingItemIndex an int the index of the healing item for which the type will be returned 
	 * @return a HealingItemType the type of healing item at the given index in the healing items ArrayList
	 */
	public HealingItemType getHealingItemType(int healingItemIndex) {
		HealingItemType result = null;
		if (healingItemIndex >= 0 && healingItemIndex < healingItems.size()) {
			result = healingItems.get(healingItemIndex).getHealingItemType();
		}
		return result;
	}
	
	/**
	 * Returns a String listing the quantities of each healing item in the team's inventory. Fundamental change 
	 * to PowerUps/HealingItems means this method is no longer relevant/correct.
	 * @return a multiline String a list of the qunatities of each healing item in the team's inventory
	 */
	@Deprecated
	public String showHealingItems () {
		
		int doBroCounter = Collections.frequency(healingItems, new HealingItem(HealingItemType.DOUBLE_BROWN));
		int lionCounter = Collections.frequency(healingItems, new HealingItem(HealingItemType.LION_RED));
		int lindauerCounter = Collections.frequency(healingItems, new HealingItem(HealingItemType.LINDAUER));
		int total = doBroCounter + lionCounter + lindauerCounter;
		int counter = 0;
		String result = "Team " + teamName + " are carrying the following healing items:\n"; 
		
		if (doBroCounter > 0) {
			result += doBroCounter + " bottles of double brown.\n";
		}
		
		if (lionCounter > 0) {
			result += lionCounter + " pints of Lion Red.\n";
		}
		
		if (lindauerCounter > 0) {
			result += lindauerCounter + " bottles of Lindauer.\n";
		}
		
		if (total != healingItems.size()) {
			result += healingItems.size() - total + " mystery healing items.\n";
			
		}
		
		return result;
	}
	
	/**
	 * Returns the number of healing items in the team's inventory. Fundamental change 
	 * to PowerUps/HealingItems means this method is no longer relevant/correct.
	 * @return an int the number of healing items in the team's inventory
	 */@Deprecated
	public int getHealingItemsSize() {
		return healingItems.size();
	}
	
	
	/**
	 * Method to get a list of healing Items the team currently has in html format
	 * @return String, list of healing Items
	 */
	public String listHealingItems() {
		String result = "<html><center>Your Healing Items:<br>";
		for(HealingItem healingItem: this.healingItems) {
			result += MessageFormat.format("{0} x{1}<br>", healingItem.toString(), healingItem.getAmount());
		}
		result += "</center></html>";
		return result;
	}
	
	//***************************Maps******************************************
	
	/**
	 * Outputs the number of maps the team is carrying
	 */
	public int getMaps(){
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
	//***************************Money**********************************************
	
	/**
	 * Prints to output and returns the amount of money the team has
	 * @return a double the amount of money the team has
	 */
	public double getMoney() {
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
	//*************************************Time*****************************************
	/**
	 * Starts the game clock by setting the value of startTime to the current system time
	 */
	public void startClock() {
	    this.startTime = System.currentTimeMillis();
	}
	
	/**
	 * Returns the time in seconds since the start of play
	 * @return a double the time in seconds since the start of the game
	 */
	public double getTime() {
		long currentTime = System.currentTimeMillis() - startTime;
		return (double)(currentTime / 1000);
	}
	
	//***********************************************************************************
	@Override
	public String toString() {
		String result = "Team " + teamName + " contains: \n";
		for (Hero who: memberList) {
			result += (who + "\n");
		}
		return result;
	}
//*****************************Test Code***********************************
//	public static void main(String[] args) {
//		Team t1 = new Team("Awesome");
//		Hero h1 = new Hero("Jim", HeroType.ALL_BLACK);
//		PowerUp p1 = new PowerUp(PowerUpType.CHEESE_ROLL);
//		PowerUp p2 = new PowerUp(PowerUpType.CHEESE_ROLL);
//		PowerUp p3 = new PowerUp(PowerUpType.PINEAPPLE_LUMPS);
//		t1.addMember(h1);
//		t1.addPowerUp(p1);
//		t1.addPowerUp(p2);
//		t1.addPowerUp(p3);
//		System.out.print(t1.showPowerUps());
//		System.out.print(t1.applyPowerUp(PowerUpType.PAVLOVA, 0));
//		System.out.print(t1.applyPowerUp(PowerUpType.CHEESE_ROLL, 0));
//		System.out.print(t1.showPowerUps());
//		
//	}

}
