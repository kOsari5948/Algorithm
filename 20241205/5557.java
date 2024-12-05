import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // 숫자의 개수를 입력받습니다.
        int[] numbers = new int[N]; // 숫자를 저장할 배열을 생성합니다.
        for (int i = 0; i < N; i++) {
            numbers[i] = scanner.nextInt(); // 숫자를 배열에 저장합니다.
        }
        scanner.close();

        long[][] dp = new long[N][21]; // 동적 프로그래밍을 위한 2차원 배열을 생성합니다.
        dp[0][numbers[0]] = 1; // 첫 번째 숫자를 초기화합니다.

        for (int i = 1; i < N - 1; i++) { // 두 번째 숫자부터 마지막 전 숫자까지 반복합니다.
            for (int j = 0; j <= 20; j++) { // 중간 결과가 0 이상 20 이하인 경우를 확인합니다.
                if (dp[i - 1][j] > 0) { // 이전 단계에서 가능한 경우만 처리합니다.
                    if (j + numbers[i] <= 20) { // 현재 숫자를 더했을 때 20 이하인 경우
                        dp[i][j + numbers[i]] += dp[i - 1][j]; // 경우의 수를 더합니다.
                    }
                    if (j - numbers[i] >= 0) { // 현재 숫자를 뺐을 때 0 이상인 경우
                        dp[i][j - numbers[i]] += dp[i - 1][j]; // 경우의 수를 더합니다.
                    }
                }
            }
        }

        System.out.println(dp[N - 2][numbers[N - 1]]); // 마지막 숫자와 비교하여 결과를 출력합니다.
    }
}
