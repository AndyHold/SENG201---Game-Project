package HeroGame;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;


/**
 * Cities Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class Cities {
	
	private Villains availableVillains = new Villains();
	private Team heroTeam;
	private ArrayList<City> stages = new ArrayList<City>();
	private ArrayList<String> cityNames = new ArrayList<String>();
	private Random rand = new Random();
	private int currentStage;
	
	
	/**
	 * Constructor method for Cities class
	 * @param newTeam Team, Team of heroes being controlled by the player.
	 * @param numOfCities int, Number of cities to be created
	 */
	public Cities(Team newTeam, int numOfCities) {
		this.heroTeam = newTeam;
		fillCityNames();
		createCities(numOfCities);
	}
	
	/**
	 * Returns the next city in the game to be played
	 * @return a City the next city the team must play
	 */
	public City getStage() {
		if(currentStage < stages.size()) {
			int newStage = currentStage;
			currentStage++;
			return stages.get(newStage);
		}
		else {
			return null;
		}
	}
	
	
	/**
	 * Method to create random cities and add them to the ArrayList stages.
	 * @param numOfCities int, Number of cities to be created
	 */
	private void createCities(int numOfCities) {
		for(int x=0; x < numOfCities; x++) {
			int n = this.rand.nextInt(cityNames.size());
			String cityName = cityNames.get(n);
			cityNames.remove(n);
			if(x == numOfCities - 1) {
				Villain cityVillain = Villain.AUSSIECRICKETER;
				stages.add(new City(cityName, cityVillain, heroTeam));
			} else {
				Villain cityVillain = availableVillains.getVillain();
				stages.add(new City(cityName, cityVillain, heroTeam));
			}
		}
	}
	
	
	/**
	 * Method to get a city from a given stage number.
	 * @param stageNumber int, Number of stage to fetch city from.
	 * @return City, City allocated to given stage number.
	 */
	public City getCity(int stageNumber) {
		return this.stages.get(stageNumber);
	}
	
	
	/**
	 * Method to fill cityNames ArrayList
	 */
	private void fillCityNames() {
		cityNames.add("Springfield");
		cityNames.add("Te Puke");
		cityNames.add("Gore");
		cityNames.add("Ohakune");
		cityNames.add("Paeroa");
		cityNames.add("Taihape");
	}
	
	/**
	 * Returns a list of cities in a formatted String. Used in FinalScreen if game is won
	 * @return a String a formatted list of Cities in the game 
	 */
	public String getCityList() {
		String result = "";
		for (int i = 0; i < (stages.size() -2); i++) { //Return cities up to last two separated by a comma
			result += (stages.get(i).getName() + ", ");
		}
		result += (stages.get(stages.size() - 2).getName() + " & " + stages.get(stages.size()-1).getName()); //Last two separated by &
		return result;
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("Cities class currently has {0} stages, and {1} options for city name left", stages.size(), cityNames.size());
	}


}
