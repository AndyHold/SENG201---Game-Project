/**
 * HealingItem Enumerated Type for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public enum HealingItem {
	DOUBLE_BROWN(4.99, 30.0, 25,
			"A crate bottle of Double Brown, 745 millilitres of slightly fizzy cow's wee",
			"flicks the top off the bottle with a cigarette lighter and drains the bottle. \"Gawd that's horrid\""), 
	LION_RED(9.99, 15.0, 25,
			"A foaming pint of Lion Red, lovingly described as NZ's most generic beer",
			"grins and skulls the pint. \"Huh. Surprisingly good\""), 
	LINDAUER(14.99, 30.0, 50,
			"A bottle of Lindauer Methode Traditionale, the chosen tipple of drunken racegoers throughout NZ",
			"delicately pours a glass, and takes a sip. \"Dahling, it's simply mah-vellous\"");

	private double cost;
	private double applyTime; //In seconds
	private int healthValue;
	private String longDescription;
	private String response;
	
	/*
	 * Healing item constructor
	 */
	HealingItem(double costArg, double timeArg, int healthArg, String longDescriptionArg, String responseArg) {
		this.cost = costArg;
		this.applyTime = timeArg;
		this.healthValue = healthArg;
		this.longDescription = longDescriptionArg;
		this.response = responseArg;
		
	}
	/**
	 * Getter method for cost of healing item
	 * @return a double the cost of the healing item
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * Getter method for time taken to apply healing item (in seconds)
	 * @return a double the time in seconds taken to apply the healing item
	 */
	public double getApplyTime() {
		return applyTime;
	}
	
	/**
	 * Getter method for the health effect of a healing item
	 * @return an int the amount added to a hero's health by a healing item
	 */
	public int getHealthValue() {
		return healthValue;
	}
	/**
	 * Getter method for the long description of a healing item
	 * @return a String the long description of a healing item
	 */
	public String getLongdescription() {
		return longDescription;
	}
		
	/**
	 * Getter method for the hero's response to a healing item
	 * @return a String the hero's response upon receiving the healing item
	 */
	public String getResponse() {
		return response;
	}
	
	@Override
	public String toString() {
		switch(this) {
		case DOUBLE_BROWN: return "A crate bottle of Double Brown";
		case LION_RED: return "A pint of Lion Red";
		case LINDAUER: return "A bottle of Lindauer Methode Traditionale";
		default: throw new IllegalArgumentException(); //default case mandatory
		}
	}
	/*
	 * Test Code Only
	 */
	public static void main(String[] args) {
		HealingItem h1 = HealingItem.DOUBLE_BROWN;
		HealingItem h2 = HealingItem.LION_RED;
		HealingItem h3 = HealingItem.LINDAUER;
		System.out.println(h1 + " which costs $" + h1.getCost());
		System.out.println(h2.getLongdescription() + " " + h2.getResponse());
	}

}
