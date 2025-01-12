import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int start;
        int end;
        int weight;

        Node(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }

    }

    static int[] parent;
    static ArrayList<Node> edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            if (M == 0 && N == 0) {
                break;
            }

            edgeList = new ArrayList<>();

            int cost = 0; //기본 비용
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                edgeList.add(new Node(x, y, z));
                cost += z;
            }
            
            //가장 작은 거 부터 뽑기 위한 정렬
            Collections.sort(edgeList);

            parent = new int[M];
            for (int i = 0; i < M; i++) {
                parent[i] = i;
            }

            int minCost = 0; // 최소 비용
            // 가장 작은 거 부터 뽑는다 . 
            for (int i = 0; i < edgeList.size(); i++) {
                Node node = edgeList.get(i);

                //현재 이어 지지 않은 집이면
                if (find(node.start) != find(node.end)) {
                    minCost += node.weight;
                    union(node.start, node.end);
                }
            }

            bw.write((cost - minCost) + "\n"); // 비용 비교
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }
}
