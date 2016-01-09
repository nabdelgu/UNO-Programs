/**
 * @date 10/10/2015
 * @author Noah Abdelguerfi
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class matmulperform {

    private static PrintWriter pr = null;
    
    public static class Matrix {

        double m1[][];
        double m2[][];

        public Matrix(double m1[][], double m2[][]) {
            this.m1 = m1;
            this.m2 = m2;
        }
    }

    private static Matrix generateMatrix(int n) {
        double m1[][] = new double[n][n];
        double m2[][] = new double[n][n];
        Random rn = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double randomNum = (rn.nextDouble() * 90.000d) - 100.000d;
                double random = Math.round(randomNum * 1000.0) / 1000.0;
                m1[j][i] = random;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double randomNum = (rn.nextDouble() * 90.000d) - 100.000d;
                double random = Math.round(randomNum * 1000.0) / 1000.0;
                m2[j][i] = random;
            }
        }

        Matrix matrix = new Matrix(m1, m2);
        return matrix;

    }

    private static void sequentialMatrixmul() throws FileNotFoundException {
        long timeStartSeq;
        long timeFinishSeq;
        long totalTimeSeq = 0;
        int b;
        pr = new PrintWriter("./Seq_exe.csv");
        pr.print("N,");
        pr.println("Average Time");
        for (int c = 2; c <= 50; c++) {
            Matrix m = generateMatrix(c);
            timeStartSeq = System.currentTimeMillis();
            for (b = 0; b < 99; b++) {

                double m1[][] = m.m1;
                double m2[][] = m.m2;
                double result[][] = new double[c][c];

                for (int i = 0; i < m1.length; i++) {
                    for (int j = 0; j < m1[0].length; j++) {
                        for (int k = 0; k < m1[0].length; k++) {
                            result[i][j] += m1[i][k] * m2[k][j];
                        }
                    }
                }
                timeFinishSeq = System.currentTimeMillis();
                totalTimeSeq += timeFinishSeq - timeStartSeq;

            }
            double averageTime = totalTimeSeq / 100;

            pr.print(c + ",");
            pr.println(averageTime + ",");

        }
        pr.close();

    }

    private static void parallelMatrixmul() throws InterruptedException, FileNotFoundException {
        long timeStartPar;
        long timeFinishPar;
        long totalTimePar = 0;
        pr = new PrintWriter("./Paral_exe.csv");
        pr.print("N,");
        pr.println("Average Time");
        for (int c = 2; c <= 50; c++) {
            Matrix m = generateMatrix(c);
            timeStartPar = System.currentTimeMillis();
            for (int b = 0; b < 99; b++) {
                int numberOfThreads = matrixmul.numberOfThreads(4, c);

                double m1[][] = m.m1;
                double m2[][] = m.m2;
                double result[][] = new double[c][c];
                boolean print = false;

                double numberThreads = Double.parseDouble(Integer.toString(numberOfThreads));
                int a = (int) (c / numberThreads);

                Thread[] threads = new Thread[numberOfThreads];

                ExecutorService e = Executors.newFixedThreadPool(numberOfThreads);

                for (int t = 0; t < numberOfThreads; t++) {
                    threads[t] = new Thread(new matrixmul.MatrixThread(t, numberOfThreads, a, m1, m2, result, print));
                }

                for (int t = 0; t < numberOfThreads; t++) {
                    e.execute(threads[t]);
                }

                e.shutdown();
            }
            timeFinishPar = System.currentTimeMillis();
            totalTimePar += timeFinishPar - timeStartPar;
            double averageTime = totalTimePar / 100;
            pr.print(c + ",");
            pr.println(averageTime + ",");
        }
        pr.close();

    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        sequentialMatrixmul();
        parallelMatrixmul();
    }

}
