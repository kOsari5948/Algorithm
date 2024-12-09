import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Main {
    static final int INF = 987654321; //초기화 값(최대 값)
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
 
        //노드들 초기화
        int[][] arr = new int[V + 1][V + 1];
 
        //같으면 0 다르면 무한대
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i != j) {
                    arr[i][j] = INF;
                }
            }
        }
 
        //입력 받기
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
 
            arr[a][b] = c;
        }
 
        // 플로이드 와샬 알고리즘 수행
        // i에서 j 까지 가는데 k를 거쳐서 가는 경로중 최솟값
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (i == j) {
                        continue;
                    }
 
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }
 
        // 모든 경로 살펴보기
        int ans = INF;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j) {
                    continue;
                }
                
                // 서로에게 가는 경로가 있다면, 사이클이 존재한다는 뜻.
                // i에서 j로 갈수 있고 j에서 i로 갈수 있으면 사이클이 있음
                // 그 사이클의 값이 최소인거 찾기
                if (arr[i][j] != INF && arr[j][i] != INF) {
                    ans = Math.min(ans, arr[i][j] + arr[j][i]);
                }
            }
        }
 
        // ans가 초기값이면 사이클이 존재하지 않음.
        ans = (ans == INF) ? -1 : ans;
 
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
 
}