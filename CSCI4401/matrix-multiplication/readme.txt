Programming Assignment 1 - readme.txt

---------------------------------
Files included with this project:
---------------------------------
matrixmul.java matmulperform.java

---------------
How to compile:
---------------
To compile both files run: "javac *.java"

-----------
How to run:
-----------
Running matrixmul.java: "java matrixmul"
    You will then be prompted for the number of cores you want to utilize.
    If 1 core is chosen the program will be run sequentially.
    If more than 1 is chosen it will be run in parallel.
    The program will then print to the the result and output the result to output.txt.
    You can continue running with different number of cores or type -1 to exit.

Running matmulperform.java: "java matmulperform"
    The program will run 100 iterations from n=2 to n=50 and take the average execution time in miliseconds.
    The time will be computed in parallel(4 cores utilized) and sequentially.
    It will then be printed to 2 .csv files(Paral_exe.csv and Seq_exe.csv)


