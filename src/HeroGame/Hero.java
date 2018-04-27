package HeroGame;
/**
 * Hero Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public class Hero {
	
	private String heroName;
	private HeroType heroType;
	private String description;
	private String ability;
	private int health = 100;
	private int strength;
	private PowerUp powerUpEaten;
	
	/**
	 * Empty Hero Constructor. Used as a dummy to access HeroList
	 */
	Hero(){
	}
	
	
	/**
	 * Parameterised constructor for Hero - used in building team
	 * @param heroName
	 * @param heroType
	 */
	Hero(String heroName, HeroType heroType) {
		this.heroName = heroName;
		this.heroType = heroType;
		this.description = heroType.getDescription();
		this.ability = heroType.getAbility();
		this.strength = heroType.getStrength();
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
	 * Getter method for individual hero's type
	 * @return a HeroType, the hero's type
	 */
	public HeroType getType() {
		return heroType;
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
		return description;
	}
	
	/**
	 * Prints to output a list of the available heros in type Hero, and their abilities
	 * @return an Int, the number of different heroes available
	 */
	public int printHeroList() {
		int counter = 0;
		System.out.println("Available heroes:");
		for (HeroType hero : HeroType.values()) {

			System.out.println(counter + ". " + hero.getDescription() + 
					" with the ability to " + hero.getAbility());
			counter++;
		}
		return counter;
	}

}
