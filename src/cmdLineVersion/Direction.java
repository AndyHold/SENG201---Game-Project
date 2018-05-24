package cmdLineVersion;


/**
 * Direction Enum for Heroes & Villains Game
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public enum Direction {
	
	
	EAST(0), WEST(1), SOUTH(3), NORTH(2), CENTER(4);
	
	
	private int number;
	
	
	/**
	 * Constructor Method for Direction Enum
	 * @param number, number representation of the direction for use in the toString Method
	 */
	Direction(int number){
		this.number = number;
	}
	
	
	private int getNumberValue() {
		return this.number;
	}

	
	@Override
	public String toString() {
		int n = this.getNumberValue();
		switch(n) {
		case 0:
			return new String("East");
		case 1:
			return new String("West");
		case 2:
			return new String("North");
		case 3:
			return new String("South");
		case 4:
			return new String("Center");
		}
		return null;
	}

}
