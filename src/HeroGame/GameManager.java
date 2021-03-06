package HeroGame;




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
	private Sound sounds = new Sound();


	
	

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
	 * Getter method for the Cities the team will play through
	 * @return a Cities the Cities the tema will play through
	 */
	public Cities getCities () {
		return cities;
	}
	
	/**
	 * Getter method for the Team to play the game
	 * @return a Team, the team of heroes the user will play the game with
	 */
	public Team getTeam() {
		return heroTeam;
	}
	
	/**
	 * Launches a WelcomeScreen, the initial screen for the game 
	 */
	public void launchWelcomeScreen() {
		WelcomeScreen welcomeScreen = new WelcomeScreen(this);
		//sounds.playMusic();
	}
	
	/**
	 * Remotely closes a game WelcomeScreen and calls launchTeamBuildScreen()
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
		heroTeam.startClock();
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
		cityScreen.closeScreen();
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
			if (heroTeam.getTeamSize() < 1) {//Team are all dead
				launchFinalScreen("lose");
			} else { //Carry on with game
				CityScreen thisStage = new CityScreen(city, direction, this);
			}
		} else {//Villain has been defeated
			city = cities.getStage();
			if(city != null) {
				CityScreen thisStage = new CityScreen(city, Direction.CENTER, this);
			} else {
				launchFinalScreen("win");
			}
		}
	}
	
	/**
	 * Launches a FinalScreen being the screen where win/lose/credits are displayed
	 * @param outcome a String denoting win or lose
	 */
	public void launchFinalScreen(String outcome) {
		FinalScreen finalScreen = new FinalScreen(outcome, this);
		//sounds.stopMusic();
	}
	
	/**
	 * Launches a VendorScreen, the screen where items are purchased in the shop
	 * @param direction the Direction within the city from where the screen is launched, and to which it will return  on close
	 * @param city the City currently being played
	 * @param shop a Shop class which provides most of the methods required in this location
	 * @param cityScreen the CityScreen from which the VendorScreen is launched
	 */
	public void launchVendorScreen(Direction direction, City city, Shop shop, CityScreen cityScreen) {
		cityScreen.closeScreen();
		VendorScreen vendor = new VendorScreen(this, direction, city, shop);
	}

	/**
	 * Remotely closes a VendorScreen and creates a new CityScreen
	 * @param vendor the VendorScreen to be closed
	 * @param city the City currently being played
	 * @param direction the direction in which the shop is located within the current city
	 */
	public void closeVendorScreen(VendorScreen vendor, City city, Direction direction) {
		vendor.closeScreen();
		CityScreen cityScreen = new CityScreen(city, direction, this);
		
	}
	
	


	/**
	 * Main method for game
	 * @param args
	 */
	public static void main(String[] args) {
		GameManager manager = new GameManager();
		manager.launchWelcomeScreen();
	}
	
	
}
