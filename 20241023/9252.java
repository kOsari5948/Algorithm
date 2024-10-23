
 import java.io.*;
 import java.util.Stack;

 public class Main {
     static int[][] dp;
     static StringBuilder sb = new StringBuilder();
     
     public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


         char[] str1 = br.readLine().toCharArray();
         char[] str2 = br.readLine().toCharArray();

         int length_1 = str1.length;
         int length_2 = str2.length;

         dp = new int[length_1 + 1][length_2 + 1];

         for(int i = 1; i <= length_1; i++) {
             for(int j = 1; j <= length_2; j++) {
                 // (i-1)번째 문자 == (j-1)번째 문자
                 if(str1[i - 1] == str2[j - 1]) {
                 	//dp의 값은 대각선 위의 값 +1
                     dp[i][j] = dp[i - 1][j - 1] + 1;
                 } else {
                     // 일치 하지 않다면 해당 부분은 포함되지 않은 것임 그렇지만 이전까지 최대치를 누적해 줘야함
                     //이전 열(i-1)과 이전 행(j-1)의 값 중 큰 것으로 갱신
                     dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                 }
             }
         }
         
         //LCS 출력 부
         ToString(str1, length_1, length_2);
         System.out.println(dp[length_1][length_2]);
         System.out.println(sb);
     }
     
     //LCS출력은 결국 값이 달라지는 부분이 포함된 영역이 된다.
     //예) 길이가 1에서 2로 증가
	 //따라서 i 이전 값이 같으면 i를 줄이고 j의 이전값이 같으면 j를 줄여 다른값을 찾아서 
	 // 다른 값을 찾을 경우 출력후 대각선으로 올라가 준다.
     static void ToString(char[] str, int i, int j) {
         Stack<Character> st = new Stack<>();
         while (i > 0 && j > 0) {
             if (dp[i][j] == dp[i - 1][j]) {
                 i--;
             } else if (dp[i][j] == dp[i][j - 1]) {
                 j--;
             } else {
                 st.push(str[i-1]);
                 i--;
                 j--;
             }
         }
         while (!st.isEmpty()) {
             sb.append(st.pop());
         }
     }
 }