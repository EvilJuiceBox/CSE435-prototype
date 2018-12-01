

/*
 * Firewall system to prevent intrusion and provide security
 */
public class Firewall extends SecurityComponent {
	protected static boolean checkInput(String d)
	{
		//check if input d is safe and validated
		return true; //returns true if safe, false if uncertain/dangerous to discard
	}
	
	protected static String sanitise(String input)
	{
		//sanitise all input from outside sources
		return input;
	}
}
