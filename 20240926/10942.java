import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;
	static int[][] ans;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		
		N = Integer.parseInt(in.readLine());
		ans = new int[N][N];
		st = new StringTokenizer(in.readLine());
		arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		int M = Integer.parseInt(in.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			//실제 연산 수행 후 출력
			int res = solve(L-1,R-1);
			if(res == 1)sb.append(res).append("\n");
			else sb.append(0).append("\n");
		}
		
		System.out.println(sb);

	}

	public static int solve(int L, int R) {
		
		//1개이면 무조건 참
		if(L==R)return ans[L][R] = 1;
		
		//2개 일떄 가장 먼것만 비교
		if(L == R-1) {
			if(arr[L] ==arr[R])return ans[L][R] = 1;
			else return ans[L][R] = -1;
		}
		
		//만약 이미 나온 값이면 그대로 리턴
		if(ans[L][R] != 0)return ans[L][R];
		
		// 이미 앞에 부분은 전부 구했기만 가장 나중에 붙인 값들만 비교
		if((solve(L+1,R-1) == 1) && (arr[L] == arr[R]) ) {
			return ans[L][R] = 1;
		}
		else {
			return ans[L][R] = -1;
		}
	}
}