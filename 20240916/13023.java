import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static int answer = 0;
    public static int N;
    public static int M;
    public static ArrayList<Integer>[] edgeList;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        String[] split = line.split(" ");

        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        visited = new boolean[N];
        edgeList = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            String line1 = br.readLine();
            String[] split1 = line1.split(" ");
            int from = Integer.parseInt(split1[0]);
            int to = Integer.parseInt(split1[1]);
            edgeList[from].add(to);
            edgeList[to].add(from);
        }

        for (int i = 0; i < N; i++) {
            if (answer != 1) dfs(i, 1);
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int start, int depth) {
        // 조건에 따라 모든 노드에서 시작해서 깊이가 5인것이 있는지 확인
        // 조건이 5개가 연결된 간선이 생기는 노드가 있는지 확인하는 것임
        if (depth == 5) {
            answer = 1;
            return;
        }
        visited[start] = true;
        for (int to : edgeList[start]) {
            if (!visited[to]) {
                dfs(to, depth + 1);
            }
        }
        visited[start] = false;
    }
}