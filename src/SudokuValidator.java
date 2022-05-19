import java.util.HashMap;
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;  
public class SudokuValidator {
	
	public static void main(String [] args) {
		
		
		int [][] test= csvToMatrix("C:\\Users\\drilo\\Desktop\\Sudoku_Validator_Test_Cases\\Test Cases\\sumValidationError.csv");
		System.out.println(matrixToString(test));
		System.out.println(checkMatrix(test));

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
			if(checkDuplicates(matrix[i])) {
				System.out.println("i="+i);
				return false;
			}
		}
		
		int [] currentColumn=new int [matrix[0].length];
//		// i kontrollojme shtyllat
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[i].length; j++) {
			currentColumn[j]=matrix[j][i];
		}
			if(checkDuplicates(currentColumn)) {
				System.out.println("currentcolumn:"+ arrayToString(currentColumn));
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
			if(checkDuplicates(currentSquare)) {
				System.out.println("currentSquare: "+arrayToString(currentSquare));
				return false;
			}
		}			
		}
		return true;
	}
	
	public static boolean checkDuplicates(int [] row) {
	    HashMap<Integer,Integer> hm= new HashMap<Integer,Integer>();
	    for(int i=0; i<row.length; i++) {
	    	
	    	if(hm.containsKey(row[i])) {
	    		return true;
	    	}
	    	else {
	    		hm.put(row[i],row[i]);
	    	}
	    }
	    return false;
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