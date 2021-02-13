
public class PartB5 {
	public static int count (String s, char c) {
		if (s.indexOf(c) == -1)
			return 0;
		if (s.length() == 0)
			return 0;
		return count(s.substring(s.indexOf(c) + 1), c) + 1;
	}
	public static void main(String[] args) {
		System.out.println(count("this is a test", 's'));
	}

}
