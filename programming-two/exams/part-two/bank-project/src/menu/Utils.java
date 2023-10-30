package menu;

import java.util.Scanner;

public class Utils {

	public static Double exchangeRate = 40.0;
    
    public static void clearScreen() {
	    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
	        System.out.println("\f");
	    } else {
	        System.out.println("\033[H\033[2J");
	    }
	}

	public static void waitForUserInput(Scanner scanner) {
		System.out.println("\nPress enter to continue");
	    scanner.nextLine();

	    clearScreen();
	}
}
