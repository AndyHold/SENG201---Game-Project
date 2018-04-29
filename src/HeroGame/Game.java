package HeroGame;
/**
 * Game Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public class Game {
	//Variables from specification 
	private static final int MAX_NAME_LENGTH = 10;
	private static final int MIN_NAME_LENGTH = 2;
	private static final int MAX_NUM_CITIES = 6;
	private static final int MIN_NUM_CITIES = 3;
	private static final int MAX_TEAM_SIZE = 3;
	
	private String teamName;
	private int nCities;
	private int nHeroes;
	private long startTime;//0 until game play starts
	private City[] cities;
	private Team heroTeam; //null pointer until buildTeam() is called
	private Selector gameSelector = new Selector();
	/**
	 * Game constructor - uses GameSetup class UI to obtain parameters of
	 * game from input. Uses buildTeam method in Heroes class o
	 */
	Game() {
		
		teamName = gameSelector.stringSelector(MIN_NAME_LENGTH, MAX_NAME_LENGTH, 
				"Input team name (2-10 characters):", 
				"Team name must be 2-10 characters.");
		nCities = gameSelector.intSelector(MIN_NUM_CITIES, MAX_NUM_CITIES, 
				"Input number of towns to visit(3-6):", 
				"Number of towns must be 3-6.");
		nHeroes = gameSelector.intSelector(1, MAX_TEAM_SIZE, 
				"Input number of heroes in team (1-3):", 
				"Number of heroes must be 1-3.");
		
		System.out.println("In this game, " + teamName + ", a team of " + nHeroes 
				+ " will battle their way through the petty villains of " + nCities + 
				" small NZ towns.\n");
		
		heroTeam = new Team(teamName);
		heroTeam.buildTeam(nHeroes);
		System.out.println(heroTeam);
		/*
	    //this.cities = new city[nCities];
		for (int i = 0; i < nCities; i++) {
			//instantiate a city with a randomised layout
			//add it to nCities at index i
		}
		*/
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Game game1 = new Game();

	}

}