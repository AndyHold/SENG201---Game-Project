/**
 * Generic Hero Class for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class Hero {

	private HeroType heroType;
	
	private int strength = 100; 
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString (){
		return (heroType + " whose special ability is" + heroType.getAbility());
	}
	/*
	 * Test code - delete at some point
	 */
	public static void main(String[] args) {
		Hero hero1 = new Hero();
		hero1.heroType = HeroType.ALL_BLACK;
		hero1.strength = 100;
		System.out.println(hero1);
	}

}
