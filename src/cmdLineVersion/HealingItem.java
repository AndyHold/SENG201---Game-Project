package cmdLineVersion;
/**
 * HealingItem Enumerated Type for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public class HealingItem extends PowerItem {
	
	private HealingItemType healingItemType;
	private double applyTime; //In seconds
	private int healthValue;
	
	/**
	 * HealingItem Constructor
	 * @param healingItemType the type of HealingItem to be constructed
	 */
	public HealingItem(HealingItemType healingItemType) {
		this.healingItemType = healingItemType;
		super.setCost(healingItemType.getCost());
		this.applyTime = healingItemType.getApplyTime();
		this.healthValue = healingItemType.getHealthValue();
		super.setDescription(healingItemType.getDescription());
		super.setLongDescription(healingItemType.getLongDescription());
		super.setResponse(healingItemType.getResponse());
	}
	

	public HealingItemType getHealingItemType() {
		return healingItemType;
	}
	
	/**
	 * Getter method for time taken to apply HealingItem (in seconds)
	 * @return a double the time in seconds taken to apply the HealingItem
	 */
	public double getApplyTime() {
		return applyTime;
	}
	
	/**
	 * Getter method for the health effect of a HealingItem
	 * @return an int the amount added to a Hero's health by a HealingItem
	 */
	public int getHealthValue() {
		return healthValue;
	}
	
	/**
	 * Setter method for the health effect of a HealingItem
	 * @param healthValueChange an int the amount by which the health value changes. Can be +/-
	 */
	public void changeHealthValue(int healthValueChange) {
		this.healthValue += healthValueChange;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} 
		
		if (!(obj instanceof HealingItem)) {
			return false;
		} 
		
		HealingItem h = (HealingItem) obj;
		
		if (h.healingItemType == this.healingItemType) {
			return true;
		} else {
			return false;
		}
	}


}
