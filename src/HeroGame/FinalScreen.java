package HeroGame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FinalScreen {

	private JFrame frmNzCleanUp;
	private JTextField txtTeamHaveCleaned;
	private GameManager manager;
	private String outcome;



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
		frmNzCleanUp.setBounds(100, 100, 696, 471);
		frmNzCleanUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNzCleanUp.getContentPane().setLayout(null);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeScreen();
				manager.launchWelcomeScreen();
			}
		});
		btnNewGame.setBounds(550, 331, 125, 25);
		frmNzCleanUp.getContentPane().add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeScreen();
			}
		});
		btnExit.setBounds(550, 368, 125, 25);
		frmNzCleanUp.getContentPane().add(btnExit);
		
		JPanel panel = new JPanel();
		panel.setBounds(149, 12, 393, 423);
		frmNzCleanUp.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtTeamHaveCleaned = new JTextField();
		txtTeamHaveCleaned.setText("Team " + manager.getTeamName() + " have cleaned up the villains from <getCitytList>. They celebrate with a trip to Rainbow's End and a feed of shark and tatties.");
		txtTeamHaveCleaned.setBounds(12, 347, 349, 64);
		panel.add(txtTeamHaveCleaned);
		txtTeamHaveCleaned.setColumns(10);
		txtTeamHaveCleaned.setEditable(false);
		
		JLabel lblNewLabel = new JLabel("Hooray!!");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(156, 12, 83, 24);
		panel.add(lblNewLabel);
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.setBounds(550, 405, 125, 25);
		frmNzCleanUp.getContentPane().add(btnCredits);
	}
	
	/**
	 * Close the window when required
	 */
	public void closeScreen() {
		frmNzCleanUp.dispose();

	}
	
	
}
