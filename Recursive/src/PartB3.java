public class PartB3 {
	public static String repeat(char s, int n) {
		String w = Character.toString(s);
		if (n == 1)
			return w;
		return w + repeat(s, n-1);
	}
	public static void main(String[] args) {
		System.out.println(repeat('x',10));
	}

}
