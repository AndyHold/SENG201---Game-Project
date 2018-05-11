package HeroGame;
/**
 * Battle Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */

import java.util.Random;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class BattleScreen {

	private JFrame frmBattleTheVillain;
	private Hero player;
	private Villain baddie;
	private Team team;
	private Random rnd = new Random();
	//Game display elements	
	JLabel lblHealth = new JLabel();
	private JLabel lblVillainHealth = new JLabel();
	private JPanel gamePanel = new JPanel();
	private JTextArea txtrHint = new JTextArea();
	private JButton btnPlay = new JButton("");
	private JLabel lblVillainChoice = new JLabel();
	private JLabel lblOutcome = new JLabel();
	private String[] gameTypes = {"PaperScissorsRock", "GuessNumber", "RollDice"};
	//Specific to RPS game
	private String outcomes[] = {"draw", "win", "lose"};
	private String playerChoiceString;
	private int villainChoice;
	private String rPSChoices[] = {"Rock", "Paper", "Scissors"}; 
	private JComboBox rpsComBox = new JComboBox(rPSChoices);
	//Specific to Guess Number Game
	private String guessNumChoices[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
	private JComboBox numComBox = new JComboBox(guessNumChoices);
	private int attempts;
	private int playerNumGuess;
	private String numResult = null;
	


	/**
	 * Create the application.
	 */
	public BattleScreen(Hero player, Villain baddie, Team team) {
		this.player = player;
		this.baddie = baddie;
		this.team = team;
		initialize(randomGame());
		frmBattleTheVillain.setVisible(true);
		

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String gameType) {
		frmBattleTheVillain = new JFrame();
		frmBattleTheVillain.getContentPane().setBackground(Color.WHITE);
		frmBattleTheVillain.setTitle("Battle the Villain");
		frmBattleTheVillain.setBounds(100, 100, 642, 488);
		frmBattleTheVillain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBattleTheVillain.getContentPane().setLayout(null);
		
		JLabel lblYouAreIn = new JLabel(player.getName() + " has entered the lair of " + baddie.getName() );
		lblYouAreIn.setFont(new Font("Dialog", Font.BOLD, 14));
		lblYouAreIn.setBounds(12, 12, 604, 15);
		frmBattleTheVillain.getContentPane().add(lblYouAreIn);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 39, 286, 104);
		frmBattleTheVillain.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Hero: " + player.getName());
		lblNewLabel_1.setBounds(12, 0, 259, 24);
		panel.add(lblNewLabel_1);
		
		JLabel lblType = new JLabel("Type: " + player.getType().getDescription());
		lblType.setBounds(12, 28, 221, 15);
		panel.add(lblType);
		
		JLabel lblStrength = new JLabel("Strength: " + player.getStrength());
		lblStrength.setBounds(12, 51, 150, 15);
		panel.add(lblStrength);
		
		lblHealth.setText("Health: " + player.getHealth());
		lblHealth.setBounds(12, 78, 150, 15);
		panel.add(lblHealth);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(330, 39, 286, 105);
		frmBattleTheVillain.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		
		JLabel lblNewLabel_2 = new JLabel("Villain: " + baddie.getName() );
		lblNewLabel_2.setBounds(12, 0, 259, 24);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Strength: " + baddie.getStrength());
		lblNewLabel_3.setBounds(12, 51, 127, 15);
		panel_1.add(lblNewLabel_3);
		
		lblVillainHealth.setText("Health: " +  baddie.getHealth());
		lblVillainHealth.setBounds(12, 78, 150, 15);
		panel_1.add(lblVillainHealth);
		
		JButton btnPlayAgain = new JButton("Play Again");
		btnPlayAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGame(player, baddie, team);
			}
		});
		btnPlayAgain.setBounds(515, 388, 112, 25);
		frmBattleTheVillain.getContentPane().add(btnPlayAgain);
		
		JButton btnLeave = new JButton("Leave");
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLeave.setBounds(515, 425, 112, 25);
		frmBattleTheVillain.getContentPane().add(btnLeave);
		
		
		JPanel gamePanel = new JPanel();
		gamePanel.setBounds(12, 174, 491, 276);
		frmBattleTheVillain.getContentPane().add(gamePanel);
		gamePanel.setLayout(null);
		
		JTextArea txtrVillainTaunt = new JTextArea(baddie.getTaunt());
		txtrVillainTaunt.setLineWrap(true);
		txtrVillainTaunt.setFont(new Font("Dialog", Font.BOLD, 12));
		txtrVillainTaunt.setBackground(new Color(238, 238, 238));
		txtrVillainTaunt.setWrapStyleWord(true);
		txtrVillainTaunt.setBounds(12, 10, 467, 35);
		gamePanel.add(txtrVillainTaunt);
		
		JTextArea txtrChallenge = new JTextArea();
		txtrChallenge.setLineWrap(true);
		txtrChallenge.setFont(new Font("Dialog", Font.BOLD, 12));
		txtrChallenge.setBackground(new Color(238, 238, 238));
		txtrChallenge.setWrapStyleWord(true);
		txtrChallenge.setBounds(12, 53, 467, 35);
		gamePanel.add(txtrChallenge);
		
		txtrHint.setLineWrap(true);
		txtrHint.setFont(new Font("Dialog", Font.ITALIC, 10));
		txtrHint.setBackground(new Color(238, 238, 238));
		txtrHint.setWrapStyleWord(true);
		txtrHint.setBounds(12, 100, 467, 25);
		gamePanel.add(txtrHint);
				

		btnPlay.setBounds(163, 150, 117, 25);
		gamePanel.add(btnPlay);
		
		lblVillainChoice.setBounds(12, 208, 476, 15);
		gamePanel.add(lblVillainChoice);
		

		lblOutcome.setBounds(12, 249, 467, 15);
		gamePanel.add(lblOutcome);
		

		

		
		//*************************Branch Game Panel for 3 Different Games*********************
		if (gameType == "PaperScissorsRock") {
			
			villainChoice = rnd.nextInt(rPSChoices.length);
			txtrChallenge.setText("Now you have to play me at Paper Scissors Rock. Make your choice "
					+ "and let's see who has the stronger hand:");

			rpsComBox.setBounds(12, 150, 104, 24);
			gamePanel.add(rpsComBox);

			
			//*****Skew game if Pavlova PowerUp is present*********
			if (player.getPowerUp() != null) {
				
				if (player.getPowerUp().getType() == PowerUpType.PAVLOVA) {
					txtrHint.setText("The lightning quick reflexes brought on by the slice of pavlova " +
							player.getName() + " ate earlier enables them to see " + baddie.getName() + 
							" play " + rPSChoices[villainChoice]); 
					player.clearPowerUp();
				}
			}//*******************************************************
			
			btnPlay.setText("Play");
			btnPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					playerChoiceString = rpsComBox.getSelectedItem().toString();
					lblVillainChoice.setText(baddie.getName() + " plays " + rPSChoices[villainChoice]);
					int playerChoiceInt = 0;
					for (int i = 0; i < rPSChoices.length; i++) {
						if (rPSChoices[i] == playerChoiceString) {
							playerChoiceInt = i;
						}
					}
					paperScissorsRock(playerChoiceInt, villainChoice);
					btnPlay.setVisible(false);
					
				}
			});
			
			
		} else if (gameType == "GuessNumber") {
			txtrChallenge.setText("Now you have to guess the number I'm thinking of between 1 and 10. "
					+ "Make sure you choose carefully!");

			numComBox.setBounds(12, 150, 50, 24);
			gamePanel.add(numComBox);
			//txtrHint.setText("Guess num Hint");
			btnPlay.setText("Play");
			guessNumber();
			
			
			
		} else {//Dice game
			txtrChallenge.setText("Now you have to roll the dice with me. Highest number doesn't die!");
			btnPlay.setText("Roll Dice");
			btnPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diceRolls();
					btnPlay.setVisible(false);
				}
			});
			
		}
		
		
	}
	
	
	/**
	 * Close the window when required
	 */
	public void closeScreen() {
		frmBattleTheVillain.dispose();

	}
	
	/**
	 * Creates a new game when the 'Play Again' button is clicked
	 * @param hero a Hero the hero passed into the original constructor
	 * @param villain a Villain the villain passed into the original constructor
	 * @param team a Team the team passed into the original constructor
	 * @param gameType a String the type of game to be played
	 */
	public void newGame(Hero hero, Villain villain, Team team) {
		closeScreen();
		new BattleScreen(hero, villain, team);
		
	}
	
	/**
	 * Randomly allocate a game to play
	 * @return a String from the gameType list
	 */
	public String randomGame() {
		int gameTypeIndex = rnd.nextInt(gameTypes.length);
		return gameTypes[gameTypeIndex];
	}
	
	/**
	 * Rock paper scissors battle game decision mechanics
	 * As per tradition, rock beats scissors, paper beats rock, scissors beat paper
	 * No lizard or spock implemented at this stage
	 * Decides game outcome and triggers the consequences
	 * 0 - Rock, 1 - Paper, 2 - Scissors
	 * @param playerChoice an int the int representing the player's choice
	 * @param villainChoice an int the int representing the player's choice
	 */
	public void paperScissorsRock(int playerChoice, int villainChoice){
		String playerResult;
		if (playerChoice == villainChoice) {
			playerResult = outcomes[0]; //Draw
		
		} else if (playerChoice == (villainChoice + 1) //Player win paper > rock or scissors over paper
				|| playerChoice == (villainChoice - 2)) { //Player win rock > scissors
			playerResult = outcomes[1]; //Player win
			
		} else {
			playerResult = outcomes[2];
		}
		battleConsequence(playerResult);

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
	public void guessNumber(){
		int max_num = 10; //From game specification
		int max_attempts = 2; //From game specification
		attempts = 0;
		numResult = outcomes[2];

		villainChoice = (rnd.nextInt(max_num) + 1);
			
		//*****Skew game if Cheese Roll PowerUp is present*********
		if (player.getPowerUp() != null) {
			if (player.getPowerUp().getType() == PowerUpType.CHEESE_ROLL) {
				txtrHint.setText("The strange effects of the mystical cheese roll " +
						player.getName() + " ate earlier suddenly become strangely relevant. \"" 
						+ baddie.getName() + " is thinking of the number " + villainChoice +
						"\" thinks " + player.getName()); 
			player.clearPowerUp();
			}
		}//*******************************************************
		
		
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerNumGuess = (Integer.parseInt(numComBox.getSelectedItem().toString()));
				lblVillainChoice.setText("\"Higher\" says " + baddie.getName());
			}
		});
		
//		while (attempts < max_attempts && numResult != outcomes[1]) {
//			if (playerNumGuess == villainChoice) { //Player win
//				numResult = outcomes[1]; 
//			
//			} else if (playerNumGuess < villainChoice) { //Player choice too low
//				lblVillainChoice.setText("\"Higher\" says " + baddie.getName());
//				attempts++; 
//			
//			} else { //Player choice too high
//				lblVillainChoice.setText("\"Lower\" says " + baddie.getName());
//				attempts++;
//			}
//
//		}
//		btnPlay.setVisible(false);
//		battleConsequence(numResult);			
	}
	
	/**
	 * Dice rolls battle game. Player rolls a six sided dice by pressing Enter
	 * Then baddie rolls, and the game is decided on the highest roll
	 * @param player a Hero the player's chosen team member for the battle
	 * @param baddie a Villain the villain being played
	 * @return a String "draw", "win" or "lose"
	 */
	public void diceRolls() {
		int SMALLEST_ROLL = 1;
		int LARGEST_ROLL = 6;
		String playerResult= outcomes[0];
		int playerChoice = (rnd.nextInt(LARGEST_ROLL) + SMALLEST_ROLL);
		
		//*****Skew game if Pineapple Lumps PowerUp is present*********
		if (player.getPowerUp() != null) {
			if (player.getPowerUp().getType() == PowerUpType.PINEAPPLE_LUMPS) {
				txtrHint.setText("By the magic of Pineapple Lumps " +
						player.getName() + " plays a " + LARGEST_ROLL);
				playerChoice = LARGEST_ROLL;
				player.clearPowerUp();
			}
		}//*******************************************************
			
		villainChoice = (rnd.nextInt(LARGEST_ROLL) + SMALLEST_ROLL);
		lblVillainChoice.setText(player.getName() + " rolls a " + playerChoice + ". " + 
		baddie.getName() + " rolls a " + villainChoice + ".");
		
		if (playerChoice == villainChoice) {//Draw
			playerResult = outcomes[0];
		
		} else if (playerChoice > villainChoice) {//Player win
			playerResult = outcomes[1];
		} else {
			playerResult = outcomes[2];
		}
	
		this.battleConsequence(playerResult);
	
	}
	
	
	
	
	/**
	 *  
	 * @param result
	 */
	public void battleConsequence(String result) {
		int damage = 34; //Damage in a normal fight. 1/3 of normal strength
		if (result == "win") {
			damage = ((player.getStrength()/3) + 1);//Roughly 3 games to finish an opponent - per inconsistent game spec
			baddie.takeDamage(damage);
			lblVillainHealth.setText("Health: " +  baddie.getHealth());
		} else if (result == "lose") {
			damage = ((baddie.getStrength()/3) + 1);
			if (player.getType() == HeroType.RETURNED_SERVICEMAN) {//Returned serviceman takes half damage
				damage /= 2; 
			}
			player.changeHealth(-damage);
			lblHealth.setText("Health: " + player.getHealth());
			if (player.getHealth() <= 0) {
				System.out.println(player.getName() + " is dead.");
				team.removeMember(team.getIndex(player));
			}
		}
		lblOutcome.setText(player.getName() + " " + result + "s! Click Play Again to continue or leave to retreat.");
	}
	
	
	
	
	//*********************************Test Code************************************************
	public static void main(String[] args) {
		Hero h1 = new Hero("Jim", HeroType.RETURNED_SERVICEMAN);
		Villain v1 = Villain.AUSSIECRICKETER;
		Team t1 = new Team("Awesome");
		PowerUp p1 = new PowerUp(PowerUpType.PINEAPPLE_LUMPS);
		t1.addMember(h1);
		h1.eatPowerUp(p1);
		BattleScreen b1 = new BattleScreen(h1, v1, t1);
	}
}
