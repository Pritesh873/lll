
import java.util.Scanner;

public class Fibonacci {

    // Recursive method
    static int fibRecursive(int n) {
        if (n <= 1)
            return n;

        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    // Non-recursive method
    static void fibIterative(int n) {
        int a = 0, b = 1, c;

        System.out.print("Non-Recursive Series: ");

        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            c = a + b;
            a = b;
            b = c;
        }

        System.out.println();
    }

    // Recursive series printing
    static void printRecursive(int n) {
        System.out.print("Recursive Series: ");

        for (int i = 0; i < n; i++) {
            System.out.print(fibRecursive(i) + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of terms: ");
        int n = sc.nextInt();

        if (n < 0) {
            System.out.println("Invalid input");
        } else {
            fibIterative(n);
            printRecursive(n);
        }

        sc.close();
    }
}
