
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
<<<<<<< HEAD
		cityVillain = newVillain;
		ruler = cityVillain.getName();
=======
		//cityVillain = setVillain();
		//ruler = cityVillain.getName();
>>>>>>> 5570e5cd19b4068c85a504a5627079555b2c3000
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
