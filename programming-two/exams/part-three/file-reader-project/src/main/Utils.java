package main;

import java.util.HashMap;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class Utils {
    
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

	/*
	 * This method reads the file and returns an ArrayList of all
	 * the words in the file.
	 */
	public static ArrayList<String> findFile() throws Exception {
		String filePath = "src/main/lorem-ipsum.txt";

        ArrayList<String> words = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(filePath);

            // Create a BufferedReader object to wrap the FileReader object, enabling more efficient line-by-line reading
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read the file line by line
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line into words, splitting spaces and punctuation
                String[] splitWords = line.split("\\s+|\\W+");

                for (String word : splitWords) {
                    words.add(word.toLowerCase());
                }
            }

            // Close the file
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

		return words;
	}

	public static void getWordsToLookFor(Scanner scanner, HashMap<String, Integer> wordsOcurrences) throws Exception {
		System.out.print("Insert words to look for (space separated): ");
		String[] wordsToLookFor = scanner.nextLine().toLowerCase().split(" ");
		for (String word : wordsToLookFor) {
			wordsOcurrences.put(word, 0);
		}

		ArrayList<String> wordsInFile = findFile();
		for (String word : wordsInFile) {
			if (wordsOcurrences.containsKey(word)) {
				wordsOcurrences.put(word, wordsOcurrences.get(word) + 1);
			}
		}
	}


	public static void generateStatsFile(HashMap<String, Integer> wordsOcurrences) {
		String filePath = "src/main/stats.txt";

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

			writer.write("=== [STATS LOG FILE] =======================\n");
			for (String word : wordsOcurrences.keySet()) {
				writer.write(word + ": " + wordsOcurrences.get(word) + "\n");
			}
			writer.write("============================================");

			writer.close();
		} catch (IOException e) {
			System.out.println("Error writing to file: " + e.getMessage());
		}
	}

	public static void showStatistics(HashMap<String, Integer> wordsOcurrences) {
		System.out.println("=== [STATISTICS] =======================");

		if (wordsOcurrences.isEmpty()) {
			System.out.println("[No statistics available]");
			return;
		}

		for (String word : wordsOcurrences.keySet()) {
			System.out.println(word + ": " + wordsOcurrences.get(word));
		}

		generateStatsFile(wordsOcurrences);

		System.out.println("\n[Stats file generated]");
	}
}