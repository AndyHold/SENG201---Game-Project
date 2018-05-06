package HeroGame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TeamBuildScreen {

	private static final int MAX_HERO_NAME_LENGTH = 12;
	private JFrame frmNzCleanUp;
	private GameManager manager;
	private JLabel member1Name = new JLabel("");
	private JLabel member1Type = new JLabel("");
	private JLabel member2Name = new JLabel("");
	private JLabel member2Type = new JLabel("");
	private JLabel member3Name = new JLabel("");
	private JLabel member3Type = new JLabel("");


	/**
	 * Create the application.
	 */
	public TeamBuildScreen(GameManager incomingManager) {
		this.manager = incomingManager;
		initialize();
		frmNzCleanUp.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNzCleanUp = new JFrame();
		frmNzCleanUp.setTitle("NZ Clean Up - Build Team");
		frmNzCleanUp.getContentPane().setBackground(Color.WHITE);
		frmNzCleanUp.setBounds(100, 100, 550, 370);
		frmNzCleanUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNzCleanUp.getContentPane().setLayout(null);
		
		JLabel lblNowYouNeed = new JLabel("Now you need to choose " + manager.getNHeroes() 
			+ " hero(es) to join team " + manager.getTeamName() + ".");
		lblNowYouNeed.setBounds(12, 12, 524, 15);
		frmNzCleanUp.getContentPane().add(lblNowYouNeed);
		
		JLabel lblThereAre = new JLabel("There are 6 different types of hero in NZ:");
		lblThereAre.setBounds(12, 39, 312, 15);
		frmNzCleanUp.getContentPane().add(lblThereAre);
		
		JButton aBButton = new JButton("All Black");
		
		/**
		 * All black button. On press calls for name entry, if name entry is legal adds an AB to the team
		 * If team slots are all allocated, brings up error message 
		 */
		aBButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameEntry(HeroType.ALL_BLACK);
			}
		});
		
		aBButton.setToolTipText(HeroType.ALL_BLACK.getDescription() +  " with the ability to " + 
				HeroType.ALL_BLACK.getAbility());
		aBButton.setFont(new Font("Dialog", Font.BOLD, 10));
		aBButton.setVerticalAlignment(SwingConstants.TOP);
		aBButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		aBButton.setHorizontalTextPosition(SwingConstants.CENTER);
		aBButton.setIcon(new ImageIcon(TeamBuildScreen.class.getResource("/HeroGame/Images/a_b.jpg")));
		aBButton.setBounds(12, 66, 103, 129);
		frmNzCleanUp.getContentPane().add(aBButton);
		
		JButton fFButton = new JButton("Fire Fighter");
		
		/**
		 * Firefighter button. On press calls for name entry, if name entry is legal adds a firefighter to the team
		 * If team slots are all allocated, brings up error message
		 */
		fFButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameEntry(HeroType.FIREFIGHTER);
			}
		});
		fFButton.setToolTipText(HeroType.FIREFIGHTER.getDescription() +  " with the ability to " + 
				HeroType.FIREFIGHTER.getAbility());
		fFButton.setFont(new Font("Dialog", Font.BOLD, 10));
		fFButton.setVerticalAlignment(SwingConstants.TOP);
		fFButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		fFButton.setHorizontalTextPosition(SwingConstants.CENTER);
		fFButton.setIcon(new ImageIcon(TeamBuildScreen.class.getResource("/HeroGame/Images/f_f.jpg")));
		fFButton.setBounds(127, 66, 103, 129);
		frmNzCleanUp.getContentPane().add(fFButton);
		
		JButton fMButton = new JButton("Foster Mum");
		
		/**
		 * Foster Mum button. On press calls for name entry, if name entry is legal adds a foster mum to the team
		 * If team slots are all allocated, brings up error message
		 */
		fMButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameEntry(HeroType.FOSTER_MUM);
			}
		});

		fMButton.setToolTipText(HeroType.FOSTER_MUM.getDescription() +  " with the ability to " + 
				HeroType.FOSTER_MUM.getAbility());
		fMButton.setFont(new Font("Dialog", Font.BOLD, 10));
		fMButton.setVerticalAlignment(SwingConstants.TOP);
		fMButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		fMButton.setHorizontalTextPosition(SwingConstants.CENTER);
		fMButton.setIcon(new ImageIcon(TeamBuildScreen.class.getResource("/HeroGame/Images/mum.jpg")));
		fMButton.setBounds(242, 66, 103, 129);
		frmNzCleanUp.getContentPane().add(fMButton);
		
		JButton nButton = new JButton("Nurse");
		
		/**
		 * Nurse button. On press calls for name entry, if name entry is legal adds a nurse to the team
		 * If team slots are all allocated, brings up error message
		 */
		nButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameEntry(HeroType.NURSE);
			}
		});
		nButton.setToolTipText(HeroType.NURSE.getDescription() +  " with the ability to " + 
				HeroType.NURSE.getAbility());
		nButton.setFont(new Font("Dialog", Font.BOLD, 10));
		nButton.setVerticalAlignment(SwingConstants.TOP);
		nButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		nButton.setHorizontalTextPosition(SwingConstants.CENTER);
		nButton.setIcon(new ImageIcon(TeamBuildScreen.class.getResource("/HeroGame/Images/nrs.jpg")));
		nButton.setBounds(12, 203, 103, 129);
		frmNzCleanUp.getContentPane().add(nButton);
		
		JButton rSButton = new JButton("Serviceman");
		
		/**
		 * Returned Serviceman button. On press calls for name entry, if name entry is legal adds a returned serviceman to the team
		 * If team slots are all allocated, brings up error message
		 */
		rSButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameEntry(HeroType.RETURNED_SERVICEMAN);
			}
		});
		rSButton.setToolTipText(HeroType.RETURNED_SERVICEMAN.getDescription() +  " with the ability to " + 
				HeroType.RETURNED_SERVICEMAN.getAbility());
		rSButton.setFont(new Font("Dialog", Font.BOLD, 10));
		rSButton.setVerticalAlignment(SwingConstants.TOP);
		rSButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		rSButton.setHorizontalTextPosition(SwingConstants.CENTER);
		rSButton.setIcon(new ImageIcon(TeamBuildScreen.class.getResource("/HeroGame/Images/rsm.jpg")));
		rSButton.setBounds(127, 203, 103, 129);
		frmNzCleanUp.getContentPane().add(rSButton);
		
		JButton sButton = new JButton("Surveyor");
		
		/**
		 * Surveyor button. On press calls for name entry, if name entry is legal adds a surveyor to the team
		 * If team slots are all allocated, brings up error message
		 */
		sButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameEntry(HeroType.SURVEYOR);
			}
		});
		sButton.setToolTipText(HeroType.SURVEYOR.getDescription() +  " with the ability to " + 
				HeroType.SURVEYOR.getAbility());
		sButton.setFont(new Font("Dialog", Font.BOLD, 10));
		sButton.setVerticalAlignment(SwingConstants.TOP);
		sButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		sButton.setHorizontalTextPosition(SwingConstants.CENTER);
		sButton.setIcon(new ImageIcon(TeamBuildScreen.class.getResource("/HeroGame/Images/svr.jpg")));
		sButton.setBounds(242, 203, 103, 129);
		frmNzCleanUp.getContentPane().add(sButton);
		
		JButton btnNewButton = new JButton("Start Game");
		
		/**
		 * Start game button. On press, if the correct number of heroes have been added to the team
		 * closes the window, otherwise brings up an error message
		 */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nMissing = manager.getNHeroes() - manager.getTeam().getTeamSize(); 
				if (nMissing == 0) {
					finishedTeamBuildScreen();
				} else {
					JOptionPane.showMessageDialog(frmNzCleanUp, "You still need to add " + nMissing +
							" hero(es)", "Uh-oh...", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(419, 307, 117, 25);
		frmNzCleanUp.getContentPane().add(btnNewButton);
		
		JPanel teamRoster = new JPanel();
		teamRoster.setBounds(357, 62, 179, 239);
		frmNzCleanUp.getContentPane().add(teamRoster);
		teamRoster.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Team " + manager.getTeamName() + " Roster");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel.setBounds(0, 0, 179, 15);
		teamRoster.add(lblNewLabel);
		
		JPanel member_1 = new JPanel();
		member_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		member_1.setBounds(10, 27, 157, 53);
		teamRoster.add(member_1);
		member_1.setLayout(null);
		
		member1Name.setBounds(0, 0, 145, 15);
		member_1.add(member1Name);
		
		member1Type.setBounds(0, 27, 145, 15);
		member_1.add(member1Type);
		
		JPanel member_2 = new JPanel();
		member_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		member_2.setBounds(10, 89, 155, 53);
		teamRoster.add(member_2);
		member_2.setLayout(null);
		
		member2Name.setBounds(0, 0, 145, 15);
		member_2.add(member2Name);
		
		member2Type.setBounds(0, 27, 145, 15);
		member_2.add(member2Type);
		
		JPanel member_3 = new JPanel();
		member_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		member_3.setBounds(10, 151, 157, 53);
		teamRoster.add(member_3);
		member_3.setLayout(null);
		
		member3Name.setBounds(0, 0, 145, 15);
		member_3.add(member3Name);
		
		member3Type.setBounds(0, 27, 145, 15);
		member_3.add(member3Type);
	}
	
	public void nameEntry(HeroType heroType) {
		String name;
		if (manager.getTeam().getTeamSize() >= manager.getNHeroes()) {
			JOptionPane.showMessageDialog(frmNzCleanUp, "Team already has " +
					manager.getNHeroes() + " member(s)", "Uh-oh...", JOptionPane.ERROR_MESSAGE);
		} else {
			name = JOptionPane.showInputDialog(frmNzCleanUp, "Enter a name (1-12 Characters:");
			if (name == null) {
				return;
			} else if ((name.length() == 0) || (name.length() > MAX_HERO_NAME_LENGTH)) {
			JOptionPane.showMessageDialog(frmNzCleanUp, "Name must be 1-12 character long", "Uh-oh...", JOptionPane.ERROR_MESSAGE);
			} else {
				Hero newHero = new Hero(name, heroType);
				manager.getTeam().addMember(newHero);
				updateRoster();
			}
		}
	}
	
	public void updateRoster() {
		if (manager.getTeam().getTeamSize() > 0) {
			member1Name.setText("Name: "+ manager.getTeam().getHero(0).getName());
			member1Type.setText("Type: " + manager.getTeam().getHero(0).getType().getDescription());
		}
		
		if (manager.getTeam().getTeamSize() > 1) {
			member2Name.setText("Name: "+ manager.getTeam().getHero(1).getName());
			member2Type.setText("Type: " + manager.getTeam().getHero(1).getType().getDescription());
		}
		
		if (manager.getTeam().getTeamSize() > 2) {
			member3Name.setText("Name: "+ manager.getTeam().getHero(2).getName());
			member3Type.setText("Type: " + manager.getTeam().getHero(2).getType().getDescription());
		}
		
		
	}
	
	public void closeScreen() {
		frmNzCleanUp.dispose();
	}
	
	public void finishedTeamBuildScreen() {
		manager.closeTeamBuildScreen(this);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
