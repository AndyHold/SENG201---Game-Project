import java.util.Scanner;

/**
 * Game Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public class Game {
	
	private int nCities;
	private int nHeroes;
	private long startTime;//0 until game play starts
	private City[] cities;
	private Team heroTeam; //null pointer until buildTeam() is called
	
	/**
	 * Game constructor - uses GameSetup class UI to obtain parameters of
	 * game from input. Uses buildTeam method in Heroes class o
	 */
	Game() {
		GameSetup setup = new GameSetup();
		nHeroes = setup.getNumHeroes();
		nCities = setup.getNumCities();
		heroTeam = new Team(setup.getTeamName(), nHeroes);
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
