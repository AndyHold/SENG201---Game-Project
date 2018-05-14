package HeroGame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.beans.PropertyChangeEvent;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class CityScreen {

	private JFrame frame;
	private JPanel movePanel;
	private JPanel mapPanel;
	private JLabel northLocationLbl;
	private JLabel southLocationLbl;
	private JLabel eastLocationLbl;
	private JLabel westLocationLbl;
	private LocationType northType;
	private LocationType southType;
	private LocationType eastType;
	private LocationType westType;
	private JPanel northLocationPanel;
	private JPanel southLocationPanel;
	private JPanel westLocationPanel;
	private JPanel eastLocationPanel;
	private JPanel centerLocationPanel;
	private City city;
	private GameManager manager;
	private Direction currentDirection = Direction.CENTER;
	private CityScreen cityScreen;
	
	
	public CityScreen(City newCity, Direction newDirection, GameManager newManager) {
		this.city = newCity;
		this.manager = newManager;
		this.currentDirection = newDirection;
		this.cityScreen = this;
		initialize();
		this.frame.setVisible(true);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CityScreen window = new CityScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 */
	public CityScreen() {
		initialize();
	}

	
	/**
	 * gets type of panel to build from the location in that direction then builds the panel.
	 * @param currentLocation Location, location in the given compass direction
	 * @return JPanel, panel of type dictated by the location given.
	 */
	private JPanel buildLocationPanel(Location currentLocation) {
		LocationType type = currentLocation.getLocationType();
		switch(type) {
		case VILLIANSLAIR:
			return buildVilliansLairPanel((VillainsLair)currentLocation);
			
		case SHOP:
			return new JPanel();
//			return buildShopPanel((Shop)currentLocation);
			
		case POWERUPDEN:
//			return buildPowerUpDenPanel((PowerUpDen)currentLocation);
			return new JPanel();
			
		case HOSPITAL:
//			return buildHospitalPanel((Hospital)currentLocation);
			return new JPanel();
			
		case HOMEBASE:
//			return buildHomeBasePanel((HomeBase)currentLocation);
			return new JPanel();
		}
		return null;
	}
	
	
	private JPanel buildVilliansLairPanel(VillainsLair villiansLair) {
		JPanel villainsLairPanel = new JPanel();
		
		villainsLairPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		villainsLairPanel.setBounds(230, 55, 290, 265);
		villainsLairPanel.setMinimumSize(new Dimension(290, 265));
		villainsLairPanel.setLayout(null);
		
		JLabel vLWelcomeMessageLbl = new JLabel("Welcome to " + villiansLair.getName());
		vLWelcomeMessageLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		vLWelcomeMessageLbl.setBounds(10, 10, villainsLairPanel.getWidth(), 15);
		villainsLairPanel.add(vLWelcomeMessageLbl, 0);
		
		JLabel vLVillainPictureLbl = new JLabel("");
		vLVillainPictureLbl.setIcon(villiansLair.getVillainImage());
		vLVillainPictureLbl.setBounds(10, 35, 130, 190);
		vLVillainPictureLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		villainsLairPanel.add(vLVillainPictureLbl, 1);
		
		
		JLabel vLVillainNameLbl = new JLabel(this.city.getVillain().getName());
		vLVillainNameLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		vLVillainNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		vLVillainNameLbl.setBounds(10, 235, 130, 15);
		villainsLairPanel.add(vLVillainNameLbl, 2);
		
		JLabel chooseHeroLbl = new JLabel("Please Choose a Hero:");
		chooseHeroLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		chooseHeroLbl.setHorizontalAlignment(SwingConstants.CENTER);
		chooseHeroLbl.setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140, 35, villainsLairPanel.getWidth() - 160, 25);
		villainsLairPanel.add(chooseHeroLbl, 3);
		
		JComboBox<Hero> heroPickerComboBox = new JComboBox(this.city.getTeam().getMemberList().toArray());
		heroPickerComboBox.setFont(new Font("Tahoma", Font.BOLD, 10));
		heroPickerComboBox.setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140, 90, villainsLairPanel.getWidth() - 160, 25);
		villainsLairPanel.add(heroPickerComboBox, 4);
		
		JButton BattleVillainBtn = new JButton("Battle The Villain");
		BattleVillainBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		BattleVillainBtn.setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140, 145, villainsLairPanel.getWidth() - 160, 25);
		villainsLairPanel.add(BattleVillainBtn, 5);
		BattleVillainBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.closeCityScreen(cityScreen, city, currentDirection, (Hero) ((JComboBox<Hero>) getPanel(currentDirection).getComponent(4)).getSelectedItem());
			}
		});
		
		JButton rollDiceBtn = new JButton("Roll the Dice");
		rollDiceBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rollDiceBtn.setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140, 200, villainsLairPanel.getWidth() - 160, 25);
		villainsLairPanel.add(rollDiceBtn, 6);
		rollDiceBtn.setVisible(false);
		
		
		
		return villainsLairPanel;
	}
	
	
	private JPanel getPanel(Direction currentDirection) {
		switch(currentDirection) {
		case NORTH:
			return northLocationPanel;
		case SOUTH:
			return southLocationPanel;
		case WEST:
			return westLocationPanel;
		case EAST:
			return eastLocationPanel;
		case CENTER:
			return centerLocationPanel;
		}
		return null;
	}
	
	
	private void resizePanel(LocationType type, JPanel panel) {
		switch(type) {
		case VILLIANSLAIR:
			villiansLairResizeRules(panel);
			break;
		case SHOP:
			shopResizeRules(panel);
			break;
		case POWERUPDEN:
			powerUpDenResizeRules(panel);
			break;
		case HOSPITAL:
			hospitalResizeRules(panel);
			break;
		case HOMEBASE:
			homeBaseResizeRules(panel);
			break;
		}
	}
	
	
	private void homeBaseResizeRules(JPanel homeBasePanel) {
		
	}
	
	
	private void hospitalResizeRules(JPanel hospitalPanel) {
		
	}
	
	
	private void powerUpDenResizeRules(JPanel powerUpDenPanel) {
		
	}
	
	
	private void shopResizeRules(JPanel shopLocationPanel) {
		
	}
	
	
	/**
	 * moves the buttons in villains lair panel when the panel is resized
	 * @param villiansLairPanel JPanel, villainsLair Panel
	 */
	private void villiansLairResizeRules(JPanel villainsLairPanel) {
		villainsLairPanel.getComponent(0).setBounds(10, 10, villainsLairPanel.getWidth(), 15);
		villainsLairPanel.getComponent(3).setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140, 35, villainsLairPanel.getWidth() - 160, 25);
		villainsLairPanel.getComponent(4).setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140, (villainsLairPanel.getComponent(3).getY() + 25) + (villainsLairPanel.getHeight() - 175) / 3, villainsLairPanel.getWidth() - 160, 25);
		villainsLairPanel.getComponent(5).setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140, (villainsLairPanel.getComponent(4).getY() + 25) + (villainsLairPanel.getHeight() - 175) / 3, villainsLairPanel.getWidth() - 160, 25);
		villainsLairPanel.getComponent(6).setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140, villainsLairPanel.getHeight() - 65, villainsLairPanel.getWidth() - 160, 25);
	}
	
	
	/**
	 * Changes location panel to match location
	 * @param direction Direction, direction user would like to move in
	 */
	private void move(Direction direction) {
		switch(direction) {
		case NORTH:
			if(currentDirection == Direction.CENTER) {
				centerLocationPanel.setVisible(false);
				northLocationPanel.setVisible(true);
				currentDirection = Direction.NORTH;
			} else {
				if(currentDirection == Direction.SOUTH) {
					southLocationPanel.setVisible(false);
					centerLocationPanel.setVisible(true);
					currentDirection = Direction.CENTER;
				} else {
					JOptionPane.showMessageDialog(frame, this.city.getBadDirectionMessage(), "Whoops...", JOptionPane.ERROR_MESSAGE);
				}
			}
			break;
			
		case SOUTH:
			if(currentDirection == Direction.CENTER) {
				centerLocationPanel.setVisible(false);
				southLocationPanel.setVisible(true);
				currentDirection = Direction.SOUTH;
			} else {
				if(currentDirection == Direction.NORTH) {
					northLocationPanel.setVisible(false);
					centerLocationPanel.setVisible(true);
					currentDirection = Direction.CENTER;
				} else {
					JOptionPane.showMessageDialog(frame, this.city.getBadDirectionMessage(), "Whoops...", JOptionPane.ERROR_MESSAGE);
				}
			}
			break;
			
		case EAST:
			if(currentDirection == Direction.CENTER) {
				centerLocationPanel.setVisible(false);
				eastLocationPanel.setVisible(true);
				currentDirection = Direction.EAST;
			} else {
				if(currentDirection == Direction.WEST) {
					westLocationPanel.setVisible(false);
					centerLocationPanel.setVisible(true);
					currentDirection = Direction.CENTER;
				} else {
					JOptionPane.showMessageDialog(frame, this.city.getBadDirectionMessage(), "Whoops...", JOptionPane.ERROR_MESSAGE);
				}
			}
			break;
			
		case WEST:
			if(currentDirection == Direction.CENTER) {
				centerLocationPanel.setVisible(false);
				westLocationPanel.setVisible(true);
				currentDirection = Direction.WEST;
			} else {
				if(currentDirection == Direction.EAST) {
					eastLocationPanel.setVisible(false);
					centerLocationPanel.setVisible(true);
					currentDirection = Direction.CENTER;
				} else {
					JOptionPane.showMessageDialog(frame, this.city.getBadDirectionMessage(), "Whoops...", JOptionPane.ERROR_MESSAGE);
				}
			}
			break;
		}
	}
	
	/**
	 * makes starting panel visible
	 */
	private void setStartPanel() {
		switch(currentDirection) {
		case NORTH:
			northLocationPanel.setVisible(true);
			break;
		case SOUTH:
			southLocationPanel.setVisible(true);
			break;
		case EAST:
			eastLocationPanel.setVisible(true);
			break;
		case WEST:
			westLocationPanel.setVisible(true);
			break;
		case CENTER:
			centerLocationPanel.setVisible(true);
			break;
		}
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
//				setupPanel.setBounds(230, 55, frame.getWidth() - 260, frame.getHeight() - 105);
				northLocationPanel.setBounds(230, 55, frame.getWidth() - 260, frame.getHeight() - 105);
				resizePanel(northType, northLocationPanel);
				southLocationPanel.setBounds(230, 55, frame.getWidth() - 260, frame.getHeight() - 105);
				resizePanel(southType, southLocationPanel);
				eastLocationPanel.setBounds(230, 55, frame.getWidth() - 260, frame.getHeight() - 105);
				resizePanel(eastType, eastLocationPanel);
				westLocationPanel.setBounds(230, 55, frame.getWidth() - 260, frame.getHeight() - 105);
				resizePanel(westType, westLocationPanel);
				centerLocationPanel.setBounds(230, 55, frame.getWidth() - 260, frame.getHeight() - 105);
				resizePanel(LocationType.HOMEBASE, centerLocationPanel);
				movePanel.setBounds(40 , (165 + (frame.getHeight() - 270) / 2) , 150, 100);
			}
		});
		frame.setBounds(100, 100, 550, 370);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setMinimumSize(new Dimension(550, 370));
		
//		JLabel welcomeLbl = new JLabel("Welcome to ");
		JLabel welcomeLbl = new JLabel("Welcome to " + city.getName());
		welcomeLbl.setBounds(10, 10, 510, 15);
		welcomeLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(welcomeLbl);
		
//		JLabel rulerLbl = new JLabel("Currently Ruled By: ");
		JLabel rulerLbl = new JLabel("Currently Ruled By: " + this.city.getVillain().getName());
		rulerLbl.setBounds(10, 30, 510, 15);
		rulerLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(rulerLbl);
		
		northType = this.city.getNorthLocation().getLocationType();
		northLocationPanel = buildLocationPanel(this.city.getNorthLocation());
		frame.getContentPane().add(northLocationPanel);
		northLocationPanel.setVisible(false);
		
		southType = this.city.getSouthLocation().getLocationType();
		southLocationPanel = buildLocationPanel(this.city.getSouthLocation());
		frame.getContentPane().add(southLocationPanel);
		southLocationPanel.setVisible(false);
		
		eastType = this.city.getEastLocation().getLocationType();
		eastLocationPanel = buildLocationPanel(this.city.getEastLocation());
		frame.getContentPane().add(eastLocationPanel);
		eastLocationPanel.setVisible(false);
		
		westType = this.city.getWestLocation().getLocationType();
		westLocationPanel = buildLocationPanel(this.city.getWestLocation());
		frame.getContentPane().add(westLocationPanel);
		westLocationPanel.setVisible(false);
		
		centerLocationPanel = buildLocationPanel(this.city.getCenterLocation());
		frame.getContentPane().add(centerLocationPanel);
		centerLocationPanel.setVisible(false);
		setStartPanel();
		
//		JPanel villainsLairPanel = new JPanel();
//		villainsLairPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
//		villainsLairPanel.setBounds(230, 55, 290, 265);
//		frame.getContentPane().add(villainsLairPanel);
//		villainsLairPanel.setMinimumSize(new Dimension(290, 265));
//		villainsLairPanel.setLayout(null);
////		villainsLairPanel.addComponentListener(new ComponentAdapter() {
////			public void componentResized(ComponentEvent componentEvent) {
////				villainsLairPanel.getComponent(3).setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140, 35, villainsLairPanel.getWidth() - 160, 25);
////				villainsLairPanel.getComponent(4).setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140, (villainsLairPanel.getComponent(3).getY() + 25) + (villainsLairPanel.getHeight() - 175) / 3, villainsLairPanel.getWidth() - 160, 25);
////				villainsLairPanel.getComponent(5).setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140,  (villainsLairPanel.getComponent(4).getY() + 25) + (villainsLairPanel.getHeight() - 175) / 3, villainsLairPanel.getWidth() - 160, 25);
////				villainsLairPanel.getComponent(6).setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140, villainsLairPanel.getHeight() - 65, villainsLairPanel.getWidth() - 160, 25);
////			}
////		});
//		
//		JLabel vLWelcomeMessageLbl = new JLabel("Welcome to "); //Need to figure out how to get villains Lair name
//		vLWelcomeMessageLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
//		vLWelcomeMessageLbl.setBounds(10, 10, 270, 15);
//		villainsLairPanel.add(vLWelcomeMessageLbl);
//		
//		JLabel vLVillainPictureLbl = new JLabel("");
//		vLVillainPictureLbl.setIcon(new ImageIcon(CityScreen.class.getResource("/HeroGame/Images/aussie_cricketer.jpg")));
//		vLVillainPictureLbl.setBounds(10, 35, 130, 190);
//		vLVillainPictureLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
//		villainsLairPanel.add(vLVillainPictureLbl);
//		
//		
//		JLabel vLVillainNameLbl = new JLabel("Villain Name");
////		JLabel vLVillainNameLbl = new JLabel(this.city.getVillain().getName());
//		vLVillainNameLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
//		vLVillainNameLbl.setBounds(10, 235, 130, 15);
//		villainsLairPanel.add(vLVillainNameLbl);
//		
//		JButton randomBattleBtn = new JButton("Pick a random battle");
//		randomBattleBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
//		randomBattleBtn.setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140, 35, villainsLairPanel.getWidth() - 160, 25);
//		villainsLairPanel.add(randomBattleBtn);
//		randomBattleBtn.setVisible(false);
//		
//		JButton paperScissorsRockBtn = new JButton("Paper Scissors Rock");
//		paperScissorsRockBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
//		paperScissorsRockBtn.setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140, (randomBattleBtn.getY() + 25) + (villainsLairPanel.getHeight() - 175) / 3, villainsLairPanel.getWidth() - 160, 25);
//		villainsLairPanel.add(paperScissorsRockBtn);
//		paperScissorsRockBtn.setVisible(false);
//		
//		JButton guessNumberBtn = new JButton("Guess a Number");
//		guessNumberBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
//		guessNumberBtn.setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140,  (paperScissorsRockBtn.getY() + 25) + (villainsLairPanel.getHeight() - 175) / 3, villainsLairPanel.getWidth() - 160, 25);
//		villainsLairPanel.add(guessNumberBtn);
//		
//		JButton rollDiceBtn = new JButton("Roll the Dice");
//		rollDiceBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
//		rollDiceBtn.setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140, villainsLairPanel.getHeight() - 65, villainsLairPanel.getWidth() - 160, 25);
//		villainsLairPanel.add(rollDiceBtn);
//		
//		JComboBox<Hero> heroPickerComboBox = new JComboBox(this.city.getTeam().getArray().toArray());
//		heroPickerComboBox.setFont(new Font("Tahoma", Font.BOLD, 10));
//		heroPickerComboBox.setBounds(150, 60, 130, 25);
//		villainsLairPanel.add(heroPickerComboBox);
//		
//		
//		JLabel lblNewLabel = new JLabel("Please Choose a Hero:");
//		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
//		lblNewLabel.setBounds(150, 35, 130, 15);
//		villainsLairPanel.add(lblNewLabel);
//		villainsLairPanel.setVisible(true);
		
		mapPanel = new JPanel();
		mapPanel.setBackground(Color.DARK_GRAY);
		mapPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mapPanel.setBounds(10, 55, 210, 150);
		frame.getContentPane().add(mapPanel);
		mapPanel.setLayout(null);
		mapPanel.setMaximumSize(new Dimension(210, 150));
		mapPanel.setMinimumSize(new Dimension(210, 150));
		
		JLabel centerLocationLbl = new JLabel("");
		centerLocationLbl.setIcon(new ImageIcon(CityScreen.class.getResource("/HeroGame/Images/HomeBase.png")));
		centerLocationLbl.setBounds(70, 50, 70, 50);
		mapPanel.add(centerLocationLbl);
		centerLocationLbl.setHorizontalAlignment(0);
		centerLocationLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		southLocationLbl = new JLabel("");
		southLocationLbl.setIcon(new ImageIcon(CityScreen.class.getResource("/HeroGame/Images/QuestionMark.png")));
		southLocationLbl.setBounds(70, 100, 70, 50);
		mapPanel.add(southLocationLbl);
		southLocationLbl.setHorizontalAlignment(0);
		southLocationLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		northLocationLbl = new JLabel("");
		northLocationLbl.setIcon(new ImageIcon(CityScreen.class.getResource("/HeroGame/Images/QuestionMark.png")));
		northLocationLbl.setBounds(70, 0, 70, 50);
		mapPanel.add(northLocationLbl);
		northLocationLbl.setHorizontalAlignment(0);
		northLocationLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		westLocationLbl = new JLabel("");
		westLocationLbl.setIcon(new ImageIcon(CityScreen.class.getResource("/HeroGame/Images/QuestionMark.png")));
		westLocationLbl.setBounds(0, 50, 70, 50);
		mapPanel.add(westLocationLbl);
		westLocationLbl.setHorizontalAlignment(0);
		westLocationLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		eastLocationLbl = new JLabel("");
		eastLocationLbl.setIcon(new ImageIcon(CityScreen.class.getResource("/HeroGame/Images/QuestionMark.png")));
		eastLocationLbl.setBounds(140, 50, 70, 50);
		mapPanel.add(eastLocationLbl);
		eastLocationLbl.setHorizontalAlignment(0);
		eastLocationLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblMap = new JLabel("Map:");
		lblMap.setForeground(Color.LIGHT_GRAY);
		lblMap.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMap.setBackground(Color.DARK_GRAY);
		lblMap.setBounds(0, 0, 46, 14);
		mapPanel.add(lblMap);
		
		movePanel = new JPanel();
		movePanel.setBackground(Color.DARK_GRAY);
		movePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		movePanel.setBounds(40, 215, 150, 100);
		frame.getContentPane().add(movePanel);
		movePanel.setLayout(null);
		movePanel.setMaximumSize(new Dimension(140, 100));
		movePanel.setMinimumSize(new Dimension(140, 100));
		
		JButton northMoveBtn = new JButton("N");
		northMoveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				move(Direction.NORTH);
//				useMap();
			}
		});
		northMoveBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		northMoveBtn.setBounds(50, 0, 50, 50);
		movePanel.add(northMoveBtn);
		
		JButton southMoveBtn = new JButton("S");
		southMoveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(Direction.SOUTH);
			}
		});
		southMoveBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		southMoveBtn.setBounds(50, 50, 50, 50);
		movePanel.add(southMoveBtn);
		
		JButton westMoveBtn = new JButton("W");
		westMoveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(Direction.WEST);
			}
		});
		westMoveBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		westMoveBtn.setBounds(0, 25, 50, 50);
		movePanel.add(westMoveBtn);
		
		JButton eastMoveBtn = new JButton("E");
		eastMoveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(Direction.EAST);
			}
		});
		eastMoveBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		eastMoveBtn.setBounds(100, 25, 50, 50);
		movePanel.add(eastMoveBtn);
		
		JLabel lblMove = new JLabel("Move:");
		lblMove.setForeground(Color.LIGHT_GRAY);
		lblMove.setBounds(0, -1, 46, 15);
		movePanel.add(lblMove);
		lblMove.setFont(new Font("Tahoma", Font.BOLD, 13));
	}
}
