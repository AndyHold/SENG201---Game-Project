import java.util.ArrayList;
import java.util.Random;


/**
 * Lawyer Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class Lawyer extends Villain {
	
	private ArrayList<String> taunts = new ArrayList<String>();
	
	/**
	 * Constructor for Lawyer Class
	 */
	public Lawyer() {
		super("Lionel the Lawyer");
		setTaunts();
	}
	
	
	/**
	 * Fills the arrayList taunts with three taunts specific to Bouncer Villain type
	 */
	private void setTaunts(){
		taunts.add("I object to your presence!");
		taunts.add("I'm going to take every penny you have!");
		taunts.add("You're contractually obliged to lose!");
	}
	
	
	/**
	 * Randomly picks a taunt from the arrayList taunts
	 * @return a random String taunt from the arrayList taunts
	 */
	private String getTaunt() {
		Random rand = new Random();
		int n = rand.nextInt(3);
		return taunts.get(n);
	}
	
	@Override
	public String toString() {
		return super.getName() + " says: \n" + getTaunt();
	}
	
	
	
	/**
	 * Test code only
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lawyer barister = new Lawyer();
		System.out.println(barister.getTaunt());
		System.out.println(barister);
		System.out.println(barister.getName());
		System.out.println(barister.getHealth());
		System.out.println(barister.isAlive());
		System.out.println(barister.takeDamage());
		System.out.println(barister);
		System.out.println(barister.getHealth());
		System.out.println(barister.takeDamage());
		System.out.println(barister);
		System.out.println(barister.getHealth());
		System.out.println(barister.takeDamage());
		System.out.println(barister);
		System.out.println(barister.getHealth());
		System.out.println(barister.takeDamage());
		System.out.println(barister);
		System.out.println(barister.isAlive());
		System.out.println(barister.getHealth());
	}

}
