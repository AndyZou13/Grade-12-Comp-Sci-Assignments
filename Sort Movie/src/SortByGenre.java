import java.util.Comparator;
public class SortByGenre implements Comparator <Title>{ //this class is used to sort Genres for title arraylist
	public int compare (Title o1, Title o2) {
		return o2.getGenre().trim().compareToIgnoreCase(o1.getGenre().trim());
	}
}
