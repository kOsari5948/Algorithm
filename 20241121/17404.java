import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n][3];
		
		int[][] dp = new int[n+1][3];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int red = Integer.parseInt(st.nextToken());
			int green= Integer.parseInt(st.nextToken());
			int blue= Integer.parseInt(st.nextToken());
			
			arr[i][0] = red;
			arr[i][1] = green;
			arr[i][2] = blue;
		}
		
		
		
		int res = Integer.MAX_VALUE;
		for(int k =0; k<3; k++) {
			// k는 처음 의 색
			for(int i = 0 ; i < 3; i++) {
                if(i == k) {
                	dp[1][i] = arr[0][i];
                }
                else dp[1][i] = 1000000000;
            }

			for(int i = 2; i<=n; i++) {
				/*
				 * 1번 집의 색은 2번, N번 집의 색과 같지 않아야 한다.
				 * N번 집의 색은 N-1번, 1번 집의 색과 같지 않아야 한다.
				 * i(2 ≤ i ≤ N-1)번 집의 색은 i-1, i+1번 집의 색과 같지 않아야 한다. 
				 */
				
				if(i==n) {
					//1번집과 이전 집이랑 색이 다른거 구하기
					if(k==0) {
						//빨강 빼고 넣기
						dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2])+arr[i-1][1];
						dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1])+arr[i-1][2];
					}
					
					if(k==1) {
						//초록 빼고 넣기
						dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2])+arr[i-1][0];
						dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1])+arr[i-1][2];
					}
					
					if(k==2) {
						//파랑 빼고 넣기
						dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2])+arr[i-1][0];
						dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2])+arr[i-1][1];
					}
					
					continue;
	 			}
				dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2])+arr[i-1][0];
				dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2])+arr[i-1][1];
				dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1])+arr[i-1][2];
				
			}
			
			for(int i=0; i<3; i++) {
				if(k==i) {
					continue;
				}
				res = Math.min(res, dp[n][i]);
			}
			
		}
		
		System.out.println(res);
	}
}