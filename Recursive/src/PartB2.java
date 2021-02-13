public class PartB2 {
	public static int multiply (int a, int b) {
		if (b == 1)
			return a;
		if (a == 0 || b == 0)
			return 0;
		if (a < 0 ) {
			a *= -1;
			return -1* (a + multiply (a, b-1));
		}
		if (b < 0) {
			b *= -1;
			return -1* (a + multiply (a, b-1));
		}
		return a + multiply (a, b-1);
	}
	public static void main(String[] args) {
		System.out.println(multiply(-8,-2));
	}

}
