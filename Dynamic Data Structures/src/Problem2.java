/*	Name: Andy Zou  
 *	Course Code: ICS4U
 *	Date: 11-28-19
 *	Introduction: This program allows the user to enter a maximum denominator, a minimum fraction and a maximum
 *	fraction to find all the fractions with the maximum denominator and all the fractions that come in between the min and
 * 	max fractions.
 */
import java.io.*;
import java.util.*;
public class Problem2 {
	public static int createFraction (int d) { //creates all the fractions with a certain denominator, returns the TreeSet size, and takes in the denominator
		Set <Fractions> newSet = new TreeSet <Fractions> (); //to store and count all the fractions without reeats
		for (double i = 0; i <= d; i ++) { //double for loop to run through all the fractions
			for (double j = 0; j <= d; j ++) {
				if (i/j <= 1 && i/j >= 0) 
					newSet.add(new Fractions((int)i + "/" + (int)j)); //adding the new fraction to the list
			}
		}
		return newSet.size();
	}
	public static int inclusiveFractions(Fractions l, Fractions h, int d) { //finds all the inclusive fractions between min and max fractions
		Set <Fractions> newSet = new TreeSet <Fractions> ();
		int total = 0;
		for (double i = 0; i <= d; i ++) {
			for (double j = 0; j <= d; j ++) {
				if (i/j <= 1 && i/j >= 0) 
					newSet.add(new Fractions((int)i + "/" + (int)j));
			}
		}
		Iterator <Fractions> iter = newSet.iterator(); //iterator to loop through all the elements in the TreeSet
		while (iter.hasNext()) {
			Fractions i = iter.next();
			if (i.getDecimal() >= l.getDecimal() && i.getDecimal() <= h.getDecimal()) //if they're value fits in between the min and max fraction
				total ++;
		}
		return total;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in); //user input
		int denominator = -1;
		String fraction; 
		while (denominator <= -1) { //askes for all the necessary user input
			try {
				System.out.println("Enter the maximum denominator: "); 
				denominator = Integer.parseInt(in.nextLine().trim());
			}
			catch (NumberFormatException e) {
			}
		}
		System.out.println("Enter the lower limit: ");
		String low = in.nextLine().trim(); //askes for the lower fraction
		
		while (low.indexOf('/') <= 0 || low.indexOf('-') != -1 ) { //error check
			System.out.println(3);
			System.out.println("Enter the lower limit: ");
			low = in.nextLine().trim();
			if (low.indexOf("/") <= 0) {
				System.out.println(1);
				System.out.println("Enter the lower limit: ");
				low = in.nextLine().trim();
			}
			if (low.indexOf("-") != -1) {
				System.out.println(2);
				System.out.println("Enter the lower limit: ");
				low = in.nextLine().trim();
			}
		}
		Fractions lower = new Fractions (low);
		System.out.println("Enter the higher limit: ");
		String high = in.nextLine().trim();
		while (high.indexOf('/') <= 0 || high.indexOf('-') != -1) {
			System.out.println(3);
			System.out.println("Enter the higher limit: ");
			high = in.nextLine().trim();
			if (high.indexOf("/") <= 0) {
				System.out.println(1);
				System.out.println("Enter the higher limit: ");
				high = in.nextLine().trim();
			}
			if (high.indexOf("-") != -1) {
				System.out.println(2);
				System.out.println("Enter the higher limit: ");
				high = in.nextLine().trim();
			}
		}
		Fractions higher = new Fractions (high);
		while (lower.getDecimal() > higher.getDecimal()) //error check
			while (high.indexOf('/') <= 0 || high.indexOf('-') != -1) { //error check
				System.out.println(3);
				System.out.println("Enter the higher limit: ");
				high = in.nextLine().trim();
				if (high.indexOf("/") <= 0) {
					System.out.println(1);
					System.out.println("Enter the higher limit: ");
					high = in.nextLine().trim();
				}
				if (high.indexOf("-") != -1) {
					System.out.println(2);
					System.out.println("Enter the higher limit: ");
					high = in.nextLine().trim();
				}
				higher = new Fractions (high);
			}
		System.out.println("");
		System.out.println("Total number of fractions: " + createFraction(denominator));
		System.out.println("Number of fractions between " + lower + " and " + higher + " inclusive: " + inclusiveFractions(lower, higher, denominator));
	}

}
