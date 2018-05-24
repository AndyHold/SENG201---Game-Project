package cmdLineVersion;
/**
 * PowerUp subclass for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public class PowerUp extends PowerItem {

	private String effect;
	private PowerUpType powerUpType;
	
	/**
	 * PowerUp constructor
	 * @param powerUpType a PowerUpType the type of PowerUp to be constructed
	 */
	public PowerUp(PowerUpType powerUpType) {
		this.powerUpType = powerUpType;
		super.setCost(powerUpType.getCost());
		super.setDescription(powerUpType.getDescription());
		super.setLongDescription(powerUpType.getLongDescription());
		this.effect = powerUpType.getEffect();
		super.setResponse(powerUpType.getResponse());
		
	}
	
	
	/**
	 * Getter method for the effect that this PowerUp has
	 * @return a String, the effect that this PowerUp has when used
	 */
	public String getEffect() {
		return effect;
	}
	
	/**
	 * Getter method for the type of an individual PowerUp 
	 * @return a PowerUpType the type of this PowerUp 
	 */
	public PowerUpType getType() {
		return powerUpType;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} 
		
		if (!(obj instanceof PowerUp)) {
			return false;
		} 
		
		PowerUp p = (PowerUp) obj;
		
		if (p.powerUpType == this.powerUpType) {
			return true;
		} else {
			return false;
		}
	}
	
}
