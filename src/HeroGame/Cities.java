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
	
	
	Cities(Team newTeam, int numOfCities) {
		this.heroTeam = newTeam;
		fillCityNames();
		createCities(numOfCities);
	}
	
	
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
	
	
	public void runCities() {
		for(City currentCity: this.stages) {
			currentCity.runCity();
		}
	}
	
	
	public City getCity(int stageNumber) {
		return this.stages.get(stageNumber);
	}
	
	
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

	}

}
