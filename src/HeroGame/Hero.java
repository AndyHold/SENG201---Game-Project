package HeroGame;
/**
 * Hero Enumerated Type for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public enum Hero {
	
	ALL_BLACK(null, "have a virtually unprecedented success in games. Wins 50% more games", 100, 100, null), 
	SURVEYOR(null, "know the layout of a town at a single steely eyed glance", 100, 100, null), 
	RETURNED_SERVICEMAN(null, "be virtually immune to villainy, having seen it all before. (Takes half damage in all battles)", 100, 100, null), 
	FIREFIGHTER(null, "be seemingly hewn from stone. Has a starting health of 125", 100, 125, null), 
	NURSE(null, "make your health dollars go further by doubling health bonuses", 100, 100, null),
	FOSTER_MUM(null,"see into the future, as she has seen it all before (Can sometimes predict the outcome of games)", 100, 100, null);
	
	private String heroName;	
	private String ability;
	private int strength;
	private int health;
	private PowerUp powerUpEaten;
	
	
	/*
	 * Hero constructor
	 */
	Hero(String heroName, String abilityArg, int strengthArg, int healthArg, PowerUp powerUpArg){
		ability = abilityArg;
		strength = strengthArg;
		health = healthArg;
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
	 * Getter method for particular hero's strength
	 * @return an int the hero's current strength
	 */
	public int getStrength() {
		return strength;
	}
	
	/**
	 * Method for adding or subtracting strength from Hero
	 * @param strengthChange an int, the amount by which the hero's strength is to be changed
	 * @return an int, the hero's stregnth after the change
	 */
	public int changeStrength(int strengthChange) {
		this.strength += strengthChange;
		return this.strength;
	}
	
	/**
	 * Getter method for particular hero's health
	 * @return an int the hero's current health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Method for adding or subtracting health from Hero
	 * @param healthChange an int, the amount by which the hero's health is to be changed
	 * @return an int, the hero's health after the change
	 */
	public int changeHealth(int healthChange) {
		this.health += healthChange;
		return this.health;
	}
	
	/**
	 * Getter method to return any PowerUp that the Hero has eaten
	 * @return a PowerUp, the PowerUp most recently eaten by the hero
	 */
	public PowerUp getPowerUp() {
		return powerUpEaten;
	}
	
	/**
	 * Clears any power ups the Hero has eaten back to null
	 */
	public void clearPowerUp() {
		this.powerUpEaten = null;
	}
	
	/**
	 * Setter method to have hero eat power up
	 * @param powerUpToEat
	 */
	public void eatPowerUp(PowerUp powerUpToEat) {
		this.powerUpEaten = powerUpToEat;
		System.out.println(this.heroName + " " + powerUpToEat.getResponse());
	}
	

	@Override
	public String toString() {
		switch(this) {
		case ALL_BLACK: return "An All Black";
		case SURVEYOR: return "A surveyor";
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
