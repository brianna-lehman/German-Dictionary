import java.util.*;
import java.io.*;

public class Dictionary implements Serializable{
	// an array list of German objects which can be sorted by the english word, the german word, the gender, or the quality

	ArrayList<German> dict; 

	public Dictionary() {
		dict = new ArrayList<German>();
	}

	public void sort(String mode) {
		// NOTE: make sure that the arraylist uses a stable sort

		if (mode.equalsIgnoreCase("english")) {
			// sort the objects in the dictionary by the english word from the German object
			Collections.sort(dict, German.EngComparator); 
		} else if (mode.equalsIgnoreCase("german")) {
			// sort the objects in the dictionary by the german word from the German object
			Collections.sort(dict, German.GerComparator);
		} else if (mode.equalsIgnoreCase("gender")) {
			// sort the objects in the dictionary by the gender from the German object
			Collections.sort(dict, German.GenderComparator);
		} else if (mode.equalsIgnoreCase("quality")) {
			// sor the objects in the dictionary by the quality from the German object
			Collections.sort(dict, German.QualityComparator);
		} else {
			System.out.println("Error, unable to sort by this parameter.");
		}
	}

	public void printList() {
		int i = 1;
		for (German entry: dict) {
			System.out.println(i+".) "+entry.getEnglish()+": ("+entry.getGender()+") "+entry.getGerman()+", "+entry.getPlural()+", "+entry.getQuality());
			i++;
		}
	}

	public void add(German entry) {
		dict.add(entry);
	}

	public German find(int index) {
		return dict.get(index);
	}

	public void replace(int index, German entry) {
		dict.set(index, entry);
	}

	public void remove(int index) {
		dict.remove(index);
	}
}