
/**
 * City Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public class City {
	
	private String cityName;
	private Villian cityVillain;
	private Location centerArea = new Location("HomeBase");
	private Location northArea;
	private Location eastArea;
	private Location southArea;
	private Location westArea;
	private String ruler;
	private boolean completed = false;
	
	
	public void City(String newName) {
		cityName = newName;
		cityVillain = setVillian();
		ruler = cityVillain.getName();
		setDirections();
	}
	
	
	public void setVillian() {
		
	}
	
	
	public String toString() {
		return "Welcome to " + cityName + "currently ruled by " + ruler;
	}
	
	
	public void setDirections() {
		// create a hospital
		// random number between 0-3
		// set hospital to direction of number
		// repeat for VillainsLair etc
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
