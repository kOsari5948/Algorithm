import java.io.*;
import java.util.*;
 
public class Main {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
 
 
        int [][] arr = new int[n][m];
        int [][] dp = new int[n+1][m+1];
 
        for(int i=0; i<n; i++){
            String s1 = br.readLine();
            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(String.valueOf(s1.charAt(j)));
            }
        }
        
        //m이 1이면 아무리 N이 크더라도 정사각형의 크기는 1이 최대 
        if(m==1){
            for(int i=0; i<n; i++){
                if(arr[i][0]==1){
                    System.out.println(1);
                    return;
                }
            }
            System.out.println(0);
            return;
        }
        //n이 1이라도 동일
        if(n==1){
            for(int i=0; i<m; i++){
                if(arr[0][i]==1){
                    System.out.println(1);
                    return;
                }
            }
            System.out.println(0);
            return;
        }
        
        
        //첫번째 값을 초기화 시키기
        //1,1~1,m 초기 값 갱신
        for(int i=1; i<=m; i++){
            dp[1][i]=arr[0][i-1];
        }
        int result=0;
 
        for(int i=2; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(arr[i-1][j-1]==1){
                	// 그 후로는 3면에 값중 최소 +1 이러면 최초 초기화 이외는 주변이 0일테니 +1해도 1(자기자신)부터 시작함
                    dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                    result = Math.max(result,dp[i][j]);
                }
            }
        }
        System.out.println(result*result);
    }
}