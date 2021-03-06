package HeroGame;
/**
 * HeroType Enumerated Type for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public enum HeroType {
	
	ALL_BLACK("(All Black)", "have a virtually unprecedented success in games. Wins 50% more games", 100), 
	SURVEYOR("(Surveyor)", "know the layout of a town at a single steely eyed glance", 100), 
	RETURNED_SERVICEMAN("(Veteran)", "be virtually immune to villainy, having seen it all before. (Takes half damage in all battles)", 100), 
	FIREFIGHTER("(Firefighter)", "be seemingly hewn from stone. Has a strength of 125", 125), 
	NURSE("(Nurse)", "make your health dollars go further by doubling health bonuses", 100),
	FOSTER_MUM("(Foster Mum)", "read minds, as she has seen it all before (Sometimes predicts the number in the Guess Number game)", 100);
	
	private String description;	
	private String ability;
	private int strength;

	
	/*
	 * HeroType constructor
	 */
	HeroType(String descriptionArg, String abilityArg, int strengthArg){
		description = descriptionArg;
		ability = abilityArg;
		strength = strengthArg;

	}
	
	/**
	 * Getter method for HeroType's description
	 * @return a String containing a short description of this HeroType 
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Getter method for HeroType's special ability
	 * @return a String containing a description of the HeroType's special ability
	 */
	public String getAbility() {
		return ability;
	}
	
	/**
	 * Getter method for particular HeroType's default strength
	 * @return an int the HeroType's default strength
	 */
	public int getStrength() {
		return strength;
	}
	
}