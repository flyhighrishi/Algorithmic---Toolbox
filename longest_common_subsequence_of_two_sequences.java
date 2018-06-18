import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class longest_common_subsequence_of_two_sequences 
{
    public static void main(String[] args) throws IOException{
         Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a[] = new int[n+1];
           for(int i=0; i<n; i++){
            a[i+1] = scanner.nextInt();
        }
		int m = scanner.nextInt();;
		int b[] = new int[m+1];
        for(int i=0; i<m; i++){
            b[i+1] = scanner.nextInt();
        }
        
        int count[][] = new int[n+1][m+1];
        String p = "";
        for(int i=0; i<=n; i++){
            for(int j=0; j<=m; j++){
                if(i==0 || j==0)
                    count[i][j] = 0;
                else{
                    if(a[i]==b[j])
                        count[i][j] = count[i-1][j-1] + 1;
                    else
                        count[i][j] = Math.max(count[i][j-1],count[i-1][j]);
                }
            }
        }
        int x = n;
        int y = m;
		
        while(x>0 && y>0){
            if(a[x]==b[y]){
                if(p=="")
                    p = Integer.toString(a[x]);
                else
                    p = Integer.toString(a[x]) + " " + p;
                x--;
                y--;
            }
            else if(count[x-1][y]>count[x][y-1])
                x--;
            else
                y--;         
     		}
			int g=p. codePointCount(0, (p.length()));
			if(g==1)
			{
			System.out.println(g);
			}
			else
			{
				if(g<1)
				{
					g=0;
					System.out.println(g);
				}
				else				
				System.out.println(g-1);
			}
    }
}