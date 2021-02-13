/*	Name: Andy Zou
 *	Course Code: ICS4U
 *	Date: 09-16-2019
 *	Introduction: This program allows the user to input product name, product price, and how many they have on hand
 * 				and the program will return the total inventory value and they highest value item in the inventory.
 */
import java.util.*;

public class PartA {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Inventory Management Program \n");
		boolean more = true;
		boolean rightNumber = true;
		String productName = "";
		double unitPrice = 0;
		int amount = 0;
		double totalPrice = 0;
		double totalValue = 0;
		String highestName = "";
		double highestPrice = 0;
		do {
			System.out.print("Please enter the name of the next product: ");
			productName = in.nextLine();
			productName = productName.trim();
			if (productName.equalsIgnoreCase("") == true || productName.matches("[a-zA-Z0-9 ]+") !=  true) { // If the entered values are other than alphanumeric characters
				do {
					System.out.print("Please enter a valid name for the product: ");
					productName = in.nextLine();
					productName = productName.trim();
				}
				while(productName.equalsIgnoreCase("") == true || productName.matches("[a-zA-Z0-9 ]+") !=  true);
			}
			try { //This try and catch checks if the input is valid for a double
				System.out.print("Please enter the unit price for " + productName + ": $");
				unitPrice = Double.parseDouble(in.nextLine());
			}
			catch(NumberFormatException f) {
				do {
					try {
						rightNumber = true;
						System.out.print("Please enter a valid unit price for " + productName + ": $");
						unitPrice = Double.parseDouble(in.nextLine());
					}
					catch(NumberFormatException e) { // catch if the entered characters
						rightNumber = false;
					}
				}
				while (rightNumber == false);
			}
			if (unitPrice < 0) { // If the unit price was negative
				do {
					try {
						System.out.print("Please enter a valid unit price for " + productName + ": $");
						unitPrice = Double.parseDouble(in.nextLine());
					}
					catch(NumberFormatException f) { // catch if the entered characters
						do {
							try {
								rightNumber = true;
								System.out.print("Please enter a valid unit price for " + productName + ": $");
								unitPrice = Double.parseDouble(in.nextLine());
							}
							catch(NumberFormatException e) {
								rightNumber = false;
							}
						}
						while (rightNumber == false);
					}
				}
				while(unitPrice < 0);
			}
			
			
			do { // How many you have on hand
				try {
					rightNumber = true;
					System.out.print("How many " + productName + " do you have on hand?: ");
					amount = Integer.parseInt(in.nextLine());
				}
				catch (NumberFormatException g) {
					rightNumber = false;
				}
			}
			while (amount <= 0 || rightNumber == false);
			
			
			totalPrice = amount * unitPrice; // calculating total value
			if (totalPrice > highestPrice) {
				highestPrice = totalPrice;
				highestName = productName;
			}
			totalValue += totalPrice;
			System.out.printf("You have %d %s @ $%.2f for a total value of: $%.2f" , amount, productName, unitPrice, totalPrice);
			System.out.println("");
			System.out.print("Do you have any more products? (y/n): ");
			String yesNo = in.nextLine();
			if (yesNo.equalsIgnoreCase("y") == true) {
				more = true;
			}
			if (yesNo.equalsIgnoreCase("n") == true) {
				more = false;
			}
		}
		while(more == true);
		System.out.printf("The total value of all inventory is : $%.2f", totalValue);
		System.out.println("");
		System.out.printf("The item with the highest total value is $%.2f for %s.", highestPrice, highestName);
		System.out.println("");
		System.out.println("Thank you for using the Inventory Management Program.");
	}
}
