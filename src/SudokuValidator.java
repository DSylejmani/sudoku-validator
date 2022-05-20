import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuValidator {

	public static void main(String[] args) {
		// This variable keeps track of the File Names and the Expected Outputs
		String[][] tests = {
				// File Name				Expected Output
				{ "basicGrid.csv", 	        "Valid" }, 
				{ "basicGrid2.csv",         "Valid" },
				{ "columnError.csv",        "Not Valid" }, 
				{ "outOfRangeError.csv",    "Not Valid" },
				{ "outOfRangeError2.csv",   "Not Valid" }, 
				{ "rowError.csv",           "Not Valid" },
				{ "rubbishError.csv",       "Not Valid" }, 
				{ "subGridError.csv",       "Not Valid" },
				{ "sumValidationError.csv", "Not Valid" } 
			};
		// Here we print info about each Test Case
		for (int i = 0; i < tests.length; i++) {
			int[][] testMatrix = csvToMatrix("Test Cases\\" + tests[i][0]);
			System.out.println("-----------------------------------");
			System.out.println("The Sudoku inside " + tests[i][0]); // Description of the Test Case
			System.out.println(matrixToString(testMatrix));         // The Sudoku itself 
			System.out.println("Expected: " + tests[i][1]);         // Expected Output
			if (checkMatrix(testMatrix)) {                          // Results from my algorithm
				System.out.println("Result: Valid");
			} else {
				System.out.println("Result: Not Valid");
			}
			System.out.println("-----------------------------------");
		}
	}

	// This method will take a relative/absolute path to a CSV File and transform it into a 2D Array 
	public static int[][] csvToMatrix(String path) {
		int[][] rez = new int[9][9];
		int currentRow = 0;
		try {
			Scanner sc = new Scanner(new File(path));
			sc.useDelimiter(",");
			while (sc.hasNext()) {
				String s = sc.nextLine();
				String[] nums = s.split(",");
				for (int i = 0; i < nums.length; i++) {
					rez[currentRow][i] = Integer.parseInt(nums[i]);
				}
				currentRow++;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("File was not found.");
		}
		return rez;
	}

	// This is the main method that checks if a Sudoku is Valid
	public static boolean checkMatrix(int[][] matrix) {
		// Checking the rows
		boolean rez = false;
		for (int i = 0; i < matrix.length; i++) {
			if (!checkValidRow(matrix[i])) {
				return false;
			}
		}

		int[] currentColumn = new int[matrix[0].length];
		// Checking the columns
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				currentColumn[j] = matrix[j][i];
			}
			if (!checkValidRow(currentColumn)) {
				return false;
			}
		}
		// Checking the 3x3 SubGrids
		int currentSquareIndex = 0;

		int[] currentSquareArray = new int[9];
		for (int l = 0; l < 9; l += 3) {
			for (int i = 0; i < 9; i += 3) {
				for (int j = l; j < l + 3; j++) {
					for (int k = i; k < i + 3; k++) {
						currentSquareArray[currentSquareIndex] = matrix[j][k];
						currentSquareIndex++;
					}
				}
				currentSquareIndex = 0;
				if (!checkValidRow(currentSquareArray)) {
					return false;
				}
			}
		}
		return true;
	}

	// This method checks if an array/row is a valid Sudoku array/row
	public static boolean checkValidRow(int[] row) {
		HashMap<Integer, Integer> valuesMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < row.length; i++) {
			if (row[i] < 1 || row[i] > 9) {          // Checks if all numbers are Valid Sudoku Values
				return false;
			}
			if (valuesMap.containsKey(row[i])) {     // Checks if there are any Duplicate Values
				return false;
			} else {
				valuesMap.put(row[i], row[i]);
			}
		}
		return true;
	}

	// This method converts an Array to a String
	public static String arrayToString(int[] array) {
		String rez = "";
		for (int i = 0; i < array.length; i++) {
			rez = rez + array[i] + ", ";
		}
		return rez;
	}

	// This method converts a Matrix to a String
	public static String matrixToString(int[][] matrix) {
		String rez = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				rez = rez + matrix[i][j] + ", ";
			}
			rez = rez + "\n";
		}
		return rez;
	}
}
