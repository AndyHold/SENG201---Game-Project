import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;


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
	private HashMap<String, String> lairNames = new HashMap<String, String>();
	
	
	/**
	 * Constructor for Villain Super Class
	 * @param newName name of the Villain
	 * @param currentHealth health of the Villain
	 */
	private Villain(String newName, String taunt1, String taunt2, String taunt3) {
		name = newName;
		currentHealth = 100;
		setTaunts(taunt1, taunt2, taunt3);
		setLairNames();
	}
	
	
	/**
	 * setsLairNames for type of Villain selected and places them in a dictionary with city names as keys.
	 */
	private void setLairNames() {
		switch (this) {
			case POLITICIAN:
				lairNames.put("Springfield", "Springfield Giant Donut Statue");
				lairNames.put("Te Puke", "Te Puke Giant Kiwifruit Statue");
				lairNames.put("Gore", "Gore Giant Trout Statue");
				lairNames.put("Ohakune", "Ohakune Giant Carrot Statue");
				lairNames.put("Paeroa", "Paeroa L&P Statue");
				lairNames.put("Taihape", "Taihape Giant Gumboot Statue");
				break;
				
			case LAWYER:
				lairNames.put("Springfield", "Christchurch District Court Chambers");
				lairNames.put("Te Puke", "Te Awamutu District Court Chambers");
				lairNames.put("Gore", "Gore District Court");
				lairNames.put("Ohakune", "Ohakune District Court");
				lairNames.put("Paeroa", "Hauraki District Court");
				lairNames.put("Taihape", "Taihape District Court");
				break;
				
			case BOUNCER:
				lairNames.put("Springfield", "The Springfield Hotel");
				lairNames.put("Te Puke", "Molly O'Connors Pub");
				lairNames.put("Gore", "Howl at the Moon, Cafe and Bar");
				lairNames.put("Ohakune", "Powderkeg Restaurant and Bar");
				lairNames.put("Paeroa", "The Paeroa Hotel");
				lairNames.put("Taihape", "Gumboot Manor Restaurant and Bar");
				break;
				
			case CALLCENTREOPERATOR:
				lairNames.put("Springfield", "Springfield Telephone Exchange");
				lairNames.put("Te Puke", "Te Puke Telephone Exchange");
				lairNames.put("Gore", "Gore Telephone Exchange");
				lairNames.put("Ohakune", "Ohakune Telephone Exchange");
				lairNames.put("Paeroa", "Paeroa Telephone Exchange");
				lairNames.put("Taihape", "Taihape Telephone Exchange");
				break;
				
			case BADRUGBYREFEREE:
				lairNames.put("Springfield", "Selwyn Rugby Club");
				lairNames.put("Te Puke", "Centenial Park, Te Puke");
				lairNames.put("Gore", "Newman Park, Gore");
				lairNames.put("Ohakune", "Rochfort Park, Ohakune");
				lairNames.put("Paeroa", "Paeroa Old Boys Football Ground");
				lairNames.put("Taihape", "Memorial Park, Taihape");
				break;
		}	
	}
	
	
	/**
	 * gets the lair name from the dictionary and returns it.
	 * @return String lair name for given City and Villain type.
	 */
	public String getLairName(String cityName) {
		return lairNames.get(cityName);
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
	 * gets the name of the Villain
	 * @return String name of the Villain
	 */
	public String getName() {
		return name;
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