package HeroGame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.sound.sampled.*;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import java.awt.CardLayout;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import java.beans.PropertyChangeListener;
import java.io.IOException;
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
	private Random rand = new Random();
	private JPanel hospitalPanel;
	private JPanel villainsLairPanel;
	private JPanel shopPanel;
	private JPanel homeBasePanel;
	private JPanel powerUpDenPanel;
	private Sound sounds = new Sound();
	
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
	 * Closes the screen when required
	 */
	public void closeScreen() {
		this.frame.dispose();
	}
	
	



	/**
	 * Gets type of panel to build from the location in that direction then builds the panel.
	 * @param currentLocation Location, location in the given compass direction
	 * @return JPanel, panel of type dictated by the location given.
	 */
	private JPanel buildLocationPanel(Location currentLocation, Direction direction) {
		LocationType type = currentLocation.getLocationType();
		switch(type) {
		case VILLIANSLAIR:
			return buildVilliansLairPanel((VillainsLair)currentLocation, direction);
			
		case SHOP:
			return buildShopPanel((Shop)currentLocation, direction);
			
		case POWERUPDEN:
			return buildPowerUpDenPanel((PowerUpDen)currentLocation, direction);
			
		case HOSPITAL:
			return buildHospitalPanel((Hospital)currentLocation, direction);
			
		case HOMEBASE:
			return buildHomeBasePanel((HomeBase)currentLocation, direction);
			
		}
		return null;
	}
	
	
	/**
	 * Method to construct a Shop Panel
	 * @param shop Shop, the shop this panel is being constructed from
	 * @return JPanel, the constructed panel for the shop
	 */
	private JPanel buildShopPanel(final Shop shop, Direction direction) {
		
		JPanel shopPanel = new JPanel();
		shopPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		shopPanel.setBounds(230, 55, 290, 265);
		frame.getContentPane().add(shopPanel);
		shopPanel.setMinimumSize(new Dimension(290, 265));
		shopPanel.setLayout(null);
		
		JLabel welcomeMessageLbl = new JLabel(MessageFormat.format("{0} Area: Welcome to {1}", direction.toString(), shop.getName()));
		welcomeMessageLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		welcomeMessageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeMessageLbl.setBounds(10, 10, shopPanel.getWidth(), 15);
		shopPanel.add(welcomeMessageLbl, 0);
		
		JLabel interiorPictureLbl = new JLabel("");
		interiorPictureLbl.setIcon(shop.getInteriorImage());
		interiorPictureLbl.setBounds(10, 35, 130, 190);
		interiorPictureLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		shopPanel.add(interiorPictureLbl, 1);
				
		JButton speakToVendorBtn = new JButton("Talk to the Vendor");
		speakToVendorBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		speakToVendorBtn.setBounds(((shopPanel.getWidth() - 140) / 2) - (shopPanel.getWidth() - 160) / 2 + 140,  35 + (shopPanel.getHeight() - 60) / 2, shopPanel.getWidth() - 160, 25);
		shopPanel.add(speakToVendorBtn, 2);	
		speakToVendorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchVendorScreen(currentDirection, city, shop, cityScreen);
			}
		});	
		this.shopPanel = shopPanel;
		return shopPanel;
	}
	
	
	/**
	 * Method to construct a Home Base Panel
	 * @param homeBase HomeBase, the HomeBase this panel is being constructed from
	 * @return JPanel, the constructed panel for the shop
	 */
	private JPanel buildHomeBasePanel(HomeBase homeBase, Direction direction) {
		
		JPanel homeBasePanel = new JPanel();
		homeBasePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		homeBasePanel.setBounds(230, 55, 290, 265);
		frame.getContentPane().add(homeBasePanel);
		homeBasePanel.setMinimumSize(new Dimension(290, 265));
		homeBasePanel.setLayout(null);
		homeBasePanel.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent componentEvent) {
				int n = rand.nextInt(20);
				switch(n) {
				case 0:
					int amount = rand.nextInt((int) city.getTeam().getMoney());
					city.getTeam().changeMoney(amount * -1);
					sounds.playOhNo();
					JOptionPane.showMessageDialog(frame, MessageFormat.format("The local kids have stolen your sneakers! And also your wallet which had ${0} in it!", amount), "Oh No!!!", JOptionPane.ERROR_MESSAGE);
					break;
				case 1:
					if(city.getTeam().getPowerUpsSize() > 0) {
						int index = rand.nextInt(city.getTeam().getPowerUpsSize());
						sounds.playOhNo();
						JOptionPane.showMessageDialog(frame, MessageFormat.format("The local kids have stolen your sneakers! And also your beloved {0}! Guess you will be going hungry tonight!", city.getTeam().getPowerUpType(index).toString()), "Oh No!!!", JOptionPane.ERROR_MESSAGE);
						PowerUp powerUp = city.getTeam().getPowerUps().get(index);
						city.getTeam().removePowerUp(index);
						powerUp.changeAmount(-1);
						if(powerUp.getAmount() == 0) {
							((JComboBox<PowerUp>) powerUpDenPanel.getComponent(5)).removeItemAt(index);
						}
						break;
					}
				case 2:
					if(city.getTeam().getHealingItemsSize() > 0) {
						int index = rand.nextInt(city.getTeam().getHealingItemsSize());
						sounds.playOhNo();
						JOptionPane.showMessageDialog(frame, MessageFormat.format("The local kids have stolen your sneakers! And also your beloved {0}! Guess you will be going thirsty tonight!", city.getTeam().getHealingItemType(index).toString()), "Oh No!!!", JOptionPane.ERROR_MESSAGE);
						HealingItem healingItem = city.getTeam().getHealingItems().get(index);
						city.getTeam().removeHealingItem(index);
						healingItem.changeAmount(-1);
						if(healingItem.getAmount() == 0) {
							((JComboBox<PowerUp>) hospitalPanel.getComponent(5)).removeItemAt(index);
						}
						break;
					}
				case 3:
					int gift = rand.nextInt(15);
					city.getTeam().changeMoney(gift);
					sounds.playWooHoo();
					JOptionPane.showMessageDialog(frame, MessageFormat.format("You found some poor fullas wallet! Better take the ${0} in it before you hand it in to the cops.", gift), "Oh shot!", JOptionPane.INFORMATION_MESSAGE);
					break;
				case 4:
					int puIndex = rand.nextInt(3);
					ArrayList<PowerUpType> powerUps = new ArrayList<PowerUpType>();
					powerUps.add(PowerUpType.CHEESE_ROLL);
					powerUps.add(PowerUpType.PAVLOVA);
					powerUps.add(PowerUpType.PINEAPPLE_LUMPS);
					PowerUpType powerUpType = powerUps.get(puIndex);
					city.getTeam().addPowerUp(powerUpType);
					sounds.playWooHoo();
					JOptionPane.showMessageDialog(frame, MessageFormat.format("You found a {0} just lying on the ground! 2 second rule cuz.", powerUpType), "Oh shot!", JOptionPane.INFORMATION_MESSAGE);
					break;
				case 5:
					int HIIndex = rand.nextInt(3);
					ArrayList<HealingItemType> healingItems = new ArrayList<HealingItemType>();
					healingItems.add(HealingItemType.DOUBLE_BROWN);
					healingItems.add(HealingItemType.LINDAUER);
					healingItems.add(HealingItemType.LION_RED);
					HealingItemType healingItemType = healingItems.get(HIIndex);
					city.getTeam().addHealingItem(healingItemType);
					sounds.playWooHoo();
					JOptionPane.showMessageDialog(frame, MessageFormat.format("Your mate Bazza bought you {0} on the way home! You Beauty!", healingItemType), "Oh shot!", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
		});
		
		JLabel welcomeMessageLbl = new JLabel(MessageFormat.format("{0} Area: Welcome to {1}", direction.toString(), homeBase.getName()));
		welcomeMessageLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		welcomeMessageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeMessageLbl.setBounds(10, 10, homeBasePanel.getWidth(), 15);
		homeBasePanel.add(welcomeMessageLbl, 0);
		
		JLabel interiorPictureLbl = new JLabel("");
		interiorPictureLbl.setIcon(homeBase.getInteriorImage());
		interiorPictureLbl.setBounds(10, 35, 130, 190);
		interiorPictureLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		homeBasePanel.add(interiorPictureLbl, 1);
		
		JLabel currentMapsLbl = new JLabel(MessageFormat.format("You have {0} maps", this.city.getTeam().getMaps()));
		currentMapsLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		currentMapsLbl.setBounds(150, 35, homeBasePanel.getWidth() - 160, 15);
		homeBasePanel.add(currentMapsLbl, 2);
		
		JButton useMapBtn = new JButton("Use a Map");
		useMapBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		useMapBtn.setBounds(150, currentMapsLbl.getY() + 15 + (homeBasePanel.getHeight() - 225) / 5, homeBasePanel.getWidth() - 160, 25);
		homeBasePanel.add(useMapBtn, 3);
		useMapBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(city.getTeam().getMaps() > 0) {
					if(!city.isMapped()) {
						northLocationLbl.setIcon(city.getNorthLocation().getIcon());
						southLocationLbl.setIcon(city.getSouthLocation().getIcon());
						eastLocationLbl.setIcon(city.getEastLocation().getIcon());
						westLocationLbl.setIcon(city.getWestLocation().getIcon());
						city.getTeam().changeMaps(-1);
						city.makeMapped();
					} else {
						sounds.playBadSound();
						JOptionPane.showMessageDialog(frame, "You've already Mapped this city!", "Woops...", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					sounds.playBadSound();
					JOptionPane.showMessageDialog(frame, "You've run out of maps!", "Not good...", JOptionPane.ERROR_MESSAGE);
				}
			}
		});	
		
		JLabel chooseHeroLbl = new JLabel("Please choose a hero:");
		chooseHeroLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		chooseHeroLbl.setBounds(150, useMapBtn.getY() + 25 + (homeBasePanel.getHeight() - 225) / 5, homeBasePanel.getWidth() - 160, 15);
		homeBasePanel.add(chooseHeroLbl, 4);
		
		JComboBox teamCombo = new JComboBox(this.city.getTeam().getMemberList().toArray());
		teamCombo.setFont(new Font("Tahoma", Font.BOLD, 13));
		teamCombo.setBounds(150, chooseHeroLbl.getY() + 15 + (homeBasePanel.getHeight() - 225) / 5, homeBasePanel.getWidth() - 160, 25);
		teamCombo.setSelectedItem(null);
		homeBasePanel.add(teamCombo, 5);
				
		JButton getStatusBtn = new JButton("Get Hero Status");
		getStatusBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		getStatusBtn.setBounds(150, teamCombo.getY() + 25 + (homeBasePanel.getHeight() - 225) / 5, homeBasePanel.getWidth() - 160, 25);
		homeBasePanel.add(getStatusBtn, 6);
		getStatusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hero hero = (Hero) ((JComboBox<Hero>) getPanel(currentDirection).getComponent(5)).getSelectedItem();
				if(hero != null) {
					((JLabel) ((JPanel) getPanel(currentDirection).getComponent(7)).getComponent(0)).setText(MessageFormat.format("Name: {0}", ((Hero) ((JComboBox<Hero>) getPanel(currentDirection).getComponent(5)).getSelectedItem()).getName()));
					((JLabel) ((JPanel) getPanel(currentDirection).getComponent(7)).getComponent(1)).setText(MessageFormat.format("Health: {0}", ((Hero) ((JComboBox<Hero>) getPanel(currentDirection).getComponent(5)).getSelectedItem()).getHealth()));
					((JLabel) ((JPanel) getPanel(currentDirection).getComponent(7)).getComponent(2)).setText(MessageFormat.format("Strength: {0}", ((Hero) ((JComboBox<Hero>) getPanel(currentDirection).getComponent(5)).getSelectedItem()).getStrength()));
					((JLabel) ((JPanel) getPanel(currentDirection).getComponent(7)).getComponent(3)).setText(MessageFormat.format("<html>Ability: {0}</td></html>", ((Hero) ((JComboBox<Hero>) getPanel(currentDirection).getComponent(5)).getSelectedItem()).getAbility()));
					PowerUp heroPowerUp = ((Hero) ((JComboBox<Hero>) getPanel(currentDirection).getComponent(5)).getSelectedItem()).getPowerUp();
					if (heroPowerUp != null) {
						((JLabel) ((JPanel) getPanel(currentDirection).getComponent(7)).getComponent(4)).setText(MessageFormat.format("<html>Current Power Up: {0}</html>", heroPowerUp.getLongDescription()));
					}
				} else {
					sounds.playBadSound();
					JOptionPane.showMessageDialog(frame, "You have failed to choose a hero, seriously...one job...", "Not good...", JOptionPane.ERROR_MESSAGE);
				}
			}
		});	
		
		JPanel heroStatusPanel = new JPanel();
		heroStatusPanel.setBounds(150, homeBasePanel.getHeight() - 85, homeBasePanel.getWidth() - 160, 75);
		heroStatusPanel.setLayout(null);
		heroStatusPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		homeBasePanel.add(heroStatusPanel, 7);	
		
		JLabel heroNameLbl = new JLabel("");
		heroNameLbl.setBounds(0, 0, heroStatusPanel.getWidth() / 3,15);
		heroStatusPanel.add(heroNameLbl, 0);
		
		JLabel heroHealthLbl = new JLabel("");
		heroHealthLbl.setBounds(0, 30, heroStatusPanel.getWidth() / 3, 15);
		heroStatusPanel.add(heroHealthLbl, 1);
		
		JLabel heroStrengthLbl = new JLabel("");
		heroStrengthLbl.setBounds(0, 15, heroStatusPanel.getWidth() / 3, 15);
		heroStatusPanel.add(heroStrengthLbl, 2);
		
		JLabel heroAbilityLbl = new JLabel("");
		heroAbilityLbl.setBounds(heroStatusPanel.getWidth() / 3, 0, 2 * heroStatusPanel.getWidth() / 3, 45);
		heroStatusPanel.add(heroAbilityLbl, 3);
		heroAbilityLbl.setVerticalTextPosition(0);
		
		JLabel heroPowerUpsLbl = new JLabel("");
		heroPowerUpsLbl.setBounds(0, 45, heroStatusPanel.getWidth(), 30);
		heroStatusPanel.add(heroPowerUpsLbl, 4);
		
		this.homeBasePanel = homeBasePanel;
		return homeBasePanel;
	}
	
	private JPanel buildPowerUpDenPanel(PowerUpDen powerUpDen, Direction direction) {
		
		JPanel powerUpDenPanel = new JPanel();
		powerUpDenPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		powerUpDenPanel.setBounds(230, 55, 290, 265);
		frame.getContentPane().add(powerUpDenPanel);
		powerUpDenPanel.setMinimumSize(new Dimension(290, 265));
		powerUpDenPanel.setLayout(null);
		
		JLabel welcomeMessageLbl = new JLabel(MessageFormat.format("{0} Area: Welcome to {1}", direction.toString(), powerUpDen.getName()));
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
		heroPickerComboBox.setSelectedItem(null);
		powerUpDenPanel.add(heroPickerComboBox, 3);
		
		JLabel itemChoiceLbl = new JLabel("Please Choose a Power Up:");
		itemChoiceLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		itemChoiceLbl.setBounds(((powerUpDenPanel.getWidth() - 140) / 2) - (powerUpDenPanel.getWidth() - 160) / 2 + 140,  (heroPickerComboBox.getY() + 25) + (powerUpDenPanel.getHeight() - 230) / 3, powerUpDenPanel.getWidth() - 160, 15);
		powerUpDenPanel.add(itemChoiceLbl, 4);
		
		JComboBox itemPickerComboBox = new JComboBox(this.city.getTeam().getPowerUps().toArray());
		itemPickerComboBox.setFont(new Font("Tahoma", Font.BOLD, 10));
		itemPickerComboBox.setBounds(((powerUpDenPanel.getWidth() - 140) / 2) - (powerUpDenPanel.getWidth() - 160) / 2 + 140, itemChoiceLbl.getY() + 15, powerUpDenPanel.getWidth() - 160, 25);
		powerUpDenPanel.add(itemPickerComboBox, 5);
		itemPickerComboBox.setSelectedItem(null);
		powerUpDenPanel.add(itemPickerComboBox, 5);
		
		JButton applyItemBtn = new JButton("Apply Power Up");
		applyItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		applyItemBtn.setBounds(((powerUpDenPanel.getWidth() - 140) / 2) - (powerUpDenPanel.getWidth() - 160) / 2 + 140,  (itemPickerComboBox.getY() + 25) + (powerUpDenPanel.getHeight() - 230) / 3, powerUpDenPanel.getWidth() - 160, 25);
		powerUpDenPanel.add(applyItemBtn, 6);	
		applyItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hero hero = (Hero) ((JComboBox<Hero>) getPanel(currentDirection).getComponent(3)).getSelectedItem();
				if(hero != null) {
					PowerUp powerUp = (PowerUp) ((JComboBox<PowerUp>) getPanel(currentDirection).getComponent(5)).getSelectedItem();
					if (powerUp == null) { //No power up selected
						sounds.playBadSound();
						JOptionPane.showMessageDialog(frame, MessageFormat.format(((PowerUpDen) city.getLocation(currentDirection)).getBadPowerUpMessage(), 
								hero.getName()), "Not good...", JOptionPane.ERROR_MESSAGE);
					}
					else if (hero.getPowerUp() != null) { //Hero already has a PowerUp
						JOptionPane.showMessageDialog(frame, hero.getName() + " you greedy beggar, you've had one", 
								"Not good...", JOptionPane.ERROR_MESSAGE);
					} else { //Hero can eat PowerUp
						String response = "<html><body style='width: 200px; padding: 5px;'>" + hero.eatPowerUp(powerUp)+"</html>";
						int powerUpIndex = ((JComboBox<PowerUp>) getPanel(currentDirection).getComponent(5)).getSelectedIndex();
						powerUp.changeAmount(-1);
						if (powerUp.getAmount() == 0) {//Last of this type has been consumed
							city.getTeam().removePowerUp(powerUpIndex);
							((JComboBox<Hero>) getPanel(currentDirection).getComponent(5)).removeItem(powerUp);//Remove from ComboBox
						}
						sounds.playMunching();
						JOptionPane.showMessageDialog(frame, response, "Well Done!", JOptionPane.INFORMATION_MESSAGE);
					} 
				} else {
					sounds.playBadSound();
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
		
		this.powerUpDenPanel = powerUpDenPanel;
		return powerUpDenPanel;
	}
	

	/**
	 * Method to construct a Hospital Panel
	 * @param hospital Hospital, the hospital this panel is being constructed from
	 * @return JPanel, the constructed panel for the hospital
	 */
	private JPanel buildHospitalPanel(Hospital hospital, Direction direction) {
		
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
		
		JLabel welcomeMessageLbl = new JLabel(MessageFormat.format("{0} Area: Welcome to {1}", direction.toString(), hospital.getName()));
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
					if(healingItem == null) {//No HealingItem selected
						sounds.playBadSound();
						JOptionPane.showMessageDialog(frame, MessageFormat.format(((Hospital) city.getLocation(currentDirection)).getBadHealingItemMessage(), 
								hero.getName()), "Not good...", JOptionPane.ERROR_MESSAGE);
					} else if (hero.checkHealingItemTime(city.getTeam().getTime()) > 0) {//Hero already has a healing item on the go
						JOptionPane.showMessageDialog(frame, "Oi " + hero.getName() + " other people are thirsty too ya know!", 
								"Not good...", JOptionPane.ERROR_MESSAGE);
						
					} else { //Hero can consume HealingItem
						double drinkTime = city.getTeam().getTime();
						String response = "<html><body style='width: 200px; padding: 5px;'>" + hero.drinkHealingItem(healingItem, drinkTime) + "</html>";
						int healingItemIndex = ((JComboBox<HealingItem>) getPanel(currentDirection).getComponent(5)).getSelectedIndex();
						healingItem.changeAmount(-1);
						if (healingItem.getAmount() == 0) { //Last of this type has been consumed
							city.getTeam().removeHealingItem(healingItemIndex);
							((JComboBox<Hero>) getPanel(currentDirection).getComponent(5)).removeItemAt(healingItemIndex);//Remove from ComboBox
						}
						sounds.playOpenCan();
						JOptionPane.showMessageDialog(frame, response, "Well Done!", JOptionPane.INFORMATION_MESSAGE);
					} 
				} else {
					sounds.playBadSound();
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
		
		this.hospitalPanel = hospitalPanel;
		return hospitalPanel;
	}
	
	
	/**
	 * Method to construct the panel containing the Villain's lair
	 * @param villiansLair the Villain's lair this panel is constructed from
	 * @return JPanel, the constructed panel for villain's lair
	 */
	private JPanel buildVilliansLairPanel(VillainsLair villiansLair, Direction direction) {
		JPanel villainsLairPanel = new JPanel();
		
		villainsLairPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		villainsLairPanel.setBounds(230, 55, 290, 265);
		villainsLairPanel.setMinimumSize(new Dimension(290, 265));
		villainsLairPanel.setLayout(null);
		
		JLabel vLWelcomeMessageLbl = new JLabel(MessageFormat.format("{0} Area: Welcome to {1}", direction.toString(), villiansLair.getName()));
		vLWelcomeMessageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		vLWelcomeMessageLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		vLWelcomeMessageLbl.setBounds(10, 10, villainsLairPanel.getWidth(), 15);
		villainsLairPanel.add(vLWelcomeMessageLbl, 0);
		
		JLabel vLVillainPictureLbl = new JLabel("");
		vLVillainPictureLbl.setIcon(villiansLair.getVillainImage());
		vLVillainPictureLbl.setBounds(10, 35, 130, 190);
		vLVillainPictureLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		villainsLairPanel.add(vLVillainPictureLbl, 1);
		
		
		JLabel vLVillainNameLbl = new JLabel(MessageFormat.format("<html><center>{0}</html>", this.city.getVillain().getName()));
		vLVillainNameLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		vLVillainNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		vLVillainNameLbl.setBounds(10, 235, 130, 30);
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
		
		this.villainsLairPanel = villainsLairPanel;
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
		homeBasePanel.getComponent(0).setBounds(10, 10, homeBasePanel.getWidth(), 15);
		homeBasePanel.getComponent(2).setBounds(150, 35, homeBasePanel.getWidth() - 160, 15);
		homeBasePanel.getComponent(3).setBounds(150, homeBasePanel.getComponent(2).getY() + 15 + (homeBasePanel.getHeight() - 225) / 5, homeBasePanel.getWidth() - 160, 25);
		homeBasePanel.getComponent(4).setBounds(150, homeBasePanel.getComponent(3).getY() + 25 + (homeBasePanel.getHeight() - 225) / 5, homeBasePanel.getWidth() - 160, 15);
		homeBasePanel.getComponent(5).setBounds(150, homeBasePanel.getComponent(4).getY() + 15 + (homeBasePanel.getHeight() - 225) / 5, homeBasePanel.getWidth() - 160, 25);
		homeBasePanel.getComponent(6).setBounds(150, homeBasePanel.getComponent(5).getY() + 25 + (homeBasePanel.getHeight() - 225) / 5, homeBasePanel.getWidth() - 160, 25);
		homeBasePanel.getComponent(7).setBounds(150, homeBasePanel.getHeight() - 85, homeBasePanel.getWidth() - 160, 75);
		((JPanel) homeBasePanel.getComponent(7)).getComponent(0).setBounds(0, 0, ((JPanel) homeBasePanel.getComponent(7)).getWidth() / 3,15);
		((JPanel) homeBasePanel.getComponent(7)).getComponent(1).setBounds(0, 30, ((JPanel) homeBasePanel.getComponent(7)).getWidth() / 3, 15);
		((JPanel) homeBasePanel.getComponent(7)).getComponent(2).setBounds(0, 15, ((JPanel) homeBasePanel.getComponent(7)).getWidth() / 3, 15);
		((JPanel) homeBasePanel.getComponent(7)).getComponent(3).setBounds(((JPanel) homeBasePanel.getComponent(7)).getWidth() / 3, 0, 2 * ((JPanel) homeBasePanel.getComponent(7)).getWidth() / 3, 45);
		((JPanel) homeBasePanel.getComponent(7)).getComponent(4).setBounds(0, 45, ((JPanel) homeBasePanel.getComponent(7)).getWidth(), 30);
		
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
	
	
	private void shopResizeRules(JPanel shopPanel) {
		shopPanel.getComponent(2).setBounds(((shopPanel.getWidth() - 140) / 2) - (shopPanel.getWidth() - 160) / 2 + 140,  35 + (shopPanel.getHeight() - 60) / 2, shopPanel.getWidth() - 160, 25);
		shopPanel.getComponent(0).setBounds(10, 10, shopPanel.getWidth(), 15);
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
				sounds.playMarching();
			} else {
				if(currentDirection == Direction.SOUTH) {
					southLocationPanel.setVisible(false);
					centerLocationPanel.setVisible(true);
					currentDirection = Direction.CENTER;
					sounds.playMarching();
				} else {
					sounds.playBadSound();
					JOptionPane.showMessageDialog(frame, this.city.getBadDirectionMessage(), "Whoops...", JOptionPane.ERROR_MESSAGE);
				}
			}
			break;
			
		case SOUTH:
			if(currentDirection == Direction.CENTER) {
				centerLocationPanel.setVisible(false);
				southLocationPanel.setVisible(true);
				currentDirection = Direction.SOUTH;
				sounds.playMarching();
			} else {
				if(currentDirection == Direction.NORTH) {
					northLocationPanel.setVisible(false);
					centerLocationPanel.setVisible(true);
					currentDirection = Direction.CENTER;
					sounds.playMarching();
				} else {
					sounds.playBadSound();
					JOptionPane.showMessageDialog(frame, this.city.getBadDirectionMessage(), "Whoops...", JOptionPane.ERROR_MESSAGE);
				}
			}
			break;
			
		case EAST:
			if(currentDirection == Direction.CENTER) {
				centerLocationPanel.setVisible(false);
				eastLocationPanel.setVisible(true);
				currentDirection = Direction.EAST;
				sounds.playMarching();
			} else {
				if(currentDirection == Direction.WEST) {
					westLocationPanel.setVisible(false);
					centerLocationPanel.setVisible(true);
					currentDirection = Direction.CENTER;
					sounds.playMarching();
				} else {
					sounds.playBadSound();
					JOptionPane.showMessageDialog(frame, this.city.getBadDirectionMessage(), "Whoops...", JOptionPane.ERROR_MESSAGE);
				}
			}
			break;
			
		case WEST:
			if(currentDirection == Direction.CENTER) {
				centerLocationPanel.setVisible(false);
				westLocationPanel.setVisible(true);
				currentDirection = Direction.WEST;
				sounds.playMarching();
			} else {
				if(currentDirection == Direction.EAST) {
					eastLocationPanel.setVisible(false);
					centerLocationPanel.setVisible(true);
					currentDirection = Direction.CENTER;
					sounds.playMarching();
				} else {
					sounds.playBadSound();
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
		frame.setMinimumSize(new Dimension(780, 385));
		
		welcomeLbl = new JLabel(MessageFormat.format("Welcome to {0}", city.getName()));
		welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLbl.setBounds(10, 10, 510, 15);
		welcomeLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(welcomeLbl);
		
		rulerLbl = new JLabel(MessageFormat.format("Currently Ruled By: {0}", this.city.getVillain().getName()));
		rulerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		rulerLbl.setBounds(10, 30, 510, 15);
		rulerLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(rulerLbl);
		
		northType = this.city.getNorthLocation().getLocationType();
		northLocationPanel = buildLocationPanel(this.city.getNorthLocation(), Direction.NORTH);
		frame.getContentPane().add(northLocationPanel);
		northLocationPanel.setVisible(false);
		
		southType = this.city.getSouthLocation().getLocationType();
		southLocationPanel = buildLocationPanel(this.city.getSouthLocation(), Direction.SOUTH);
		frame.getContentPane().add(southLocationPanel);
		southLocationPanel.setVisible(false);
		
		eastType = this.city.getEastLocation().getLocationType();
		eastLocationPanel = buildLocationPanel(this.city.getEastLocation(), Direction.EAST);
		frame.getContentPane().add(eastLocationPanel);
		eastLocationPanel.setVisible(false);
		
		westType = this.city.getWestLocation().getLocationType();
		westLocationPanel = buildLocationPanel(this.city.getWestLocation(), Direction.WEST);
		frame.getContentPane().add(westLocationPanel);
		westLocationPanel.setVisible(false);
		
		centerLocationPanel = buildLocationPanel(this.city.getCenterLocation(), Direction.CENTER);
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
		
		if(city.getTeam().checkPresent(HeroType.SURVEYOR) || city.isMapped()) {//Surveyor knows city map
			northLocationLbl.setIcon(city.getNorthLocation().getIcon());
			southLocationLbl.setIcon(city.getSouthLocation().getIcon());
			eastLocationLbl.setIcon(city.getEastLocation().getIcon());
			westLocationLbl.setIcon(city.getWestLocation().getIcon());
			city.makeMapped();
		}
		
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




	//***************************Test Code
	public static void main(String[] args) {
		GameManager manager = new GameManager(); 
		Hero hero = new Hero("Jim", HeroType.ALL_BLACK);
		Team team = new Team("Team");
		team.addPowerUp(PowerUpType.CHEESE_ROLL);
		team.addPowerUp(PowerUpType.PAVLOVA);
		team.addPowerUp(PowerUpType.PINEAPPLE_LUMPS);
		team.addHealingItem(HealingItemType.DOUBLE_BROWN);
		team.addHealingItem(HealingItemType.LINDAUER);
		team.addHealingItem(HealingItemType.LION_RED);
		team.addMember(hero);
		team.addMember(new Hero("Tim", HeroType.FIREFIGHTER));
		team.addMember(new Hero("Gav", HeroType.FOSTER_MUM));
		team.addMember(new Hero("Stacey", HeroType.NURSE));
		team.addMember(new Hero("dan", HeroType.RETURNED_SERVICEMAN));
		team.addMember(new Hero("ladjn", HeroType.SURVEYOR));
		City newerCity = new City("Springfield", Villain.AUSSIECRICKETER, team);
		CityScreen newCityScreen = new CityScreen(newerCity, Direction.CENTER, manager);
//		newCityScreen.playBadSound();
	}
}
