package HeroGame;

/**
 * Game Manager Class for Heroes & Villains Game
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
	
	
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String newName) {
		teamName = newName;
	}
	
	public int getNCities() {
		return nCities;
	}
	
	public void setNCities(int newNCities) {
		nCities = newNCities;
	}
	
	public int getNHeroes() {
		return nHeroes;
	}
	
	public void setNHeroes(int newNHeroes) {
		nHeroes = newNHeroes;
	}
	
	public Team getTeam() {
		return heroTeam;
	}
	
	/**
	 * Launches the game welcome screen
	 */
	public void launchWelcomeScreen() {
		WelcomeScreen welcomeScreen = new WelcomeScreen(this);
	}
	
	public void closeWelcomeScreen(WelcomeScreen welcomeScreen) {
		welcomeScreen.closeScreen();
		heroTeam = new Team(teamName);
		launchTeamBuildScreen();
	}
	
	public void launchTeamBuildScreen() {
		TeamBuildScreen teamBuildScreen = new TeamBuildScreen(this);
	}
	
	public void closeTeamBuildScreen(TeamBuildScreen teamBuildScreen) {
		teamBuildScreen.closeScreen();
		//launchMainGameLoop
	}
	
	public void launchBattleScreen(Hero player, Villain baddie) {
		BattleScreen battleScreen = new BattleScreen(player, baddie, heroTeam, this);
		//Andy - may be able to get villain via methods rather than passing in?
	}
	
	public void closeBattleScreen(BattleScreen battleScreen ) {
		battleScreen.closeScreen();
		//return to wherever in game
	}
	
	public static void main(String[] args) {
		GameManager manager = new GameManager();
		//manager.launchWelcomeScreen();
		Hero h1 = new Hero("Jim", HeroType.RETURNED_SERVICEMAN);
		Villain v1 = Villain.AUSSIECRICKETER;
		Team t1 = new Team("Awesome");
		PowerUp p1 = new PowerUp(PowerUpType.CHEESE_ROLL);
		t1.addMember(h1);
		h1.eatPowerUp(p1);
		manager.launchBattleScreen(h1, v1);

	}

}
