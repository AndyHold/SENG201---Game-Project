
/**
 * Place Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public class Location {
	
	private String placeName;
	
	
	protected Location(String cityName) {
		this.placeName = cityName;
	}
	
	
	public String getLocationName() {
		return placeName;
	}
	
	
	protected void setName(String newName) {
		placeName = newName;
	}
	
	
	public String getName() {
		return placeName;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
