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
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.beans.PropertyChangeEvent;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

/**
 * CityScreen Class for Heroes & Villains Game
 * This is the primary running loop for the GUI game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
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
	private JLabel welcomeLbl;
	private JLabel rulerLbl;
	
	/**
	 * Parameterised Constructor
	 * @param newCity a City the City to be displayed/navigated
	 * @param newDirection a Direction an instance of the Direction Enum
	 * @param newManager a GameManager the manager maintaining state through the game
	 */
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
		GameManager manager = new GameManager(); 
		Hero hero = new Hero("Jim", HeroType.ALL_BLACK);
		Team team = new Team("Team");
		PowerUp cheeseRoll = new PowerUp(PowerUpType.CHEESE_ROLL);
		PowerUp pavlova = new PowerUp(PowerUpType.PAVLOVA);
		PowerUp pineappleLumps = new PowerUp(PowerUpType.PINEAPPLE_LUMPS);
		HealingItem doubleBrown = new HealingItem(HealingItemType.DOUBLE_BROWN);
		HealingItem lindauer = new HealingItem(HealingItemType.LINDAUER);
		HealingItem lionRed = new HealingItem(HealingItemType.LION_RED);
		team.addPowerUp(cheeseRoll);
		team.addPowerUp(pavlova);
		team.addPowerUp(pineappleLumps);
		team.addHealingItem(doubleBrown);
		team.addHealingItem(lindauer);
		team.addHealingItem(lionRed);
		team.addMember(hero);
		City newerCity = new City("Springfield", Villain.AUSSIECRICKETER, team);
		CityScreen newCityScreen = new CityScreen(newerCity, Direction.CENTER, manager);
	}

	
	/**
	 * Non Parameterised constructor. Unused? AH???
	 */
	public CityScreen() {
		initialize();
	}

	
	/**
	 * Gets type of panel to build from the location in that direction then builds the panel.
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
			return buildPowerUpDenPanel((PowerUpDen)currentLocation);
			
		case HOSPITAL:
			return buildHospitalPanel((Hospital)currentLocation);
			
		case HOMEBASE:
//			return buildHomeBasePanel((HomeBase)currentLocation);
			return new JPanel();
		}
		return null;
	}
	
	
	
	private JPanel buildPowerUpDenPanel(PowerUpDen powerUpDen) {
		
		JPanel powerUpDenPanel = new JPanel();
		powerUpDenPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		powerUpDenPanel.setBounds(230, 55, 290, 265);
		frame.getContentPane().add(powerUpDenPanel);
		powerUpDenPanel.setMinimumSize(new Dimension(290, 265));
		powerUpDenPanel.setLayout(null);
		
		JLabel welcomeMessageLbl = new JLabel(MessageFormat.format("Welcome to {0}", powerUpDen.getName()));
		welcomeMessageLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		welcomeMessageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeMessageLbl.setBounds(10, 10, powerUpDenPanel.getWidth(), 15);
		powerUpDenPanel.add(welcomeMessageLbl, 0);
		
		JLabel interiorPictureLbl = new JLabel("");
		interiorPictureLbl.setIcon(powerUpDen.getInteriorImage());
		interiorPictureLbl.setBounds(10, 35, 130, 190);
		interiorPictureLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		powerUpDenPanel.add(interiorPictureLbl, 1);
				
		JLabel heroChoiceLbl = new JLabel("Please Choose a Hero for the Power Up:");
		heroChoiceLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		heroChoiceLbl.setBounds(((powerUpDenPanel.getWidth() - 140) / 2) - (powerUpDenPanel.getWidth() - 160) / 2 + 140, 35, powerUpDenPanel.getWidth() - 160, 15);
		powerUpDenPanel.add(heroChoiceLbl, 2);
		
		JComboBox<Hero> heroPickerComboBox = new JComboBox(this.city.getTeam().getMemberList().toArray());
		heroPickerComboBox.setFont(new Font("Tahoma", Font.BOLD, 10));
		heroPickerComboBox.setBounds(((powerUpDenPanel.getWidth() - 140) / 2) - (powerUpDenPanel.getWidth() - 160) / 2 + 140, heroChoiceLbl.getY() + 15, powerUpDenPanel.getWidth() - 160, 25);
		powerUpDenPanel.add(heroPickerComboBox, 3);
		heroPickerComboBox.setSelectedItem(null);
		
		JLabel itemChoiceLbl = new JLabel("Please Choose a Power Up:");
		itemChoiceLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		itemChoiceLbl.setBounds(((powerUpDenPanel.getWidth() - 140) / 2) - (powerUpDenPanel.getWidth() - 160) / 2 + 140,  (heroPickerComboBox.getY() + 25) + (powerUpDenPanel.getHeight() - 230) / 3, powerUpDenPanel.getWidth() - 160, 15);
		powerUpDenPanel.add(itemChoiceLbl, 4);
		System.out.println(powerUpDenPanel.getHeight());
		
		JComboBox<Hero> itemPickerComboBox = new JComboBox(this.city.getTeam().getPowerUps().toArray());
		itemPickerComboBox.setFont(new Font("Tahoma", Font.BOLD, 10));
		itemPickerComboBox.setBounds(((powerUpDenPanel.getWidth() - 140) / 2) - (powerUpDenPanel.getWidth() - 160) / 2 + 140, itemChoiceLbl.getY() + 15, powerUpDenPanel.getWidth() - 160, 25);
		powerUpDenPanel.add(itemPickerComboBox, 5);
		itemPickerComboBox.setSelectedItem(null);
		
		JButton applyItemBtn = new JButton("Apply Power Up");
		applyItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		applyItemBtn.setBounds(((powerUpDenPanel.getWidth() - 140) / 2) - (powerUpDenPanel.getWidth() - 160) / 2 + 140,  (itemPickerComboBox.getY() + 25) + (powerUpDenPanel.getHeight() - 230) / 3, powerUpDenPanel.getWidth() - 160, 25);
		powerUpDenPanel.add(applyItemBtn, 6);	
		applyItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hero hero = (Hero) ((JComboBox<Hero>) getPanel(currentDirection).getComponent(3)).getSelectedItem();
				if(hero != null) {
					PowerUp powerUp = (PowerUp) ((JComboBox<PowerUp>) getPanel(currentDirection).getComponent(5)).getSelectedItem();
					if(powerUp != null) {
						String response = hero.eatPowerUp(powerUp);
						int powerUpIndex = ((JComboBox<PowerUp>) getPanel(currentDirection).getComponent(5)).getSelectedIndex();
						city.getTeam().removePowerUp(powerUpIndex);
						// DOESNT REMOVE THE ITEM FROM THE COMBOBOX AFTER USE
						JOptionPane.showMessageDialog(frame, response, "Well Done!", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(frame, MessageFormat.format(((PowerUpDen) city.getLocation(currentDirection)).getBadPowerUpMessage(), hero.getName()), "Not good...", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "You have failed to choose a hero, seriously...one job...", "Not good...", JOptionPane.ERROR_MESSAGE);
				}
			}
		});	
		
		JButton checkTimersBtn = new JButton("Check Current Power Ups");
		checkTimersBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		checkTimersBtn.setBounds(((powerUpDenPanel.getWidth() - 140) / 2) - (powerUpDenPanel.getWidth() - 160) / 2 + 140,   (applyItemBtn.getY() + 25) + (powerUpDenPanel.getHeight() - 230) / 3, powerUpDenPanel.getWidth() - 160, 25);
		powerUpDenPanel.add(checkTimersBtn, 7);
		checkTimersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((JLabel) getPanel(currentDirection).getComponent(8)).setText(((PowerUpDen) city.getLocation(currentDirection)).checkPowerUps());
				((JLabel) getPanel(currentDirection).getComponent(8)).setBorder(new LineBorder(new Color(0, 0, 0)));
			}
		});
		
		JLabel timesLbl = new JLabel("");
		timesLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		timesLbl.setHorizontalAlignment(SwingConstants.LEADING);
		timesLbl.setVerticalAlignment(SwingConstants.TOP);
		timesLbl.setBounds(((powerUpDenPanel.getWidth() - 140) / 2) - (powerUpDenPanel.getWidth() - 160) / 2 + 140,   checkTimersBtn.getY() + 25, powerUpDenPanel.getWidth() - 160, 65);
		powerUpDenPanel.add(timesLbl, 8);
		
		powerUpDenPanel.setVisible(true);
		return powerUpDenPanel;
	}
	
	
	/**
	 * Method to construct a Hospital Panel
	 * @param hospital Hospital, the hospital this panel is being constructed from
	 * @return JPanel, the constructed panel for the hospital
	 */
	private JPanel buildHospitalPanel(Hospital hospital) {
		
		JPanel hospitalPanel = new JPanel();
		hospitalPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		hospitalPanel.setBounds(230, 55, 290, 265);
		frame.getContentPane().add(hospitalPanel);
		hospitalPanel.setMinimumSize(new Dimension(290, 265));
		hospitalPanel.setLayout(null);
		hospitalPanel.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent componentEvent) {
				((Hospital) city.getLocation(currentDirection)).checkHealingTimes();
			}
		});
		
		JLabel welcomeMessageLbl = new JLabel(MessageFormat.format("Welcome to {0}", hospital.getName()));
		welcomeMessageLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		welcomeMessageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeMessageLbl.setBounds(10, 10, hospitalPanel.getWidth(), 15);
		hospitalPanel.add(welcomeMessageLbl, 0);
		
		JLabel interiorPictureLbl = new JLabel("");
		interiorPictureLbl.setIcon(hospital.getInteriorImage());
		interiorPictureLbl.setBounds(10, 35, 130, 190);
		interiorPictureLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		hospitalPanel.add(interiorPictureLbl, 1);
				
		JLabel heroChoiceLbl = new JLabel("Please Choose a Hero to Heal:");
		heroChoiceLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		heroChoiceLbl.setBounds(((hospitalPanel.getWidth() - 140) / 2) - (hospitalPanel.getWidth() - 160) / 2 + 140, 35, hospitalPanel.getWidth() - 160, 15);
		hospitalPanel.add(heroChoiceLbl, 2);
		
		JComboBox<Hero> heroPickerComboBox = new JComboBox(this.city.getTeam().getMemberList().toArray());
		heroPickerComboBox.setFont(new Font("Tahoma", Font.BOLD, 10));
		heroPickerComboBox.setBounds(((hospitalPanel.getWidth() - 140) / 2) - (hospitalPanel.getWidth() - 160) / 2 + 140, heroChoiceLbl.getY() + 15, hospitalPanel.getWidth() - 160, 25);
		hospitalPanel.add(heroPickerComboBox, 3);
		heroPickerComboBox.setSelectedItem(null);
		
		JLabel itemChoiceLbl = new JLabel("Please Choose a Healing Item:");
		itemChoiceLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		itemChoiceLbl.setBounds(((hospitalPanel.getWidth() - 140) / 2) - (hospitalPanel.getWidth() - 160) / 2 + 140,  (heroPickerComboBox.getY() + 25) + (hospitalPanel.getHeight() - 230) / 3, hospitalPanel.getWidth() - 160, 15);
		hospitalPanel.add(itemChoiceLbl, 4);
		System.out.println(hospitalPanel.getHeight());
		
		JComboBox<Hero> itemPickerComboBox = new JComboBox(this.city.getTeam().getHealingItems().toArray());
		itemPickerComboBox.setFont(new Font("Tahoma", Font.BOLD, 10));
		itemPickerComboBox.setBounds(((hospitalPanel.getWidth() - 140) / 2) - (hospitalPanel.getWidth() - 160) / 2 + 140, itemChoiceLbl.getY() + 15, hospitalPanel.getWidth() - 160, 25);
		hospitalPanel.add(itemPickerComboBox, 5);
		itemPickerComboBox.setSelectedItem(null);
		
		JButton applyItemBtn = new JButton("Apply Item");
		applyItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		applyItemBtn.setBounds(((hospitalPanel.getWidth() - 140) / 2) - (hospitalPanel.getWidth() - 160) / 2 + 140,  (itemPickerComboBox.getY() + 25) + (hospitalPanel.getHeight() - 230) / 3, hospitalPanel.getWidth() - 160, 25);
		hospitalPanel.add(applyItemBtn, 6);	
		applyItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hero hero = (Hero) ((JComboBox<Hero>) getPanel(currentDirection).getComponent(3)).getSelectedItem();
				if(hero != null) {
					HealingItem healingItem = (HealingItem) ((JComboBox<HealingItem>) getPanel(currentDirection).getComponent(5)).getSelectedItem();
					if(healingItem != null) {
						double drinkTime = city.getTeam().getTime();
						String response = hero.drinkHealingItem(healingItem, drinkTime);
						int healingItemIndex = ((JComboBox<HealingItem>) getPanel(currentDirection).getComponent(5)).getSelectedIndex();
						city.getTeam().removeHealingItem(healingItemIndex);
						// DOESNT REMOVE THE ITEM FROM THE COMBOBOX AFTER USE
						JOptionPane.showMessageDialog(frame, response, "Well Done!", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(frame, MessageFormat.format(((Hospital) city.getLocation(currentDirection)).getBadHealingItemMessage(), hero.getName()), "Not good...", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "You have failed to choose a hero, seriously...one job...", "Not good...", JOptionPane.ERROR_MESSAGE);
				}
			}
		});	
		
		JButton checkTimersBtn = new JButton("Check Timers");
		checkTimersBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		checkTimersBtn.setBounds(((hospitalPanel.getWidth() - 140) / 2) - (hospitalPanel.getWidth() - 160) / 2 + 140,   (applyItemBtn.getY() + 25) + (hospitalPanel.getHeight() - 230) / 3, hospitalPanel.getWidth() - 160, 25);
		hospitalPanel.add(checkTimersBtn, 7);
		checkTimersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((JLabel) getPanel(currentDirection).getComponent(8)).setText(((Hospital) city.getLocation(currentDirection)).checkHealingTimes());
				((JLabel) getPanel(currentDirection).getComponent(8)).setBorder(new LineBorder(new Color(0, 0, 0)));
			}
		});
		
		JLabel timesLbl = new JLabel("");
		timesLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		timesLbl.setHorizontalAlignment(SwingConstants.LEADING);
		timesLbl.setVerticalAlignment(SwingConstants.TOP);
		timesLbl.setBounds(((hospitalPanel.getWidth() - 140) / 2) - (hospitalPanel.getWidth() - 160) / 2 + 140,   checkTimersBtn.getY() + 25, hospitalPanel.getWidth() - 160, 65);
		hospitalPanel.add(timesLbl, 8);
		
		return hospitalPanel;
	}
	
	
	/**
	 * Method to construct the panel containing the Villain's lair
	 * @param villiansLair the Villain's lair this panel is constructed from
	 * @return JPanel, the constructed panel for villain's lair
	 */
	private JPanel buildVilliansLairPanel(VillainsLair villiansLair) {
		JPanel villainsLairPanel = new JPanel();
		
		villainsLairPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		villainsLairPanel.setBounds(230, 55, 290, 265);
		villainsLairPanel.setMinimumSize(new Dimension(290, 265));
		villainsLairPanel.setLayout(null);
		
		JLabel vLWelcomeMessageLbl = new JLabel(MessageFormat.format("Welcome to {0}", villiansLair.getName()));
		vLWelcomeMessageLbl.setHorizontalAlignment(SwingConstants.CENTER);
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
	
	
	/**
	 * moves the Components in hospital panel when the panel is resized
	 * @param hospitalPanel JPanel, hospital Panel
	 */
	private void hospitalResizeRules(JPanel hospitalPanel) {
		hospitalPanel.getComponent(0).setBounds(10, 10, hospitalPanel.getWidth(), 15);
		hospitalPanel.getComponent(2).setBounds(((hospitalPanel.getWidth() - 140) / 2) - (hospitalPanel.getWidth() - 160) / 2 + 140, 35, hospitalPanel.getWidth() - 160, 15);
		hospitalPanel.getComponent(3).setBounds(((hospitalPanel.getWidth() - 140) / 2) - (hospitalPanel.getWidth() - 160) / 2 + 140, hospitalPanel.getComponent(2).getY() + 15, hospitalPanel.getWidth() - 160, 25);
		hospitalPanel.getComponent(4).setBounds(((hospitalPanel.getWidth() - 140) / 2) - (hospitalPanel.getWidth() - 160) / 2 + 140,  (hospitalPanel.getComponent(3).getY() + 25) + (hospitalPanel.getHeight() - 230) / 3, hospitalPanel.getWidth() - 160, 15);
		hospitalPanel.getComponent(5).setBounds(((hospitalPanel.getWidth() - 140) / 2) - (hospitalPanel.getWidth() - 160) / 2 + 140, hospitalPanel.getComponent(4).getY() + 15, hospitalPanel.getWidth() - 160, 25);
		hospitalPanel.getComponent(6).setBounds(((hospitalPanel.getWidth() - 140) / 2) - (hospitalPanel.getWidth() - 160) / 2 + 140,  (hospitalPanel.getComponent(5).getY() + 25) + (hospitalPanel.getHeight() - 230) / 3, hospitalPanel.getWidth() - 160, 25);
		hospitalPanel.getComponent(7).setBounds(((hospitalPanel.getWidth() - 140) / 2) - (hospitalPanel.getWidth() - 160) / 2 + 140,   (hospitalPanel.getComponent(6).getY() + 25) + (hospitalPanel.getHeight() - 230) / 3, hospitalPanel.getWidth() - 160, 25);
		hospitalPanel.getComponent(8).setBounds(((hospitalPanel.getWidth() - 140) / 2) - (hospitalPanel.getWidth() - 160) / 2 + 140,   hospitalPanel.getComponent(7).getY() + 25, hospitalPanel.getWidth() - 160, 65);
	}
	
	
	private void powerUpDenResizeRules(JPanel powerUpDenPanel) {
		powerUpDenPanel.getComponent(0).setBounds(10, 10, powerUpDenPanel.getWidth(), 15);
		powerUpDenPanel.getComponent(2).setBounds(((powerUpDenPanel.getWidth() - 140) / 2) - (powerUpDenPanel.getWidth() - 160) / 2 + 140, 35, powerUpDenPanel.getWidth() - 160, 15);
		powerUpDenPanel.getComponent(3).setBounds(((powerUpDenPanel.getWidth() - 140) / 2) - (powerUpDenPanel.getWidth() - 160) / 2 + 140, powerUpDenPanel.getComponent(2).getY() + 15, powerUpDenPanel.getWidth() - 160, 25);
		powerUpDenPanel.getComponent(4).setBounds(((powerUpDenPanel.getWidth() - 140) / 2) - (powerUpDenPanel.getWidth() - 160) / 2 + 140,  (powerUpDenPanel.getComponent(3).getY() + 25) + (powerUpDenPanel.getHeight() - 230) / 3, powerUpDenPanel.getWidth() - 160, 15);
		powerUpDenPanel.getComponent(5).setBounds(((powerUpDenPanel.getWidth() - 140) / 2) - (powerUpDenPanel.getWidth() - 160) / 2 + 140, powerUpDenPanel.getComponent(4).getY() + 15, powerUpDenPanel.getWidth() - 160, 25);
		powerUpDenPanel.getComponent(6).setBounds(((powerUpDenPanel.getWidth() - 140) / 2) - (powerUpDenPanel.getWidth() - 160) / 2 + 140,  (powerUpDenPanel.getComponent(5).getY() + 25) + (powerUpDenPanel.getHeight() - 230) / 3, powerUpDenPanel.getWidth() - 160, 25);
		powerUpDenPanel.getComponent(7).setBounds(((powerUpDenPanel.getWidth() - 140) / 2) - (powerUpDenPanel.getWidth() - 160) / 2 + 140,   (powerUpDenPanel.getComponent(6).getY() + 25) + (powerUpDenPanel.getHeight() - 230) / 3, powerUpDenPanel.getWidth() - 160, 25);
		powerUpDenPanel.getComponent(8).setBounds(((powerUpDenPanel.getWidth() - 140) / 2) - (powerUpDenPanel.getWidth() - 160) / 2 + 140,   powerUpDenPanel.getComponent(7).getY() + 25, powerUpDenPanel.getWidth() - 160, 65);
	}
	
	
	private void shopResizeRules(JPanel shopLocationPanel) {
		
	}
	
	
	/**
	 * moves the Components in villains lair panel when the panel is resized
	 * @param villiansLairPanel JPanel, villainsLair Panel
	 */
	private void villiansLairResizeRules(JPanel villainsLairPanel) {
		villainsLairPanel.getComponent(0).setBounds(10, 10, villainsLairPanel.getWidth(), 15);
		villainsLairPanel.getComponent(3).setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140, 35, villainsLairPanel.getWidth() - 160, 25);
		villainsLairPanel.getComponent(4).setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140, (villainsLairPanel.getComponent(3).getY() + 25) + (villainsLairPanel.getHeight() - 175) / 3, villainsLairPanel.getWidth() - 160, 25);
		villainsLairPanel.getComponent(5).setBounds(((villainsLairPanel.getWidth() - 140) / 2) - (villainsLairPanel.getWidth() - 160) / 2 + 140, (villainsLairPanel.getComponent(4).getY() + 25) + (villainsLairPanel.getHeight() - 175) / 3, villainsLairPanel.getWidth() - 160, 25);
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
				welcomeLbl.setBounds(10, 10, frame.getWidth(), 15);
				rulerLbl.setBounds(10, 30, frame.getWidth(), 15);
			}
		});
		frame.setBounds(100, 100, 550, 370);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setMinimumSize(new Dimension(550, 370));
		
//		JLabel welcomeLbl = new JLabel("Welcome to ");
		welcomeLbl = new JLabel(MessageFormat.format("Welcome to {0}", city.getName()));
		welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLbl.setBounds(10, 10, 510, 15);
		welcomeLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(welcomeLbl);
		
//		JLabel rulerLbl = new JLabel("Currently Ruled By: ");
		rulerLbl = new JLabel(MessageFormat.format("Currently Ruled By: {0}", this.city.getVillain().getName()));
		rulerLbl.setHorizontalAlignment(SwingConstants.CENTER);
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
		
		
		//MAP PANEL
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



