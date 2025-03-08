import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hey Mam, Please Enter a Number to get Fibonacci: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), a = 0, b = 1;
        sc.close();

        System.out.print(a);
        if (n > 1) System.out.print(" " + b);
        for (int i = 2; i < n; i++) {
            System.out.print(" " + (b += a = b - a));
        }
    }
}
