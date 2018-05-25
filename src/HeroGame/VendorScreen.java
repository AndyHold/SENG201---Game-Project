package HeroGame;

import java.awt.EventQueue;
import javax.sound.sampled.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Container;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.awt.Dimension;

/**
 * VendorScreen Class for Heroes & Villains Game. Sets up and displays Screen where items are purchased.
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class VendorScreen {

	private JFrame frame;
	private JPanel mainMenuPanel;
	private JPanel buyMapPanel;
	private JPanel buyPowerUpPanel;
	private JPanel buyHealthItemPanel;
	private JPanel showInventoryPanel;
	private JPanel showPricesPanel;
	private Team team;
	private City city;
	private Shop shop;
	private JLabel currentFundsMAPLbl;
	private JLabel currentFundsPULbl;
	private JLabel currentFundsHILbl; 
	private JLabel currentFundsInventoryLbl;
	private GameManager manager;
	private Direction direction;
	private VendorScreen vendor = this;
	private JLabel vendorImageLbl;
	private JLabel numberMapsLbl;
	private JLabel cheeseRollPULbl;
	private JPanel buyCheeseRollPanel;
	private JButton buyPavlovaBtn;
	private JPanel buyPavlovaPanel;
	private JLabel cheeseRollPricePULbl;
	private JLabel yourPowerUpItemsLbl;
	private JLabel yourHealingItemsLbl;
	private JButton mainMenuInventoryBtn;
	private JLabel welcomeMessageLbl;
	private JButton buyMapBtn;
	private JButton buyPowerUpBtn;
	private JButton buyHealingItemBtn;
	private JButton showPricesBtn;
	private JButton showInventoryBtn;
	private JButton nothingThanksBtn;
	private JButton mainMenuPUBtn;
	private JLabel pavlovaPULbl;
	private JLabel pavlovaPricePULbl;
	private JButton buyCheeseRollBtn;
	private JPanel buyPineappleLumpsPanel;
	private JLabel pineappleLumpsPULbl;
	private JLabel pineappleLumpsPricePULbl;
	private JButton buyPineappleLumpsBtn;
	private JPanel buyDoubleBrownPanel;
	private JLabel doubleBrownHILbl;
	private JLabel doubleBrownPriceHILbl;
	private JButton buyDoubleBrownBtn;
	private JPanel buyLionRedPanel;
	private JLabel lionRedHILbl;
	private JLabel lionRedPriceHILbl;
	private JButton buyLionRedBtn;
	private JPanel buyLindauerPanel;
	private JLabel lindauerHILbl;
	private JLabel lindauerPriceHILbl;
	private JButton buyLindauerBtn;
	private JButton mainMenuHIBtn;
	private JLabel inventoryWelcomeLbl;
	private JLabel mapsLbl;
	private JLabel mapsPriceLbl;
	private JLabel pavlovaLbl;
	private JLabel pavlovaPriceLbl;
	private JLabel cheeseRollLbl;
	private JLabel cheeseRollPriceLbl;
	private JLabel pineappleLumpsLbl;
	private JLabel pineappleLumpsPriceLbl;
	private JLabel doubleBrownLbl;
	private JLabel doubleBrownPrice;
	private JLabel lionRedLbl;
	private JLabel lionRedPriceLbl;
	private JLabel lindauerLbl;
	private JLabel lindauerPriceLbl;
	private JButton mainMenuPricesBtn;
	private JLabel mapCostLbl;
	private JButton purchaseMapBtn;
	private JButton mainMenuMAPBtn;
	private JPanel lionRedPricePanel;
	private JPanel lindauerPricePanel;
	private JPanel doubleBrownPricePanel;
	private JPanel pineappleLumpsPricePanel;
	private JPanel cheeseRollPricePanel;
	private JPanel pavlovaPricePanel;
	private JPanel mapsPricePanel;
	private Sound sounds = new Sound();

	/**
	 * Constructor for new VendorScreen, the screen where items are pruchased from the shop
	 * @param manager a GameManager the state manager for the game
	 * @param direction a Direction, the direction on the map in which the shop is located
	 * @param city a City the City currently being played
	 * @param shop a Shop the Shop class containing the functiuon used in this screen
	 */
	public VendorScreen(GameManager manager, Direction direction, City city, Shop shop) {
		this.manager = manager;
		this.direction = direction;
		this.city = city;
		this.shop = shop;
		this.team = city.getTeam();
		initialize();
		this.frame.setVisible(true);
	}
	
	



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//----------------------Creates the frame-----------------------
		frame = new JFrame();
		frame.setTitle("NZ Clean Up - " + shop.getName());
		frame.getContentPane().addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				int x = frame.getWidth() / 2 - 150;
				int height = frame.getHeight() - 250;
				vendorImageLbl.setBounds(x, 10, 300, 180);
				mainMenuPanel.setBounds(x, 200, 300, height);
				buyMapPanel.setBounds(x, 200, 300, height);
				buyPowerUpPanel.setBounds(x, 200, 300, height);
				buyHealthItemPanel.setBounds(x, 200, 300, height);
				showInventoryPanel.setBounds(x, 200, 300, height);
				showPricesPanel.setBounds(x, 200, 300, height);
			}
		});
		frame.setMinimumSize(new Dimension(550, 500));
		frame.setBounds(100, 100, 550, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//-------------------------------------------------------------
		
		vendorImageLbl = new JLabel(""); // image of the vendor
		vendorImageLbl.setIcon(new ImageIcon(VendorScreen.class.getResource("/HeroGame/Images/vendor.jpg")));
		vendorImageLbl.setBounds(125, 10, 300, 180);
		frame.getContentPane().add(vendorImageLbl);
		
		//----------------------Creates the prices panel-----------------------
		showPricesPanel = new JPanel();
		showPricesPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) { // updates sizes and positions of panels when fram resized
				mapsPricePanel.setBounds(10, 35, 280, 15);
				pavlovaPricePanel.setBounds(10, mapsPricePanel.getY() + 15 + (showPricesPanel.getHeight() - 170) / 7, 280, 15);
				cheeseRollPricePanel.setBounds(10, pavlovaPricePanel.getY() + 15 + (showPricesPanel.getHeight() - 170) / 7, 280, 15);
				pineappleLumpsPricePanel.setBounds(10, cheeseRollPricePanel.getY() + 15 + (showPricesPanel.getHeight() - 170) / 7, 280, 15);
				doubleBrownPricePanel.setBounds(10, pineappleLumpsPricePanel.getY() + 15 + (showPricesPanel.getHeight() - 170) / 7, 280, 15);
				lionRedPricePanel.setBounds(10, doubleBrownPricePanel.getY() + 15 + (showPricesPanel.getHeight() - 170) / 7, 280, 15);
				lindauerPricePanel.setBounds(10, lionRedPricePanel.getY() + 15 + (showPricesPanel.getHeight() - 170) / 7, 280, 15);
				mainMenuPricesBtn.setBounds(10, showPricesPanel.getHeight() - 35, 280, 25);
			}
		});
		showPricesPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		showPricesPanel.setBounds(125, 200, 300, 233);
		frame.getContentPane().add(showPricesPanel);
		showPricesPanel.setLayout(null);
		showPricesPanel.setVisible(false);
		
		inventoryWelcomeLbl = new JLabel("Shop Inventory:");
		inventoryWelcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		inventoryWelcomeLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		inventoryWelcomeLbl.setBounds(10, 10, 280, 15);
		showPricesPanel.add(inventoryWelcomeLbl);
		
		mapsPricePanel = new JPanel(); // contains the price and name of maps
		mapsPricePanel.setBounds(10, 35, 280, 15);
		showPricesPanel.add(mapsPricePanel);
		mapsPricePanel.setLayout(null);
		
		mapsLbl = new JLabel("Maps");
		mapsLbl.setBounds(0, 0, 130, 15);
		mapsPricePanel.add(mapsLbl);
		mapsLbl.setHorizontalAlignment(SwingConstants.LEFT);
		mapsLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		mapsPriceLbl = new JLabel("$5.00");
		mapsPriceLbl.setBounds(150, 0, 130, 15);
		mapsPricePanel.add(mapsPriceLbl);
		mapsPriceLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		mapsPriceLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		
		pavlovaPricePanel = new JPanel(); // contains the price and name of pavlova
		pavlovaPricePanel.setBounds(10, 50, 280, 15);
		showPricesPanel.add(pavlovaPricePanel);
		pavlovaPricePanel.setLayout(null);
		
		pavlovaLbl = new JLabel("Pavlova");
		pavlovaLbl.setBounds(0, 0, 130, 15);
		pavlovaPricePanel.add(pavlovaLbl);
		pavlovaLbl.setToolTipText(MessageFormat.format("<html>{0}<br>{1}</html>", PowerUpType.PAVLOVA.getLongDescription(), PowerUpType.PAVLOVA.getEffect()));
		pavlovaLbl.setHorizontalAlignment(SwingConstants.LEFT);
		pavlovaLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		pavlovaPriceLbl = new JLabel(MessageFormat.format("${0}", PowerUpType.PAVLOVA.getCost()));
		pavlovaPriceLbl.setBounds(150, 0, 130, 15);
		pavlovaPricePanel.add(pavlovaPriceLbl);
		pavlovaPriceLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		pavlovaPriceLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		cheeseRollPricePanel = new JPanel();// contains the price and name of cheese roll
		cheeseRollPricePanel.setBounds(10, 65, 280, 15);
		showPricesPanel.add(cheeseRollPricePanel);
		cheeseRollPricePanel.setLayout(null);
		
		cheeseRollLbl = new JLabel("Cheese Roll");
		cheeseRollLbl.setBounds(0, 0, 130, 15);
		cheeseRollPricePanel.add(cheeseRollLbl);
		cheeseRollLbl.setToolTipText(MessageFormat.format("<html>{0}<br>{1}</html>", PowerUpType.CHEESE_ROLL.getLongDescription(), PowerUpType.CHEESE_ROLL.getEffect()));
		cheeseRollLbl.setHorizontalAlignment(SwingConstants.LEFT);
		cheeseRollLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		cheeseRollPriceLbl = new JLabel(MessageFormat.format("${0}", PowerUpType.CHEESE_ROLL.getCost()));
		cheeseRollPriceLbl.setBounds(150, 0, 130, 15);
		cheeseRollPricePanel.add(cheeseRollPriceLbl);
		cheeseRollPriceLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		cheeseRollPriceLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		pineappleLumpsPricePanel = new JPanel(); // contains the price and name of pineapple lumps
		pineappleLumpsPricePanel.setBounds(10, 80, 280, 15);
		showPricesPanel.add(pineappleLumpsPricePanel);
		pineappleLumpsPricePanel.setLayout(null);
		
		pineappleLumpsLbl = new JLabel("Pineapple Lumps");
		pineappleLumpsLbl.setBounds(0, 0, 130, 15);
		pineappleLumpsPricePanel.add(pineappleLumpsLbl);
		pineappleLumpsLbl.setToolTipText(MessageFormat.format("<html>{0}<br>{1}</html>", PowerUpType.PINEAPPLE_LUMPS.getLongDescription(), PowerUpType.PINEAPPLE_LUMPS.getEffect()));
		pineappleLumpsLbl.setHorizontalAlignment(SwingConstants.LEFT);
		pineappleLumpsLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		pineappleLumpsPriceLbl = new JLabel(MessageFormat.format("${0}", PowerUpType.PINEAPPLE_LUMPS.getCost()));
		pineappleLumpsPriceLbl.setBounds(150, 0, 130, 15);
		pineappleLumpsPricePanel.add(pineappleLumpsPriceLbl);
		pineappleLumpsPriceLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		pineappleLumpsPriceLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		doubleBrownPricePanel = new JPanel(); // contains the price and name of double brown
		doubleBrownPricePanel.setBounds(10, 95, 280, 15);
		showPricesPanel.add(doubleBrownPricePanel);
		doubleBrownPricePanel.setLayout(null);
		
		doubleBrownLbl = new JLabel("Double Brown");
		doubleBrownLbl.setBounds(0, 0, 130, 15);
		doubleBrownPricePanel.add(doubleBrownLbl);
		doubleBrownLbl.setToolTipText(MessageFormat.format("<html>{0}<br>Heals {1} health in {2} seconds</html>", HealingItemType.DOUBLE_BROWN.getLongDescription(), HealingItemType.DOUBLE_BROWN.getHealthValue(), HealingItemType.DOUBLE_BROWN.getApplyTime()));
		doubleBrownLbl.setHorizontalAlignment(SwingConstants.LEFT);
		doubleBrownLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		doubleBrownPrice = new JLabel(MessageFormat.format("${0}", HealingItemType.DOUBLE_BROWN.getCost()));
		doubleBrownPrice.setBounds(150, 0, 130, 15);
		doubleBrownPricePanel.add(doubleBrownPrice);
		doubleBrownPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		doubleBrownPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lionRedPricePanel = new JPanel(); // contains the price and name of lion red
		lionRedPricePanel.setBounds(10, 110, 280, 15);
		showPricesPanel.add(lionRedPricePanel);
		lionRedPricePanel.setLayout(null);
		
		lionRedLbl = new JLabel("Lion Red");
		lionRedLbl.setBounds(0, 0, 130, 15);
		lionRedPricePanel.add(lionRedLbl);
		lionRedLbl.setToolTipText(MessageFormat.format("<html>{0}<br>Heals {1} health in {2} seconds</html>", HealingItemType.LION_RED.getLongDescription(), HealingItemType.LION_RED.getHealthValue(), HealingItemType.LION_RED.getApplyTime()));
		lionRedLbl.setHorizontalAlignment(SwingConstants.LEFT);
		lionRedLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lionRedPriceLbl = new JLabel(MessageFormat.format("${0}", HealingItemType.LION_RED.getCost()));
		lionRedPriceLbl.setBounds(150, 0, 130, 15);
		lionRedPricePanel.add(lionRedPriceLbl);
		lionRedPriceLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		lionRedPriceLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lindauerPricePanel = new JPanel();
		lindauerPricePanel.setBounds(10, 125, 280, 15);
		showPricesPanel.add(lindauerPricePanel);
		lindauerPricePanel.setLayout(null);
		
		lindauerLbl = new JLabel("Lindauer"); // contains the price and name of lindauer
		lindauerLbl.setBounds(0, 0, 130, 15);
		lindauerPricePanel.add(lindauerLbl);
		lindauerLbl.setToolTipText(MessageFormat.format("<html>{0}<br>Heals {1} health in {2} seconds</html>", HealingItemType.LINDAUER.getLongDescription(), HealingItemType.LINDAUER.getHealthValue(), HealingItemType.LINDAUER.getApplyTime()));
		lindauerLbl.setHorizontalAlignment(SwingConstants.LEFT);
		lindauerLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lindauerPriceLbl = new JLabel(MessageFormat.format("${0}", HealingItemType.LINDAUER.getCost()));
		lindauerPriceLbl.setBounds(150, 0, 130, 15);
		lindauerPricePanel.add(lindauerPriceLbl);
		lindauerPriceLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		lindauerPriceLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		mainMenuPricesBtn = new JButton("Choose Another Option"); // Button to return to the main menu
		mainMenuPricesBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainMenuPricesBtn.setBounds(10, 170, 280, 25);
		showPricesPanel.add(mainMenuPricesBtn);
		mainMenuPricesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainMenuPanel.setVisible(true);
				showPricesPanel.setVisible(false);
			}
		});
		
		// --------------------------Creates Main Menu Panel------------
		mainMenuPanel = new JPanel();
		mainMenuPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) { // updates sizes and positions of panels when fram resized
				buyPowerUpBtn.setBounds(10, buyMapBtn.getY() + 25 + (showPricesPanel.getHeight() - 195) / 5, 280, 25);
				buyHealingItemBtn.setBounds(10, buyPowerUpBtn.getY() + 25 + (showPricesPanel.getHeight() - 195) / 5, 280, 25);
				showPricesBtn.setBounds(10, buyHealingItemBtn.getY() + 25 + (showPricesPanel.getHeight() - 195) / 5, 280, 25);
				showInventoryBtn.setBounds(10, showPricesBtn.getY() + 25 + (showPricesPanel.getHeight() - 195) / 5, 280, 25);
				nothingThanksBtn.setBounds(10, mainMenuPanel.getHeight() - 35, 280, 25);
			}
		});
		mainMenuPanel.setVisible(true);
		
		mainMenuPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainMenuPanel.setBounds(125, 200, 300, 233);
		frame.getContentPane().add(mainMenuPanel);
		mainMenuPanel.setLayout(null);
		
		welcomeMessageLbl = new JLabel("Hi I'm Gary, how can I help you?");
		welcomeMessageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeMessageLbl.setBounds(10, 10, 280, 15);
		mainMenuPanel.add(welcomeMessageLbl);
		welcomeMessageLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		buyMapBtn = new JButton("Purchase Map"); // goes to buy map panel
		buyMapBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		buyMapBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainMenuPanel.setVisible(false);
				buyMapPanel.setVisible(true);
			}
		});
		buyMapBtn.setBounds(10, 35, 280, 25);
		mainMenuPanel.add(buyMapBtn);
		
		buyPowerUpBtn = new JButton("Purchase Power Up"); // goes to buy power up panel
		buyPowerUpBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		buyPowerUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainMenuPanel.setVisible(false);
				buyPowerUpPanel.setVisible(true);
			}
		});
		buyPowerUpBtn.setBounds(10, 65, 280, 25);
		mainMenuPanel.add(buyPowerUpBtn);
		
		buyHealingItemBtn = new JButton("Purchase Healing Item"); // goes to buy healing item panel
		buyHealingItemBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		buyHealingItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainMenuPanel.setVisible(false);
				buyHealthItemPanel.setVisible(true);
			}
		});
		buyHealingItemBtn.setBounds(10, 100, 280, 25);
		mainMenuPanel.add(buyHealingItemBtn);
		
		showPricesBtn = new JButton("Show All Prices"); // goes to show prices panel
		showPricesBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		showPricesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainMenuPanel.setVisible(false);
				showPricesPanel.setVisible(true);
			}
		});
		showPricesBtn.setBounds(10, 134, 280, 25);
		mainMenuPanel.add(showPricesBtn);
		
		showInventoryBtn = new JButton("Show Current Inventory"); // goes to show inventory panel
		showInventoryBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		showInventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainMenuPanel.setVisible(false);
				showInventoryPanel.setVisible(true);
			}
		});
		showInventoryBtn.setBounds(10, 168, 280, 25);
		mainMenuPanel.add(showInventoryBtn);
		
		nothingThanksBtn = new JButton("Nothing Thanks"); // exits the shop
		nothingThanksBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manager.closeVendorScreen(vendor, city, direction);
			}
		});
		nothingThanksBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		nothingThanksBtn.setBounds(10, 204, 280, 25);
		mainMenuPanel.add(nothingThanksBtn);
		
		// -----------------------Creates Buy Health Item Panel---------------
		buyHealthItemPanel = new JPanel(); 
		buyHealthItemPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) { // updates current funds when shown
				currentFundsHILbl.setText(MessageFormat.format("You Have ${0}", team.getMoney()));
			}
			@Override
			public void componentResized(ComponentEvent e) { // updates sizes and positions of panels when fram resized
				buyDoubleBrownPanel.setBounds(10, 25 + (buyHealthItemPanel.getHeight() - 135) / 4, 280, 25);
				buyLionRedPanel.setBounds(10, buyDoubleBrownPanel.getY() + 25 + (buyHealthItemPanel.getHeight() - 135) / 4, 280, 25);
				buyLindauerPanel.setBounds(10, buyLionRedPanel.getY() + 25 + (buyHealthItemPanel.getHeight() - 135) / 4, 280, 25);
				mainMenuHIBtn.setBounds(10, buyHealthItemPanel.getHeight() - 35, 280, 25);
			}
		});
		buyHealthItemPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		buyHealthItemPanel.setLayout(null);
		buyHealthItemPanel.setBounds(125, 200, 300, 233);
		frame.getContentPane().add(buyHealthItemPanel);
		buyHealthItemPanel.setVisible(false);
		
		currentFundsHILbl = new JLabel(MessageFormat.format("You Have ${0}", team.getMoney()));
		currentFundsHILbl.setBounds(10, 10, 280, 15); // displays current funds
		buyHealthItemPanel.add(currentFundsHILbl);
		currentFundsHILbl.setHorizontalAlignment(SwingConstants.CENTER);
		currentFundsHILbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		buyDoubleBrownPanel = new JPanel(); // displays double brown with a buy button
		buyDoubleBrownPanel.setBounds(10, 35, 280, 25);
		buyHealthItemPanel.add(buyDoubleBrownPanel);
		buyDoubleBrownPanel.setLayout(null);
		
		doubleBrownHILbl = new JLabel("Double Brown");
		doubleBrownHILbl.setToolTipText(MessageFormat.format("<html>{0}<br>Heals {1} health in {2} seconds</html>", HealingItemType.DOUBLE_BROWN.getLongDescription(), HealingItemType.DOUBLE_BROWN.getHealthValue(), HealingItemType.DOUBLE_BROWN.getApplyTime()));
		doubleBrownHILbl.setBounds(0, 0, 130, 25);
		buyDoubleBrownPanel.add(doubleBrownHILbl);
		doubleBrownHILbl.setHorizontalAlignment(SwingConstants.LEFT);
		doubleBrownHILbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		doubleBrownPriceHILbl = new JLabel(MessageFormat.format("${0}", HealingItemType.DOUBLE_BROWN.getCost()));
		doubleBrownPriceHILbl.setBounds(150, 0, 60, 25);
		buyDoubleBrownPanel.add(doubleBrownPriceHILbl);
		doubleBrownPriceHILbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		doubleBrownPriceHILbl.setHorizontalAlignment(SwingConstants.RIGHT);
		
		buyDoubleBrownBtn = new JButton("Buy"); 
		buyDoubleBrownBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		buyDoubleBrownBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {// checks if money is sufficient and adds item if so with transaction sound and updates current funds label
				if(team.getMoney() >= HealingItemType.DOUBLE_BROWN.getCost()) {
					team.addHealingItem(HealingItemType.DOUBLE_BROWN);
					team.changeMoney(HealingItemType.DOUBLE_BROWN.getCost() * -1);
					sounds.playTransaction();
					JOptionPane.showMessageDialog(frame, MessageFormat.format("You have successfully bought a {0}! Quick head to the local pub and get it down!", HealingItemType.DOUBLE_BROWN.toString()) , "Well Done!", JOptionPane.INFORMATION_MESSAGE);
					currentFundsHILbl.setText(MessageFormat.format("You Have ${0}", team.getMoney()));
				} else { // else displays error message
					sounds.playBadSound();
					JOptionPane.showMessageDialog(frame, shop.getMoneyError(), "Uh Oh!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buyDoubleBrownBtn.setBounds(210, 0, 70, 25);
		buyDoubleBrownPanel.add(buyDoubleBrownBtn);
		
		buyLionRedPanel = new JPanel(); // displays lion red with a buy button
		buyLionRedPanel.setBounds(10, 70, 280, 25);
		buyHealthItemPanel.add(buyLionRedPanel);
		buyLionRedPanel.setLayout(null);
		
		lionRedHILbl = new JLabel("Lion Red");
		lionRedHILbl.setToolTipText(MessageFormat.format("<html>{0}<br>Heals {1} health in {2} seconds</html>", HealingItemType.LION_RED.getLongDescription(), HealingItemType.LION_RED.getHealthValue(), HealingItemType.LION_RED.getApplyTime()));
		lionRedHILbl.setBounds(0, 0, 130, 25);
		buyLionRedPanel.add(lionRedHILbl);
		lionRedHILbl.setHorizontalAlignment(SwingConstants.LEFT);
		lionRedHILbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lionRedPriceHILbl = new JLabel(MessageFormat.format("${0}", HealingItemType.LION_RED.getCost()));
		lionRedPriceHILbl.setBounds(150, 0, 60, 25);
		buyLionRedPanel.add(lionRedPriceHILbl);
		lionRedPriceHILbl.setHorizontalAlignment(SwingConstants.RIGHT);
		lionRedPriceHILbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		buyLionRedBtn = new JButton("Buy");
		buyLionRedBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		buyLionRedBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// checks if money is sufficient and adds item if so with transaction sound and updates current funds label
				if(team.getMoney() >= HealingItemType.LION_RED.getCost()) {
					team.addHealingItem(HealingItemType.LION_RED);
					team.changeMoney(HealingItemType.LION_RED.getCost() * -1);
					sounds.playTransaction();
					JOptionPane.showMessageDialog(frame, MessageFormat.format("You have successfully bought a {0}! Quick head to the local pub and get it down!", HealingItemType.LION_RED.toString()) , "Well Done!", JOptionPane.INFORMATION_MESSAGE);
					currentFundsHILbl.setText(MessageFormat.format("You Have ${0}", team.getMoney()));
				} else { // else displays error message
					sounds.playBadSound();
					JOptionPane.showMessageDialog(frame, shop.getMoneyError(), "Uh Oh!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buyLionRedBtn.setBounds(210, 0, 70, 25);
		buyLionRedPanel.add(buyLionRedBtn);
		
		buyLindauerPanel = new JPanel(); // displays lindauer with a buy button
		buyLindauerPanel.setBounds(10, 105, 280, 25);
		buyHealthItemPanel.add(buyLindauerPanel);
		buyLindauerPanel.setLayout(null);
		
		lindauerHILbl = new JLabel("Lindauer");
		lindauerHILbl.setToolTipText(MessageFormat.format("<html>{0}<br>Heals {1} health in {2} seconds</html>", HealingItemType.LINDAUER.getLongDescription(), HealingItemType.LINDAUER.getHealthValue(), HealingItemType.LINDAUER.getApplyTime()));
		lindauerHILbl.setBounds(0, 0, 130, 25);
		buyLindauerPanel.add(lindauerHILbl);
		lindauerHILbl.setHorizontalAlignment(SwingConstants.LEFT);
		lindauerHILbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lindauerPriceHILbl = new JLabel(MessageFormat.format("${0}", HealingItemType.LINDAUER.getCost()));
		lindauerPriceHILbl.setBounds(150, 0, 60, 25);
		buyLindauerPanel.add(lindauerPriceHILbl);
		lindauerPriceHILbl.setHorizontalAlignment(SwingConstants.RIGHT);
		lindauerPriceHILbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		buyLindauerBtn = new JButton("Buy");
		buyLindauerBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		buyLindauerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // checks if money is sufficient and adds item if so with transaction sound and updates current funds label
				if(team.getMoney() >= HealingItemType.LINDAUER.getCost()) {
					team.addHealingItem(HealingItemType.LINDAUER);
					team.changeMoney(HealingItemType.LINDAUER.getCost() * -1);
					sounds.playTransaction();
					JOptionPane.showMessageDialog(frame, MessageFormat.format("You have successfully bought a {0}! Quick head to the local pub and get it down!", HealingItemType.LINDAUER.toString()) , "Well Done!", JOptionPane.INFORMATION_MESSAGE);
					currentFundsHILbl.setText(MessageFormat.format("You Have ${0}", team.getMoney()));
				} else { // else displays error message
					sounds.playBadSound();
					JOptionPane.showMessageDialog(frame, shop.getMoneyError(), "Uh Oh!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buyLindauerBtn.setBounds(210, 0, 70, 25);
		buyLindauerPanel.add(buyLindauerBtn);
		
		mainMenuHIBtn = new JButton("Choose Another Option");
		mainMenuHIBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainMenuHIBtn.setBounds(10, 170, 280, 25);
		buyHealthItemPanel.add(mainMenuHIBtn);
		mainMenuHIBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainMenuPanel.setVisible(true);
				buyHealthItemPanel.setVisible(false);
			}
		});
		
		// -------------------Creates Buy Power Up Panel--------------------
		buyPowerUpPanel = new JPanel();
		buyPowerUpPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) { // updates current funds whenever it is displayed
				currentFundsPULbl.setText(MessageFormat.format("You Have ${0}", team.getMoney()));
			}
			@Override
			public void componentResized(ComponentEvent e) { // updates sizes and positions of panels when fram resized
				buyPavlovaPanel.setBounds(10, 25 + (buyHealthItemPanel.getHeight() - 135) / 4, 280, 25);
				buyCheeseRollPanel.setBounds(10, buyPavlovaPanel.getY() + 25 + (buyHealthItemPanel.getHeight() - 135) / 4, 280, 25);
				buyPineappleLumpsPanel.setBounds(10, buyCheeseRollPanel.getY() + 25 + (buyHealthItemPanel.getHeight() - 135) / 4, 280, 25);
				mainMenuPUBtn.setBounds(10, buyPowerUpPanel.getHeight() - 35, 280, 25);
				
			}
		});
		buyPowerUpPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		buyPowerUpPanel.setBounds(125, 200, 300, 233);
		frame.getContentPane().add(buyPowerUpPanel);
		buyPowerUpPanel.setLayout(null);
		buyPowerUpPanel.setVisible(false);
		
		currentFundsPULbl = new JLabel(MessageFormat.format("You Have ${0}", team.getMoney()));
		currentFundsPULbl.setHorizontalAlignment(SwingConstants.CENTER); // shows current funds
		currentFundsPULbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		currentFundsPULbl.setBounds(10, 10, 280, 15);
		buyPowerUpPanel.add(currentFundsPULbl);
		
		mainMenuPUBtn = new JButton("Choose Another Option");
		mainMenuPUBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainMenuPUBtn.setBounds(10, 170, 280, 25);
		buyPowerUpPanel.add(mainMenuPUBtn);
		
		buyPavlovaPanel = new JPanel();
		buyPavlovaPanel.setBounds(10, 35, 280, 25);
		buyPowerUpPanel.add(buyPavlovaPanel);
		buyPavlovaPanel.setLayout(null);
		
		pavlovaPULbl = new JLabel("Pavlova");
		pavlovaPULbl.setToolTipText(MessageFormat.format("<html>{0}<br>{1}</html>", PowerUpType.PAVLOVA.getLongDescription(), PowerUpType.PAVLOVA.getEffect()));
		pavlovaPULbl.setBounds(0, 0, 130, 25);
		buyPavlovaPanel.add(pavlovaPULbl);
		pavlovaPULbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		pavlovaPULbl.setHorizontalAlignment(SwingConstants.LEFT);
		
		pavlovaPricePULbl = new JLabel(MessageFormat.format("${0}", PowerUpType.PAVLOVA.getCost()));
		pavlovaPricePULbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		pavlovaPricePULbl.setHorizontalAlignment(SwingConstants.RIGHT);
		pavlovaPricePULbl.setBounds(150, 0, 60, 25);
		buyPavlovaPanel.add(pavlovaPricePULbl);
		
		buyPavlovaBtn = new JButton("Buy");
		buyPavlovaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(team.getMoney() >= PowerUpType.PAVLOVA.getCost()) { // if you have enough money, purchases the item and updates current funds label with transaction sound and success message
					team.addPowerUp(PowerUpType.PAVLOVA);
					team.changeMoney(PowerUpType.PAVLOVA.getCost() * -1);
					sounds.playTransaction();
					JOptionPane.showMessageDialog(frame, MessageFormat.format("You have successfully bought a {0}! Quick head to the cafe and tuck in!", PowerUpType.PAVLOVA.toString()) , "Well Done!", JOptionPane.INFORMATION_MESSAGE);
					currentFundsPULbl.setText(MessageFormat.format("You Have ${0}", team.getMoney()));
				} else { // else displays an error
					sounds.playBadSound();
					JOptionPane.showMessageDialog(frame, shop.getMoneyError(), "Uh Oh!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buyPavlovaBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		buyPavlovaBtn.setBounds(210, 0, 70, 25);
		buyPavlovaPanel.add(buyPavlovaBtn);
		
		buyCheeseRollPanel = new JPanel();
		buyCheeseRollPanel.setLayout(null);
		buyCheeseRollPanel.setBounds(10, 69, 280, 25);
		buyPowerUpPanel.add(buyCheeseRollPanel);
		
		cheeseRollPULbl = new JLabel("Cheese Roll");
		cheeseRollPULbl.setToolTipText(MessageFormat.format("<html>{0}<br>{1}</html>", PowerUpType.CHEESE_ROLL.getLongDescription(), PowerUpType.CHEESE_ROLL.getEffect()));
		cheeseRollPULbl.setHorizontalAlignment(SwingConstants.LEFT);
		cheeseRollPULbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		cheeseRollPULbl.setBounds(0, 0, 130, 25);
		buyCheeseRollPanel.add(cheeseRollPULbl);
		
		cheeseRollPricePULbl = new JLabel(MessageFormat.format("${0}", PowerUpType.CHEESE_ROLL.getCost()));
		cheeseRollPricePULbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		cheeseRollPricePULbl.setHorizontalAlignment(SwingConstants.RIGHT);
		cheeseRollPricePULbl.setBounds(150, 0, 60, 25);
		buyCheeseRollPanel.add(cheeseRollPricePULbl);
		
		buyCheeseRollBtn = new JButton("Buy");
		buyCheeseRollBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// if you have enough money, purchases the item and updates current funds label with transaction sound and success message
				if(team.getMoney() >= PowerUpType.CHEESE_ROLL.getCost()) {
					team.addPowerUp(PowerUpType.CHEESE_ROLL);
					team.changeMoney(PowerUpType.CHEESE_ROLL.getCost() * -1);
					sounds.playTransaction();
					JOptionPane.showMessageDialog(frame, MessageFormat.format("You have successfully bought a {0}! Quick head to the cafe and tuck in!", PowerUpType.CHEESE_ROLL.toString()) , "Well Done!", JOptionPane.INFORMATION_MESSAGE);
					currentFundsPULbl.setText(MessageFormat.format("You Have ${0}", team.getMoney()));
				} else { // else displays an error
					sounds.playBadSound();
					JOptionPane.showMessageDialog(frame, shop.getMoneyError(), "Uh Oh!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buyCheeseRollBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		buyCheeseRollBtn.setBounds(210, 0, 70, 25);
		buyCheeseRollPanel.add(buyCheeseRollBtn);
		
		buyPineappleLumpsPanel = new JPanel();
		buyPineappleLumpsPanel.setLayout(null);
		buyPineappleLumpsPanel.setBounds(10, 105, 280, 25);
		buyPowerUpPanel.add(buyPineappleLumpsPanel);
		
		pineappleLumpsPULbl = new JLabel("Pineapple Lumps");
		pineappleLumpsPULbl.setToolTipText(MessageFormat.format("<html>{0}<br>{1}</html>", PowerUpType.PINEAPPLE_LUMPS.getLongDescription(), PowerUpType.PINEAPPLE_LUMPS.getEffect()));
		pineappleLumpsPULbl.setHorizontalAlignment(SwingConstants.LEFT);
		pineappleLumpsPULbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		pineappleLumpsPULbl.setBounds(0, 0, 130, 25);
		buyPineappleLumpsPanel.add(pineappleLumpsPULbl);
		
		pineappleLumpsPricePULbl = new JLabel(MessageFormat.format("${0}", PowerUpType.PINEAPPLE_LUMPS.getCost()));
		pineappleLumpsPricePULbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		pineappleLumpsPricePULbl.setHorizontalAlignment(SwingConstants.RIGHT);
		pineappleLumpsPricePULbl.setBounds(150, 0, 60, 25);
		buyPineappleLumpsPanel.add(pineappleLumpsPricePULbl);
		
		buyPineappleLumpsBtn = new JButton("Buy");
		buyPineappleLumpsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // if you have enough money, purchases the item and updates current funds label with transaction sound and success message
				if(team.getMoney() >= PowerUpType.PINEAPPLE_LUMPS.getCost()) {
					team.addPowerUp(PowerUpType.PINEAPPLE_LUMPS);
					team.changeMoney(PowerUpType.PINEAPPLE_LUMPS.getCost() * -1);
					sounds.playTransaction();
					JOptionPane.showMessageDialog(frame, MessageFormat.format("You have successfully bought a {0}! Quick head to the cafe and tuck in!", PowerUpType.PINEAPPLE_LUMPS.toString()) , "Well Done!", JOptionPane.INFORMATION_MESSAGE);
					currentFundsPULbl.setText(MessageFormat.format("You Have ${0}", team.getMoney()));
				} else { // else displays an error
					sounds.playBadSound();
					JOptionPane.showMessageDialog(frame, shop.getMoneyError(), "Uh Oh!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buyPineappleLumpsBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		buyPineappleLumpsBtn.setBounds(210, 0, 70, 25);
		buyPineappleLumpsPanel.add(buyPineappleLumpsBtn);
		mainMenuPUBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainMenuPanel.setVisible(true);
				buyPowerUpPanel.setVisible(false);
			}
		});
		

		// -------------------Creates Buy Map Panel--------------------
		buyMapPanel = new JPanel();
		buyMapPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) { // updates current funds when displayed
				currentFundsMAPLbl.setText(MessageFormat.format("You Have ${0}", team.getMoney()));
			}
			@Override
			public void componentResized(ComponentEvent e) { // updates sizes and positions of panels when fram resized
				mapCostLbl.setBounds(10, 25 + (buyMapPanel.getHeight() - 100) / 3, 280, 15);
				purchaseMapBtn.setBounds(10, mapCostLbl.getY() + 15 + (buyMapPanel.getHeight() - 100) / 3, 280, 25);
				mainMenuMAPBtn.setBounds(10, purchaseMapBtn.getY() + 25 + (buyMapPanel.getHeight() - 100) / 3, 280, 25);
			}
		});
		buyMapPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		buyMapPanel.setBounds(125, 200, 300, 233);
		frame.getContentPane().add(buyMapPanel);
		buyMapPanel.setLayout(null);
		buyMapPanel.setVisible(false);
		
		currentFundsMAPLbl = new JLabel(MessageFormat.format("You Have ${0}", team.getMoney()));
		currentFundsMAPLbl.setHorizontalAlignment(SwingConstants.CENTER); // displays current funds
		currentFundsMAPLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		currentFundsMAPLbl.setBounds(10, 10, 280, 15);
		buyMapPanel.add(currentFundsMAPLbl);
		
		mapCostLbl = new JLabel("Maps cost $5.00");
		mapCostLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mapCostLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		mapCostLbl.setBounds(10, 50, 280, 15);
		buyMapPanel.add(mapCostLbl);
		
		purchaseMapBtn = new JButton("Purchase Map");
		purchaseMapBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		purchaseMapBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(team.getMoney() >= 5.00) {// if you have enough money, purchases the item and updates current funds label with transaction sound and success message
					team.changeMaps(1);
					team.changeMoney(-5.00);
					sounds.playTransaction();
					JOptionPane.showMessageDialog(frame, "You have successfully bought a map! Best go back to base and study the local landmarks!", "Well Done!", JOptionPane.INFORMATION_MESSAGE);
					currentFundsMAPLbl.setText(MessageFormat.format("You Have ${0}", team.getMoney()));
				} else { // else displays error message
					sounds.playBadSound();
					JOptionPane.showMessageDialog(frame, shop.getMoneyError(), "Uh Oh!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		purchaseMapBtn.setBounds(10, 70, 280, 25);
		buyMapPanel.add(purchaseMapBtn);
		
		mainMenuMAPBtn = new JButton("Choose Another Option");
		mainMenuMAPBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainMenuMAPBtn.setBounds(10, 170, 280, 25);
		buyMapPanel.add(mainMenuMAPBtn);
		mainMenuMAPBtn.addActionListener(new ActionListener() { // returns to main menu
			public void actionPerformed(ActionEvent arg0) {
				mainMenuPanel.setVisible(true);
				buyMapPanel.setVisible(false);
			}
		});
		
		// -----------Creates Show Inventory Panel---------------------		
		showInventoryPanel = new JPanel();
		showInventoryPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) { // updates values for current funds, maps, power ups, healing items
				currentFundsInventoryLbl.setText(MessageFormat.format("You Have ${0}", team.getMoney()));
				numberMapsLbl.setText(MessageFormat.format("You have {0} maps", team.getMaps()));
				yourPowerUpItemsLbl.setText(team.listPowerUps());
				yourHealingItemsLbl.setText(team.listHealingItems());
			}
			@Override
			public void componentResized(ComponentEvent arg0) { // updates sizes and positions of panels when fram resized
				numberMapsLbl.setBounds(10, 25 + (showInventoryPanel.getHeight() - 215) / 4, 280, 15);
				yourPowerUpItemsLbl.setBounds(10, numberMapsLbl.getY() + 15 + (showInventoryPanel.getHeight() - 215) / 4, 280, 70);
				yourHealingItemsLbl.setBounds(10, yourPowerUpItemsLbl.getY() + 70 + (showInventoryPanel.getHeight() - 215) / 4, 280, 70);
				mainMenuInventoryBtn.setBounds(10, showInventoryPanel.getHeight() - 35, 280, 25);
			}
		});
		showInventoryPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		showInventoryPanel.setBounds(125, 200, 300, 233);
		frame.getContentPane().add(showInventoryPanel);
		showInventoryPanel.setLayout(null);
		showInventoryPanel.setVisible(false);
		
		currentFundsInventoryLbl = new JLabel(MessageFormat.format("You Have ${0}", team.getMoney()));
		currentFundsInventoryLbl.setHorizontalAlignment(SwingConstants.CENTER); // shows current funds
		currentFundsInventoryLbl.setBounds(10, 5, 280, 15);
		currentFundsInventoryLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		showInventoryPanel.add(currentFundsInventoryLbl);
		
		numberMapsLbl = new JLabel(MessageFormat.format("You have {0} maps", team.getMaps()));
		numberMapsLbl.setFont(new Font("Tahoma", Font.BOLD, 13)); // shows the number of maps the team has
		numberMapsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		numberMapsLbl.setBounds(10, 25, 280, 15);
		showInventoryPanel.add(numberMapsLbl);
		
		yourPowerUpItemsLbl = new JLabel(team.listPowerUps()); // shows the teams current power ups
		yourPowerUpItemsLbl.setVerticalAlignment(SwingConstants.TOP);
		yourPowerUpItemsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		yourPowerUpItemsLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		yourPowerUpItemsLbl.setBounds(10, 45, 280, 70);
		showInventoryPanel.add(yourPowerUpItemsLbl);
		
		yourHealingItemsLbl = new JLabel(this.team.listHealingItems()); // shows the teams current healing items
		yourHealingItemsLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		yourHealingItemsLbl.setVerticalAlignment(SwingConstants.TOP);
		yourHealingItemsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		yourHealingItemsLbl.setBounds(10, 126, 280, 70);
		showInventoryPanel.add(yourHealingItemsLbl);
		
		mainMenuInventoryBtn = new JButton("Choose Another Option"); // returns to main menu
		mainMenuInventoryBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainMenuInventoryBtn.setBounds(10, 197, 280, 25);
		mainMenuInventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainMenuPanel.setVisible(true);
				showInventoryPanel.setVisible(false);
			}
		});
		showInventoryPanel.add(mainMenuInventoryBtn); 
		sounds.playHello(); // Plays the welcome sound effect
	}
	
	/**
	 * Closes the screen when no longer required
	 */
	public void closeScreen() {
		frame.dispose();
	}
}
