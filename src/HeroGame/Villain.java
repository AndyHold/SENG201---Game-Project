package HeroGame;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import java.util.HashMap;


/**
 * Villain Enum for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public enum Villain {
	
	AUSSIECRICKETER("Darin the Aussie Cricketer", //name
			"Crikey! A bit of sand paper will deadset sort you out ya drongo!", // taunts
			"Strewth! An under arm bowl from me and you'll be in the dunny but!",
			"Fair Dinkum! No chocky bikkies for you ya mongrel!", 
			0, // int associated with the villain (for referencing)
			200, // health
			200, // strength
			new ImageIcon(Villain.class.getResource("/HeroGame/Images/aussie_cricketer.jpg"))),

	BADRUGBYREFEREE("Barry the Bad Referee", 
			"I'm going to penalize you off the paddock!", 
			"Do you even know the rules?!", 
			"Thats a red card for you buddy!", 
			1,
			100, 
			100, 
			new ImageIcon(Villain.class.getResource("/HeroGame/Images/a_bad_referee.jpg"))),
	
	BOUNCER("Dan the Doorman", 
			"Sorry cuz, you've had a bit much to come in tonight!", 
			"This isn't your I.D.!", 
			"You look like you need a few waters!", 
			2,
			100, 
			100, 
			new ImageIcon(Villain.class.getResource("/HeroGame/Images/bouncer.jpg"))), 
	
	CALLCENTREOPERATOR("Carol the Call Centre Operator", 
			"I'll put you on hold for the rest of your life!", 
			"Your call is not important to us!", 
			"Let me put you through to our complaints department, Oh wait we don't have one!", 
			3,
			100, 
			100, 
			new ImageIcon(Villain.class.getResource("/HeroGame/Images/call-center-girl.jpg"))),
	
	LAWYER("Lionel the Lawyer", 
			"I object to your presence!", 
			"I'm going to take you for every penny you have!", 
			"You're contractually obliged to lose!",
			4,
			100, 
			100, 
			new ImageIcon(Villain.class.getResource("/HeroGame/Images/lawyer.jpg"))), 
	
	POLITICIAN("Peter the Politician", 
			"The people want what I say they want!", 
			"Let's mine the national parks for oil!", 
			"Election promises are for losers!",
			5,
			100, 
			100, 
			new ImageIcon(Villain.class.getResource("/HeroGame/Images/politician.jpg"))); 

	
	private String name;
	private int currentHealth;
	private int currentStrength;
	private boolean alive = true;
	private ArrayList<String> taunts = new ArrayList<String>();
	private HashMap<String, String> lairNames = new HashMap<String, String>();
	private ImageIcon image;
	
	
	/**
	 * Constructor for Villain Class
	 * @param newName name of the Villain
	 * @param currentHealth health of the Villain
	 */
	private Villain(String newName, String taunt1, String taunt2, String taunt3, int typeInt, int newStrength, int health, ImageIcon newImage) {
		this.name = newName;
		this.currentHealth = health;
		this.currentStrength = newStrength;
		setTaunts(taunt1, taunt2, taunt3);
		setLairNames(typeInt);
		this.image = newImage;
	}
	
	
	/**
	 * Method to reset values, for use when starting a new game
	 * @param villain, this Villain
	 */
	public void resetValues(Villain villain) {
		if(villain == Villain.AUSSIECRICKETER) { // if final vallain reset to final villain values
			this.currentHealth = 200;
			this.alive = true;
			
		} else { // else reset to regular villain values
			this.currentHealth = 100;
			this.alive = true;
		}
	}
	
	/**
	 * Returns the image associated with the Villain
	 * @return an ImageIcon the image associated with the Villain
	 */
	public ImageIcon getImage() {
		return this.image;
	}
	
	
	/**
	 * Method to change Strength of Villain
	 * @param newStrength an int the amount the villain's strength is changed to
	 */
	public void changeStrength(int newStrength) {
		this.currentStrength = newStrength;
	}
	
	
	/**
	 * Method to get current strength of Villain
	 * @Return int, current strength of Villain
	 */
	public int getStrength() {
		return this.currentStrength;
	}
	
	
	/**
	 * Method to set lair names for type of Villain selected and places them in a dictionary with city names as keys
	 * so that Villains are matched with appropriate lairs.
	 * Int selectors correspond as follows:
	 * 0 = Aussie Cricketer
	 * 1 = Bad Rugby Referee
	 * 2 = Bouncer
	 * 3 = Call Centre operator
	 * 4 = Lawyer
	 * 5 = Politician
	 */
	private void setLairNames(int typeInt) {
		switch (typeInt) { // puts lairnames in a hash map with the city name as key and the lairname as value depending on the type of villain

		case 0:
			lairNames.put("Springfield", "Hagley Oval");
			lairNames.put("Te Puke", "Bay Oval");
			lairNames.put("Gore", "The Grove Park");
			lairNames.put("Ohakune", "Ohakune Domain");
			lairNames.put("Paeroa", "Centennial Park");
			lairNames.put("Taihape", "Memorial Park, Taihape");
			break;

		case 1:
			lairNames.put("Springfield", "Selwyn Rugby Club");
			lairNames.put("Te Puke", "Centenial Park, Te Puke");
			lairNames.put("Gore", "Newman Park, Gore");
			lairNames.put("Ohakune", "Rochfort Park, Ohakune");
			lairNames.put("Paeroa", "Paeroa Old Boys Football Ground");
			lairNames.put("Taihape", "Memorial Park, Taihape");
			break;

		case 2:
			lairNames.put("Springfield", "The Springfield Hotel");
			lairNames.put("Te Puke", "Molly O'Connors Pub");
			lairNames.put("Gore", "Howl at the Moon, Cafe and Bar");
			lairNames.put("Ohakune", "Powderkeg Restaurant and Bar");
			lairNames.put("Paeroa", "The Paeroa Hotel");
			lairNames.put("Taihape", "Gumboot Manor Restaurant and Bar");
			break;

		case 3:
			lairNames.put("Springfield", "Springfield Telephone Exchange");
			lairNames.put("Te Puke", "Te Puke Telephone Exchange");
			lairNames.put("Gore", "Gore Telephone Exchange");
			lairNames.put("Ohakune", "Ohakune Telephone Exchange");
			lairNames.put("Paeroa", "Paeroa Telephone Exchange");
			lairNames.put("Taihape", "Taihape Telephone Exchange");
			break;

		case 4:
			lairNames.put("Springfield", "Christchurch District Court Chambers");
			lairNames.put("Te Puke", "Te Awamutu District Court Chambers");
			lairNames.put("Gore", "Gore District Court");
			lairNames.put("Ohakune", "Ohakune District Court");
			lairNames.put("Paeroa", "Hauraki District Court");
			lairNames.put("Taihape", "Taihape District Court");
			break;

		case 5:
			lairNames.put("Springfield", "Springfield Giant Donut Statue");
			lairNames.put("Te Puke", "Te Puke Giant Kiwifruit Statue");
			lairNames.put("Gore", "Gore Giant Trout Statue");
			lairNames.put("Ohakune", "Ohakune Giant Carrot Statue");
			lairNames.put("Paeroa", "Paeroa L&P Statue");
			lairNames.put("Taihape", "Taihape Giant Gumboot Statue");
			break;
		}	
	}
	
	
	/**
	 * Method to get the lair name from the dictionary and returns it.
	 * @return String lair name for given City and Villain type.
	 */
	public String getLairName(String cityName) {
		return lairNames.get(cityName);
	}
	
	
	/**
	 * Method to fill the arrayList taunts with three taunts specific to the Villain type
	 * @param taunts1 String, First taunt specific to the Villain type
	 * @param taunts2 String, Second taunt specific to the Villain type
	 * @param taunts3 String, Third taunt specific to the Villain type
	 */
	private void setTaunts(String taunt1, String taunt2, String taunt3){
		taunts.add(taunt1); // adds taunts to the arraylist
		taunts.add(taunt2);
		taunts.add(taunt3);
	}
	
	
	/**
	 * Method to get the name of the Villain
	 * @return String, Name of the Villain
	 */
	public String getName() {
		return this.name;
	}
	
	
	/**
	 * Method to randomly pick a taunt from the arrayList taunts
	 * @return String, A random taunt from the arrayList taunts
	 */
	public String getTaunt() {
		Random rand = new Random();
		int n = rand.nextInt(3); // creates a random int
		return taunts.get(n); // returns the taunt at that index
	}
	
	
	/**
	 * Takes damage and updates boolean alive if Villain is defeated
	 * @return String representation of the damage done and notifies when villain is defeated.
	 */
	public String takeDamage(int amount) {
		if(amount > currentHealth) { // if damage amount is greater than the current health set the damage amount to the current health
			amount = currentHealth;
		}
		currentHealth -= amount; // take the damage
		if(currentHealth == 0) { // if villain is killed set to dead and return a string
			alive = false;
			currentHealth = 0;
			return name + " has taken " + amount + " damage \n" + name + " has been defeated!";
		} else {
			return name + " has taken " + amount + " damage";
		}
	}
	
	
	/**
	 * Method to get the current health of the villain
	 * @return int, Current health of the villain
	 */
	public int getHealth() {
		return currentHealth;
	}
	
	
	/**
	 * Method to get whether or not the Villain is alive
	 * @return boolean, true if the villain is alive.
	 */
	public boolean isAlive() {
		return alive;
	}
	
	
	@Override
	public String toString() {
		return name + " says: \n" + getTaunt();
	}
}