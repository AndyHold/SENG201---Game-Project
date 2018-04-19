import java.util.ArrayList;
import java.util.Random;


/**
 * Villains Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class Villains {
	
	
	private static ArrayList<Villain> availableVillains = new ArrayList<Villain>();
	
	
	/**
	 * Constructor for Villains Super Class
	 * @param availableVillains ArrayList<Villain> list of all available villains
	 */
	private Villains() {
		availableVillains.add(Villain.AUSSIECRICKETER);
		availableVillains.add(Villain.BADRUGBYREFEREE);
		availableVillains.add(Villain.BOUNCER);
		availableVillains.add(Villain.CALLCENTREOPERATOR);
		availableVillains.add(Villain.LAWYER);
		availableVillains.add(Villain.POLITICIAN);
	}
	
	
	/**
	 * Gets the number of Villains currently not in use.
	 * @return int number of Villains not in use. 
	 */
	public static int getNumberOfVillains() {
		return availableVillains.size();
	}
	
	
	/**
	 * Chooses a random villain for use and removes it from availableVillains
	 * @return Villain a random villain picked from availableVillains.
	 */
	public static Villain getVillain() {
		Random rand = new Random();
		int n = rand.nextInt(getNumberOfVillains());
		Villain currentVillain = availableVillains.get(n);
		availableVillains.remove(n);
		return currentVillain;
	}
	
	
	@Override
	public String toString() {
		String result = "The available Villains are: \n";
		for(Villain badguy: availableVillains) {
			result += (badguy + "\n");
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Villains baddies = new Villains();
//		System.out.println(baddies);
//		Villain baddie1 = Villains.getVillain();
//		System.out.println(baddie1);
//		Villain baddie2 = Villains.getVillain();
//		System.out.println(baddie2);
//		Villain baddie3 = Villains.getVillain();
//		System.out.println(baddie3);
//		Villain baddie4 = Villains.getVillain();
//		System.out.println(baddie4);
//		Villain baddie5 = Villains.getVillain();
//		System.out.println(baddie5);
		// GET AN ERROR WHEN TRYING TO PRINT AN EMPTY VILLAINS!!!
	}

}
