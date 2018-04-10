import java.util.ArrayList;

/**
 * Villain Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */


public class Villain {
	
	private String name;
	private static final int MAX_HEALTH = 100;
	private int current_health;
	private boolean alive = true;
	
	public Villain(String newName) {
		name = newName;
		current_health = 100;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public String takeDamage() {
		current_health -= 25;
		if(current_health <= 0) {
			alive = false;
			return name + " has been defeated!";
		} else {
			return name + " has taken 25 damage";
		}
	}
	
	
	public int gethealth() {
		return current_health;
	}
	
	

}