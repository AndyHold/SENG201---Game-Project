/**
 * Hero Enumerated Type for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public enum Hero {
	
	ALL_BLACK(null, "win 80% of games", 100), 
	VOLUNTEER(null, "be very cheap", 100), 
	RETURNED_SERVICEMAN(null, "be a grumpy old bastard", 100), 
	FIREFIGHTER(null, "have no fear", 100), 
	NURSE(null, "have a starting strength of 110%", 110),
	FOSTER_MUM(null,"be immune to taunts", 110);
	
	private String heroName;	
	private String ability;
	private int strength;
	
	
	/*
	 * Hero constructor
	 */
	Hero(String heroName, String abilityArg, int strengthArg){
		ability = abilityArg;
		strength = strengthArg;
	}
	
	/**
	 * Setter method for individual hero name
	 * @param newHeroName a String, the individual hero's name
	 */
	public void setName(String newHeroName) {
		this.heroName = newHeroName;
	}
	
	/**
	 * Getter method for individual hero's name
	 * @return a String, the hero's name
	 */
	public String getName() {
		return heroName;
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
		case VOLUNTEER: return "A volunteer";
		case RETURNED_SERVICEMAN: return "A returned serviceman";
		case FIREFIGHTER: return "A firefighter";
		case NURSE: return "A nurse";
		case FOSTER_MUM: return "A foster mum";
		default: throw new IllegalArgumentException(); //default case mandatory
		}
	}
	
	/**
	 * Prints to output a list of the available heros in type Hero, and their abilities
	 * @return an Int, the number of different heroes available
	 */
	public int printHeroList() {
		int counter = 0;
		System.out.println("Available heroes:");
		for (Hero hero : Hero.values()) {
			counter++;
			System.out.println(counter + ". " + hero + 
					" with the ability to " + hero.ability);
		}
		return counter;
	}

}
