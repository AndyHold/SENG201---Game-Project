package HeroGame;
/**
 * HealingItem Enumerated Type for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public class HealingItem extends PowerItem {
	
	private HealingItemType healingItemType;
	private double applyTime; //In seconds
	private int healthValue;
	
	/*
	 * Healing item constructor
	 */
	HealingItem(HealingItemType healingItemType) {
		this.healingItemType = healingItemType;
		super.setCost(healingItemType.getCost());
		this.applyTime = healingItemType.getApplyTime();
		this.healthValue = healingItemType.getHealthValue();
		super.setDescription(healingItemType.getDescription());
		super.setLongDescription(healingItemType.getLongDescription());
		super.setResponse(healingItemType.getResponse());
	}

	/**
	 * Getter method for healing item type
	 * @return a Healing Item Type the type of healing item
	 */
	public HealingItemType getHealingItemType() {
		return healingItemType;
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


}
