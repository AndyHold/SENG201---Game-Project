import java.util.Scanner;

/**
 * Game Setup Class for Heroes & Villains Game
 * Contains the UI for initial game setup
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public class GameSetup {
	//Variables from specification 
	private static final int MAX_NAME_LENGTH = 10;
	private static final int MIN_NAME_LENGTH = 2;
	private static final int MAX_NUM_CITIES = 6;
	private static final int MIN_NUM_CITIES = 3;
	private static final int MAX_TEAM_SIZE = 3;
	
	private String teamName;
	private int numCities;
	private int startNumHeroes;
	
	/**
	 * Interactive constructor. Requests team name, number of cities and 
	 * number of heroes in team. 
	 */
	GameSetup() {
		Scanner in = new Scanner(System.in); //Don't close this anywhere - breaks things
        boolean looping = true; 
	    while (looping) {
	    	System.out.println("Input team name (2-10 characters):");
	    	this.teamName = in.nextLine();
	    	if (this.teamName.length() < MIN_NAME_LENGTH || this.teamName.length() > MAX_NAME_LENGTH) {
	    		System.out.println("Team name must be 2-10 characters.");
	    	}
	    	else {
	    		looping = false;
	    	}
	    }
	    looping = true;
	    // **********No protection against non int input yet ********** 
	    while (looping) { //Obtain number of cities to visit from input
	    	System.out.println("Input number of cities to visit(3-6):");
	    	this.numCities = in.nextInt();
	    	if (this.numCities < MIN_NUM_CITIES || this.numCities > MAX_NUM_CITIES) {
	    		System.out.println("Number of cities must be 3-6.");
	    	}
	    	else {
	    		looping = false;
	    	}	    	
	    }
	    looping = true;
	    // **********No protection against non int input yet ********** 
	    while (looping) { //Obtain number of heroes in team from input
	    	System.out.println("Input number of heroes in team (1-3):");
	    	this.startNumHeroes = in.nextInt();
	    	if (this.startNumHeroes < 1 || this.startNumHeroes > MAX_TEAM_SIZE) {
	    		System.out.println("Number of cities must be 3-6.");
	    	}
	    	else {
	    		looping = false;
	    	}
	    }
	    
	}
	
	@Override
	public String toString() {
		return "In this game, " + teamName + ", a team of " + startNumHeroes + 
				" will battle their way through " + numCities + " cities.";
	}
	
	/**
	 * Getter method for team name
	 * @return a String the team name
	 */
	public String getTeamName() {
		return teamName;
	}
	
	/**
	 * Getter method for number of heroes in team
	 * @return an int the number of heroes in the team
	 */
	public int getNumHeroes() {
		return startNumHeroes;
	}
	
	/**
	 * Getter method for number of cities in game
	 * @return an int the number of cities
	 */
	public int getNumCities() {
		return numCities;
	}

}
