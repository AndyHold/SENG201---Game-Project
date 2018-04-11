import java.util.ArrayList;
import java.util.Random;


/**
 * Villain Enum for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public enum Villain {
	
	
	POLITICIAN("Peter the Politician", 
			"The people want what I say they want!", 
			"Let's mine the national parks for oil!", 
			"Election promises are for losers!"), 
	LAWYER("Lionel the Lawyer", 
			"I object to your presence!", 
			"I'm going to take you for every penny you have!", 
			"You're contractually obliged to lose!"), 
	BOUNCER("Dan the Doorman", 
			"Sorry cuz, you've had a bit much to come in tonight!", 
			"This isn't your I.D.!", 
			"You look like you need a few waters!"), 
	CALLCENTREOPERATOR("Carol the Call Centre Operator", 
			"I'll put you on hold for the rest of your life!", 
			"Your call is not important to us!", 
			"Let me put you through to our complaints department, Oh wait we don't have one!"), 
	BADRUGBYREFEREE("Barry the Bad Referee", 
			"I'm going to penalize you off the paddock!", 
			"Do you even know the rules?!", 
			"Thats a red card for you buddy!");
	
	private String name;
	private int currentHealth;
	private boolean alive = true;
	private ArrayList<String> taunts = new ArrayList<String>();
	
	
	/**
	 * Constructor for Villain Super Class
	 * @param newName name of the Villain
	 * @param currentHealth health of the Villain
	 */
	private Villain(String newName, String taunt1, String taunt2, String taunt3) {
		name = newName;
		currentHealth = 100;
		setTaunts(taunt1, taunt2, taunt3);
	}
	
	
	/**
	 * Fills the arrayList taunts with three taunts specific to the Villain type
	 * @param taunts1 first taunt specific to the Villain type
	 * @param taunts2 second taunt specific to the Villain type
	 * @param taunts3 third taunt specific to the Villain type
	 */
	private void setTaunts(String taunt1, String taunt2, String taunt3){
		taunts.add(taunt1);
		taunts.add(taunt2);
		taunts.add(taunt3);
	}
	
	
	/**
	 * Randomly picks a taunt from the arrayList taunts
	 * @return a random String taunt from the arrayList taunts
	 */
	public String getTaunt() {
		Random rand = new Random();
		int n = rand.nextInt(3);
		return taunts.get(n);
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
	
	
	@Override
	public String toString() {
		return name + " says: \n" + getTaunt();
	}
	
	
	/**
	 * Test code only
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Villain baddie = new Villain.POLITICIAN;
//		baddie.getTaunt();
//		System.out.println(barister);
//		System.out.println(barister);
//		System.out.println(barister);
//		System.out.println(barister);
//		System.out.println(barister);
	
	

}}