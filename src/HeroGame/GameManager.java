package HeroGame;

import cmdLineVersion.Selector;

/**
 * Game Manager Class for Heroes & Villains Game
 * Includes main() for GUI game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

//Assume this will replace Game Class in final product

public class GameManager {
	

	
	private String teamName;
	private int nCities;
	private int nHeroes;
	private long startTime;//0 until game play starts
	private Cities cities;
	private Team heroTeam; //null pointer until buildTeam() is called
	private Selector gameSelector = new Selector();	
	
	/**
	 * Getter method for teamName
	 * @return a String, the name of the Team
	 */
	public String getTeamName() {
		return teamName;
	}
	
	/**
	 * Setter method for teamName
	 * @param newName a String the name of the Team
	 */
	public void setTeamName(String newName) {
		teamName = newName;
	}
	
	/**
	 * Getter method for nCities, the number of cities to be played
	 * @return an int the number of cities to be played in the game
	 */
	public int getNCities() {
		return nCities;
	}
	
	/**
	 * Setter method for nCities, the number of cities to be played in the game
	 * @param newNCities an int the number of cities to be played in the game
	 */
	public void setNCities(int newNCities) {
		nCities = newNCities;
	}
	
	/**
	 * Getter method for nHeroes, the number of heroes in the Team
	 * @return an int, the number of heroes in the Team
	 */
	public int getNHeroes() {
		return nHeroes;
	}
	
	/**
	 * Setter method for nHeroes, the number of heroes in the Team
	 * @param newNHeroes an int, the number of heroes in the Team
	 */
	public void setNHeroes(int newNHeroes) {
		nHeroes = newNHeroes;
	}
	
	/**
	 * Getter method for the Team to play the game
	 * @return a Team, the team of heroes the user will play the game with
	 */
	public Team getTeam() {
		return heroTeam;
	}
	
	/**
	 * Launches a game welcome screen
	 */
	public void launchWelcomeScreen() {
		WelcomeScreen welcomeScreen = new WelcomeScreen(this);
	}
	
	/**
	 * Remotely closes a game WelcomeScreen
	 * @param welcomeScreen a WelcomeScreen the screen to be closed
	 */
	public void closeWelcomeScreen(WelcomeScreen welcomeScreen) {
		welcomeScreen.closeScreen();
		heroTeam = new Team(teamName);
		launchTeamBuildScreen();
	}
	
	/**
	 * Launches a TeamBuildScreen where heroes are added to the new Team
	 */
	public void launchTeamBuildScreen() {
		TeamBuildScreen teamBuildScreen = new TeamBuildScreen(this);
	}
	
	/**
	 * Remotely closes a TeamBuildScreen and launches the first CityScreen
	 * @param teamBuildScreen, the TeamBuildScreen to be closed
	 */
	public void closeTeamBuildScreen(TeamBuildScreen teamBuildScreen) {
		teamBuildScreen.closeScreen();
		cities = new Cities(this.heroTeam, this.nCities);
		City first_level = cities.getStage();
		//launchMainGameLoop
		CityScreen first_level_screen = new CityScreen(first_level, Direction.CENTER, this);

	}
	
	/**
	 * Remotely closes a CityScreen for Battle
	 * @param cityScreen the CityScreen to close
	 * @param city the current City
	 * @param direction the Direction of the VillainsLair from which the CityScreen is closed
	 * @param hero the Hero chosen for the current Battle
	 */
	public void closeCityScreen(CityScreen cityScreen, City city, Direction direction, Hero hero) {
		
		BattleScreen battlescreen = new BattleScreen(hero, city, heroTeam, this, direction);
	}
	
	/**
	 * Launches a BattleScreen
	 * @param player a Hero the Hero chosen for this battle
	 * @param city a City the current City
	 * @param direction a Direction the direction of the VillainsLair from which this battle will be launched
	 */
	public void launchBattleScreen(Hero player, City city, Direction direction) {
		BattleScreen battleScreen = new BattleScreen(player, city, heroTeam, this, direction);
	}
	
	/**
	 * Remotely closes a BattleScreen. Used if Villain/Hero defeated or Hero retreats 
	 * @param battleScreen the BattleScreen to be closed
	 * @param city a City the City to return to
	 * @param direction a Direction the Direction to be returned to
	 */
	public void closeBattleScreen(BattleScreen battleScreen, City city, Direction direction ) {
		battleScreen.closeScreen();
		if(city.getVillain().isAlive()) {
			//Check if any heroes left in team. If not got to game over screen, otherwise,
			CityScreen thisStage = new CityScreen(city, direction, this);
		} else {
			city = cities.getStage();
			if(city != null) {
				CityScreen thisStage = new CityScreen(city, Direction.CENTER, this);
			} else {
				//DO FINISHED GAME SCREEN
			}
		}
	}
	
	/**
	 * Main method for game
	 * @param args
	 */
	public static void main(String[] args) {
		GameManager manager = new GameManager();
		manager.launchWelcomeScreen();
//**************Test code******************************************
//		Hero h1 = new Hero("Jim", HeroType.RETURNED_SERVICEMAN);
//		Villain v1 = Villain.AUSSIECRICKETER;
//		Team t1 = new Team("Awesome");
//		City c1 = new City("Springfield", v1, t1);
//		PowerUp p1 = new PowerUp(PowerUpType.CHEESE_ROLL);
//		t1.addMember(h1);
//		h1.eatPowerUp(p1);
//		manager.launchBattleScreen(h1, c1, Direction.CENTER);

	}

}
