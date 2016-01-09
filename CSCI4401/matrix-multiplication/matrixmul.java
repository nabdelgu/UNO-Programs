/**
 * @date 10/10/2015
 * @author Noah Abdelguerfi
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.floor;
import java.util.InputMismatchException;
import java.util.concurrent.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class matrixmul extends Thread {

    public static final int availibleCores = Runtime.getRuntime().availableProcessors();
    private static int rowsm1;
    private static int colsm1;
    private static int rowsm2;
    private static int colsm2;

    protected static void printMatrix(double matrix[][]) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%.3f", matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();

    }

    private static void outputToFile(String fileName, double matrix[][]) {
        try {
            PrintWriter writer = new PrintWriter(new File(fileName));
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    writer.printf("%.3f", matrix[i][j]);
                    writer.print(" ");
                }
                writer.println();
            }

            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void preRead(String fileName) {

        try {
            String line = null;
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                if (line.trim().length() == 0) {
                    break;
                }
                colsm1 = (line.split(",")).length;
                rowsm1++;
            }

            while ((line = br.readLine()) != null) {
                if (line.trim().length() == 0) {
                    break;
                }
                colsm2 = (line.split(",")).length;
                rowsm2++;
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static class Matrix {

        double[][] m1;
        double[][] m2;
        double result[][];

        public Matrix(double[][] m1, double[][] m2, double result[][]) {
            this.m1 = m1;
            this.m2 = m2;
            this.result = result;
        }
    }

    protected static Matrix readMatricies(String fileName) {
        String currentLine = null;

        double[][] m1 = new double[rowsm1][colsm1];
        double[][] m2 = new double[rowsm2][colsm2];
        double[][] result = new double[rowsm1][colsm2];
        Matrix matrix = new Matrix(m1, m2, result);

        try {
            String line = null;
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String append = null;
            int count = 0;
            int j = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().length() == 0) {
                    break;
                }

                String tokens[] = line.trim().split(",");

                for (int i = 0; i < tokens.length; i++) {
                    double current = Double.parseDouble(tokens[i]);
                    m1[j][i] = current;
                }

                j++;

            }
            j = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().length() == 0) {
                    break;
                }

                String tokens[] = line.trim().split(",");

                for (int i = 0; i < tokens.length; i++) {
                    double current = Double.parseDouble(tokens[i]);
                    m2[j][i] = current;
                }

                j++;

            }
            return matrix;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(matrixmul.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(matrixmul.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    protected static int numberOfThreads(int cores, int rowsm1) {
        int numberOfThreads;
        if (rowsm1 < cores) {
            numberOfThreads = rowsm1;
        } else {
            numberOfThreads = cores;
        }
        return numberOfThreads;
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        preRead("./input0.txt");

        System.out.println("Enter the number of cores");
        Scanner input = new Scanner(System.in);
        int cores = 0;
        while (true) {
            try {
                cores = input.nextInt();

                if (cores > availibleCores) {
                    System.out.println("You do not have that many cores avalible. You have " + availibleCores + " availible.");
                }
                if (cores <= availibleCores) {
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer. Try again");
                input = new Scanner(System.in);
                continue;
            }
        }
        System.out.println("----------------------------");
        System.out.println("Number of Processors: " + cores);
        System.out.println("----------------------------");
        Matrix matrix = readMatricies("./input0.txt");
        double m1[][] = matrix.m1;
        double m2[][] = matrix.m2;
        double result[][] = matrix.result;

        if (cores == 1) {

            for (int i = 0; i < rowsm1; i++) {
                for (int j = 0; j < colsm2; j++) {
                    for (int k = 0; k < colsm1; k++) {
                        result[i][j] += m1[i][k] * m2[k][j];
                    }
                }
            }

            printMatrix(result);

        }

        if (cores > 1) {

            boolean print = true;

            int numberOfThreads = numberOfThreads(cores, rowsm1);

            double numberThreads = Double.parseDouble(Integer.toString(numberOfThreads));
            int a = (int) (rowsm1 / numberThreads);
            Thread[] threads = new Thread[numberOfThreads];

            ExecutorService e = Executors.newFixedThreadPool(numberOfThreads);

            for (int t = 0; t < numberOfThreads; t++) {
                threads[t] = new Thread(new matrixmul.MatrixThread(t, numberOfThreads, a, m1, m2, result, print));
                threads[t].start();
            }

            for (int t = 0; t < numberOfThreads; t++) {
                threads[t].join();
            }
            System.out.println();
            System.out.println("----------------------------");
            System.out.println("Final result");
            System.out.println("----------------------------");
            printMatrix(result);

        }

    }

    public static class MatrixThread implements Runnable {

        public final int t;
        private final int totalThreads;
        double[][] result;
        double[][] m1;
        double[][] m2;
        boolean print;
        public int a;

        public MatrixThread(int t, int totalThreads, int a, double m1[][], double m2[][], double result[][], boolean print) {
            this.result = result;
            this.totalThreads = totalThreads;
            this.m1 = m1;
            this.m2 = m2;
            this.t = t;
            this.a = a;
            this.print = print;
        }

        @Override
        public void run() {
            final int maxRow = t != (totalThreads - 1) ? (t + 1) * a : rowsm1;
            for (int i = t * a; i < maxRow; i++) {
                for (int j = 0; j < m2[0].length; j++) {
                    for (int k = 0; k < m1[0].length; k++) {

                        result[i][j] += m1[i][k] * m2[k][j];

                        if (print) {
                            System.out.println(Thread.currentThread().getName() + " " + t + " " + (floor(1000 * m1[i][k] + 0.5) / 1000) + " * " + (floor(1000 * m2[k][j] + 0.5) / 1000) + " = " + (floor(1000 * (m2[i][k] * m1[k][j]) + 0.5) / 1000));
                        }                       

                    }
                }
            }

        }

    }

}
