package HeroGame;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * City Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public class City {
	
	private String cityName;
	private Team currentTeam;
	private Villain cityVillain;
	private HomeBase centerArea = new HomeBase(this, currentTeam);
	private Location northArea;
	private Location eastArea;
	private Location southArea;
	private Location westArea;
	private String ruler;
	private boolean completed = false;
	private boolean mapped = false;
	private HashMap<String, String> placeNames = new HashMap<String, String>();
	
	
	City(String newName, Villain newVillain, Team newTeam) {
		cityName = newName;
		cityVillain = newVillain;
		ruler = cityVillain.getName();
		currentTeam = newTeam;
		setPlaceNames();
		setDirections();
	}
	
	
	public void showMap() {
		System.out.println("To the South is:");
		System.out.println(southArea.getName());
		System.out.println("To the East is:");
		System.out.println(eastArea.getName());
		System.out.println("To the North is:");
		System.out.println(northArea.getName());
		System.out.println("To the West is:");
		System.out.println(westArea.getName());
	}
	
	
	public boolean isMapped() {
		return mapped;
	}
	
	
	public void makeMapped() {
		mapped = true;
	}
	
	
	private void setPlaceNames() {
		placeNames.put("Springfield HomeBase", "");
		placeNames.put("Springfield Hospital", "");
		placeNames.put("Springfield PowerUpDen", "");
		placeNames.put("Springfield Shop", "");
		
		placeNames.put("Te Puke HomeBase", "");
		placeNames.put("Te Puke Hospital", "");
		placeNames.put("Te Puke PowerUpDen", "");
		placeNames.put("Te Puke Shop", "");
		
		placeNames.put("Gore HomeBase", "");
		placeNames.put("Gore Hospital", "");
		placeNames.put("Gore PowerUpDen", "");
		placeNames.put("Gore Shop", "");
		
		placeNames.put("Ohakune HomeBase", "");
		placeNames.put("Ohakune Hospital", "");
		placeNames.put("Ohakune PowerUpDen", "");
		placeNames.put("Ohakune Shop", "");
		
		placeNames.put("Paeroa HomeBase", "");
		placeNames.put("Paeroa Hospital", "");
		placeNames.put("Paeroa PowerUpDen", "");
		placeNames.put("Paeroa Shop", "");
		
		placeNames.put("Taihape HomeBase", "");
		placeNames.put("Taihape Hospital", "");
		placeNames.put("Taihape PowerUpDen", "");
		placeNames.put("Taihape Shop", "");
	}
	
	
	/**
	 * consults the place name dictionary and returns the place name.
	 * @param key String place type and city string representation.
	 * @return String PlaceName for location.
	 */
	public String getPlaceName(String key) {
		return placeNames.get(key);
	}
	
	
	public String getName() {
		return cityName;
	}
	
	
	
	public String toString() {
		return "Welcome to " + cityName + "currently ruled by " + ruler;
	}
	
	
	public void setDirections() {
//		ArrayList<Location> locations = new ArrayList<Location>();
//		locations.add(new VilliansLair(this, cityVillain, this.currentTeam));
//		locations.add(hospital);
//		locations.add(powerupden);
//		locations.add(shop);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
