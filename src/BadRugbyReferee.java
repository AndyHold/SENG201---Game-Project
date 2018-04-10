import java.util.ArrayList;
import java.util.Random;

public class BadRugbyReferee extends Villain{
	
	
	/**
	 * Bad Rugby Referee Class for Heroes & Villains Game
	 * SENG201 2018S1
	 * @author Andy Holden & Alex Liggett
	 */
	
	
	
	private ArrayList<String> taunts = new ArrayList<String>();
	
	public BadRugbyReferee() {
		super("Barry the Bad Referee");
		setTaunts();
	}
	
	private void setTaunts(){
		taunts.add("I'm going to penalize you off the paddock!");
		taunts.add("Do you even know the rules?!");
		taunts.add("Thats a red card for you buddy!");
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
		BadRugbyReferee ref = new BadRugbyReferee();
		ref.getTaunt();
		System.out.println(ref);
		
	}

}
