import java.util.ArrayList;
import java.util.Scanner;

/**
 * Team Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public class Team {

	private static final int MAX_HERO_NAME_LENGTH = 12;
	private String teamName;
	private int nMembers;
	private ArrayList<Hero> memberList;
	//private ArrayList<PowerUp> powerUps;
	//private ArrayList<HealingItem> healingItems;
	//private ArrayList<map> maps;
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
	}
	
	/**
	 * The current size of the team
	 * @return an int the number of members currently in the team
	 */
	public int getTeamSize() {
		return memberList.size();
	}
	
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
	    
        while (memberList.size() < size){
			int heroNum = 0;
			String heroName = "Steve";
	        boolean looping = true; 
			Scanner in = new Scanner(System.in);
	    	
	    	while (looping) {
	    		System.out.println("Enter the hero's name (1-12 Characters)");
		    	heroName = in.nextLine();
		    	if (heroName.length() < 1 || heroName.length() > MAX_HERO_NAME_LENGTH) {
		    		System.out.println("Name must be 1-12 characters.");
		    	}
		    	else if (checkNameUnique(heroName) == false) {
		    		System.out.println("There is already a hero named " 
		    				+ heroName + " in the team");
		    	}
		    	else {
		    		looping = false;
		    	}
		    }
	    	looping = true;
	    	
			while (looping)  {
	    		System.out.println("Choose a type of hero " +
	    				"(1-"+ numPossibleHeroes +"):");
	    		heroNum = in.nextInt();
	    		if (heroNum < 1 || heroNum > numPossibleHeroes) {
	    			System.out.println("Choice must be between 1 & " + numPossibleHeroes);
	    		}
	    		else {
	    			looping = false;
	    		}
			}
			switch (heroNum) { //Implemented this way until we see how GUI will work
			case 1:
				currentHero = Hero.ALL_BLACK;
				break;
			case 2:
				currentHero = Hero.VOLUNTEER;
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
		Team team1 = new Team("Awesome", 4);
		team1.buildTeam(4);
//		team1.addMember(Hero.ALL_BLACK);
//		team1.addMember(Hero.VOLUNTEER);
//		team1.addMember(Hero.FIREFIGHTER);
//		team1.addMember(Hero.FIREFIGHTER);
//		System.out.print(team1);
//		System.out.print(team1.removeMember(3));
//		team1.addMember(Hero.RETURNED_SERVICEMAN);
//		System.out.println();
//		team1.memberList.get(1).changeStrength(-100);
//		System.out.println(team1.memberList.get(1).getStrength());
		System.out.print(team1);

	}

}
