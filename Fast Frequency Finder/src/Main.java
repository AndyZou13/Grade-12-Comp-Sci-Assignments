/*	Name: Andy Zou   
 *	Course Code: ICS4U
 *	Date: 12-14-19
 *	Introduction: This code finds the 20 most frequent words in a text file
 */
import java.util.*;
import java.io.*;
public class Main {
	public static Map <String, Integer> myWord = new HashMap <String, Integer> (); //map to store the words and their frequency, key is word, value is frequency
	public static Set <Words> myTree = new TreeSet <Words> (); //to sort for the top 20 most frequent words
	public static String[] pronouns = {"you", "i", "he", "she", "they", "we", "it", "they"}; //to store the pronouns needed to sort from
	public static void main(String[] args) throws IOException {
		String fileName = "alice.txt";
		long start = System.currentTimeMillis(); //keep track of time
		StringTokenizer word;
		BufferedReader input;
		String currLine = "";
		try {
			input = new BufferedReader (new FileReader (fileName)); //file reader input
			while (currLine != null) {
				currLine = input.readLine();
				if (currLine != null) {
					if (currLine.length() > 0) {
						word = new StringTokenizer (currLine, "!@#$%^&*()`~_+=[];:,.<>,/<?\"\\{\\} "); //splits into tokens based off of all the special characters except "-" and "'"
						while (word.hasMoreTokens()) { //while there are more tokens to run through
							String currWord = word.nextToken().toLowerCase();
							//case 1 where there is "-" on either ends of the word
							while (currWord.indexOf("-") == 0 || currWord.lastIndexOf("-") == currWord.length() - 1) {
								if (currWord.length() > 1) {
									if (currWord.indexOf("-") == 0) 
										currWord = currWord.substring(1, currWord.length());
									if (currWord.lastIndexOf("-") == currWord.length() - 1)
										currWord = currWord.substring(0, currWord.length() - 1);
								}
								if (currWord.length() <= 1) {
									currWord = "";
									break;
								}
							}

							//case 2 where "-' is in between words
							if (currWord.indexOf("-") != -1 && currWord.indexOf("-") != currWord.length() - 1 && currWord.charAt(currWord.indexOf("-") + 1) == '-') { //if the word has consecutive "-"
								StringTokenizer temp = new StringTokenizer(currWord,  "-"); //splits the word
								while (temp.hasMoreTokens()) {
									String t = temp.nextToken().toLowerCase();
									if (t.lastIndexOf("'") != t.length() - 1 && t.indexOf("'") != 0 && t.indexOf("'") != -1) {
										for (String s : pronouns) { //check if it is a pronoun in front
											if (t.substring(0, t.lastIndexOf("'")).compareToIgnoreCase(s) == 0) {
													if (myWord.containsKey(t)) { //checks if the word is already in the map and will add value if it is and add new word if it isn't
														int value = myWord.get(t);
														myWord.put(t, value + 1);
														break;
													}
													if (!myWord.containsKey(t) && t != "") {
														myWord.put(t, 1);
														break;
												}
											}
										}
									}
									else if (t.indexOf("-") == -1 && t != null) { //no more dashes left and inserting into the map
										if (myWord.containsKey(t)) {
											int value = myWord.get(t);
											myWord.put(t, value + 1);
											break;
										}
										if (!myWord.containsKey(t) && t != "") {
											myWord.put(t, 1);
											break;
										}
									}
								}
							}

							//case 3 where there are "'"
							else if (currWord.lastIndexOf("'") != -1) {
								int track = -1;
								for (String s : pronouns) { //checking for pronoun occurrences
									if (currWord.substring(0, currWord.lastIndexOf("'")).compareToIgnoreCase(s) == 0) {
										if (currWord != null && currWord != "") {
											if (myWord.containsKey(currWord)) {
												int value = myWord.get(currWord);
												myWord.put(currWord, value + 1);
												track = 0;
												break;
											}
											if (!myWord.containsKey(currWord) && currWord != "") {
												myWord.put(currWord, 1);
												track = 0;
												break;
											}
										}
									}
								}
								if (track == -1) { //remove 's from each word
									if (currWord.substring(currWord.indexOf("'") + 1, currWord.length()).compareToIgnoreCase("s") == 0) { 
										currWord = currWord.substring(0, currWord.indexOf("'"));
									}
									if (currWord.compareToIgnoreCase("'") != 0) {  
										if (myWord.containsKey(currWord)) {
											int value = myWord.get(currWord);
											myWord.put(currWord, value + 1);
										}
										if (!myWord.containsKey(currWord) && currWord != "") {
											myWord.put(currWord, 1);
										}
									}
								}
							}

							else if (currWord != null && currWord != "") { //another other word that is not caught by cases above
								if (myWord.containsKey(currWord)) {
									int value = myWord.get(currWord);
									myWord.put(currWord, value + 1);
								}
								if (!myWord.containsKey(currWord) && currWord != "") {
									myWord.put(currWord, 1);
								}
							}
						}
					}
				}
			}
			input.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!!!");
		}
		for (String key : myWord.keySet()) { //using the treeset to sort for the top 20 frequencies
				myTree.add(new Words(key, myWord.get(key)));
		}
		System.out.println("Most Frequent Words in a File");
		System.out.println("File Name: " + fileName);
		System.out.println("Total Time: " + (System.currentTimeMillis() - start) + " Milliseconds"); //end timer
		System.out.println("20 Most Frequent Words");
		System.out.println("Word Frequency");
		Iterator <Words> iter = myTree.iterator(); //iterator to iterate through the TreeSet to retrieve the top 20 values
		for (int i = 0; i < 20 && iter.hasNext(); i ++) {
			Words n = iter.next();
			System.out.println(i + 1 + ") " + n + " " + n.getFreq()); //display all the 20 words
		}
	}
}
