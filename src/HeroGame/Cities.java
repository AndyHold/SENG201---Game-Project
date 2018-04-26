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
	
	
	Cities(Team newTeam, int numOfCities) {
		this.heroTeam = newTeam;
		fillCityNames();
		createCities(numOfCities);
	}
	
	
	private void createCities(int numOfCities) {
		for(int x=0; x < numOfCities; x++) {
			Random rand = new Random();
			int n = rand.nextInt(6);
			String cityName = cityNames.get(n);
			Villain cityVillain = availableVillains.getVillain();
			stages.add(new City(cityName, cityVillain, heroTeam));
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
