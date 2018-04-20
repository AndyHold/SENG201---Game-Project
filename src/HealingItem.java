/**
 * Selector Class for Heroes & Villains Game
 * Contains tools for command line interface
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public enum HealingItem {
	DOUBLE_BROWN(4.99, 30.0, 25), 
	SPEIGHTS(9.99, 15.0, 25), 
	EMERSONS_1812(14.99, 30.0, 50);

	private double cost;
	private double applyTime;
	private int strengthValue;
	
	HealingItem(double costArg, double timeArg, int strengthArg) {
		this.cost = costArg;
		this.applyTime = timeArg;
		this.strengthValue = strengthArg;
	}
	

}
