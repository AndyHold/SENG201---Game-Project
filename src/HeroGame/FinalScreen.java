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
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class FinalScreen {

	private JFrame frmNzCleanUp;
	private GameManager manager;
	private String outcome;
	private JPanel panelLose = new JPanel();
	private JPanel panelWin = new JPanel();
	private JPanel panelCredits = new JPanel();
	private Boolean creditsVisible = false;



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
		
		//****************************************Win Panel********************************************
		panelWin.setBounds(149, 12, 393, 381);
		frmNzCleanUp.getContentPane().add(panelWin);
		panelWin.setLayout(null);
		panelWin.setVisible(false);
		
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
		panelCredits.setBounds(149, 12, 393, 418);
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
				+ "Andy Holden: A rubgy playing bartender from whom code flows like Lion's finest from the tap.\n\n"
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
		txtrAttribution.setText("https://oliverpreston.wordpress.com/category/australian-cricket/\n"
				+ "https://thecuriouskiwi.co.nz/rotorua_cafe_guide.php\n"
				+ "http://www.k2interiors.co.nz/project-red_hummingbird-1-1\n"
				+ "https://www.aut.ac.nz/__data/assets/image/0005/107996/mardie-healey.jpg\n"
				+ "https://www.otago.ac.nz/cs/groups/public/@surveying/documents/contributorimg/otago652829.jpg\n"
				+ "https://teara.govt.nz/files/31971-odt_0.jpg\nhttp://sp.imgci.com/PICTURES/CMS/100/106.1.jpg\n"
				+ "https://thespinoff.co.nz/wp-content/uploads/2017/10/Roger-Douglas.jpg\n"
				+ "https://resources.stuff.co.nz/content/dam/images/1/9/7/x/9/u/image.related.StuffLandscapeSixteenByNine.620x349.197x9b.png/1452971758822.jpg\n"
				+ "https://upload.wikimedia.org/wikipedia/commons/3/3e/Rudy_Giuliani.jpg\n"
				+ "http://www.icmotorgroup.co.nz/i/images/Thumbnails/team/Service.jpg\n"
				+ "http://static.tvtropes.org/pmwiki/pub/images/bouncer2_2294.jpg\n"
				+ "https://i1.wp.com/www.endofthreefitness.com/wp-content/uploads/2012/06/band-of-brothers.jpeg?zoom=2&resize=640%2C360\n"
				+ "http://p.imgci.com/db/PICTURES/CMS/209800/209877.jpg");
		txtrAttribution.setFont(new Font("Dialog", Font.PLAIN, 5));
		txtrAttribution.setBounds(12, 290, 369, 128);
		panelCredits.add(txtrAttribution);
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toggleCredits();

			}
		});
		btnCredits.setBounds(550, 405, 125, 25);
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
	 * Close the window when required
	 */
	public void closeScreen() {
		frmNzCleanUp.dispose();

	}
	
//	public static void main(String[] args) {
//		GameManager m1 = new GameManager();
//		System.out.println(System.currentTimeMillis());
//		Team t1 = new Team("Awesome");
//		m1.setTeam(t1);
//		t1.startClock();
//		m1.launchFinalScreen("win");
		
//	}
	
}
