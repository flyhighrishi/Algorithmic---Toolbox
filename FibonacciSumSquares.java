import java.util.Scanner;

public class FibonacciSumSquares
 {
    public static int getFibonacciLastDigitFast(int n) 
	{
        if (n <= 1)
            return n;
			long sum=1;
        long[] result = new long[n + 1];
        result[0] = 0;
        result[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            result[i] = (result[i - 1] + result[i - 2]) % 10;
			sum=result[i]*result[i];
        }
        return sum%10d;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long c = getFibonacciLastDigitFast(n);
        System.out.println(c);
    }
}