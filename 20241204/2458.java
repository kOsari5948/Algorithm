import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int res = 0;
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 인접행렬 기본적으로 false으로 초기화 => 만날 수 없다는 뜻
		boolean[][] adjMatrix = new boolean[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjMatrix[from][to] = true;
		} // end input. end create adjacent matrix

		// Floyd-Warshall: i노드에서 다른 노드를 경유해서라도 j로 갈 수 있는지 검사 
		for (int k = 1; k <= N; k++) {           // 경유 노드
			for (int i = 1; i <= N; i++) {       // 출발 노드
				if (i == k) continue;
				for (int j = 1; j <= N; j++) {   // 도착 노드
					if (i == j || j == k) continue;
					// i 에서 j로 갈 수 있음 
					if(adjMatrix[i][k] && adjMatrix[k][j]) {
						adjMatrix[i][j] = true;
					}
				}
			}
		} // end Floyd
		
		// 인접행렬에서, 어떤 수 n이 행일 때 true인 수 + 어떤 수 n이 열일 때 true인 수 = N-1이면 가능
        // n 뒤에 몇명 + n 앞에 몇명 = N-나 이면 나는 그 중간에 껴있다는 것이다. 
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			// i가 행일 때
			for (int j = 1; j <= N; j++) {
				if(i==j) continue;
				if(adjMatrix[i][j]) cnt++;
			}
			for (int j = 1; j <= N; j++) {
				if(i==j) continue;
				if(adjMatrix[j][i]) cnt++;
			}
			if(cnt==N-1) res++;
		}
		System.out.println(res);
	}
}