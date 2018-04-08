import java.util.Scanner;

/**
 * Main Game Class for Heroes & Villains Game
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
	
	private int nCities;
	private int nHeroes;
	private long startTime;//0 until game play starts
	//private city[] cities;
	private Team heroTeam; //null pointer until buildTeam() is called
	
	/**
	 * Game constructor
	 */
	Game() {

		String teamName;
		Scanner in = new Scanner(System.in);
        boolean looping = true; 
	    while (looping) {
	    	System.out.println("Input team name (2-10 characters):");
	    	teamName = in.nextLine();
	    	if (teamName.length() < MIN_NAME_LENGTH || teamName.length() > MAX_NAME_LENGTH) {
	    		System.out.println("Team name must be 2-10 characters.");
	    	}
	    	else {
	    		looping = false;
	    	}
	    }
	    looping = true;
	    while (looping) {
	    	System.out.println("Input number of cities to visit(3-6):");
	    	this.nCities = in.nextInt();
	    	if (this.nCities < MIN_NUM_CITIES || this.nCities > MAX_NUM_CITIES) {
	    		System.out.println("Number of cities must be 3-6.");
	    	}
	    	else {
	    		looping = false;
	    	}	    	
	    }
	    looping = true;
	    while (looping) {
	    	System.out.println("Input number of heroes in team (1-3):");
	    	this.nHeroes = in.nextInt();
	    	if (this.nHeroes < 1 || this.nHeroes > MAX_TEAM_SIZE) {
	    		System.out.println("Number of cities must be 3-6.");
	    	}
	    	else {
	    		looping = false;
	    	}	    	
	    }
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
