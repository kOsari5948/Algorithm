import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
	// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int n = s.nextInt(); 
		int k = s.nextInt();
	
		int[][] dp = new int[201][201]; // k번 더해서 n이 되는 경우의 수
		
		for(int i=1;i<=k;i++) {
			dp[i][0]=1;
		}	
	
		for(int i=1;i<=k;i++) {
			for(int j=1;j<=n;j++) {
			dp[i][j] = (dp[i][j-1] + dp[i-1][j])%1000000000;
			}
		}
		
		System.out.println(dp[k][n]);
		}
}