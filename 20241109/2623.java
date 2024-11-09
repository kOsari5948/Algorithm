import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            a.add(new ArrayList<>());
        }

        // 입력 받기 단 입력을 받는 순서대로 그래프를 그린다.
        // 1 2 3 이 입력이 됬다면 그래프는ㄴ 1->2->3 이런식으로 그린다.
        //그렇게 하기 위해 0번 인덱스가 아닌 1번 인덱스를 시작으로 자신의 이전 값에 현재 값을 연결 시킨다.
        int[] indegree = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int before = Integer.parseInt(st.nextToken());
            for (int j = 1; j < num; j++) {
                int singer = Integer.parseInt(st.nextToken());
                a.get(before).add(singer);
                indegree[singer]++;

                before = singer;
            }
        }

        String ans = topologicalSort(a, indegree, N);

        bw.write(ans);
        bw.flush();
        bw.close();
        br.close();
    }

    // 위상 정렬
    public static String topologicalSort(ArrayList<ArrayList<Integer>> a, int[] indegree, int N) {

        //기존 위상 정렬과 동일 하지만 사이클을 확인한다.
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();

        //들어오는 간선이 0인 것들을 큐에 담는다.
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        //큐에서 하나씩 뽑으면서 뽑은 노드와 연결된 다른 노들들의 들어오는 간선을 하나씩 줄인다.
        while (!q.isEmpty()) {
            int now = q.poll();
            result.add(now);


            for (int next : a.get(now)) {
                indegree[next]--;
                // 줄인이후 들어오는 간선이 0이 되면 해당 노드를 추가로 선택한다.
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        // result 사이즈를 보았을 때 만약 노드수랑 일치하지 않는다면 중간에 사이클이 생겨 담지 못한 노드가 존재
        // why? 사이클이 생길경우 Node의 들어오는 간선은 무조건 1개가 존재 아닐경우 사이클이 아님
        // 예) 1->2->3->1 이 사이클에서는 1에는 3이 들어오고 2는 1 3은 2가 들어온다. 이때 들어오는 간선을 0을 만드는 방법은 없다.
        // 그렇게 사이클이 있으면 0을 출력하고 종료
        if (result.size() != N) {
            return "0\n";
        }

        //정상이라면 출력하자
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i) + "\n");
        }

        return sb.toString();

    }
}
