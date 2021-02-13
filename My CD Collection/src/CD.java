/*	Name: Andy Zou 
 *	Course Code: ICS4U
 *	Date: 11-12-19
 *	Introduction: This class is responsible for storing Songs and info for the CD
 */
import java.util.*;
public class CD implements Comparable <CD>{
	private ArrayList <Song> Songs = new ArrayList <Song> ();
	private String title = "";
	private int number = 0;
	private int time = 0;
	private int mins = 0;
	private int secs = 0;
	
	public CD (String Title, int n) {//constructor
		title = Title;
		number = n;
	}
	public void displaySongs () { //displays all the songs
		for (int i = 0; i < Songs.size(); i++) {
			System.out.println(i+1 + ") " + Songs.get(i).getTitle());
		}
	}
	public void addSong (String title, String artist, String genre, int rating, String length) { //for the Driver class to be able to add songs to the CD class
		Songs.add(new Song(title, artist, genre, rating, length));
	}
	public void removeSong(int index) {
		Songs.remove(index);
	}
	public void removeSongByName(Song index) {
		Songs.remove(index);
	}
	//getter and setters
	public String getTitle () {
		return title;
	}
	public int getSize () {
		return number;
	}
	public int getSongSize() {
		return Songs.size();
	}
	public String getTime() {
		for (int i = 0; i < Songs.size(); i ++) {
			time += Songs.get(i).totalLength();
		}
		mins = time / 60;
		secs = time % 60;
		return mins + " minutes " + secs + " seconds";
	}

	public void returnInfo(int n) {
		System.out.println("Song name: " + Songs.get(n).getTitle());
		System.out.println("Song artist: " + Songs.get(n).getName());
		System.out.println("Song genre: " + Songs.get(n).getGenre());
		System.out.println("Song rating: " + Songs.get(n).getRating() + " out of 5");
		System.out.println("Song length: " + Songs.get(n).getTime());
	}
	public ArrayList<Song> getSong() {
		return Songs;
	}
	public String getSongTitle(int index) {
		return Songs.get(index).getTitle();
	}
	public String getSongName(int index) {
		return Songs.get(index).getName();
	}
	public String getSongGenre(int index) {
		return Songs.get(index).getGenre();
	}
	public int getSongRating(int index) {
		return Songs.get(index).getRating();
	}
	public String getSongTime(int index) {
		return Songs.get(index).getTime();
	} 
	public int compareTo (CD cd1) {
		return this.title.compareToIgnoreCase(cd1.title);
	}
}

class SortByTitle implements Comparator <Song> { //comparator used in binarySearch for the specific CD in the CDs arrayList
	public int compare (Song s1, Song s2) {
		return s1.getTitle().compareToIgnoreCase(s2.getTitle());
	}
}
class SortByArtist implements Comparator <Song> { //comparator used in binarySearch for the specific CD in the CDs arrayList
	public int compare (Song s1, Song s2) {
		return s1.getName().compareToIgnoreCase(s2.getName());
	}
}
class SortByTime implements Comparator <Song> { //comparator used in binarySearch for the specific CD in the CDs arrayList
	public int compare (Song s1, Song s2) {
		return Integer.toString(s1.totalLength()).compareToIgnoreCase(Integer.toString(s2.totalLength()));
	}
}