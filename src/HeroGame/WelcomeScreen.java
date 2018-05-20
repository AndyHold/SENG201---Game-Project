package HeroGame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

/**
 * WelcomeScreen Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class WelcomeScreen {
	
	//Variables from spec

	private static final int MAX_NAME_LENGTH = 10;
	private static final int MIN_NAME_LENGTH = 2;
	private static final String NUMCITIES[] = {"3", "4", "5", "6"};
	private static final String TEAMSIZE[] = {"1", "2", "3"};
	
	private JFrame frmNzCleanUp;
	private GameManager manager;
	private JTextField teamNameField = new JTextField();
	private JComboBox<String> nCitiesBox = new JComboBox(NUMCITIES);
	private JComboBox<String> nHeroesBox = new JComboBox(TEAMSIZE);
	private JLabel warning = new JLabel("");

	/**
	 * Constructor for new WelcomeScreen
	 * @param incomingManager a GameManager the state manager for the game
	 */
	public WelcomeScreen(GameManager incomingManager) {
		this.manager = incomingManager;
		initialize();
		frmNzCleanUp.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNzCleanUp = new JFrame();
		frmNzCleanUp.setBackground(Color.LIGHT_GRAY);
		frmNzCleanUp.getContentPane().setBackground(Color.WHITE);
		frmNzCleanUp.setTitle("NZ Clean Up - Welcome");
		frmNzCleanUp.setBounds(100, 100, 550, 370);
		frmNzCleanUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNzCleanUp.getContentPane().setLayout(null);
		
		JTextPane txtpnWelcomeToNz = new JTextPane();
		txtpnWelcomeToNz.setBackground(Color.WHITE);
		txtpnWelcomeToNz.setText("Welcome to NZ Clean Up, in which a ballsy band of kiwi legends will attempt to eliminate some of society's little problems.");
		txtpnWelcomeToNz.setBounds(12, 43, 524, 36);
		frmNzCleanUp.getContentPane().add(txtpnWelcomeToNz);
		txtpnWelcomeToNz.setEditable(false);
		
		JLabel lblNzCleanUp = new JLabel("NZ Clean Up");
		lblNzCleanUp.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNzCleanUp.setBounds(213, 12, 115, 20);
		frmNzCleanUp.getContentPane().add(lblNzCleanUp);
		
		JLabel lblFirstChoseA = new JLabel("First, chose a team name (2-10 characters):");
		lblFirstChoseA.setBounds(12, 91, 316, 15);
		frmNzCleanUp.getContentPane().add(lblFirstChoseA);
		
		/**
		 * Run teamname entry field if return is pressed
		 */
		teamNameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent nameArg) {
				if (teamNameField.getText().length() >= MIN_NAME_LENGTH &&
						teamNameField.getText().length() <= MAX_NAME_LENGTH) {
					manager.setTeamName(teamNameField.getText());
					
				} else {
					teamNameField.setText("2-10 characters please");
				}
			}
		});
		teamNameField.setBounds(326, 91, 167, 19);
		frmNzCleanUp.getContentPane().add(teamNameField);
		teamNameField.setColumns(10);
		
		JLabel lblNextChoseThe = new JLabel("Now chose the number of towns your heroes will sort out (3-6):");
		lblNextChoseThe.setBounds(12, 130, 458, 15);
		frmNzCleanUp.getContentPane().add(lblNextChoseThe);
		
		nCitiesBox.setMaximumRowCount(4);
		nCitiesBox.setBounds(484, 125, 36, 24);
		frmNzCleanUp.getContentPane().add(nCitiesBox);
		
		JLabel lblNowChooseHow = new JLabel("Now choose how many heroes you want on your team (1-3):");
		lblNowChooseHow.setBounds(12, 168, 441, 15);
		frmNzCleanUp.getContentPane().add(lblNowChooseHow);
				
		nHeroesBox.setMaximumRowCount(3);
		nHeroesBox.setBounds(484, 163, 36, 24);
		frmNzCleanUp.getContentPane().add(nHeroesBox);
		

		JButton btnChooseYourHeroes = new JButton("Choose your heroes");
		
		/**
		 *Button to close screen and move to team build screen. If team name input is valid,
		 *sets team name to current current value. Otherwise brings up warning. Takes current 
		 *position of nHeroes and nCities comboboxes and sets nCities and nHeroes accordingly. 
		 */
		btnChooseYourHeroes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent buttonArg) {
				if (teamNameField.getText().length() >= MIN_NAME_LENGTH &&
						teamNameField.getText().length() <= MAX_NAME_LENGTH) {
					manager.setTeamName(teamNameField.getText());
				} else {
					teamNameField.setText("2-10 characters please");
				}
				
				manager.setNCities(Integer.parseInt(nCitiesBox.getSelectedItem().toString()));
				manager.setNHeroes(Integer.parseInt(nHeroesBox.getSelectedItem().toString()));
				
				
				if (manager.getTeamName() != null) {
					finishedWelcomeScreen();
				} else {
					warning.setText("Please enter a suitable team name");
				}
				
			}
		});
		btnChooseYourHeroes.setBounds(326, 262, 194, 25);
		frmNzCleanUp.getContentPane().add(btnChooseYourHeroes);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(WelcomeScreen.class.getResource("/HeroGame/Images/new-zealander-flag-graphic.png")));
		lblNewLabel.setBounds(12, 184, 252, 151);
		frmNzCleanUp.getContentPane().add(lblNewLabel);
		warning.setForeground(Color.RED);
		warning.setFont(new Font("Dialog", Font.ITALIC, 10));
		
		warning.setBounds(336, 298, 200, 15);
		frmNzCleanUp.getContentPane().add(warning);

	}
	
	/**
	 * Closes the screen when no longer required
	 */
	public void closeScreen() {
		frmNzCleanUp.dispose();
	}
	
	/**
	 * Remotely callable method to close the screen and return to the state manager
	 */
	public void finishedWelcomeScreen() {
		manager.closeWelcomeScreen(this);
	}
}
