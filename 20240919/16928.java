import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int M;
    private static int[] ladder = new int[101];
    private static int[] snake = new int[101];
    private static boolean[] bl = new boolean[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //입력 받아서 사다리 스네이크 저장
        for(int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int before = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());

            if(i < N) ladder[before] = after;
            else snake[before] = after;
        }

        //que에서 하나씩 뽑아서 위치 이동
        Queue<Integer> que = new LinkedList<>();
        que.add(1);

        int cnt = 0;
        while(!que.isEmpty()) {
            int size = que.size();
            cnt++; //시간
            //외부에서 시간을 체크해야 1~6까지 주사위로 이동하는거 계산 가능

            for(int i = 0; i < size; i++) {
                int now = que.poll(); //최초 크기만큼 반복해서 큐 비우기
                for(int j = 1; j <= 6; j++) {
                	//이동 중 마지막이 넘어가면 다음 값
                    int current = now + j;
                    if(current > 100) continue;
                    
                    //현재 위치가 사다리라면 현재 위치는 사다리타고 이동
                    if(ladder[current] > 0) {
                        current = ladder[current];
                    }
                    //현재 위치가 사다리가 아닌 스네이크라면 
                    //뱀 타고 이동
                    else if(snake[current] > 0) {
                        current = snake[current];
                    }
                    //이미 방문한 위치라면 다음 진행
                    if(bl[current]) continue;

                    //도착할 경유 현재 시간 출력
                    if(current == 100) {
                        System.out.println(cnt);
                        return;
                    }
                    //이동 후에도 도착못했으면 큐에 내용 추가하고 
                    //현재위치 방문 체크
                    bl[current] = true;
                    que.add(current);
                }
            }
        }
    }
}