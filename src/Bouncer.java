import java.util.ArrayList;
import java.util.Random;


/**
 * Bouncer Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */


public class Bouncer extends Villain {
	

	
	private ArrayList<String> taunts = new ArrayList<String>();
	
	public Bouncer() {
		super("Dan the Doorman");
		setTaunts();
	}
	
	private void setTaunts(){
		taunts.add("Sorry cuz, you've had a bit much to come in tonight!");
		taunts.add("This isn't your I.D.!");
		taunts.add("You look like you need a few waters!");
	}
	
	
	private String getTaunt() {
		Random rand = new Random();
		int n = rand.nextInt(3);
		return taunts.get(n);
	}
	
	
	public String toString() {
		return super.getName() + " says: \n" + getTaunt();
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bouncer doorman = new Bouncer();
		doorman.getTaunt();
		System.out.println(doorman);
		System.out.println(doorman);
		System.out.println(doorman);
		System.out.println(doorman);
		System.out.println(doorman);
	}

}
