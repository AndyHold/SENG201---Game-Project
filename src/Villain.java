/**
 * Villain Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class Villain {
	
	private String name;
	private int currentHealth;
	private boolean alive = true;
	
	
	
	/**
	 * Constructor for Villain Super Class
	 * @param newName name of the Villain
	 * @param currentHealth health of the Villain
	 */
	public Villain(String newName) {
		name = newName;
		currentHealth = 100;
	}
	
	
	/**
	 * Name of the villain
	 * @return String name of the Villain
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * Takes 25 damage and updates boolean alive if Villain is defeated
	 * @return String representation of the damage done and notifies when villain is defeated.
	 */
	public String takeDamage() {
		currentHealth -= 25;
		if(currentHealth <= 0) {
			alive = false;
			return name + " has taken 25 damage \n" + name + " has been defeated!";
		} else {
			return name + " has taken 25 damage";
		}
	}
	
	
	/**
	 * current health of the villain
	 * @return int current health of the villain
	 */
	public int getHealth() {
		return currentHealth;
	}
	
	
	/**
	 * Weather or not the Villain is alive
	 * @return boolean alive which indicates weather or not the villain is alive.
	 */
	public boolean isAlive() {
		return alive;
	}
	
	
	/**
	 * Test code only
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Villain baddie = new Villain();
//		baddie.getTaunt();
//		System.out.println(barister);
//		System.out.println(barister);
//		System.out.println(barister);
//		System.out.println(barister);
//		System.out.println(barister);
	
	

}}