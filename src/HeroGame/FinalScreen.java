package HeroGame;


import java.awt.EventQueue;
import javax.sound.sampled.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.io.*;

/**
 * FinalScreen Class for Heroes & Villains Game. Sets up and displays End of Game Screen.
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class FinalScreen {

	private JFrame frmNzCleanUp;
	private GameManager manager;
	private String outcome;
	private JPanel panelLose = new JPanel();
	private JPanel panelWin = new JPanel();
	private JPanel panelCredits = new JPanel();
	private Boolean creditsVisible = false;
	private Sound sounds = new Sound();
	private String IMAGE_CREDITS_FILENAME = "/HeroGame/Images/image references.txt";
	private String SOUND_CREDITS_FILENAME = "/HeroGame/Sound/Sounds references.txt";




	/**
	 * Create the application.
	 */
	public FinalScreen(String outcome, GameManager manager) {
		this.manager = manager;
		this.outcome = outcome;
		initialize();
		frmNzCleanUp.setVisible(true);
	}
		
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNzCleanUp = new JFrame();
		frmNzCleanUp.setTitle("NZ Clean Up - Final Screen");
		frmNzCleanUp.setBounds(100, 100, 696, 559);
		frmNzCleanUp.setResizable(false);
		frmNzCleanUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNzCleanUp.getContentPane().setLayout(null);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeScreen();
				manager.launchWelcomeScreen();
			}
		});
		btnNewGame.setBounds(550, 422, 125, 25);
		frmNzCleanUp.getContentPane().add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeScreen();
			}
		});
		btnExit.setBounds(550, 459, 125, 25);
		frmNzCleanUp.getContentPane().add(btnExit);
		
		//****************************************Win Panel********************************************
		panelWin.setBounds(149, 12, 393, 381);
		frmNzCleanUp.getContentPane().add(panelWin);
		panelWin.setLayout(null);
		panelWin.setVisible(false);
		panelWin.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent componentEvent) {
				sounds.playApplause();
			}
		});
		
		JTextArea txtTeamDone = new JTextArea();
		txtTeamDone.setBackground(UIManager.getColor("Panel.background"));
		txtTeamDone.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtTeamDone.setLineWrap(true);
		txtTeamDone.setText("Team " + manager.getTeamName() + " have cleaned up the villains from " + manager.getCities().getCityList() + ". They celebrate with a trip to Rainbow's End and a feed of "
				+ "shark and tatties.");
		txtTeamDone.setWrapStyleWord(true);
		txtTeamDone.setBounds(12, 272, 369, 74);
		panelWin.add(txtTeamDone);
		txtTeamDone.setColumns(10);
		txtTeamDone.setEditable(false);
		
		JLabel lblHooray = new JLabel("Hooray!!");
		lblHooray.setFont(new Font("Dialog", Font.BOLD, 18));
		lblHooray.setBounds(142, 22, 104, 24);
		panelWin.add(lblHooray);
		
		JLabel lblWinnerPic = new JLabel("");
		lblWinnerPic.setIcon(new ImageIcon(FinalScreen.class.getResource("/HeroGame/Images/Winners.jpeg")));
		lblWinnerPic.setBounds(30, 63, 329, 192);
		panelWin.add(lblWinnerPic);
		
		JLabel lblWinningTime = new JLabel("Winning Time: " + getWinningTime());
		lblWinningTime.setBounds(89, 347, 212, 22);
		panelWin.add(lblWinningTime);
		
		//**************************************Lose Panel********************************************************
		panelLose.setBounds(149, 12, 393, 381);
		frmNzCleanUp.getContentPane().add(panelLose);
		panelLose.setLayout(null);
		panelLose.setVisible(false);
		panelLose.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent componentEvent) {
				sounds.playEvilLaugh();
			}
		});
		
		JTextArea txtTeamUndone = new JTextArea();
		txtTeamUndone.setBackground(UIManager.getColor("Panel.background"));
		txtTeamUndone.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtTeamUndone.setLineWrap(true);
		txtTeamUndone.setText("See what you did? Team " + manager.getTeamName() + " have been defeated by Darrin the "
				+ "Aussie Cricketer and his minions. This is why we can't have nice things.");
		txtTeamUndone.setWrapStyleWord(true);
		txtTeamUndone.setBounds(12, 272, 369, 74);
		panelLose.add(txtTeamUndone);
		txtTeamUndone.setColumns(10);
		txtTeamUndone.setEditable(false);
		
		JLabel lblBoo = new JLabel("Boo!!");
		lblBoo.setFont(new Font("Dialog", Font.BOLD, 18));
		lblBoo.setBounds(142, 22, 104, 24);
		panelLose.add(lblBoo);
		
		JLabel lblLoserPic = new JLabel("");
		lblLoserPic.setIcon(new ImageIcon(FinalScreen.class.getResource("/HeroGame/Images/Loser.jpg")));
		lblLoserPic.setBounds(12, 58, 369, 192);
		panelLose.add(lblLoserPic);
		
		//*****************************************Credits Panel**************************************************
		panelCredits.setBounds(149, 12, 393, 509);
		frmNzCleanUp.getContentPane().add(panelCredits);
		panelCredits.setLayout(null);
		panelCredits.setVisible(false);
		
		JLabel lblCredits = new JLabel("Credits");
		lblCredits.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCredits.setBounds(142, 0, 104, 24);
		panelCredits.add(lblCredits);
		
		JTextArea txtCredits = new JTextArea();
		txtCredits.setBackground(UIManager.getColor("Panel.background"));
		txtCredits.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtCredits.setLineWrap(true);
		txtCredits.setText("NZ Clean Up was developed for SENG201-S1 2018 by this team of heroes:\n\n"
				+ "Andy Holden: A rugby playing bartender from whom code flows like Lion's finest from the tap.\n\n"
				+ "Alex Liggett: Float like a concrete canoe, sting like a getting a C, his tidy code and silly jokes "
				+ "hide his average ability.\n\n"
				+ "This game is not intended for general release. Characters are fictional and any resemblance to famous "
				+ "politicians, sportspeople or anyone's mother are entirely coincidental. Media in this game was shamelessly "
				+ "lifted from these sources without permission:");
		txtCredits.setWrapStyleWord(true);
		txtCredits.setBounds(12, 23, 369, 268);
		panelCredits.add(txtCredits);
		txtCredits.setColumns(10);
		txtCredits.setEditable(false);
		
		JTextArea txtrAttribution = new JTextArea();
		txtrAttribution.setBackground(UIManager.getColor("Panel.background"));
		txtrAttribution.setText(readCredits(IMAGE_CREDITS_FILENAME) + readCredits(SOUND_CREDITS_FILENAME));
		txtrAttribution.setFont(new Font("Dialog", Font.PLAIN, 5));
		txtrAttribution.setBounds(12, 286, 369, 211);
		panelCredits.add(txtrAttribution);
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toggleCredits();

			}
		});
		btnCredits.setBounds(550, 496, 125, 25);
		frmNzCleanUp.getContentPane().add(btnCredits);
		
		JLabel lblGameOver = new JLabel("Game Over");
		lblGameOver.setFont(new Font("Dialog", Font.BOLD, 14));
		lblGameOver.setBounds(299, 405, 99, 20);
		frmNzCleanUp.getContentPane().add(lblGameOver);

		setWinLosePanels();

	}
	
	/**
	 * Switch between the credits and the win/lose screen
	 */
	public void toggleCredits() {
		if (creditsVisible) {
			creditsVisible = false;
			panelCredits.setVisible(creditsVisible);
			setWinLosePanels();
		} else {
			creditsVisible = true;
			panelCredits.setVisible(creditsVisible);
			panelWin.setVisible(false);
			panelLose.setVisible(false);

		}
		
	}
	
	/**
	 * Set the win lose screen for the appropriate outcome
	 */
	public void setWinLosePanels( ) {
		if (outcome == "win") {
			panelWin.setVisible(true);
		} else {
			panelLose.setVisible(true);
		}
	
	}
	
	/**
	 * Returns a 00:00:00 formatted String containing the time taken to complete the game
	 * @return  a String formatted hh:mm:ss  being the time taken to complete the game
	 */
	public String getWinningTime() {
		double elapsed = manager.getTeam().getTime(); //in seconds
		int hours = (int)elapsed / 3600;
		elapsed -= (double)hours * 3600;
		int minutes = (int)elapsed /60;
		elapsed -= (double)(minutes * 60);
		int seconds = (int)elapsed;
		String result = String.format("%02d:%02d:%02d", hours,minutes,seconds);
		return result;
	}
	
	/**
	 * Read a text file and return a string containing the lines in the file separated by
	 * a newline character. Will stop reading at a blank line.
	 * @param filename a String, the name of the file to read
	 * @return a String, the contents of the file, with lines separated by newline characters
	 */
	public String readCredits(String filename) {
		String result = "";
		try {
			InputStream images = getClass().getResourceAsStream(filename);
			BufferedReader brText = new BufferedReader(new InputStreamReader(images));
			String line = brText.readLine();
			while(line != null) {
				result += line + "\n";
				line = brText.readLine();
			}
		} catch (IOException error) {
			result = "File Not Found";
		}
		
		return result;
		
	}
	
	/**
	 * Close the window when required
	 */
	public void closeScreen() {
		frmNzCleanUp.dispose();

	}
	
//	public static void main(String[] args) {
//		GameManager m1 = new GameManager();
//		FinalScreen f1 = new FinalScreen("wins", m1);
//		System.out.print(f1.readCredits());
//		
//	}
	
}
