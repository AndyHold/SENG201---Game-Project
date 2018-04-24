
/**
 * Place Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public class Location {
	
	private String placeName;
	protected Team heroTeam;
	protected String cityName;
	
	
	Location(String locationName, Team newTeam, String newCityName) {
		this.placeName = locationName;
		this.heroTeam = newTeam;
		this.cityName = newCityName;
	}
	
	
	public String getName() {
		return placeName;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
