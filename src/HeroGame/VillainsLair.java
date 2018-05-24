package HeroGame;
import java.text.MessageFormat;
import java.util.Random;

import javax.swing.ImageIcon;


/**
 * VillainsLair Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class VillainsLair extends Location {
	
	
	
	private Random rand = new Random();
	private Villain cityVillain;
	
	
	/**
	 * Constructor for VillainsLair Class Text Version.
	 * @param cityName Name of the City containing this Villains Lair
	 * @param currentVillian Current Villain of this city.
	 */
	VillainsLair(City thisCity, Villain currentVillain, Team heroTeam) {
		super(currentVillain.getLairName(thisCity.getName()), heroTeam, thisCity.getName(), LocationType.VILLIANSLAIR, new ImageIcon(CityScreen.class.getResource("/HeroGame/Images/VillainsLair.jpg")));
		this.cityVillain = currentVillain;
	}
	
	/**
	 * Sets the image associated with a particular Villain
	 * @return an ImageIcon the image associated with the particular Villain.
	 */
	public ImageIcon getVillainImage() {
		return this.cityVillain.getImage();
	}
	



	@Override
	public String toString() {
		return MessageFormat.format("{0} currently ruled by {1}", this.getName(), this.cityVillain.getName());
	}

}
