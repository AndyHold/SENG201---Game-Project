
/**
 * City Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public class City {
	
	private String cityName;
	private Villain cityVillain;
	private HomeBase centerArea = new HomeBase(cityName);
	private Location northArea;
	private Location eastArea;
	private Location southArea;
	private Location westArea;
	private String ruler;
	private boolean completed = false;
	
	
	public void City(String newName, Villain newVillain) {
		cityName = newName;
		cityVillain = newVillain;
		ruler = cityVillain.getName();
		setDirections();
	}
	
	
	
	
	public String toString() {
		return "Welcome to " + cityName + "currently ruled by " + ruler;
	}
	
	
	public void setDirections() {
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
