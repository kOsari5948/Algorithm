 

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int ans = Integer.MAX_VALUE;

        int[] memoryArr = new int[n];
        int[] costArr = new int[n];
        int[][] dp = new int[n][100001];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        // 비용과 메모리 초기화부분
        for (int i = 0; i < n; i++) {
            memoryArr[i] = Integer.parseInt(st1.nextToken());
            costArr[i] = Integer.parseInt(st2.nextToken());
        }


        //dp 실행
        for (int i = 0; i < n; i++) {
            int cost = costArr[i];
            int memory = memoryArr[i];


            for (int j = 0; j <= 10000; j++) {
                // 앱이 하나일 경우 예외처리
                if (i == 0) {
                    if (j >= cost){
                        dp[i][j] = memory;
                    }
                } else {
                    if (j >= cost){
                        //바로 이전 값 이랑 이전값에서 현재 메모리를 더한 값 비교
                        dp[i][j] = Math.max(dp[i - 1][j - cost] + memory, dp[i - 1][j]);
                    }
                    else {
                        //더 못넣으면 이전값이랑 동일
                        dp[i][j] = dp[i - 1][j];
                    }
                }

                // 문제에서 주어진 필요한 메모리보다 확보가능한 메모리가 클 경우 정답으로 저장
                if (dp[i][j] >= m) {
                    ans = Math.min(ans, j);
                }
            }
        }
        System.out.println(ans);
    }
}
