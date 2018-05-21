package HeroGame;


import javax.sound.sampled.*;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 * Sound Class for Heroes & Villains Game. Contains sound effects and theme music
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class Sound {
	
	private Clip theme;
	private JFrame frame = new JFrame();
	
	/**
	 * Method to play background music
	 */
	public void playMusic() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(GameManager.class.getResource("/HeroGame/Sound/music.wav"));
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			theme = (Clip)AudioSystem.getLine(info);
			theme.open(ais);
			theme.start();
			theme.loop(Clip.LOOP_CONTINUOUSLY);
			} catch(IOException error) {
				JOptionPane.showMessageDialog(frame, "Couldn't find the music file!", "Not Good...", JOptionPane.ERROR_MESSAGE);
				error.printStackTrace();
			} catch (LineUnavailableException e) {
				JOptionPane.showMessageDialog(frame, "Couldn't get the clip!", "Not Good...", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				 JOptionPane.showMessageDialog(frame, "Unsupported File Type!", "Not Good...", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
	}
	
	/**
	 * Method to play Oh No sound
	 */
	public void playOhNo() {
		playEffect("/HeroGame/Sound/oh no.wav");
	}
	
	
	/**
	 * Method to play Munching sound
	 */
	public void playMunching() {
		playEffect("/HeroGame/Sound/munching.wav");
	}
	
	
	/**
	 * Method to play Marching sound
	 */
	public void playMarching() {
		playEffect("/HeroGame/Sound/marching.wav");
	}
	
	
	/**
	 * Method to play Open Can sound
	 */
	public void playOpenCan() {
		playEffect("/HeroGame/Sound/opencan.wav");
	}
	
	
	/**
	 * Method to play a bad sound
	 */
	public void playBadSound() {
		playEffect("/HeroGame/Sound/bad.wav");

	}
	
	/**
	 * Method to play a transaction sound
	 */
	public void playTransaction() {
		playEffect("/HeroGame/Sound/transaction.wav");
	}
	
	
	/**
	 * Method to play a hello sound
	 */
	public void playHello() {
		playEffect("/HeroGame/Sound/hello.wav");
	}
	
	
	
	
	/**
	 * Method to play a good sound
	 */
	public void playGoodSound() {
		playEffect("/HeroGame/Sound/good.wav");
	}
	
	/**
	 * Method for playing sound filenames
	 * @param filename a String the filename of the sound effect to be played
	 */
	public void playEffect(String filename) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(GameManager.class.getResource(filename));
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip)AudioSystem.getLine(info);
			clip.open(ais);
			clip.start();
			} catch(IOException error) {
				JOptionPane.showMessageDialog(frame, "Couldn't find the music file!", "Not Good...", JOptionPane.ERROR_MESSAGE);
				error.printStackTrace();
			} catch (LineUnavailableException e) {
				JOptionPane.showMessageDialog(frame, "Couldn't get the clip!", "Not Good...", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				 JOptionPane.showMessageDialog(frame, "Unsupported File Type!", "Not Good...", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
	}
	
	/**
	 * Stops the theme music upon demand
	 */
	public void stopMusic() {
		theme.stop();
	}


}
