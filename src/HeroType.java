/**
 * Hero Enumerated Type for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public enum HeroType {
	
	ALL_BLACK(" to win 80% of games"), VOLUNTEER("being very cheap"), 
	RETURNED_SERVICEMAN("being a grumpy old bastard"), 
	FIREFIGHTER("having no fear");
	
	private String ability;
	
	/*
	 * HeroType constructor
	 */
	HeroType(String abilityArg){
		ability = abilityArg;
	}
	
	/**
	 * Getter method for particular hero's special ability
	 * @return a String containing a description of the hero's special ability
	 */
	
	public String getAbility() {
		return ability;
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
