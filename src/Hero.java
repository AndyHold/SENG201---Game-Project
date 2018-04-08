/**
 * Hero Enumerated Type for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public enum Hero {
	
	ALL_BLACK(" to win 80% of games", 100), VOLUNTEER("being very cheap", 100), 
	RETURNED_SERVICEMAN("being a grumpy old bastard", 100), 
	FIREFIGHTER("having no fear", 100);
	
	private String ability;
	
	private int strength;
	
	
	/*
	 * Hero constructor
	 */
	Hero(String abilityArg, int strengthArg){
		ability = abilityArg;
		strength = strengthArg;
	}
	
	/**
	 * Getter method for particular hero's special ability
	 * @return a String containing a description of the hero's special ability
	 */
	public String getAbility() {
		return ability;
	}
	
	/**
	 * Getter method for particular hero
	 * @return an int the hero's current strength
	 */
	public int getStrength() {
		return strength;
	}
	
	public void changeStrength(int strengthChange) {
		this.strength += strengthChange;

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		switch(this) {
		case ALL_BLACK: return "An All Black";
		case VOLUNTEER: return "A Volunteer";
		case RETURNED_SERVICEMAN: return "A returned serviceman";
		case FIREFIGHTER: return "A firefighter";
		default: throw new IllegalArgumentException(); //default case mandatory
		}
	}

}
