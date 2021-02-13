import java.util.*;
import java.io.*;
public class Main {
	public static ArrayList <Title> titles = new ArrayList <Title> (); //arraylist to store all the movies
	public static ArrayList <Genre> genres = new ArrayList <Genre> (); //arraylist to store the genres once they are found
	public static void main(String[] args) {
		Scanner inFile;
		Scanner userInput = new Scanner (System.in);
		String search = "";
		String [] input = new String [10000];
		String title = "";
		String rating = "";
		String genre = "";
		int indexTitle = 0;
		int indexRating = 0;
		int indexGenre = 0;
		Double r = 0.0;
		int counter = indexGenre;
		int interval = 1;
		try {
			inFile = new Scanner (new File ("Movies.txt"));
			while(inFile.hasNextLine()) {
				input = inFile.nextLine().trim().split(" ");
				try { //checks if the rating is a value number ie. if its over 100% or under 0% or not even a number at all
					r = Double.parseDouble(input[0].substring(0, input[0].length() - 1));
				}
				catch (NumberFormatException e) {
					r = -1.0;
				}
				if (input[0].indexOf("%") != -1 && input.length >= 3 && r >= 0 && r <= 100) {
					rating = input[0];
					genre = input[input.length - 1];
					for (int i = 1; i < input.length - 1; i ++) { //this for loop puts all the imported data into an ArrayList
						title += input[i] + " ";
					}
					title.trim();
					titles.add(new Title(title.trim(), rating.trim(), genre.trim())); //creates new object and adds it to the arraylist
				}
				title = "";
				Collections.sort(titles); //sorts the arraylist
			}
		}
		catch (FileNotFoundException f) { //catch exceptions in file input
			System.out.println ("File Not Found!!!");
		}
		catch (IOException e) {
			System.out.println("Reading Error!!!");
		}
		while(search.equals("exit") != true) {
			System.out.println("Please enter 'movie' or 'genre' to search (enter 'exit' to terminate:");
			search = userInput.nextLine(); // Ask for user input
			if (search.compareToIgnoreCase("movie") == 0) {
				while (true) {
					System.out.println("Please enter a movie to search or back to return to main menu:");
					search = userInput.nextLine(); // Ask for user input
					indexTitle = Collections.binarySearch(titles, new Title(search, null, null ), new SortByTitle()); // binarySearch for the specific title in the ArrayList
					if (indexTitle > -1 ) { // if the title was found, would return from anything greater than -1
						System.out.println("Movie Title: " + titles.get(indexTitle).getTitle()); //prints all the necessary information
						System.out.println("Movie Rating: " + titles.get(indexTitle).getRating());
						System.out.println("Movie Genre: " + titles.get(indexTitle).getGenre());
						String indexRate = titles.get(indexTitle).getRating(); //saves the required rating for further use
						Collections.sort(titles,  new SortByRating());	//sorts title ArrayList by rating
						indexRating = Collections.binarySearch(titles, new Title(null, indexRate, null ), new SortByRating()); //Finds what place the rating of that movie is
						indexRating += 1; // index + 1 since the array starts from 0
						System.out.println("Movie Raking: " + indexRating + " of " + titles.size()); 
						Collections.sort(titles);
						System.out.println(" ");
					}
					if (search.compareToIgnoreCase("back") == 0)
						break;
					if (indexTitle <= -1) { //will identify if the movie is not in the list
						System.out.println("No such movie or genre found in list");
						System.out.println(" ");
					}
				}
			}
			if (search.compareToIgnoreCase("genre") == 0) {
				while (true) {
					System.out.println("Please enter a genre to search or back to return to main menu:");
					search = userInput.nextLine(); // Ask for user input	
					Collections.sort(titles, new SortByGenre());
					indexGenre = Collections.binarySearch(titles, new Title(null, null, search), new SortByGenre());
					if (indexGenre > -1) {
						counter = indexGenre;
						while (titles.get(counter).getGenre().compareToIgnoreCase(search) == 0 && counter < titles.size()) {
							genres.add(new Genre(titles.get(counter).getTitle(), titles.get(counter).getRating(), titles.get(counter).getGenre())); //adds the movie that is found in titles arraylist with that specific genre into the genres arraylist
							counter += interval;
							if (counter < 0)
								break; //when there's no more of that category left
							if (counter >= titles.size() || titles.get(counter).getGenre().compareToIgnoreCase(search) != 0) { // if the counter goes over the ArrayList size to prevent IndexOutOfBounds
								counter = indexGenre;
								interval *= -1; // sets it to go the other direction
								counter += interval;
							}
						}
						Collections.sort(genres);
						for (int i = 0; i < genres.size(); i++) {
							System.out.println("Movie Title: " + genres.get(i).getTitle()); //prints all the necessary information
							System.out.println("Movie Rating: " + genres.get(i).getRating());
							System.out.println("Movie Genre: " + genres.get(i).getGenre());
							String indexRate = genres.get(i).getRating(); 
							Collections.sort(titles,  new SortByRating());	
							indexRating = Collections.binarySearch(titles, new Title(null, indexRate, null ), new SortByRating()); //Finds what place the rating of that movie is
							indexRating += 1; // index + 1 since the array starts from 0
							System.out.println("Movie Raking: " + indexRating + " of " + titles.size()); 
							Collections.sort(titles);
							System.out.println(" ");
						}
						genres.clear();
						Collections.sort(titles);
						counter = 0;
					}
					if (search.compareToIgnoreCase("back") == 0)
						break;
					if (indexGenre <= -1) { //will identify if the movie is not in the list
						System.out.println("No such genre found in list");
						System.out.println(" ");
					}
				}
			}
			if (indexTitle <= -1 && indexGenre <= -1 && !search.equals("exit")) { //will identify if the movie is not in the list
				System.out.println("No such movie or genre found in list");
				System.out.println(" ");
			}
			Collections.sort(titles);
		}

	}

}
