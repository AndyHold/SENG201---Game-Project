import java.util.Scanner;

/**
 * Selector Class for Heroes & Villains Game
 * Contains tools for command line interface
 * SENG201 2018S1
 * @author Andy Holden & Alex Liggett
 */
public class Selector {
	
	/**
	 * Command line integer selector. Includes robust handling of
	 * non-integer input
	 * @param lowerBound an int the minimum value of integer input
	 * @param upperBound an int the maximum value of integer input
	 * @param inputMessage a String the message displayed at the
	 * command prompt
	 * @param errorMessage a string the message displayed on non-compliant
	 * input. inputMessage is then displayed and interface resets
	 * @return an int legal input from the command line
	 */
	public int intSelector(int lowerBound, int upperBound, String inputMessage, String errorMessage) {
		int result = 0;
    	Scanner in = new Scanner(System.in);		
        boolean looping = true; 
	    while (looping) { 
	    	System.out.println(inputMessage);
	    	try {
	    		result = Integer.parseInt(in.nextLine());
		    	if (result < lowerBound || result > upperBound) {
		    		System.out.println(errorMessage);	
		    		
		    	} else {
		    		looping = false;
		    	}
	    	}
	    	catch (NumberFormatException e) {
	    		System.out.println(errorMessage);
		    }
	    }
	    return result;
	}
	
	/**
	 * Command line string input selector
	 * @param lowerBound an int the minimum length for a legal String
	 * @param upperBound an int the maximum length for a legal String
	 * @param inputMessage a String the message displayed at the
	 * command prompt
	 * @param errorMessage a string the message displayed on non-compliant
	 * input. inputMessage is then displayed and interface resets
	 * @return a String legal input from the command line
	 */
	public String stringSelector(int lowerBound, int upperBound, String inputMessage, String errorMessage) {
		String result = "Blank";
    	Scanner in = new Scanner(System.in);		
        boolean looping = true; 
	    while (looping) {
	    	System.out.println(inputMessage);
	    	result = in.nextLine();
		    if (result.length() < lowerBound || result.length() > upperBound) {
		    	System.out.println(errorMessage);	
		    } else {
		    	looping = false;
		    }
	    }
	    return result;
	}
	
	public void returnDetect(){
    	Scanner in = new Scanner(System.in);		
        boolean looping = true; 
	    while (looping) {
	    	String readLine = in.nextLine();
	    	if (readLine.equals("")) {
	    	    looping = false;
	    	} else {
	    		System.out.println("Press Enter");
	    	}

	    }
	}
	
	public static void main(String[] args) {
		Selector s1 = new Selector();
		System.out.println("Press Enter to Continue");
		s1.returnDetect();
		
		
	}
	
}
