import java.util.ArrayList;
import java.util.Random;

/**
 * Politician Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

public class Politician extends Villain{
	

	private ArrayList<String> taunts = new ArrayList<String>();
	
	public Politician() {
		super("Peter the Politician");
		setTaunts();
	}
	
	private void setTaunts(){
		taunts.add("The people want what i say they want!");
		taunts.add("Let's mine the national parks for oil!");
		taunts.add("I'm going to raise taxes so i can buy myself a holiday home");
	}
	
	
	private String getTaunt() {
		Random rand = new Random();
		int n = rand.nextInt(3);
		return taunts.get(n);
	}
	
	
	public String toString() {
		return super.getName() + " says: /n" + getTaunt();
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Politician minister = new Politician();
		minister.getTaunt();
		System.out.println(minister);
	}

}
