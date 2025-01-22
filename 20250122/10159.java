import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] count;	//비교 가능한 물건 저장 배열
    static boolean[] visited;	//물건 방문 배열
    static List<List<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        count = new int[N+1];
        //입력값 그래프 형태로 변환
        for(int i=0;i<=N;i++)
            graph.add(new ArrayList<>());
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
        }
        //각 물건 기준으로 DFS 탐색 진행
        for(int i=1;i<=N;i++) {
            visited = new boolean[N+1];
            visited[i] = true;	//기준이 되는 물건 방문 확인
            dfs(i, i, 0);
        }
        //비교 불가능한 물건 개수 구해서 저장
        //점화식 : (전체 물건의 개수) - (비교 가능한 물건의 개수)
        for(int i=1;i<=N;i++)
            sb.append(N - count[i]).append("\n");
        bw.write(sb.toString());	//결과 BufferedWriter 저장
        bw.flush();			//결과 출력
        bw.close();
        br.close();
    }
    //DFS탐색 진행하는 함수
    private static void dfs(int idx,int root, int val) {
        count[idx]++;	//현재 기준이 되는 물건 +1
        //DFS탐색으로 하위 물건들 방문
        for(int nxt : graph.get(idx)) {
            if(visited[nxt])
                continue;
            visited[nxt] = true;
            count[root]++;	//방문한 물건 + 1
            dfs(nxt, root, val);
        }
    }
}
