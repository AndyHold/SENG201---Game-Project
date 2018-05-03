package HeroGame;
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
	
	
	/**
	 * Constructor method for Cities class
	 * @param newTeam Team, Team of heroes being controlled by the player.
	 * @param numOfCities int, Number of cities to be created
	 */
	Cities(Team newTeam, int numOfCities) {
		this.heroTeam = newTeam;
		fillCityNames();
		createCities(numOfCities);
	}
	
	
	/**
	 * Method to create random cities and add them to the ArrayList stages.
	 * @param numOfCities int, Number of cities to be created
	 */
	private void createCities(int numOfCities) {
		for(int x=0; x < (numOfCities); x++) {
			int n = this.rand.nextInt(6);
			String cityName = cityNames.get(n);
			if(x == numOfCities) {
				Villain cityVillain = Villain.AUSSIECRICKETER;
				stages.add(new City(cityName, cityVillain, heroTeam));
			} else {
				Villain cityVillain = availableVillains.getVillain();
				stages.add(new City(cityName, cityVillain, heroTeam));
			}
		}
	}
	
	
	/**
	 * Run loop for Cities class, calls each city in the stages ArrayList until completion.
	 */
	public void runCities() {
		for(City currentCity: this.stages) {
			currentCity.runCity();
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
	
		public static void main(String[] args) {
		// TODO Auto-generated method stub
//			Team testTeam = new Team("Test Team");
//			Hero h1 = new Hero("Cletus", HeroType.ALL_BLACK);
//			Hero h2 = new Hero("Ethel Aardvark", HeroType.SURVEYOR);
//			Hero h3 = new Hero("Abraham Lincoln", HeroType.SURVEYOR);
//			testTeam.addMember(h1);
//			testTeam.addMember(h2);
//			testTeam.addMember(h3);
	}

}
