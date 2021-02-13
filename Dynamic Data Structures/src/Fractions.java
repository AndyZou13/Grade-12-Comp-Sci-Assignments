/*	Name: Andy Zou  
 *	Course Code: ICS4U
 *	Date: 11-28-19
 *	Introduction: This class is for managing the fractions for problem 2
 */
public class Fractions implements Comparable <Fractions>{ //implements comparable to override the compareTo method for the TreeSet
	public int n;
	public int d;
	public double decimal;
	
	public Fractions (String f) { //constructor class
		n = Integer.parseInt(f.substring(0, f.indexOf("/")));
		d = Integer.parseInt(f.substring(f.indexOf("/") + 1));
		decimal = (double)n/(double)d;
	}
	
	public double getDecimal () {
		return decimal;
	}

	public int compareTo (Fractions f) { //overriding the compare for hashset
		if (decimal > f.getDecimal())
			return 1;
		if (decimal < f.getDecimal())
			return -1;
		return 0;
	}
	
	public String toString () { //overriding toString method when calling a fraction
		return n + "/" + d;
	}
}
