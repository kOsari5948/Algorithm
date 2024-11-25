import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        // 날짜별 걸리는 시간, 금액 입력
        int[] time = new int[N + 2];
        int[] pay = new int[N + 2];
        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");

            time[i] = Integer.parseInt(input[0]);
            pay[i] = Integer.parseInt(input[1]);
        }

        // i + 1일에 돈 받음
        int[] dp = new int[N + 2];
        int max = 0; // i 일 까지의 최고 수익
        for (int i = 1; i <= N + 1; i++) {
            // i일 까지의 수익(dp[i])이 최대일 때 max값 갱신
            if (max < dp[i]) {
                max = dp[i];
            }
            // i 일에 상담하면 끝나는 날짜
            int day = i + time[i];
            // 퇴사 전까지 상담을 끝낼 수 있으면
            if (day <= N + 1) {
                // 상담 끝나는 날짜에 
                // '현재 날짜까지의 최대 금액 + 현재 상담 금액' 과
                // '현재 날짜에 기록된 금액' 
                // 중 더 큰 금액 입력
                dp[day] = Math.max(dp[day], max + pay[i]);
            }
        }

        System.out.println(max);
    }

}