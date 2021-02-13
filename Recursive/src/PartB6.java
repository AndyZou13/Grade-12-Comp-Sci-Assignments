
public class PartB6 {
	public static int rectangle (int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		return n + rectangle(n-1);
		}
	public static void main(String[] args) {
		System.out.println(rectangle(3));
	}

}
