import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Function;

public class Main {

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 점화식 생성 https://nahwasa.com/entry/%EB%B0%B1%EC%A4%80-2133-%EC%9E%90%EB%B0%94-%ED%83%80%EC%9D%BC-%EC%B1%84%EC%9A%B0%EA%B8%B0-BOJ-2133-JAVA 다음 참고


        int n = Integer.parseInt(br.readLine());
        
        //홀수이면 만들기가 부라능 함
        if (n%2==1) {
            System.out.println(0);
            return;
        }


        //짝수만 계산 하기 때문에 배열을 절반만 만들기
        int[] dp = new int[Math.max(n/2, 2)];
        dp[0] = 3;
        dp[1] = 11;
        int tmp = 0;
        for (int i = 2; i < n/2; i++) {
            dp[i] = dp[i-1] * 3 + 2 + (tmp+=dp[i-2]*2);
        }
        System.out.println(dp[n/2-1]);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}