 import java.util.PriorityQueue;
import java.util.Scanner;

class Node {
    char ch;
    int freq;
    Node left, right;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    Node(int freq, Node left, Node right) {
        this.ch = '-';
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}

public class Haffman {

    // Recursive traversal to generate variable-length prefix codes
    static void printCodes(Node root, String code) {
        if (root == null) return;

        // Leaf node reached
        if (root.left == null && root.right == null) {
            System.out.println(root.ch + " : " + code);
            return;
        }

        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of characters: ");
        int n = sc.nextInt();

        char[] chars = new char[n];
        int[] freq = new int[n];

        System.out.println("Enter characters and their frequencies:");
        for (int i = 0; i < n; i++) {
            chars[i] = sc.next().charAt(0);
            freq[i] = sc.nextInt();
        }

        // Min-PriorityQueue based on frequency
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.freq - b.freq);

        for (int i = 0; i < n; i++) {
            pq.add(new Node(chars[i], freq[i]));
        }

        // Greedy step: combine two nodes with the lowest frequency
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();

            Node merged = new Node(left.freq + right.freq, left, right);
            pq.add(merged);
        }

        Node root = pq.peek();

        System.out.println("\nHuffman Codes:");
        // Handle single-character edge case
        if (root.left == null && root.right == null) {
            System.out.println(root.ch + " : 0");
        } else {
            printCodes(root, "");
        }

        sc.close();
    }
} 
    

