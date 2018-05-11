package HeroGame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
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

public class CityScreen {

	private JFrame frame;
	private JPanel locationPanel;
	private JPanel movePanel;
	private JPanel mapPanel;
	private JLabel northLocationLbl;
	private JLabel southLocationLbl;
	private JLabel eastLocationLbl;
	private JLabel westLocationLbl;
	private City city;
	
	
	public CityScreen(City newCity) {
		this.city = newCity;
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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				locationPanel.setBounds(230, 55, frame.getWidth() - 260, frame.getHeight() - 105);
				movePanel.setBounds(40 , (165 + (frame.getHeight() - 270) / 2) , 150, 100);
			}
		});
		frame.setBounds(100, 100, 550, 370);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setMinimumSize(new Dimension(550, 370));
		
		JLabel welcomeLbl = new JLabel("Welcome to ");
//		JLabel welcomeLbl = new JLabel("Welcome to " + city.getName());
		welcomeLbl.setBounds(10, 10, 510, 15);
		welcomeLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(welcomeLbl);
		
		JLabel rulerLbl = new JLabel("Currently Ruled By: ");
//		JLabel rulerLbl = new JLabel("Currently Ruled By: " + this.city.getVillain().getName());
		rulerLbl.setBounds(10, 30, 510, 15);
		rulerLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(rulerLbl);
		
		locationPanel = new JPanel();
		locationPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		locationPanel.setBounds(230, 55, 290, 265);
		frame.getContentPane().add(locationPanel);
		locationPanel.setMinimumSize(new Dimension(290, 265));
		locationPanel.setLayout(new CardLayout(0, 0));
		
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
				//Move North
				//useMap();
			}
		});
		northMoveBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		northMoveBtn.setBounds(50, 0, 50, 50);
		movePanel.add(northMoveBtn);
		
		JButton southMoveBtn = new JButton("S");
		southMoveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Move South
			}
		});
		southMoveBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		southMoveBtn.setBounds(50, 50, 50, 50);
		movePanel.add(southMoveBtn);
		
		JButton westMoveBtn = new JButton("W");
		westMoveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Move West
			}
		});
		westMoveBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		westMoveBtn.setBounds(0, 25, 50, 50);
		movePanel.add(westMoveBtn);
		
		JButton eastMoveBtn = new JButton("E");
		eastMoveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Move East
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
