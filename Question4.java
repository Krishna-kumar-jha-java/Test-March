import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Hey Mam, Please enter the number of rows: ");
        int m = sc.nextInt();
        
        System.out.println("Hey Mam, Please enter the number of columns: ");
        int n = sc.nextInt();
        
        int[][] A = new int[m][n];
        int[][] B = new int[m][n];
        int[][] C = new int[m][n];

        System.out.println("Mam, Please enter the elements of Matrix A:");
        readMatrix(sc, A, m, n);

        System.out.println("Mam, Please enter the elements of Matrix B:");
        readMatrix(sc, B, m, n);

        // Adding matrices
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }

        System.out.println("Mam, Here is the resulting matrix after addition:");
        printMatrix(C, m, n);
        
        sc.close();
    }

    public static void readMatrix(Scanner sc, int[][] matrix, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
    }

    public static void printMatrix(int[][] matrix, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
