import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    static int distance[][];
    static boolean visited[];
    static int [][] node;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodeNum = Integer.parseInt(st.nextToken());
        int wantNum = Integer.parseInt(st.nextToken());

        node = new int [nodeNum+1][nodeNum+1];
        distance = new int[nodeNum+1][nodeNum+1];

        // init Node
        for(int i=0; i<nodeNum-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            node[a][b] = 1;
            node[b][a] = 1;
            distance[a][b] = dis;
            distance[b][a] = dis;
        }

        // start bfs
        for(int i=0; i<wantNum; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b=  Integer.parseInt(st.nextToken());
            bfs(a, b, nodeNum);
        }
    }

    public static void bfs(int start, int end, int nodeNum){
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[nodeNum+1];

        visited[start] = true;
        queue.add(start);
        int ans[] = new int[nodeNum+1];

        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int i=1; i<=nodeNum; i++){

                if(node[cur][i] == 1 && !visited[i]){       // 만약 연결되어 있고 방문하지 않은 노드이면
                    ans[i] += distance[cur][i] + ans[cur];  // 거리를 갱신해준다.

                    if(i == end){           // 만약 마지막 노드이면 종료
                        System.out.println(ans[end]);
                        return;
                    }

                    queue.add(i);
                    visited[i] = true;
                }
            }
        }

    }



}
