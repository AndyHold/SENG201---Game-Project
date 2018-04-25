import java.util.ArrayList;
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
	private int nMembers;
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
	public Team(String teamName, int nMembers){
		this.teamName = teamName;
		this.nMembers = nMembers;
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
	public boolean addMember(Hero newHero) {
		boolean result = false;
		if (memberList.size() < nMembers) {
			memberList.add(newHero);
			result = true;
		}
		return result;
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
		
		Hero currentHero = Hero.FIREFIGHTER;
		int numPossibleHeroes = currentHero.printHeroList();
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
	    		int heroNum = teamSelector.intSelector(1, numPossibleHeroes, 
	    				"Choose a type of hero " + "(1-"+ numPossibleHeroes +"):", 
	    				"Choice must be between 1 & " + numPossibleHeroes);
 
	    		switch (heroNum) { //Implemented this way until we see how GUI will work
	    		//Terribly non-OO. Needs fixing
	    		case 1:
	    			currentHero = Hero.ALL_BLACK;
	    			break;
	    		case 2:
	    			currentHero = Hero.SURVEYOR;
	    			break;
	    		case 3:
	    			currentHero = Hero.RETURNED_SERVICEMAN;
	    			break;
	    		case 4:
	    			currentHero = Hero.FIREFIGHTER;
	    			break;
	    		case 5:
	    			currentHero = Hero.NURSE;
	    			break;
	    		case 6:
	    			currentHero = Hero.FOSTER_MUM;
	    			break;
	    		}
	    		currentHero.setName(heroName);
	    		addMember(currentHero);
	    		System.out.println(currentHero +" called " 
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
	 * Checks whether a particular Hero is present in a team
	 * @param heroInQuestion a Hero the Hero to check
	 * @return a boolean true if heroInQuestion is present, else false
	 */
	public boolean checkPresent(Hero heroInQuestion) {
		boolean result = false;
		for (Hero who : memberList) {
			if (who == heroInQuestion){ 
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
	 */
	public int getIndex(Hero heroInQuestion) {
		return memberList.indexOf(heroInQuestion);
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
	public void addPowerUp(PowerUp powerUp){
		this.powerUps.add(powerUp);
	}
	
	/**
	 * Removes the power up at a given index from the list
	 * @param powerUpIndex an int, the index of the power up to remove in the ArrayList of power ups 
	 */
	public void removePowerUp(int powerUpIndex){
		this.powerUps.remove(powerUpIndex);
	}
	
	/**
	 * Makes the team member at index teamMember eat the powerup at index powerUpIndex. Removes the 
	 * power up from the team's inventory 
	 * @param powerUpIndex an int the index of the power up to apply
	 * @param teamMemberIndex an int the index of the team member who is to eat the power up
	 */
	public void applyPowerUp(int powerUpIndex, int teamMemberIndex){
		memberList.get(teamMemberIndex).eatPowerUp(powerUps.get(powerUpIndex));
		removePowerUp(powerUpIndex);
	}
	
	/**
	 * Prints to output a list of the power ups in the team's inventory, and the index of each
	 */
	public void showPowerUps(){
		System.out.println("Team " + teamName + " are carrying the following power ups:");
		for (int i = 0; i < powerUps.size(); i++){
			System.out.print(i + ". " + powerUps.get(i) + "\n");
		}
	}
	
	//**********************************Healing Items***************************
	
	/**
	 * Adds a healing item to the team's inventory
	 * @param healingItem a HealingItem, the healing item to add to the team's inventory 
	 */
	public void addHealingItem(HealingItem healingItem){
		this.healingItems.add(healingItem);
	}
	
	/**
	 * Removes the healing at a given index from the list
	 * @param healingItemIndex an int, the index of the healing item to remove in the ArrayList of healing items 
	 */
	public void removeHealingItem(int healingItemIndex){
		this.healingItems.remove(healingItemIndex);
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
	
	//***************************Maps******************************************
	
	/**
	 * Outputs the number of maps the team is carrying
	 */
	public void getMaps(){
		System.out.println("Team " + teamName + " are carrying " + maps + " maps");
	}
	
	/**
	 * Setter method for the number of maps the team is carrying
	 * @param mapChange an int the amount by which the number of maps is to change
	 */
	public void changeMaps(int mapChange) {
		this.maps += mapChange;
		getMaps();
	}
	
	@Override
	public String toString() {
		String result = "Team " + teamName + " contains: \n";
		for (Hero who: memberList) {
			result += (who + "\n");
		}
		return result;
	}
	
	/*
	 * Test Code Only
	 */
	public static void main(String[] args) {
		Team team1 = new Team("Awesome", 2);
		team1.buildTeam(2);
//		team1.addMember(Hero.ALL_BLACK);
//		team1.addMember(Hero.VOLUNTEER);
//		team1.addMember(Hero.FIREFIGHTER);
//		team1.addMember(Hero.FIREFIGHTER);
//		System.out.print(team1);
//		System.out.print(team1.removeMember(3));
//		team1.addMember(Hero.RETURNED_SERVICEMAN);
//		System.out.println();
//		team1.memberList.get(1).changeStrength(-100);
		//System.out.println(team1.memberList.get(0).getHealth());
		//team1.memberList.get(0).changeHealth(-101);
		//System.out.print(team1);
		
		team1.teamStatus();
		team1.addPowerUp(PowerUp.PAVLOVA);
		team1.addPowerUp(PowerUp.PINEAPPLE_LUMPS);
		team1.addHealingItem(HealingItem.LINDAUER);
		team1.addHealingItem(HealingItem.LION_RED);
		team1.applyPowerUp(0, 0);
		team1.showPowerUps();
		team1.showHealingItems();
		team1.changeMaps(1);
		team1.getMaps();
		Hero h1 = Hero.FIREFIGHTER;
		System.out.println(team1.checkPresent(h1));

		
	}

}
