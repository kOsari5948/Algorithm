import java.util.*;
import java.io.*;

class Main {
    static int N, K;
    static int[] parent = new int[100001];
    static int[] time = new int[100001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();

        // 순서대로 구하기 위해 목적지 부터 parent 배열을 stack 에 담았다가 다시 pop
        // 즉 목적지 부터 꺼꾸로 구하기
        Stack<Integer> stack = new Stack<>();
        stack.push(K);
        int index = K;

        while (index != N) {
            stack.push(parent[index]);
            index = parent[index];
        }

        // 최종 출력
        sb.append(time[K] - 1 + "\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }

        System.out.println(sb.toString());
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(N);
        time[N] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();

            if (now == K) return;

            for (int i=0; i<3; i++) {
                int next;

                if (i == 0)         next = now + 1;
                else if (i == 1)    next = now - 1;
                else                next = now * 2;

                if (next < 0 || next > 100000) continue;

                //어재와 같이 구하면서 시간을 추가 할때는 parent 배열에 저장하기
                if (time[next] == 0) {
                    q.add(next);
                    time[next] = time[now] + 1;
                    parent[next] = now;
                }
            }
        }
    }
}