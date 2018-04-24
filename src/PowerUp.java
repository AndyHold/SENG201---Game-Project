/**
 * PowerUp Enumerated Type for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public enum PowerUp {
	PAVLOVA(4.99, "A slice of pavlova topped with cream and kiwifruit", 
			"gives a hero the lightning quick speed to see the opponent's" +
			" move in rock, paper, scissors and play the appropriate move",
			"smiles and bites into the totally non-Australian glory of a slice of pav. " +
			"\"I can feel my reaction time improving - watch me throw this slice of " +
			"kiwifruit up and catch it in my mouth!\""),
	CHEESE_ROLL(7.49, "A perfectly cooked cheese roll - a little piece of Southland", 
			"gives a hero the ability to read an opponent's mind, " +
			"curiously limited only to what number they are thinking of", 
			"frowns and suspiciously views the cheese roll from all angles, then reluctantly" +
			" begins to eat. \"Hey, this isn't bad!\""),
	PINEAPPLE_LUMPS(5.99, "A packet of pineapple lumps, New Zealand's premier indigenous confection", 
			"gives a hero the amazing ability to roll sixes",
			"tears the packet open and begins wolfing them down. \"Nom Nom Nom!\"");
	
	private double cost;
	private String longDescription;
	private String effect;
	private String response;
	
	/*
	 * PowerUp Constructor
	 */
	PowerUp(double costArg, String longDescriptionArg, String effectArg, String responseArg) {
		this.cost = costArg;
		this.longDescription = longDescriptionArg;
		this.effect = effectArg;
		this.response = responseArg;
	}
	
	/**
	 * Getter method for cost of power up
	 * @return a double the cost of the power up
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * Getter method for the long description of a power up
	 * @return a String the long description of a power up
	 */
	public String getLongdescription() {
		return longDescription;
	}
	
	/**
	 * Getter method for the effect String of a power up
	 * @return a String the purported effect of the power up
	 */
	public String getEffect() {
		return effect;
	}
	
	/**
	 * Getter method for the hero's response to a power up
	 * @return a String the hero's response upon receiving the power up
	 */
	public String getResponse() {
		return response;
	}
	
	@Override
	public String toString() {
		switch(this) {
		case PAVLOVA: return "A slice of pavlova";
		case CHEESE_ROLL: return "A cheese roll";
		case PINEAPPLE_LUMPS: return "A pack of pineapple lumps";
		default: throw new IllegalArgumentException(); //default case mandatory
		}
	}
	
	/*
	 * Test Code Only
	 */
	public static void main(String[] args) {
	PowerUp p1 = PowerUp.PAVLOVA;
	PowerUp p2 = PowerUp.CHEESE_ROLL;
	PowerUp p3 = PowerUp.PINEAPPLE_LUMPS;
	System.out.println(p1 + " which costs $" + p1.getCost());
	System.out.println(p2.getLongdescription() + " " + p2.getEffect());

	}
}
