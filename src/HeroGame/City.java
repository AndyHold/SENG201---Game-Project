package HeroGame;
import java.util.ArrayList;
import java.util.Random;
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
	private HomeBase centerArea;
	private Location northArea;
	private Location eastArea;
	private Location southArea;
	private Location westArea;
	private String ruler;
	private boolean completed = false;
	private boolean mapped = false;
	private HashMap<String, String> placeNames = new HashMap<String, String>();
	private Random rand = new Random();
	private ArrayList<Location> availableLocations = new ArrayList<Location>();
	
	
	City(String newName, Villain newVillain, Team newTeam) {
		cityName = newName;
		cityVillain = newVillain;
		ruler = cityVillain.getName();
		currentTeam = newTeam;
		setPlaceNames();
		fillAvailableLocations();
		centerArea = new HomeBase(this, this.currentTeam);
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
		this.eastArea = getLocation();
		this.eastArea.setDirection(Direction.EAST);
		this.westArea = getLocation();
		this.westArea.setDirection(Direction.WEST);
		this.southArea = getLocation();
		this.southArea.setDirection(Direction.SOUTH);
		this.northArea = getLocation();
		this.northArea.setDirection(Direction.NORTH);
	}
	
	
	private Location getLocation() {
		int n = this.rand.nextInt(availableLocations.size());
		Location location = availableLocations.get(n);
		availableLocations.remove(n);
		return location;
	}
	
	
	private void fillAvailableLocations() {
		availableLocations.add(new VilliansLair(this, cityVillain, this.currentTeam));
		availableLocations.add(new Hospital(this, this.currentTeam));
		availableLocations.add(new PowerUpDen(this, this.currentTeam));
		availableLocations.add(new Shop(this, this.currentTeam));
	}
	
	
	public void runCity() {
		int n = this.centerArea.runLocation();
		boolean finishedCity = false;
		while(!finishedCity) {
			switch(n) {
			case 0:
				n = this.centerArea.runLocation();
				
			case 1:
				switch(this.northArea.getLocationType()) {
				
				case HOSPITAL:
					((Hospital) this.northArea).runLocation();
					break;
					
				case VILLIANSLAIR:
					((VilliansLair) this.northArea).runLocation();
					break;
					
				case SHOP:
					((Shop) this.northArea).runLocation();
					break;
					
				case POWERUPDEN:
					((PowerUpDen) this.northArea).runLocation();
					break;
				}
				
				
			case 2:
				switch(this.eastArea.getLocationType()) {
				
				case HOSPITAL:
					((Hospital) this.eastArea).runLocation();
					break;
					
				case VILLIANSLAIR:
					((VilliansLair) this.eastArea).runLocation();
					break;
					
				case SHOP:
					((Shop) this.eastArea).runLocation();
					break;
					
				case POWERUPDEN:
					((PowerUpDen) this.eastArea).runLocation();
					break;
				}
				
				
			case 3:
				switch(this.southArea.getLocationType()) {
				
				case HOSPITAL:
					((Hospital) this.southArea).runLocation();
					break;
					
				case VILLIANSLAIR:
					((VilliansLair) this.southArea).runLocation();
					break;
					
				case SHOP:
					((Shop) this.southArea).runLocation();
					break;
					
				case POWERUPDEN:
					((PowerUpDen) this.southArea).runLocation();
					break;
				}
				
				
			case 4:
				switch(this.westArea.getLocationType()) {
				
				case HOSPITAL:
					((Hospital) this.westArea).runLocation();
					break;
					
				case VILLIANSLAIR:
					((VilliansLair) this.westArea).runLocation();
					break;
					
				case SHOP:
					((Shop) this.westArea).runLocation();
					break;
					
				case POWERUPDEN:
					((PowerUpDen) this.westArea).runLocation();
					break;
				}
				
				
			}
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Team testTeam = new Team("Test Team");
		Hero h1 = new Hero("Cletus", HeroType.ALL_BLACK);
		Hero h2 = new Hero("Ethel Aardvark", HeroType.SURVEYOR);
		Hero h3 = new Hero("Abraham Lincoln", HeroType.SURVEYOR);
		testTeam.addMember(h1);
		testTeam.addMember(h2);
		testTeam.addMember(h3);
		Villain peter = Villain.AUSSIECRICKETER;
		City newCity = new City("Taihape", peter, testTeam);
		System.out.println(newCity.getName());
	}

}
