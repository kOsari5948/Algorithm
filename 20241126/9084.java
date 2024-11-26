import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for (int q = 0; q < t; q++) {
        	//테스트 케이스 실행
            int n = Integer.parseInt(br.readLine());
            
            StringTokenizer st = new StringTokenizer(br.readLine());
        
            int[] coins = new int[n+1];
            
            //동전 저장
            for (int i = 1; i <= n; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            
            int m = Integer.parseInt(br.readLine());
            
            //dp는 0 부터 시작해서 입력된 숫자 까지
            int[] dp = new int[m+1];
            
            //dp 진행
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <=m; j++) {
                	//현재 금액에서 새로운 동전을 시도해 볼수 있는지
                    if (j - coins[i] > 0) {
                    	//있다면 해당 동전 횟수만큼 횟수를 추가할 수 있음 
                    	//예를 들어 1만 사용해서 4를 만들땐 1개 이지만 
                    	//2를 사용한다면 2만 사용 1만 사용 1과 2를 사용 으로 3개임
                    	//이게 현재 위치 4는 1인데 4-2인 2의 위치는 1로만 구성되거나 2로만 구성되거나로 2개임
                    	//즉 1과 2 더해서 3
                        dp[j] = dp[j] + dp[j-coins[i]];
                    } else if (j - coins[i] == 0) {
                    	//만약 뺏을때 현재 동전의 값어치와 동일하다면 걍 해당 동전으로만 이루어진 경우 1개만 추가해주면됨
                    	//2위치에서 2동전
                        dp[j]++;
                    }
                }
            }
            sb.append(dp[m] + "\n");
        }
        
        //최종 출력
        System.out.print(sb);
    }
}