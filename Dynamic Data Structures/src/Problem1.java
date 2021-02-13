/*	Name: Andy Zou  
 *	Course Code: ICS4U
 *	Date: 11-28-19
 *	Introduction: This code finds the number of substrings in a specific word in an inputed file
 */
import java.util.*;
import java.io.*;
public class Problem1 {

	public static void main(String[] args) {
		Scanner in;
		HashSet <String> h = new HashSet <String> (); //hashset to store only one copy of the substrings
		int input = 0;
		String word;
		try {
			in = new Scanner (new File("test.txt"));
			input = Integer.parseInt(in.nextLine());
			System.out.println("Finding the number of substrings");
			for (int i = 0; i < input; i ++) {
				word = in.nextLine();
				System.out.println("String: " + word);
				for (int j = 0; j < word.length(); j++) {
					for (int k = j; k <= word.length(); k++) {
						h.add(word.substring(j, k)); //adding to hashset
					}
				}
				System.out.println("No. of substrings: " + h.size());
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("No File Found!!");
		}
	}

}
