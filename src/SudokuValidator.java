import java.util.HashMap;
public class SudokuValidator {
	
	public static void main(String [] args) {
		
		int [][] testCase1= {
				{1,2,3,4,5,6,7,8,9},
				{4,5,6,7,8,9,1,2,3},
				{7,8,9,1,2,3,4,5,6},
				{9,1,2,3,4,5,6,7,8},
				{3,4,5,6,7,8,9,1,2},
				{6,7,8,9,1,2,3,4,5},
				{8,9,1,2,3,4,5,6,7},
				{2,3,4,5,6,7,8,9,1},
				{5,6,7,8,9,1,2,3,4}
				};
		
		System.out.println(checkMatrix(testCase1));
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
//			
//			
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
	
	
}
// {2.3.4}
// {7,9,6}
// {3,4,8}