import java.util.*;
public class SortByTitle implements Comparator <Title>{ //this class is used to sort titles for title arraylist
		public int compare (Title o1, Title o2) {
			return o1.getTitle().toLowerCase().trim().compareTo(o2.getTitle().toLowerCase().trim());
		}
}
