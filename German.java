import java.util.*;
import java.io.*;

public class German implements Serializable{
	String english;
	String german;
	String plural;
	char gender;
	String quality;

	public German(String english, String german, String plural, char gender, String quality) {
		this.english = english;
		this.german = german;
		this.plural = plural;
		this.gender = gender;
		this.quality = quality;
	}

	/**gettersandsetters*/
	public String getEnglish() {
		return english;
	}

	public String getGerman() {
		return german;
	}

	public String getPlural() {
		return plural;
	}

	public char getGender() {
		return gender;
	}

	public String getQuality() {
		return quality;
	}

	public static Comparator<German> EngComparator = new Comparator<German>() {
		public int compare(German word1, German word2) {
			String eng1 = word1.getEnglish().toLowerCase();
			String eng2 = word2.getEnglish().toLowerCase();

			return eng1.compareTo(eng2);
		}
	};

	public static Comparator<German> GerComparator = new Comparator<German>() {
		public int compare(German word1, German word2) {
			String ger1 = word1.getGerman().toLowerCase();
			String ger2 = word2.getGerman().toLowerCase();

			return ger1.compareTo(ger2);
		}
	};

	public static Comparator<German> GenderComparator = new Comparator<German>() {
		public int compare(German word1, German word2) {
			char gen1 = Character.toLowerCase(word1.getGender());
			char gen2 = Character.toLowerCase(word2.getGender());

			return gen1 - gen2;
		}
	};

	public static Comparator<German> QualityComparator = new Comparator<German>() {
		public int compare(German word1, German word2) {
			String qual1 = word1.getQuality().toLowerCase();
			String qual2 = word2.getQuality().toLowerCase();

			return qual1.compareTo(qual2);
		}
	};
}