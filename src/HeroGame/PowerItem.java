package HeroGame;

/**
 * PowerItem SuperClass for Heroes & Villains Game
 * Superclass for HealingItem and PowerUp
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */


public class PowerItem {
	private double cost;
	private String description;
	private String longDescription;
	private String response;
	
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
	
	@Override
	public String toString() {
		return this.description;
	}

}
