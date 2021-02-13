public class Genre implements Comparable <Genre>{ //implements the comparable interface for use in the binarySearch and sort
	private String title = "";
	private String rating = "";
	private String genre = "";
	public Genre (String t, String r, String g) { //Constructor method
		this.title = t;
		this.rating = r;
		this.genre = g;
	}
	public String getTitle () { //getter methods
		return title;
	}
	public String getRating () {
		return rating;
	}
	public String getGenre () {
		return genre;
	}
	public int compareTo (Genre g){ //sorting the object by alpha order
		return this.title.compareToIgnoreCase(g.title);
	}

}
