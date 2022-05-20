import java.util.HashMap;
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;  
public class SudokuValidator {
	
	public static void main(String [] args) {
		String [][] tests= {
				{"basicGrid.csv",			"Valid"},
				{"basicGrid2.csv",			"Valid"},
				{"columnError.csv",			"Not Valid"},
				{"outOfRangeError.csv",		"Not Valid"},
				{"outOfRangeError2.csv",	"Not Valid"},
				{"rowError.csv",			"Not Valid"},
				{"rubbishError.csv",		"Not Valid"},
				{"subGridError.csv",		"Not Valid"},
				{"sumValidationError.csv",	"Not Valid"}
		};
		for(int i=0; i<tests.length; i++) {
			int [][] testMatrix=csvToMatrix("Test Cases\\"+tests[i][0]);
			System.out.println("-----------------------------------");
			System.out.println("The Sudoku inside "+tests[i][0]);
			System.out.println(matrixToString(testMatrix));
			System.out.println("Expected: "+tests[i][1]);
			if(checkMatrix(testMatrix)) {
				System.out.println("Result: Valid");
			}
			else {
				System.out.println("Result: Not Valid");
			}
			System.out.println("-----------------------------------");

		}
		
		

	}
	
	public static int [][] csvToMatrix(String path){
		int [][] rez=new int[9][9];
		int currentRow=0;
		try {
			Scanner sc = new Scanner(new File(path));
			sc.useDelimiter(",");
			while (sc.hasNext()) {
				String s=sc.nextLine();
				String [] nums=s.split(",");
				for(int i=0; i<nums.length; i++) {
					rez[currentRow][i]=Integer.parseInt(nums[i]);
				}
				currentRow++;
				
			}
			sc.close();
			}
		catch (FileNotFoundException e) {
			System.out.println("File was not found.");
		}
		return rez;
		}

	public static boolean checkMatrix(int [][] matrix) {
		//i kontrollojme rreshtat
		boolean rez=false;
		for(int i=0; i<matrix.length; i++) {
			if(!checkValidRow(matrix[i])) {
			//	System.out.println("i="+i);
				return false;
			}
		}
		
		int [] currentColumn=new int [matrix[0].length];
//		// i kontrollojme shtyllat
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[i].length; j++) {
			currentColumn[j]=matrix[j][i];
		}
			if(!checkValidRow(currentColumn)) {
		//		System.out.println("currentcolumn:"+ arrayToString(currentColumn));
				return false;
			}
		}
//		//i kontrollojme grupet 3x3
		int index=0;

		int [] currentSquare= new int [9];
		for(int l=0; l<9; l+=3) {
		for(int i=0; i<9;  i+=3) {
			for(int j=l; j<l+3; j++) {
				for(int k=i; k<i+3; k++) {
					currentSquare[index]=matrix[j][k];
					index++;
				}
			}
			index=0;
			if(!checkValidRow(currentSquare)) {
		//		System.out.println("currentSquare: "+arrayToString(currentSquare));
				return false;
			}
		}			
		}
		return true;
	}
	
	public static boolean checkValidRow(int [] row) {
	    HashMap<Integer,Integer> hm= new HashMap<Integer,Integer>();
	    for(int i=0; i<row.length; i++) {
	    	if(row[i]<1 || row[i]>9 ) {
	    		return false;
	    	}
	    	if(hm.containsKey(row[i])) {
	    		return false;
	    	}
	    	else {
	    		hm.put(row[i],row[i]);
	    	}
	    }
	    return true;
	}
	
	public static String arrayToString(int [] array) {
		 String rez="";
	     for(int i=0; i<array.length; i++) {
	    	 rez=rez+array[i]+", ";
	     }
	     return rez;
	}
	public static String matrixToString(int [][] matrix) {
		String rez="";
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[i].length; j++) {
				rez=rez+matrix[i][j]+", ";
			} 
			rez=rez+"\n";
		}
		return rez;
	}
	
	
}
// {2.3.4}
// {7,9,6}
// {3,4,8}