package HeroGame;


/**
 * HealingItemType Enumerated Type for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */


public enum HealingItemType {
	DOUBLE_BROWN(4.99, 30.0, 25,
			"A crate bottle of Doble Bronze",
			"A crate bottle of Double Brown, 745 millilitres of slightly fizzy cow's wee",
			"<html>Flicks the top off the bottle with a cigarette lighter<br>and drains the bottle. \"Gawd that's horrid\"</html>"), 
	LION_RED(9.99, 15.0, 25,
			"A pint of Lion Red",
			"A foaming pint of Lion Red, lovingly described as NZ's most generic beer",
			"Grins and skulls the pint. \"Huh. Surprisingly good\""), 
	LINDAUER(14.99, 30.0, 50,
			"A bottle of Lindauer Methode Traditionale",
			"A bottle of Lindauer Methode Traditionale, the chosen tipple of drunken racegoers throughout NZ",
			"Delicately pours a glass, and takes a sip. \"Dahling, it's simply mah-vellous\"");

	private double cost;
	private double applyTime; //In seconds
	private int healthValue;
	private String description;
	private String longDescription;
	private String response;
	
	/*
	 * Healing item constructor
	 */
	HealingItemType(double costArg, double timeArg, int healthArg, String descriptionArg, String longDescriptionArg, String responseArg) {
		this.cost = costArg;
		this.applyTime = timeArg;
		this.healthValue = healthArg;
		this.description = descriptionArg;
		this.longDescription = longDescriptionArg;
		this.response = responseArg;
		
	}
	/**
	 * Getter method for cost of this HeslingItemType
	 * @return a double the cost of this HealingItemType
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * Getter method for time taken to apply this HealingItemType(in seconds)
	 * @return a double the time in seconds taken to apply this HealingItemType
	 */
	public double getApplyTime() {
		return applyTime;
	}
	
	/**
	 * Getter method for the health effect of this HealingItemType
	 * @return an int the amount added to a hero's health by this HealingItemType
	 */
	public int getHealthValue() {
		return healthValue;
	}
	
	/**
	 * Getter method for the desciption of this HealingItemType
	 * @return a String the description of this HealingItemType
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Getter method for the long description of this HealingItemType
	 * @return a String the long description of this HealingItemType
	 */
	public String getLongDescription() {
		return longDescription;
	}
		
	/**
	 * Getter method for the Hero's response to this HealingItemType
	 * @return a String the Hero's response upon receiving this HealingItemType
	 */
	public String getResponse() {
		return response;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
	
}