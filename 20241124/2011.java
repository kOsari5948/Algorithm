import java.io.*;
import java.util.*;

public class Main {

    private static final int MOD = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int[] dp = new int[str.length()+1];
        dp[0] = 1;

        for(int i=1; i<=str.length(); i++) {
            int now = str.charAt(i-1) - '0';
            //현재가 0이 아니라면 숫자가 될 수 있다.
            if(now >= 1 && now <= 9) {
                dp[i] += dp[i-1];
                dp[i] %= MOD;
            }

            if(i == 1) continue; //첫번째 글자일 경우
            
            //이저 글자 확인
            int prev = str.charAt(i-2) - '0';

            if(prev == 0) continue; //0으로 시작할경우 존재 X

            //이전 글자까지해서 2글자 인지 확인
            int value = prev*10+now;

            //만약 2글자 이면 그것까지 될 수 있으니 더하기
            if(value >= 10 && value <= 26) {
                dp[i] += dp[i-2];
                dp[i] %= MOD;
            }
        }
        System.out.println(dp[str.length()]);
    }
}