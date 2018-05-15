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
	private HealingItem healingItemDrunk;
	private double healingItemTime;
	
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
	public Hero(String heroName, HeroType heroType) {
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
	 * @return an int, the hero's strength after the change
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
	 * @return a String the hero's response to eating the power up
	 */
	public String eatPowerUp(PowerUp powerUpToEat) {
		this.powerUpEaten = powerUpToEat;
		String result = this.heroName + " " + powerUpToEat.getResponse();
		System.out.println(result);
		return result;
	}
	
	/**
	 * Starts process of applying healing item by setting time value when item will apply
	 * @param healingItemToDrink the type of healing item to be applied
	 * @param drinkTime the time in seconds int he game when the healing item was drunk
	 * @return a string the hero's response to drinking the healing item
	 */
	public String drinkHealingItem(HealingItem healingItemToDrink, double drinkTime) {
		this.healingItemTime = drinkTime + healingItemToDrink.getApplyTime();
		String result = this.heroName + " " + healingItemToDrink.getResponse();
		this.healingItemDrunk = healingItemToDrink;
		System.out.println(result);
		return result;
	}

	
	/**
	 * Check whether the apply time on a healing item has been fulfilled. After certain time
	 * applies a percentage of the health benefit and reduces the amount remaining
	 * @param currentTime a double the time in seconds since the start of play
	 * @return a double the time remaining before the healing item will fully apply. O if it can apply now, or if none present.
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
	
	public String heroStatus () {
		String result = this.heroName + " is a " + this.getType().getDescription() + " with health " + this.health + " and strength " + this.strength + 
				". \n";
		if (this.powerUpEaten != null) {
			result += this.heroName + " has eaten a delicious " + powerUpEaten + ", which " + powerUpEaten.getEffect();
		}
		return result;
	}
	
	//Test Code - delete
	public static void main(String[] args) {
		Hero h1 = new Hero("Jim", HeroType.ALL_BLACK);
		PowerUp p1 = new PowerUp(PowerUpType.PAVLOVA);
		h1.eatPowerUp(p1);
		h1.getPowerUp();
		System.out.print(h1.heroStatus());
	}
}
