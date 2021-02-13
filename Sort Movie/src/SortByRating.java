import java.util.*;
public class SortByRating implements Comparator <Title>{ //this class is used to sort ratings for title arraylist
	public int compare (Title o1, Title o2) {
		return o2.getRating().trim().compareTo(o1.getRating().trim());
	}
}
