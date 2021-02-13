public class PartB1 {
	public static int addFib (int n) {
		if (n <= 0)
			return 0;
		if (n == 1 || n == 2)
			return 1;
		return addFib(n - 1) + addFib (n - 2);
	}
	public static void main(String[] args) {
		System.out.println(addFib(6));
	}

}
