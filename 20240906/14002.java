import java.io.*;
import java.util.*;

public class Main {  
	static int N;
	static int[] list; // 수열 입력받는 배열
	static int[] dp;
	static int[] tmp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));   
        
		N = Integer.parseInt(br.readLine()); 
		list = new int[N + 1];
		dp = new int[N + 1];
		tmp = new int[N + 1];
        
		int result = 0;
		int resultIdx = 0;
        
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//데이터를 입력 받으면 서 동시에 dp 초기화
		for(int i = 0; i < N; i++) {
			//최초의 dp값은 본인이 있기에 1
			list[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
			
			//그 후 이전 입력중 자신보다 작고 dp+1이 현재 dp보다 높은 값을 찾아 추가 
			//이 때 이전 값의 위치를 저장하기 위해 tmp에 찾은 값 추가
			//만약 가장 큰게 나중에 나오면 나올 수록 업데이트 됨 tmp에
			for(int j = 0; j < i; j++){
				if(list[i] > list[j] && dp[j]+1 > dp[i]){
					dp[i] = dp[j] + 1;
					tmp[i] = j;
				}
			}
			
			//반복이후 체크된 dp 값이 result 보다 크다면 세로운 정답
			// 세로운 정답의 정답 값과 index를 갱신
			if(dp[i] > result){
				result = dp[i];
				resultIdx = i;
			}
		} 
        
		int[] answer = new int[result];
		int index = resultIdx;

		
		//정닶 index 부터 시작해서 temp를 바라보며 이전 값 저장
		for(int i = result - 1; i >= 0; i--) {
			answer[i] = list[index];
			index = tmp[index]; 
		}
        
		bw.write(result + "\n");
        
		//저장 된 값 차례로 출력
		for(int i = 0; i < result; i++) {
			bw.write(answer[i] + " \n");
		}
        
		bw.flush();
		br.close();
		bw.close();
	}	
}