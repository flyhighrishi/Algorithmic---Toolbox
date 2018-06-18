import java.util.Scanner;

public class Knapsack {
    
    static int optimalWeight_Repetition(int W, int[] w, int[] v) {
        // bottom-up from each smaller weight W.
        int[] table = new int[W + 1];
        for(int weight = 1; weight <= W; weight++) {
            for(int i = 0; i < w.length; i++) {
                if(w[i] > weight) continue;
                int value = table[weight - w[i]] + v[i];
                table[weight] = Math.max(table[weight], value);
            }
        }
        return table[W];
    }

   
    static int optimalWeight_NoRepetition(int W, int[] w, int[] v) {
        // bottom-up from all combinations of smaller item i and weight
        int n = w.length;
        int[][] table = new int[n + 1][W + 1];
        // if no item is chosen (i=0), value is 0;
        for(int i = 0; i <= n; i++) table[i][0] = 0;
        // if total weight is 0 (weight=0), value is 0;
        for(int j = 0; j <= W; j++) table[0][j] = 0;
        for(int i = 1; i <= n; i++) {
            for(int weight = 1; weight <= W; weight++) {
                // BZ: if total weight < wi, still regard as not taking i;
                // cannot simply ignoring as 0!
                table[i][weight] = table[i - 1][weight];
                // BZ: the ith item has index i - 1;
                // since row is from 0 to n;
                if (weight < w[i - 1]) continue;
                table[i][weight] = Math.max(
                        table[i - 1][weight - w[i - 1]] + v[i - 1],
                        table[i][weight]);
            }
        }
        return table[n][W];
    }

    static int optimalWeight(int W, int[] w) {
        // Example: 10 3 1 4 8 -> 9
        // build from all combinations of smaller i and weight;
        int n = w.length;
        int[][] table = new int[n + 1][W + 1];
        // fill in first row and first col by 0
        for (int j = 0; j <= W; j++) table[0][j] = 0;
        for (int i = 0; i <= n; i++) table[i][0] = 0;
        // either take ith: (w - wi, i - 1); or no taking: (w, i - 1)
        for (int i = 1; i <= n; i++) {
            for (int weight = 1; weight <= W; weight++) {
                // even wi exceeds, keep value of (w, i - 1) as no taking
                table[i][weight] = table[i - 1][weight];
                // BZ: the ith item has index i - 1, since 0~n
                if (weight < w[i - 1]) continue;
                table[i][weight] = Math.max(
                        table[i][weight],
                        table[i - 1][weight - w[i - 1]] + w[i - 1]);
                // vi is related with wi, so add wi into knapsack
            }
        }
        return table[n][W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
        scanner.close();
    }
}