package HeroGame;
/**
 * HeroType Enumerated Type for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public enum HeroType {
	
	ALL_BLACK("An All Black", "have a virtually unprecedented success in games. Wins 50% more games", 100), 
	SURVEYOR("A surveyor", "know the layout of a town at a single steely eyed glance", 100), 
	RETURNED_SERVICEMAN("A returned serviceman", "be virtually immune to villainy, having seen it all before. (Takes half damage in all battles)", 100), 
	FIREFIGHTER("A firefighter", "be seemingly hewn from stone. Has a strength of 125", 125), 
	NURSE("A nurse", "make your health dollars go further by doubling health bonuses", 100),
	FOSTER_MUM("A foster mum", "see into the future, as she has seen it all before (Can sometimes predict the outcome of games)", 100);
	
	private String description;	
	private String ability;
	private int strength;

	
	/*
	 * Hero constructor
	 */
	HeroType(String descriptionArg, String abilityArg, int strengthArg){
		description = descriptionArg;
		ability = abilityArg;
		strength = strengthArg;

	}
	
	/**
	 * Getter method for hero type's description
	 * @return a String containing a short description of the hero 
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Getter method for hero type's special ability
	 * @return a String containing a description of the hero type's special ability
	 */
	public String getAbility() {
		return ability;
	}
	
	/**
	 * Getter method for particular hero type's default strength
	 * @return an int the hero type's default strength
	 */
	public int getStrength() {
		return strength;
	}
	
}