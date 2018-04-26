package HeroGame;

/**
 * PowerUpDen Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class PowerUpDen extends Location {
	
	
	
	PowerUpDen(City thisCity, Team heroTeam) {
		super(thisCity.getPlaceName(thisCity.getName() + " PowerUpDen"), heroTeam, thisCity.getName());
	}
	
	
	
	public void applyPowerUp(int powerUpIndex, int teamMemberIndex) {
		heroTeam.applyPowerUp(powerUpIndex, teamMemberIndex);
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
