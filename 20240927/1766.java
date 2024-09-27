import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
 
        int[] indegree = new int[N + 1];
        
        //먼저 풀어야하는 문제들
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
 
        for (int i = 0; i <= N; i++) {
            a.add(new ArrayList<>());
        }
 
        //먼저 풀어야하는 문제를 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int last = Integer.parseInt(st.nextToken());
 
            a.get(first).add(last);
            indegree[last]++; // 특정 문제의 번호보다 먼저 풀어야하는 문제의 개수
        }
 
        PriorityQueue<Integer> pq = new PriorityQueue<>();
 
        //혼자만 풀면 되는 문제를 먼저 저장
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) { 
                pq.offer(i);
            }
        }
 
        StringBuilder sb = new StringBuilder();
        
        //하나씩 추출하면서 연결된 문제를 해결
        while (!pq.isEmpty()) {
            int now = pq.poll();
            sb.append(now + " ");
 
            // now와 연결된 문제가 있는지 확인.
            for (int next : a.get(now)) {
                // now에 해당하는 문제를 풀었기때문에
                // next보다 먼저 풀어야하는 문제의 개수가 1개 줄어듦.
                indegree[next]--;
 
                // 먼저 풀어야하는 문제가 없는 경우
                // 새롭게 큐에 데이터를 집어넣는다.
                if (indegree[next] == 0) {
                    pq.offer(next);
                }
            }
        }
 
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
