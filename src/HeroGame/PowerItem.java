package HeroGame;

/**
 * PowerItem SuperClass for Heroes & Villains Game. 
 * SuperClass for HealingItem and PowerUp
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */


public class PowerItem {
	private double cost;
	private String description;
	private String longDescription;
	private String response;
	private int amount = 1;
	
	/**
	 * Getter method for cost of power item
	 * @return a double, the cost of the power item
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Setter method for cost of power item
	 * @param cost a double the cost of the power item
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	/**
	 * Setter method for description of power item
	 * param a String the description of the power item
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Getter method for long description of power item
	 * @return a String, the extended description of the power item
	 */
	public String getLongDescription() {
		return longDescription;
	}
	
	/**
	 * Setter method for long description of power item
	 * param a String the extended description of the power item
	 */
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	
	/**
	 * Getter method for response to power item
	 * @return a String, the hero's reponse to eating the power item
	 */
	public String getResponse() {
		return response;
	}
	
	/**
	 * Setter method for response to power item
	 * @param reponse a String, the hero's response to eating the power item
	 */
	public void setResponse(String response) {
		this.response = response;
	}
	
	/**
	 * Setter method for the number of items of this type currently in the team inventory
	 * @param amount an int the amount of items of this type in the teams inventory
	 */
	public void changeAmount(int amount) {
		this.amount += amount;
	}
	
	/**
	 * Getter method for the amount of this item in the teams inventory
	 */
	public int getAmount() {
		return this.amount;
	}

	/**
	 * Getter method for healing item type
	 * @return a HealingItemType the type of HealingItem
	 */
	
	@Override
	public String toString() {
		return this.description;
	}

}
