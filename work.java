package work;
import java.util.*;
public class work {
	public static void main(String[]args) {
		/* List of assumptions
		 * -------------------
		 * A selftest is run on bootup to ensure the console 
		 * is ready for interactive mode
		 * This can be an interactive program in the sense
		 * that the user can start/stop the checkout process, and have the 
		 * ability to select items.
		 * This interactive program can be used to place more than one
		 * order.
		 * You can invoke the interactive prompts using the interactiveMode() function call
		 * todaysScheme is an object that contains info regarding cost per item
		 * The "Chips and Salsa" combo/bundle doesn't have an item number associated with it
		 * You can only pass in a string value into c.scan()
		 */
		
		if(selfTest() == true) { //successful selfTest bootup sequence
			interactiveMode(); //starting interactive mode
		}

		
	}
	public static boolean selfTest(){
		System.out.println("Running self test");
		boolean result = false;
		Scheme todaysScheme = new Scheme(1.99, 3.49, 2.49, 2.49, 15.49);
		Checkout c = new Checkout(todaysScheme);
		c.scan("1983"); // toothbrush
		c.scan("4900"); // salsa
		c.scan("8873"); // milk
		c.scan("6732"); // chips
		c.scan("0923"); // wine
		c.scan("1983"); // toothbrush
		c.scan("1983"); // toothbrush
		c.scan("1983"); // toothbrush
		if(c.getTotal() == 3037) {
			System.out.println("Self Test successful");
			System.out.println("This terminal is ready for use by customers\n\n");
			result = true;
		}
		else{
			System.out.println("Self Test failure");
			System.out.println("This terminal is not ready for use by customer\n\n");
		}
		
		return result;
		
	}
	public static void interactiveMode() {
		//Welcome screen
		System.out.println("------------------------");
		System.out.println("Welcome to Super Food's!");
		System.out.println("------------------------");
		System.out.println("Here are today's prices");
		Scheme todaysScheme = new Scheme(1.99, 3.49, 2.49, 2.49, 15.49);

		//Prompt user to start checkout sequence
		System.out.println("\nAre you ready to place your order? (YES to continue, NO or other response to stop)");
		Scanner input = new Scanner(System.in);
		String response = input.next();
		
		//Quick and simple input validation
		if(response.equals("Y") || response.equals("Yes") || response.equals("YES") ||response.equals("y") || response.equals("yes") || response.equals("yEs") || response.equals("yeS") ) {
			while(response.equals("Y") || response.equals("Yes") || response.equals("YES") ||response.equals("y") || response.equals("yes") || response.equals("yEs") || response.equals("yeS") ) {
				//Load Today's scheme into checkout
				Checkout c = new Checkout(todaysScheme);
				
				System.out.println("Enter items (0 to stop)");
				int itemNum = -1;
				while(itemNum!=0) {
					try {
						itemNum = input.nextInt();//Get item number from user
						if(itemNum!=0) {
							c.scan(String.valueOf(itemNum)); //Convert int to String in order to pass it into function as argument
						}
					}
					catch(Exception e) {
						System.out.println("Invalid input, try again (0 to stop)");
						input.nextLine(); //consume invalid input in order to prevent infinite loop
					}
				}
				
				//Print out total
				System.out.println("Your total is "+c.getTotal());

				//Prompt user to run checkout again
				System.out.println("Do you want to place another order? (YES to continue, NO or other response to stop)");
				response = input.next();
			}
		}
		System.out.println("Thank you for shopping at Super Food!  Good bye!");
		input.close();
	}
}
