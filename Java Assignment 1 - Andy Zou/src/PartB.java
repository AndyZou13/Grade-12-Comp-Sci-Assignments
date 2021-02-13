/*	Name: Andy Zou
 *	Course Code: ICS4U
 *	Date: 09-16-2019
 *	Introduction: This program takes encrypted words with letter in hexadecimal and translates them
 * 				into alphabetic letters which is more readable
 */
import java.util.*;
import java.io.*;

public class PartB{

	public static String translateCharacter (String s1) { // this method translates the hexadecimal code into alphabetic letters
		String word = "";
		char letter;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) == '%') {
				int converter = Integer.parseInt(s1.substring(i + 1, i + 3), 16);
				letter = (char)converter;
				word += Character.toString(letter);
				i += 2;
			}
			else {
				word += s1.charAt(i);
			}
		}
		return word;
	}

	public static void main(String[] args) {
		Scanner inFile;
		try {
			inFile = new Scanner (new File("ExampleB.txt"));
			while (inFile.hasNextLine()) {
				String line = inFile.nextLine();
				System.out.println(line + " --> " + translateCharacter(line));
			}
			inFile.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("No File Found!!");
		}
	}
}
