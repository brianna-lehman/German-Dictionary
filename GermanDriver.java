import java.util.*;
import java.io.*;

public class GermanDriver {

	public static void main(String[] args) {
		Dictionary dict = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;

		// a dictionary hasn't been created yet
		if (args.length == 0) {
			// create a new dictionary
			dict = new Dictionary();

			try {
				// create a new file that will hold the dictionary object
				oos = new ObjectOutputStream(new FileOutputStream("dictionary.ser"));
			} catch (Exception ex) {
				System.exit(0);
			}
		}

		// a dictionary already exists
		else {
			/* open this file, create a dictionary out of the ArrayList object read in from the file */
			try {
				ois = new ObjectInputStream(new FileInputStream(args[0]));
				dict = (Dictionary)ois.readObject();
				ois.close();
			} catch (Exception ex) {
				System.out.println("Error opening file and reading dictionary object.");
				ex.printStackTrace();
				System.exit(0);
			}
			try {
				oos = new ObjectOutputStream(new FileOutputStream(args[0]));
			} catch (Exception ex) {
				System.out.println("Error creating new file to write the dictionary to.");
			}
		}

		int choice = printMenu();

		while (choice > 0) {
			if (choice == 1) {
				dict.sort("english");
				dict.printList();
				choice = printMenu();
			} else if (choice == 2) {
				dict.sort("german");
				dict.printList();
				choice = printMenu();
			} else if (choice == 3) {
				dict.sort("gender"); 
				dict.printList();
				choice = printMenu();
			} else if (choice == 4) {
				dict.sort("quality");
				dict.printList();
				choice = printMenu();
			} else if (choice == 5) {
				Scanner kb = new Scanner(System.in);

				char again = 'y';
				while (again == 'y') {
					System.out.print("English word: ");
					String eng = kb.nextLine();
					System.out.print("German word: ");
					String ger = kb.nextLine();
					System.out.print("Gender: ");
					char gen = kb.nextLine().charAt(0);
					System.out.print("Plural: ");
					String plur = kb.nextLine();
					System.out.print("Quality in sentence: ");
					String qual = kb.nextLine();

					German entry = new German(eng, ger, plur, gen, qual);
					dict.add(entry);

					System.out.print("New entry? ");
					again = kb.nextLine().charAt(0);
					System.out.println();
				}

				choice = printMenu();

			} else {
				try {
					oos.writeObject(dict);
					oos.close();
					System.exit(0);
				} catch (Exception ex) {
					System.exit(0);
				}
			}
		}
	}

	public static int printMenu() {
		Scanner kb = new Scanner(System.in);

		System.out.println("1.\tPrint Dictionary in English");
		System.out.println("2.\tPrint Dictionary in German");
		System.out.println("3.\tPrint Dictionary by Gender");
		System.out.println("4.\tPrint Dictionary by Quality in Sentence");
		System.out.println("5.\tAdd New Entry");
		System.out.println("6.\tExit");

		return kb.nextInt();
	}
}