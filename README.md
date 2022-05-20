# sudoku-validator
This project is my solution to validate a Sudoku board. To test my solution I used Sudoku tables saved in CSV files, so instead of just creating the algorithm I also had
to read and transform CSV files to Java 2D arrays. I checked 3 Sudoku rules: 
- The rows should be 1 to 9 with no duplicates
- The columns should be 1 to 9 with no duplicates
- The 9 different 3x3 subgrids should be 1 to 9 with no duplicates
All of the test cases that you send via CSV format are being read and evaluated using my solution.

## running the code
To run the program just run the Sudoku.java located inside the src folder. Running the program will display: 
- The file that is being tested 
- The Sudoku itself 
- The expected value 
- The result of the solution
for each Solution.

If you are having trouble running the file, here is a short tutorial I found online:
https://beginnersbook.com/2013/05/first-java-program/
