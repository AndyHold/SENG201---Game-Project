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
 * Welcome Screen Class for Heroes & Villains Game
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
	private JTextField teamNameField;
	private JComboBox<String> nCitiesBox = new JComboBox(NUMCITIES);
	private JComboBox<String> nHeroesBox = new JComboBox(TEAMSIZE);

	/**
	 * Create the application.
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
		frmNzCleanUp.setBounds(100, 100, 550, 362);
		frmNzCleanUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNzCleanUp.getContentPane().setLayout(null);
		
		JTextPane txtpnWelcomeToNz = new JTextPane();
		txtpnWelcomeToNz.setBackground(Color.WHITE);
		txtpnWelcomeToNz.setText("Welcome to NZ Clean Up, in which a ballsy band of kiwi legends will attempt to eliminate some of society's little problems.");
		txtpnWelcomeToNz.setBounds(12, 43, 524, 36);
		frmNzCleanUp.getContentPane().add(txtpnWelcomeToNz);
		
		JLabel lblNzCleanUp = new JLabel("NZ Clean Up");
		lblNzCleanUp.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNzCleanUp.setBounds(213, 12, 115, 20);
		frmNzCleanUp.getContentPane().add(lblNzCleanUp);
		
		JLabel lblFirstChoseA = new JLabel("First, chose a team name (2-10 characters):");
		lblFirstChoseA.setBounds(12, 97, 316, 15);
		frmNzCleanUp.getContentPane().add(lblFirstChoseA);
		
		//Name entry text field
		teamNameField = new JTextField();
		teamNameField.setText("Enter Team Name");
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
		teamNameField.setBounds(326, 95, 167, 19);
		frmNzCleanUp.getContentPane().add(teamNameField);
		teamNameField.setColumns(10);
		
		JLabel lblNextChoseThe = new JLabel("Now chose the number of towns your heroes will sort out (3-6):");
		lblNextChoseThe.setBounds(12, 141, 458, 15);
		frmNzCleanUp.getContentPane().add(lblNextChoseThe);
		
		//Number of heroes combo box
		nCitiesBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent nCitiesArg) {
				manager.setNCities(Integer.parseInt(nCitiesBox.getSelectedItem().toString()));
			}
		});
		
		nCitiesBox.setMaximumRowCount(4);
		nCitiesBox.setBounds(484, 137, 36, 24);
		frmNzCleanUp.getContentPane().add(nCitiesBox);
		
		JLabel lblNowChooseHow = new JLabel("Now choose how many heroes you want on your team (1-3):");
		lblNowChooseHow.setBounds(12, 187, 441, 15);
		frmNzCleanUp.getContentPane().add(lblNowChooseHow);
		
		nHeroesBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent nHeroesArg) {
				manager.setNHeroes(Integer.parseInt(nHeroesBox.getSelectedItem().toString()));
			}
		});
		
		nHeroesBox.setMaximumRowCount(3);
		nHeroesBox.setBounds(484, 182, 36, 24);
		frmNzCleanUp.getContentPane().add(nHeroesBox);
		
		JButton btnChooseYourHeroes = new JButton("Choose your heroes");
		btnChooseYourHeroes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Fix this so that you can't exit if not set up
				finishedWelcomeScreen();
			}
		});
		btnChooseYourHeroes.setBounds(326, 299, 194, 25);
		frmNzCleanUp.getContentPane().add(btnChooseYourHeroes);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(WelcomeScreen.class.getResource("/HeroGame/Images/new-zealander-flag-graphic.png")));
		lblNewLabel.setBounds(12, 214, 302, 110);
		frmNzCleanUp.getContentPane().add(lblNewLabel);

	}
	
	public void closeScreen() {
		frmNzCleanUp.dispose();
	}
	
	public void finishedWelcomeScreen() {
		manager.closeWelcomeScreen(this);
	}
}
