import java.util.Arrays;
import java.util.Scanner;

class Item {
    double val, wt, ratio;

    Item(double val, double wt) {
        this.val = val;
        this.wt = wt;
        this.ratio = val / wt;
    }
}

public class fsack {

    static double getMaxValue(Item[] items, double capacity) {
        // Sort items by profit-to-weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalVal = 0.0;

        for (Item item : items) {
            if (capacity >= item.wt) {
                // Take whole item
                capacity -= item.wt;
                totalVal += item.val;
            } else {
                // Take fraction of item
                totalVal += item.ratio * capacity;
                break;
            }
        }
        return totalVal;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        Item[] items = new Item[n];
        System.out.println("Enter value and weight for each item:");
        for (int i = 0; i < n; i++) {
            double v = sc.nextDouble();
            double w = sc.nextDouble();
            items[i] = new Item(v, w);
        }

        System.out.print("Enter knapsack capacity: ");
        double capacity = sc.nextDouble();

        System.out.printf("Maximum Value = %.2f\n", getMaxValue(items, capacity));
        sc.close();
    }
}