import java.util.ArrayList;

/**
 * Team Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public class Team {
	
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
	
	@Override
	public String toString() {
		String result = "This team contains: \n";
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
		team1.addMember(Hero.ALL_BLACK);
		team1.addMember(Hero.VOLUNTEER);
		team1.addMember(Hero.FIREFIGHTER);
		team1.addMember(Hero.FIREFIGHTER);
		System.out.print(team1);
		System.out.print(team1.removeMember(3));
		team1.addMember(Hero.RETURNED_SERVICEMAN);
		System.out.println();
		team1.memberList.get(1).changeStrength(-100);
		System.out.println(team1.memberList.get(1).getStrength());
		System.out.print(team1);

	}

}
