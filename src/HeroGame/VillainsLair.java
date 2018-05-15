package HeroGame;
import java.text.MessageFormat;
import java.util.Random;

import javax.swing.ImageIcon;

import cmdLineVersion.Selector;
import cmdLineVersion.Battle;

/**
 * VillainsLair Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class VillainsLair extends Location {
	
	
	
	private Random rand = new Random();
	private Villain cityVillain;
	
	
	/**
	 * Constructor for VillainsLair Class Text Version.
	 * @param cityName Name of the City containing this Villains Lair
	 * @param currentVillian Current Villain of this city.
	 */
	VillainsLair(City thisCity, Villain currentVillain, Team heroTeam) {
		super(currentVillain.getLairName(thisCity.getName()), heroTeam, thisCity.getName(), LocationType.VILLIANSLAIR, new ImageIcon(CityScreen.class.getResource("/HeroGame/Images/Shop.png")));
		this.cityVillain = currentVillain;
	}
	
	
	public ImageIcon getVillainImage() {
		return this.cityVillain.getImage();
	}
	
	
	/**
	 * Method to list available options for player to choose from
	 */
	public void listOptions() {
		System.out.println(MessageFormat.format("Welcome to {0}:", this.getName()));
		System.out.println("1) Move to another location");
		System.out.println("2) Enter Villians Lair");
	}
	
	
	/**
	 * Run loop for VillainsLair class
	 * @return int, Number corresponding to the direction to move to next.
	 */
	public int runLocation() {
		boolean finishedInLocation = false;
		while(!finishedInLocation) {
			listOptions();
			int n = this.getSelector().intSelector(1, 2, "Please select an option", "Invalid option, try again");
			finishedInLocation = runOption(n);
		}
		if(!this.cityVillain.isAlive()) {
			return 0;
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
			System.out.println("Please choose a hero to battle with:");
			this.heroTeam.listHeroes();
			int heroIndex = this.getSelector().intSelector(0, this.heroTeam.getTeamSize(), "Please choose a hero", "Invalid selection, please try again");
			Battle currentBattle = new Battle();
			this.chooseBattle(currentBattle, heroIndex);
			return !this.cityVillain.isAlive();		
		}
		return false;
	}
	
	
	private void chooseBattle(Battle currentBattle, int heroIndex) {
		int n = this.rand.nextInt(3);
		
		switch(n) {
		case 0:
			currentBattle.diceRolls(this.heroTeam, this.heroTeam.getHero(heroIndex), this.cityVillain);
			break;
			
		case 1:
			currentBattle.guessNumber(this.heroTeam, this.heroTeam.getHero(heroIndex), this.cityVillain);
			break;
			
		case 2:
			currentBattle.paperScissorsRock(this.heroTeam, this.heroTeam.getHero(heroIndex), this.cityVillain);
			break;
		}
		
	}


	@Override
	public String toString() {
		return MessageFormat.format("{0} currently ruled by {1}", this.getName(), this.cityVillain.getName());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
