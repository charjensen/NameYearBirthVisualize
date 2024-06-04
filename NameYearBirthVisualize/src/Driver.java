import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Scanner;

import edu.du.dudraw.*;

public class Driver {

	// instsance variables, creating hashmap and array to store the total names per
	// year, and scanner
	private static double[] totalNames = new double[142];
	private static HashMap<String, Integer> yearData = new HashMap<String, Integer>();
	private static HashMap<String, LinkedList<Name>> nameData = populateHashMap();
	private static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		
		// used to see if user wants to continue using the program
		boolean cont = true;

		// DUDraw initialization
		DUDraw.setCanvasSize(1000, 600);
		DUDraw.enableDoubleBuffering();

		// ask user which mode they want
		System.out.println("Press 1 for name data over years, 2 for year data for names");

		// create a new scanner because issues
		keyboard = new Scanner(System.in);

		// enter mode one if user desires
		if (keyboard.nextLine().equals("1")) {
			do {

				// ask user for name and gener
				System.out.println("Please enter a name and gender, Ex: Mary, F");

				// Make a new keyboard
				keyboard = new Scanner(System.in);

				// declare key array which is the user input, ex: Mary, F
				String[] keyArr = null;
				// declare the reference bucket which is where all the user's names are, ex
				// where all the marys reside
				LinkedList<Name> nameQuery = null;

				do {

					// Key Array for user input
					keyArr = userInput();

					// reference to the bucket we want (whatever name the user input)
					nameQuery = nameData.get(keyArr[0]);

					// check if user messed up
					if (nameQuery == null) {
						System.out.println("Invalid name, please try again.");
					}

				} while (nameQuery == null);

				// draw graph
				drawGraph(nameQuery, keyArr);

				// check if the user would like to run the program again
				System.out.println("Would you like to enter another name? (y or n)");

				if (keyboard.next().equals("n")) {
					cont = false;
				}

			} while (cont);

			// exit
			keyboard.close();
			System.exit(0);
			
		}

		else {

			// create new scanner cause issues
			keyboard = new Scanner(System.in);

			// get user input for year
			System.out.println("Please enter a year");
			int year = keyboard.nextInt();

			// get user input for how many names they would like to see
			System.out.println("How many names would you like to see?");
			int k = keyboard.nextInt();

			// run the method that gets the most popular names in a year
			mostOccuringNames(year, k);

			// exit
			DUDraw.closeWindow();
			keyboard.close();

		}

	}

	public static String[] userInput() {

		// Format user input to be read later by removing all white space
		// and spliting at the comma
		String key = keyboard.nextLine().toLowerCase();
		key = key.replaceAll("\\s", "");
		String[] keyArr = key.split(",");

		return keyArr;

	}

	// populate the hashmap
	public static HashMap<String, LinkedList<Name>> populateHashMap() {

		// make the new hash map
		HashMap<String, LinkedList<Name>> nameData = new HashMap<String, LinkedList<Name>>();

		// While loop to read all the files
		for (int i = 1880; i <= 2021; i++) {

			// File reader
			File theFile = new File("yob" + i + ".txt");

			// instantiate and initializie a temp name
			Name tempName = null;

			// File Scanner to read file
			Scanner fileScanner = null;
			try {
				fileScanner = new Scanner(theFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			// Scan through all names and put in hashmap
			while (fileScanner.hasNext()) {

				// Parse the Name, Gender, and Occurrences
				String[] name = fileScanner.nextLine().toLowerCase().split(",");

				// make new name of name, gender, year, occurrences
				tempName = new Name(name[0], name[1], i, Integer.parseInt(name[2]));

				// If there is not already a linked list in the hashmap
				// where the key is then make a new one
				if (nameData.get(tempName.getName()) == null) {
					nameData.put(tempName.getName(), new LinkedList<Name>());
				}

				// add the name to the year data hashmap which puts the name and year for the
				// key, occurences for the value
				yearData.put(tempName.getName() + "," + tempName.getYear(), tempName.getOccurences());

				// No matter what add the name to the linked list
				nameData.get(tempName.getName()).add(tempName);

				// add the current names occurrences so we get the total names for that year,
				// save it in an array
				totalNames[i - 1880] += tempName.getOccurences();

			}

		}

		return nameData;

	}

	public static void drawGraph(LinkedList<Name> nameQuery, String[] keyArr) {

		// create a loop to search through all names
		// get the occurrences per year per name
		// draw graph with normalized data
		// loop all years

		// biggest occurence for scale of the graph
		int biggestYearPercent = Integer.MIN_VALUE;
		double biggestPercent = Double.MIN_VALUE;
		// declare array to save what the percentage of the current name divided by the
		// total name is
		double[] yearPercentages = new double[142];

		// get data for the name user wants
		for (Name n : nameQuery) {

			// check if the name where looping through matches up with user input
			if (n.getName().equals(keyArr[0]) & n.getGender().equals(keyArr[1])) {

				// calculate the percent of the current name and the total names in the year
				yearPercentages[n.getYear() - 1880] = n.getOccurences() / totalNames[n.getYear() - 1880];

				// check if its the biggest and save it
				if (yearPercentages[n.getYear() - 1880] > biggestPercent) {
					biggestPercent = yearPercentages[n.getYear() - 1880];
					biggestYearPercent = n.getYear();
				}

			}
		}

		// prepare for drawing graph
		DUDraw.clear();
		DUDraw.setXscale(0, 141);
		// set the y scale to be the biggest percentage
		DUDraw.setYscale(0, biggestPercent);

		// draw each name in the graph
		for (Name n : nameQuery) {

			// make sure name matches up with user input
			if (n.getName().equals(keyArr[0]) & n.getGender().equals(keyArr[1])) {

				// draw the rectangle
				// param 1 puts it in the year, y value is always 0, width, height which is the
				// percentage for this name
				DUDraw.filledRectangle((n.getYear() - 1880) - 0.5, 0, 0.5, yearPercentages[n.getYear() - 1880] * 0.9);

			}

		}

		// display info
		DUDraw.text(71, biggestPercent * 0.95, keyArr[0] + ", first year: " + nameQuery.get(0).getYear());
		DUDraw.text(71, biggestPercent * 0.92, "Max Frequency: "
				+ new DecimalFormat("#.##").format(biggestPercent * 100) + "% in " + biggestYearPercent);

		// show graph!
		DUDraw.show();
	}

	// mode 2 function
	public static void mostOccuringNames(int year, int k) {

		// declare array for top k names
		String[] occuringNames = new String[k];
		// declare for the biggest name to be read
		String biggestName = null;

		// loop through all names, find the biggest name for the desired year, do that k
		// times
		for (int i = 0; i < k; i++) {
			int biggestOccurence = Integer.MIN_VALUE;
			for (String n : yearData.keySet()) {
				String[] nArr = n.split(",");
				if (yearData.get(n) > biggestOccurence && nArr[1].equals(String.valueOf(year))) {
					biggestOccurence = yearData.get(n);
					biggestName = n;
				}

			}
			// remove the biggest name so we can find following biggest names
			yearData.remove(biggestName);
			// save the biggest name in a year
			occuringNames[i] = biggestName.split(",")[0];
		}

		// print out all the names!
		System.out.println("Most Popular names of " + year + ": ");

		for (int i = 0; i < occuringNames.length; i++) {
			System.out.println((i + 1) + ". " + occuringNames[i]);
		}

	}

}
