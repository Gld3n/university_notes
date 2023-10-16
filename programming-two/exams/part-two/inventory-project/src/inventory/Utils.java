package inventory;

import java.util.Scanner;

public class Utils {
    public static void clearScreen() {
	    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
	        System.out.println("\f");
	    } else {
	        System.out.println("\033[H\033[2J");
	    }
	}

	public static void waitForUserInput() {
	    Scanner userInputScanner = new Scanner(System.in);
		
		System.out.println("\nPress enter to continue");
	    userInputScanner.nextLine();

	    clearScreen();
	}
}
