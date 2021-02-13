/*	Name: Andy Zou 
 *	Course Code: ICS4U
 *	Date: 11-12-19
 *	Introduction: This class stores the specific song info
 */
public class Song {
	private String title = "";
	private String name = "";
	private String genre = "";
	private int rating = 0;
	private Time length;
	private Time time;
	
	
	public Song (String Title, String Name, String Genre, int Rating, String Time) { //constructor
		title = Title;
		name = Name;
		genre = Genre;
		rating = Rating;
		length = new Time(Time);
		time = new Time(Integer.parseInt(Time.substring(0, Time.indexOf(':'))), Integer.parseInt(Time.substring(Time.indexOf(':') + 1, Time.length() - 1)));
	}
	
	//getter methods
	public String getTitle() {
		return title;
	}
	public String getName() {
		return name;
	}
	public String getGenre() {
		return genre;
	}
	public int getRating() {
		return rating;
	}
	public String getTime() {
		return length.getTime();
	}
	public int totalLength() {
		return time.getTotalSec();
	}
	public boolean equals (Object s1) {
		return title.equalsIgnoreCase(((Song)s1).title);
	}
}
