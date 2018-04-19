import java.util.ArrayList;


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
	
	
	City(String newName, Villain newVillain, Team newTeam) {
		cityName = newName;
		cityVillain = newVillain;
		ruler = cityVillain.getName();
		currentTeam = newTeam;
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
	
	
	public String getName() {
		return cityName;
	}
	
	
	
	public String toString() {
		return "Welcome to " + cityName + "currently ruled by " + ruler;
	}
	
	
	public void setDirections() {
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(new VilliansLair(cityName, cityVillain));
//		locations.add(hospital);
//		locations.add(powerupden);
//		locations.add(shop);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
