import java.util.Random;
/**
 * Battle Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class Battle {
	
	private Selector battleSelector = new Selector();
	private String outcomes[] = {"draw", "win", "lose"};
	String playerResult = outcomes[2]; //Default to player lose
	private Random rnd = new Random();
	int playerChoice;
	int villainChoice;
	
	/**
	 * Rock paper scissors battle game
	 * As per tradition, rock beats scissors, paper beats rock, scissors beat paper
	 * No lizard or spock implemented at this stage
	 * @param player a Hero the player's chosen team member for the battle
	 * @param baddie a Villain the villain being played
	 * @return a String "draw", "win" or "lose"
	 */
	public String paperScissorsRock(Team team, Hero player, Villain baddie){
		String choices[] = {"Rock", "Paper", "Scissors"}; 
		
		System.out.println(baddie + " Now you must play me at Paper Scissors Rock:");
		
		for (int i = 0; i < choices.length; i++) {
			System.out.print(i + ". " + choices[i] + "\n");
		}
		playerChoice = battleSelector.intSelector(0, choices.length, "Choose a move (0 - " + (choices.length - 1) + "):", 
				"Move must be 0 - " + (choices.length - 1) + "):");
		System.out.println(player.getName() + " plays " + choices[playerChoice]);
		villainChoice = rnd.nextInt(choices.length);
		System.out.println(baddie.getName() +  " plays " +  choices[villainChoice]);
		
		if (playerChoice == villainChoice) {
			playerResult = outcomes[0]; //Draw
		
		} else if (playerChoice == (villainChoice + 1) //Player win paper > rock or scissors over paper
				|| playerChoice == (villainChoice - 2)) { //Player win rock > scissors
			playerResult = outcomes[1]; //Player win
			
		} else {
			System.out.println(baddie.getTaunt());
		}
		
		this.battleConsequence(team, player, baddie, playerResult);
		return player.getName() + " " + playerResult + "s";
	}
	
	/**
	 * Guess a number battle game
	 * Player guesses a number between 1 & 10 (per game specification)
	 * Villain says "higher" or "lower" and options adjust accordingly. 
	 * Two attempts allowed (per game specification) 
	 * @param player a Hero the player's chosen team member for the battle
	 * @param baddie a Villain the villain being played
	 * @return a String "draw", "win" or "lose"
	 */
	public String guessNumber(Team team, Hero player, Villain baddie){
		int min_num = 1; //From game specification
		int max_num = 10; //From game specification
		int max_attempts = 2; //From game specification
		int attempts = 0;
		villainChoice = (rnd.nextInt(max_num) + 1);
		
		System.out.println(villainChoice); //****Obviously Test Code Must Remove **************
		System.out.println(baddie + " " +
				"Now go ahead and guess a number between " + min_num + 
				" and " + max_num);
		
		while (attempts < max_attempts && playerResult != outcomes[1]) {
			playerChoice = battleSelector.intSelector(min_num, max_num, 
					"Choose a move (" + min_num + " - " + max_num + "):", 
					"Move must be " + min_num + " - " + max_num);
			
			if (playerChoice == villainChoice) { //Player win
				playerResult = outcomes[1]; 
			
			} else if (playerChoice < villainChoice) { //Player choice too low
				System.out.println("\"Higher\" says " + baddie.getName());
				min_num = playerChoice;
				attempts++; 
			
			} else { //Player choice too high
				System.out.println("\"Lower\" says " + baddie.getName());
				max_num = playerChoice;
				attempts++;
			}
		}
		
		this.battleConsequence(team, player, baddie, playerResult);
		return player.getName() + " " + playerResult + "s";			
	}
	
	/**
	 * Dice rolls battle game. Player rolls a six sided dice by pressing Enter
	 * Then baddie rolls, and the game is decided on the highest roll
	 * @param player a Hero the player's chosen team member for the battle
	 * @param baddie a Villain the villain being played
	 * @return a String "draw", "win" or "lose"
	 */
	public String diceRolls(Team team, Hero player, Villain baddie) {
		int SMALLEST_ROLL = 1;
		int LARGEST_ROLL = 6;
		
		System.out.println(baddie + " Now you will roll the dice with me. You roll first, " +
				"highest roll wins!");
		System.out.println("Press Enter to roll the dice");
		
		battleSelector.returnDetect();
		
		playerChoice = (rnd.nextInt(LARGEST_ROLL) + SMALLEST_ROLL);
		System.out.println(player.getName() + " has rolled a " + playerChoice);
		
		villainChoice = (rnd.nextInt(LARGEST_ROLL) + SMALLEST_ROLL);
		System.out.println(baddie.getName() + " has rolled a " + villainChoice);
		
		if (playerChoice == villainChoice) {//Draw
			playerResult = outcomes[0];
		
		} else if (playerChoice > villainChoice) {//Player win
			playerResult = outcomes[1];
		} else {
			System.out.println(baddie.getTaunt());
		}
		
		System.out.println(player.getName() + " " + playerResult + "s");	
		this.battleConsequence(team, player, baddie, playerResult);
		return playerResult;	
	}
	
	
	/**
	 * 
	 * @param player
	 * @param baddie
	 * @param result
	 */
	public void battleConsequence(Team team, Hero player, Villain baddie, String result) {
		int damage = 34; //Damage in a normal fight. 1/3 of normal strength
		if (result == "win") {
			System.out.println(baddie.takeDamage(damage));
		} else if (result == "lose") {
			if (player == Hero.RETURNED_SERVICEMAN) {//Returned serviceman takes half damage
				damage /= 2; 
			}
			System.out.println(player.getName() + "'s health is now " + player.changeHealth(-damage));
			if (player.getHealth() <= 0) {
				System.out.println(player.getName() + " is dead.");
				team.removeMember(team.getIndex(player));
			}
		} 
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Battle b1 = new Battle();
		Team t1 = new Team("Awesome", 2);
		Hero h1 = Hero.ALL_BLACK;
		h1.setName("JimBob");
		Hero h2 = Hero.RETURNED_SERVICEMAN;
		h2.setName("Herbie");
		t1.addMember(h1);
		t1.addMember(h2);
		Villain v1 = Villain.AUSSIECRICKETER;
		//System.out.print(b1.guessNumber(h1, v1));
		//System.out.print(b1.paperScissorsRock(h1, v1));
		System.out.print(b1.diceRolls(t1, h1, v1));
		t1.teamStatus();

	}

}
