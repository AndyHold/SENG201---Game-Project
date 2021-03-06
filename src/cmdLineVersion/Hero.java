package cmdLineVersion;
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
	private HealingItem healingItemDrunk;
	private double healingItemTime;
	
	/**
	 * Empty Hero Constructor. Used as a dummy to access HeroList
	 */
	@Deprecated
	Hero(){
	}
	
	
	/**
	 * Parameterised constructor for Hero - used in building team
	 * @param heroName a String the name of the new Hero
	 * @param heroType a HeroType the type of Hero to be created
	 */
	public Hero(String heroName, HeroType heroType) {
		this.heroName = heroName;
		this.heroType = heroType;
		this.description = heroType.getDescription();
		this.ability = heroType.getAbility();
		this.strength = heroType.getStrength();
		}
			
	/**
	 * Setter method for individual Hero name
	 * @param newHeroName a String, the individual Hero's name
	 */
	public void setName(String newHeroName) {
		this.heroName = newHeroName;
	}
	
	/**
	 * Getter method for individual Hero's name
	 * @return a String, the Hero's name
	 */
	public String getName() {
		return heroName;
	}
	
	/**
	 * Getter method for individual Hero's type
	 * @return a HeroType, the Hero's type
	 */
	public HeroType getType() {
		return heroType;
	}
	
		
	/**
	 * Getter method for particular Hero's special ability
	 * @return a String containing a description of the Hero's special ability
	 */
	public String getAbility() {
		return ability;
	}
	
	/**
	 * Getter method for particular Hero's strength
	 * @return an int the Hero's current strength
	 */
	public int getStrength() {
		return strength;
	}
	
	/**
	 * Method for adding or subtracting strength from Hero
	 * @param strengthChange an int, the amount by which the Hero's strength is to be changed
	 * @return an int, the Hero's strength after the change
	 */
	public int changeStrength(int strengthChange) {
		this.strength += strengthChange;
		return this.strength;
	}
	
	/**
	 * Getter method for particular Hero's health
	 * @return an int the Hero's current health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Method for adding or subtracting health from Hero
	 * @param healthChange an int, the amount by which the Hero's health is to be changed
	 * @return an int, the Hero's health after the change
	 */
	public int changeHealth(int healthChange) {
		this.health += healthChange;
		return this.health;
	}
	
	/**
	 * Getter method to return any PowerUp that the Hero has eaten
	 * @return a PowerUp, the PowerUp most recently eaten by the Hero
	 */
	public PowerUp getPowerUp() {
		return powerUpEaten;
	}
	
	/**
	 * Clears any PowerUps the Hero has eaten back to null
	 */
	public void clearPowerUp() {
		this.powerUpEaten = null;
	}
	
	/**
	 * Setter method to have Hero eat PowerUp
	 * @param powerUpToEat
	 * @return a String the Hero's response to eating the PowerUp
	 */
	public String eatPowerUp(PowerUp powerUpToEat) {
		this.powerUpEaten = powerUpToEat;
		String result = this.heroName + " " + powerUpToEat.getResponse();
		return result;
	}
	
	/**
	 * Starts process of applying HealingItem by setting time value when item will apply
	 * @param healingItemToDrink the type of HealingItem to be applied
	 * @param drinkTime the time in seconds in the game when the HealingItem was drunk
	 * @return a string the hero's response to drinking the HealingItem
	 */
	public String drinkHealingItem(HealingItem healingItemToDrink, double drinkTime) {
		this.healingItemTime = drinkTime + healingItemToDrink.getApplyTime();
		String result = this.heroName + " " + healingItemToDrink.getResponse();
		this.healingItemDrunk = healingItemToDrink;
		return result;
	}

	
	/**
	 * Check whether the apply time on a HealingItem has been fulfilled. After certain time
	 * applies a percentage of the health benefit and reduces the amount remaining
	 * @param currentTime a double the time in seconds since the start of play
	 * @return a double the time remaining before the HealingItem will fully apply. O if it can apply now, or if none present.
	 */
	public double checkHealingItemTime(double currentTime) {
		double remainingTime = this.healingItemTime - currentTime;

		if (this.healingItemDrunk == null) {
			return 0.0;
		} 
		switch (this.healingItemDrunk.getHealthValue()){
		case(100):
			if (remainingTime <= (this.healingItemDrunk.getApplyTime() * 0.75)) {
				this.healingItemDrunk.changeHealthValue(-25);
				this.health += 25;
				return remainingTime;
			} else {
				return remainingTime;
			}
		case(75):
			if (remainingTime <= (this.healingItemDrunk.getApplyTime() * 0.5)) {
				this.healingItemDrunk.changeHealthValue(-25);
				this.health += 25;
				return remainingTime;
			} else {
				return remainingTime;
			}
		case(50): //May cause slightly eccentric timing for power ups of initial value 50. SFW.
			if (remainingTime <= (this.healingItemDrunk.getApplyTime() * 0.25)) {
				this.healingItemDrunk.changeHealthValue(-25);
				this.health += 25;
				return remainingTime;
			} else {
				return remainingTime;
			}
		case(25):
			if (remainingTime <= 0.0) {
				this.healingItemDrunk = null;
				this.healingItemTime = 0;
				this.health += 25;
				return 0;
			} else {
				return remainingTime;
			}
		default:
			return 0.0;
			
		}
			
	}
	

	@Override
	public String toString() {
		return heroName + " " + description;
	}
	
	/**
	 * Prints to output a list of the available heroes in type Hero, and their abilities. Command Line Version Only
	 * @return an int, the number of different heroes available
	 */
	@Deprecated
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
	
	/**
	 * Prints the status of a given Hero. Used in the command line version of the game
	 * @return a String detailing the name, description, health, strength of a Hero and any PowerUps eaten
	 */
	@Deprecated
	public String heroStatus () {
		String result = this.heroName + " is a " + this.getType().getDescription() + " with health " + this.health + " and strength " + this.strength + 
				". \n";
		if (this.powerUpEaten != null) {
			result += this.heroName + " has eaten a delicious " + powerUpEaten + ", which " + powerUpEaten.getEffect();
		}
		return result;
	}

}
