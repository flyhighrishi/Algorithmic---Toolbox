import java.util.Scanner;
public class PlacingParentheses {
    public static long getMaximValue(String exp) {
        int n = exp.length() / 2 + 1;
        int[][] min = new int[n][n], max = new int[n][n];
        for(int i = 0; i < n; i++) {
            min[i][i] = exp.charAt(i*2) - '0';
            max[i][i] = exp.charAt(i*2) - '0';
        }
        for(int size = 1; size <= n - 1; size++) {
            for(int i = 0; i <= n - 1 - size; i++) {
                int j = size + i;  // maintain j - i = size
                int[] res = getMinAndMax(exp, i, j, min, max);
                min[i][j] = res[0];
                max[i][j] = res[1];
            }
        }
        return max[0][n - 1];
    }

   
    public static int[] getMinAndMax(String exp, int i, int j,
            int[][] min,
            int[][] max) {
        int[] res = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        for(int index = i; index <= j - 1; index++) {
            char oper = exp.charAt(index * 2 + 1);
            long a = eval(min[i][index], min[index + 1][j], oper),
                    b  = eval(min[i][index], max[index + 1][j], oper),
                    c  = eval(max[i][index], min[index + 1][j], oper),
                    d  = eval(max[i][index], max[index + 1][j], oper);
            res[0] = (int) Math.min(a, Math.min(b,
                    Math.min(c, Math.min(d, res[0]))));
            res[1] = (int) Math.max(a, Math.max(b,
                    Math.max(c, Math.max(d, res[1]))));
        }
        return res;
    }

    public static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
        scanner.close();
    }
}