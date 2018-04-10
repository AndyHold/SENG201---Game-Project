import java.util.ArrayList;
import java.util.Random;


/**
 * Call Centre Operator Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */


public class CallCentreOperator extends Villain{
	

	private ArrayList<String> taunts = new ArrayList<String>();
	
	public CallCentreOperator() {
		super("Carol the Call Centre Operator");
		setTaunts();
	}
	
	private void setTaunts(){
		taunts.add("I'll put you on hold for the rest of your life!");
		taunts.add("Your call is not important to us!");
		taunts.add("Let me put you through to our complaints department, Oh wait we don't have one!");
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
		CallCentreOperator phone = new CallCentreOperator();
		phone.getTaunt();
		System.out.println(phone);
	}

}
