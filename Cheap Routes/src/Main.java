import java.util.*;
import java.io.*;
public class Main {
	public static StringBuilder CheapestRoute = new StringBuilder();
	public static StringBuilder CheapestRouteAmount = new StringBuilder();
	public static int CheapestPrice = 0;
	public static StringBuilder CurrRoute = new StringBuilder();
	public static StringBuilder CurrRouteAmount = new StringBuilder();
	public static int CurrPrice = 0;

	public static void findPath (String [][] grid, int col, int row) {
		if (row == 0 && col == grid[0].length - 1 ) {
			CurrPrice += Integer.parseInt(grid[row][col].trim());
			if (CurrPrice < CheapestPrice) {
				CheapestPrice = CurrPrice;
				CheapestRoute = CurrRoute;
			}
			if (CheapestPrice == 0) {
				CheapestPrice = CurrPrice;
				CheapestRoute = CurrRoute;
				CheapestRouteAmount = CurrRouteAmount;
			}
			CurrRoute.delete(CurrRoute.length() - 5, CurrRoute.length());
			if (CurrRouteAmount.length() != 0) {
				CurrRouteAmount.delete(CurrRouteAmount.length() - 1, CurrRouteAmount.length());
			}
			CurrPrice -= Integer.parseInt(grid[row][col].trim());
			return;
		}

		if (row < 0) {
			if (CurrRoute.length() != 0) {
				CurrRoute.delete(CurrRoute.length() - 6, CurrRoute.length());
			}
			if (CurrRouteAmount.length() != 0) {
				CurrRouteAmount.delete(CurrRouteAmount.length() - 1, CurrRouteAmount.length());
			}
			return;
		}
		if (col > grid[0].length - 1) {
			if (CurrRoute.length() != 0) {
				CurrRoute.delete(CurrRoute.length() - 5, CurrRoute.length());
			}
			if (CurrRouteAmount.length() != 0) {
				CurrRouteAmount.delete(CurrRouteAmount.length() - 1, CurrRouteAmount.length());
			}
			return;
		}

		CurrRoute.append("NORTH ");
		CurrPrice += Integer.parseInt(grid[row][col].trim());
		CurrRouteAmount.append(grid[row][col].trim());
		findPath(grid, col, row - 1);		
		if (CurrRouteAmount.length() != 0) {
			CurrRouteAmount.delete(CurrRouteAmount.length() - 1, CurrRouteAmount.length());
		}
		if (CurrRoute.length() != 0) {
			CurrRoute.delete(CurrRoute.length() - 6, CurrRoute.length());
		}
		CurrPrice -= Integer.parseInt(grid[row][col].trim());


		CurrRoute.append("EAST ");
		CurrPrice += Integer.parseInt(grid[row][col].trim());
		CurrRouteAmount.append(grid[row][col].trim());
		findPath(grid, col + 1, row);
		if (CurrRouteAmount.length() != 0) {
			CurrRouteAmount.delete(CurrRouteAmount.length() - 1, CurrRouteAmount.length());
		}
		if (CurrRoute.length() != 0) {
			CurrRoute.delete(CurrRoute.length() - 5, CurrRoute.length());
		}
		CurrPrice -= Integer.parseInt(grid[row][col].trim());


		if (CurrRoute.length() == 0)
			return;
	}

	public static void main(String[] args) {
		Scanner inFile;
		int n;
		int rowN;
		int colN;
		String [][] table;

		try {
			inFile = new Scanner (new File ("Routes.txt"));
			while(inFile.hasNextLine()) {
				n = Integer.parseInt(inFile.nextLine().trim());
				for (int i = 1; i <= n; i ++) {
					System.out.println("Grid " + i + ": ");
					rowN = Integer.parseInt(inFile.nextLine());
					colN = Integer.parseInt(inFile.nextLine());
					table = new String [rowN][colN];
					for (int j = 0; j < rowN; j ++) {
						String newString = inFile.nextLine();
						table[j] = newString.split(" ");

					}
					findPath(table, 0, rowN - 1);
					System.out.println("Cheapest Route: " + CheapestRouteAmount);
					System.out.println("Directions: " + CheapestRoute);
					System.out.println("Cheapest Cost: $" + CheapestPrice);
				}
				inFile.close();
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("No file found!!");
		}

	}
}



