/*	Name: Andy Zou 
 *	Course Code: ICS4U
 *	Date: 11-12-19
 *	Introduction: This set of code allows the user to input and read CDs from files and access every bit of information that
 *	they would like from the uploaded CD with the correct sets of information like a data base.
 */
import java.io.*;
import java.util.*;

public class Driver
{
	private static ArrayList <CD> CDs = new ArrayList <CD> ();
	private static int displayMenu (int menuNum, BufferedReader stdIn) throws IOException { //displays the main menus (which menu, the user input) , returns an int to direct what sub menu to go to
		int choice = 0;	
		if (menuNum == 0)	
		{
			System.out.println ("----------  MAIN MENU  -----------"); //main menu
			System.out.println ("1) Accessing your list of CDs");
			System.out.println ("2) Accessing within a particular CD");
			System.out.println ("3) Exit");
			System.out.println ("----------------------------------");
		}
		else if (menuNum == 1)
		{
			System.out.println ("\n---------  SUB-MENU #1  ----------"); // sub menu 1
			System.out.println ("1) Display all of your CDs"); 
			System.out.println ("2) Display info on a particular CD");
			System.out.println ("3) Add a CD");
			System.out.println ("4) Remove a CD");
			System.out.println ("5) Copy a CD");
			System.out.println ("6) Create a sub-CD");
			System.out.println ("7) List songs in common between two CDs");
			System.out.println ("8) Return back to main menu.");
			System.out.println ("----------------------------------");
		}
		else
		{
			System.out.println ("\n---------  SUB-MENU #2  ----------"); // sub menu 2
			System.out.println ("1) Display all songs (in the last sorted order) ");
			System.out.println ("2) Display info on a particular song ");
			System.out.println ("3) Add song");
			System.out.println ("4) Remove Song (4 options)");
			System.out.println ("5) Sort songs (3 options)");
			System.out.println ("6) Return back to main menu");
			System.out.println ("----------------------------------");
		}

		System.out.print ("\nPlease enter the number of your choice:  ");
		try {
			choice = Integer.parseInt (stdIn.readLine ().trim()); //asking for input
			if (menuNum == 0) //checking for invalid input
				if (choice < 1 || choice > 3)
					System.out.println("Invalid Input");
			if (menuNum == 1)
				if (choice < 1 || choice > 8)
					System.out.println("Invalid Input");
			if (menuNum == 2)
				if (choice < 1 || choice > 6)
					System.out.println("Invalid Input");
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid Input");
		}
		return choice;
	}

	public static int addCD () throws IOException { // to prevent the copy and pasting of the same code over and over again for adding a cd
		String ui;
		Scanner inFile;
		String CdTitle;
		String title;
		int number;
		String artist;
		String genre;
		int rating;
		String time;
		BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in)); //user input
		System.out.println("Please enter the name of the CD or 'back' to return: ");
		ui = stdIn.readLine().trim();
		if (ui.compareToIgnoreCase("back") != 0) {
			try {
				inFile = new Scanner (new File (ui + ".txt")); //opens the specified text file
				CdTitle = inFile.nextLine();
				number = Integer.parseInt(inFile.nextLine());	
				CDs.add(new CD (CdTitle, number));
				while (inFile.hasNextLine()) { //retrieves the data from the file
					title = inFile.nextLine().trim();
					artist = inFile.nextLine().trim();
					genre = inFile.nextLine().trim();
					rating = Integer.parseInt(inFile.nextLine().trim());
					time = inFile.nextLine().trim();
					CDs.get(CDs.size() - 1).addSong(title, artist, genre, rating, time); //adds it to the list in the CD class
				}
				inFile.close();
				System.out.println("Added CD");
				return 1;
			}
			catch (FileNotFoundException e) {	//catches if there were no file that was named like that CD
				System.out.println("\nCD not found");
				return 0;
			}
		}
		return -1;
	}

	public static int chooseCD () throws IOException { //choosing a CD returns -1 if they entered back or the CD index if all is fine
		String ui;
		int choice = -1;
		BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in)); //user input
		System.out.println("Here is this list of CDs: ");
		for (int i = 0; i < CDs.size(); i++) { //displays all the CDs
			System.out.println(i+1 + ") " + CDs.get(i).getTitle());
		}
		System.out.println(" ");
		System.out.print("Please enter a choice or 'back' to return: ");
		System.out.println(" ");
		ui = stdIn.readLine().trim();
		while (choice > -1 || ui.compareToIgnoreCase("back") != 0) { //checks for error input
			try {
				choice = Integer.parseInt(ui) - 1;
				if (choice < CDs.size() && choice > -1)
					break;
				if (ui.compareToIgnoreCase("back") == 0)
					break;
				System.out.println("Please enter a valid choice: ");
				ui = stdIn.readLine().trim();
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter a valid choice: ");
				ui = stdIn.readLine().trim();
			}
		}
		return choice;
	}

	public static void subSongs() throws IOException { //creates subCD 
		String ui;
		int startIndex = -1;
		int endIndex = -1;
		BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in)); //user input
		int n = chooseCD();
		if (n > -1) {
			System.out.println("Here is the list of Songs: ");
			CDs.get(n).displaySongs();
			System.out.println(" ");
			System.out.println("Please enter a starting index or 'back' to return: ");
			ui = stdIn.readLine().trim();
			while (startIndex > -1 || ui.compareToIgnoreCase("back") != 0) { //error check
				try {
					startIndex = Integer.parseInt(ui) - 1;
					if (startIndex < CDs.get(n).getSongSize() && startIndex > -1)
						break;
					if (ui.compareToIgnoreCase("back") == 0)
						break;
					System.out.println("Please enter a valid index: ");
					ui = stdIn.readLine().trim();
				}
				catch (NumberFormatException e) {
					System.out.println("Please enter a valid index: ");
					ui = stdIn.readLine().trim();
				}
			}
			System.out.println("Please enter an ending index or 'back' to return: ");
			ui = stdIn.readLine().trim();
			while (endIndex > -1 || ui.compareToIgnoreCase("back") != 0) { //error check
				try {
					endIndex = Integer.parseInt(ui) - 1;
					if (endIndex < CDs.get(n).getSongSize() && endIndex > -1 && endIndex >= startIndex)
						break;
					if (ui.compareToIgnoreCase("back") == 0)
						break;
					System.out.println("Please enter a valid index or an index greater than the starting indexor 'back' to return: ");
					ui = stdIn.readLine().trim();
					endIndex = -1;
				}
				catch (NumberFormatException e) {
					System.out.println("Please enter a valid index or an index greater than the starting index or 'back' to return: ");
					ui = stdIn.readLine().trim();
				}
			}
			if (ui.compareToIgnoreCase("back") != 0) { //this would run if back was not typed
				CDs.add(new CD ("SUB" + CDs.get(n).getTitle(), endIndex + 1)); //creates new CD
				for (int i = startIndex; i <= endIndex; i ++) { //adding all the sub songs into the new CD
					CDs.get(CDs.size()-1).addSong(CDs.get(n).getSongTitle(i), CDs.get(n).getSongName(i), CDs.get(n).getSongGenre(i), CDs.get(n).getSongRating(i), CDs.get(n).getSongTime(i));
				}
				System.out.println("SUB" + CDs.get(n).getTitle() + " has been created");
			}
		}
	}

	public static int chooseSong(int n) throws IOException { //choosing song parameters take in the CD index returns the Song index
		String ui;
		int choice = -1;
		BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in)); //user input
		System.out.println("Here is the list of songs: ");
		CDs.get(n).displaySongs();
		System.out.println(" ");
		System.out.print("Please enter a choice or 'back' to return: ");
		System.out.println(" ");
		ui = stdIn.readLine().trim();
		while (choice > -1 || ui.compareToIgnoreCase("back") != 0) { //error check
			try {
				choice = Integer.parseInt(ui) - 1;
				if (choice < CDs.get(n).getSongSize() && choice > -1)
					break;
				if (ui.compareToIgnoreCase("back") == 0)
					break;
				System.out.println("Please enter a valid choice: ");
				ui = stdIn.readLine().trim();
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter a valid choice: ");
				ui = stdIn.readLine().trim();
			}
		}
		return choice;
	}

	public static int removeMenu() throws IOException { //prompts the song removing menu return the option for removing
		String ui;
		int choice = -1;
		BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in)); //user input
		System.out.println(" ");
		System.out.println("How would you like to remove the song? Please enter your choice or 'back' to return: "); //the options for removing
		System.out.println("1) By number");
		System.out.println("2) Song Title");
		System.out.println("3) First Song");
		System.out.println("4) Last Song");
		ui = stdIn.readLine().trim();
		while (choice > -1 || ui.compareToIgnoreCase("back") != 0) { //error check
			try {
				choice = Integer.parseInt(ui);
				if (choice < 5 && choice > 0)
					break;
				if (ui.compareToIgnoreCase("back") == 0)
					break;
				System.out.println("Please enter a valid choice: ");
				ui = stdIn.readLine().trim();
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter a valid choice: ");
				ui = stdIn.readLine().trim();
			}
		}
		return choice;
	}
	public static void removeSong(int index) throws IOException{ //removing the song with the "by title" option, takes in the CD index
		String ui;
		int choice = -1;
		BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in)); //user input
		ArrayList <Song> temp = new ArrayList <Song> (CDs.get(index).getSong()); //copy of the song array list
		Collections.sort(temp, new SortByTitle());
		System.out.println("Here is the list of songs: ");
		CDs.get(index).displaySongs();
		System.out.println(" ");
		System.out.print("Please enter name or 'back' to return: ");
		System.out.println(" ");
		ui = stdIn.readLine().trim();
		while (choice > -1 || ui.compareToIgnoreCase("back") != 0) { //error check
			try {
				choice = Collections.binarySearch(temp, new Song(ui, null, null, 0, "0:00"), new SortByTitle());
				if (choice > -1)
					break;
				if (ui.compareToIgnoreCase("back") == 0)
					break;
				System.out.println(ui + " was not found. Please enter a valid choice: ");
				ui = stdIn.readLine().trim();
			}
			catch (NumberFormatException e) {
				System.out.println(ui + " was not found. Please enter a valid choice: ");
				ui = stdIn.readLine().trim();
			}
		}
		if (choice > -1) {
			CDs.get(index).removeSongByName(temp.get(choice));;
			System.out.println(ui + " has been removed");
		}
	}

	public static void inputSong(int index) throws IOException { //add song from user input, takes in the current CD index
		String ui;
		String title;
		String artist;
		String genre;
		int rating = -1;
		int seconds = -1;
		int choice = -1;
		BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in)); //user input
		while (choice == -1) { //while the user does not type back, outputs the choices and asks for input
			System.out.println(" ");
			System.out.print("Please enter title or 'back' to return: ");
			title = stdIn.readLine().trim();
			if (title.compareToIgnoreCase("back") == 0)
				break;
			System.out.println(" ");
			System.out.print("Please enter artist or 'back' to return: ");
			artist = stdIn.readLine().trim();
			if (artist.compareToIgnoreCase("back") == 0)
				break;
			System.out.println(" ");
			System.out.print("Please enter genre or 'back' to return: ");
			genre = stdIn.readLine().trim();
			if (genre.compareToIgnoreCase("back") == 0)
				break;
			System.out.println(" ");
			System.out.print("Please enter a rating out of 5 or 'back' to return: ");
			ui = stdIn.readLine().trim();
			if (ui.compareToIgnoreCase("back") == 0)
				break;
			while (choice == -1) {
				try {
					rating = Integer.parseInt(ui);
					if (rating > -1 && rating <= 5)
						break;
					System.out.println(" ");
					System.out.print("Please enter a valid input: ");
					ui = stdIn.readLine().trim();
					if (ui.compareToIgnoreCase("back") == 0)
						break;
				}
				catch (NumberFormatException e) {
					System.out.println(" ");
					System.out.print("Please enter a valid input: ");
					ui = stdIn.readLine().trim();
				}
			}
			System.out.println(" ");
			System.out.print("Please enter a time in seconds or 'back' to return: ");
			ui = stdIn.readLine().trim();
			if (ui.compareToIgnoreCase("back") == 0)
				break;
			while (choice == -1) {
				try {
					seconds = Integer.parseInt(ui);
					if (seconds > -1)
						break;
					if (ui.compareToIgnoreCase("back") == 0)
						break;
					System.out.println(" ");
					System.out.print("Please enter a valid input: ");
					ui = stdIn.readLine().trim();
				}
				catch (NumberFormatException e) {
					System.out.println(" ");
					System.out.print("Please enter a valid input: ");
					ui = stdIn.readLine().trim();
				}
			}
			String time= ""; //making the time for the Song constructor
			time += Integer.toString(seconds / 60);
			time += ":";
			time += Integer.toString(seconds % 60);
			if (time.substring(time.indexOf(':') + 1, time.length()).length() == 1)
				time += "0";
			CDs.get(index).addSong(title, artist, genre, rating, time); //adding the song
			break;
		}
	}
	public static int sortSong() throws IOException { //sorts song menu, returns the user choice
		String ui;
		int choice = -1;
		BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in)); //user input
		System.out.println(" "); //questions
		System.out.println("How would you like to sort the songs? Please enter your choice or 'back' to return: ");
		System.out.println("1) By title");
		System.out.println("2) By artist");
		System.out.println("3) By time");
		ui = stdIn.readLine().trim();
		while (choice > -1 || ui.compareToIgnoreCase("back") != 0) { //error check
			try {
				choice = Integer.parseInt(ui); 
				if (choice <= 3 && choice > 0) //breaks if meets the requirements of being one of the menu numbers or user typed back
					break;
				if (ui.compareToIgnoreCase("back") == 0)
					break;
				System.out.println("Please enter a valid choice: ");
				ui = stdIn.readLine().trim();
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter a valid choice: ");
				ui = stdIn.readLine().trim();
			}
		}
		return choice;
	}
	public static void main (String[] args) throws IOException
	{	
		String ui;
		int search;
		int mainMenuChoice = 0;
		int subMenuChoice = 0;
		while(mainMenuChoice != 3) {
			BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in)); //user input
			mainMenuChoice = displayMenu (0, stdIn);
			if (mainMenuChoice == 1) { //sub menu 1
				while(subMenuChoice != 8) {
					subMenuChoice = displayMenu (1, stdIn);
					if (CDs.size() == 0 && subMenuChoice != 3 && subMenuChoice < 8) 
						System.out.println("No CD in the list");

					if (subMenuChoice == 1 && CDs.size() != 0) { //listing all the CDs when there were at least 1 CD in the list
						System.out.println(" ");
						System.out.println("Here is the list of CDs: ");
						for (int i = 0; i < CDs.size(); i++) {
							System.out.println(CDs.get(i).getTitle());
						}
					}
					if (subMenuChoice == 2 && CDs.size() != 0) { //displays particular CD info
						int n = chooseCD();
						if (n > -1) {
							System.out.println("\nCD Title: " + CDs.get(n).getTitle());
							System.out.println("Number of Songs: " + CDs.get(n).getSongSize());
							System.out.println("CD length: " + CDs.get(n).getTime());
						}
					}
					if (subMenuChoice == 3) { //add CD
						int n = 0;
						while (n == 0) {
							n = addCD();
						}
					}
					if (subMenuChoice == 4 && CDs.size() != 0) { // remove CD
						int n = chooseCD();
						if (n > -1) {
							CDs.remove(n);
							System.out.println("CD has been removed");
						}
					}

					if (subMenuChoice == 5 && CDs.size() != 0) { // Copy CD
						int n = chooseCD();
						if (n > -1) {
							CDs.add(new CD ("COPY" + CDs.get(n).getTitle(), CDs.get(n).getSongSize()));
							for (int i = 0; i < CDs.get(n).getSongSize(); i ++) {
								CDs.get(CDs.size()-1).addSong(CDs.get(n).getSongTitle(i), CDs.get(n).getSongName(i), CDs.get(n).getSongGenre(i), CDs.get(n).getSongRating(i), CDs.get(n).getSongTime(i));
							}
							System.out.println("COPY" + CDs.get(n).getTitle() + " has been created");
						}
					}
					if (subMenuChoice == 6 && CDs.size() != 0) { //create sub CD
						subSongs();
					}
					if (subMenuChoice == 7 && CDs.size() != 0) { //List Songs in Common
						int n1 = chooseCD();
						if (n1 > -1) {
							int n2 = chooseCD();
							while (n2 == n1)
								n2 = chooseCD();
							if (n2 > -1 && n1 > -1) {
								System.out.println("Here is the list of common songs: ");
								for (int i = 0; i < CDs.get(n2).getSongSize(); i++) {
									if (CDs.get(n1).getSong().contains(CDs.get(n2).getSong().get(i)))
										System.out.println(i + 1 + ") " + CDs.get(n2).getSongTitle(i));
								}
							}
						}
					}
				}
			}
			else if (mainMenuChoice == 2) { //sub menu 2
				if (CDs.size() == 0) //checks when there are no CDs in list
					System.out.println("There are no CDs in the list, please add a CD");
				if (CDs.size() != 0) {
					int n = chooseCD();
					while (n < 0 && n != -1) { //checks whether the CD is actually in the list
						System.out.print ("\nPlease enter a valid CD:  ");
						ui = stdIn.readLine().trim();
						search = Collections.binarySearch(CDs, new CD (ui, 0), new SortByName());
					}
					mainMenuChoice = 0;
					subMenuChoice = 0;
					while(subMenuChoice != 6 && n != -1) {
						subMenuChoice = displayMenu (2, stdIn);
						if (CDs.get(n).getSongSize() == 0 && subMenuChoice != 3 && subMenuChoice < 6) 
							System.out.println("No Songs in the list");
						if (subMenuChoice == 1 && CDs.get(n).getSongSize() != 0) { // displays all songs
							System.out.println(" ");
							System.out.println("Here is the list of songs: ");
							CDs.get(n).displaySongs();
						}
						if (subMenuChoice == 2 && CDs.get(n).getSongSize() != 0) { // displays specific song info
							int choice = chooseSong(n);
							if (choice > -1) {
								CDs.get(n).returnInfo(choice);
							}
						}
						if (subMenuChoice == 3) { // add song
							inputSong(n);
						}
						if (subMenuChoice == 4 && CDs.get(n).getSongSize() != 0) { // removes song based on user choice
							int choice = removeMenu();
							if (choice == 1) {
								int num = chooseSong(n);
								CDs.get(n).removeSong(num);
							}
							if (choice == 2) {
								removeSong(n);
							}
							if (choice == 3) {
								CDs.get(n).removeSong(0);
								System.out.println("The first song has been removed");
							}
							if (choice == 4) {
								CDs.get(n).removeSong(CDs.get(n).getSongSize() - 1);
								System.out.println("The last song has been removed");
							}
						}
						if (subMenuChoice == 5) { // sort song off user choice
							int choice = sortSong();
							if (choice > -1) {
								if (choice == 1)
									Collections.sort(CDs.get(n).getSong(), new SortByTitle());
								if (choice == 2)
									Collections.sort(CDs.get(n).getSong(), new SortByArtist());
								if (choice == 3)
									Collections.sort(CDs.get(n).getSong(), new SortByTime());
								System.out.println("List has been sorted");
							}
						}
					}
				}
			}
		}
	}
}


class SortByName implements Comparator <CD> { //comparator used in binarySearch for the specific CD in the CDs arrayList
	public int compare (CD cd1, CD cd2) {
		return cd1.getTitle().compareToIgnoreCase(cd2.getTitle());
	}
}