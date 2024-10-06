import java.util.*;
import java.io.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
 
import java.io.*;
import java.util.*;

public class Main {
    static int T;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        
        for(int t = 0; t<T; t++) {
            int files[]; //전체 파일 크기 저장
            int K = sc.nextInt();
            files = new int[K+1];
            int dp[][];  //dp 배열
            dp = new int[K+1][K+1];

            files[1] = sc.nextInt();//합 배열을 사용하기에 1부터 시작
            for(int i=2; i<=K; i++) {
                int tmp = sc.nextInt();
                files[i] = tmp+files[i-1]; //합 배열로 저장
            }


            for(int gap=1; gap<K; gap++) { //차이를 늘려가면서 계산
                for(int start = 1; start+gap <= K; start++) { //처음부터 시작해 갭만큼 뛰면서 계산
                    int end = start + gap;
                    dp[start][end] = Integer.MAX_VALUE;

                    for(int mid = start; mid<end ;mid++) {
                    	//최솟값 찾기
                        dp[start][end] = Math.min(dp[start][end],dp[start][mid]+dp[mid+1][end]+files[end]-files[start-1]);
                    }
                }
            }
            System.out.println(dp[1][K]);
        }
    }
}