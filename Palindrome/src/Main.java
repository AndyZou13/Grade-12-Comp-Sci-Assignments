/*	Name: Andy Zou
 *	Course Code: ICS4U
 *	Introduction: This program 1 long string and finds the longest palindrome it in.
 */

import java.util.*;
import java.io.*;

public class Main {

	public static String findPalindrome (String s1) { // This method finds the palindrome in the string (radar)
		String word = "";
		String longestWord = "";
		String input = s1.toLowerCase();
		boolean check = true;
		int n = 1;
		for (int i = 0; i < input.length(); i ++) {
			n = 1;
			try {
				while (check == true) {
					check = input.substring(i - n,i - n + 1).equals(input.substring(i + n,i + n + 1)); //check if letter on the opposite sides are equal
					if (check == false) {
						check = true;
						break;
					}
					if (check == true) {
						word = input.substring(i - n, i + n + 1);
						n ++;
						if (word.length() > longestWord.length()) {
							longestWord = word;
						}
					}
				}
			}

			catch (StringIndexOutOfBoundsException e) {
			}
		}
		for (int i = 1; i < input.length(); i ++) { // this for loop check for the palindrome that starts as an even word (raddar)
			n = 1;
			try {
				if (input.substring(i-n, i).equals(input.substring(i, i + n)) == true) {
					while (check == true) {
						check = input.substring(i - n - 1,i - n).equals(input.substring(i + n,i + n + 1)); //check if letter on the opposite sides are equal
						if (check == false) {
							check = true;
							break;
						}
						if (check == true) {
							word = input.substring(i - n - 1, i + n + 1);
							n ++;
							if (word.length() > longestWord.length()) {
								longestWord = word;
							}
						}
					}
				}
				if (input.substring(i + n, i + n + 1).equals(input.substring(i, i + n)) == true) {
					while (check == true) {
						check = input.substring(i - n,i).equals(input.substring(i + n + 1,i + n + 2)); //check if letter on the opposite sides are equal
						if (check == false) {
							check = true;
							break;
						}
						if (check == true) {
							word = input.substring(i + n, i + n + 2);
							n ++;
							if (word.length() > longestWord.length()) {
								longestWord = word;
							}
						}
					}
				}
			}
			catch (StringIndexOutOfBoundsException e) {

			}
		}
		return longestWord;
	}


	public static void main(String[] args) {
		BufferedReader inFile;
		String largestPalindrome;
		String line = "";
		int largestPosition = 0;
		int largestLength = 0;
		try {
			inFile = new BufferedReader (new FileReader("inputPartC1.txt"));
			line = inFile.readLine();
			System.out.println("File input:");
			System.out.println("    " + line);
			largestPalindrome = findPalindrome(line);
			largestPosition = line.indexOf(largestPalindrome) + 1;
			largestLength = largestPalindrome.length();
			System.out.println("Screen Output:");
			System.out.println("    Finding the largest palindrome");
			System.out.println("    Largest Palindrome: " + largestPalindrome);
			System.out.println("    Starting Position: " + largestPosition);
			System.out.println("    Length: " + largestLength);
			inFile.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("No File Found!!");
		}
		catch (IOException f) {
			System.out.println("Reading Error!!");
		}
	}

}
