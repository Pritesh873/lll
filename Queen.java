import java.util.Scanner;

public class Queen {

    static int n;
    static int fixedRow, fixedCol;

    // Check if placing a queen at (row, col) conflicts with any existing queen
    static boolean isSafe(int[][] board, int row, int col) {
        // 1. Check the entire row
        for (int j = 0; j < n; j++) {
            if (j != col && board[row][j] == 1) {
                return false;
            }
        }

        // 2. Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }

        // 3. Check lower-left diagonal
        for (int i = row + 1, j = col - 1; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1) return false;
        }

        // 4. Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1) return false;
        }

        // 5. Check lower-right diagonal
        for (int i = row + 1, j = col + 1; i < n && j < n; i++, j++) {
            if (board[i][j] == 1) return false;
        }

        return true;
    }

    // Backtracking solver
    static boolean solve(int[][] board, int col) {
        // Base case: all columns have been processed
        if (col == n) {
            return true;
        }

        // If the current column already contains the fixed queen, skip to next column
        if (col == fixedCol) {
            return solve(board, col + 1);
        }

        // Try placing a queen in each row of the current column
        for (int row = 0; row < n; row++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;

                if (solve(board, col + 1)) {
                    return true;
                }

                // Backtrack
                board[row][col] = 0;
            }
        }

        return false;
    }

    static void printBoard(int[][] board) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] == 1 ? "Q " : ". ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter N (board size): ");
        n = sc.nextInt();

        if (n < 1) {
            System.out.println("Invalid board size.");
            sc.close();
            return;
        }

        int[][] board = new int[n][n];

        System.out.print("Enter row for the first Queen (0 to " + (n - 1) + "): ");
        fixedRow = sc.nextInt();
        System.out.print("Enter col for the first Queen (0 to " + (n - 1) + "): ");
        fixedCol = sc.nextInt();

        if (fixedRow < 0 || fixedRow >= n || fixedCol < 0 || fixedCol >= n) {
            System.out.println("Invalid coordinate: position is out of board bounds.");
            sc.close();
            return;
        }

        // Place the first Queen permanently
        board[fixedRow][fixedCol] = 1;

        // Start recursive placement from column 0
        if (solve(board, 0)) {
            System.out.println("\nFinal N-Queens Matrix (Q = Queen, . = Empty):");
            printBoard(board);
        } else {
            System.out.println("\nNo solution exists with a Queen fixed at (" + fixedRow + ", " + fixedCol + ").");
        }

        sc.close();
    }
}