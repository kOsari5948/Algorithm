import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
   public static void main(String[] args) throws NumberFormatException, IOException {
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int n = Integer.parseInt(br.readLine());
       
       //꼭지점 입력 받기
       int[][] arr = new int[n+1][2];
       for (int i = 0; i < n; i++) {
           StringTokenizer st = new StringTokenizer(br.readLine());
           arr[i][0] = Integer.parseInt(st.nextToken());
           arr[i][1] = Integer.parseInt(st.nextToken());
       }
       
       // 연산 시작 
       // 신발끈 공식을 사용하기 위해 마지막 배열은 처음과 동일하게 만들기
       arr[n][0] = arr[0][0];
       arr[n][1] = arr[0][1];

       //int면 에러 발생 long지정
       long sum = 0;
       for (int i = 0; i < n; i++) {
           sum += 1l*arr[i][0]*arr[i+1][1] - 1l*arr[i+1][0]*arr[i][1];
       }
       sum = Math.abs(sum);
       System.out.printf("%.1f", 1d*sum/2);
   }

}