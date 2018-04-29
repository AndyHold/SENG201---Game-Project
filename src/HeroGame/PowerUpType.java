package HeroGame;

/**
 * PowerUp Enumerated Type for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public enum PowerUpType {
	PAVLOVA(4.99, "Pavlova", "A slice of pavlova topped with cream and kiwifruit", 
			"gives a hero the lightning quick speed to see the opponent's" +
			" move in rock, paper, scissors and play the appropriate move",
			"smiles and bites into the totally non-Australian glory of a slice of pav. " +
			"\"I can feel my reaction time improving - watch me throw this slice of " +
			"kiwifruit up and catch it in my mouth!\""),
	CHEESE_ROLL(7.49, "Cheese Roll", "A perfectly cooked cheese roll - a little piece of Southland", 
			"gives a hero the ability to read an opponent's mind, " +
			"curiously limited only to what number they are thinking of", 
			"frowns and suspiciously views the cheese roll from all angles, then reluctantly" +
			" begins to eat. \"Hey, this isn't bad!\""),
	PINEAPPLE_LUMPS(5.99, "Pineapple Lumps", "A packet of pineapple lumps, New Zealand's premier indigenous confection", 
			"gives a hero the amazing ability to roll sixes",
			"tears the packet open and begins wolfing them down. \"Nom Nom Nom!\"");
	
	private double cost;
	private String description;
	private String longDescription;
	private String effect;
	private String response;
	
	/*
	 * PowerUp Constructor
	 */
	PowerUpType(double costArg, String descriptionArg, String longDescriptionArg, String effectArg, String responseArg) {
		this.cost = costArg;
		this.description = descriptionArg;
		this.longDescription = longDescriptionArg;
		this.effect = effectArg;
		this.response = responseArg;
	}
	
	/**
	 * Getter method for cost of this power up type
	 * @return a double the cost of this power up type
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * Getter method for description of this power up type 
	 * @return a String the description of this power up type
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Getter method for the long description of this power up type
	 * @return a String the long description of this power up type
	 */
	public String getLongdescription() {
		return longDescription;
	}
	
	/**
	 * Getter method for the effect String of this power up type
	 * @return a String the purported effect of this power up type
	 */
	public String getEffect() {
		return effect;
	}
	
	/**
	 * Getter method for the hero's response to this power up type
	 * @return a String the hero's response upon receiving this power up type
	 */
	public String getResponse() {
		return response;
	}
	
}