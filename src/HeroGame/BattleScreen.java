package HeroGame;

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

public class BattleScreen {

	private JFrame frmBattleTheVillain;
	private Hero player;
	private Villain baddie;
	private Team team;
	private JPanel gamePanel = new JPanel();
	private String[] gameTypes = {"Paper Scissors Rock", "Guess my number", "Roll the Dice"};


	/**
	 * Create the application.
	 */
	public BattleScreen(Hero player, Villain baddie, Team team, String gameType) {
		this.player = player;
		this.baddie = baddie;
		this.team = team;
		initialize(gameType);
		frmBattleTheVillain.setVisible(true);
		

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String gameType) {
		frmBattleTheVillain = new JFrame();
		frmBattleTheVillain.getContentPane().setBackground(Color.WHITE);
		frmBattleTheVillain.setTitle("Battle the Villain");
		frmBattleTheVillain.setBounds(100, 100, 641, 419);
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
		
		JLabel lblHealth = new JLabel("Health: " + player.getHealth());
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
		
		JLabel lblNewLabel = new JLabel("Health: " +  baddie.getHealth());
		lblNewLabel.setBounds(12, 78, 150, 15);
		panel_1.add(lblNewLabel);
		
		JButton btnPlayAgain = new JButton("Play Again");
		btnPlayAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGame(player, baddie, team, "Roll Dice");
			}
		});
		btnPlayAgain.setBounds(515, 319, 112, 25);
		frmBattleTheVillain.getContentPane().add(btnPlayAgain);
		
		JButton btnLeave = new JButton("Leave");
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLeave.setBounds(515, 356, 112, 25);
		frmBattleTheVillain.getContentPane().add(btnLeave);
		
		
		JPanel gamePanel = new JPanel();
		gamePanel.setBounds(12, 174, 491, 207);
		frmBattleTheVillain.getContentPane().add(gamePanel);
		gamePanel.setLayout(null);
		
		JLabel lblVillainTaunt = new JLabel(baddie.getTaunt());
		lblVillainTaunt.setBounds(12, 12, 415, 15);
		gamePanel.add(lblVillainTaunt);
		
		JLabel lblChallenge = new JLabel("Now you must play me at " + gameType);
		lblChallenge.setBounds(12, 37, 415, 15);
		gamePanel.add(lblChallenge);
		
		JLabel lblHint = new JLabel("");
		lblHint.setFont(new Font("Dialog", Font.ITALIC, 10));
		lblHint.setBounds(12, 64, 364, 15);
		gamePanel.add(lblHint);
		
		JButton btnPlay = new JButton("");
		btnPlay.setBounds(162, 97, 117, 25);
		gamePanel.add(btnPlay);
		
		JLabel villainsChoice = new JLabel("Baddie's choice");
		villainsChoice.setBounds(12, 144, 291, 15);
		gamePanel.add(villainsChoice);
		
		JLabel lblOutcome = new JLabel("Outcome:");
		lblOutcome.setBounds(12, 180, 70, 15);
		gamePanel.add(lblOutcome);
		
		//*************************Branch Game Panel for 3 Different Games*********************
		if (gameType == "Paper Scissors Rock") {
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(12, 97, 104, 24);
			gamePanel.add(comboBox);
			lblHint.setText("PSR Hint");
			btnPlay.setText("Play");
			
		} else if (gameType == "GuessNumber") {
			JTextField numberEntry = new JTextField();
			numberEntry.setBounds(12, 97, 104, 24);
			gamePanel.add(numberEntry);
			lblHint.setText("Guess num Hint");
			btnPlay.setText("Play");
			
		} else {//Dice game
			btnPlay.setText("Roll Dice");
		}
		

		
	}
	

	
	public void closeScreen() {
		frmBattleTheVillain.dispose();

	}
	
	public void newGame(Hero hero, Villain villain, Team team, String gameType) {
		closeScreen();
		new BattleScreen(hero, villain, team, gameType);
		
	}
	
	public static void main(String[] args) {
		Hero h1 = new Hero("Jim", HeroType.RETURNED_SERVICEMAN);
		Villain v1 = Villain.AUSSIECRICKETER;
		Team t1 = new Team("Awesome");
		t1.addMember(h1);
		BattleScreen b1 = new BattleScreen(h1, v1, t1, "GuessNumber");
	}
}
