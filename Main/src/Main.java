import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int lt = 1;
		int lb = 0;
		int rt = 0;
		int rb = 0;
		Scanner in = new Scanner(System.in);
		String word = in.nextLine();
		for (int i = 0; i < word.length(); i++) {
			if (Character.toString(word.charAt(i)).compareToIgnoreCase("v") == 0) {
				if (lt == 1) {
					rt = lt;
					lt = 0;
				}
				else if (lb == 1) {
					rb = lb;
					lb = 0;
				}
				else if (rt == 1) {
					lt = rt;
					rt = 0;
				}
				else if (rb == 1) {
					lb = rb;
					rb = 0;
				}
			}
			if (Character.toString(word.charAt(i)).compareToIgnoreCase("h") == 0) {
				if (lt == 1) {
					lb = lt;
					lt = 0;
				}
				else if (rt == 1) {
					rb = rt;
					rt = 0;
				}
				else if (lb == 1) {
					lt = lb;
					lb = 0;
				}
				else if (rb == 1) {
					rt = rb;
					rb = 0;
				}
			}
		}
		if (lt == 1) {
			System.out.println("1" + " " + "2");
			System.out.println("3" + " " + "4");
		}
		else if (lb == 1) {
			System.out.println("3" + " " + "4");
			System.out.println("1" + " " + "2");
		}
		else if (rt == 1) {
			System.out.println("2" + " " + "1");
			System.out.println("4" + " " + "3");
		}
		else if (rb == 1) {
			System.out.println("4" + " " + "3");
			System.out.println("2" + " " + "1");
		}
	}

}
