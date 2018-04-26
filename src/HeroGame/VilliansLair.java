package HeroGame;

/**
 * VillainsLair Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class VilliansLair extends Location {
	
	/**
	 * Constructor for VillainsLair Class.
	 * @param cityName Name of the City containing this Villains Lair
	 * @param currentVillian Current Villain of this city.
	 */
	VilliansLair(City thisCity, Villain currentVillian, Team heroTeam) {
		super(currentVillian.getLairName(thisCity.getName()), heroTeam, thisCity.getName());
	}
	
	
	@Override
	public String toString() {
		return super.getName() + this.getName();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
