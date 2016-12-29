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

		int choice = 1;

		while (choice > 0) {
			choice = printMenu();
			if (choice == 1) {
				Scanner kb = new Scanner(System.in);

				System.out.println("\n1.\tBy English");
				System.out.println("2.\tBy German");
				System.out.println("3.\tBy Gender");
				System.out.println("4.\tBy Quality");
				int sortBy = kb.nextInt();

				if (sortBy == 1) {
					dict.sort("english");
					dict.printList();
				} else if (sortBy == 2) {
					dict.sort("german");
					dict.printList();
				} else if (sortBy == 3) {
					dict.sort("gender");
					dict.printList();
				} else if (sortBy == 4) {
					dict.sort("quality");
					dict.printList();
				} else {
					try {
						oos.writeObject(dict);
						oos.close();
						System.exit(0);
					} catch (Exception ex) {
						System.exit(0);
					}
				}
			} else if (choice == 2) {
				Scanner kb = new Scanner(System.in);

				char again = 'y';
				while (again == 'y') {
					German entry = createEntry();
					dict.add(entry);

					System.out.print("New entry? ");
					again = kb.nextLine().charAt(0);
					System.out.println();
				}

			} else if (choice == 3) {
				Scanner kb = new Scanner(System.in);

				System.out.print("What entry do you want to change? ");
				int index = kb.nextInt();
				German entry = createEntry();
				dict.replace(index-1, entry);

			} else if (choice == 4) {
				Scanner kb = new Scanner(System.in);

				System.out.print("What entry do you want to remove? ");
				int index = kb.nextInt();
				dict.remove(index-1);

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

		System.out.println("\n1.\tPrint Dictionary");
		System.out.println("2.\tAdd New Entry");
		System.out.println("3.\tChange Existing Entry");
		System.out.println("4.\tRemove Existing Entry");
		System.out.println("5.\tExit");

		return kb.nextInt();
	}

	public static German createEntry() {
		Scanner kb = new Scanner(System.in);
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

		return new German(eng, ger, plur, gen, qual);
	}
}